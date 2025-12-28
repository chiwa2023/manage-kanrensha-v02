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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory18Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory18Repository;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 基準時間より前の関連者企業・団体最新データを抽出する
 */
@Component
public class DumpKanrenshaKigyouDtHistory18ItemReader extends RepositoryItemReader<KanrenshaKigyouDtHistory18Entity> {

    /**
     * コンストラクタ
     *
     * @param partnerCorpHistory18Repository 関連者企業履歴18Repository
     */
    public DumpKanrenshaKigyouDtHistory18ItemReader(
            final @Autowired KanrenshaKigyouDtHistory18Repository partnerCorpHistory18Repository) {
        super();
        super.setRepository(partnerCorpHistory18Repository);
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
