package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * MovePostalcodeRsdtService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("MovePostalcodeRsdtServiceTest.sql")
class MovePostalcodeRsdtServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private MovePostalcodeRsdtService movePostalcodeRsdtService;

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void test() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        // このServiceの外側でTransaction を構成する
        entityManager.joinTransaction();

        final String lgCode = "012041";
        final String postalOld1 = "074";
        final String postalOld2 = "1181";
        final String postalNew1 = "996";
        final String postalNew2 = "9989";

        movePostalcodeRsdtService.practice(entityManager, userDto, lgCode, postalOld1, postalOld2, postalNew1,
                postalNew2);

        // 該当データは履歴に変更
        AddressRsdtTemplateEntity entity10 = this.getAddressEntity(lgCode, 207287);
        assertEquals(false, entity10.getIsLatest());
        AddressRsdtTemplateEntity entity11 = this.getAddressEntity(lgCode, 207288);
        assertEquals(false, entity11.getIsLatest());
        AddressRsdtTemplateEntity entity12 = this.getAddressEntity(lgCode, 207289);
        assertEquals(false, entity12.getIsLatest());

        // 該当データは履歴に変更
        AddressRsdtTemplateEntity entity20 = this.getAddressEntity(lgCode, 207305);
        assertEquals(true, entity20.getIsLatest());
        assertEquals(entity10.getAddressBlock(), entity20.getAddressBlock());
        assertEquals(postalNew1, entity20.getPostalcode1());
        assertEquals(postalNew2, entity20.getPostalcode2());

        AddressRsdtTemplateEntity entity21 = this.getAddressEntity(lgCode, 207306);
        assertEquals(true, entity21.getIsLatest());
        assertEquals(entity11.getAddressBlock(), entity21.getAddressBlock());
        assertEquals(postalNew1, entity21.getPostalcode1());
        assertEquals(postalNew2, entity21.getPostalcode2());

        AddressRsdtTemplateEntity entity22 = this.getAddressEntity(lgCode, 207307);
        assertEquals(true, entity22.getIsLatest());
        assertEquals(entity12.getAddressBlock(), entity22.getAddressBlock());
        assertEquals(postalNew1, entity22.getPostalcode1());
        assertEquals(postalNew2, entity22.getPostalcode2());

        entityManager.flush();
    }

    private AddressRsdtTemplateEntity getAddressEntity(final String lgCode, final Integer rsdtId) {
        String sql = "SELECT * FROM address_rsdt_" + lgCode + "  WHERE address_rsdt_id = " + rsdtId;
        Query query = entityManager.createNativeQuery(sql, AddressRsdtTemplateEntity.class);
        return (AddressRsdtTemplateEntity) query.getSingleResult(); // NOPMD LawDemeter
    }

}
