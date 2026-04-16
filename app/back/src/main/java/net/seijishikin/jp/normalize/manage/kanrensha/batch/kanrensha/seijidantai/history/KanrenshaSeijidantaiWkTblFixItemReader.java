package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.history;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiHistoryResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiHistoryResultRepository;


/**
 * 関連者政治団体ワークテーブル修正ItemReader
 */
@Component
public class KanrenshaSeijidantaiWkTblFixItemReader extends RepositoryItemReader<WkTblKanrenshaSeijidantaiHistoryResultEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblKanrenshaSeijidantaiResultRepository 関連者政治団体ワークテーブルRepository
     */
    public KanrenshaSeijidantaiWkTblFixItemReader(
            final @Autowired WkTblKanrenshaSeijidantaiHistoryResultRepository wkTblKanrenshaSeijidantaiResultRepository) {

        super();
        super.setRepository(wkTblKanrenshaSeijidantaiResultRepository);
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
