package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManagerFactory;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaCombineOrgResultRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearInsertCombineOrgService;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 個人団体紐づけ記録ItemWriter
 */
@Service
public class CombineOrgRecordItemWriter extends JpaItemWriter<WkTblKanrenshaCombineOrgEntity> {

    /** 関連者企業・団体履歴(01)Repository */
    @Autowired
    private WkTblKanrenshaCombineOrgResultRepository wkTblKanrenshaCombineOrgResultRepository;

    /** 個人団体紐づけ年切り替えService */
    @Autowired
    private SwitchYearInsertCombineOrgService switchYearInsertCombineOrgService;

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
    public CombineOrgRecordItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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

        final List<WkTblKanrenshaCombineOrgResultEntity> list = new ArrayList<>();

        // 編集処理
        for (WkTblKanrenshaCombineOrgEntity entity : items) {

            // すべて登録できたら判定テーブルに処理成功と記録する
            if (switchYearInsertCombineOrgService.practice(entity, userDto)) {
                list.add(this.createJudge(entity));
            }
        }

        wkTblKanrenshaCombineOrgResultRepository.saveAllAndFlush(list);
    }

    private WkTblKanrenshaCombineOrgResultEntity createJudge(final WkTblKanrenshaCombineOrgEntity entityWkTbl) {
        WkTblKanrenshaCombineOrgResultEntity entity = new WkTblKanrenshaCombineOrgResultEntity();
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setWkTblKanrenshaCombineOrgId(entityWkTbl.getWkTblKanrenshaCombineOrgId());
        return entity;
    }

}
