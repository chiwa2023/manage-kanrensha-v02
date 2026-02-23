package net.seijishikin.jp.normalize.manage.kanrensha.logic.address.registory;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * GetChibanCsvLogic単体テスト(外部実行用)
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetChibanCsvLogicTest.sql")
class GetChibanCsvLogicTest {

    /** テスト対象 */
    @Autowired
    private GetChibanCsvLogic getChibanCsvLogic;

    @Test
    @Tag("TableTruncate")
    void testLimit() throws Exception {

        String storeDir = "c:/temp/address/mt_parcel_city";
        final String lgCodePref = "13";
        getChibanCsvLogic.practice(lgCodePref, storeDir);

        fail("Not yet implemented");
    }

}
