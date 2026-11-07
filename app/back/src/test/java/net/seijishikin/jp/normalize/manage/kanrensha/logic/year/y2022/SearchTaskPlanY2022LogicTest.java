package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskPlanCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SearchTaskPlanY2022Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SearchTaskPlanY2022LogicTest.sql")
class SearchTaskPlanY2022LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchTaskPlanY2022Logic searchTaskPlanY2022Logic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        SearchTaskPlanCapsuleDto capsuleDto1 = new SearchTaskPlanCapsuleDto();
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto1.setAllCount(0);
        capsuleDto1.setLimit(30);
        capsuleDto1.setPageNumber(0);
        capsuleDto1.setStartDate(LocalDateTime.of(2022, 6, 1, 0, 0, 0));
        capsuleDto1.setEndDate(LocalDateTime.of(2022, 12, 31, 23, 59, 59));
        capsuleDto1.setSearchTaskWord("名称4");
        capsuleDto1.setFlgFinished(1); // 有効
        capsuleDto1.setFlgStart(1); // 有効
        capsuleDto1.setFlgSuspended(1); // 有効
        // タスク情報種類有効
        List<Integer> listCode = new ArrayList<>();
        listCode.add(TaskInfoConstants.PROMOTE_ADMIN);
        listCode.add(TaskInfoConstants.SAVE_POSTAL_REPAIR_CSV);
        capsuleDto1.setInfoCodeList(listCode);

        final Integer taskInfoCount = 3;

        SearchTaskPlanResultDto resultDto1 = searchTaskPlanY2022Logic.practice(taskInfoCount, capsuleDto1);
        List<TaskPlanBaseEntity> list1 = resultDto1.getListTaskPlan();
        assertEquals(2, list1.size());
        assertEquals(205, list1.get(0).getTaskPlanId());
        assertEquals(206, list1.get(1).getTaskPlanId());
    }

}
