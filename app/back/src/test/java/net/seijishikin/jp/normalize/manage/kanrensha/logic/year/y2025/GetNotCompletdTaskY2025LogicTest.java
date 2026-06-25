package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * GetNotCompletdTaskY2025Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
// @Transactional
@Sql("GetNotCompletdTaskY2025LogicTest.sql")
class GetNotCompletdTaskY2025LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetNotCompletdTaskY2025Logic getNotCompletdTaskY2025Logic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        List<TaskPlanBaseEntity> list = getNotCompletdTaskY2025Logic.practice(CreateLeastUserForTestUtil.practice(), 3);

        assertEquals(3, list.size());

        assertEquals(427, list.get(0).getTaskPlanId());
        assertEquals(426, list.get(1).getTaskPlanId());
        assertEquals(425, list.get(2).getTaskPlanId());

        // 対象がなくても正常に空リストが返るだけ
        LeastUserDto leastUserDto = new LeastUserDto();
        leastUserDto.setUserPersonCode(1249);
        List<TaskPlanBaseEntity> list1 = getNotCompletdTaskY2025Logic.practice(leastUserDto, 3);
        assertTrue(list1.isEmpty());

    }

}
