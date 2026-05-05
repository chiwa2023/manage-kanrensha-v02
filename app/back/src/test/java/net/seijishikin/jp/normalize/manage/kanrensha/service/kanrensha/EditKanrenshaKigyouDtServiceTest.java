package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditKanrenshaKigyouDtService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("EditKanrenshaKigyouDtServiceTest.sql")
class EditKanrenshaKigyouDtServiceTest {

    /** テスト対象 */
    @Autowired
    private EditKanrenshaKigyouDtService editKanrenshaKigyouDtService;

    /** 企業団体マスタリポジトリ */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    /** 連絡先Repository */
    @Autowired
    private KanrenshaKigyouDtAccessRepository kanrenshaKigyouDtAccessRepository;

    /** 住所Repository */
    @Autowired
    private KanrenshaKigyouDtAddressRepository kanrenshaKigyouDtAddressRepository;

    /** 属性Repository */
    @Autowired
    private KanrenshaKigyouDtPropertyRepository kanrenshaKigyouDtPropertyRepository;

    /** 関連者企業取得Service */
    @Autowired
    private GetKanrenshaKigyouDtDtoService getKanrenshaKigyouDtDtoService;

    @Test
    @Tag("TableTruncate")
    void testNoChange() throws Exception {

        final Integer editId = 144;
        KanrenshaKigyouDtMasterEntity masterEntity = kanrenshaKigyouDtMasterRepository.findById(editId).get();
        KanrenshaKigyouDtDto dto = getKanrenshaKigyouDtDtoService.practice(masterEntity);
        SaveKanrenshaKigyouDtCapsuleDto capsuleDto = new SaveKanrenshaKigyouDtCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setKanrenshaKigyouDtDto(dto);

        Integer sumId = editKanrenshaKigyouDtService.practice(capsuleDto);
        assertEquals(0, sumId); // すべての更新がなく合計0
    }

    @Test
    @Tag("TableTruncate")
    void testAll() throws Exception {
        final Integer editId = 145;
        KanrenshaKigyouDtMasterEntity masterEntity = kanrenshaKigyouDtMasterRepository.findById(editId).get();
        KanrenshaKigyouDtDto dto = getKanrenshaKigyouDtDtoService.practice(masterEntity);
        dto.setHoujinNo("mnbvc"); // マスタを変更するとIdを変更するために全テーブルに波及
        SaveKanrenshaKigyouDtCapsuleDto capsuleDto = new SaveKanrenshaKigyouDtCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setKanrenshaKigyouDtDto(dto);

        Integer sumId = editKanrenshaKigyouDtService.practice(capsuleDto);
        assertNotEquals(0, sumId); // なんか更新した

        KanrenshaKigyouDtMasterEntity lastMasterEntity = kanrenshaKigyouDtMasterRepository.findAll().getLast();
        Integer nowMasterId = lastMasterEntity.getKanrenshaKigyouDtMasterId();
        assertNotEquals(editId, nowMasterId); // テストがランダム実行なのでIdそのものの固定値特定はできないが、呼び出しIdとは異なる
        assertEquals(dto.getHoujinNo(), lastMasterEntity.getHoujinNo());

        KanrenshaKigyouDtAccessEntity lastAccessEntity = kanrenshaKigyouDtAccessRepository.findAll().getLast();
        assertEquals(nowMasterId, lastAccessEntity.getKanrenshaKigyouDtId());

        KanrenshaKigyouDtAddressEntity lastAddressEntity = kanrenshaKigyouDtAddressRepository.findAll().getLast();
        assertEquals(nowMasterId, lastAddressEntity.getKanrenshaKigyouDtId());

        KanrenshaKigyouDtPropertyEntity lastPropertyEntity = kanrenshaKigyouDtPropertyRepository.findAll().getLast();
        assertEquals(nowMasterId, lastPropertyEntity.getKanrenshaKigyouDtId());
    }

    @Test
    @Tag("TableTruncate")
    void testAccessOnly() throws Exception {

        final Integer editId = 146;
        KanrenshaKigyouDtMasterEntity masterEntity = kanrenshaKigyouDtMasterRepository.findById(editId).get();
        KanrenshaKigyouDtDto dto = getKanrenshaKigyouDtDtoService.practice(masterEntity);
        dto.getInputAccessDto().setEmail("wefzdzg"); // メアドを変更しても全テーブルに影響が波及しない
        SaveKanrenshaKigyouDtCapsuleDto capsuleDto = new SaveKanrenshaKigyouDtCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setKanrenshaKigyouDtDto(dto);

        Integer sumId = editKanrenshaKigyouDtService.practice(capsuleDto);
        assertNotEquals(0, sumId); // なんか更新した

        KanrenshaKigyouDtMasterEntity lastMasterEntity = kanrenshaKigyouDtMasterRepository.findAll().getLast();
        Integer nowMasterId = lastMasterEntity.getKanrenshaKigyouDtMasterId();
        assertEquals(editId, nowMasterId); // マスタは触らなかった

        KanrenshaKigyouDtAccessEntity lastAccessEntity = kanrenshaKigyouDtAccessRepository.findAll().getLast();
        assertEquals(nowMasterId, lastAccessEntity.getKanrenshaKigyouDtId());

        KanrenshaKigyouDtAddressEntity lastAddressEntity = kanrenshaKigyouDtAddressRepository.findAll().getLast();
        assertEquals(nowMasterId, lastAddressEntity.getKanrenshaKigyouDtId()); // マスタIdは変わっていない

        // 変更内容をテスト
        assertNotEquals(dto.getAccessId(), lastAccessEntity.getKanrenshaKigyouDtAccessId());
        assertEquals(dto.getInputAccessDto().getEmail(), lastAccessEntity.getEmail());

        KanrenshaKigyouDtPropertyEntity lastPropertyEntity = kanrenshaKigyouDtPropertyRepository.findAll().getLast();
        assertEquals(nowMasterId, lastPropertyEntity.getKanrenshaKigyouDtId());
    }

}
