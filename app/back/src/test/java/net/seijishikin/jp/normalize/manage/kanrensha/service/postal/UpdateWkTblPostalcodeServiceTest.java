package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SaveWktblPostalCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalEditEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalEditRepository;

/**
 * UpdateWkTblPostalcodeService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("UpdateWkTblPostalcodeServiceTest.sql")
class UpdateWkTblPostalcodeServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private UpdateWkTblPostalcodeService updateWkTblPostalcodeService;

    /** ワークテーブル郵便番号差分Repository */
    @Autowired
    private WkTblPostalEditRepository wkTblPostalEditRepository;

    @Test
    void test() {

        // 誤って対象を指定した場合
        SaveWktblPostalCapsuleDto capsuleDto10 = new SaveWktblPostalCapsuleDto();
        capsuleDto10.getEditEntity().setWkTblPostalEditId(1111);
        assertThrows(EmptyResultDataAccessException.class, () -> updateWkTblPostalcodeService.practice(capsuleDto10));

        // 削除
        SaveWktblPostalCapsuleDto capsuleDto11 = new SaveWktblPostalCapsuleDto();
        final int deleteId = 626;
        capsuleDto11.getEditEntity().setWkTblPostalEditId(deleteId);
        capsuleDto11.setIsDelete(true);
        updateWkTblPostalcodeService.practice(capsuleDto11);
        WkTblPostalEditEntity ansEntity11 = wkTblPostalEditRepository.findById(deleteId).get();
        assertFalse(ansEntity11.getIsLatest());

        // 編集
        WkTblPostalEditEntity editEntity = new WkTblPostalEditEntity();
        final int editId = 627;

        editEntity.setWkTblPostalEditId(editId);
        editEntity.setLgCode("04207");
        editEntity.setPostalcode5("982  ");
        editEntity.setPostalcode7("9820046");
        editEntity.setPrefNameKana("ミヤギケン");
        editEntity.setCityNameKana("ナトリシ");
        editEntity.setOrgNameKana("ソウゴダイ");
        editEntity.setPrefName("宮城県");
        editEntity.setCityName("名取市");
        editEntity.setOrgName("相互台");
        editEntity.setFlgProp1("11");
        editEntity.setFlgProp2("12");
        editEntity.setFlgProp3("13");
        editEntity.setFlgProp4("14");
        editEntity.setFlgKoushin("1");
        editEntity.setFlgHenkouRiyu("4");
        editEntity.setFlgEdit("22");
        editEntity.setIsRepair(true);

        editEntity.setAddressPostalId(424);
        editEntity.setIsGyoseikuData(true);
        editEntity.setAddressPostalIrregularId(535);
        editEntity.setWorksText("aaaaa");

        SaveWktblPostalCapsuleDto capsuleDto12 = new SaveWktblPostalCapsuleDto();
        capsuleDto12.setEditEntity(editEntity);

        Integer newId2 = updateWkTblPostalcodeService.practice(capsuleDto12);

        WkTblPostalEditEntity ansEntity = wkTblPostalEditRepository.findById(newId2).get();

        assertEquals(editEntity.getLgCode(), ansEntity.getLgCode());
        assertEquals(editEntity.getPostalcode5(), ansEntity.getPostalcode5());
        assertEquals(editEntity.getPostalcode7(), ansEntity.getPostalcode7());
        assertEquals(editEntity.getPrefNameKana(), ansEntity.getPrefNameKana());
        assertEquals(editEntity.getCityNameKana(), ansEntity.getCityNameKana());
        assertEquals(editEntity.getOrgNameKana(), ansEntity.getOrgNameKana());
        assertEquals(editEntity.getPrefName(), ansEntity.getPrefName());
        assertEquals(editEntity.getCityName(), ansEntity.getCityName());
        assertEquals(editEntity.getOrgName(), ansEntity.getOrgName());
        assertEquals(editEntity.getFlgProp1(), ansEntity.getFlgProp1());
        assertEquals(editEntity.getFlgProp2(), ansEntity.getFlgProp2());
        assertEquals(editEntity.getFlgProp3(), ansEntity.getFlgProp3());
        assertEquals(editEntity.getFlgProp4(), ansEntity.getFlgProp4());
        assertEquals(editEntity.getFlgKoushin(), ansEntity.getFlgKoushin());
        assertEquals(editEntity.getFlgHenkouRiyu(), ansEntity.getFlgHenkouRiyu());

        assertEquals(editEntity.getFlgEdit(), ansEntity.getFlgEdit());
        assertEquals(editEntity.getIsRepair(), ansEntity.getIsRepair());

        assertEquals(editEntity.getAddressPostalId(), ansEntity.getAddressPostalId());
        assertEquals(editEntity.getIsGyoseikuData(), ansEntity.getIsGyoseikuData());
        assertEquals(editEntity.getAddressPostalIrregularId(), ansEntity.getAddressPostalIrregularId());
        assertEquals(editEntity.getWorksText(), ansEntity.getWorksText());

        WkTblPostalEditEntity ansEntity12 = wkTblPostalEditRepository.findById(editId).get();
        assertFalse(ansEntity12.getIsLatest());

    }

}
