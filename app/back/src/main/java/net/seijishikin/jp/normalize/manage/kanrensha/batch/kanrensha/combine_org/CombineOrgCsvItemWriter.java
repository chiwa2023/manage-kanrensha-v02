package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org;

import java.util.Optional;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaCombineOrgRepository;

/**
 * 個人団体紐づけCsvItemWriter
 */
@Component
public class CombineOrgCsvItemWriter extends JpaItemWriter<WkTblKanrenshaCombineOrgEntity> {

    /** 個人団体紐づけワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaCombineOrgRepository wkTblKanrenshaCombineOrgRepository;

    
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
    public CombineOrgCsvItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblKanrenshaCombineOrgEntity> items) {

        int code = 0;

//        Optional<WkTblKanrenshaCombineOrgEntity> optional = wkTblKanrenshaCombineOrgRepository
//                .findFirstByOrderByWkTblKanrenshaCombineOrgCodeDesc();
//        if (!optional.isEmpty()) {
//            code = optional.get().getWkTblKanrenshaCombineOrgCode();
//        }
//
//        for (WkTblKanrenshaCombineOrgEntity entity : items) {
//            code++;
//            setTableDataHistoryUtil.practiceInsert(userDto, entity);
//            entity.setWkTblKanrenshaCombineOrgCode(code);
//        }
//
//        wkTblKanrenshaCombineOrgRepository.saveAll(items);
    }


}
