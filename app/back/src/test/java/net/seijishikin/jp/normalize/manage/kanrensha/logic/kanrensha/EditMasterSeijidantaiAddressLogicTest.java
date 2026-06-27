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

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaSeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaSeijidantaiDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditMasterSeijidantaiAddressLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallForEditSeijidantaiAccessEntityLogicTest.sql")
class EditMasterSeijidantaiAddressLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private EditMasterSeijidantaiAddressLogic editMasterSeijidantaiAddressLogic;

    /** 政治団体住所マスタリポジトリ */
    @Autowired
    private KanrenshaSeijidantaiAddressRepository kanrenshaSeijidantaiAddressRepository;

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
        // 建物情報が追加
        kigyouDtDto0.getInputAddressDto().setAddressBuilding("三角ビル５５５号室");
        kigyouDtDto0.getInputAddressDto().setRsdtId("015");
        kigyouDtDto0.getInputAddressDto().setRsdt2Id("0555");
        capsuleDto0.setKanrenshaSeijidantaiDto(kigyouDtDto0);

        Integer newId0 = editMasterSeijidantaiAddressLogic.practice(capsuleDto0);

        KanrenshaSeijidantaiAddressEntity addressEntity0 = kanrenshaSeijidantaiAddressRepository.findById(newId0).get();
        assertEquals(kigyouDtDto0.getSeijidantaiKanrenshaCode(), addressEntity0.getSeijidantaiKanrenshaCode());
        assertEquals(kigyouDtDto0.getMasterId(), addressEntity0.getKanrenshaSeijidantaiId());
        assertEquals(kigyouDtDto0.getInputOrgNameDto().getOrgName(), addressEntity0.getKanrenshaName());

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
        final Long preCount = kanrenshaSeijidantaiAddressRepository.count();
        SaveKanrenshaSeijidantaiCapsuleDto capsuleDto1 = new SaveKanrenshaSeijidantaiCapsuleDto();
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto1.setKanrenshaSeijidantaiDto(this.getDto());
        Integer newId1 = editMasterSeijidantaiAddressLogic.practice(capsuleDto1);
        assertEquals(0, newId1);
        Long proCount = kanrenshaSeijidantaiAddressRepository.count();
        assertEquals(preCount, proCount);

        // 変更があったので履歴追加
        SaveKanrenshaSeijidantaiCapsuleDto capsuleDto2 = new SaveKanrenshaSeijidantaiCapsuleDto();
        capsuleDto2.setUserDto(CreateLeastUserForTestUtil.practice());
        KanrenshaSeijidantaiDto kigyouDtDto2 = this.getDto();
        kigyouDtDto2.getInputAddressDto().setLgCode("wsdfs");
        capsuleDto2.setKanrenshaSeijidantaiDto(kigyouDtDto2);
        Integer oldId2 = kigyouDtDto2.getAddressId();
        Integer newId2 = editMasterSeijidantaiAddressLogic.practice(capsuleDto2);
        KanrenshaSeijidantaiAddressEntity addressEntity21 = kanrenshaSeijidantaiAddressRepository.findById(oldId2)
                .get();
        assertFalse(addressEntity21.getIsLatest());
        KanrenshaSeijidantaiAddressEntity addressEntity20 = kanrenshaSeijidantaiAddressRepository.findById(newId2)
                .get();
        assertEquals(kigyouDtDto2.getInputAddressDto().getLgCode(), addressEntity20.getLgCode());
    }

    private KanrenshaSeijidantaiDto getDto() {
        KanrenshaSeijidantaiMasterEntity masterEntity = kanrenshaSeijidantaiMasterRepository.findById(1013).get();
        return getKanrenshaSeijidantaiDtoService.practice(masterEntity);
    }

}
