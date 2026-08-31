package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.SendMaileResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.PromoteUserAdminCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.PromoteAdminEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.send_message.PromoteUserAdminSendMailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.PromoteAdminRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.task_plan.InsertTaskPlanService;

/**
 * SE権限推薦Service
 */
@Service
public class PromoteUserAdminService {

    /** SE権限推薦Repository */
    @Autowired
    private PromoteAdminRepository promoteAdminRepository;

    /** タスク計画Repository */
    @Autowired
    private InsertTaskPlanService insertTaskPlanService;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** mail送信Logic */
    @Autowired
    private PromoteUserAdminSendMailLogic promoteUserAdminSendMailLogic;

    /**
     * 処理を行う
     * 
     * @param capsuleDto    処理条件Dto
     * @param creatDateTime 処理時間
     * @return 処理結果
     */
    @Transactional
    public FrameworkMessageAndResultDto practice(final PromoteUserAdminCapsuleDto capsuleDto,
            final LocalDateTime creatDateTime) {

        // 推薦者向けに推薦諾否タスクを登録
        LeastUserDto userDtoPromote = new LeastUserDto();
        UserPersonEntity personEntity = capsuleDto.getEntityUserPromote();
        BeanUtils.copyProperties(personEntity, userDtoPromote);
        InsertTaskPlanResultDto resultTaskDto = insertTaskPlanService.practice(userDtoPromote, creatDateTime,
                TaskInfoConstants.PROMOTE_ADMIN, null);

        // 推薦テーブルに保存(紐づくタスク計画も)
        PromoteAdminEntity promoteAdminEntity = new PromoteAdminEntity();
        promoteAdminEntity.setTaskPlanId(resultTaskDto.getTaskPlanId());
        promoteAdminEntity.setTaskYear(resultTaskDto.getTaskYear());
        promoteAdminEntity.setIsAccept(false); // 未承諾状態を明記
        promoteAdminEntity.setPromoteUserId(userDtoPromote.getUserPersonId());
        promoteAdminEntity.setPromoteUserCode(userDtoPromote.getUserPersonCode());
        promoteAdminEntity.setPromoteUserName(userDtoPromote.getUserPersonName());

        // コードを取得
        Integer code = 1;
        Optional<PromoteAdminEntity> optional = promoteAdminRepository.findFirstByOrderByPromoteAdminCodeDesc();
        if (!optional.isEmpty()) {
            code = optional.get().getPromoteAdminCode();
        }

        LeastUserDto userDtoWorker = capsuleDto.getUserDto();
        setTableDataHistoryUtil.practiceInsert(userDtoWorker, promoteAdminEntity);
        promoteAdminEntity.setPromoteAdminCode(code);
        promoteAdminEntity.setPromoteAdminId(0); // auto increment明記
        final Integer savedId = promoteAdminRepository.save(promoteAdminEntity).getPromoteAdminId();

        // 作業者向けの作業跡が見えないのでメールを送っておく
        // 内容は「諾否は決定次第メールで送信するけど不安なら利用者検索で確認してください」
        SendMaileResultDto maileResultDto = promoteUserAdminSendMailLogic.pracitce(userDtoWorker);

        return this.createResultDto(savedId, resultTaskDto, maileResultDto);
    }

    private FrameworkMessageAndResultDto createResultDto(final Integer savedId,
            final FrameworkMessageAndResultDto resultTaskDto, final SendMaileResultDto maileResultDto) {

        StringBuilder builder = new StringBuilder();
        String parenthesesStart = "(";
        final String semiColon = ";";
        if (savedId == 0) {
            builder.append(parenthesesStart).append("タスク予定が保存できませんでした").append(semiColon);
            parenthesesStart = "";
        }
        if (resultTaskDto.getIsFailure()) {
            builder.append(parenthesesStart).append(resultTaskDto.getMessage()).append(semiColon);
            parenthesesStart = "";
        }
        if (maileResultDto.getIsFailure()) {
            builder.append(parenthesesStart).append(maileResultDto.getMessage()).append(semiColon);
        }

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        if (!builder.isEmpty()) {
            resultDto.setIsFailure(true);
            resultDto.setMessage(builder.append(')').toString());
        }

        return resultDto;
    }

}
