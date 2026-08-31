package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.MailDataDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.SendMaileResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.NewComerDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserNewEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.send_message.SendMailUserLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserNewRepository;

/**
 * 新規ユーザ用仮コード発行Service
 */
@Service
public class PublishNewUserCodeService {

    /** 送信メールアドレス */
    @Value("${app.send.mail.address:test@example.com}")
    private String sendEmail;

    /** 新規登録中ユーザRepository */
    @Autowired
    private UserNewRepository userNewRepository;

    /** mail送信Logic */
    @Autowired
    private SendMailUserLogic sendMailUserLogic;

    /** ResourceLoader */
    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * 処理を行う
     *
     * @param newComerDto 新規ユーザDto
     * @return コード入力したDto
     */
    public NewComerDto practice(final NewComerDto newComerDto) throws IOException {

        // 新規・再発行にかかわらず一回主キー = emailで削除
        String email = newComerDto.getMailAddress();
        userNewRepository.deleteById(email);

        // エンティティにセットして登録
        UserNewEntity userNewEntity = new UserNewEntity();
        userNewEntity.setEmail(email);
        String regiCode = UUID.randomUUID().toString();
        userNewEntity.setRegistCode(regiCode);
        LocalDateTime limitTime = newComerDto.getLimitDateTime();
        userNewEntity.setLimitDatetime(limitTime);
        // URLダイレクトアクセス用認証トークン
        String verifyToken = UUID.randomUUID().toString();
        userNewEntity.setVerifyToken(verifyToken);
        userNewEntity.setVerifyLimitDateTime(limitTime);
        userNewRepository.save(userNewEntity);

        // 返却Dtoに複写
        List<MailDataDto> list = new ArrayList<>();
        list.add(this.createMailData(email, regiCode, limitTime, verifyToken));

        SendMaileResultDto resultDto = sendMailUserLogic.practice(list);

        if (resultDto.getIsFailure()) {
            // コード登録ができなかった場合はnull
            return null;
        } else {
            NewComerDto responseDto = new NewComerDto();
            responseDto.setMailAddress(email);
            responseDto.setRegistCode(regiCode);
            responseDto.setLimitDateTime(limitTime);
            responseDto.setIsFailure(false);
            return responseDto;
        }
    }

    private MailDataDto createMailData(final String email, final String regiCode, final LocalDateTime limitTime,
            final String verifyToken) throws IOException {

        // この業務ロジックではmail送信可否をDB管理しないので初期値のまま
        // mailDataDto.setIsRepeat(false);
        // mailDataDto.setSendAlertMailId(0);
        // mailDataDto.setSendAlertMailCode(0);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sendEmail); // 送信元メールアドレス
        mailMessage.setTo(email);
        // mailMessage.setCc(""); // cc不要
        // mailMessage.setBcc(""); // bcc不要
        mailMessage.setSubject("メール疎通認証コード送信：政治資金関連者標準化サイト");
        // mailMessage.setReplyTo("このアドレスに返信はできません");

        // String pathString =
        // GetCurrentResourcePath.getBackSrcPath("/main/resources/templates/email/send_regi_code.txt");
        // String body = Files.readString(Paths.get(pathString));

        Resource resource = resourceLoader.getResource("classpath:templates/email/send_regi_code.txt");

        String body;
        try (InputStream inputStream = resource.getInputStream()) {
            body = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }

        body = body.replaceAll("【email】", email);
        body = body.replaceAll("【regiCode】", regiCode);
        body = body.replaceAll("【limitTime】", limitTime.format(DateTimeFormatter.ISO_DATE_TIME));
        body = body.replaceAll("【verifyToken】", verifyToken);
        mailMessage.setText(body);

        MailDataDto mailDataDto = new MailDataDto();
        mailDataDto.setSimpleMailMessage(mailMessage);

        return mailDataDto;
    }

}
