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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaSeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaSeijidantaiDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditMasterSeijidantaiMasterLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("EditMasterSeijidantaiMasterLogicTest.sql")
class EditMasterSeijidantaiMasterLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private EditMasterSeijidantaiMasterLogic editMasterSeijidantaiMasterLogic;

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
        KanrenshaSeijidantaiDto seijidantaiDto0 = new KanrenshaSeijidantaiDto();
        seijidantaiDto0.setMasterId(1012);
        // 建物情報が追加
        seijidantaiDto0.setPoliOrgNo("agerhbfxhz");
        capsuleDto0.setKanrenshaSeijidantaiDto(seijidantaiDto0);

        Integer newId0 = editMasterSeijidantaiMasterLogic.practice(capsuleDto0);

        KanrenshaSeijidantaiMasterEntity masterEntity0 = kanrenshaSeijidantaiMasterRepository.findById(newId0).get();

        assertEquals(seijidantaiDto0.getSeijidantaiKanrenshaCode(), masterEntity0.getSeijidantaiKanrenshaCode());
        assertEquals(seijidantaiDto0.getInputOrgNameDto().getOrgName(), masterEntity0.getKanrenshaName());

        assertEquals(seijidantaiDto0.getInputAddressDto().getAddressAll(), masterEntity0.getAllAddress());
        assertEquals(seijidantaiDto0.getOrgDelegateLeastDto().getPersonName(), masterEntity0.getSeijidantaiDelegate());
        assertEquals(seijidantaiDto0.getPoliOrgNo(), masterEntity0.getPoliOrgNo());
        assertEquals(seijidantaiDto0.getDantaiKbn(), masterEntity0.getDantaiKbn());

        // 編集処理をしたが変更はなかったので何もしない
        final Long preCount = kanrenshaSeijidantaiMasterRepository.count();
        SaveKanrenshaSeijidantaiCapsuleDto capsuleDto1 = new SaveKanrenshaSeijidantaiCapsuleDto();
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto1.setKanrenshaSeijidantaiDto(this.getDto());
        Integer newId1 = editMasterSeijidantaiMasterLogic.practice(capsuleDto1);
        assertEquals(0, newId1);
        Long proCount = kanrenshaSeijidantaiMasterRepository.count();
        assertEquals(preCount, proCount);

        // 変更があったので履歴追加
        SaveKanrenshaSeijidantaiCapsuleDto capsuleDto2 = new SaveKanrenshaSeijidantaiCapsuleDto();
        capsuleDto2.setUserDto(CreateLeastUserForTestUtil.practice());
        KanrenshaSeijidantaiDto SeijidantaiDto2 = this.getDto();
        SeijidantaiDto2.setPoliOrgNo("agerhbfxhz");
        capsuleDto2.setKanrenshaSeijidantaiDto(SeijidantaiDto2);
        Integer oldId2 = SeijidantaiDto2.getMasterId();
        Integer newId2 = editMasterSeijidantaiMasterLogic.practice(capsuleDto2);
        KanrenshaSeijidantaiMasterEntity masterEntity21 = kanrenshaSeijidantaiMasterRepository.findById(oldId2).get();
        assertFalse(masterEntity21.getIsLatest());
        KanrenshaSeijidantaiMasterEntity masterEntity20 = kanrenshaSeijidantaiMasterRepository.findById(newId2).get();
        assertEquals(SeijidantaiDto2.getPoliOrgNo(), masterEntity20.getPoliOrgNo());
    }

    private KanrenshaSeijidantaiDto getDto() {
        KanrenshaSeijidantaiMasterEntity masterEntity = kanrenshaSeijidantaiMasterRepository.findById(1013).get();
        return getKanrenshaSeijidantaiDtoService.practice(masterEntity);
    }

}
