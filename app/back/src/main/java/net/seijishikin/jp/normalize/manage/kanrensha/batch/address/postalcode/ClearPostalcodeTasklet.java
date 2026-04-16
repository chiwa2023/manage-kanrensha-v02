package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

/**
 * 郵便番号テーブルを初期化する
 */
@Component
public class ClearPostalcodeTasklet implements Tasklet {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        Query queryDelete = entityManager.createNativeQuery("DELETE FROM address_postal");
        queryDelete.executeUpdate();

        Query queryDeleteIrregular = entityManager.createNativeQuery("DELETE FROM address_postal_irregular");
        queryDeleteIrregular.executeUpdate();

        Query queryIncrement = entityManager.createNativeQuery("ALTER TABLE address_postal auto_increment = 0");
        queryIncrement.executeUpdate();

        Query queryIncrementIrregular = entityManager
                .createNativeQuery("ALTER TABLE address_postal_irregular auto_increment = 0");
        queryIncrementIrregular.executeUpdate();

        // 処理終了
        return RepeatStatus.FINISHED;

    }

}
