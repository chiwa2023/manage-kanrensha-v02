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

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAccessDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaPersonDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditMasterPersonAccessLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallForEditPersonAccessEntityLogicTest.sql")
class EditMasterPersonAccessLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private EditMasterPersonAccessLogic editMasterPersonAccessLogic;

    /** マスタ個人連絡先レポジトリ */
    @Autowired
    private KanrenshaPersonAccessRepository kanrenshaPersonAccessRepository;

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
        // 電話番号だけ足してもらった
        kigyouDtDto0.getInputAccessDto().setPhon1("123");
        kigyouDtDto0.getInputAccessDto().setPhon1("456");
        kigyouDtDto0.getInputAccessDto().setPhon1("7890");
        capsuleDto0.setKanrenshaPersonDto(kigyouDtDto0);

        Integer newId0 = editMasterPersonAccessLogic.practice(capsuleDto0);

        KanrenshaPersonAccessEntity accessEntity0 = kanrenshaPersonAccessRepository.findById(newId0).get();
        assertEquals(kigyouDtDto0.getPersonKanrenshaCode(), accessEntity0.getPersonKanrenshaCode());
        assertEquals(kigyouDtDto0.getMasterId(), accessEntity0.getKanrenshaPersonId());
        assertEquals(kigyouDtDto0.getInputPersonNameDto().getAllName(), accessEntity0.getKanrenshaName());

        // 新に追加した電話番号以外は空文字比較
        InputAccessDto inputAccessDto = kigyouDtDto0.getInputAccessDto();
        assertEquals(inputAccessDto.getPhon1(), accessEntity0.getPhon1());
        assertEquals(inputAccessDto.getPhon2(), accessEntity0.getPhon2());
        assertEquals(inputAccessDto.getPhon3(), accessEntity0.getPhon3());
        assertEquals(inputAccessDto.getEmail(), accessEntity0.getEmail());
        assertEquals(inputAccessDto.getMyPortalUrl(), accessEntity0.getMyPortalUrl());
        assertEquals(inputAccessDto.getSnsServiceId(), accessEntity0.getSnsServiceId());
        assertEquals(inputAccessDto.getSnsServiceCode(), accessEntity0.getSnsServiceCode());
        assertEquals(inputAccessDto.getSnsServiceName(), accessEntity0.getSnsServiceName());
        assertEquals(inputAccessDto.getSnsPortalUrl(), accessEntity0.getSnsPortalUrl());
        assertEquals(inputAccessDto.getSnsAccount(), accessEntity0.getSnsAccount());

        // 編集処理をしたが変更はなかったので何もしない
        final Long preCount = kanrenshaPersonAccessRepository.count();
        SaveKanrenshaPersonCapsuleDto capsuleDto1 = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto1.setKanrenshaPersonDto(this.getDto());
        Integer newId1 = editMasterPersonAccessLogic.practice(capsuleDto1);
        assertEquals(0, newId1);
        Long proCount = kanrenshaPersonAccessRepository.count();
        assertEquals(preCount, proCount);

        // 変更があったので履歴追加
        SaveKanrenshaPersonCapsuleDto capsuleDto2 = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto2.setUserDto(CreateLeastUserForTestUtil.practice());
        KanrenshaPersonDto kigyouDtDto2 = this.getDto();
        kigyouDtDto2.getInputAccessDto().setSnsAccount("erwg");
        capsuleDto2.setKanrenshaPersonDto(kigyouDtDto2);
        Integer oldId2 = kigyouDtDto2.getAccessId();
        Integer newId2 = editMasterPersonAccessLogic.practice(capsuleDto2);
        KanrenshaPersonAccessEntity accessEntity21 = kanrenshaPersonAccessRepository.findById(oldId2).get();
        assertFalse(accessEntity21.getIsLatest());
        KanrenshaPersonAccessEntity accessEntity20 = kanrenshaPersonAccessRepository.findById(newId2).get();
        assertEquals(kigyouDtDto2.getInputAccessDto().getSnsAccount(), accessEntity20.getSnsAccount());
    }

    private KanrenshaPersonDto getDto() {
        KanrenshaPersonMasterEntity masterEntity = kanrenshaPersonMasterRepository.findById(391).get();
        return getKanrenshaPersonDtoService.practice(masterEntity);
    }

}
