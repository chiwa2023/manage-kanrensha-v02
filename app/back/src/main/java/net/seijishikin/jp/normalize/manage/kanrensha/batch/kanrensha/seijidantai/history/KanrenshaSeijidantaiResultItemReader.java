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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiHistoryRepository;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 関連者政治団体ワークテーブルItemReader
 */
@Component
public class KanrenshaSeijidantaiResultItemReader extends RepositoryItemReader<WkTblKanrenshaSeijidantaiHistoryEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblKanrenshaSeijidantaiHistoryRepository 関連者政治団体ワークテーブルRepository
     */
    public KanrenshaSeijidantaiResultItemReader(
            final @Autowired WkTblKanrenshaSeijidantaiHistoryRepository wkTblKanrenshaSeijidantaiHistoryRepository) {

        super();
        super.setRepository(wkTblKanrenshaSeijidantaiHistoryRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findByInsertUserCodeAndIsLatestAndIsAffectedAndIsFinish");

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
        list.add(SetTableDataHistoryUtil.INSERT_STATE);
        list.add(true);
        list.add(false);

        super.setArguments(list); // NOPMD
    }

}
