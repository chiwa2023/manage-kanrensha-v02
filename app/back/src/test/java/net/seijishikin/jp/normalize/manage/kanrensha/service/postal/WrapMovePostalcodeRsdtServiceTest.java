package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.MovePostalCodeCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * WrapMovePostalcodeRsdtService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("WrapMovePostalcodeRsdtServiceTest.sql")
class WrapMovePostalcodeRsdtServiceTest {
    // CHECKSTYLE:OFF MagciNumber

    /** テスト対象 */
    @Autowired
    private WrapMovePostalcodeRsdtService wrapMovePostalcodeRsdtService;

    /** 正規郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** 正規郵便番号Repository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        MovePostalCodeCapsuleDto capsuleDto = new MovePostalCodeCapsuleDto();
        final String lgCode = "042072";
        // 9811201→1112222
        capsuleDto.setLgCode(lgCode);
        capsuleDto.setPostalOld1("981");
        capsuleDto.setPostalOld2("1201");
        capsuleDto.setPostalNew1("111");
        capsuleDto.setPostalNew2("2222");
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        assertEquals(2, wrapMovePostalcodeRsdtService.practice(capsuleDto));

        AddressPostalEntity postalEntityOld = addressPostalRepository.findById(13).get();
        assertFalse(postalEntityOld.getIsLatest());

        AddressPostalEntity postalEntityNew = addressPostalRepository.findById(45).get();
        assertTrue(postalEntityNew.getIsLatest());
        assertEquals(capsuleDto.getPostalNew1(), postalEntityNew.getPostalcode1());
        assertEquals(capsuleDto.getPostalNew2(), postalEntityNew.getPostalcode2());

        AddressPostalIrregularEntity irregularEntityOld = addressPostalIrregularRepository.findById(2).get();
        assertFalse(irregularEntityOld.getIsLatest());

        AddressPostalIrregularEntity irregularEntityNew = addressPostalIrregularRepository.findById(3).get();
        assertTrue(irregularEntityNew.getIsLatest());
        assertEquals(capsuleDto.getPostalNew1(), postalEntityNew.getPostalcode1());
        assertEquals(capsuleDto.getPostalNew2(), postalEntityNew.getPostalcode2());

        // 北海道旭川市神居町西丘8番地2号
        AddressRsdtTemplateEntity ansRsdtEntity01 = this.getAddressEntity(lgCode, 207301);
        assertEquals(false, ansRsdtEntity01.getIsLatest());

        AddressRsdtTemplateEntity ansRsdtEntity11 = this.getAddressEntity(lgCode, 207306);
        assertEquals(true, ansRsdtEntity11.getIsLatest());
        assertEquals(ansRsdtEntity01.getAddressBlock(), ansRsdtEntity11.getAddressBlock()); // 住所は変わらない
        assertEquals(capsuleDto.getPostalNew1(), ansRsdtEntity11.getPostalcode1());
        assertEquals(capsuleDto.getPostalNew2(), ansRsdtEntity11.getPostalcode2());

        // 北海道旭川市神居町西丘2番地2号
        AddressRsdtTemplateEntity ansRsdtEntity02 = this.getAddressEntity(lgCode, 207289);
        assertEquals(false, ansRsdtEntity02.getIsLatest());
        
        AddressRsdtTemplateEntity ansRsdtEntity12 = this.getAddressEntity(lgCode, 207305);
        assertEquals(true, ansRsdtEntity12.getIsLatest());
        assertEquals(ansRsdtEntity02.getAddressBlock(), ansRsdtEntity12.getAddressBlock()); // 住所は変わらない
        assertEquals(capsuleDto.getPostalNew1(), ansRsdtEntity12.getPostalcode1());
        assertEquals(capsuleDto.getPostalNew2(), ansRsdtEntity12.getPostalcode2());
    }

    private AddressRsdtTemplateEntity getAddressEntity(final String lgCode, final Integer rsdtId) {
        String sql = "SELECT * FROM address_rsdt_" + lgCode + "  WHERE address_rsdt_id = " + rsdtId;
        Query query = entityManager.createNativeQuery(sql, AddressRsdtTemplateEntity.class);
        return (AddressRsdtTemplateEntity) query.getSingleResult(); // NOPMD LawDemeter
    }

}
