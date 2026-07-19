package net.seijishikin.jp.normalize.manage.kanrensha.logic.send_message;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.MailDataDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.SendMaileResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;

/**
 * SE権限作業者向けメール送信Logic
 */
@Component
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
public class PromoteUserAdminSendMailLogic {

    /** 送信メールアドレス */
    @Value("${app.send.mail.address:test@example.com}")
    private String sendEmail;

    /** mail送信Logic */
    @Autowired
    private SendMailUserLogic sendMailUserLogic;

    /** ユーザ個人Repository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /**
     * 処理を行う
     * 
     * @param userDto SE権限推薦業者ユーザ最小限Dto
     * @return メール送信結果
     */
    public SendMaileResultDto pracitce(final LeastUserDto userDto) {

        // 送信先メールアドレス取得
        Optional<UserPersonEntity> optionalPerson = userPersonRepository.findById(userDto.getUserPersonId());
        if (optionalPerson.isEmpty()) {
            SendMaileResultDto resultDto = new SendMaileResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage("作業者のメールアドレスが取得できませんでした");
            return resultDto;
        }
        UserPersonEntity userPersonEntity = optionalPerson.get();

        try {
            List<MailDataDto> list = new ArrayList<>();
            list.add(this.createMailData(userPersonEntity.getEmail()));
            return sendMailUserLogic.practice(list);
        } catch (IOException iOException) {
            SendMaileResultDto resultDto = new SendMaileResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage("メール用テンプレートが取得できませんでした");
            return resultDto;
        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容
            SendMaileResultDto resultDto = new SendMaileResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage("その他のシステムエラー");
        }

        return new SendMaileResultDto();
    }

    private MailDataDto createMailData(final String email) throws IOException {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sendEmail); // 送信元メールアドレス
        mailMessage.setTo(email);
        // mailMessage.setCc(""); // cc不要
        // mailMessage.setBcc(""); // bcc不要
        mailMessage.setSubject("SE権限推薦作業完了：政治資金関連者標準化サイト");
        mailMessage.setReplyTo("このアドレスに返信はできません");

        String pathString = GetCurrentResourcePath
                .getBackSrcPath("/main/resources/templates/email/promote_admin_worker.txt");

        String body = Files.readString(Paths.get(pathString));
        mailMessage.setText(body);

        MailDataDto mailDataDto = new MailDataDto();
        mailDataDto.setSimpleMailMessage(mailMessage);

        return mailDataDto;
    }

}
