package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_std; // NOPMD

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
import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtMasterResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtMasterResultRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.InsertKanrenshaKigyouDtHistoryService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateDokujiCodeForKigyouDtUtil;

/**
 * 関連者企業・団体標準登録マスタ複写ItemWriter
 */
@Component
public class MasterKigyouDtAddStdRecordItemWriter extends JpaItemWriter<WkTblKanrenshaKigyouDtMasterEntity> {

    /** 関連者企業・団体マスタ標準判定結果Repository */
    @Autowired
    private WkTblKanrenshaKigyouDtMasterResultRepository wkTblKanrenshaKigyouDtMasterResultRepository;

    /** 関連者企業・団体履歴登録Service */
    @Autowired
    private InsertKanrenshaKigyouDtHistoryService insertKanrenshaKigyouDtHistoryService;

    /** 関連者企業・団体マスタRepository */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    /** 関連者企業・団体マスタ住所Repository */
    @Autowired
    private KanrenshaKigyouDtAddressRepository kanrenshaKigyouDtAddressRepository;

    /** 関連者企業・団体マスタ住所Repository */
    @Autowired
    private KanrenshaKigyouDtAccessRepository kanrenshaKigyouDtAccessRepository;

    /** 関連者企業・団体マスタ(その他属性)Repository */
    @Autowired
    private KanrenshaKigyouDtPropertyRepository kanrenshaKigyouDtPropertyRepository;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 全文自然検索整形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    
    /** 関連者コード企業・団体用発行Utility */
    @Autowired
    private CreateDokujiCodeForKigyouDtUtil createDokujiCodeForKigyouDtUtil;

    /** ユーザ最低限Dto */
    private LeastUserDto userDto;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public MasterKigyouDtAddStdRecordItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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

        final List<WkTblKanrenshaKigyouDtMasterResultEntity> list = new ArrayList<>();

        // 編集処理
        for (WkTblKanrenshaKigyouDtMasterEntity entity : items) {

            // 関連者コードを設定
            String kanrenshaCode = createDokujiCodeForKigyouDtUtil.practice(entity.getHoujinNo());

            // マスタ登録
            int masterId = this.insertMaster(entity, kanrenshaCode);
            // マスタの内容を複写した履歴を登録
            int historyId = this.insertHistory(entity, kanrenshaCode);

            // 両方間違いなく更新できたら結果に残す
            if (masterId != 0 && historyId != 0) {
                list.add(this.createJudge(entity));
            }
        }

        wkTblKanrenshaKigyouDtMasterResultRepository.saveAllAndFlush(list);
    }

    /* マスタ登録処理を行う */
    private int insertMaster(final WkTblKanrenshaKigyouDtMasterEntity entityWkTbl, final String kanrenshaCode) {

        // マスタ本体登録
        KanrenshaKigyouDtMasterEntity KanrenshaKigyouDtMasterEntity = new KanrenshaKigyouDtMasterEntity();
        BeanUtils.copyProperties(entityWkTbl, KanrenshaKigyouDtMasterEntity);
        KanrenshaKigyouDtMasterEntity.setKigyouDtKanrenshaCode(kanrenshaCode);
        setTableDataHistoryUtil.practiceInsert(userDto, KanrenshaKigyouDtMasterEntity);
        KanrenshaKigyouDtMasterEntity.setCompareNameText(formatNaturalSearchTextUtil.practice(KanrenshaKigyouDtMasterEntity.getKanrenshaName()));
        int masterId = kanrenshaKigyouDtMasterRepository.save(KanrenshaKigyouDtMasterEntity).getKanrenshaKigyouDtMasterId();

        // マスタ住所登録
        KanrenshaKigyouDtAddressEntity addressEntity = new KanrenshaKigyouDtAddressEntity();
        addressEntity.setKigyouDtKanrenshaCode(kanrenshaCode);
        addressEntity.setKanrenshaKigyouDtId(masterId);
        BeanUtils.copyProperties(entityWkTbl, addressEntity);
        setTableDataHistoryUtil.practiceInsert(userDto, addressEntity);
        kanrenshaKigyouDtAddressRepository.save(addressEntity);

        // マスタ連絡先登録
        KanrenshaKigyouDtAccessEntity accessEntity = new KanrenshaKigyouDtAccessEntity();
        accessEntity.setKigyouDtKanrenshaCode(kanrenshaCode);
        accessEntity.setKanrenshaKigyouDtId(masterId);
        BeanUtils.copyProperties(entityWkTbl, accessEntity);
        setTableDataHistoryUtil.practiceInsert(userDto, accessEntity);
        kanrenshaKigyouDtAccessRepository.save(accessEntity);

        // マスタ属性登録
        KanrenshaKigyouDtPropertyEntity propertyEntity = new KanrenshaKigyouDtPropertyEntity();
        propertyEntity.setKigyouDtKanrenshaCode(kanrenshaCode);
        propertyEntity.setKanrenshaKigyouDtId(masterId);
        BeanUtils.copyProperties(entityWkTbl, propertyEntity);
        setTableDataHistoryUtil.practiceInsert(userDto, propertyEntity);
        kanrenshaKigyouDtPropertyRepository.save(propertyEntity);

        return masterId;
    }

    /* マスタ履歴登録処理を行う */
    private int insertHistory(final WkTblKanrenshaKigyouDtMasterEntity entityWkTbl, final String kanrenshaCode) {

        KanrenshaKigyouDtHistoryBaseEntity entity = new KanrenshaKigyouDtHistoryBaseEntity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setKigyouDtKanrenshaCode(kanrenshaCode);

        return insertKanrenshaKigyouDtHistoryService.practice(userDto, entity);
    }

    /* ワークテーブル処理結果Entityを作成する */
    private WkTblKanrenshaKigyouDtMasterResultEntity createJudge(final WkTblKanrenshaKigyouDtMasterEntity entityWkTbl) {

        WkTblKanrenshaKigyouDtMasterResultEntity entity = new WkTblKanrenshaKigyouDtMasterResultEntity();
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setWkTblKanrenshaKigyouDtMasterId(entityWkTbl.getWkTblKanrenshaKigyouDtMasterId());

        return entity;
    }
}
