package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.ShinseiStatusConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeAcceptCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaCodeMoveEntity;
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
 * MoveCodeMasterPersonService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("MoveCodeMasterPersonServiceTest.sql")
class MoveCodeMasterPersonServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private MoveCodeMasterPersonService moveCodeMasterPersonService;

    /** 関連者個人マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /** 関連者個人住所Repository */
    @Autowired
    private KanrenshaPersonAddressRepository kanrenshaPersonAddressRepository;

    /** 関連者個人連絡先Repository */
    @Autowired
    private KanrenshaPersonAccessRepository kanrenshaPersonAccessRepository;

    /** 関連者個人属性Repository */
    @Autowired
    private KanrenshaPersonPropertyRepository kanrenshaPersonPropertyRepository;

    @Test
    @Tag("TableTruncate")
    void testOrginLast() throws Exception {

        MoveKanrenshaCodeAcceptCapsuleDto capsuleDto = new MoveKanrenshaCodeAcceptCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        KanrenshaCodeMoveEntity moveEntity = new KanrenshaCodeMoveEntity();

        final String orgCode = "12345";
        moveEntity.setMoveStatus(ShinseiStatusConstants.ACCEPT);
        moveEntity.setAbolishKanrenshaCode("98765");
        moveEntity.setOriginKanrenshaCode(orgCode);
        moveEntity.setIsAbolishLast(false); // 元コードを最新にする

        moveEntity.setKanrenshaKbn(KanrenshaKbnConstants.PERSON);
        moveEntity.setAbolishKanrenshaName("廃止名称");
        moveEntity.setOriginName("残す名称");
        moveEntity.setMoveReason("移動理由");

        capsuleDto.setKanrenshaCodeMoveEntity(moveEntity);

        assertTrue(moveCodeMasterPersonService.practice(capsuleDto));

        // 廃止コード最新・履歴と元コード履歴が履歴となる
        // 挿入時間が維持されているので、必要に応じて挿入時間順に並べれば時系列に整列
        List<KanrenshaPersonMasterEntity> listMasterHistory = kanrenshaPersonMasterRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonMasterIdDesc(orgCode, false);
        assertEquals(5, listMasterHistory.size());
        KanrenshaPersonMasterEntity masterEntity00 = listMasterHistory.get(0);
        assertEquals(false, masterEntity00.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎2a", masterEntity00.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 26, 16, 30, 24), masterEntity00.getInsertTimestamp());

        KanrenshaPersonMasterEntity masterEntity01 = listMasterHistory.get(1);
        assertEquals(false, masterEntity01.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎2b", masterEntity01.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 23, 16, 30, 24), masterEntity01.getInsertTimestamp());

        KanrenshaPersonMasterEntity masterEntity02 = listMasterHistory.get(2);
        assertEquals(false, masterEntity02.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎2c", masterEntity02.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 24, 16, 30, 24), masterEntity02.getInsertTimestamp());

        KanrenshaPersonMasterEntity masterEntity03 = listMasterHistory.get(3);
        assertEquals(false, masterEntity03.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎1c", masterEntity03.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 22, 16, 30, 24), masterEntity03.getInsertTimestamp());

        KanrenshaPersonMasterEntity masterEntity04 = listMasterHistory.get(4);
        assertEquals(false, masterEntity04.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎1b", masterEntity04.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 25, 16, 30, 24), masterEntity04.getInsertTimestamp());

        List<KanrenshaPersonAddressEntity> listAddressHistory = kanrenshaPersonAddressRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonAddressIdDesc(orgCode, false);
        assertEquals(5, listAddressHistory.size());
        KanrenshaPersonAddressEntity addressEntity00 = listAddressHistory.get(0);
        assertEquals(false, addressEntity00.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎2a", addressEntity00.getKanrenshaName());

        KanrenshaPersonAddressEntity addressEntity01 = listAddressHistory.get(1);
        assertEquals(false, addressEntity01.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎2b", addressEntity01.getKanrenshaName());

        KanrenshaPersonAddressEntity addressEntity02 = listAddressHistory.get(2);
        assertEquals(false, addressEntity02.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎2c", addressEntity02.getKanrenshaName());

        KanrenshaPersonAddressEntity addressEntity03 = listAddressHistory.get(3);
        assertEquals(false, addressEntity03.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎1c", addressEntity03.getKanrenshaName());

        KanrenshaPersonAddressEntity addressEntity04 = listAddressHistory.get(4);
        assertEquals(false, addressEntity04.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎1b", addressEntity04.getKanrenshaName());

        List<KanrenshaPersonAccessEntity> listAccessHistory = kanrenshaPersonAccessRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonAccessIdDesc(orgCode, false);
        assertEquals(5, listAccessHistory.size());
        KanrenshaPersonAccessEntity accessEntity00 = listAccessHistory.get(0);
        assertEquals(false, accessEntity00.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎2a", accessEntity00.getKanrenshaName());

        KanrenshaPersonAccessEntity accessEntity01 = listAccessHistory.get(1);
        assertEquals(false, accessEntity01.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎2b", accessEntity01.getKanrenshaName());

        KanrenshaPersonAccessEntity accessEntity02 = listAccessHistory.get(2);
        assertEquals(false, accessEntity02.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎2c", accessEntity02.getKanrenshaName());

        KanrenshaPersonAccessEntity accessEntity03 = listAccessHistory.get(3);
        assertEquals(false, accessEntity03.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎1c", accessEntity03.getKanrenshaName());

        KanrenshaPersonAccessEntity accessEntity04 = listAccessHistory.get(4);
        assertEquals(false, accessEntity04.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎1b", accessEntity04.getKanrenshaName());

        List<KanrenshaPersonPropertyEntity> listPropertyHistory = kanrenshaPersonPropertyRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonPropertyIdDesc(orgCode, false);
        assertEquals(5, listPropertyHistory.size());
        KanrenshaPersonPropertyEntity propertyEntity00 = listPropertyHistory.get(0);
        assertEquals(false, propertyEntity00.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎2a", propertyEntity00.getKanrenshaName());

        KanrenshaPersonPropertyEntity propertyEntity01 = listPropertyHistory.get(1);
        assertEquals(false, propertyEntity01.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎2b", propertyEntity01.getKanrenshaName());

        KanrenshaPersonPropertyEntity propertyEntity02 = listPropertyHistory.get(2);
        assertEquals(false, propertyEntity02.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎2c", propertyEntity02.getKanrenshaName());

        KanrenshaPersonPropertyEntity propertyEntity03 = listPropertyHistory.get(3);
        assertEquals(false, propertyEntity03.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎1c", propertyEntity03.getKanrenshaName());

        KanrenshaPersonPropertyEntity propertyEntity04 = listPropertyHistory.get(4);
        assertEquals(false, propertyEntity04.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎1b", propertyEntity04.getKanrenshaName());

        // 元データが最新
        List<KanrenshaPersonMasterEntity> listMasterLatest = kanrenshaPersonMasterRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonMasterIdDesc(orgCode, true);
        assertEquals(1, listMasterLatest.size());
        KanrenshaPersonMasterEntity masterEntity10 = listMasterLatest.get(0);
        assertEquals(true, masterEntity10.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎1a", masterEntity10.getKanrenshaName()); // NOPMD

        List<KanrenshaPersonAddressEntity> listAddressLatest = kanrenshaPersonAddressRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonAddressIdDesc(orgCode, true);
        assertEquals(1, listAddressLatest.size());
        KanrenshaPersonAddressEntity addressEntity10 = listAddressLatest.get(0);
        assertEquals(true, addressEntity10.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎1a", addressEntity10.getKanrenshaName());

        List<KanrenshaPersonAccessEntity> listAccessLatest = kanrenshaPersonAccessRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonAccessIdDesc(orgCode, true);
        assertEquals(1, listAccessLatest.size());
        KanrenshaPersonAccessEntity accessEntity10 = listAccessLatest.get(0);
        assertEquals(true, accessEntity10.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎1a", accessEntity10.getKanrenshaName());

        List<KanrenshaPersonPropertyEntity> listPropertyLatest = kanrenshaPersonPropertyRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonPropertyIdDesc(orgCode, true);
        assertEquals(1, listPropertyLatest.size());
        KanrenshaPersonPropertyEntity propertyEntity10 = listPropertyLatest.get(0);
        assertEquals(true, propertyEntity10.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎1a", propertyEntity10.getKanrenshaName());
    }

    @Test
    @Tag("TableTruncate")
    void testAbolishLast() throws Exception {

        MoveKanrenshaCodeAcceptCapsuleDto capsuleDto = new MoveKanrenshaCodeAcceptCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        KanrenshaCodeMoveEntity moveEntity = new KanrenshaCodeMoveEntity();

        final String orgCode = "23456";
        moveEntity.setMoveStatus(ShinseiStatusConstants.ACCEPT);
        moveEntity.setAbolishKanrenshaCode("87654");
        moveEntity.setOriginKanrenshaCode(orgCode);
        moveEntity.setIsAbolishLast(true); // 廃止コードを最新にする

        moveEntity.setKanrenshaKbn(KanrenshaKbnConstants.PERSON);
        moveEntity.setAbolishKanrenshaName("廃止名称");
        moveEntity.setOriginName("残す名称");
        moveEntity.setMoveReason("移動理由");

        capsuleDto.setKanrenshaCodeMoveEntity(moveEntity);

        assertTrue(moveCodeMasterPersonService.practice(capsuleDto));

        // 廃止コード最新・履歴と元コード履歴が履歴となる
        // 挿入時間が維持されているので、必要に応じて挿入時間順に並べれば時系列に整列
        List<KanrenshaPersonMasterEntity> listMasterHistory = kanrenshaPersonMasterRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonMasterIdDesc(orgCode, false);
        assertEquals(5, listMasterHistory.size());
        KanrenshaPersonMasterEntity masterEntity00 = listMasterHistory.get(0);
        assertEquals(false, masterEntity00.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎4b", masterEntity00.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 23, 16, 30, 24), masterEntity00.getInsertTimestamp());

        KanrenshaPersonMasterEntity masterEntity01 = listMasterHistory.get(1);
        assertEquals(false, masterEntity01.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎4c", masterEntity01.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 24, 16, 30, 24), masterEntity01.getInsertTimestamp());

        KanrenshaPersonMasterEntity masterEntity02 = listMasterHistory.get(2);
        assertEquals(false, masterEntity02.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎3a", masterEntity02.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 27, 16, 30, 24), masterEntity02.getInsertTimestamp());

        KanrenshaPersonMasterEntity masterEntity03 = listMasterHistory.get(3);
        assertEquals(false, masterEntity03.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎3c", masterEntity03.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 22, 16, 30, 24), masterEntity03.getInsertTimestamp());

        KanrenshaPersonMasterEntity masterEntity04 = listMasterHistory.get(4);
        assertEquals(false, masterEntity04.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎3b", masterEntity04.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 25, 16, 30, 24), masterEntity04.getInsertTimestamp());

        List<KanrenshaPersonAddressEntity> listAddressHistory = kanrenshaPersonAddressRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonAddressIdDesc(orgCode, false);
        assertEquals(5, listAddressHistory.size());
        KanrenshaPersonAddressEntity addressEntity00 = listAddressHistory.get(0);
        assertEquals(false, addressEntity00.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎4b", addressEntity00.getKanrenshaName());

        KanrenshaPersonAddressEntity addressEntity01 = listAddressHistory.get(1);
        assertEquals(false, addressEntity01.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎4c", addressEntity01.getKanrenshaName());

        KanrenshaPersonAddressEntity addressEntity02 = listAddressHistory.get(2);
        assertEquals(false, addressEntity02.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎3a", addressEntity02.getKanrenshaName());

        KanrenshaPersonAddressEntity addressEntity03 = listAddressHistory.get(3);
        assertEquals(false, addressEntity03.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎3c", addressEntity03.getKanrenshaName());

        KanrenshaPersonAddressEntity addressEntity04 = listAddressHistory.get(4);
        assertEquals(false, addressEntity04.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎3b", addressEntity04.getKanrenshaName());

        List<KanrenshaPersonAccessEntity> listAccessHistory = kanrenshaPersonAccessRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonAccessIdDesc(orgCode, false);
        assertEquals(5, listAccessHistory.size());
        KanrenshaPersonAccessEntity accessEntity00 = listAccessHistory.get(0);
        assertEquals(false, accessEntity00.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎4b", accessEntity00.getKanrenshaName());

        KanrenshaPersonAccessEntity accessEntity01 = listAccessHistory.get(1);
        assertEquals(false, accessEntity01.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎4c", accessEntity01.getKanrenshaName());

        KanrenshaPersonAccessEntity accessEntity02 = listAccessHistory.get(2);
        assertEquals(false, accessEntity02.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎3a", accessEntity02.getKanrenshaName());

        KanrenshaPersonAccessEntity accessEntity03 = listAccessHistory.get(3);
        assertEquals(false, accessEntity03.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎3c", accessEntity03.getKanrenshaName());

        KanrenshaPersonAccessEntity accessEntity04 = listAccessHistory.get(4);
        assertEquals(false, accessEntity04.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎3b", accessEntity04.getKanrenshaName());

        List<KanrenshaPersonPropertyEntity> listPropertyHistory = kanrenshaPersonPropertyRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonPropertyIdDesc(orgCode, false);
        assertEquals(5, listPropertyHistory.size());
        KanrenshaPersonPropertyEntity propertyEntity00 = listPropertyHistory.get(0);
        assertEquals(false, propertyEntity00.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎4b", propertyEntity00.getKanrenshaName());

        KanrenshaPersonPropertyEntity propertyEntity01 = listPropertyHistory.get(1);
        assertEquals(false, propertyEntity01.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎4c", propertyEntity01.getKanrenshaName());

        KanrenshaPersonPropertyEntity propertyEntity02 = listPropertyHistory.get(2);
        assertEquals(false, propertyEntity02.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎3a", propertyEntity02.getKanrenshaName());

        KanrenshaPersonPropertyEntity propertyEntity03 = listPropertyHistory.get(3);
        assertEquals(false, propertyEntity03.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎3c", propertyEntity03.getKanrenshaName());

        KanrenshaPersonPropertyEntity propertyEntity04 = listPropertyHistory.get(4);
        assertEquals(false, propertyEntity04.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎3b", propertyEntity04.getKanrenshaName());

        // 廃止データが最新
        List<KanrenshaPersonMasterEntity> listMasterLatest = kanrenshaPersonMasterRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonMasterIdDesc(orgCode, true);
        assertEquals(1, listMasterLatest.size());
        KanrenshaPersonMasterEntity masterEntity10 = listMasterLatest.get(0);
        assertEquals(true, masterEntity10.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎4a", masterEntity10.getKanrenshaName()); // NOPMD

        List<KanrenshaPersonAddressEntity> listAddressLatest = kanrenshaPersonAddressRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonAddressIdDesc(orgCode, true);
        assertEquals(1, listAddressLatest.size());
        KanrenshaPersonAddressEntity addressEntity10 = listAddressLatest.get(0);
        assertEquals(true, addressEntity10.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎4a", addressEntity10.getKanrenshaName());

        List<KanrenshaPersonAccessEntity> listAccessLatest = kanrenshaPersonAccessRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonAccessIdDesc(orgCode, true);
        assertEquals(1, listAccessLatest.size());
        KanrenshaPersonAccessEntity accessEntity10 = listAccessLatest.get(0);
        assertEquals(true, accessEntity10.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎4a", accessEntity10.getKanrenshaName());

        List<KanrenshaPersonPropertyEntity> listPropertyLatest = kanrenshaPersonPropertyRepository
                .findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonPropertyIdDesc(orgCode, true);
        assertEquals(1, listPropertyLatest.size());
        KanrenshaPersonPropertyEntity propertyEntity10 = listPropertyLatest.get(0);
        assertEquals(true, propertyEntity10.getIsLatest());
        assertEquals("迂回献金 ミカエル太郎4a", propertyEntity10.getKanrenshaName());

    }

}
