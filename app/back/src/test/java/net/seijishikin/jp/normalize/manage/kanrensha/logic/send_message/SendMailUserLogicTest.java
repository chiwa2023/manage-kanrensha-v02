package net.seijishikin.jp.normalize.manage.kanrensha.logic.send_message;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.MailDataDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.SendMaileResultDto;

/**
 * SendMailUserLogic単体テスト
 * TODO 認証等例外テストができるようになったら追加する
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SendMailUserLogicTest {

    /** テスト対象 */
    @Autowired
    private SendMailUserLogic sendMailUserLogic;

    @Test
    @Tag("ExternalService")
    void test() throws Exception {

        // 正常終了

        List<MailDataDto> list = new ArrayList<>();
        list.add(this.createData(0));

        SendMaileResultDto resultDto01 = sendMailUserLogic.practice(list);
        assertFalse(resultDto01.getIsFailure(), "正常終了");
    }
    
    @Test
    @Tag("ExternalService")
    void testAuthentication() throws Exception {
        
        //assertThrows(MailAuthenticationException.class, ()-> sendMailUserLogic.practice(list));
        fail("Not yet implemented");
    }

    @Test
    @Tag("ExternalService")
    void testMailParseException() throws Exception {
        
        //assertThrows(MailAuthenticationException.class, ()-> sendMailUserLogic.practice(list));
        fail("Not yet implemented");
    }

    
    private MailDataDto createData(final int index) {

        // メールメッセージ
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("testA" + index + "t@test.jp"); // 送信元メールアドレス
        mailMessage.setTo("testB" + index + "@test.jp");
        mailMessage.setCc("testC" + index + "@test.jp");
        mailMessage.setBcc("testD" + index + "@test.jp");
        mailMessage.setSubject("テスト用表題");
        mailMessage.setText("ローカルから送る用テストメッセージ");
        mailMessage.setReplyTo("テスト用表題" + index);

        MailDataDto mailDataDto = new MailDataDto();
        mailDataDto.setSimpleMailMessage(mailMessage);

        return mailDataDto;
    }

}
