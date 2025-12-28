package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory34Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory34Repository;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 基準時間より前の関連者個人最新履歴を抽出する
 */
@Component
public class DumpKanrenshaPersonHistory34ItemReader extends RepositoryItemReader<KanrenshaPersonHistory34Entity> {

    /**
     * コンストラクタ
     *
     * @param partnerPersonHistory34Repository 関連者企業履歴34Repository
     */
    public DumpKanrenshaPersonHistory34ItemReader(
            final @Autowired KanrenshaPersonHistory34Repository partnerPersonHistory34Repository) {
        super();
        super.setRepository(partnerPersonHistory34Repository);
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
