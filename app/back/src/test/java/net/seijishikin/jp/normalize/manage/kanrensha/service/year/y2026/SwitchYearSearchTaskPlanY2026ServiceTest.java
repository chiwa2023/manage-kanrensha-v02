package net.seijishikin.jp.normalize.manage.kanrensha.service.year.y2026;

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
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearSearchTaskPlanService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SwitchYearSearchTaskPlanService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SwitchYearSearchTaskPlanY2026ServiceTest.sql")
class SwitchYearSearchTaskPlanY2026ServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearSearchTaskPlanService switchYearSearchTaskPlanService;

    @Test
    @Tag("FullTextSearch")
    void test2026() throws Exception {

        SearchTaskPlanCapsuleDto capsuleDto = new SearchTaskPlanCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setAllCount(0);
        capsuleDto.setLimit(30);
        capsuleDto.setPageNumber(0);
        capsuleDto.setStartDate(LocalDateTime.of(2026, 6, 1, 0, 0, 0));
        capsuleDto.setEndDate(LocalDateTime.of(2026, 12, 31, 23, 59, 59));
        capsuleDto.setSearchTaskWord("名称4");
        capsuleDto.setFlgFinished(1); // 有効
        capsuleDto.setFlgStart(1); // 有効
        capsuleDto.setFlgSuspended(1); // 有効
        // タスク情報種類有効
        List<Integer> listCode = new ArrayList<>();
        listCode.add(TaskInfoConstants.PROMOTE_ADMIN);
        listCode.add(TaskInfoConstants.SAVE_POSTAL_REPAIR_CSV);
        capsuleDto.setInfoCodeList(listCode);

        SearchTaskPlanResultDto resultDto = switchYearSearchTaskPlanService.practice(capsuleDto);

        assertEquals(capsuleDto.getLimit(), resultDto.getLimit(), "最後に取得できるLimitは必ず初期のLimitに一致する");
        assertEquals(2, resultDto.getAllCount());
        assertEquals(0, resultDto.getPageNumber());
        List<TaskPlanBaseEntity> list = resultDto.getListTaskPlan();
        assertEquals(2, list.size(), "年をまたいでいないので取得できたリストと全件数が一致");
    }

}
