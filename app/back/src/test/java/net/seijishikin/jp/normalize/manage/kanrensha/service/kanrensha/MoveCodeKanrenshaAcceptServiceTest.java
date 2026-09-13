package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertTrue; // NOPMD HighImports
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

import net.seijishikin.jp.normalize.manage.kanrensha.constants.ShinseiStatusConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeAcceptCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaCodeMoveEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory01Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory01Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory01Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2025.TaskPlan2025Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaCodeMoveRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory01Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory01Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory01Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.TaskPlan2025Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * MoveCodeKanrenshaAcceptService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql({ "MoveCodeKanrenshaAcceptServiceTest.sql", //
        "MoveCodeHistoryKigyouDtServiceTest.sql", "MoveCodeHistoryPersonServiceTest.sql",
        "MoveCodeHistorySeijidantaiServiceTest.sql", "MoveCodeMasterKigyouDtServiceTest.sql",
        "MoveCodeMasterPersonServiceTest.sql", "MoveCodeMasterSeijidantaiServiceTest.sql" })
class MoveCodeKanrenshaAcceptServiceTest { // NOPMD
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private MoveCodeKanrenshaAcceptService moveCodeKanrenshaAcceptService;

    /** 関連者コード移動申請Repository */
    @Autowired
    private KanrenshaCodeMoveRepository kanrenshaCodeMoveRepository;

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

    /** 関連者個人履歴01Repository */
    @Autowired
    private KanrenshaPersonHistory01Repository kanrenshaPersonHistory01Repository;

    /** 関連者企業・団体履歴01Repository */
    @Autowired
    private KanrenshaKigyouDtHistory01Repository kanrenshaKigyouDtHistory01Repository;

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

    /** 関連者政治団体履歴01Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory01Repository kanrenshaSeijidantaiHistory01Repository;

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

    /** ユーザ権限Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

    /** タスク計画Repository(2025) */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

