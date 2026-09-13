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
 * MoveCodeMasterSeijidantaiService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("MoveCodeMasterSeijidantaiServiceTest.sql")
class MoveCodeMasterSeijidantaiServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private MoveCodeMasterSeijidantaiService moveCodeMasterSeijidantaiService;

    /** 関連者政治団体マスタRepository */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    /** 関連者政治団体住所Repository */
    @Autowired
    private KanrenshaSeijidantaiAddressRepository kanrenshaSeijidantaiAddressRepository;

    /** 関連者政治団体連絡先Repository */
    @Autowired
    private KanrenshaSeijidantaiAccessRepository kanrenshaSeijidantaiAccessRepository;

    /** 関連者政治団体属性Repository */
    @Autowired
    private KanrenshaSeijidantaiPropertyRepository kanrenshaSeijidantaiPropertyRepository;

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

        moveEntity.setKanrenshaKbn(KanrenshaKbnConstants.SEIJIDANTAI);
        moveEntity.setAbolishKanrenshaName("廃止名称");
        moveEntity.setOriginName("残す名称");
        moveEntity.setMoveReason("移動理由");

        capsuleDto.setKanrenshaCodeMoveEntity(moveEntity);

        assertTrue(moveCodeMasterSeijidantaiService.practice(capsuleDto));

        // 廃止コード最新・履歴と元コード履歴が履歴となる
        // 挿入時間が維持されているので、必要に応じて挿入時間順に並べれば時系列に整列
        List<KanrenshaSeijidantaiMasterEntity> listMasterHistory = kanrenshaSeijidantaiMasterRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiMasterIdDesc(orgCode, false);
        assertEquals(5, listMasterHistory.size());
        KanrenshaSeijidantaiMasterEntity masterEntity00 = listMasterHistory.get(0);
        assertEquals(false, masterEntity00.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体2a", masterEntity00.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 26, 16, 30, 24), masterEntity00.getInsertTimestamp());

        KanrenshaSeijidantaiMasterEntity masterEntity01 = listMasterHistory.get(1);
        assertEquals(false, masterEntity01.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体2b", masterEntity01.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 23, 16, 30, 24), masterEntity01.getInsertTimestamp());

        KanrenshaSeijidantaiMasterEntity masterEntity02 = listMasterHistory.get(2);
        assertEquals(false, masterEntity02.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体2c", masterEntity02.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 24, 16, 30, 24), masterEntity02.getInsertTimestamp());

        KanrenshaSeijidantaiMasterEntity masterEntity03 = listMasterHistory.get(3);
        assertEquals(false, masterEntity03.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体1c", masterEntity03.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 22, 16, 30, 24), masterEntity03.getInsertTimestamp());

        KanrenshaSeijidantaiMasterEntity masterEntity04 = listMasterHistory.get(4);
        assertEquals(false, masterEntity04.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体1b", masterEntity04.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 25, 16, 30, 24), masterEntity04.getInsertTimestamp());

        List<KanrenshaSeijidantaiAddressEntity> listAddressHistory = kanrenshaSeijidantaiAddressRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiAddressIdDesc(orgCode, false);
        assertEquals(5, listAddressHistory.size());
        KanrenshaSeijidantaiAddressEntity addressEntity00 = listAddressHistory.get(0);
        assertEquals(false, addressEntity00.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体2a", addressEntity00.getKanrenshaName());

        KanrenshaSeijidantaiAddressEntity addressEntity01 = listAddressHistory.get(1);
        assertEquals(false, addressEntity01.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体2b", addressEntity01.getKanrenshaName());

        KanrenshaSeijidantaiAddressEntity addressEntity02 = listAddressHistory.get(2);
        assertEquals(false, addressEntity02.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体2c", addressEntity02.getKanrenshaName());

        KanrenshaSeijidantaiAddressEntity addressEntity03 = listAddressHistory.get(3);
        assertEquals(false, addressEntity03.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体1c", addressEntity03.getKanrenshaName());

        KanrenshaSeijidantaiAddressEntity addressEntity04 = listAddressHistory.get(4);
        assertEquals(false, addressEntity04.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体1b", addressEntity04.getKanrenshaName());

        List<KanrenshaSeijidantaiAccessEntity> listAccessHistory = kanrenshaSeijidantaiAccessRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiAccessIdDesc(orgCode, false);
        assertEquals(5, listAccessHistory.size());
        KanrenshaSeijidantaiAccessEntity accessEntity00 = listAccessHistory.get(0);
        assertEquals(false, accessEntity00.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体2a", accessEntity00.getKanrenshaName());

        KanrenshaSeijidantaiAccessEntity accessEntity01 = listAccessHistory.get(1);
        assertEquals(false, accessEntity01.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体2b", accessEntity01.getKanrenshaName());

        KanrenshaSeijidantaiAccessEntity accessEntity02 = listAccessHistory.get(2);
        assertEquals(false, accessEntity02.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体2c", accessEntity02.getKanrenshaName());

        KanrenshaSeijidantaiAccessEntity accessEntity03 = listAccessHistory.get(3);
        assertEquals(false, accessEntity03.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体1c", accessEntity03.getKanrenshaName());

        KanrenshaSeijidantaiAccessEntity accessEntity04 = listAccessHistory.get(4);
        assertEquals(false, accessEntity04.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体1b", accessEntity04.getKanrenshaName());

        List<KanrenshaSeijidantaiPropertyEntity> listPropertyHistory = kanrenshaSeijidantaiPropertyRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiPropertyIdDesc(orgCode, false);
        assertEquals(5, listPropertyHistory.size());
        KanrenshaSeijidantaiPropertyEntity propertyEntity00 = listPropertyHistory.get(0);
        assertEquals(false, propertyEntity00.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体2a", propertyEntity00.getKanrenshaName());

        KanrenshaSeijidantaiPropertyEntity propertyEntity01 = listPropertyHistory.get(1);
        assertEquals(false, propertyEntity01.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体2b", propertyEntity01.getKanrenshaName());

        KanrenshaSeijidantaiPropertyEntity propertyEntity02 = listPropertyHistory.get(2);
        assertEquals(false, propertyEntity02.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体2c", propertyEntity02.getKanrenshaName());

        KanrenshaSeijidantaiPropertyEntity propertyEntity03 = listPropertyHistory.get(3);
        assertEquals(false, propertyEntity03.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体1c", propertyEntity03.getKanrenshaName());

        KanrenshaSeijidantaiPropertyEntity propertyEntity04 = listPropertyHistory.get(4);
        assertEquals(false, propertyEntity04.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体1b", propertyEntity04.getKanrenshaName());

        // 元データが最新
        List<KanrenshaSeijidantaiMasterEntity> listMasterLatest = kanrenshaSeijidantaiMasterRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiMasterIdDesc(orgCode, true);
        assertEquals(1, listMasterLatest.size());
        KanrenshaSeijidantaiMasterEntity masterEntity10 = listMasterLatest.get(0);
        assertEquals(true, masterEntity10.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体1a", masterEntity10.getKanrenshaName()); // NOPMD

        List<KanrenshaSeijidantaiAddressEntity> listAddressLatest = kanrenshaSeijidantaiAddressRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiAddressIdDesc(orgCode, true);
        assertEquals(1, listAddressLatest.size());
        KanrenshaSeijidantaiAddressEntity addressEntity10 = listAddressLatest.get(0);
        assertEquals(true, addressEntity10.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体1a", addressEntity10.getKanrenshaName());

        List<KanrenshaSeijidantaiAccessEntity> listAccessLatest = kanrenshaSeijidantaiAccessRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiAccessIdDesc(orgCode, true);
        assertEquals(1, listAccessLatest.size());
        KanrenshaSeijidantaiAccessEntity accessEntity10 = listAccessLatest.get(0);
        assertEquals(true, accessEntity10.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体1a", accessEntity10.getKanrenshaName());

        List<KanrenshaSeijidantaiPropertyEntity> listPropertyLatest = kanrenshaSeijidantaiPropertyRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiPropertyIdDesc(orgCode, true);
        assertEquals(1, listPropertyLatest.size());
        KanrenshaSeijidantaiPropertyEntity propertyEntity10 = listPropertyLatest.get(0);
        assertEquals(true, propertyEntity10.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体1a", propertyEntity10.getKanrenshaName());
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

        moveEntity.setKanrenshaKbn(KanrenshaKbnConstants.SEIJIDANTAI);
        moveEntity.setAbolishKanrenshaName("廃止名称");
        moveEntity.setOriginName("残す名称");
        moveEntity.setMoveReason("移動理由");

        capsuleDto.setKanrenshaCodeMoveEntity(moveEntity);

        assertTrue(moveCodeMasterSeijidantaiService.practice(capsuleDto));

        // 廃止コード最新・履歴と元コード履歴が履歴となる
        // 挿入時間が維持されているので、必要に応じて挿入時間順に並べれば時系列に整列
        List<KanrenshaSeijidantaiMasterEntity> listMasterHistory = kanrenshaSeijidantaiMasterRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiMasterIdDesc(orgCode, false);
        assertEquals(5, listMasterHistory.size());
        KanrenshaSeijidantaiMasterEntity masterEntity00 = listMasterHistory.get(0);
        assertEquals(false, masterEntity00.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体4b", masterEntity00.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 23, 16, 30, 24), masterEntity00.getInsertTimestamp());

        KanrenshaSeijidantaiMasterEntity masterEntity01 = listMasterHistory.get(1);
        assertEquals(false, masterEntity01.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体4c", masterEntity01.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 24, 16, 30, 24), masterEntity01.getInsertTimestamp());

        KanrenshaSeijidantaiMasterEntity masterEntity02 = listMasterHistory.get(2);
        assertEquals(false, masterEntity02.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体3a", masterEntity02.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 27, 16, 30, 24), masterEntity02.getInsertTimestamp());

        KanrenshaSeijidantaiMasterEntity masterEntity03 = listMasterHistory.get(3);
        assertEquals(false, masterEntity03.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体3c", masterEntity03.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 22, 16, 30, 24), masterEntity03.getInsertTimestamp());

        KanrenshaSeijidantaiMasterEntity masterEntity04 = listMasterHistory.get(4);
        assertEquals(false, masterEntity04.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体3b", masterEntity04.getKanrenshaName()); // NOPMD
        assertEquals(LocalDateTime.of(2025, 12, 25, 16, 30, 24), masterEntity04.getInsertTimestamp());

        List<KanrenshaSeijidantaiAddressEntity> listAddressHistory = kanrenshaSeijidantaiAddressRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiAddressIdDesc(orgCode, false);
        assertEquals(5, listAddressHistory.size());
        KanrenshaSeijidantaiAddressEntity addressEntity00 = listAddressHistory.get(0);
        assertEquals(false, addressEntity00.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体4b", addressEntity00.getKanrenshaName());

        KanrenshaSeijidantaiAddressEntity addressEntity01 = listAddressHistory.get(1);
        assertEquals(false, addressEntity01.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体4c", addressEntity01.getKanrenshaName());

        KanrenshaSeijidantaiAddressEntity addressEntity02 = listAddressHistory.get(2);
        assertEquals(false, addressEntity02.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体3a", addressEntity02.getKanrenshaName());

        KanrenshaSeijidantaiAddressEntity addressEntity03 = listAddressHistory.get(3);
        assertEquals(false, addressEntity03.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体3c", addressEntity03.getKanrenshaName());

        KanrenshaSeijidantaiAddressEntity addressEntity04 = listAddressHistory.get(4);
        assertEquals(false, addressEntity04.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体3b", addressEntity04.getKanrenshaName());

        List<KanrenshaSeijidantaiAccessEntity> listAccessHistory = kanrenshaSeijidantaiAccessRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiAccessIdDesc(orgCode, false);
        assertEquals(5, listAccessHistory.size());
        KanrenshaSeijidantaiAccessEntity accessEntity00 = listAccessHistory.get(0);
        assertEquals(false, accessEntity00.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体4b", accessEntity00.getKanrenshaName());

        KanrenshaSeijidantaiAccessEntity accessEntity01 = listAccessHistory.get(1);
        assertEquals(false, accessEntity01.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体4c", accessEntity01.getKanrenshaName());

        KanrenshaSeijidantaiAccessEntity accessEntity02 = listAccessHistory.get(2);
        assertEquals(false, accessEntity02.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体3a", accessEntity02.getKanrenshaName());

        KanrenshaSeijidantaiAccessEntity accessEntity03 = listAccessHistory.get(3);
        assertEquals(false, accessEntity03.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体3c", accessEntity03.getKanrenshaName());

        KanrenshaSeijidantaiAccessEntity accessEntity04 = listAccessHistory.get(4);
        assertEquals(false, accessEntity04.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体3b", accessEntity04.getKanrenshaName());

        List<KanrenshaSeijidantaiPropertyEntity> listPropertyHistory = kanrenshaSeijidantaiPropertyRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiPropertyIdDesc(orgCode, false);
        assertEquals(5, listPropertyHistory.size());
        KanrenshaSeijidantaiPropertyEntity propertyEntity00 = listPropertyHistory.get(0);
        assertEquals(false, propertyEntity00.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体4b", propertyEntity00.getKanrenshaName());

        KanrenshaSeijidantaiPropertyEntity propertyEntity01 = listPropertyHistory.get(1);
        assertEquals(false, propertyEntity01.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体4c", propertyEntity01.getKanrenshaName());

        KanrenshaSeijidantaiPropertyEntity propertyEntity02 = listPropertyHistory.get(2);
        assertEquals(false, propertyEntity02.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体3a", propertyEntity02.getKanrenshaName());

        KanrenshaSeijidantaiPropertyEntity propertyEntity03 = listPropertyHistory.get(3);
        assertEquals(false, propertyEntity03.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体3c", propertyEntity03.getKanrenshaName());

        KanrenshaSeijidantaiPropertyEntity propertyEntity04 = listPropertyHistory.get(4);
        assertEquals(false, propertyEntity04.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体3b", propertyEntity04.getKanrenshaName());

        // 廃止データが最新
        List<KanrenshaSeijidantaiMasterEntity> listMasterLatest = kanrenshaSeijidantaiMasterRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiMasterIdDesc(orgCode, true);
        assertEquals(1, listMasterLatest.size());
        KanrenshaSeijidantaiMasterEntity masterEntity10 = listMasterLatest.get(0);
        assertEquals(true, masterEntity10.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体4a", masterEntity10.getKanrenshaName()); // NOPMD

        List<KanrenshaSeijidantaiAddressEntity> listAddressLatest = kanrenshaSeijidantaiAddressRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiAddressIdDesc(orgCode, true);
        assertEquals(1, listAddressLatest.size());
        KanrenshaSeijidantaiAddressEntity addressEntity10 = listAddressLatest.get(0);
        assertEquals(true, addressEntity10.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体4a", addressEntity10.getKanrenshaName());

        List<KanrenshaSeijidantaiAccessEntity> listAccessLatest = kanrenshaSeijidantaiAccessRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiAccessIdDesc(orgCode, true);
        assertEquals(1, listAccessLatest.size());
        KanrenshaSeijidantaiAccessEntity accessEntity10 = listAccessLatest.get(0);
        assertEquals(true, accessEntity10.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体4a", accessEntity10.getKanrenshaName());

        List<KanrenshaSeijidantaiPropertyEntity> listPropertyLatest = kanrenshaSeijidantaiPropertyRepository
                .findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiPropertyIdDesc(orgCode, true);
        assertEquals(1, listPropertyLatest.size());
        KanrenshaSeijidantaiPropertyEntity propertyEntity10 = listPropertyLatest.get(0);
        assertEquals(true, propertyEntity10.getIsLatest());
        assertEquals("ちゃらんぽらん政治団体4a", propertyEntity10.getKanrenshaName());

    }

}
