package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_history;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskPlanInfoDto;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RetryBatchHistoryKigyouDtService
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("RetryBatchHistoryKigyouDtServiceTest.sql")
class RetryBatchHistoryKigyouDtServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private RetryBatchHistoryKigyouDtService retryBatchHistoryKigyouDtService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {
        final Integer year = 2026;
        TaskPlanInfoDto planDto = new TaskPlanInfoDto();
        planDto.setTaskPlanId(453);
        planDto.setTaskPlanCode(187);

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        assertDoesNotThrow(() -> retryBatchHistoryKigyouDtService.practice(userDto, year, planDto));
    }

    @Test
    @Tag("TableTruncate")
    void testBatchError() throws Exception {
        // バッチパラメータが正常でない場合はここで例外処理をする
        // バッチ内部例外は別で処理をする
        TaskPlanInfoDto planDto = new TaskPlanInfoDto();
        planDto.setTaskPlanId(424);
        planDto.setTaskPlanCode(7);

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        assertDoesNotThrow(() -> retryBatchHistoryKigyouDtService.practice(userDto, null, planDto));
    }

}
