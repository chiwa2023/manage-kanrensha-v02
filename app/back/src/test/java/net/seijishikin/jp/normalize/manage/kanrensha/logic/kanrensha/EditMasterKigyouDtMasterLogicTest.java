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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaKigyouDtDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditMasterKigyouDtMasterLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("EditMasterKigyouDtMasterLogicTest.sql")
class EditMasterKigyouDtMasterLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private EditMasterKigyouDtMasterLogic editMasterKigyouDtMasterLogic;

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
        kigyouDtDto0.setMasterId(144);
        // 建物情報が追加
        kigyouDtDto0.setHoujinNo("agerhbfxhz");
        capsuleDto0.setKanrenshaKigyouDtDto(kigyouDtDto0);

        Integer newId0 = editMasterKigyouDtMasterLogic.practice(capsuleDto0);

        KanrenshaKigyouDtMasterEntity masterEntity0 = kanrenshaKigyouDtMasterRepository.findById(newId0).get();
        assertEquals(kigyouDtDto0.getKigyouDtKanrenshaCode(), masterEntity0.getKigyouDtKanrenshaCode());
        assertEquals(kigyouDtDto0.getInputOrgNameDto().getOrgName(), masterEntity0.getKanrenshaName());

        assertEquals(kigyouDtDto0.getHoujinNo(), masterEntity0.getHoujinNo());
        assertEquals(kigyouDtDto0.getInputAddressDto().getAddressAll(), masterEntity0.getAllAddress());
        assertEquals(kigyouDtDto0.getOrgDelegateLeastDto().getPersonName(), masterEntity0.getKigyouDtDelegate());

        // 編集処理をしたが変更はなかったので何もしない
        final Long preCount = kanrenshaKigyouDtMasterRepository.count();
        SaveKanrenshaKigyouDtCapsuleDto capsuleDto1 = new SaveKanrenshaKigyouDtCapsuleDto();
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto1.setKanrenshaKigyouDtDto(this.getDto());
        Integer newId1 = editMasterKigyouDtMasterLogic.practice(capsuleDto1);
        assertEquals(0, newId1);
        Long proCount = kanrenshaKigyouDtMasterRepository.count();
        assertEquals(preCount, proCount);

        // 変更があったので履歴追加
        SaveKanrenshaKigyouDtCapsuleDto capsuleDto2 = new SaveKanrenshaKigyouDtCapsuleDto();
        capsuleDto2.setUserDto(CreateLeastUserForTestUtil.practice());
        KanrenshaKigyouDtDto kigyouDtDto2 = this.getDto();
        kigyouDtDto2.setHoujinNo("agerhbfxhz");
        capsuleDto2.setKanrenshaKigyouDtDto(kigyouDtDto2);
        Integer oldId2 = kigyouDtDto2.getMasterId();
        Integer newId2 = editMasterKigyouDtMasterLogic.practice(capsuleDto2);
        KanrenshaKigyouDtMasterEntity masterEntity21 = kanrenshaKigyouDtMasterRepository.findById(oldId2).get();
        assertFalse(masterEntity21.getIsLatest());
        KanrenshaKigyouDtMasterEntity masterEntity20 = kanrenshaKigyouDtMasterRepository.findById(newId2).get();
        assertEquals(kigyouDtDto2.getHoujinNo(), masterEntity20.getHoujinNo());
    }

    private KanrenshaKigyouDtDto getDto() {
        KanrenshaKigyouDtMasterEntity masterEntity = kanrenshaKigyouDtMasterRepository.findById(145).get();
        return getKanrenshaKigyouDtDtoService.practice(masterEntity);
    }

}
