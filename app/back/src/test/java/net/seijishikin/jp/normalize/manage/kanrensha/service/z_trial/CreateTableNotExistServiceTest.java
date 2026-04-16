package net.seijishikin.jp.normalize.manage.kanrensha.service.z_trial;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * CreateTableNotExistService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CreateTableNotExistServiceTest {

    /** テスト対象 */
    @Autowired
    private CreateTableNotExistService createTableNotExistService;
    
    @Test
    void test()throws Exception {
        
        // とりあえず何事もなく動いている
        assertDoesNotThrow(() -> createTableNotExistService.practice());
    }

}
