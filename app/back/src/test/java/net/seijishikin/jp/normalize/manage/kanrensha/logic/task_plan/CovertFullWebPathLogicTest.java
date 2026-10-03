package net.seijishikin.jp.normalize.manage.kanrensha.logic.task_plan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

/**
 * CovertFullWebPathLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
class CovertFullWebPathLogicTest {

    /** テスト対象 */
    @Autowired
    private ConvertFullWebPathLogic convertFullWebPathLogic;

    @Test
    void test() {
        String path = "/aaa/bbb";

        String ans = convertFullWebPathLogic.practice(path);

        assertEquals("http://localhost:5173/api" + path, ans);
    }

}
