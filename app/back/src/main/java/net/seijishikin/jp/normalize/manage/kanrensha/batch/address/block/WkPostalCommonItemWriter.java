package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalCommonRepository;

/**
 * 郵便番号ワークテーブルを最新フラグに従って保存するItemWriter
 */
@Component
public class WkPostalCommonItemWriter extends JpaItemWriter<WkTblPostalCommonEntity> {

    /** 郵便番号終生ワークテーブルRespository */
    @Autowired
    private WkTblPostalCommonRepository wkTblPostalCommonRepository;

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
     * @param entityManagerFactory EntityManagerFactory
     */
    public WkPostalCommonItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    /**
     * BeforeStep(ユーザ情報設定)
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
    public void write(final Chunk<? extends WkTblPostalCommonEntity> items) {

        for (WkTblPostalCommonEntity entity : items) {
            if (entity.getIsLatest()) {
                setTableDataHistoryUtil.practiceInsert(userDto, entity);
            } else {
                setTableDataHistoryUtil.practiceDelete(userDto, entity);
            }
        }

        // 編集済みデータを保存するだけ
        wkTblPostalCommonRepository.saveAllAndFlush(items);
    }


}
