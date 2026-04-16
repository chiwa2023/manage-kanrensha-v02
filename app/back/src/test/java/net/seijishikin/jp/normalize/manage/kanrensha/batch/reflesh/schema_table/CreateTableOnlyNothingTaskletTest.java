package net.seijishikin.jp.normalize.manage.kanrensha.batch.reflesh.schema_table;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * CreateTableOnlyNothingTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CreateTableOnlyNothingTaskletTest {

    /** テスト対象 */
    @Autowired
    private CreateTableOnlyNothingTasklet createTableOnlyNothingTasklet;
    
    @Test
    void test()throws Exception {

        // とりあえず問題なく起動
        // TODO 必要に応じてTest
        assertDoesNotThrow(() -> createTableOnlyNothingTasklet.execute(null, null));
        
        fail("Not yet implemented");
    }

}
