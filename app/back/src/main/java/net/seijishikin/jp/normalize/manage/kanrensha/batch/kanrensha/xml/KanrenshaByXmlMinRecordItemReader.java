package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.infrastructure.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;

/**
 * 関連者政治団体最小登録実登録ItemReader
 */
@Component
public class KanrenshaByXmlMinRecordItemReader extends RepositoryItemReader<WkTblMasterAllByXmlEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblMasterAllByXmlRepository XMLマスタ最小登録Repository
     */
    public KanrenshaByXmlMinRecordItemReader(
            final @Autowired WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository) {

        super(wkTblMasterAllByXmlRepository, new HashMap<>());
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
