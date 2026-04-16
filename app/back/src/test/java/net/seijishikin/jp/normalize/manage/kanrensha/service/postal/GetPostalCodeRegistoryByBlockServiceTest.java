package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
import org.springframework.transaction.annotation.Transactional;

/**
 * GetPostalCodeRegistoryByBlockService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("GetPostalCodeRegistoryByBlockServiceTest.sql")
class GetPostalCodeRegistoryByBlockServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetPostalCodeRegistoryByBlockService getPostalCodeRegistoryByBlockService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 呼び出しできない場合はnull
        final String wrongAddress = "適当住所";
        assertNull(getPostalCodeRegistoryByBlockService.practice("", wrongAddress));

        final String lgCode = "011029";
        assertNull(getPostalCodeRegistoryByBlockService.practice(lgCode, wrongAddress));

        final String addressBlock = "札幌市北区北十条西一丁目1番地1号";

        assertEquals(326, getPostalCodeRegistoryByBlockService.practice(lgCode, addressBlock).getAddressRsdtId());
    }

}
