package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.MailDataDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.SendMaileResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.ResetPassswordCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.LoginStatusEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPasswordResetEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.send_message.SendMailUserLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.LoginStatusRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPasswordResetRepository;

/**
 * パスワードリセットコードを指定アドレスに送信Service
 */
@Service
public class ResetPasswordMailInputService {

    // TODO 正しいアドレスに修正する
    /** 新規登録中ユーザRepository */
    private static final String sendEmail = "test@example.com";

    /** mail送信Logic */
    @Autowired
    private SendMailUserLogic sendMailUserLogic;

    /** パスワードリセットRepository */
    @Autowired
    private UserPasswordResetRepository userPasswordResetRepository;

    /** ログイン状態Repository */
    @Autowired
    private LoginStatusRepository loginStatusRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto パスワードリセット処理条件Dto
     * @return 処理結果
     * @throws IOException メール例文読み込み不可例外
     */
    public FrameworkMessageAndResultDto practice(final ResetPassswordCapsuleDto capsuleDto) throws IOException {

        String email = capsuleDto.getEmail();
        
        Optional<LoginStatusEntity> optional = loginStatusRepository.findById(email);

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        if (optional.isEmpty()) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("ご指定のアドレスでユーザ登録が見つかりませんでした");
            return resultDto;
        }

        // 現在テーブル内にあるなしかかわらず主キーで削除
        userPasswordResetRepository.deleteById(email);

        // エンティティにセットして登録
        UserPasswordResetEntity restEntity = new UserPasswordResetEntity();
        restEntity.setEmail(email);
        String regiCode = UUID.randomUUID().toString();
        restEntity.setRegistCode(regiCode);
        LocalDateTime limitTime = LocalDateTime.now().plusHours(2L);
        restEntity.setLimitDatetime(limitTime);

        // 返却Dtoに複写
        List<MailDataDto> list = new ArrayList<>();
        list.add(this.createMailData(email, regiCode, limitTime));

        SendMaileResultDto mailtDto = sendMailUserLogic.practice(list);

        if (mailtDto.getIsFailure()) {
            // コード登録ができなかった場は
            resultDto.setIsFailure(true);
            resultDto.setMessage("リセット用認証コードが登録できませんでした");
            return resultDto;
        } else {
            resultDto.setMessage("指定メールアドレスに認証コードを送信しました。【今のページ】の認証コード入力に送信されたコードを貼り付けしてください");
            return resultDto;
        }
    }

    private MailDataDto createMailData(final String email, final String regiCode, final LocalDateTime limitTime)
            throws IOException {

        // この業務ロジックではmail送信可否をDB管理しないので初期値のまま
        // mailDataDto.setIsRepeat(false);
        // mailDataDto.setSendAlertMailId(0);
        // mailDataDto.setSendAlertMailCode(0);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sendEmail); // 送信元メールアドレス
        mailMessage.setTo(email);
        // mailMessage.setCc(""); // cc不要
        // mailMessage.setBcc(""); // bcc不要
        mailMessage.setSubject("パスワードリセット認証コード送信：政治資金関連者標準化サイト");
        mailMessage.setReplyTo("このアドレスに返信はできません");

        String pathString = GetCurrentResourcePath
                .getBackSrcPath("/main/resources/templates/email/send_password_reset_code.txt");

        String body = Files.readString(Paths.get(pathString));
        body = body.replaceAll("【email】", email);
        body = body.replaceAll("【regiCode】", regiCode);
        body = body.replaceAll("【limitTime】", limitTime.format(DateTimeFormatter.ISO_DATE_TIME));
        // body = body.replaceAll("【verifyToken】", verifyToken);
        mailMessage.setText(body);

        MailDataDto mailDataDto = new MailDataDto();
        mailDataDto.setSimpleMailMessage(mailMessage);

        return mailDataDto;
    }
}
