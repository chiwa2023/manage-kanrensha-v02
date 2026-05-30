package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtChangeRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtDeleteRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtFileRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtMarkRepository;

/**
 * アドレス・ベース・レジストリ削除Tasklet
 */
@Component
public class DeleteWkTblAddressTasklet implements Tasklet, StepExecutionListener {

    /** アドレス・ベース・レジストリワークテーブルファイル登録Repository */
    @Autowired
    private WkTblAddressRsdtFileRepository wkTblAddressRsdtFileRepository;

    /** アドレス・ベース・レジストリワークテーブル更新マークRepository */
    @Autowired
    private WkTblAddressRsdtChangeRepository wkTblAddressRsdtChangeRepository;

    /** アドレス・ベース・レジストリワークテーブル削除Repository */
    @Autowired
    private WkTblAddressRsdtDeleteRepository wkTblAddressRsdtDeleteRepository;

    /** アドレス・ベース・レジストリワークテーブル処理マークRepository */
    @Autowired
    private WkTblAddressRsdtMarkRepository wkTblAddressRsdtMarkRepository;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** ユーザ最低限Dto */
    private LeastUserDto userDto;

    /**
     * BeforeStep(ユーザ情報設定)
     *
     * @param stepExecution stepExecution
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
    @Transactional
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        // ユーザコードで関連ワークテーブルを全削除
        Integer userCode = userDto.getUserPersonCode();

        wkTblAddressRsdtFileRepository.deleteByInsertUserCode(userCode);
        wkTblAddressRsdtChangeRepository.deleteByInsertUserCode(userCode);
        wkTblAddressRsdtDeleteRepository.deleteByInsertUserCode(userCode);
        wkTblAddressRsdtMarkRepository.deleteByInsertUserCode(userCode);

        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
