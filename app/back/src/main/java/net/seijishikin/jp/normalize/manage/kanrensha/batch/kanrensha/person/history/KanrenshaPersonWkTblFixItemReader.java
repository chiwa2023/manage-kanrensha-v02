package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.history;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonHistoryResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonHistoryResultRepository;


/**
 * 関連者個人ワークテーブル修正ItemReader
 */
@Component
public class KanrenshaPersonWkTblFixItemReader extends RepositoryItemReader<WkTblKanrenshaPersonHistoryResultEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblKanrenshaPersonResultRepository 関連者個人ワークテーブルRepository
     */
    public KanrenshaPersonWkTblFixItemReader(
            final @Autowired WkTblKanrenshaPersonHistoryResultRepository wkTblKanrenshaPersonResultRepository) {

        super();
        super.setRepository(wkTblKanrenshaPersonResultRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findByInsertUserCode");

        List<Object> list = new ArrayList<>();
        super.setArguments(list); // NOPMD
    }

    /**
     * BeforeStep
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
