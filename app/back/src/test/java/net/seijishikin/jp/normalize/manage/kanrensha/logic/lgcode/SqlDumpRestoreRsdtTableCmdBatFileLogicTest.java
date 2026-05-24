package net.seijishikin.jp.normalize.manage.kanrensha.logic.lgcode;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * SqlDumpRestoreRsdtTableCmdBatFileLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SqlDumpRestoreRsdtTableCmdBatFileLogicTest {

    /** テスト対象 */
    @Autowired
    private SqlDumpRestoreRsdtTableCmdBatFileLogic sqlDumpRestoreRsdtTableCmdBatFileLogic;
    
    @Test
    @Tag("TableTruncate")
    void test() throws Exception {
        
        final String lgCodePref = "47";
        final String dir = "c:/temp";
        
        assertDoesNotThrow(() -> sqlDumpRestoreRsdtTableCmdBatFileLogic.practice(lgCodePref, dir));
    }
}
