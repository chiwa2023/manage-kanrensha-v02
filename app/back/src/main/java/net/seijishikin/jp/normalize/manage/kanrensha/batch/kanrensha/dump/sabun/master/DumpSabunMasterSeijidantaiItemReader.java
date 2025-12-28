package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.sabun.master;

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
 * 開始日時以上かつ終了日時より小さい関連者政治団体最新マスタ差分データItemReader
 */
@Component
public class DumpSabunMasterSeijidantaiItemReader
        extends RepositoryItemReader<KanrenshaSeijidantaiMasterEntity> {

    /**
     * コンストラクタ
     *
     * @param kanrenshaSeijidantaiMasterRepository 関連者政治団体マスタRepository
     */
    public DumpSabunMasterSeijidantaiItemReader(
            final @Autowired KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository) {
        super();
        super.setRepository(kanrenshaSeijidantaiMasterRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findByInsertTimestampGreaterThanEqualAndInsertTimestampLessThanAndIsLatest");

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

        LocalDateTime datetimeStart = stepExecution.getJobParameters().getLocalDateTime("datetimeStart");
        LocalDateTime datetimeEnd = stepExecution.getJobParameters().getLocalDateTime("datetimeEnd");

        List<Object> list = new ArrayList<>();
        list.add(datetimeStart);
        list.add(datetimeEnd);
        list.add(SetTableDataHistoryUtil.INSERT_STATE);
        super.setArguments(list);
    }

}
