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
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RetryBatchHistoryPersonService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("RetryBatchHistoryPersonServiceTest.sql")
class RetryBatchHistoryPersonServiceTest {

    /** テスト対象 */
    @Autowired
    private RetryBatchHistoryPersonService retryBatchHistoryPersonService;

    @Test
    @Transactional
    @Tag("TableTruncate")
    // @Sql({ "sample_wk_tbl_partner_person_history.sql", "delete_hsitory_person.sql" })
    void test() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        assertDoesNotThrow(() -> retryBatchHistoryPersonService.practice(userDto));
    }

}
