package net.seijishikin.jp.normalize.manage.kanrensha.service.year.undefined;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskPlanCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearSearchTaskPlanService;

/**
 * SwitchYearSearchTaskPlanService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
class SwitchYearSearchTaskPlanUndefinedServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearSearchTaskPlanService switchYearSearchTaskPlanService;

    @Test
    @Tag("FullTextSearch")
    void test2019() throws Exception {

        SearchTaskPlanCapsuleDto capsuleDto = new SearchTaskPlanCapsuleDto();
        capsuleDto.setAllCount(0);
        capsuleDto.setLimit(30);
        capsuleDto.setPageNumber(0);
        capsuleDto.setStartDate(LocalDateTime.of(1001, 5, 2, 0, 0, 0));
        capsuleDto.setEndDate(LocalDateTime.of(1001, 12, 31, 23, 59, 59));

        assertThrows(IllegalArgumentException.class, () -> switchYearSearchTaskPlanService.practice(capsuleDto));

    }

}
