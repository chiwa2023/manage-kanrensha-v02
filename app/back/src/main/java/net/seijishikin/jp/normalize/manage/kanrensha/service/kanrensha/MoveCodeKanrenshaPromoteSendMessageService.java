package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodePromoteCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.MailDataDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.SendMaileResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.send_message.SendMailUserLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;

/**
 * 関連者移動申請メール送信Service
 */
@Service
public class MoveCodeKanrenshaPromoteSendMessageService {

    /** 送信メールアドレス */
    @Value("${app.send.mail.address:test@example.com}")
    private String sendEmail;

    /** ユーザ権限Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

    /** メール送信Logic */
    @Autowired
    private SendMailUserLogic sendMailUserLogic;

    /**
     * 処理を行う
     * 
     * @param capsuleDto    コード移行申請Dto
     * @param planResultDto 登録タスク計画内容Dto
     */
    public SendMaileResultDto practice(final MoveKanrenshaCodePromoteCapsuleDto capsuleDto,
            final InsertTaskPlanResultDto planResultDto) {
        try {
            List<MailDataDto> listMailData = new ArrayList<>();

            String kbnName = KanrenshaKbnConstants.getUserRole(capsuleDto.getKanrenshaKbn());

            // 保存コード所有者メールメッセージを作成する(見かけ上リスト実装だが、コードの一意性から実際には最大1件)
            List<MailDataDto> listMailOrg = this.createMessageList(kbnName, planResultDto,
                    userRoleRepository.findByKanrenshaCodeAndIsLatestTrue(capsuleDto.getOriginKanrenshaCode()));
            if (!listMailOrg.isEmpty()) {
                listMailData.addAll(listMailOrg);
            }

            // 廃止コード所有者メールメッセージを作成する(見かけ上リスト実装だが、コードの一意性から実際には最大1件)
            List<MailDataDto> listMailAbolish = this.createMessageList(kbnName, planResultDto,
                    userRoleRepository.findByKanrenshaCodeAndIsLatestTrue(capsuleDto.getAbolishKanrenshaCode()));
            if (!listMailAbolish.isEmpty()) {
                listMailData.addAll(listMailAbolish);
            }

            if (listMailData.isEmpty()) {
                return new SendMaileResultDto();
            } else {
                return sendMailUserLogic.practice(listMailData, planResultDto.getTaskPlanCode());
            }

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            // 何かの事情でメールが送れなくても本体作業そのものは成功しているので握りつぶし
            return new SendMaileResultDto();
        }
    }

    private List<MailDataDto> createMessageList(final String kbnName, final InsertTaskPlanResultDto planResultDto,
            final List<UserRoleEntity> listRole) {

        List<MailDataDto> list = new ArrayList<>();

        for (UserRoleEntity enity : listRole) {
            // コードと権限が一致した場合のみ処理(現実問題としてはコード一致だけでも十分)
            if (kbnName.equals(enity.getRole())) {
                list.add(this.createMailData(enity.getEmail(), planResultDto));
            }
        }

        return list;
    }

    private MailDataDto createMailData(final String email, final InsertTaskPlanResultDto planResultDto) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sendEmail); // 送信元メールアドレス
        mailMessage.setTo(email);
        // mailMessage.setCc(""); // cc不要
        // mailMessage.setBcc(""); // bcc不要
        mailMessage.setSubject(planResultDto.getTaskPlanName() + "：政治資金関連者標準化サイト");
        // mailMessage.setReplyTo("このアドレスに返信はできません");

        // ここでは結果を待つだけなので遷移ページは設けない
        // 異議があった場合は→運営者に連絡をして作業を止めてもらう
        String body = planResultDto.getMessageTemplate();
        mailMessage.setText(body);

        MailDataDto mailDataDto = new MailDataDto();
        mailDataDto.setSimpleMailMessage(mailMessage);
        mailDataDto.setIsRepeat(false);

        return mailDataDto;
    }

}
