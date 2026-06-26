package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_min;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.infrastructure.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonAddMinResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonAddMinResultRepository;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人処理結果反映ItemReader
 */
@Component
public class KanrenshaPersonAddMiniWkTblFixItemReader
        extends RepositoryItemReader<WkTblKanrenshaPersonAddMinResultEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblKanrenshaPersonAddMinResultRepository 関連者個人最小登録処理結果ワークテーブルRepository
     */
    public KanrenshaPersonAddMiniWkTblFixItemReader(
            final @Autowired WkTblKanrenshaPersonAddMinResultRepository wkTblKanrenshaPersonAddMinResultRepository) {

        super(wkTblKanrenshaPersonAddMinResultRepository, new HashMap<>());
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
