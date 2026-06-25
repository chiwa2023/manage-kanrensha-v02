package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
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
 * SearchTaskPlanY2026Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SearchTaskPlanY2026LogicTest.sql")
class SearchTaskPlanY2026LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchTaskPlanY2026Logic searchTaskPlanY2026Logic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        SearchTaskPlanCapsuleDto capsuleDto0 = new SearchTaskPlanCapsuleDto();
        capsuleDto0.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto0.setAllCount(0);
        capsuleDto0.setLimit(30);
        capsuleDto0.setPageNumber(0);
        capsuleDto0.setStartDate(LocalDateTime.of(2026, 6, 1, 0, 0, 0));
        capsuleDto0.setEndDate(LocalDateTime.of(2026, 12, 31, 23, 59, 59));
        // TODO 開始条件とタスクの種類は改めてテストする
        capsuleDto0.setFlgFinished(2);
        capsuleDto0.setFlgStart(2);
        capsuleDto0.setFlgSuspended(2);
        List<Integer> listCode = new ArrayList<>();
        listCode.add(TaskInfoConstants.PROMOTE_ADMIN);
        listCode.add(TaskInfoConstants.SAVE_POSTAL_REPAIR_CSV);
        listCode.add(TaskInfoConstants.WKTBL_KANRENSHA_XML);
        capsuleDto0.setInfoCodeList(listCode);

        SearchTaskPlanResultDto resultDto0 = searchTaskPlanY2026Logic.practice(capsuleDto0);

        List<TaskPlanBaseEntity> list0 = resultDto0.getListTaskPlan();
        assertEquals(2, list0.size());
        assertEquals(203, list0.get(0).getTaskPlanId());
        assertEquals(205, list0.get(1).getTaskPlanId());

        SearchTaskPlanCapsuleDto capsuleDto1 = new SearchTaskPlanCapsuleDto();
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto1.setAllCount(0);
        capsuleDto1.setLimit(30);
        capsuleDto1.setPageNumber(0);
        capsuleDto1.setStartDate(LocalDateTime.of(2026, 6, 1, 0, 0, 0));
        capsuleDto1.setEndDate(LocalDateTime.of(2026, 12, 31, 23, 59, 59));
        capsuleDto1.setSearchTaskWord("名称4");
        capsuleDto1.setFlgFinished(2);
        capsuleDto1.setFlgStart(2);
        capsuleDto1.setFlgSuspended(2);
        capsuleDto1.setInfoCodeList(listCode);

        SearchTaskPlanResultDto resultDto1 = searchTaskPlanY2026Logic.practice(capsuleDto1);
        List<TaskPlanBaseEntity> list1 = resultDto1.getListTaskPlan();
        assertEquals(1, list1.size());
        assertEquals(205, list1.get(0).getTaskPlanId());
    }

}
