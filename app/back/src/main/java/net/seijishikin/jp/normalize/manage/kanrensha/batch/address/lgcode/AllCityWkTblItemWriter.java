package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import java.util.ArrayList;
import java.util.List;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressCityDeleteEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressCityDeleteRepository;

/**
 * 地方自治体コード削除テーブルItemWriter
 */
@Component
public class AllCityWkTblItemWriter extends JpaItemWriter<AddressCityDeleteEntity> {

    /** 地方自治体コード削除Repository */
    @Autowired
    private AddressCityDeleteRepository addressCityDeleteRepository;

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
    public AllCityWkTblItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends AddressCityDeleteEntity> items) {

        List<AddressCityDeleteEntity> listDelete = new ArrayList<>();
        for (AddressCityDeleteEntity entity : items) {
            setTableDataHistoryUtil.practiceInsert(userDto, entity);
            entity.setAddressCityDeleteId(0); // auto increment明記
            listDelete.add(entity);
        }
        addressCityDeleteRepository.saveAll(items);
    }

}
