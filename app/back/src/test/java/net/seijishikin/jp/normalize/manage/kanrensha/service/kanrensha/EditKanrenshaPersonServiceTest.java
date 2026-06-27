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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditKanrenshaPersonService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("EditKanrenshaPersonServiceTest.sql")
class EditKanrenshaPersonServiceTest {

    /** テスト対象 */
    @Autowired
    private EditKanrenshaPersonService editKanrenshaPersonService;
    

    /** 個人マスタリポジトリ */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /** 連絡先Repository */
    @Autowired
    private KanrenshaPersonAccessRepository kanrenshaPersonAccessRepository;

    /** 住所Repository */
    @Autowired
    private KanrenshaPersonAddressRepository kanrenshaPersonAddressRepository;

    /** 属性Repository */
    @Autowired
    private KanrenshaPersonPropertyRepository kanrenshaPersonPropertyRepository;

    /** 関連者個人取得Service */
    @Autowired
    private GetKanrenshaPersonDtoService getKanrenshaPersonDtoService;

    @Test
    @Tag("TableTruncate")
    void testNoChange() throws Exception {

        final Integer editId = 390;
        KanrenshaPersonMasterEntity masterEntity = kanrenshaPersonMasterRepository.findById(editId).get();
        KanrenshaPersonDto dto = getKanrenshaPersonDtoService.practice(masterEntity);
        SaveKanrenshaPersonCapsuleDto capsuleDto = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setKanrenshaPersonDto(dto);

        Integer sumId = editKanrenshaPersonService.practice(capsuleDto);
        assertEquals(0, sumId); // すべての更新がなく合計0
    }

    @Test
    @Tag("TableTruncate")
    void testAll() throws Exception {
        final Integer editId = 391;
        KanrenshaPersonMasterEntity masterEntity = kanrenshaPersonMasterRepository.findById(editId).get();
        KanrenshaPersonDto dto = getKanrenshaPersonDtoService.practice(masterEntity);
        dto.getInputShokugyouDto().setAllShokugyou("/saewvc"); // マスタを変更するとIdを変更するために全テーブルに波及
        SaveKanrenshaPersonCapsuleDto capsuleDto = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setKanrenshaPersonDto(dto);

        Integer sumId = editKanrenshaPersonService.practice(capsuleDto);
        assertNotEquals(0, sumId); // なんか更新した

        KanrenshaPersonMasterEntity lastMasterEntity = kanrenshaPersonMasterRepository.findAll().getLast();
        Integer nowMasterId = lastMasterEntity.getKanrenshaPersonMasterId();
        assertNotEquals(editId, nowMasterId); // テストがランダム実行なのでIdそのものの固定値特定はできないが、呼び出しIdとは異なる
        assertEquals(dto.getInputShokugyouDto().getAllShokugyou(), lastMasterEntity.getPersonShokugyou());

        KanrenshaPersonAccessEntity lastAccessEntity = kanrenshaPersonAccessRepository.findAll().getLast();
        assertEquals(nowMasterId, lastAccessEntity.getKanrenshaPersonId());

        KanrenshaPersonAddressEntity lastAddressEntity = kanrenshaPersonAddressRepository.findAll().getLast();
        assertEquals(nowMasterId, lastAddressEntity.getKanrenshaPersonId());

        KanrenshaPersonPropertyEntity lastPropertyEntity = kanrenshaPersonPropertyRepository.findAll().getLast();
        assertEquals(nowMasterId, lastPropertyEntity.getKanrenshaPersonId());
    }

    @Test
    @Tag("TableTruncate")
    void testAccessOnly() throws Exception {

        final Integer editId = 392;
        KanrenshaPersonMasterEntity masterEntity = kanrenshaPersonMasterRepository.findById(editId).get();
        KanrenshaPersonDto dto = getKanrenshaPersonDtoService.practice(masterEntity);
        dto.getInputAccessDto().setEmail("wefzdzg"); // メアドを変更しても全テーブルに影響が波及しない
        SaveKanrenshaPersonCapsuleDto capsuleDto = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setKanrenshaPersonDto(dto);

        Integer sumId = editKanrenshaPersonService.practice(capsuleDto);
        assertNotEquals(0, sumId); // なんか更新した

        KanrenshaPersonMasterEntity lastMasterEntity = kanrenshaPersonMasterRepository.findAll().getLast();
        Integer nowMasterId = lastMasterEntity.getKanrenshaPersonMasterId();
        assertEquals(editId, nowMasterId); // マスタは触らなかった

        KanrenshaPersonAccessEntity lastAccessEntity = kanrenshaPersonAccessRepository.findAll().getLast();
        assertEquals(nowMasterId, lastAccessEntity.getKanrenshaPersonId());

        KanrenshaPersonAddressEntity lastAddressEntity = kanrenshaPersonAddressRepository.findAll().getLast();
        assertEquals(nowMasterId, lastAddressEntity.getKanrenshaPersonId()); // マスタIdは変わっていない

        // 変更内容をテスト
        assertNotEquals(dto.getAccessId(), lastAccessEntity.getKanrenshaPersonAccessId());
        assertEquals(dto.getInputAccessDto().getEmail(), lastAccessEntity.getEmail());

        KanrenshaPersonPropertyEntity lastPropertyEntity = kanrenshaPersonPropertyRepository.findAll().getLast();
        assertEquals(nowMasterId, lastPropertyEntity.getKanrenshaPersonId());
    }


}