    @Test
    @Tag("TableTruncate")
    void testPerson() throws Exception {

        final String orgCode = "12345";
        final String abolishCode = "98765";

        // 申請却下
        KanrenshaCodeMoveEntity inputEntity0 = kanrenshaCodeMoveRepository.findById(242).get();
        inputEntity0.setMoveStatus(ShinseiStatusConstants.REJECT);
        MoveKanrenshaCodeAcceptCapsuleDto capsuleDto0 = new MoveKanrenshaCodeAcceptCapsuleDto();
        capsuleDto0.setKanrenshaCodeMoveEntity(inputEntity0);
        capsuleDto0.setUserDto(CreateLeastUserForTestUtil.practice());
        Integer saveId0 = moveCodeKanrenshaAcceptService.practice(capsuleDto0,
                LocalDateTime.of(2025, 11, 3, 12, 34, 56));
        KanrenshaCodeMoveEntity moveEntity0 = kanrenshaCodeMoveRepository.findById(saveId0).get();

        assertEquals(ShinseiStatusConstants.REJECT, moveEntity0.getMoveStatus());

        // 以下マスタ・履歴変更が変更されていないことが記述
        KanrenshaPersonMasterEntity masterEntity0 = kanrenshaPersonMasterRepository.findById(391).get();
        assertTrue(masterEntity0.getIsLatest()); // 承認なら変わるべきマスタが最新で変わっていない

        KanrenshaPersonHistory01Entity historyEntity001 = kanrenshaPersonHistory01Repository.findById(103).get();
        assertTrue(historyEntity001.getIsLatest()); // 承認なら変わるべきマスタが最新で変わっていない

        // 却下なので当然コードに変更はない
        List<UserRoleEntity> listRole = userRoleRepository
                .findByEmailAndIsLatestTrue("nnnn@politician.balanse.report.net");
        assertEquals(1, listRole.size());
        assertEquals(abolishCode, listRole.get(0).getKanrenshaCode());

        // 申請者にタスクが入っている(他と違って却下を通知)
        List<TaskPlan2025Entity> listTaskPlan = taskPlan2025Repository.findAll();
        final Integer shinseiUserCode = 196;
        TaskPlan2025Entity taskPlan2025Entity = this.getPlanEntity(shinseiUserCode, listTaskPlan);

        assertNotNull(taskPlan2025Entity);
        assertEquals(391, taskPlan2025Entity.getTaskInfoCode()); // タスク情報
        assertEquals(shinseiUserCode, taskPlan2025Entity.getTaskUserCode()); // 申請者のユーザコード

        // 承認時はマスタ反映(個人)
        KanrenshaCodeMoveEntity inputEntity1 = kanrenshaCodeMoveRepository.findById(243).get();
        inputEntity1.setMoveStatus(ShinseiStatusConstants.ACCEPT);
        MoveKanrenshaCodeAcceptCapsuleDto capsuleDto1 = new MoveKanrenshaCodeAcceptCapsuleDto();
        capsuleDto1.setKanrenshaCodeMoveEntity(inputEntity1);
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        Integer saveId1 = moveCodeKanrenshaAcceptService.practice(capsuleDto1,
                LocalDateTime.of(2025, 11, 3, 12, 34, 56));
        KanrenshaCodeMoveEntity moveEntity1 = kanrenshaCodeMoveRepository.findById(saveId1).get();
        assertEquals(ShinseiStatusConstants.ACCEPT, moveEntity1.getMoveStatus());

        // 以下マスタ・履歴が反映されている記述

        // 履歴が変わっている01
        List<KanrenshaPersonHistory01Entity> list01New = kanrenshaPersonHistory01Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list01New.size());
        KanrenshaPersonHistory01Entity entity010 = list01New.get(0);
        assertEquals("ぼったくり個人011a", entity010.getAllName());
        KanrenshaPersonHistory01Entity entity011 = list01New.get(1);
        assertEquals("ぼったくり個人012a", entity011.getAllName());
        List<KanrenshaPersonHistory01Entity> list01Old = kanrenshaPersonHistory01Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list01Old.size());

        // マスタが変わっている
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

        // 権限に格納した紐づくコードが切り替わっている
        List<UserRoleEntity> listRole1 = userRoleRepository
                .findByEmailAndIsLatestTrue("nnnn@politician.balanse.report.net");
        assertEquals(1, listRole1.size());
        assertEquals(orgCode, listRole1.get(0).getKanrenshaCode());
        assertEquals(UserRoleConstants.KANRENSHA_PERSON, listRole1.get(0).getRole());

        // 申請者にタスクが入っている(試行2回のタスク計画に差異はないので件数だけを検証)
        List<TaskPlan2025Entity> listTaskPlan2 = taskPlan2025Repository.findAll();
        assertEquals(2, this.getPlanEntityCount(shinseiUserCode, listTaskPlan2));
    }

    @Test
    @Tag("TableTruncate")
    void testKigyouDt() throws Exception {

        // 承認時はマスタ反映(企業・団体)
        KanrenshaCodeMoveEntity inputEntity1 = kanrenshaCodeMoveRepository.findById(244).get();
        inputEntity1.setMoveStatus(ShinseiStatusConstants.ACCEPT);
        MoveKanrenshaCodeAcceptCapsuleDto capsuleDto1 = new MoveKanrenshaCodeAcceptCapsuleDto();
        capsuleDto1.setKanrenshaCodeMoveEntity(inputEntity1);
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        Integer saveId1 = moveCodeKanrenshaAcceptService.practice(capsuleDto1,
                LocalDateTime.of(2025, 11, 3, 12, 34, 56));
        KanrenshaCodeMoveEntity moveEntity1 = kanrenshaCodeMoveRepository.findById(saveId1).get();
        assertEquals(ShinseiStatusConstants.ACCEPT, moveEntity1.getMoveStatus());

        // 以下マスタ・履歴が反映されている記述
        final String orgCode = "12345";
        final String abolishCode = "98765";

        // 01履歴
        List<KanrenshaKigyouDtHistory01Entity> list01New = kanrenshaKigyouDtHistory01Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list01New.size());
        KanrenshaKigyouDtHistory01Entity entity010 = list01New.get(0);
        assertEquals("ぼったくり企業011a", entity010.getAllName());
        KanrenshaKigyouDtHistory01Entity entity011 = list01New.get(1);
        assertEquals("ぼったくり企業012a", entity011.getAllName());
        List<KanrenshaKigyouDtHistory01Entity> list01Old = kanrenshaKigyouDtHistory01Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list01Old.size());

        // マスタ
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

        // 権限に格納した紐づくコードが切り替わっている
        List<UserRoleEntity> listRole = userRoleRepository
                .findByEmailAndIsLatestTrue("llll@politician.balanse.report.net");
        assertEquals(1, listRole.size());
        assertEquals(orgCode, listRole.get(0).getKanrenshaCode());
        assertEquals(UserRoleConstants.KANRENSHA_KIGYOU_DT, listRole.get(0).getRole());

        // 申請者にタスクが入っている
        List<TaskPlan2025Entity> listTaskPlan = taskPlan2025Repository.findAll();
        final Integer shinseiUserCode = 194;
        TaskPlan2025Entity taskPlan2025Entity = this.getPlanEntity(shinseiUserCode, listTaskPlan);

        assertNotNull(taskPlan2025Entity);
        assertEquals(391, taskPlan2025Entity.getTaskInfoCode()); // タスク情報
        assertEquals(shinseiUserCode, taskPlan2025Entity.getTaskUserCode()); // 申請者のユーザコード

    }

    @Test
    @Tag("TableTruncate")
    void testSeijidantai() throws Exception {
        // 承認時はマスタ反映(政治団体)

        KanrenshaCodeMoveEntity inputEntity1 = kanrenshaCodeMoveRepository.findById(245).get();
        inputEntity1.setMoveStatus(ShinseiStatusConstants.ACCEPT);
        MoveKanrenshaCodeAcceptCapsuleDto capsuleDto1 = new MoveKanrenshaCodeAcceptCapsuleDto();
        capsuleDto1.setKanrenshaCodeMoveEntity(inputEntity1);
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        Integer saveId1 = moveCodeKanrenshaAcceptService.practice(capsuleDto1,
                LocalDateTime.of(2025, 11, 3, 12, 34, 56));
        KanrenshaCodeMoveEntity moveEntity1 = kanrenshaCodeMoveRepository.findById(saveId1).get();
        assertEquals(ShinseiStatusConstants.ACCEPT, moveEntity1.getMoveStatus());

        // 以下マスタ・履歴が反映されている記述
        final String orgCode = "12345";
        final String abolishCode = "98765";

        // 01履歴
        List<KanrenshaSeijidantaiHistory01Entity> list01New = kanrenshaSeijidantaiHistory01Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list01New.size());
        KanrenshaSeijidantaiHistory01Entity entity010 = list01New.get(0);
        assertEquals("ぼったくり政治団体011a", entity010.getAllName());
        KanrenshaSeijidantaiHistory01Entity entity011 = list01New.get(1);
        assertEquals("ぼったくり政治団体012a", entity011.getAllName());
        List<KanrenshaSeijidantaiHistory01Entity> list01Old = kanrenshaSeijidantaiHistory01Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list01Old.size());

        // マスタ
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

        // 権限に格納した紐づくコードが切り替わっている
        List<UserRoleEntity> listRole = userRoleRepository
                .findByEmailAndIsLatestTrue("mmmm@politician.balanse.report.net");
        assertEquals(1, listRole.size());
        assertEquals(orgCode, listRole.get(0).getKanrenshaCode());
        assertEquals(UserRoleConstants.KANRENSHA_SEIJIDANTAI, listRole.get(0).getRole());

        // 申請者にタスクが入っている
        List<TaskPlan2025Entity> listTaskPlan = taskPlan2025Repository.findAll();
        final Integer shinseiUserCode = 195;
        TaskPlan2025Entity taskPlan2025Entity = this.getPlanEntity(shinseiUserCode, listTaskPlan);

        assertNotNull(taskPlan2025Entity);
        assertEquals(391, taskPlan2025Entity.getTaskInfoCode()); // タスク情報
        assertEquals(shinseiUserCode, taskPlan2025Entity.getTaskUserCode()); // 申請者のユーザコード

    }

    private TaskPlan2025Entity getPlanEntity(final Integer code,final  List<TaskPlan2025Entity> list) {
        for (TaskPlan2025Entity entity : list) {
            if (code.equals(entity.getTaskUserCode())) {
                return entity;
            }
        }
        return null;
    }

    private int getPlanEntityCount(final Integer code,final  List<TaskPlan2025Entity> list) {
        int cnt = 0;
        for (TaskPlan2025Entity entity : list) {
            if (code.equals(entity.getTaskUserCode())) {
                cnt++;
            }
        }
        return cnt;
    }

}
