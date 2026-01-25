package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.AcceptUserAdminCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.PromoteAdminEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.TaskPlan2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.PromoteAdminRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.TaskPlan2026Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * AcceptUserAdminService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("AcceptUserAdminServiceTest.sql")
class AcceptUserAdminServiceTest {
    // CHECKSTYLE:OFF MagicNUmber

    /** テスト対象 */
    @Autowired
    private AcceptUserAdminService acceptUserAdminService;

    /** SE権限追加推薦Repository */
    @Autowired
    private PromoteAdminRepository promoteAdminRepository;

    /** タスク計画Repository(2026) */
    @Autowired
    private TaskPlan2026Repository taskPlan2026Repository;

    /** タスク計画Repository(2026) */
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Test
    @Tag("ExternalService")
    void test() throws Exception {

        AcceptUserAdminCapsuleDto capsuleDto = new AcceptUserAdminCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        PromoteAdminEntity promoteAdminEntity = new PromoteAdminEntity();
        promoteAdminEntity.setIsAccept(true); // 使うのはこの値だけ
        capsuleDto.setPromoteAdminEntity(promoteAdminEntity);

        LocalDateTime endTime = LocalDateTime.of(2028, 3, 21, 12, 34, 56); // あえて終了年を登録テーブルと異なる値にしている
        
        Integer count = acceptUserAdminService.practice(capsuleDto, endTime);
        assertEquals(6, count);

        List<PromoteAdminEntity> listPromote = promoteAdminRepository.findAll();

        PromoteAdminEntity entityPromote0 = listPromote.get(0);
        assertFalse(entityPromote0.getIsLatest());
        PromoteAdminEntity entityPromote1 = listPromote.get(1);
        assertFalse(entityPromote1.getIsLatest());
        PromoteAdminEntity entityPromote2 = listPromote.get(2);
        assertFalse(entityPromote2.getIsLatest());

        PromoteAdminEntity entityPromote3 = listPromote.get(3);
        assertFalse(entityPromote3.getIsLatest());
        assertTrue(entityPromote3.getIsAccept());

        PromoteAdminEntity entityPromote4 = listPromote.get(4);
        assertFalse(entityPromote4.getIsLatest());
        assertTrue(entityPromote4.getIsAccept());
        PromoteAdminEntity entityPromote5 = listPromote.get(5);
        assertFalse(entityPromote5.getIsLatest());
        assertTrue(entityPromote5.getIsAccept());

        List<TaskPlan2026Entity> listTask = taskPlan2026Repository.findAll();

        TaskPlan2026Entity entityTask0 = listTask.get(0);
        assertFalse(entityTask0.getIsLatest());
        TaskPlan2026Entity entityTask1 = listTask.get(1);
        assertFalse(entityTask1.getIsLatest());
        TaskPlan2026Entity entityTask2 = listTask.get(2);
        assertFalse(entityTask2.getIsLatest());

        TaskPlan2026Entity entityTask3 = listTask.get(3);
        assertFalse(entityTask3.getIsLatest());
        assertEquals(true, entityTask3.getIsStart());
        assertEquals(true, entityTask3.getIsFinished());
        assertEquals(endTime, entityTask3.getStartDatetime());
        assertEquals(endTime, entityTask3.getEndDateimte());

        TaskPlan2026Entity entityTask4 = listTask.get(4);
        assertFalse(entityTask4.getIsLatest());
        assertEquals(true, entityTask4.getIsStart());
        assertEquals(true, entityTask4.getIsFinished());
        assertEquals(endTime, entityTask4.getStartDatetime());
        assertEquals(endTime, entityTask4.getEndDateimte());

        TaskPlan2026Entity entityTask5 = listTask.get(5);
        assertFalse(entityTask5.getIsLatest());
        assertEquals(true, entityTask5.getIsStart());
        assertEquals(true, entityTask5.getIsFinished());
        assertEquals(endTime, entityTask5.getStartDatetime());
        assertEquals(endTime, entityTask5.getEndDateimte());

        // admin権限が追加されたことを確認
        List<UserRoleEntity> listRole = userRoleRepository.findAll();
        assertEquals(2, listRole.size());
        UserRoleEntity entityRole = listRole.get(1);
        assertEquals(UserRoleConstants.ADMIN, entityRole.getRole());
        
        // MEMO 回答完了メールを目視確認
    }

}
