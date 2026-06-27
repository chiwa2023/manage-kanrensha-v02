package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

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
 * SwitchYearSearchTaskPlanService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SwitchYearSearchTaskPlanServiceTest.sql")
class SwitchYearSearchTaskPlanServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearSearchTaskPlanService switchYearSearchTaskPlanService;

    @Test
    @Tag("FullTextSearch")
    void test2025() throws Exception {

        SearchTaskPlanCapsuleDto capsuleDto = new SearchTaskPlanCapsuleDto();
        capsuleDto.setAllCount(0);
        capsuleDto.setLimit(30);
        capsuleDto.setPageNumber(0);
        capsuleDto.setStartDate(LocalDateTime.of(2025, 5, 2, 0, 0, 0));
        capsuleDto.setEndDate(LocalDateTime.of(2025, 12, 31, 23, 59, 59));
        
        SearchTaskPlanResultDto resultDto = switchYearSearchTaskPlanService.practice(capsuleDto);
        
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit(),"最後に取得できるLimitは必ず初期のLimitに一致する");
        assertEquals(2, resultDto.getAllCount());
        assertEquals(0, resultDto.getPageNumber());
        List<TaskPlanBaseEntity> list = resultDto.getListTaskPlan();
        assertEquals(2, list.size(),"年をまたいでいないので取得できたリストと全件数が一致");
    }

}
