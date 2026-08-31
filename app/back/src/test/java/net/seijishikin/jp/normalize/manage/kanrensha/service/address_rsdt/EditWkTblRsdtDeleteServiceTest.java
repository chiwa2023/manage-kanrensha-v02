package net.seijishikin.jp.normalize.manage.kanrensha.service.address_rsdt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.EditWktblRsdtDeleteCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtDeleteEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtDeleteRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditWkTblRsdtDeleteService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("EditWkTblRsdtDeleteServiceTest.sql")
class EditWkTblRsdtDeleteServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private EditWkTblRsdtDeleteService editWkTblRsdtDeleteService;

    /** 住居差分ワークテーブル削除Repository */
    @Autowired
    private WkTblAddressRsdtDeleteRepository wkTblAddressRsdtDeleteRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 誤って削除対象を指定した場合
        EditWktblRsdtDeleteCapsuleDto capsuleDto10 = new EditWktblRsdtDeleteCapsuleDto();
        capsuleDto10.getEditEntity().setWkTblAddressRsdtDeleteId(111);
        assertThrows(EmptyResultDataAccessException.class, () -> editWkTblRsdtDeleteService.practice(capsuleDto10));

        // 変更
        WkTblAddressRsdtDeleteEntity entity0 = new WkTblAddressRsdtDeleteEntity();
        entity0.setLgCode("013589");
        entity0.setWkTblAddressRsdtDeleteId(525);
        entity0.setAddressRsdtId(803);
        entity0.setPostalcode1("11");
        entity0.setPostalcode2("12");
        entity0.setAddressBlock("13");
        entity0.setAddressBuilding("14");
        entity0.setMachiazaId("15");
        entity0.setBlkId("16");
        entity0.setPrcId("17");
        entity0.setRsdtId("18");
        entity0.setRsdt2Id("19");
        entity0.setEffectDate(LocalDate.of(2025, 6, 24));
        entity0.setAbolishDate(LocalDate.of(2026, 7, 11));

        EditWktblRsdtDeleteCapsuleDto capsuleDto0 = new EditWktblRsdtDeleteCapsuleDto();
        capsuleDto0.setEditEntity(entity0);
        capsuleDto0.setUserDto(CreateLeastUserForTestUtil.practice());

        Integer newId0 = editWkTblRsdtDeleteService.practice(capsuleDto0);
        WkTblAddressRsdtDeleteEntity baseEntity10 = wkTblAddressRsdtDeleteRepository.findById(newId0).get();
        assertTrue(baseEntity10.getIsLatest());
        assertEquals(entity0.getLgCode(), baseEntity10.getLgCode());
        assertEquals(entity0.getPostalcode1(), baseEntity10.getPostalcode1());
        assertEquals(entity0.getPostalcode2(), baseEntity10.getPostalcode2());
        assertEquals(entity0.getAddressBlock(), baseEntity10.getAddressBlock());
        assertEquals(entity0.getAddressBuilding(), baseEntity10.getAddressBuilding());
        assertEquals(entity0.getMachiazaId(), baseEntity10.getMachiazaId());
        assertEquals(entity0.getBlkId(), baseEntity10.getBlkId());
        assertEquals(entity0.getPrcId(), baseEntity10.getPrcId());
        assertEquals(entity0.getRsdtId(), baseEntity10.getRsdtId());
        assertEquals(entity0.getRsdt2Id(), baseEntity10.getRsdt2Id());
        assertEquals(entity0.getEffectDate(), baseEntity10.getEffectDate());
        assertEquals(entity0.getAbolishDate(), baseEntity10.getAbolishDate());

        WkTblAddressRsdtDeleteEntity oldEntity = wkTblAddressRsdtDeleteRepository.findById(525).get();
        assertFalse(oldEntity.getIsLatest());

        // 追加
        WkTblAddressRsdtDeleteEntity entity1 = new WkTblAddressRsdtDeleteEntity();
        entity1.setWkTblAddressRsdtDeleteId(0);
        entity1.setLgCode("11223344");
        entity1.setAddressRsdtId(0);
        entity1.setPostalcode1("121");
        entity1.setPostalcode2("122");
        entity1.setAddressBlock("123");
        entity1.setAddressBuilding("124");
        entity1.setMachiazaId("125");
        entity1.setBlkId("126");
        entity1.setPrcId("127");
        entity1.setRsdtId("128");
        entity1.setRsdt2Id("129");
        entity1.setEffectDate(LocalDate.of(2013, 4, 23));
        entity1.setAbolishDate(LocalDate.of(2014, 2, 9));

        EditWktblRsdtDeleteCapsuleDto capsuleDto1 = new EditWktblRsdtDeleteCapsuleDto();
        capsuleDto1.setEditEntity(entity1);
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());

        Integer newId1 = editWkTblRsdtDeleteService.practice(capsuleDto1);
        WkTblAddressRsdtDeleteEntity baseEntity11 = wkTblAddressRsdtDeleteRepository.findById(newId1).get();
        assertTrue(baseEntity11.getIsLatest());
        assertEquals(entity1.getLgCode(), baseEntity11.getLgCode());
        assertEquals(entity1.getPostalcode1(), baseEntity11.getPostalcode1());
        assertEquals(entity1.getPostalcode2(), baseEntity11.getPostalcode2());
        assertEquals(entity1.getAddressBlock(), baseEntity11.getAddressBlock());
        assertEquals(entity1.getAddressBuilding(), baseEntity11.getAddressBuilding());
        assertEquals(entity1.getMachiazaId(), baseEntity11.getMachiazaId());
        assertEquals(entity1.getBlkId(), baseEntity11.getBlkId());
        assertEquals(entity1.getPrcId(), baseEntity11.getPrcId());
        assertEquals(entity1.getRsdtId(), baseEntity11.getRsdtId());
        assertEquals(entity1.getRsdt2Id(), baseEntity11.getRsdt2Id());
        assertEquals(entity1.getEffectDate(), baseEntity11.getEffectDate());
        assertEquals(entity1.getAbolishDate(), baseEntity11.getAbolishDate());
    }

}
