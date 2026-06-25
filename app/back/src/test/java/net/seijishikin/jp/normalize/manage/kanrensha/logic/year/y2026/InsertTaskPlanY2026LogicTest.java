package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
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
@Transactional
@Sql("InsertTaskPlanY2026LogicTest.sql")
class InsertTaskPlanY2026LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private InsertTaskPlanY2026Logic insertTaskPlanY2026Logic;

    /** タスク計画Repository(2026) */
    @Autowired
    private TaskPlan2026Repository taskPlan2026Repository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        final Integer taskCode = TaskInfoConstants.SAVE_POSTAL_REPAIR_CSV;
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        LocalDateTime dateTimeStart = LocalDateTime.of(2026, 12, 13, 11, 22, 33);
        Map<String, String> map = new TreeMap<>();
        map.put("asd", "123");
        map.put("zxc", "456");
        LeastUserDto workUserDto = new LeastUserDto();
        workUserDto.setUserPersonCode(854);
        workUserDto.setUserPersonName("利用者　直子");

        InsertTaskPlanResultDto dto = insertTaskPlanY2026Logic.practice(workUserDto, userDto, dateTimeStart, taskCode,
                map);

        TaskPlan2026Entity entity = taskPlan2026Repository.findById(dto.getTaskPlanId()).get();

        assertEquals(true, entity.getIsLatest());

        assertEquals(dateTimeStart.getYear(), entity.getTableYear());
        assertEquals(taskCode, entity.getTaskInfoCode());
        assertEquals(1, entity.getTaskPlanCode());
        assertEquals("郵便番号差分修正", entity.getTaskPlanName());
        assertEquals(dateTimeStart, entity.getStartDatetime());
        assertEquals(DtoEntityInitialValueInterface.INIT_TIMESTAMP, entity.getEndDateimte());
        assertEquals(true, entity.getIsStart());
        assertEquals(false, entity.getIsSuspended());
        assertEquals(false, entity.getIsFinished());
        assertEquals("admin,manager", entity.getRoleList());
        assertEquals("http://localhost:6180/kanrensha-manage/edit-page?asd=123&zxc=456", entity.getTransferPass());
        assertEquals(workUserDto.getUserPersonCode(), entity.getTaskUserCode());
        assertEquals(workUserDto.getUserPersonName(), entity.getTaskUserName());

        assertEquals(entity.getTaskInfoCode(), dto.getTaskInfoCode());
        assertEquals(entity.getTaskPlanCode(), dto.getTaskPlanCode());
        assertEquals(entity.getTaskPlanId(), dto.getTaskPlanId());
        assertEquals(entity.getTaskPlanName(), dto.getTaskPlanName());
        assertEquals(entity.getTableYear(), dto.getTaskYear());
        assertEquals(entity.getTransferPass(), dto.getTransferPass());

        assertThrows(EmptyResultDataAccessException.class,
                () -> insertTaskPlanY2026Logic.practice(null, userDto, dateTimeStart, 622, map));

        // 本人宛タスク
        InsertTaskPlanResultDto dto1 = insertTaskPlanY2026Logic.practice(null, userDto, dateTimeStart, taskCode, map);
        TaskPlan2026Entity entity1 = taskPlan2026Repository.findById(dto1.getTaskPlanId()).get();
        assertEquals(userDto.getUserPersonCode(), entity1.getTaskUserCode());
        assertEquals(userDto.getUserPersonName(), entity1.getTaskUserName());
    }

}
