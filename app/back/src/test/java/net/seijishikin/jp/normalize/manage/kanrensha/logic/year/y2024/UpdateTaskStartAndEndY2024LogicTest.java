package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024; // NOPMD ManyStaticImport 

import static org.junit.jupiter.api.Assertions.assertEquals; // NOPMD TooManyStaticImport
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2024.TaskPlan2024Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2024.TaskPlan2024Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * UpdateTaskStartAndEndY2024Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
// @Transactional
@Sql("UpdateTaskStartAndEndY2024LogicTest.sql")
class UpdateTaskStartAndEndY2024LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private UpdateTaskStartAndEndY2024Logic updateTaskStartAndEndY2024Logic;

    /** タスク計画Repository(2024) */
    @Autowired
    private TaskPlan2024Repository taskPlan2024Repository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        LocalDateTime endTime = LocalDateTime.of(2028, 3, 21, 12, 34, 56); // あえて終了年を登録テーブルと異なる値にしている

        // 更新元が取得できない場合は作業全体を中断するために例外を投げる
        assertThrows(EmptyResultDataAccessException.class,
                () -> updateTaskStartAndEndY2024Logic.practice(userDto, 11, endTime));

        final Integer loadId = 523;
        Integer savedId = updateTaskStartAndEndY2024Logic.practice(userDto, loadId, endTime);
        // 履歴が積みあがっていること
        assertNotEquals(loadId, savedId);

        TaskPlan2024Entity entityPre = taskPlan2024Repository.findById(loadId).get();
        // 過去データに未使用フラグ以外の変更はないこと
        assertFalse(entityPre.getIsLatest());
        assertTrue(entityPre.getIsStart());
        assertFalse(entityPre.getIsFinished());

        TaskPlan2024Entity entityPro = taskPlan2024Repository.findById(savedId).get();
        assertEquals(entityPre.getTaskPlanCode(), entityPro.getTaskPlanCode()); // 同じコード
        // 積み上げた履歴は終了履歴であること
        assertTrue(entityPro.getIsLatest());
        assertTrue(entityPro.getIsFinished());
        assertTrue(entityPro.getIsStart());
        assertEquals(entityPre.getStartDatetime(), entityPro.getStartDatetime());
        assertEquals(endTime, entityPro.getEndDateimte());
    }

}
