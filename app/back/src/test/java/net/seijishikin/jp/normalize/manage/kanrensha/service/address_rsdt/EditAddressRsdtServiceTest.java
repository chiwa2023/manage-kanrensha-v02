package net.seijishikin.jp.normalize.manage.kanrensha.service.address_rsdt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.EditAddressRsdtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditAddressRsdtService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("EditAddressRsdtServiceTest.sql")
class EditAddressRsdtServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private EditAddressRsdtService editAddressRsdtService;

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 既存データの編集
        final String lgCode = "131016";
        final Integer deleteId = 624;

        AddressRsdtTemplateEntity srcEntity = this.getAddressEntity(lgCode, deleteId);
        AddressRsdtTemplateEntity baseEntity = new AddressRsdtTemplateEntity();
        BeanUtils.copyProperties(srcEntity, baseEntity);

        baseEntity.setPostalcode1("123");
        baseEntity.setPostalcode2("3456");
        baseEntity.setMachiazaId("0013018");
        baseEntity.setPrcId("017");
        baseEntity.setBlkId("334");
        baseEntity.setRsdtId("556");
        baseEntity.setRsdt2Id("778");
        baseEntity.setEffectDate(LocalDate.of(2022, 11, 19));
        baseEntity.setAbolishDate(LocalDate.of(2041, 2, 6));
        baseEntity.setAddressBlock("札幌市豊平区月寒東五条十八丁目aaa17番地11号");
        baseEntity.setAddressBuilding("99号室");

        EditAddressRsdtCapsuleDto capsuleDto = new EditAddressRsdtCapsuleDto();
        capsuleDto.setEditEntiy(baseEntity);
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        assertNotEquals(0, editAddressRsdtService.practice(capsuleDto));

        AddressRsdtTemplateEntity entityAnswer = this.getAddressEntity(lgCode, 627);
        assertTrue(entityAnswer.getIsLatest());

        assertEquals(baseEntity.getLgCode(), entityAnswer.getLgCode());
        assertEquals(baseEntity.getPostalcode1(), entityAnswer.getPostalcode1());
        assertEquals(baseEntity.getPostalcode2(), entityAnswer.getPostalcode2());
        assertEquals(baseEntity.getMachiazaId(), entityAnswer.getMachiazaId());
        assertEquals(baseEntity.getPrcId(), entityAnswer.getPrcId());
        assertEquals(baseEntity.getBlkId(), entityAnswer.getBlkId());
        assertEquals(baseEntity.getRsdtId(), entityAnswer.getRsdtId());
        assertEquals(baseEntity.getRsdt2Id(), entityAnswer.getRsdt2Id());
        assertEquals(baseEntity.getEffectDate(), entityAnswer.getEffectDate());
        assertEquals(baseEntity.getAbolishDate(), entityAnswer.getAbolishDate());
        assertEquals(baseEntity.getAddressBlock(), entityAnswer.getAddressBlock());
        assertEquals(baseEntity.getAddressBuilding(), entityAnswer.getAddressBuilding());

        // 新規
        AddressRsdtTemplateEntity newEntity = new AddressRsdtTemplateEntity();
        newEntity.setAddressRsdtId(0);
        newEntity.setLgCode(lgCode);
        newEntity.setPostalcode1("124");
        newEntity.setPostalcode2("3457");
        newEntity.setMachiazaId("0013019");
        newEntity.setPrcId("018");
        newEntity.setBlkId("335");
        newEntity.setRsdtId("557");
        newEntity.setRsdt2Id("779");
        newEntity.setEffectDate(LocalDate.of(2022, 11, 20));
        newEntity.setAbolishDate(LocalDate.of(2041, 2, 7));
        newEntity.setAddressBlock("札幌市豊平区月寒東五条十八丁目aaa17番地12号");
        newEntity.setAddressBuilding("100号室");

        EditAddressRsdtCapsuleDto capsuleDtoNew = new EditAddressRsdtCapsuleDto();
        capsuleDtoNew.setEditEntiy(newEntity);
        capsuleDtoNew.setUserDto(CreateLeastUserForTestUtil.practice());

        assertNotEquals(0, editAddressRsdtService.practice(capsuleDtoNew));

        AddressRsdtTemplateEntity entityAnswerNew = this.getAddressEntity(lgCode, 628);
        assertTrue(entityAnswerNew.getIsLatest());

        assertEquals(newEntity.getLgCode(), entityAnswerNew.getLgCode());
        assertEquals(newEntity.getPostalcode1(), entityAnswerNew.getPostalcode1());
        assertEquals(newEntity.getPostalcode2(), entityAnswerNew.getPostalcode2());
        assertEquals(newEntity.getMachiazaId(), entityAnswerNew.getMachiazaId());
        assertEquals(newEntity.getPrcId(), entityAnswerNew.getPrcId());
        assertEquals(newEntity.getBlkId(), entityAnswerNew.getBlkId());
        assertEquals(newEntity.getRsdtId(), entityAnswerNew.getRsdtId());
        assertEquals(newEntity.getRsdt2Id(), entityAnswerNew.getRsdt2Id());
        assertEquals(newEntity.getEffectDate(), entityAnswerNew.getEffectDate());
        assertEquals(newEntity.getAbolishDate(), entityAnswerNew.getAbolishDate());
        assertEquals(newEntity.getAddressBlock(), entityAnswerNew.getAddressBlock());
        assertEquals(newEntity.getAddressBuilding(), entityAnswerNew.getAddressBuilding());

    }

    private AddressRsdtTemplateEntity getAddressEntity(final String lgCode, final Integer rsdtId) {
        String sql = "SELECT * FROM address_rsdt_" + lgCode + "  WHERE address_rsdt_id = " + rsdtId;
        Query query = entityManager.createNativeQuery(sql, AddressRsdtTemplateEntity.class);
        return (AddressRsdtTemplateEntity) query.getSingleResult(); // NOPMD LawDemeter
    }

}
