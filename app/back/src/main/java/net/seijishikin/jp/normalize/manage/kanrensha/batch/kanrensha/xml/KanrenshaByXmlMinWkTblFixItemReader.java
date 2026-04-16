package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.xml;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlResultRepository;

/**
 * XMLから最小マスタ登録処理結果反映ItemReader
 */
@Component
public class KanrenshaByXmlMinWkTblFixItemReader extends RepositoryItemReader<WkTblMasterAllByXmlResultEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblMasterAllByXmlResultRepository XMLから最小マスタ登録処理結果ワークテーブルRepository
     */
    public KanrenshaByXmlMinWkTblFixItemReader(
            final @Autowired WkTblMasterAllByXmlResultRepository wkTblMasterAllByXmlResultRepository) {

        super();
        super.setRepository(wkTblMasterAllByXmlResultRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findByInsertUserCodeAndIsLatest");

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

        super.setArguments(list); // NOPMD
    }

}
