package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.history;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiHistoryRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.InsertKanrenshaSeijidantaiHistoryService;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 関連者政治団体最終書き込み処理
 */
@Component
public class KanrenshaSeijidantaiWkTblFixItemWriter extends JpaItemWriter<WkTblKanrenshaSeijidantaiHistoryEntity> {

    /** 関連者政治団体ワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaSeijidantaiHistoryRepository wkTbKanrenshaSeijidantaiHistoryRepository;

    /** 関連者政治団体履歴挿入Service */
    @Autowired
    private InsertKanrenshaSeijidantaiHistoryService insertKanrenshaSeijidantaiHistoryService;

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
    public KanrenshaSeijidantaiWkTblFixItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblKanrenshaSeijidantaiHistoryEntity> items) {

        final Integer zero = 0;

        final List<WkTblKanrenshaSeijidantaiHistoryEntity> list = new ArrayList<>();

        // 編集処理
        for (WkTblKanrenshaSeijidantaiHistoryEntity entity : items) {
            if (!zero.equals(entity.getWkKanrenshaSeijidantaiHistoryId())) {
                if (entity.getIsAffected()) {
                    // 判定が影響させるの場合は本体に書き込み
                    this.insertHistoryTable(entity);
                    entity.setIsFinish(true);
                    entity.setJudgeReason("正常保存");
                } else {
                    entity.setIsFinish(false);
                }
                entity.setWkKanrenshaSeijidantaiHistoryCode(entity.getWkKanrenshaSeijidantaiHistoryId());
                setTableDataHistoryUtil.practiceInsert(userDto, entity);
                list.add(entity);
            }
            // ワークうテーブルから呼び出しできなかったテーブルIdが0のデータは更新対象外
        }

        wkTbKanrenshaSeijidantaiHistoryRepository.saveAll(list);
    }

    /* 履歴テーブル本体に保存する */
    private void insertHistoryTable(final WkTblKanrenshaSeijidantaiHistoryEntity entityWkTbl) {

        KanrenshaSeijidantaiHistoryBaseEntity entity = new KanrenshaSeijidantaiHistoryBaseEntity();

        entity.setAllName(entityWkTbl.getKanrenshaName());
        entity.setAllAddress(entityWkTbl.getAllAddress());
        entity.setOrgDelegateName(entityWkTbl.getSeijidantaiDelegate());
        entity.setOrgDelegateCode(entityWkTbl.getOrgDelegateCode());
        entity.setSeijidantaiKanrenshaCode(entityWkTbl.getSeijidantaiKanrenshaCode());

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, entity);
    }

}
