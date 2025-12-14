package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.history;

import java.util.Optional;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtHistoryRepository;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業・団体ワークテーブルItemWriter
 */
@Component
public class KanrenshaKigyouDtHistoryItemWriter extends JpaItemWriter<WkTblKanrenshaKigyouDtHistoryEntity> {

    /** 関連者企業・団体ワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaKigyouDtHistoryRepository wkTbKanrenshaKigyouDtHistoryRepository;

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
    public KanrenshaKigyouDtHistoryItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblKanrenshaKigyouDtHistoryEntity> items) {

        int code = 0;

        Optional<WkTblKanrenshaKigyouDtHistoryEntity> optional = wkTbKanrenshaKigyouDtHistoryRepository
                .findFirstByOrderByWkKanrenshaKigyouDtHistoryCodeDesc();
        if (!optional.isEmpty()) {
            code = optional.get().getWkKanrenshaKigyouDtHistoryCode();
        }

        for (WkTblKanrenshaKigyouDtHistoryEntity entity : items) {
            code++;
            setTableDataHistoryUtil.practiceInsert(userDto, entity);
            entity.setWkKanrenshaKigyouDtHistoryCode(code);
        }

        wkTbKanrenshaKigyouDtHistoryRepository.saveAll(items);
    }

}
