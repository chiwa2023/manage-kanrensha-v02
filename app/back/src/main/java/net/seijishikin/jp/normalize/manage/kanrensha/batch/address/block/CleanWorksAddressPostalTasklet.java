package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalCommonRepository;

/**
 * 処理の終わった作業テーブルを全クリアする
 */
@Component
public class CleanWorksAddressPostalTasklet implements Tasklet {

    /** 郵便番号作業Repository */
    @Autowired
    private WkTblPostalCommonRepository wkTblPostalCommonRepository;

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        // 全データ消去
        wkTblPostalCommonRepository.deleteAll();

        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
