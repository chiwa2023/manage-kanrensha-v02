package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiAddMinResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiAddMinResultRepository;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 関連者政治団体処理結果反映ItemReader
 */
@Component
public class KanrenshaSeijidantaiAddMiniWkTblFixItemReader extends RepositoryItemReader<WkTblKanrenshaSeijidantaiAddMinResultEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblKanrenshaSeijidantaiAddMinResultRepository 関連者政治団体最小登録処理結果ワークテーブルRepository
     */
    public KanrenshaSeijidantaiAddMiniWkTblFixItemReader(
            final @Autowired WkTblKanrenshaSeijidantaiAddMinResultRepository wkTblKanrenshaSeijidantaiAddMinResultRepository) {

        super();
        super.setRepository(wkTblKanrenshaSeijidantaiAddMinResultRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findByInsertUserCodeAndIsLatest");

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

        super.setArguments(list); // NOPMD
    }

}
