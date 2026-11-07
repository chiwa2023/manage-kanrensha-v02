package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * GetLoginHistoryFailureY2020Logic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetLoginHistoryFailureY2020LogicTest.sql")
class GetLoginHistoryFailureY2020LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetLoginHistoryFailureY2020Logic getLoginHistoryFailureY2020Logic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        LocalDateTime startDateTime = LocalDateTime.of(LocalDate.of(2020, 4, 5), LocalTime.MIN);
        LocalDateTime endDateTime = LocalDateTime.of(LocalDate.of(2020, 4, 6), LocalTime.MAX);

        // メールアドレスが異なる
        assertEquals(0, getLoginHistoryFailureY2020Logic.practice("123@example.com", startDateTime, endDateTime));

        assertEquals(3, getLoginHistoryFailureY2020Logic.practice("aaa@politician.balanse.report.net", startDateTime,
                endDateTime));
    }

}
