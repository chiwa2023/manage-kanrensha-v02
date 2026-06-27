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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaKigyouDtDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditMasterKigyouDtPropertyLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallForEditKigyouDtAccessEntityLogicTest.sql")
class EditMasterKigyouDtPropertyLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private EditMasterKigyouDtPropertyLogic editMasterKigyouDtPropertyLogic;

    /** 企業団体属性マスタリポジトリ */
    @Autowired
    private KanrenshaKigyouDtPropertyRepository kanrenshaKigyouDtPropertyRepository;

    /** 関連者企業Dto取得Service */
    @Autowired
    private GetKanrenshaKigyouDtDtoService getKanrenshaKigyouDtDtoService;

    /** マスタRepository */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 今まで最小マスタ登録がされ、誰かが編集処理(強制で標準登録に移行)する場合は新しい履歴だけを積み上げる
        SaveKanrenshaKigyouDtCapsuleDto capsuleDto0 = new SaveKanrenshaKigyouDtCapsuleDto();
        capsuleDto0.setUserDto(CreateLeastUserForTestUtil.practice());
        KanrenshaKigyouDtDto kigyouDtDto0 = new KanrenshaKigyouDtDto();
        // 法人種別だけ足してもらった
        kigyouDtDto0.setHoujinSbts("999");
        capsuleDto0.setKanrenshaKigyouDtDto(kigyouDtDto0);

        Integer newId0 = editMasterKigyouDtPropertyLogic.practice(capsuleDto0);

        KanrenshaKigyouDtPropertyEntity propertyEntity0 = kanrenshaKigyouDtPropertyRepository.findById(newId0).get();
        assertEquals(kigyouDtDto0.getKigyouDtKanrenshaCode(), propertyEntity0.getKigyouDtKanrenshaCode());
        assertEquals(kigyouDtDto0.getMasterId(), propertyEntity0.getKanrenshaKigyouDtId());
        assertEquals(kigyouDtDto0.getInputOrgNameDto().getOrgName(), propertyEntity0.getKanrenshaName());

        assertEquals(kigyouDtDto0.getIsShiten(), propertyEntity0.getIsShiten());
        assertEquals(kigyouDtDto0.getOrgDelegateLeastDto().getPersonKanrenshaCode(),
                propertyEntity0.getOrgDelegateCode());
        assertEquals(kigyouDtDto0.getInputOrgNameDto().getOrgNameKana(), propertyEntity0.getOrgNameKana());
        assertEquals(kigyouDtDto0.getHoujinSbts(), propertyEntity0.getHoujinSbts());
        assertEquals(false, propertyEntity0.getIsForeign());

        // 編集処理をしたが変更はなかったので何もしない
        final Long preCount = kanrenshaKigyouDtPropertyRepository.count();
        SaveKanrenshaKigyouDtCapsuleDto capsuleDto1 = new SaveKanrenshaKigyouDtCapsuleDto();
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto1.setKanrenshaKigyouDtDto(this.getDto());
        Integer newId1 = editMasterKigyouDtPropertyLogic.practice(capsuleDto1);
        assertEquals(0, newId1);
        Long proCount = kanrenshaKigyouDtPropertyRepository.count();
        assertEquals(preCount, proCount);

        // 変更があったので履歴追加
        SaveKanrenshaKigyouDtCapsuleDto capsuleDto2 = new SaveKanrenshaKigyouDtCapsuleDto();
        capsuleDto2.setUserDto(CreateLeastUserForTestUtil.practice());
        KanrenshaKigyouDtDto kigyouDtDto2 = this.getDto();
        kigyouDtDto2.setHoujinSbts("vsdg");
        capsuleDto2.setKanrenshaKigyouDtDto(kigyouDtDto2);
        Integer oldId2 = kigyouDtDto2.getPropertyId();
        Integer newId2 = editMasterKigyouDtPropertyLogic.practice(capsuleDto2);
        KanrenshaKigyouDtPropertyEntity propertyEntity21 = kanrenshaKigyouDtPropertyRepository.findById(oldId2).get();
        assertFalse(propertyEntity21.getIsLatest());
        KanrenshaKigyouDtPropertyEntity propertyEntity20 = kanrenshaKigyouDtPropertyRepository.findById(newId2).get();
        assertEquals(kigyouDtDto2.getHoujinSbts(), propertyEntity20.getHoujinSbts());
    }

    private KanrenshaKigyouDtDto getDto() {
        KanrenshaKigyouDtMasterEntity masterEntity = kanrenshaKigyouDtMasterRepository.findById(145).get();
        return getKanrenshaKigyouDtDtoService.practice(masterEntity);
    }

}
