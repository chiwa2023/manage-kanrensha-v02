package net.seijishikin.jp.normalize.manage.kanrensha.service.task_plan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertTaskPlanService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("InsertTaskPlanServiceNothingMailTest.sql")
class InsertTaskPlanServiceNothingMailTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private InsertTaskPlanService insertTaskPlanService;

    // メール関係エラーは別ファイル
    @Test
    @Tag("ExternalService")
    void testMail() {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        userDto.setUserPersonId(197); // 開始時メール送信するユーザ
        userDto.setUserPersonCode(191);
        LocalDateTime createDatetime = LocalDateTime.of(2026, 12, 5, 12, 34, 56);
        FrameworkMessageAndResultDto resultDto = insertTaskPlanService.practice(userDto, createDatetime,
                TaskInfoConstants.PROMOTE_ADMIN,null);
        assertTrue(resultDto.getIsFailure());
        assertEquals("メール送信時に例外が発生しています", resultDto.getMessage());
    }

    @Test
    void testNotSendMail() {
        // メール送信機能が切れいていても、設定がユーザメール送信なしなら失敗にはならない
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        LocalDateTime createDatetime = LocalDateTime.of(2026, 12, 5, 12, 34, 56);
        FrameworkMessageAndResultDto resultDto = insertTaskPlanService.practice(userDto, createDatetime,
                TaskInfoConstants.PROMOTE_ADMIN,null);
        assertFalse(resultDto.getIsFailure());
    }

}
