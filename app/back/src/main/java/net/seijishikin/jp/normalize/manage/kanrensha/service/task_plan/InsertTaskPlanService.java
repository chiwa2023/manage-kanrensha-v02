package net.seijishikin.jp.normalize.manage.kanrensha.service.task_plan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.MailDataDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.SendMaileResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.send_message.SendMailUserLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearInsertTaskPlanService;

/**
 * タスク計画挿入Service
 */
@Service
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
public class InsertTaskPlanService {

    // TODO 正しいアドレスに修正する
    /** 送信メールアドレス */
    private static final String sendEmail = "test@example.com";

    /** propertiesからインジェクションされた通知送信フラグ */
    private Boolean flgSendAlert;

    /**
     * 通知送信フラグを取得する
     * 
     * @return 通知送信フラグ
     */
    public Boolean getFlgSendAlert() {
        return flgSendAlert;
    }

    /**
     * 通知送信フラグを設定す津
     * 
     * @param flgSendAlert 通知送信フラグ
     */
    public void setFlgSendAlert(final Boolean flgSendAlert) {
        this.flgSendAlert = flgSendAlert;
    }

    /** ユーザ個人Repository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /** 年切替タスク計画挿入Service */
    @Autowired
    private SwitchYearInsertTaskPlanService switchYearInsertTaskPlanInsertService;

    /** メール送信Logic */
    @Autowired
    private SendMailUserLogic sendMailUserLogic;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** 空文字 */
    private static final String BLANK = "";

    /**
     * 処理を行う
     * 
     * @param userDto        ユーザ最小限Dto
     * @param createDatetime タスク作成時間
     * @param taskInfoCode   タスク情報コード
     * @return 処理結果Dto
     */
    public InsertTaskPlanResultDto practice(final LeastUserDto userDto, final LocalDateTime createDatetime,
            final Integer taskInfoCode, final Map<String, String> mapParam) {

        // タスク計画が挿入できなかったとしても本筋の処理ができていれば、処理としてまぁOKなので処理継続
        InsertTaskPlanResultDto resultDto = new InsertTaskPlanResultDto();

        Integer savedId;
        try {
            resultDto = switchYearInsertTaskPlanInsertService.practice(userDto, createDatetime, taskInfoCode, mapParam);

            savedId = resultDto.getTaskPlanId();
            if (0 == savedId) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("タスク計画が登録できませんでした");
                return resultDto;
            }
        } catch (EmptyResultDataAccessException exception) {
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return resultDto;

        } catch (IllegalArgumentException exception) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("タスク計画が登録できませんでした");
            return resultDto;
        }

        // 作業者にタスクを通知
        if (flgSendAlert) {

            // 送信先メールアドレス取得
            Optional<UserPersonEntity> optionalPerson = userPersonRepository.findById(userDto.getUserPersonId());
            if (optionalPerson.isEmpty()) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("推薦者のユーザ情報がが取得できませんでした");
                return resultDto;
            }
            UserPersonEntity userPersonEntity = optionalPerson.get();

            SendMaileResultDto mailResultDto = this.sendAlert(resultDto, userPersonEntity.getEmail(), savedId);

            if (Objects.isNull(mailResultDto)) {
                // 予測されれないエラーの場合はシステムエラー
                resultDto.setIsFailure(true);
                resultDto.setMessage("推薦者へメール送信時に例外が発生しています");
                return resultDto;
            }

            if (mailResultDto.getIsFailure()) {
                // 予測されたメール不達の場合はメール未送信メッセージを送る
                resultDto.setIsFailure(true);
                resultDto.setMessage(this.getFailureMessage(mailResultDto));

                return resultDto;
            }
        }

        return resultDto;
    }

    private SendMaileResultDto sendAlert(final InsertTaskPlanResultDto resultDto, final String mailAddress,
            final Integer savedId) {
        try {

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sendEmail); // 送信元メールアドレス
            mailMessage.setTo(mailAddress);
            // mailMessage.setCc(""); // cc不要
            // mailMessage.setBcc(""); // bcc不要
            mailMessage.setSubject(resultDto.getTaskPlanName() + "：政治資金関連者標準化サイト");
            mailMessage.setReplyTo("このアドレスに返信はできません");

            String body = resultDto.getMessageTemplate();
            body = body.replaceAll("【paramQuery】", resultDto.getParamQuery());
            body = body.replaceAll("【transferPass】", resultDto.getTransferPass());
            mailMessage.setText(body);

            MailDataDto mailDataDto = new MailDataDto();
            mailDataDto.setSimpleMailMessage(mailMessage);
            mailDataDto.setIsRepeat(false);

            List<MailDataDto> listMail = new ArrayList<>();
            listMail.add(mailDataDto);

            return sendMailUserLogic.practice(listMail);

        } catch (Exception exception) { // NOPMD 業務上の理由で積極的許容
            // タスク挿入中のメール送信過程でのStackTraceを保存
            saveStackTraceService.practice(exception, LocalDateTime.now().getYear(), savedId);
            return null;
        }
    }

    private String getFailureMessage(final SendMaileResultDto mailResultDto) {

        if (BLANK.equals(mailResultDto.getMessage())) {
            return "推薦者へメール送信時に例外が発生しています";
        } else {
            return mailResultDto.getMessage();
        }

    }

}
