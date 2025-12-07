package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.InsertTaskPlanY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.TaskPlan2025Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SwitchYearUpdateTaskPlanService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SwitchYearInsertTaskPlanServiceTest.sql")
@Transactional
class SwitchYearUpdateTaskPlanServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearUpdateTaskPlanService switchYearUpdateTaskPlanService;

    /** タスク計画Repository(2025) */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

    /** タスク計画挿入Logic(2025) */
    @Autowired
    private InsertTaskPlanY2025Logic insertTaskPlanY2025Logic;

    @Test
    void testNoSource() throws Exception {
        assertThrows(IllegalArgumentException.class,
                () -> switchYearUpdateTaskPlanService.practice(1001, CreateLeastUserForTestUtil.practice(), 533, true));
    }

    @Test
    void test() throws Exception {
        // 指定年度のデータが増える
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        Integer newId = insertTaskPlanY2025Logic.practice(userDto, 101);

        Integer countPre = Math.toIntExact(taskPlan2025Repository.count());

        switchYearUpdateTaskPlanService.practice(2025, userDto, newId, false);

        Integer countPro = Math.toIntExact(taskPlan2025Repository.count());

        assertEquals(1, countPre);
        assertEquals(2, countPro);
    }

}
