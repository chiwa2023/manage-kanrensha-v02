package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.history;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.infrastructure.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonHistoryRepository;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人ワークテーブルItemReader
 */
@Component
public class KanrenshaPersonResultItemReader extends RepositoryItemReader<WkTblKanrenshaPersonHistoryEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblKanrenshaPersonHistoryRepository 関連者個人ワークテーブルRepository
     */
    public KanrenshaPersonResultItemReader(
            final @Autowired WkTblKanrenshaPersonHistoryRepository wkTblKanrenshaPersonHistoryRepository) {

        super(wkTblKanrenshaPersonHistoryRepository, new HashMap<>());
        super.setMethodName("findByInsertUserCodeAndIsLatestAndIsAffectedAndIsFinish");

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
        list.add(SetTableDataHistoryUtil.INSERT_STATE);
        list.add(true);
        list.add(false);

        super.setArguments(list); // NOPMD
    }

}
