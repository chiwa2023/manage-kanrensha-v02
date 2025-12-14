package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min;

import java.util.Optional;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiAddMinRepository;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 関連者政治団体最小登録CsvItemReaer
 */
@Component
public class KanrenshaSeijidantaiAddMiniCsvItemWriter extends JpaItemWriter<WkTblKanrenshaSeijidantaiAddMinEntity> {

    /** 関連者政治団体登録最小限Repository */
    @Autowired
    private WkTblKanrenshaSeijidantaiAddMinRepository wkTblKanrenshaSeijidantaiAddMinRepository;

    /** テーブル履歴設定Util */
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
    public KanrenshaSeijidantaiAddMiniCsvItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblKanrenshaSeijidantaiAddMinEntity> items) {

        int code = 0;

        Optional<WkTblKanrenshaSeijidantaiAddMinEntity> optional = wkTblKanrenshaSeijidantaiAddMinRepository
                .findFirstByOrderByWkTblKanrenshaSeijidantaiAddMinCodeDesc();
        if (!optional.isEmpty()) {
            code = optional.get().getWkTblKanrenshaSeijidantaiAddMinCode();
        }

        for (WkTblKanrenshaSeijidantaiAddMinEntity entity : items) {
            code++;
            setTableDataHistoryUtil.practiceInsert(userDto, entity);
            entity.setWkTblKanrenshaSeijidantaiAddMinCode(code);
        }

        wkTblKanrenshaSeijidantaiAddMinRepository.saveAll(items);
    }

}
