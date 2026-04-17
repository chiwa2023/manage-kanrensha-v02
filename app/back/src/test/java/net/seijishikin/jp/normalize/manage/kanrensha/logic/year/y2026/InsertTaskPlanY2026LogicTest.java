package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskPlanInfoDto;
import net.seijishikin.jp.normalize.common_tool.entity.AllTabeDataHistoryInterface;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskInfoEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.TaskPlan2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.TaskPlan2026Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertTaskPlanY2026Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("InsertTaskPlanY2026LogicTest.sql")
@Transactional
class InsertTaskPlanY2026LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private InsertTaskPlanY2026Logic insertTaskPlanY2026Logic;

    /** タスク計画Repository(2026) */
    @Autowired
    private TaskPlan2026Repository taskPlan2026Repository;


    @Tag("TableTruncate")
    @Transactional
    void test() {

        
//        
//        TaskInfoEntity taskInfoEntity = new TaskInfoEntity();
//        taskInfoEntity.setTaskInfoId(123);
//        taskInfoEntity.setTaskInfoCode(75);
//        taskInfoEntity.setTaskInfoName("タスク名称");
//        taskInfoEntity.setRoleList("manager");
//        // taskInfoEntity.setMessageTemplate(null);
//        taskInfoEntity.setParamQuery("asd,zxc");
//        taskInfoEntity.setTransferPass("http://localhost:6180/kanrensha-manage/edit-page");
//
//        LocalDateTime createTime = LocalDateTime.of(2026, 12, 5, 12, 34, 56);
//
//        Map<String, String> map = new TreeMap<>();
//        map.put("asd", "123");
//        map.put("zxc", "456");
//
//        InsertTaskPlanResultDto resultDto = insertTaskPlanY2026Logic.practice(CreateLeastUserForTestUtil.practice(),
//                createTime, taskInfoEntity, map);
//        assertEquals(2026, resultDto.getTaskYear());
//        Integer savedId = resultDto.getTaskPlanId();
//
//        TaskPlan2026Entity planEntity = taskPlan2026Repository.findById(savedId).get();
//
//        assertEquals(createTime.getYear(), planEntity.getTableYear());
//        assertEquals(188, planEntity.getTaskPlanCode());
//        assertEquals("タスク名称", planEntity.getTaskPlanName());
//        assertEquals(taskInfoEntity.getTaskInfoCode(), planEntity.getTaskInfoCode());
//
//        assertEquals(createTime, planEntity.getStartDatetime());
//        assertEquals(AllTabeDataHistoryInterface.INIT_TIMESTAMP, planEntity.getEndDateimte());
//        assertEquals(false, planEntity.getIsFinished());
//        assertEquals(false, planEntity.getIsStart());
//        assertEquals(false, planEntity.getIsSuspended());
//        assertEquals(taskInfoEntity.getRoleList(), planEntity.getRoleList());
//
//        assertEquals("http://localhost:6180/kanrensha-manage/edit-page?asd=123&zxc=456", planEntity.getTransferPass());
        
        
        fail();
    }

}
