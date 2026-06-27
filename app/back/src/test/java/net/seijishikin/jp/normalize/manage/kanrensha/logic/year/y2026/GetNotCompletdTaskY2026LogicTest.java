package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * GetNotCompletdTaskY2026Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
// @Transactional
@Sql("GetNotCompletdTaskY2026LogicTest.sql")
class GetNotCompletdTaskY2026LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetNotCompletdTaskY2026Logic getNotCompletdTaskY2026Logic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        List<TaskPlanBaseEntity> list = getNotCompletdTaskY2026Logic.practice(CreateLeastUserForTestUtil.practice(), 3);

        assertEquals(3, list.size());

        assertEquals(427, list.get(0).getTaskPlanId());
        assertEquals(426, list.get(1).getTaskPlanId());
        assertEquals(425, list.get(2).getTaskPlanId());

        // 対象がなくても正常に空リストが返るだけ
        LeastUserDto leastUserDto = new LeastUserDto();
        leastUserDto.setUserPersonCode(1249);
        List<TaskPlanBaseEntity> list1 = getNotCompletdTaskY2026Logic.practice(leastUserDto, 3);
        assertTrue(list1.isEmpty());

    }

}
