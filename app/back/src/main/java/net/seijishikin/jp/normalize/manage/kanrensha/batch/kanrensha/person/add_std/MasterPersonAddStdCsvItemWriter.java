package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_std;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonMasterRepository;

/**
 * 関連者個人マスタ標準Csv登録ItemWriter
 */
@Component
public class MasterPersonAddStdCsvItemWriter extends JpaItemWriter<WkTblKanrenshaPersonMasterEntity> {

    /** 関連者個人マスタワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaPersonMasterRepository wkTblKanrenshaPersonMasterRepository;

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
    public MasterPersonAddStdCsvItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblKanrenshaPersonMasterEntity> items) {

        int code = 0;

        Optional<WkTblKanrenshaPersonMasterEntity> optional = wkTblKanrenshaPersonMasterRepository
                .findFirstByOrderByWkTblKanrenshaPersonMasterCodeDesc();
        if (!optional.isEmpty()) {
            code = optional.get().getWkTblKanrenshaPersonMasterCode();
        }

        for (WkTblKanrenshaPersonMasterEntity entity : items) {
            code++;
            setTableDataHistoryUtil.practiceInsert(userDto, entity);
            entity.setWkTblKanrenshaPersonMasterCode(code);
        }

        wkTblKanrenshaPersonMasterRepository.saveAll(items);
    }

}
