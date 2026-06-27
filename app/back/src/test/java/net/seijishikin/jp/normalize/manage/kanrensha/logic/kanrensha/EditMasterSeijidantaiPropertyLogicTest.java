package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaSeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaSeijidantaiDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditMasterSeijidantaiPropertyLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallForEditSeijidantaiAccessEntityLogicTest.sql")
class EditMasterSeijidantaiPropertyLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private EditMasterSeijidantaiPropertyLogic editMasterSeijidantaiPropertyLogic;

    /** 関連者政治団体住所リポジトリ */
    @Autowired
    private KanrenshaSeijidantaiPropertyRepository kanrenshaSeijidantaiPropertyRepository;

    /** 関連者政治団体Dto取得Service */
    @Autowired
    private GetKanrenshaSeijidantaiDtoService getKanrenshaSeijidantaiDtoService;

    /** マスタRepository */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 今まで最小マスタ登録がされ、誰かが編集処理(強制で標準登録に移行)する場合は新しい履歴だけを積み上げる
        SaveKanrenshaSeijidantaiCapsuleDto capsuleDto0 = new SaveKanrenshaSeijidantaiCapsuleDto();
        capsuleDto0.setUserDto(CreateLeastUserForTestUtil.practice());
        KanrenshaSeijidantaiDto kigyouDtDto0 = new KanrenshaSeijidantaiDto();
        // 会計責任者名称だけ足してもらった
        kigyouDtDto0.getAccounrMgrLeastDto().setPersonName("団体副代表 直子");
        capsuleDto0.setKanrenshaSeijidantaiDto(kigyouDtDto0);

        Integer newId0 = editMasterSeijidantaiPropertyLogic.practice(capsuleDto0);

        KanrenshaSeijidantaiPropertyEntity propertyEntity0 = kanrenshaSeijidantaiPropertyRepository.findById(newId0)
                .get();

        assertEquals(kigyouDtDto0.getMasterId(), propertyEntity0.getKanrenshaSeijidantaiId());
        assertEquals(kigyouDtDto0.getSeijidantaiKanrenshaCode(), propertyEntity0.getSeijidantaiKanrenshaCode());
        assertEquals(kigyouDtDto0.getInputOrgNameDto().getOrgName(), propertyEntity0.getKanrenshaName());

        assertEquals(kigyouDtDto0.getOrgDelegateLeastDto().getPersonKanrenshaCode(),
                propertyEntity0.getOrgDelegateCode());
        assertEquals(kigyouDtDto0.getInputOrgNameDto().getOrgNameKana(), propertyEntity0.getOrgNameKana());
        assertEquals(kigyouDtDto0.getAccounrMgrLeastDto().getPersonKanrenshaCode(),
                propertyEntity0.getAccountMgrCode());
        assertEquals(kigyouDtDto0.getAccounrMgrLeastDto().getPersonName(), propertyEntity0.getAccountMgrName());

        // 編集処理をしたが変更はなかったので何もしない
        final Long preCount = kanrenshaSeijidantaiPropertyRepository.count();
        SaveKanrenshaSeijidantaiCapsuleDto capsuleDto1 = new SaveKanrenshaSeijidantaiCapsuleDto();
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto1.setKanrenshaSeijidantaiDto(this.getDto());
        Integer newId1 = editMasterSeijidantaiPropertyLogic.practice(capsuleDto1);
        assertEquals(0, newId1);
        Long proCount = kanrenshaSeijidantaiPropertyRepository.count();
        assertEquals(preCount, proCount);

        // 変更があったので履歴追加
        SaveKanrenshaSeijidantaiCapsuleDto capsuleDto2 = new SaveKanrenshaSeijidantaiCapsuleDto();
        capsuleDto2.setUserDto(CreateLeastUserForTestUtil.practice());
        KanrenshaSeijidantaiDto kigyouDtDto2 = this.getDto();
        kigyouDtDto2.getAccounrMgrLeastDto().setPersonName("団体副代表 直子");
        capsuleDto2.setKanrenshaSeijidantaiDto(kigyouDtDto2);
        Integer oldId2 = kigyouDtDto2.getPropertyId();
        Integer newId2 = editMasterSeijidantaiPropertyLogic.practice(capsuleDto2);
        KanrenshaSeijidantaiPropertyEntity propertyEntity21 = kanrenshaSeijidantaiPropertyRepository.findById(oldId2)
                .get();
        assertFalse(propertyEntity21.getIsLatest());
        KanrenshaSeijidantaiPropertyEntity propertyEntity20 = kanrenshaSeijidantaiPropertyRepository.findById(newId2)
                .get();
        assertEquals(kigyouDtDto2.getAccounrMgrLeastDto().getPersonName(), propertyEntity20.getAccountMgrName());
    }

    private KanrenshaSeijidantaiDto getDto() {
        KanrenshaSeijidantaiMasterEntity masterEntity = kanrenshaSeijidantaiMasterRepository.findById(1013).get();
        return getKanrenshaSeijidantaiDtoService.practice(masterEntity);
    }

}
