package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAccessDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaSeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaSeijidantaiDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditMasterSeijidantaiAccessLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallForEditSeijidantaiAccessEntityLogicTest.sql")
class EditMasterSeijidantaiAccessLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private EditMasterSeijidantaiAccessLogic editMasterSeijidantaiAccessLogic;

    /** マスタ政治団体連絡先レポジトリ */
    @Autowired
    private KanrenshaSeijidantaiAccessRepository kanrenshaSeijidantaiAccessRepository;

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
        // 電話番号だけ足してもらった
        kigyouDtDto0.getInputAccessDto().setPhon1("123");
        kigyouDtDto0.getInputAccessDto().setPhon1("456");
        kigyouDtDto0.getInputAccessDto().setPhon1("7890");
        capsuleDto0.setKanrenshaSeijidantaiDto(kigyouDtDto0);

        Integer newId0 = editMasterSeijidantaiAccessLogic.practice(capsuleDto0);

        KanrenshaSeijidantaiAccessEntity accessEntity0 = kanrenshaSeijidantaiAccessRepository.findById(newId0).get();
        assertEquals(kigyouDtDto0.getSeijidantaiKanrenshaCode(), accessEntity0.getSeijidantaiKanrenshaCode());
        assertEquals(kigyouDtDto0.getMasterId(), accessEntity0.getKanrenshaSeijidantaiId());
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
        final Long preCount = kanrenshaSeijidantaiAccessRepository.count();
        SaveKanrenshaSeijidantaiCapsuleDto capsuleDto1 = new SaveKanrenshaSeijidantaiCapsuleDto();
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto1.setKanrenshaSeijidantaiDto(this.getDto());
        Integer newId1 = editMasterSeijidantaiAccessLogic.practice(capsuleDto1);
        assertEquals(0, newId1);
        Long proCount = kanrenshaSeijidantaiAccessRepository.count();
        assertEquals(preCount, proCount);

        // 変更があったので履歴追加
        SaveKanrenshaSeijidantaiCapsuleDto capsuleDto2 = new SaveKanrenshaSeijidantaiCapsuleDto();
        capsuleDto2.setUserDto(CreateLeastUserForTestUtil.practice());
        KanrenshaSeijidantaiDto kigyouDtDto2 = this.getDto();
        kigyouDtDto2.getInputAccessDto().setSnsAccount("erwg");
        capsuleDto2.setKanrenshaSeijidantaiDto(kigyouDtDto2);
        Integer oldId2 = kigyouDtDto2.getAccessId();
        Integer newId2 = editMasterSeijidantaiAccessLogic.practice(capsuleDto2);
        KanrenshaSeijidantaiAccessEntity accessEntity21 = kanrenshaSeijidantaiAccessRepository.findById(oldId2).get();
        assertFalse(accessEntity21.getIsLatest());
        KanrenshaSeijidantaiAccessEntity accessEntity20 = kanrenshaSeijidantaiAccessRepository.findById(newId2).get();
        assertEquals(kigyouDtDto2.getInputAccessDto().getSnsAccount(), accessEntity20.getSnsAccount());
    }

    private KanrenshaSeijidantaiDto getDto() {
        KanrenshaSeijidantaiMasterEntity masterEntity = kanrenshaSeijidantaiMasterRepository.findById(1013).get();
        return getKanrenshaSeijidantaiDtoService.practice(masterEntity);
    }

}
