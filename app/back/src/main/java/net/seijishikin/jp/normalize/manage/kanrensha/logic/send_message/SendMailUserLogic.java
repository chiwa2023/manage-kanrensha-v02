package net.seijishikin.jp.normalize.manage.kanrensha.logic.send_message;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.MailDataDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.SendMaileResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.WriteLogService;

/**
 * メール送信Logic(Mock) SNSのDMと併用し、「タスクが発生したのでログインしてね」くらいなので簡易でOKと判断
 * (URLを置いとけばメールリーダでリンクにしてくれるでしょう…)
 */
@Component
public class SendMailUserLogic {

    /** spring bootのメールSender */
    @Autowired
    private MailSender mailSender;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** ログ書き出しService */
    @Autowired
    private WriteLogService writeLogService;

    /**
     * メール送信を行います。
     *
     * @param listMailData メール送信格納Dto
     * @param taskPlanCode タスク計画コード
     * @return メール送信結果Dto
     */
    public SendMaileResultDto practice(final List<MailDataDto> listMailData, final Integer taskPlanCode) {

        // 複数人送信できるようを配列で一発で渡すこともできるが、○○さんには送れたが
        // ××さんに送れなかったという制御ができないのであえてループで回す
        // メールが送信されなかったことが原因で全作業の巻き戻しはしない(のでtry-catchがloop内にいる)
        SendMaileResultDto resultDto = new SendMaileResultDto();

        int listSize = listMailData.size();
        LocalDate now = LocalDate.now();
        for (int index = 0; index < listSize; index++) {

            try {
                MailDataDto dataDto = listMailData.get(index);
                SimpleMailMessage mailMessage = dataDto.getSimpleMailMessage();
                if (mailMessage != null) {
                    // 送信元(From)のサニタイズ（制御文字やスペースを除去）
                    String from = mailMessage.getFrom();
                    if (from != null) {
                        mailMessage.setFrom(this.cleanFrom(from));
                    }
                    // 送信先(To)のサニタイズ
                    String[] to = mailMessage.getTo(); // NOPMD
                    if (to != null) {
                        mailMessage.setTo(this.cleanTo(to));
                    }

                    // CCのサニタイズ
                    String[] cc = mailMessage.getCc(); // NOPMD
                    if (cc != null) {
                        mailMessage.setCc(this.cleanCc(cc));
                    }

                    // BCCのサニタイズ
                    String[] bcc = mailMessage.getBcc();
                    if (bcc != null) {
                        mailMessage.setBcc(this.cleanBcc(bcc));
                    }
                }

                mailSender.send(mailMessage);
                resultDto.getListSuccess().add(dataDto);

            } catch (MailAuthenticationException mailAuthenticationException) {
                // MailAuthenticationException - 認証に失敗した場合

                saveStackTraceService.practice(mailAuthenticationException, now.getYear(), taskPlanCode);
                writeLogService.practiceError("mail送信時に認証できませんでした", mailAuthenticationException);

                // あるユーザまで処理してきて、突然認証できなくなった・・・ということはほぼないと思われるので
                // 大概一人目。全失敗で即中断して処理を抜ける(認証できないののに何回もtryしない)
                resultDto.setIsFailure(true);

                for (int i = index; i < listSize; i++) {
                    resultDto.getListFailure().add(listMailData.get(i));
                }
                return resultDto;

            } catch (MailParseException mailParseException) {
                // MailParseException - メッセージの解析中にエラーが発生した場合

                saveStackTraceService.practice(mailParseException, now.getYear(), 0);
                writeLogService.practiceError("メッセージの解析中に例外が発生しました", mailParseException);

                resultDto.getListFailure().add(listMailData.get(index));
                resultDto.setIsFailure(true);

            } catch (MailSendException mailSendException) { // NOPMD
                // MailSendException - メッセージ送信時に失敗した場合

                saveStackTraceService.practice(mailSendException, now.getYear(), 0);
                writeLogService.practiceError("mail送信時に例外が発生しました", mailSendException);

                resultDto.getListFailure().add(listMailData.get(index));
                resultDto.setIsFailure(true);

            } catch (MailException mailException) {

                saveStackTraceService.practice(mailException, now.getYear(), 0);
                writeLogService.practiceError("mail送信処理中に例外が発生しました", mailException);

                resultDto.getListFailure().add(listMailData.get(index));
                resultDto.setIsFailure(true);

            } catch (Exception exception) { // NOPMD 業務上の理由で積極的許容

                saveStackTraceService.practice(exception, now.getYear(), 0);
                writeLogService.practiceError("メッセージの解析中に例外が発生しました", exception);

                resultDto.getListFailure().add(listMailData.get(index));
                resultDto.setIsFailure(true);
            }

        }

        return resultDto;
    }

    private String cleanFrom(final String from) {
        return from.replaceAll("[\\s\\p{Cntrl}]", ""); // NOPMD DuplicateLiteral

    }

    private String[] cleanTo(final String[] to) { // NOPMD
        String[] cleanTo = new String[to.length];
        for (int i = 0; i < to.length; i++) {
            if (to[i] != null) {
                cleanTo[i] = to[i].replaceAll("[\\s\\p{Cntrl}]", "");
            }
        }
        return cleanTo;
    }

    private String[] cleanCc(final String[] cc) { // NOPMD
        String[] cleanCc = new String[cc.length];
        for (int i = 0; i < cc.length; i++) {
            if (cc[i] != null) {
                cleanCc[i] = cc[i].replaceAll("[\\s\\p{Cntrl}]", "");
            }
        }
        return cleanCc;
    }

    private String[] cleanBcc(final String[] bcc) { // NOPMD
        String[] cleanBcc = new String[bcc.length];
        for (int i = 0; i < bcc.length; i++) {
            if (bcc[i] != null) {
                cleanBcc[i] = bcc[i].replaceAll("[\\s\\p{Cntrl}]", "");
            }
        }
        return cleanBcc;
    }

}
