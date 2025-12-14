package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_std;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtMasterResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtMasterResultRepository;

/**
 * 関連者企業・団体マスタ標準登録処理結果登録ItemReader
 */
@Component
public class MasterKigyouDtAddStdWkTblFixItemReader extends RepositoryItemReader<WkTblKanrenshaKigyouDtMasterResultEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblKanrenshaKigyouDtMasterResultRepository 関連者企業・団体マスタ標準登録処理結果ワークテーブルRepository
     */
    public MasterKigyouDtAddStdWkTblFixItemReader(
            final @Autowired WkTblKanrenshaKigyouDtMasterResultRepository wkTblKanrenshaKigyouDtMasterResultRepository) {

        super();
        super.setRepository(wkTblKanrenshaKigyouDtMasterResultRepository);
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
