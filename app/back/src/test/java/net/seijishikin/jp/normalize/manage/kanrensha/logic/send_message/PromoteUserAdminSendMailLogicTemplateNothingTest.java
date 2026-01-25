package net.seijishikin.jp.normalize.manage.kanrensha.logic.send_message;

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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.SendMaileResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * PromoteUserAdminSendMailLogic単体テスト(送信失敗)
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("PromoteUserAdminSendMailLogicTest.sql")
class PromoteUserAdminSendMailLogicTemplateNothingTest {

    /** テスト対象 */
    @Autowired
    private PromoteUserAdminSendMailLogic promoteUserAdminSendMailLogic;

    @Test
    @Tag("ExternalService")
    void test() {

        // テンプレートファイル名を変える、メールサーバを切るなどして実装以外でわざと落とすテスト
        SendMaileResultDto resultDto = promoteUserAdminSendMailLogic.pracitce(CreateLeastUserForTestUtil.practice());
        assertEquals(true, resultDto.getIsFailure());
        // メッセージは発生させる原因によって異なる
    }

}
