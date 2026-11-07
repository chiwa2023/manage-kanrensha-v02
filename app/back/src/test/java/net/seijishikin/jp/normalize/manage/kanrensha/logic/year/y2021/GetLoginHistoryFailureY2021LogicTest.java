package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021;

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
 * GetLoginHistoryFailureY2021Logic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetLoginHistoryFailureY2021LogicTest.sql")
class GetLoginHistoryFailureY2021LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetLoginHistoryFailureY2021Logic getLoginHistoryFailureY2021Logic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        LocalDateTime startDateTime = LocalDateTime.of(LocalDate.of(2021, 4, 5), LocalTime.MIN);
        LocalDateTime endDateTime = LocalDateTime.of(LocalDate.of(2021, 4, 6), LocalTime.MAX);

        // メールアドレスが異なる
        assertEquals(0, getLoginHistoryFailureY2021Logic.practice("123@example.com", startDateTime, endDateTime));

        assertEquals(3, getLoginHistoryFailureY2021Logic.practice("aaa@politician.balanse.report.net", startDateTime,
                endDateTime));
    }

}
