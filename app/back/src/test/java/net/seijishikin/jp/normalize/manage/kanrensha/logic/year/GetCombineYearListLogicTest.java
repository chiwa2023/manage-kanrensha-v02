package net.seijishikin.jp.normalize.manage.kanrensha.logic.year;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * GetCombineYearListLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class GetCombineYearListLogicTest {

    /** テスト対象 */
    @Autowired
    private GetCombineYearListLogic getCombineYearListLogic;

    @Test
    void test() throws Exception {

        List<Short> list = getCombineYearListLogic.practice();

        // TODO 年更新処理するごとに変化する
        assertEquals(Short.valueOf("2020"), list.getFirst());
        assertEquals(Short.valueOf("2025"), list.getLast());
        

        fail("Not yet implemented");
    }

}
