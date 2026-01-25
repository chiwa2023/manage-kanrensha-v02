package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.entity.AllTabeDataHistoryInterface;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.PromoteUserAdminCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.PromoteAdminEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.TaskPlan2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.PromoteAdminRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.TaskPlan2026Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * PromoteUserAdminService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("PromoteUserAdminServiceTest.sql")
class PromoteUserAdminServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private PromoteUserAdminService promoteUserAdminService;

    /** タスク計画Repository */
    @Autowired
    private TaskPlan2026Repository taskPlan2026Repository;

    /** SE権限推薦Repository */
    @Autowired
    private PromoteAdminRepository promoteAdminRepository;

    @Test
    @Tag("ExternalService")
    void test() {

        PromoteUserAdminCapsuleDto capsuleDto = new PromoteUserAdminCapsuleDto();
        LeastUserDto userDtoWorker = CreateLeastUserForTestUtil.practice();
        capsuleDto.setUserDto(userDtoWorker);
        UserPersonEntity promoteEntity = new UserPersonEntity();
        promoteEntity.setUserPersonId(323);
        promoteEntity.setUserPersonCode(310);
        promoteEntity.setUserPersonName("bbb");
        capsuleDto.setEntityUserPromote(promoteEntity);

        LocalDateTime createDatetime = LocalDateTime.of(2026, 4, 7, 2, 19, 31);

        FrameworkMessageAndResultDto resultDto = promoteUserAdminService.practice(capsuleDto, createDatetime);
        assertFalse(resultDto.getIsFailure());
        
        // タスク計画挿入処理が付帯処理として実施されていること
        List<TaskPlan2026Entity> listPlan = taskPlan2026Repository.findAll();
        assertEquals(1, listPlan.size());

        TaskPlan2026Entity planEntity = listPlan.get(0);
        assertEquals(createDatetime.getYear(), planEntity.getTableYear());
        assertEquals(1, planEntity.getTaskPlanCode());
        assertEquals("SE権限推薦処理", planEntity.getTaskPlanName());
        assertEquals(901, planEntity.getTaskInfoCode());

        assertEquals(createDatetime, planEntity.getStartDatetime());
        assertEquals(AllTabeDataHistoryInterface.INIT_TIMESTAMP, planEntity.getEndDateimte());
        assertEquals(false, planEntity.getIsFinished());
        assertEquals(false, planEntity.getIsStart());
        assertEquals(false, planEntity.getIsSuspended());
        assertEquals("admin,manager", planEntity.getRoleList());
        assertEquals("pageUrl", planEntity.getTransferPass());

        List<PromoteAdminEntity> list = promoteAdminRepository.findAll();
        assertEquals(1, list.size());

        PromoteAdminEntity entity = list.get(0);

        assertEquals(userDtoWorker.getUserPersonId(), entity.getInsertUserId());
        assertEquals(userDtoWorker.getUserPersonCode(), entity.getInsertUserCode());
        assertEquals(userDtoWorker.getUserPersonName(), entity.getInsertUserName());

        assertEquals(promoteEntity.getUserPersonId(), entity.getPromoteUserId());
        assertEquals(promoteEntity.getUserPersonCode(), entity.getPromoteUserCode());
        assertEquals(promoteEntity.getUserPersonName(), entity.getPromoteUserName());

        // タスク計画との紐づきも確認
        assertEquals(planEntity.getTableYear(), entity.getTaskYear());
        assertEquals(planEntity.getTaskPlanId(), entity.getTaskPlanId());
        assertFalse(entity.getIsAccept());
        assertTrue(entity.getIsLatest());
    }

}
