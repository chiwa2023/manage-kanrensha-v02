package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaSeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditKanrenshaSeijidantaiService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("EditKanrenshaSeijidantaiServiceTest.sql")
class EditKanrenshaSeijidantaiServiceTest {

    /** テスト対象 */
    @Autowired
    private EditKanrenshaSeijidantaiService editKanrenshaSeijidantaiService;

    /** 企業団体マスタリポジトリ */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    /** 連絡先Repository */
    @Autowired
    private KanrenshaSeijidantaiAccessRepository kanrenshaSeijidantaiAccessRepository;

    /** 住所Repository */
    @Autowired
    private KanrenshaSeijidantaiAddressRepository kanrenshaSeijidantaiAddressRepository;

    /** 属性Repository */
    @Autowired
    private KanrenshaSeijidantaiPropertyRepository kanrenshaSeijidantaiPropertyRepository;

    /** 関連者企業取得Service */
    @Autowired
    private GetKanrenshaSeijidantaiDtoService getKanrenshaSeijidantaiDtoService;

    @Test
    @Tag("TableTruncate")
    void testNoChange() throws Exception {

        final Integer editId = 1214;
        KanrenshaSeijidantaiMasterEntity masterEntity = kanrenshaSeijidantaiMasterRepository.findById(editId).get();
        KanrenshaSeijidantaiDto dto = getKanrenshaSeijidantaiDtoService.practice(masterEntity);
        SaveKanrenshaSeijidantaiCapsuleDto capsuleDto = new SaveKanrenshaSeijidantaiCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setKanrenshaSeijidantaiDto(dto);

        Integer sumId = editKanrenshaSeijidantaiService.practice(capsuleDto);
        assertEquals(0, sumId); // すべての更新がなく合計0
    }

    @Test
    @Tag("TableTruncate")
    void testAll() throws Exception {
        final Integer editId = 1215;
        KanrenshaSeijidantaiMasterEntity masterEntity = kanrenshaSeijidantaiMasterRepository.findById(editId).get();
        KanrenshaSeijidantaiDto dto = getKanrenshaSeijidantaiDtoService.practice(masterEntity);
        dto.setPoliOrgNo("mnbvc"); // マスタを変更するとIdを変更するために全テーブルに波及
        SaveKanrenshaSeijidantaiCapsuleDto capsuleDto = new SaveKanrenshaSeijidantaiCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setKanrenshaSeijidantaiDto(dto);

        Integer sumId = editKanrenshaSeijidantaiService.practice(capsuleDto);
        assertNotEquals(0, sumId); // なんか更新した

        KanrenshaSeijidantaiMasterEntity lastMasterEntity = kanrenshaSeijidantaiMasterRepository.findAll().getLast();
        Integer nowMasterId = lastMasterEntity.getKanrenshaSeijidantaiMasterId();
        assertNotEquals(editId, nowMasterId); // テストがランダム実行なのでIdそのものの固定値特定はできないが、呼び出しIdとは異なる
        assertEquals(dto.getPoliOrgNo(), lastMasterEntity.getPoliOrgNo());

        KanrenshaSeijidantaiAccessEntity lastAccessEntity = kanrenshaSeijidantaiAccessRepository.findAll().getLast();
        assertEquals(nowMasterId, lastAccessEntity.getKanrenshaSeijidantaiId());

        KanrenshaSeijidantaiAddressEntity lastAddressEntity = kanrenshaSeijidantaiAddressRepository.findAll().getLast();
        assertEquals(nowMasterId, lastAddressEntity.getKanrenshaSeijidantaiId());

        KanrenshaSeijidantaiPropertyEntity lastPropertyEntity = kanrenshaSeijidantaiPropertyRepository.findAll()
                .getLast();
        assertEquals(nowMasterId, lastPropertyEntity.getKanrenshaSeijidantaiId());
    }

    @Test
    @Tag("TableTruncate")
    void testAccessOnly() throws Exception {

        final Integer editId = 1216;
        KanrenshaSeijidantaiMasterEntity masterEntity = kanrenshaSeijidantaiMasterRepository.findById(editId).get();
        KanrenshaSeijidantaiDto dto = getKanrenshaSeijidantaiDtoService.practice(masterEntity);
        dto.getInputAccessDto().setEmail("wefzdzg"); // メアドを変更しても全テーブルに影響が波及しない
        SaveKanrenshaSeijidantaiCapsuleDto capsuleDto = new SaveKanrenshaSeijidantaiCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setKanrenshaSeijidantaiDto(dto);

        Integer sumId = editKanrenshaSeijidantaiService.practice(capsuleDto);
        assertNotEquals(0, sumId); // なんか更新した

        KanrenshaSeijidantaiMasterEntity lastMasterEntity = kanrenshaSeijidantaiMasterRepository.findAll().getLast();
        Integer nowMasterId = lastMasterEntity.getKanrenshaSeijidantaiMasterId();
        assertEquals(editId, nowMasterId); // マスタは触らなかった

        KanrenshaSeijidantaiAccessEntity lastAccessEntity = kanrenshaSeijidantaiAccessRepository.findAll().getLast();
        assertEquals(nowMasterId, lastAccessEntity.getKanrenshaSeijidantaiId());

        KanrenshaSeijidantaiAddressEntity lastAddressEntity = kanrenshaSeijidantaiAddressRepository.findAll().getLast();
        assertEquals(nowMasterId, lastAddressEntity.getKanrenshaSeijidantaiId()); // マスタIdは変わっていない

        // 変更内容をテスト
        assertNotEquals(dto.getAccessId(), lastAccessEntity.getKanrenshaSeijidantaiAccessId());
        assertEquals(dto.getInputAccessDto().getEmail(), lastAccessEntity.getEmail());

        KanrenshaSeijidantaiPropertyEntity lastPropertyEntity = kanrenshaSeijidantaiPropertyRepository.findAll()
                .getLast();
        assertEquals(nowMasterId, lastPropertyEntity.getKanrenshaSeijidantaiId());
    }

}
