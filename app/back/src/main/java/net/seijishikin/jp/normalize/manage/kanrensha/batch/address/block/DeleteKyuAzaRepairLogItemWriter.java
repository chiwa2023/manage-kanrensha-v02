package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalRepairLogEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepairLogRepository;

/**
 * 旧字削除フラグ履歴変更ItemWriter
 */
@Component
public class DeleteKyuAzaRepairLogItemWriter extends JpaItemWriter<WkTblPostalCommonEntity> {

    /** 郵便番号修復ログRepository */
    @Autowired
    private AddressPostalRepairLogRepository addressPostalRepairLogRepository;

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
    public DeleteKyuAzaRepairLogItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
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

        List<AddressPostalRepairLogEntity> list = new ArrayList<>();
        for (WkTblPostalCommonEntity entity : items) {
            list.add(this.cretateLogEntity(entity));
        }

        addressPostalRepairLogRepository.saveAll(list);
    }

    private AddressPostalRepairLogEntity cretateLogEntity(final WkTblPostalCommonEntity entity) {

        AddressPostalRepairLogEntity logEntity = new AddressPostalRepairLogEntity();
        BeanUtils.copyProperties(entity, logEntity);

        // 最新を履歴に変更して作業変更
        setTableDataHistoryUtil.practiceDelete(userDto, logEntity);

        return logEntity;
    }
}
