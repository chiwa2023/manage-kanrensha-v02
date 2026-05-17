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

import net.seijishikin.jp.normalize.common_tool.dto.input.InputPersonNameDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputShokugyouDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaPersonDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditMasterPersonPropertyLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallForEditPersonAccessEntityLogicTest.sql")
class EditMasterPersonPropertyLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private EditMasterPersonPropertyLogic editMasterPersonPropertyLogic;

    /** 個人属性マスタリポジトリ */
    @Autowired
    private KanrenshaPersonPropertyRepository kanrenshaPersonPropertyRepository;

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

        // 自由記載職業だけに追加があった
        kigyouDtDto0.getInputShokugyouDto().setShokugyouUserWrite("何かの達人");
        capsuleDto0.setKanrenshaPersonDto(kigyouDtDto0);

        Integer newId0 = editMasterPersonPropertyLogic.practice(capsuleDto0);
        KanrenshaPersonPropertyEntity propertyEntity0 = kanrenshaPersonPropertyRepository.findById(newId0).get();

        // その他の項目は空文字比較
        assertEquals(kigyouDtDto0.getMasterId(), propertyEntity0.getKanrenshaPersonId());
        assertEquals(kigyouDtDto0.getPersonKanrenshaCode(), propertyEntity0.getPersonKanrenshaCode());
        assertEquals(kigyouDtDto0.getInputPersonNameDto().getAllName(), propertyEntity0.getKanrenshaName());

        InputPersonNameDto inputPersonNameDto = kigyouDtDto0.getInputPersonNameDto();
        assertEquals(inputPersonNameDto.getAllNameKana(), propertyEntity0.getAllNameKana());
        assertEquals(inputPersonNameDto.getLastName(), propertyEntity0.getLastName());
        assertEquals(inputPersonNameDto.getFirstName(), propertyEntity0.getFirstName());
        assertEquals(inputPersonNameDto.getMiddleName(), propertyEntity0.getMiddleName());
        assertEquals(inputPersonNameDto.getLastNameKana(), propertyEntity0.getLastNameKana());
        assertEquals(inputPersonNameDto.getFirstNameKana(), propertyEntity0.getFirstNameKana());
        assertEquals(inputPersonNameDto.getMiddleNameKana(), propertyEntity0.getMiddleNameKana());

        InputShokugyouDto inputShokugyouDto = kigyouDtDto0.getInputShokugyouDto();
        assertEquals(inputShokugyouDto.getGyoushu(), propertyEntity0.getGyoushu());
        assertEquals(inputShokugyouDto.getYakushoku(), propertyEntity0.getYakushoku());
        assertEquals(inputShokugyouDto.getShokugyouUserWrite(), propertyEntity0.getShokugyouUserWrite());
        assertEquals(inputShokugyouDto.getHoujinNo(), propertyEntity0.getKigyouDtNo());
        assertEquals(inputShokugyouDto.getHoujinName(), propertyEntity0.getKigyouDtName());
        assertEquals(inputShokugyouDto.getHoujinAddress(), propertyEntity0.getKigyouDtAddress());
        assertEquals(true, propertyEntity0.getIsShokyouEdit());
        assertEquals(false, propertyEntity0.getIsShokyouAccept());

        // 編集処理をしたが変更はなかったので何もしない
        final Long preCount = kanrenshaPersonPropertyRepository.count();
        SaveKanrenshaPersonCapsuleDto capsuleDto1 = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto1.setKanrenshaPersonDto(this.getDto());
        Integer newId1 = editMasterPersonPropertyLogic.practice(capsuleDto1);
        assertEquals(0, newId1);
        Long proCount = kanrenshaPersonPropertyRepository.count();
        assertEquals(preCount, proCount);

        // 変更があったので履歴追加
        SaveKanrenshaPersonCapsuleDto capsuleDto2 = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto2.setUserDto(CreateLeastUserForTestUtil.practice());
        KanrenshaPersonDto kigyouDtDto2 = this.getDto();
        kigyouDtDto2.getInputShokugyouDto().setShokugyouUserWrite("何かの達人");
        capsuleDto2.setKanrenshaPersonDto(kigyouDtDto2);
        Integer oldId2 = kigyouDtDto2.getPropertyId();
        Integer newId2 = editMasterPersonPropertyLogic.practice(capsuleDto2);
        KanrenshaPersonPropertyEntity propertyEntity21 = kanrenshaPersonPropertyRepository.findById(oldId2).get();
        assertFalse(propertyEntity21.getIsLatest());
        KanrenshaPersonPropertyEntity propertyEntity20 = kanrenshaPersonPropertyRepository.findById(newId2).get();

        assertEquals(kigyouDtDto2.getInputShokugyouDto().getShokugyouUserWrite(),
                propertyEntity20.getShokugyouUserWrite());
    }

    private KanrenshaPersonDto getDto() {
        KanrenshaPersonMasterEntity masterEntity = kanrenshaPersonMasterRepository.findById(391).get();
        return getKanrenshaPersonDtoService.practice(masterEntity);
    }

}
