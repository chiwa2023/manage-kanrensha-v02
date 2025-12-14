package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.history;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonHistoryRepository;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * アップロードファイル内で重複している行を処理対象外とするTasklet
 */
@Component
public class SuspendDuplicateWkTblKanrenshaPersonHistoryTasklet implements Tasklet,StepExecutionListener {

    /** 関連者個人履歴Repository */
    @Autowired
    private WkTblKanrenshaPersonHistoryRepository wkTblKanrenshaPersonHistoryRepository;

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
        List<KanrenshaPersonUniquekeyDto> listKeyGroup = wkTblKanrenshaPersonHistoryRepository.findDuplicateUniqueKey(userCode);

        for (KanrenshaPersonUniquekeyDto uniqueDto : listKeyGroup) {
            List<WkTblKanrenshaPersonHistoryEntity> list = wkTblKanrenshaPersonHistoryRepository
                    .findByKanrenshaNameAndAllAddressAndPersonShokugyouAndPersonKanrenshaCodeAndInsertUserCodeOrderByWkKanrenshaPersonHistoryIdAsc(
                            uniqueDto.getKanrenshaName(), uniqueDto.getAllAddress(), uniqueDto.getPersonShokugyou(),
                            uniqueDto.getPersonKanrenshaCode(), userCode);
            list.remove(0); // 1行だけは処理実行行として残す
            for (WkTblKanrenshaPersonHistoryEntity entity : list) {
                setTableDataHistoryUtil.practiceDelete(userDto, entity); // 削除
                entity.setIsLatest(SetTableDataHistoryUtil.DELETE_STATE);
                entity.setIsFinish(true);
                entity.setJudgeReason("アップロードファイル内で重複しているデータです");
            }
            wkTblKanrenshaPersonHistoryRepository.saveAllAndFlush(list);
        }

        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
