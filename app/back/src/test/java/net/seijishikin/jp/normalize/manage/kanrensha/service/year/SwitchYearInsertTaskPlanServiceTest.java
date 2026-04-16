package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.TaskPlan2025Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SwitchYearInsertTaskPlanService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SwitchYearInsertTaskPlanServiceTest.sql")
@Transactional
class SwitchYearInsertTaskPlanServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearInsertTaskPlanService switchYearInsertTaskPlanService;

    /** タスク計画Repository(2025) */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

    @Test
    void testNoSource() throws Exception {
        assertThrows(IllegalArgumentException.class,
                () -> switchYearInsertTaskPlanService.practice(1001, CreateLeastUserForTestUtil.practice(), 533));
    }

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        Integer countPre = Math.toIntExact(taskPlan2025Repository.count());
        Integer taskCode = 101;

        switchYearInsertTaskPlanService.practice(2025, CreateLeastUserForTestUtil.practice(), taskCode);

        Integer countPro = Math.toIntExact(taskPlan2025Repository.count());

        assertEquals(0, countPre);
        assertEquals(1, countPro);
    }
}
