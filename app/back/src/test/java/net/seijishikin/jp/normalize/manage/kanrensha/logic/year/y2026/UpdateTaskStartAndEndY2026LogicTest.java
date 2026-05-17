package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026; // NOPMD ManyStaticImport 

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.TaskPlan2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.TaskPlan2026Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * UpdateTaskStartAndEndY2026Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
// @Transactional
@Sql("UpdateTaskStartAndEndY2026LogicTest.sql")
class UpdateTaskStartAndEndY2026LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private UpdateTaskStartAndEndY2026Logic updateTaskStartAndEndY2026Logic;

    /** タスク計画Repository(2026) */
    @Autowired
    private TaskPlan2026Repository taskPlan2026Repository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        LocalDateTime endTime = LocalDateTime.of(2028, 3, 21, 12, 34, 56); // あえて終了年を登録テーブルと異なる値にしている

        // 更新元が取得できない場合は作業全体を中断するために例外を投げる
        assertThrows(EmptyResultDataAccessException.class,
                () -> updateTaskStartAndEndY2026Logic.practice(userDto, 11, endTime));

        final Integer loadId = 523;
        Integer savedId = updateTaskStartAndEndY2026Logic.practice(userDto, loadId, endTime);
        // 履歴が積みあがっていること
        assertNotEquals(loadId, savedId);

        TaskPlan2026Entity entityPre = taskPlan2026Repository.findById(loadId).get();
        // 過去データに未使用フラグ以外の変更はないこと
        assertFalse(entityPre.getIsLatest());
        assertTrue(entityPre.getIsStart());
        assertFalse(entityPre.getIsFinished());

        TaskPlan2026Entity entityPro = taskPlan2026Repository.findById(savedId).get();
        assertEquals(entityPre.getTaskPlanCode(), entityPro.getTaskPlanCode()); // 同じコード
        // 積み上げた履歴は終了履歴であること
        assertTrue(entityPro.getIsLatest());
        assertTrue(entityPro.getIsFinished());
        assertTrue(entityPro.getIsStart());
        assertEquals(entityPre.getStartDatetime(), entityPro.getStartDatetime());
        assertEquals(endTime, entityPro.getEndDateimte());
    }

}
