package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.history;


import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonHistoryResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonHistoryResultRepository;

/**
 * 関連者個人ワークテーブル判定ItemWriter
 */
@Component
public class KanrenshaPersonResultItemWriter extends JpaItemWriter<WkTblKanrenshaPersonHistoryResultEntity> {


    /** 関連者個人ワークテーブル判定Repository */
    @Autowired
    private WkTblKanrenshaPersonHistoryResultRepository wkTblKanrenshaPersonResultRepository;

    
    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** ユーザ最低限Dto */
    private LeastUserDto userDto;
    
    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public KanrenshaPersonResultItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
    }

    /**
     * BeforeStep(読み取りファイル指定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        userDto = createUserLeastDtoByBatchParamUtil.practice(stepExecution);
    }

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends WkTblKanrenshaPersonHistoryResultEntity> items) {

        for (WkTblKanrenshaPersonHistoryResultEntity entity : items) {
            setTableDataHistoryUtil.practiceInsert(userDto, entity);
        }

        wkTblKanrenshaPersonResultRepository.saveAll(items);
    }
}
