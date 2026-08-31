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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaPersonDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditMasterPersonMasterLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("EditMasterPersonMasterLogicTest.sql")
class EditMasterPersonMasterLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private EditMasterPersonMasterLogic editMasterPersonMasterLogic;

    /** 関連者企業Dto取得Service */
    @Autowired
    private GetKanrenshaPersonDtoService getKanrenshaPersonDtoService;

    /** マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 今まで最小マスタ登録がされ、誰かが編集処理(強制で標準登録に移行)する場合は新しい履歴だけを積み上げる
        SaveKanrenshaPersonCapsuleDto capsuleDto0 = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto0.setUserDto(CreateLeastUserForTestUtil.practice());
        KanrenshaPersonDto kigyouDtDto0 = new KanrenshaPersonDto();
        kigyouDtDto0.setMasterId(390);
        // 建物情報が追加
        kigyouDtDto0.getInputAddressDto().setAddressAll("住所変更");
        capsuleDto0.setKanrenshaPersonDto(kigyouDtDto0);

        Integer newId0 = editMasterPersonMasterLogic.practice(capsuleDto0);

        KanrenshaPersonMasterEntity masterEntity0 = kanrenshaPersonMasterRepository.findById(newId0).get();

        assertEquals(kigyouDtDto0.getPersonKanrenshaCode(), masterEntity0.getPersonKanrenshaCode());
        assertEquals(kigyouDtDto0.getInputPersonNameDto().getAllName(), masterEntity0.getKanrenshaName());

        assertEquals(kigyouDtDto0.getInputAddressDto().getAddressAll(), masterEntity0.getAllAddress());
        assertEquals(kigyouDtDto0.getInputShokugyouDto().getAllShokugyou(), masterEntity0.getPersonShokugyou());

        // 編集処理をしたが変更はなかったので何もしない
        final Long preCount = kanrenshaPersonMasterRepository.count();
        SaveKanrenshaPersonCapsuleDto capsuleDto1 = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto1.setKanrenshaPersonDto(this.getDto());
        Integer newId1 = editMasterPersonMasterLogic.practice(capsuleDto1);
        assertEquals(0, newId1);
        Long proCount = kanrenshaPersonMasterRepository.count();
        assertEquals(preCount, proCount);

        // 変更があったので履歴追加
        SaveKanrenshaPersonCapsuleDto capsuleDto2 = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto2.setUserDto(CreateLeastUserForTestUtil.practice());
        KanrenshaPersonDto kigyouDtDto2 = this.getDto();
        kigyouDtDto2.getInputAddressDto().setAddressAll("住所変更");
        capsuleDto2.setKanrenshaPersonDto(kigyouDtDto2);
        Integer oldId2 = kigyouDtDto2.getMasterId();
        Integer newId2 = editMasterPersonMasterLogic.practice(capsuleDto2);
        KanrenshaPersonMasterEntity masterEntity21 = kanrenshaPersonMasterRepository.findById(oldId2).get();
        assertFalse(masterEntity21.getIsLatest());
        KanrenshaPersonMasterEntity masterEntity20 = kanrenshaPersonMasterRepository.findById(newId2).get();
        assertEquals(kigyouDtDto2.getInputAddressDto().getAddressAll(), masterEntity20.getAllAddress());
    }

    private KanrenshaPersonDto getDto() {
        KanrenshaPersonMasterEntity masterEntity = kanrenshaPersonMasterRepository.findById(391).get();
        return getKanrenshaPersonDtoService.practice(masterEntity);
    }

}
