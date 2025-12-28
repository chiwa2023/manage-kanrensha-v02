package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.sabun.history;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory15Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory15Repository;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 開始日時以上かつ終了日時より小さい関連者企業・団体最新履歴差分データを取得する15
 */
@Component
public class DumpSabunKanrenshaKigyouDtHistory15ItemReader
        extends RepositoryItemReader<KanrenshaKigyouDtHistory15Entity> {

    /**
     * コンストラクタ
     *
     * @param kanrenshaKigyouDtHistory15Repository 関連者企業履歴15Repository
     */
    public DumpSabunKanrenshaKigyouDtHistory15ItemReader(
            final @Autowired KanrenshaKigyouDtHistory15Repository kanrenshaKigyouDtHistory15Repository) {
        super();
        super.setRepository(kanrenshaKigyouDtHistory15Repository);
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
