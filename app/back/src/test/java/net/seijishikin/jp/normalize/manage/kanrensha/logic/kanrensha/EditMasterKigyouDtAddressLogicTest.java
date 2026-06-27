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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaKigyouDtDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditMasterKigyouDtAddressLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallForEditKigyouDtAccessEntityLogicTest.sql")
class EditMasterKigyouDtAddressLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private EditMasterKigyouDtAddressLogic editMasterKigyouDtAddressLogic;

    /** 企業団体住所マスタリポジトリ */
    @Autowired
    private KanrenshaKigyouDtAddressRepository kanrenshaKigyouDtAddressRepository;

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
        // 建物情報が追加
        kigyouDtDto0.getInputAddressDto().setAddressBuilding("三角ビル５５５号室");
        kigyouDtDto0.getInputAddressDto().setRsdtId("015");
        kigyouDtDto0.getInputAddressDto().setRsdt2Id("0555");
        capsuleDto0.setKanrenshaKigyouDtDto(kigyouDtDto0);

        Integer newId0 = editMasterKigyouDtAddressLogic.practice(capsuleDto0);

        KanrenshaKigyouDtAddressEntity addressEntity0 = kanrenshaKigyouDtAddressRepository.findById(newId0).get();
        assertEquals(kigyouDtDto0.getKigyouDtKanrenshaCode(), addressEntity0.getKigyouDtKanrenshaCode());
        assertEquals(kigyouDtDto0.getMasterId(), addressEntity0.getKanrenshaKigyouDtId());
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
        final Long preCount = kanrenshaKigyouDtAddressRepository.count();
        SaveKanrenshaKigyouDtCapsuleDto capsuleDto1 = new SaveKanrenshaKigyouDtCapsuleDto();
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto1.setKanrenshaKigyouDtDto(this.getDto());
        Integer newId1 = editMasterKigyouDtAddressLogic.practice(capsuleDto1);
        assertEquals(0, newId1);
        Long proCount = kanrenshaKigyouDtAddressRepository.count();
        assertEquals(preCount, proCount);

        // 変更があったので履歴追加
        SaveKanrenshaKigyouDtCapsuleDto capsuleDto2 = new SaveKanrenshaKigyouDtCapsuleDto();
        capsuleDto2.setUserDto(CreateLeastUserForTestUtil.practice());
        KanrenshaKigyouDtDto kigyouDtDto2 = this.getDto();
        kigyouDtDto2.getInputAddressDto().setLgCode("wsdfs");
        capsuleDto2.setKanrenshaKigyouDtDto(kigyouDtDto2);
        Integer oldId2 = kigyouDtDto2.getAddressId();
        Integer newId2 = editMasterKigyouDtAddressLogic.practice(capsuleDto2);
        KanrenshaKigyouDtAddressEntity addressEntity21 = kanrenshaKigyouDtAddressRepository.findById(oldId2).get();
        assertFalse(addressEntity21.getIsLatest());
        KanrenshaKigyouDtAddressEntity addressEntity20 = kanrenshaKigyouDtAddressRepository.findById(newId2).get();
        assertEquals(kigyouDtDto2.getInputAddressDto().getLgCode(), addressEntity20.getLgCode());
    }

    private KanrenshaKigyouDtDto getDto() {
        KanrenshaKigyouDtMasterEntity masterEntity = kanrenshaKigyouDtMasterRepository.findById(145).get();
        return getKanrenshaKigyouDtDtoService.practice(masterEntity);
    }

}
