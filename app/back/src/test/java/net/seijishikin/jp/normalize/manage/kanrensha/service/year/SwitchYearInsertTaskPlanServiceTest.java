package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

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

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.entity.AllTabeDataHistoryInterface;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskInfoEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.TaskPlan2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.TaskPlan2026Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SwitchYearInsertTaskPlanService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SwitchYearInsertTaskPlanServiceTest.sql")
class SwitchYearInsertTaskPlanServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SwitchYearInsertTaskPlanService switchYearInsertTaskPlanService;

    /** タスク計画Repository(2026) */
    @Autowired
    private TaskPlan2026Repository taskPlan2026Repository;

    @Test
    @Tag("TableTruncate")
    void test2026() {

        LeastUserDto usertDto = CreateLeastUserForTestUtil.practice();

        TaskInfoEntity taskInfoEntity = new TaskInfoEntity();
        taskInfoEntity.setTaskInfoId(123);
        taskInfoEntity.setTaskInfoCode(75);
        taskInfoEntity.setTaskInfoName("タスク名称");
        taskInfoEntity.setRoleList("manager");
        // taskInfoEntity.setMessageTemplate(null);
        taskInfoEntity.setParamQuery("asd,zxc");
        taskInfoEntity.setTransferPass("http://localhost:6180/manage-kanrensha/edit-page");

        LocalDateTime createTime = LocalDateTime.of(2026, 12, 5, 12, 34, 56);
        Map<String, String> map = new TreeMap<>();
        map.put("asd", "123");
        map.put("zxc", "456");

        InsertTaskPlanResultDto resultDto = switchYearInsertTaskPlanService.practice(usertDto, createTime,
                taskInfoEntity, map);
        assertEquals(2026, resultDto.getTaskYear());

        Integer savedId = resultDto.getTaskPlanId();
        assertNotEquals(0, savedId); // 0でなければ登録成功

        TaskPlan2026Entity planEntity = taskPlan2026Repository.findById(savedId).get();

        assertEquals(createTime.getYear(), planEntity.getTableYear());
        assertEquals(188, planEntity.getTaskPlanCode());
        assertEquals("タスク名称", planEntity.getTaskPlanName());
        assertEquals(taskInfoEntity.getTaskInfoCode(), planEntity.getTaskInfoCode());

        assertEquals(createTime, planEntity.getStartDatetime());
        assertEquals(AllTabeDataHistoryInterface.INIT_TIMESTAMP, planEntity.getEndDateimte());
        assertEquals(false, planEntity.getIsFinished());
        assertEquals(false, planEntity.getIsStart());
        assertEquals(false, planEntity.getIsSuspended());
        assertEquals(taskInfoEntity.getRoleList(), planEntity.getRoleList());

        assertEquals("http://localhost:6180/manage-kanrensha/edit-page?asd=123&zxc=456", planEntity.getTransferPass());
    }

}
