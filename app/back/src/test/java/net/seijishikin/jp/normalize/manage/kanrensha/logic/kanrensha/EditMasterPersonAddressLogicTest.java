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

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaPersonDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditMasterPersonAddressLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallForEditPersonAccessEntityLogicTest.sql")
class EditMasterPersonAddressLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private EditMasterPersonAddressLogic editMasterPersonAddressLogic;

    /** 個人住所マスタリポジトリ */
    @Autowired
    private KanrenshaPersonAddressRepository kanrenshaPersonAddressRepository;

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
        // 建物情報が追加
        kigyouDtDto0.getInputAddressDto().setAddressBuilding("三角ビル５５５号室");
        kigyouDtDto0.getInputAddressDto().setRsdtId("015");
        kigyouDtDto0.getInputAddressDto().setRsdt2Id("0555");
        capsuleDto0.setKanrenshaPersonDto(kigyouDtDto0);

        Integer newId0 = editMasterPersonAddressLogic.practice(capsuleDto0);

        KanrenshaPersonAddressEntity addressEntity0 = kanrenshaPersonAddressRepository.findById(newId0).get();
        assertEquals(kigyouDtDto0.getPersonKanrenshaCode(), addressEntity0.getPersonKanrenshaCode());
        assertEquals(kigyouDtDto0.getMasterId(), addressEntity0.getKanrenshaPersonId());
        assertEquals(kigyouDtDto0.getInputPersonNameDto().getAllName(), addressEntity0.getKanrenshaName());

        // 新に追加した建物以外は空文字比較
        InputAddressDto inputAddressDto = kigyouDtDto0.getInputAddressDto();

        assertEquals(inputAddressDto.getAddressPostal(), addressEntity0.getAddressPostal());
        assertEquals(inputAddressDto.getAddressBlock(), addressEntity0.getAddressBlock());
        assertEquals(inputAddressDto.getAddressBuilding(), addressEntity0.getAddressBuilding());
        assertEquals(inputAddressDto.getPostalcode1(), addressEntity0.getPostalcode1());
        assertEquals(inputAddressDto.getPostalcode2(), addressEntity0.getPostalcode2());
        assertEquals(inputAddressDto.getLgCode(), addressEntity0.getLgCode());
        assertEquals(inputAddressDto.getMachiazaId(), addressEntity0.getMachiazaId());
        assertEquals(inputAddressDto.getBlkId(), addressEntity0.getBlkId());
        assertEquals(inputAddressDto.getPrcId(), addressEntity0.getPrcId());
        assertEquals(inputAddressDto.getRsdtId(), addressEntity0.getRsdtId());
        assertEquals(inputAddressDto.getRsdt2Id(), addressEntity0.getRsdt2Id());
        assertEquals(inputAddressDto.getIsPostalEdit(), addressEntity0.getIsPostalEdit());
        assertEquals(inputAddressDto.getIsBlockEdit(), addressEntity0.getIsBlockEdit());
        assertEquals(inputAddressDto.getIsBuildingEdit(), addressEntity0.getIsBuildingEdit());

        // 編集処理をしたが変更はなかったので何もしない
        final Long preCount = kanrenshaPersonAddressRepository.count();
        SaveKanrenshaPersonCapsuleDto capsuleDto1 = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto1.setKanrenshaPersonDto(this.getDto());
        Integer newId1 = editMasterPersonAddressLogic.practice(capsuleDto1);
        assertEquals(0, newId1);
        Long proCount = kanrenshaPersonAddressRepository.count();
        assertEquals(preCount, proCount);

        // 変更があったので履歴追加
        SaveKanrenshaPersonCapsuleDto capsuleDto2 = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto2.setUserDto(CreateLeastUserForTestUtil.practice());
        KanrenshaPersonDto kigyouDtDto2 = this.getDto();
        kigyouDtDto2.getInputAddressDto().setLgCode("wsdfs");
        capsuleDto2.setKanrenshaPersonDto(kigyouDtDto2);
        Integer oldId2 = kigyouDtDto2.getAddressId();
        Integer newId2 = editMasterPersonAddressLogic.practice(capsuleDto2);
        KanrenshaPersonAddressEntity addressEntity21 = kanrenshaPersonAddressRepository.findById(oldId2).get();
        assertFalse(addressEntity21.getIsLatest());
        KanrenshaPersonAddressEntity addressEntity20 = kanrenshaPersonAddressRepository.findById(newId2).get();
        assertEquals(kigyouDtDto2.getInputAddressDto().getLgCode(), addressEntity20.getLgCode());
    }

    private KanrenshaPersonDto getDto() {
        KanrenshaPersonMasterEntity masterEntity = kanrenshaPersonMasterRepository.findById(391).get();
        return getKanrenshaPersonDtoService.practice(masterEntity);
    }

}
