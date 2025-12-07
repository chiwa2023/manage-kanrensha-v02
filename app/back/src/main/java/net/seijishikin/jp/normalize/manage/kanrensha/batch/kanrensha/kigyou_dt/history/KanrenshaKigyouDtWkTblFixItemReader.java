package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.history;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtHistoryResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtHistoryResultRepository;

/**
 * 関連者企業・団体ワークテーブル修正ItemReader
 */
@Component
public class KanrenshaKigyouDtWkTblFixItemReader extends RepositoryItemReader<WkTblKanrenshaKigyouDtHistoryResultEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblKanrenshaKigyouDtResultRepository 関連者企業・団体ワークテーブルRepository
     */
    public KanrenshaKigyouDtWkTblFixItemReader(
            final @Autowired WkTblKanrenshaKigyouDtHistoryResultRepository wkTblKanrenshaKigyouDtResultRepository) {

        super();
        super.setRepository(wkTblKanrenshaKigyouDtResultRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findByInsertUserCode");

        List<Object> list = new ArrayList<>();
        super.setArguments(list); // NOPMD
    }

    /**
     * BeforeStep(読み取りファイル指定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        Integer userCode = Math.toIntExact(stepExecution.getJobParameters().getLong("userCode"));

        List<Object> list = new ArrayList<>();
        list.add(userCode);

        super.setArguments(list); // NOPMD
    }

}
