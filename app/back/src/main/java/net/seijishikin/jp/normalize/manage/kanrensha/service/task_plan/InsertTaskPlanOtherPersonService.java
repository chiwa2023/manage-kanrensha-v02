package net.seijishikin.jp.normalize.manage.kanrensha.service.task_plan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.MailDataDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.SendMaileResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.send_message.SendMailUserLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearInsertTaskPlanService;

/**
 * 他人のタスク計画挿入Service
 */
@Service
public class InsertTaskPlanOtherPersonService {

    // TODO 正しいアドレスに修正する
    /** 送信メールアドレス */
    private static final String sendEmail = "test@example.com";

    /** 送信メールアドレス */
    @Autowired
    private SendMailUserLogic sendMailUserLogic;

    /** 年切替タスク計画挿入Service */
    @Autowired
    private SwitchYearInsertTaskPlanService switchYearInsertTaskPlanInsertService;

    /**
     * 処理を行う
     * 
     * @param email          メールアドレス
     * @param userDto        操作ユーザ最小限Dto
     * @param createDatetime 作成時間
     * @param taskInfoCode   タスク情報コード
     * @param mapParam       接続QueryParameterMap
     * @return 処理結果
     */
    public FrameworkMessageAndResultDto practice(final String email, final LeastUserDto userDtoTask,
            final LeastUserDto userDto, final LocalDateTime createDatetime, final Integer taskInfoCode,
            final Map<String, String> mapParam) {

        // タスク計画挿入
        InsertTaskPlanResultDto insertTaskPlanResultDto;
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();

        try {
            insertTaskPlanResultDto = switchYearInsertTaskPlanInsertService.practice(userDtoTask, userDto,
                    createDatetime, taskInfoCode, mapParam);
        } catch (EmptyResultDataAccessException exception) {
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return resultDto;
        } catch (IllegalArgumentException exception) { // NOPMD 業務的な理由から積極的に許容
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return resultDto;
        }

        if (insertTaskPlanResultDto.getIsFailure()) {
            resultDto.setIsFailure(true);
            resultDto.setMessage(insertTaskPlanResultDto.getMessage());
            return resultDto;
        }

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sendEmail); // 送信元メールアドレス
        mailMessage.setTo(email);
        // mailMessage.setCc(""); // cc不要
        // mailMessage.setBcc(""); // bcc不要
        mailMessage.setSubject(insertTaskPlanResultDto.getTaskPlanName() + "：政治資金関連者標準化サイト");
        mailMessage.setReplyTo("このアドレスに返信はできません");

        String body = insertTaskPlanResultDto.getMessageTemplate();
        body = body.replaceAll("【transferPass】", insertTaskPlanResultDto.getTransferPass());
        mailMessage.setText(body);

        MailDataDto mailDataDto = new MailDataDto();
        mailDataDto.setSimpleMailMessage(mailMessage);
        mailDataDto.setIsRepeat(false);

        List<MailDataDto> listMailData = new ArrayList<>();
        listMailData.add(mailDataDto);

        SendMaileResultDto sendMaileResultDto;
        try {
            sendMaileResultDto = sendMailUserLogic.practice(listMailData);
            if (sendMaileResultDto.getIsFailure()) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("メールが送信できませんでした");
            }
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            resultDto.setIsFailure(true);
            resultDto.setMessage("処理は実行できましたが、メールが送信できませんでした");
        }

        return resultDto;
    }
}
