package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtAddMinRepository;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業・団体最小登録実登録ItemReader
 */
@Component
public class KanrenshaKigyouDtAddMiniRecordItemReader extends RepositoryItemReader<WkTblKanrenshaKigyouDtAddMinEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblKanrenshaKigyouDtAddMinRepository 関連者企業・団体最小登録ワークテーブルRepository
     */
    public KanrenshaKigyouDtAddMiniRecordItemReader(
            final @Autowired WkTblKanrenshaKigyouDtAddMinRepository wkTblKanrenshaKigyouDtAddMinRepository) {

        super();
        super.setRepository(wkTblKanrenshaKigyouDtAddMinRepository);
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
