package net.seijishikin.jp.normalize.manage.kanrensha.logic.send_message;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.SendMaileResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * PromoteUserAdminSendMailLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("PromoteUserAdminSendMailLogicTest.sql")
class PromoteUserAdminSendMailLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private PromoteUserAdminSendMailLogic promoteUserAdminSendMailLogic;

    @Test
    @Tag("ExternalService")
    void test() throws Exception {

        SendMaileResultDto resultDto = promoteUserAdminSendMailLogic.pracitce(CreateLeastUserForTestUtil.practice());
        assertEquals(false, resultDto.getIsFailure());
        // 送信後の内容は目視で確認
    }

    @Test
    @Tag("ExternalService")
    void testWrongUser() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        userDto.setUserPersonId(621);
        
        SendMaileResultDto resultDto = promoteUserAdminSendMailLogic.pracitce(userDto);
        assertEquals(true, resultDto.getIsFailure());
        assertEquals("作業者のメールアドレスが取得できませんでした", resultDto.getMessage());
    }

    @Test
    @Tag("ExternalService")
    void testSendFlgOff() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        userDto.setUserPersonId(621);
        SendMaileResultDto resultDto = promoteUserAdminSendMailLogic.pracitce(userDto);
        // 取得できないユーザを呼び出そうとしても落ちない
        assertEquals(false, resultDto.getIsFailure());
    }

}
