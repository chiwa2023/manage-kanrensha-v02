package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtAddMinRepository;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * ファイル内抽出処理を削除する
 */
@Component
public class SuspendDuplicateWkTblKanrenshaKigyouDtAddMinTasklet implements Tasklet, StepExecutionListener {

    /** 関連者企業・団体登録最小限Repository */
    @Autowired
    private WkTblKanrenshaKigyouDtAddMinRepository wkTblKanrenshaKigyouDtAddMinRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** バッチ用ユーザ最低限作成Util */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** ユーザ最小限Dto */
    private LeastUserDto userDto;

    /**
     * 起動条件を設定する
     *
     * @param stepExecution StepExecution
     */
    @BeforeStep
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        userDto = createUserLeastDtoByBatchParamUtil.practice(stepExecution);
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        Integer userCode = userDto.getUserPersonCode();

        List<KanrenshaKigyouDtMasterUniquekeyDto> listKeyGroup = wkTblKanrenshaKigyouDtAddMinRepository
                .findDuplicateUniqueKey(userCode);

        for (KanrenshaKigyouDtMasterUniquekeyDto uniqueDto : listKeyGroup) {
            List<WkTblKanrenshaKigyouDtAddMinEntity> list = wkTblKanrenshaKigyouDtAddMinRepository
                    .findByKanrenshaNameAndAllAddressAndKigyouDtDelegateAndInsertUserCodeOrderByWkTblKanrenshaKigyouDtAddMinIdAsc(
                            uniqueDto.getKanrenshaName(), uniqueDto.getAllAddress(), uniqueDto.getKigyouDtDelegate(),
                            userCode);
            list.remove(0); // 1行だけは処理実行行として残す
            for (WkTblKanrenshaKigyouDtAddMinEntity entity : list) {
                setTableDataHistoryUtil.practiceDelete(userDto, entity); // 削除
                entity.setIsLatest(SetTableDataHistoryUtil.DELETE_STATE);
                entity.setIsFinish(true);
                entity.setJudgeReason("アップロードファイル内で重複しているデータです");
            }
            wkTblKanrenshaKigyouDtAddMinRepository.saveAll(list);
        }

        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
