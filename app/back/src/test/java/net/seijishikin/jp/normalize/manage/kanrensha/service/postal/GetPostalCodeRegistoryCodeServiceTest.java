package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
 * GetPostalCodeRegistoryCodeService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("GetPostalCodeRegistoryCodeServiceTest.sql")
class GetPostalCodeRegistoryCodeServiceTest {

    /** テスト対象 */
    @Autowired
    private GetPostalCodeRegistoryCodeService getPostalCodeRegistoryCodeService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 呼び出しできない場合はnull
        final Integer idNotExist = 0;
        assertNull(getPostalCodeRegistoryCodeService.practice("", idNotExist));

        final String lgCode = "011029";
        assertNull(getPostalCodeRegistoryCodeService.practice(lgCode, idNotExist));

        final Integer idTest = 324; // 選択されたidの値を返すだけなので、is_latestt他一切の条件を見ない

        assertEquals(idTest, getPostalCodeRegistoryCodeService.practice(lgCode, idTest).getAddressRsdtId());
    }

}
