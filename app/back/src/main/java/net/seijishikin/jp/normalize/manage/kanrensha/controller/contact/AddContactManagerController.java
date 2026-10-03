package net.seijishikin.jp.normalize.manage.kanrensha.controller.contact;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.contact.AddContactMessageCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.MailDataDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.ContactManagerEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskInfoEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.send_message.GetMailAddressLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.send_message.SendMailUserLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ValidateAuthoraizeUserDetailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.TaskInfoRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.contact.AddContactManagerService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 運営者問い合わせメッセージ追加Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/contact-manager")
public class AddContactManagerController {

    /** 運営者問い合わせメッセージ追加Service */
    @Autowired
    private AddContactManagerService addContactManagerService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** ユーザ妥当性検証Logic */
    @Autowired
    private ValidateAuthoraizeUserDetailLogic validateAuthoraizeUserDetailLogic;

    /** タスク情報 */
    @Autowired
    private TaskInfoRepository taskInfoRepository;

    /** メール送信Logic */
    @Autowired
    private SendMailUserLogic sendMailUserLogic;

    /** メールアドレス取得Logic */
    @Autowired
    private GetMailAddressLogic getMailAddressLogic;

    /** 送信メールアドレス */
    @Value("${app.send.mail.address:test@example.com}")
    private String sendEmail;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     * @return 処理結果Dto
     */
    @PostMapping("/add")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final AddContactMessageCapsuleDto capsuleDto) {
        // 更新処理に対して処理結果を返す
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            // ユーザチェック
            validateAuthoraizeUserDetailLogic.practice(capsuleDto.getUserDto());

            ContactManagerEntity entity = addContactManagerService.practice(capsuleDto);

            if (0 == entity.getContactManagerId()) {
                resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_NO_RECORD);
                resultDto.setIsFailure(true);

            } else {

                // 回答があれば問い合わせ者本人にメールを送信する
                String email = getMailAddressLogic.practice(entity.getInquireUserCode());
                List<TaskInfoEntity> listTask = taskInfoRepository
                        .findByTaskInfoCodeAndIsLatestTrue(TaskInfoConstants.CONTACT_ADD);
                if (!Objects.isNull(email) && !listTask.isEmpty()) {
                    List<MailDataDto> list = new ArrayList<>();
                    list.add(this.createMailData(email, listTask.get(0)));
                    sendMailUserLogic.practice(list);
                }

                // タスク計画は管理できない。
                // 運営者一般タスクを作成しており、問い合わせした方がクローズする場合、
                // そのタスクにアクセスする権限がないためtaskIdが渡ってくることはない
                // 本気で管理するには発生イベントに対するタスク紐づけのようなテーブルが必要
                // 両者にタスクを持たせた場合、どちらかのタスク計画は残る可能性が常にある

                resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_EXPECTED);
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }
        } catch (UsernameNotFoundException exception) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("tokenとユーザ(userDto)が不整合です");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
            resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_INTERNAL_ERROR);
            resultDto.setIsFailure(true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
    }

    private MailDataDto createMailData(final String email, final TaskInfoEntity taskInfoEntity) throws IOException {

        // この業務ロジックではmail送信可否をDB管理しないので初期値のまま
        // mailDataDto.setIsRepeat(false);
        // mailDataDto.setSendAlertMailId(0);
        // mailDataDto.setSendAlertMailCode(0);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sendEmail); // 送信元メールアドレス
        mailMessage.setTo(email);
        // mailMessage.setCc(""); // cc不要
        // 問い合わせ反応を早くするために仮に代表アドレスを入れておく
        // TODO 将来的には運営者のランダムの5名に送信のようにタスク分散したい
        mailMessage.setBcc("info@normalize-jp-seijishikin.net");
        mailMessage.setSubject(taskInfoEntity.getTaskInfoName() + "：政治資金関連者標準化サイト");
        // mailMessage.setReplyTo("このアドレスに返信はできません");

        mailMessage.setText(taskInfoEntity.getMessageStart());

        MailDataDto mailDataDto = new MailDataDto();
        mailDataDto.setSimpleMailMessage(mailMessage);

        return mailDataDto;
    }

}
