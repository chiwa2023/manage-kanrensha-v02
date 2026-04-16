package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaCombineOrgRepository;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 個人団体紐づけワークテーブル登録ItemReader
 */
@Component
public class CombineOrgRecordItemReader extends RepositoryItemReader<WkTblKanrenshaCombineOrgEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblCombineOrgRepository 個人団体紐づけワークテーブルRepository
     */
    public CombineOrgRecordItemReader(final @Autowired WkTblKanrenshaCombineOrgRepository wkTblCombineOrgRepository) {

        super();
        super.setRepository(wkTblCombineOrgRepository);
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
