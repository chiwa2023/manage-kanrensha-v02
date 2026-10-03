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
 * MoveCodeMasterKigyouDtService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("MoveCodeMasterKigyouDtServiceTest.sql")
class MoveCodeMasterKigyouDtServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private MoveCodeMasterKigyouDtService moveCodeMasterKigyouDtService;

    /** 関連者企業・団体マスタRepository */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    /** 関連者企業・団体住所Repository */
    @Autowired
    private KanrenshaKigyouDtAddressRepository kanrenshaKigyouDtAddressRepository;

    /** 関連者企業・団体連絡先Repository */
    @Autowired
    private KanrenshaKigyouDtAccessRepository kanrenshaKigyouDtAccessRepository;

    /** 関連者企業・団体属性Repository */
    @Autowired
    private KanrenshaKigyouDtPropertyRepository kanrenshaKigyouDtPropertyRepository;

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

        moveEntity.setKanrenshaKbn(KanrenshaKbnConstants.KIGYOU_DT);
        moveEntity.setAbolishKanrenshaName("廃止名称");
        moveEntity.setOriginName("残す名称");
        moveEntity.setMoveReason("移動理由");

        capsuleDto.setKanrenshaCodeMoveEntity(moveEntity);

        assertTrue(moveCodeMasterKigyouDtService.practice(capsuleDto));

        // 廃止コード最新・履歴と元コード履歴が履歴となる
        // 挿入時間が維持されているので、必要に応じて挿入時間順に並べれば時系列に整列
        List<KanrenshaKigyouDtMasterEntity> listMasterHistory = kanrenshaKigyouDtMasterRepository
                .findByKigyouDtKanrenshaCodeAndIsLatestOrderByKanrenshaKigyouDtMasterIdDesc(orgCode, false);
        assertEquals(5, listMasterHistory.size());
        KanrenshaKigyouDtMasterEntity masterEntity00 = listMasterHistory.get(0);
        assertEquals(false, masterEntity00.getIsLatest());
        assertEquals("どっちつかず企業2a", masterEntity00.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 26, 16, 30, 24), masterEntity00.getInsertTimestamp());

        KanrenshaKigyouDtMasterEntity masterEntity01 = listMasterHistory.get(1);
        assertEquals(false, masterEntity01.getIsLatest());
        assertEquals("どっちつかず企業2b", masterEntity01.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 23, 16, 30, 24), masterEntity01.getInsertTimestamp());

        KanrenshaKigyouDtMasterEntity masterEntity02 = listMasterHistory.get(2);
        assertEquals(false, masterEntity02.getIsLatest());
        assertEquals("どっちつかず企業2c", masterEntity02.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 24, 16, 30, 24), masterEntity02.getInsertTimestamp());

        KanrenshaKigyouDtMasterEntity masterEntity03 = listMasterHistory.get(3);
        assertEquals(false, masterEntity03.getIsLatest());
        assertEquals("どっちつかず企業1c", masterEntity03.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 22, 16, 30, 24), masterEntity03.getInsertTimestamp());

        KanrenshaKigyouDtMasterEntity masterEntity04 = listMasterHistory.get(4);
        assertEquals(false, masterEntity04.getIsLatest());
        assertEquals("どっちつかず企業1b", masterEntity04.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 25, 16, 30, 24), masterEntity04.getInsertTimestamp());

        List<KanrenshaKigyouDtAddressEntity> listAddressHistory = kanrenshaKigyouDtAddressRepository
                .findByKigyouDtKanrenshaCodeAndIsLatestOrderByKanrenshaKigyouDtAddressIdDesc(orgCode, false);
        assertEquals(5, listAddressHistory.size());
        KanrenshaKigyouDtAddressEntity addressEntity00 = listAddressHistory.get(0);
        assertEquals(false, addressEntity00.getIsLatest());
        assertEquals("どっちつかず企業2a", addressEntity00.getKanrenshaName());

        KanrenshaKigyouDtAddressEntity addressEntity01 = listAddressHistory.get(1);
        assertEquals(false, addressEntity01.getIsLatest());
        assertEquals("どっちつかず企業2b", addressEntity01.getKanrenshaName());

        KanrenshaKigyouDtAddressEntity addressEntity02 = listAddressHistory.get(2);
        assertEquals(false, addressEntity02.getIsLatest());
        assertEquals("どっちつかず企業2c", addressEntity02.getKanrenshaName());

        KanrenshaKigyouDtAddressEntity addressEntity03 = listAddressHistory.get(3);
        assertEquals(false, addressEntity03.getIsLatest());
        assertEquals("どっちつかず企業1c", addressEntity03.getKanrenshaName());

        KanrenshaKigyouDtAddressEntity addressEntity04 = listAddressHistory.get(4);
        assertEquals(false, addressEntity04.getIsLatest());
        assertEquals("どっちつかず企業1b", addressEntity04.getKanrenshaName());

        List<KanrenshaKigyouDtAccessEntity> listAccessHistory = kanrenshaKigyouDtAccessRepository
                .findByKigyouDtKanrenshaCodeAndIsLatestOrderByKanrenshaKigyouDtAccessIdDesc(orgCode, false);
        assertEquals(5, listAccessHistory.size());
        KanrenshaKigyouDtAccessEntity accessEntity00 = listAccessHistory.get(0);
        assertEquals(false, accessEntity00.getIsLatest());
        assertEquals("どっちつかず企業2a", accessEntity00.getKanrenshaName());

        KanrenshaKigyouDtAccessEntity accessEntity01 = listAccessHistory.get(1);
        assertEquals(false, accessEntity01.getIsLatest());
        assertEquals("どっちつかず企業2b", accessEntity01.getKanrenshaName());

        KanrenshaKigyouDtAccessEntity accessEntity02 = listAccessHistory.get(2);
        assertEquals(false, accessEntity02.getIsLatest());
        assertEquals("どっちつかず企業2c", accessEntity02.getKanrenshaName());

        KanrenshaKigyouDtAccessEntity accessEntity03 = listAccessHistory.get(3);
        assertEquals(false, accessEntity03.getIsLatest());
        assertEquals("どっちつかず企業1c", accessEntity03.getKanrenshaName());

        KanrenshaKigyouDtAccessEntity accessEntity04 = listAccessHistory.get(4);
        assertEquals(false, accessEntity04.getIsLatest());
        assertEquals("どっちつかず企業1b", accessEntity04.getKanrenshaName());

        List<KanrenshaKigyouDtPropertyEntity> listPropertyHistory = kanrenshaKigyouDtPropertyRepository
                .findByKigyouDtKanrenshaCodeAndIsLatestOrderByKanrenshaKigyouDtPropertyIdDesc(orgCode, false);
        assertEquals(5, listPropertyHistory.size());
        KanrenshaKigyouDtPropertyEntity propertyEntity00 = listPropertyHistory.get(0);
        assertEquals(false, propertyEntity00.getIsLatest());
        assertEquals("どっちつかず企業2a", propertyEntity00.getKanrenshaName());

        KanrenshaKigyouDtPropertyEntity propertyEntity01 = listPropertyHistory.get(1);
        assertEquals(false, propertyEntity01.getIsLatest());
        assertEquals("どっちつかず企業2b", propertyEntity01.getKanrenshaName());

        KanrenshaKigyouDtPropertyEntity propertyEntity02 = listPropertyHistory.get(2);
        assertEquals(false, propertyEntity02.getIsLatest());
        assertEquals("どっちつかず企業2c", propertyEntity02.getKanrenshaName());

        KanrenshaKigyouDtPropertyEntity propertyEntity03 = listPropertyHistory.get(3);
        assertEquals(false, propertyEntity03.getIsLatest());
        assertEquals("どっちつかず企業1c", propertyEntity03.getKanrenshaName());

        KanrenshaKigyouDtPropertyEntity propertyEntity04 = listPropertyHistory.get(4);
        assertEquals(false, propertyEntity04.getIsLatest());
        assertEquals("どっちつかず企業1b", propertyEntity04.getKanrenshaName());

        // 元データが最新
        List<KanrenshaKigyouDtMasterEntity> listMasterLatest = kanrenshaKigyouDtMasterRepository
                .findByKigyouDtKanrenshaCodeAndIsLatestOrderByKanrenshaKigyouDtMasterIdDesc(orgCode, true);
        assertEquals(1, listMasterLatest.size());
        KanrenshaKigyouDtMasterEntity masterEntity10 = listMasterLatest.get(0);
        assertEquals(true, masterEntity10.getIsLatest());
        assertEquals("どっちつかず企業1a", masterEntity10.getKanrenshaName()); // NOPMD

        List<KanrenshaKigyouDtAddressEntity> listAddressLatest = kanrenshaKigyouDtAddressRepository
                .findByKigyouDtKanrenshaCodeAndIsLatestOrderByKanrenshaKigyouDtAddressIdDesc(orgCode, true);
        assertEquals(1, listAddressLatest.size());
        KanrenshaKigyouDtAddressEntity addressEntity10 = listAddressLatest.get(0);
        assertEquals(true, addressEntity10.getIsLatest());
        assertEquals("どっちつかず企業1a", addressEntity10.getKanrenshaName());

        List<KanrenshaKigyouDtAccessEntity> listAccessLatest = kanrenshaKigyouDtAccessRepository
                .findByKigyouDtKanrenshaCodeAndIsLatestOrderByKanrenshaKigyouDtAccessIdDesc(orgCode, true);
        assertEquals(1, listAccessLatest.size());
        KanrenshaKigyouDtAccessEntity accessEntity10 = listAccessLatest.get(0);
        assertEquals(true, accessEntity10.getIsLatest());
        assertEquals("どっちつかず企業1a", accessEntity10.getKanrenshaName());

        List<KanrenshaKigyouDtPropertyEntity> listPropertyLatest = kanrenshaKigyouDtPropertyRepository
                .findByKigyouDtKanrenshaCodeAndIsLatestOrderByKanrenshaKigyouDtPropertyIdDesc(orgCode, true);
        assertEquals(1, listPropertyLatest.size());
        KanrenshaKigyouDtPropertyEntity propertyEntity10 = listPropertyLatest.get(0);
        assertEquals(true, propertyEntity10.getIsLatest());
        assertEquals("どっちつかず企業1a", propertyEntity10.getKanrenshaName());
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

        moveEntity.setKanrenshaKbn(KanrenshaKbnConstants.KIGYOU_DT);
        moveEntity.setAbolishKanrenshaName("廃止名称");
        moveEntity.setOriginName("残す名称");
        moveEntity.setMoveReason("移動理由");

        capsuleDto.setKanrenshaCodeMoveEntity(moveEntity);

        assertTrue(moveCodeMasterKigyouDtService.practice(capsuleDto));

        // 廃止コード最新・履歴と元コード履歴が履歴となる
        // 挿入時間が維持されているので、必要に応じて挿入時間順に並べれば時系列に整列
        List<KanrenshaKigyouDtMasterEntity> listMasterHistory = kanrenshaKigyouDtMasterRepository
                .findByKigyouDtKanrenshaCodeAndIsLatestOrderByKanrenshaKigyouDtMasterIdDesc(orgCode, false);
        assertEquals(5, listMasterHistory.size());
        KanrenshaKigyouDtMasterEntity masterEntity00 = listMasterHistory.get(0);
        assertEquals(false, masterEntity00.getIsLatest());
        assertEquals("どっちつかず企業4b", masterEntity00.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 23, 16, 30, 24), masterEntity00.getInsertTimestamp());

        KanrenshaKigyouDtMasterEntity masterEntity01 = listMasterHistory.get(1);
        assertEquals(false, masterEntity01.getIsLatest());
        assertEquals("どっちつかず企業4c", masterEntity01.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 24, 16, 30, 24), masterEntity01.getInsertTimestamp());

        KanrenshaKigyouDtMasterEntity masterEntity02 = listMasterHistory.get(2);
        assertEquals(false, masterEntity02.getIsLatest());
        assertEquals("どっちつかず企業3a", masterEntity02.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 27, 16, 30, 24), masterEntity02.getInsertTimestamp());

        KanrenshaKigyouDtMasterEntity masterEntity03 = listMasterHistory.get(3);
        assertEquals(false, masterEntity03.getIsLatest());
        assertEquals("どっちつかず企業3c", masterEntity03.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 22, 16, 30, 24), masterEntity03.getInsertTimestamp());

        KanrenshaKigyouDtMasterEntity masterEntity04 = listMasterHistory.get(4);
        assertEquals(false, masterEntity04.getIsLatest());
        assertEquals("どっちつかず企業3b", masterEntity04.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 25, 16, 30, 24), masterEntity04.getInsertTimestamp());

        List<KanrenshaKigyouDtAddressEntity> listAddressHistory = kanrenshaKigyouDtAddressRepository
                .findByKigyouDtKanrenshaCodeAndIsLatestOrderByKanrenshaKigyouDtAddressIdDesc(orgCode, false);
        assertEquals(5, listAddressHistory.size());
        KanrenshaKigyouDtAddressEntity addressEntity00 = listAddressHistory.get(0);
        assertEquals(false, addressEntity00.getIsLatest());
        assertEquals("どっちつかず企業4b", addressEntity00.getKanrenshaName());

        KanrenshaKigyouDtAddressEntity addressEntity01 = listAddressHistory.get(1);
        assertEquals(false, addressEntity01.getIsLatest());
        assertEquals("どっちつかず企業4c", addressEntity01.getKanrenshaName());

        KanrenshaKigyouDtAddressEntity addressEntity02 = listAddressHistory.get(2);
        assertEquals(false, addressEntity02.getIsLatest());
        assertEquals("どっちつかず企業3a", addressEntity02.getKanrenshaName());

        KanrenshaKigyouDtAddressEntity addressEntity03 = listAddressHistory.get(3);
        assertEquals(false, addressEntity03.getIsLatest());
        assertEquals("どっちつかず企業3c", addressEntity03.getKanrenshaName());

        KanrenshaKigyouDtAddressEntity addressEntity04 = listAddressHistory.get(4);
        assertEquals(false, addressEntity04.getIsLatest());
        assertEquals("どっちつかず企業3b", addressEntity04.getKanrenshaName());

        List<KanrenshaKigyouDtAccessEntity> listAccessHistory = kanrenshaKigyouDtAccessRepository
                .findByKigyouDtKanrenshaCodeAndIsLatestOrderByKanrenshaKigyouDtAccessIdDesc(orgCode, false);
        assertEquals(5, listAccessHistory.size());
        KanrenshaKigyouDtAccessEntity accessEntity00 = listAccessHistory.get(0);
        assertEquals(false, accessEntity00.getIsLatest());
        assertEquals("どっちつかず企業4b", accessEntity00.getKanrenshaName());

        KanrenshaKigyouDtAccessEntity accessEntity01 = listAccessHistory.get(1);
        assertEquals(false, accessEntity01.getIsLatest());
        assertEquals("どっちつかず企業4c", accessEntity01.getKanrenshaName());

        KanrenshaKigyouDtAccessEntity accessEntity02 = listAccessHistory.get(2);
        assertEquals(false, accessEntity02.getIsLatest());
        assertEquals("どっちつかず企業3a", accessEntity02.getKanrenshaName());

        KanrenshaKigyouDtAccessEntity accessEntity03 = listAccessHistory.get(3);
        assertEquals(false, accessEntity03.getIsLatest());
        assertEquals("どっちつかず企業3c", accessEntity03.getKanrenshaName());

        KanrenshaKigyouDtAccessEntity accessEntity04 = listAccessHistory.get(4);
        assertEquals(false, accessEntity04.getIsLatest());
        assertEquals("どっちつかず企業3b", accessEntity04.getKanrenshaName());

        List<KanrenshaKigyouDtPropertyEntity> listPropertyHistory = kanrenshaKigyouDtPropertyRepository
                .findByKigyouDtKanrenshaCodeAndIsLatestOrderByKanrenshaKigyouDtPropertyIdDesc(orgCode, false);
        assertEquals(5, listPropertyHistory.size());
        KanrenshaKigyouDtPropertyEntity propertyEntity00 = listPropertyHistory.get(0);
        assertEquals(false, propertyEntity00.getIsLatest());
        assertEquals("どっちつかず企業4b", propertyEntity00.getKanrenshaName());

        KanrenshaKigyouDtPropertyEntity propertyEntity01 = listPropertyHistory.get(1);
        assertEquals(false, propertyEntity01.getIsLatest());
        assertEquals("どっちつかず企業4c", propertyEntity01.getKanrenshaName());

        KanrenshaKigyouDtPropertyEntity propertyEntity02 = listPropertyHistory.get(2);
        assertEquals(false, propertyEntity02.getIsLatest());
        assertEquals("どっちつかず企業3a", propertyEntity02.getKanrenshaName());

        KanrenshaKigyouDtPropertyEntity propertyEntity03 = listPropertyHistory.get(3);
        assertEquals(false, propertyEntity03.getIsLatest());
        assertEquals("どっちつかず企業3c", propertyEntity03.getKanrenshaName());

        KanrenshaKigyouDtPropertyEntity propertyEntity04 = listPropertyHistory.get(4);
        assertEquals(false, propertyEntity04.getIsLatest());
        assertEquals("どっちつかず企業3b", propertyEntity04.getKanrenshaName());

        // 廃止データが最新
        List<KanrenshaKigyouDtMasterEntity> listMasterLatest = kanrenshaKigyouDtMasterRepository
                .findByKigyouDtKanrenshaCodeAndIsLatestOrderByKanrenshaKigyouDtMasterIdDesc(orgCode, true);
        assertEquals(1, listMasterLatest.size());
        KanrenshaKigyouDtMasterEntity masterEntity10 = listMasterLatest.get(0);
        assertEquals(true, masterEntity10.getIsLatest());
        assertEquals("どっちつかず企業4a", masterEntity10.getKanrenshaName()); // NOPMD

        List<KanrenshaKigyouDtAddressEntity> listAddressLatest = kanrenshaKigyouDtAddressRepository
                .findByKigyouDtKanrenshaCodeAndIsLatestOrderByKanrenshaKigyouDtAddressIdDesc(orgCode, true);
        assertEquals(1, listAddressLatest.size());
        KanrenshaKigyouDtAddressEntity addressEntity10 = listAddressLatest.get(0);
        assertEquals(true, addressEntity10.getIsLatest());
        assertEquals("どっちつかず企業4a", addressEntity10.getKanrenshaName());

        List<KanrenshaKigyouDtAccessEntity> listAccessLatest = kanrenshaKigyouDtAccessRepository
                .findByKigyouDtKanrenshaCodeAndIsLatestOrderByKanrenshaKigyouDtAccessIdDesc(orgCode, true);
        assertEquals(1, listAccessLatest.size());
        KanrenshaKigyouDtAccessEntity accessEntity10 = listAccessLatest.get(0);
        assertEquals(true, accessEntity10.getIsLatest());
        assertEquals("どっちつかず企業4a", accessEntity10.getKanrenshaName());

        List<KanrenshaKigyouDtPropertyEntity> listPropertyLatest = kanrenshaKigyouDtPropertyRepository
                .findByKigyouDtKanrenshaCodeAndIsLatestOrderByKanrenshaKigyouDtPropertyIdDesc(orgCode, true);
        assertEquals(1, listPropertyLatest.size());
        KanrenshaKigyouDtPropertyEntity propertyEntity10 = listPropertyLatest.get(0);
        assertEquals(true, propertyEntity10.getIsLatest());
        assertEquals("どっちつかず企業4a", propertyEntity10.getKanrenshaName());

    }

}
