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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaKigyouDtDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditMasterKigyouDtAccessLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallForEditKigyouDtAccessEntityLogicTest.sql")
class EditMasterKigyouDtAccessLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private EditMasterKigyouDtAccessLogic editMasterKigyouDtAccessLogic;

    /** マスタ企業団体連絡先レポジトリ */
    @Autowired
    private KanrenshaKigyouDtAccessRepository kanrenshaKigyouDtAccessRepository;

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
        // 電話番号だけ足してもらった
        kigyouDtDto0.getInputAccessDto().setPhon1("123");
        kigyouDtDto0.getInputAccessDto().setPhon1("456");
        kigyouDtDto0.getInputAccessDto().setPhon1("7890");
        capsuleDto0.setKanrenshaKigyouDtDto(kigyouDtDto0);

        Integer newId0 = editMasterKigyouDtAccessLogic.practice(capsuleDto0);

        KanrenshaKigyouDtAccessEntity accessEntity0 = kanrenshaKigyouDtAccessRepository.findById(newId0).get();
        assertEquals(kigyouDtDto0.getKigyouDtKanrenshaCode(), accessEntity0.getKigyouDtKanrenshaCode());
        assertEquals(kigyouDtDto0.getMasterId(), accessEntity0.getKanrenshaKigyouDtId());
        assertEquals(kigyouDtDto0.getInputOrgNameDto().getOrgName(), accessEntity0.getKanrenshaName());

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
        final Long preCount = kanrenshaKigyouDtAccessRepository.count();
        SaveKanrenshaKigyouDtCapsuleDto capsuleDto1 = new SaveKanrenshaKigyouDtCapsuleDto();
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto1.setKanrenshaKigyouDtDto(this.getDto());
        Integer newId1 = editMasterKigyouDtAccessLogic.practice(capsuleDto1);
        assertEquals(0, newId1);
        Long proCount = kanrenshaKigyouDtAccessRepository.count();
        assertEquals(preCount, proCount);

        // 変更があったので履歴追加
        SaveKanrenshaKigyouDtCapsuleDto capsuleDto2 = new SaveKanrenshaKigyouDtCapsuleDto();
        capsuleDto2.setUserDto(CreateLeastUserForTestUtil.practice());
        KanrenshaKigyouDtDto kigyouDtDto2 = this.getDto();
        kigyouDtDto2.getInputAccessDto().setSnsAccount("erwg");
        capsuleDto2.setKanrenshaKigyouDtDto(kigyouDtDto2);
        Integer oldId2 = kigyouDtDto2.getAccessId();
        Integer newId2 = editMasterKigyouDtAccessLogic.practice(capsuleDto2);
        KanrenshaKigyouDtAccessEntity accessEntity21 = kanrenshaKigyouDtAccessRepository.findById(oldId2).get();
        assertFalse(accessEntity21.getIsLatest());
        KanrenshaKigyouDtAccessEntity accessEntity20 = kanrenshaKigyouDtAccessRepository.findById(newId2).get();
        assertEquals(kigyouDtDto2.getInputAccessDto().getSnsAccount(), accessEntity20.getSnsAccount());
    }

    private KanrenshaKigyouDtDto getDto() {
        KanrenshaKigyouDtMasterEntity masterEntity = kanrenshaKigyouDtMasterRepository.findById(145).get();
        return getKanrenshaKigyouDtDtoService.practice(masterEntity);
    }
}
