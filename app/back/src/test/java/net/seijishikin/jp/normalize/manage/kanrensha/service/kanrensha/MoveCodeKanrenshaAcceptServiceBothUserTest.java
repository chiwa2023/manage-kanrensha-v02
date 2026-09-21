package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeAcceptCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaCodeMoveEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2025.TaskPlan2025Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaCodeMoveRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.TaskPlan2025Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * MoveCodeKanrenshaAcceptService単体テスト 新旧コード双方にユーザが存在する
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql({ "MoveCodeKanrenshaAcceptServiceBothUserTest.sql", //
        "MoveCodeHistoryKigyouDtServiceTest.sql", "MoveCodeHistoryPersonServiceTest.sql",
        "MoveCodeHistorySeijidantaiServiceTest.sql", "MoveCodeMasterKigyouDtServiceTest.sql",
        "MoveCodeMasterPersonServiceTest.sql", "MoveCodeMasterSeijidantaiServiceTest.sql" })
class MoveCodeKanrenshaAcceptServiceBothUserTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private MoveCodeKanrenshaAcceptService moveCodeKanrenshaAcceptService;

    /** 関連者コード移動申請Repository */
    @Autowired
    private KanrenshaCodeMoveRepository kanrenshaCodeMoveRepository;

    /** ユーザ権限Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

    /** タスク計画Repository(2025) */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

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

        // 関連者コードに関する検証は別Testファイルで行っているので省略

        // 廃止コードユーザの権限剥奪。権限剥奪というとぎょっとするが、
        // 実際の作業としては、一つの関連者コードを、(暗黙の場合も含む)2ユーザが所有権を主張している話なので
        // 承認作業をする前段階の申請段階で当人同士が気が付いてモメるべき話
        List<UserRoleEntity> listRole1 = userRoleRepository
                .findByEmailAndIsLatestTrue("nnnn@politician.balanse.report.net");
        assertEquals(0, listRole1.size());

        // 新コードユーザが存在せず、廃止コードユーザのが新コードを持つときの権限積み替えに関する検証は別Testファイルで行っているので省略
        // MEMO 廃止コードユーザが存在しないときは、コード所有権は全く問題にならない

        // 存続コードと廃止コード双方にタスク計画登録と作業完了メールがとんでいる
        List<TaskPlan2025Entity> listTaskPlan = taskPlan2025Repository.findAll();
        assertEquals(2, listTaskPlan.size());

        TaskPlan2025Entity taskEntity0 = listTaskPlan.get(0);
        assertEquals(195, taskEntity0.getTaskUserCode());
        TaskPlan2025Entity taskEntity1 = listTaskPlan.get(1);
        assertEquals(196, taskEntity1.getTaskUserCode());
    }

}
