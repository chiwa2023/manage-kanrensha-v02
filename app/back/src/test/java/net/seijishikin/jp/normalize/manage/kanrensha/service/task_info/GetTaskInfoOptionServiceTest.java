package net.seijishikin.jp.normalize.manage.kanrensha.service.task_info;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.task_info.TaskInfoCodeCheckOptionDto;

/**
 * GetTaskInfoOptionService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetTaskInfoOptionServiceTest.sql")
class GetTaskInfoOptionServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetTaskInfoOptionService getTaskInfoOptionService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        List<TaskInfoCodeCheckOptionDto> listAns = getTaskInfoOptionService.practice();
        assertEquals(2, listAns.size());

        TaskInfoCodeCheckOptionDto dto0 = listAns.get(0);
        assertEquals(311, dto0.getCodeValue());
        assertEquals("タスク名称1", dto0.getCodeName());
        assertEquals(true, dto0.getIsChecked());

        TaskInfoCodeCheckOptionDto dto1 = listAns.get(1);
        assertEquals(312, dto1.getCodeValue());
    }

}
