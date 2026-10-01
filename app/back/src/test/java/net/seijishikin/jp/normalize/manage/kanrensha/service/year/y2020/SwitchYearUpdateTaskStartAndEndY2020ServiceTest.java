package net.seijishikin.jp.normalize.manage.kanrensha.service.year.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2020.TaskPlan2020Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2020.TaskPlan2020Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearUpdateTaskStartAndEndService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SwitchYearUpdateTaskStartAndEndService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SwitchYearUpdateTaskStartAndEndY2020ServiceTest.sql")
class SwitchYearUpdateTaskStartAndEndY2020ServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearUpdateTaskStartAndEndService switchYearUpdateTaskStartAndEndService;

    /** タスク計画Repository(2020) */
    @Autowired
    private TaskPlan2020Repository taskPlan2020Repository;

    @Test
    @Tag("TableTruncate")
    void test2020() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        LocalDateTime endTime = LocalDateTime.of(2028, 3, 21, 12, 34, 56); // あえて終了年を登録テーブルと異なる値にしている

        final Integer loadId = 203;
        Integer savedId = switchYearUpdateTaskStartAndEndService.practice(userDto, 2020, loadId, endTime);
        // 履歴が積みあがっていること
        assertNotEquals(loadId, savedId);

        TaskPlan2020Entity entityPre = taskPlan2020Repository.findById(loadId).get();
        // 過去データに未使用フラグ以外の変更はないこと
        assertFalse(entityPre.getIsLatest());
        assertFalse(entityPre.getIsStart());
        assertFalse(entityPre.getIsFinished());

        TaskPlan2020Entity entityPro = taskPlan2020Repository.findById(savedId).get();
        assertEquals(entityPre.getTaskPlanCode(), entityPro.getTaskPlanCode()); // 同じコード
        // 積み上げた履歴は終了履歴であること
        assertTrue(entityPro.getIsLatest());
        assertTrue(entityPro.getIsFinished());
        assertTrue(entityPro.getIsStart());
        assertEquals(LocalDateTime.of(2022, 12, 5, 12, 34, 56), entityPro.getStartDatetime());
        assertEquals(endTime, entityPro.getEndDateimte());
    }

}
