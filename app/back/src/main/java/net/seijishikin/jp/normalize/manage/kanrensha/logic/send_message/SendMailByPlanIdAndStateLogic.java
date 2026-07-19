package net.seijishikin.jp.normalize.manage.kanrensha.logic.send_message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.MailDataDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.SendMaileResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskInfoEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;

/**
 * タスク計画と送信状態でメールを送信する
 */
@Component
public class SendMailByPlanIdAndStateLogic {

    /** 送信メールアドレス */
    @Value("${app.send.mail.address:test@example.com}")
    private String sendEmail;

    /** 送信状態開始 */
    public static final int STATE_START = 1;
    /** 送信状態終了 */
    public static final int STATE_END = 2;
    /** 送信状態中断 */
    public static final int STATE_SUSPEND = 3;

    /** ユーザ個人Repository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /** メール送信Logic */
    @Autowired
    private SendMailUserLogic sendMailUserLogic;

    /**
     * 処理を行う
     * 
     * @param userDto    ユーザ最低限Dto
     * @param year       発生年
     * @param taskPlanId タスク計画Id
     * @param state      発信状態
     */
    public void practice(final LeastUserDto userDto, final int year, final int taskPlanId, final Integer state) {

        // ユーザの通知希望を確認して、通知を希望しない場合は離脱する
        Optional<UserPersonEntity> optional = userPersonRepository.findById(userDto.getUserPersonId());
        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("idでユーザが取得できませんでした", 1);
        }

        // 通知を送らない場合はそのまま終了
        UserPersonEntity userPersonEntity = optional.get();
        if (STATE_START == state && !userPersonEntity.getIsAlertTaskStart()) {
            return;
        }
        if ((STATE_END == state || STATE_SUSPEND == state) && !userPersonEntity.getIsAlertTaskEnd()) {
            return;
        }

        // 該当年のタスク計画Idからタスク情報を取得する
        String sql = "SELECT * FROM task_info WHERE is_latest = 1 "
                + "AND task_info_code IN ( SELECT task_info_code FROM task_plan_" + year //
                + " WHERE task_plan_id = " + taskPlanId + ") ORDER BY insert_timestamp DESC LIMIT 1 ";
        Query query = entityManager.createNativeQuery(sql, TaskInfoEntity.class);

        // 取得できないときは
        TaskInfoEntity taskInfoEntity = (TaskInfoEntity) query.getSingleResult(); // NOPMD LawDemeter

        // タスク情報に基づいてメール送信
        this.sendAlert(taskInfoEntity, userPersonEntity.getEmail(), state);
    }

    private SendMaileResultDto sendAlert(final TaskInfoEntity infoEntity, final String mailAddress,
            final Integer state) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sendEmail); // 送信元メールアドレス
        mailMessage.setTo(mailAddress);
        // mailMessage.setCc(""); // cc不要
        // mailMessage.setBcc(""); // bcc不要
        mailMessage.setSubject(infoEntity.getTaskInfoName() + "：政治資金関連者標準化サイト");
        mailMessage.setReplyTo("このアドレスに返信はできません");

        String body = this.getBody(infoEntity, state);
        body = body.replaceAll("【paramQuery】", infoEntity.getParamQuery());
        body = body.replaceAll("【transferPass】", infoEntity.getTransferPass());
        mailMessage.setText(body);

        MailDataDto mailDataDto = new MailDataDto();
        mailDataDto.setSimpleMailMessage(mailMessage);
        mailDataDto.setIsRepeat(false);

        List<MailDataDto> listMail = new ArrayList<>();
        listMail.add(mailDataDto);

        return sendMailUserLogic.practice(listMail);
    }

    private String getBody(final TaskInfoEntity infoEntity, final Integer state) {

        switch (state) {
            case STATE_START:
                return infoEntity.getMessageStart();

            case STATE_END:
                return infoEntity.getMessageFinish();

            case STATE_SUSPEND:
                return infoEntity.getMessageSuspend();
            default:
                throw new IllegalArgumentException("特殊なBatchStatusが送信されたまたは通知送信状態に誤りがあります");
        }
    }

}
