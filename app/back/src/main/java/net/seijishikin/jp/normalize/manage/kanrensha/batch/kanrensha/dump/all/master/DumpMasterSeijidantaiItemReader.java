package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.master;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;

/**
 * 関連者政治団体マスタCsv出力ItemReader
 */
@Component
public class DumpMasterSeijidantaiItemReader extends RepositoryItemReader<KanrenshaSeijidantaiMasterEntity> {

    /**
     * コンストラクタ
     *
     * @param kanrenshaSeijidantaiMasterRepository 関連者企業・団体Repository
     */
    public DumpMasterSeijidantaiItemReader(
            final @Autowired KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository) {
        super();
        super.setRepository(kanrenshaSeijidantaiMasterRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findByInsertTimestampLessThanAndIsLatest");

        List<Object> list = new ArrayList<>();
        super.setArguments(list); // NOPMD
    }

    /**
     * 起動条件を設定する
     *
     * @param stepExecution StepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        LocalDateTime datetimeEnd = stepExecution.getJobParameters().getLocalDateTime("datetimeEnd");

        List<Object> list = new ArrayList<>();
        list.add(datetimeEnd);
        list.add(SetTableDataHistoryUtil.INSERT_STATE);

        super.setArguments(list);
    }

}
