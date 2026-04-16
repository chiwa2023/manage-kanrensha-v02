package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtAddMinResultRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.InsertKanrenshaKigyouDtHistoryService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateDokujiCodeForKigyouDtUtil;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業団体マスタ履歴最小登録IterWriter
 */
@Component
public class KanrenshaKigyouDtAddMiniRecordItemWriter extends JpaItemWriter<WkTblKanrenshaKigyouDtAddMinEntity> {

    /** 関連者企業・団体マスタRepository */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    /** 関連者企業・団体マスタ履歴処理結果Repository */
    @Autowired
    private WkTblKanrenshaKigyouDtAddMinResultRepository wkTblKanrenshaKigyouDtAddMinResultRepository;

    /** 全文検索用フォーマットUtility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者コード企業・団体用発行Utility */
    @Autowired
    private CreateDokujiCodeForKigyouDtUtil createDokujiCodeForKigyouDtUtil;

    /** 関連者企業団体履歴挿入Service */
    @Autowired
    private InsertKanrenshaKigyouDtHistoryService insertKanrenshaKigyouDtHistoryService;

    /** ユーザ最低限Dto */
    private LeastUserDto userDto;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public KanrenshaKigyouDtAddMiniRecordItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblKanrenshaKigyouDtAddMinEntity> items) {

        final List<WkTblKanrenshaKigyouDtAddMinResultEntity> list = new ArrayList<>();

        // 編集処理
        for (WkTblKanrenshaKigyouDtAddMinEntity entity : items) {

            String kanrenshaCode = createDokujiCodeForKigyouDtUtil.practice(entity.getHoujinNo());

            int masterId = this.insertMaster(entity, kanrenshaCode);
            int historyId = this.insertHistory(entity, kanrenshaCode);

            // 両方間違いなく更新できたら結果に残す
            if (masterId != 0 && historyId != 0) {
                list.add(this.createResult(entity));
                wkTblKanrenshaKigyouDtAddMinResultRepository.flush();
            }
        }

        wkTblKanrenshaKigyouDtAddMinResultRepository.saveAll(list);
    }

    /**
     * マスタ登録する
     * 
     * @param entityWkTbl   ワークテーブルEntity
     * @param kanrenshaCode 関連者コード
     * @return 登録Id
     */
    private int insertMaster(final WkTblKanrenshaKigyouDtAddMinEntity entityWkTbl, final String kanrenshaCode) {

        KanrenshaKigyouDtMasterEntity entity = new KanrenshaKigyouDtMasterEntity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setKigyouDtKanrenshaCode(kanrenshaCode);
        entity.setCompareNameText(formatNaturalSearchTextUtil.practice(entity.getKanrenshaName()));

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtMasterId(0); // auto_increment明示

        return kanrenshaKigyouDtMasterRepository.save(entity).getKanrenshaKigyouDtMasterId();

    }

    /**
     * 履歴登録する
     *
     * @param entityWkTbl   ワークテーブルEntity
     * @param kanrenshaCode 関連者コード
     * @return 登録Id
     */
    private int insertHistory(final WkTblKanrenshaKigyouDtAddMinEntity entityWkTbl, final String kanrenshaCode) {

        KanrenshaKigyouDtHistoryBaseEntity entity = new KanrenshaKigyouDtHistoryBaseEntity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setKigyouDtKanrenshaCode(kanrenshaCode);
        entity.setAllName(entityWkTbl.getKanrenshaName());
        entity.setOrgDelegateName(entityWkTbl.getKigyouDtDelegate());
        
        // 検索テキストはServiceで設定
        return insertKanrenshaKigyouDtHistoryService.practice(userDto, entity);

    }

    /**
     * 登録結果Entityを作成する
     * 
     * @param entityWkTbl ワークテーブルEntity
     * @return 登録Id
     */
    private WkTblKanrenshaKigyouDtAddMinResultEntity createResult(
            final WkTblKanrenshaKigyouDtAddMinEntity entityWkTbl) {
        WkTblKanrenshaKigyouDtAddMinResultEntity entity = new WkTblKanrenshaKigyouDtAddMinResultEntity();
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setWkTblKanrenshaKigyouDtAddMinId(entityWkTbl.getWkTblKanrenshaKigyouDtAddMinId());

        return entity;
    }

}
