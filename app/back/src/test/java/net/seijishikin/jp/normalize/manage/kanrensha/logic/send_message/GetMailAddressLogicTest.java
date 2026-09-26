package net.seijishikin.jp.normalize.manage.kanrensha.logic.send_message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * GetMailAddressLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetMailAddressLogicTest.sql")
class GetMailAddressLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetMailAddressLogic getMailAddressLogic;

    @Test
    @Tag("ExternalService")
    void test() throws Exception {

        // 存在しないユーザコードの時はnullを返す
        assertNull(getMailAddressLogic.practice(1024));

        assertEquals("aaa@politician.balanse.report.net", getMailAddressLogic.practice(190));
    }

}
