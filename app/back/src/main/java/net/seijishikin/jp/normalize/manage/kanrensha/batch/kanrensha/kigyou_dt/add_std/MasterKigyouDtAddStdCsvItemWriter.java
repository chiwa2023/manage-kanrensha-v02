package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_std;

import java.util.Optional;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtMasterRepository;

/**
 * 関連者企業・団体マスタ標準Csv登録ItemWriter
 */
@Component
public class MasterKigyouDtAddStdCsvItemWriter extends JpaItemWriter<WkTblKanrenshaKigyouDtMasterEntity> {

    /** 関連者企業・団体マスタワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaKigyouDtMasterRepository wkTblKanrenshaKigyouDtMasterRepository;

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
    public MasterKigyouDtAddStdCsvItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblKanrenshaKigyouDtMasterEntity> items) {

        int code = 0;

//        Optional<WkTblKanrenshaKigyouDtMasterEntity> optional = wkTblKanrenshaKigyouDtMasterRepository
//                .findFirstByOrderByWkTblMasterKigyouDtCodeDesc();
//        if (!optional.isEmpty()) {
//            code = optional.get().getWkTblMasterKigyouDtCode();
//        }
//
//        for (WkTblKanrenshaKigyouDtMasterEntity entity : items) {
//            code++;
//            setTableDataHistoryUtil.practiceInsert(userDto, entity);
//            entity.setWkTblMasterKigyouDtCode(code);
//        }
//
//        wkTblKanrenshaKigyouDtMasterRepository.saveAll(items);
    }

}
