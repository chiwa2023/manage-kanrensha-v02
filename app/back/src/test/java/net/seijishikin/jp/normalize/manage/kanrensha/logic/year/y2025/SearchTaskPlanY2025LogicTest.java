package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskPlanCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;

/**
 * SearchTaskPlanY2025Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SearchTaskPlanY2025LogicTest.sql")
class SearchTaskPlanY2025LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchTaskPlanY2025Logic searchTaskPlanY2025Logic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        SearchTaskPlanCapsuleDto capsuleDto0 = new SearchTaskPlanCapsuleDto();
        capsuleDto0.setAllCount(0);
        capsuleDto0.setLimit(30);
        capsuleDto0.setPageNumber(0);
        capsuleDto0.setStartDate(LocalDateTime.of(2025, 6, 1, 0, 0, 0));
        capsuleDto0.setEndDate(LocalDateTime.of(2025, 12, 31, 23, 59, 59));

        SearchTaskPlanResultDto resultDto0 = searchTaskPlanY2025Logic.practice(capsuleDto0);

        List<TaskPlanBaseEntity> list0 = resultDto0.getListTaskPlan();
        assertEquals(2, list0.size());
        assertEquals(203, list0.get(0).getTaskPlanId());
        assertEquals(205, list0.get(1).getTaskPlanId());

        SearchTaskPlanCapsuleDto capsuleDto1 = new SearchTaskPlanCapsuleDto();
        capsuleDto1.setAllCount(0);
        capsuleDto1.setLimit(30);
        capsuleDto1.setPageNumber(0);
        capsuleDto1.setStartDate(LocalDateTime.of(2025, 6, 1, 0, 0, 0));
        capsuleDto1.setEndDate(LocalDateTime.of(2025, 12, 31, 23, 59, 59));
        capsuleDto1.setSearchTaskWord("名称4");

        SearchTaskPlanResultDto resultDto1 = searchTaskPlanY2025Logic.practice(capsuleDto1);

        List<TaskPlanBaseEntity> list1 = resultDto1.getListTaskPlan();
        assertEquals(1, list1.size());
        assertEquals(205, list1.get(0).getTaskPlanId());
    }

}
