package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_std;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonMasterRepository;

/**
 * 関連者個人標準登録マスタ複写ItemReader
 */
@Component
public class MasterPersonAddStdRecordItemReader extends RepositoryItemReader<WkTblKanrenshaPersonMasterEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblMasterPersonRepository 関連者個人標準登録ワークテーブルRepository
     */
    public MasterPersonAddStdRecordItemReader(
            final @Autowired WkTblKanrenshaPersonMasterRepository wTblKanrenshaPersonMasterRepository) {

        super();
        super.setRepository(wTblKanrenshaPersonMasterRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findByInsertUserCodeAndIsLatestAndIsAffected");

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

        super.setArguments(list); // NOPMD
    }

}
