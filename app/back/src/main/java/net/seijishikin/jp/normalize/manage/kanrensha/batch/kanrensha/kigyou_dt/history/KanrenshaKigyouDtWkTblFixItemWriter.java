package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.history;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtHistoryRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.InsertKanrenshaKigyouDtHistoryService;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業・団体最終書き込み処理
 */
@Component
public class KanrenshaKigyouDtWkTblFixItemWriter extends JpaItemWriter<WkTblKanrenshaKigyouDtHistoryEntity> {

    /** 関連者企業・団体ワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaKigyouDtHistoryRepository wkTbKanrenshaKigyouDtHistoryRepository;

    /** 関連者企業・団体履歴新規挿入Service */
    @Autowired
    private InsertKanrenshaKigyouDtHistoryService insertKanrenshaKigyouDtHistoryService;

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
    public KanrenshaKigyouDtWkTblFixItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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

        final Integer zero = 0;

        final List<WkTblKanrenshaKigyouDtHistoryEntity> list = new ArrayList<>();

        // 編集処理
        for (WkTblKanrenshaKigyouDtHistoryEntity entity : items) {
            if (!zero.equals(entity.getWkKanrenshaKigyouDtHistoryId())) {
                if (entity.getIsAffected()) {
                    // 判定が影響させるの場合は本体に書き込み
                    this.insertHistoryTable(entity);
                    entity.setIsFinish(true);
                    entity.setJudgeReason("正常保存");
                } else {
                    entity.setIsFinish(false);
                }
                entity.setWkKanrenshaKigyouDtHistoryCode(entity.getWkKanrenshaKigyouDtHistoryId());
                setTableDataHistoryUtil.practiceInsert(userDto, entity);
                list.add(entity);
            }
            // ワークうテーブルから呼び出しできなかったテーブルIdが0のデータは更新対象外
        }

        wkTbKanrenshaKigyouDtHistoryRepository.saveAll(list);
    }

    /* 履歴テーブル本体に保存する */
    private void insertHistoryTable(final WkTblKanrenshaKigyouDtHistoryEntity entityWkTbl) {

        KanrenshaKigyouDtHistoryBaseEntity entity = new KanrenshaKigyouDtHistoryBaseEntity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setAllName(entityWkTbl.getKanrenshaName());
        entity.setOrgDelegateName(entityWkTbl.getKigyouDtDelegate());

        // 検索テキストはServiceで設定
        insertKanrenshaKigyouDtHistoryService.practice(userDto, entity);

    }

}
