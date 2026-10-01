package net.seijishikin.jp.normalize.manage.kanrensha.service.year.undefined;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskHistoryCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearSearchTaskHistoryService;

/**
 * SwitchYearSearchTaskHistoryService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
class SwitchYearSearchTaskHistoryUndefinedServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearSearchTaskHistoryService switchYearSearchTaskHistoryService;

    @Test
    @Tag("TableTruncate")
    void test2019() {

        SearchTaskHistoryCapsuleDto capsuleDto = new SearchTaskHistoryCapsuleDto();
        capsuleDto.setTaskYear(1001);
        capsuleDto.setTaskPlanCode(187);

        assertThrows(IllegalArgumentException.class, () -> switchYearSearchTaskHistoryService.practice(capsuleDto));
    }

}
