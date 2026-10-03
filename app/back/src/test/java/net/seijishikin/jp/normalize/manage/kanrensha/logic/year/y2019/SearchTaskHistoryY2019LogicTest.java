package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;

/**
 * SearchTaskHistoryY2019Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SearchTaskHistoryY2019LogicTest.sql")
class SearchTaskHistoryY2019LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchTaskHistoryY2019Logic searchTaskHistoryY2019Logic;
    
    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        Integer taskCode = 187;

        List<TaskPlanBaseEntity> list = searchTaskHistoryY2019Logic.practice(taskCode);

        assertEquals(2, list.size());

        TaskPlanBaseEntity entity0 = list.get(0);
        assertEquals(202, entity0.getTaskPlanId());
        assertEquals(2019, entity0.getTableYear());

        TaskPlanBaseEntity entity1 = list.get(1);
        assertEquals(203, entity1.getTaskPlanId());
        assertEquals(2019, entity0.getTableYear());
    }

}
