package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_std; // NOPMD

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiMasterResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiMasterResultRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.InsertKanrenshaSeijidantaiHistoryService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateDokujiCodeForSeijidantaiUtil;

/**
 * 関連者個人標準登録マスタ複写ItemWriter
 */
@Component
public class MasterSeijidantaiAddStdRecordItemWriter extends JpaItemWriter<WkTblKanrenshaSeijidantaiMasterEntity> {

    /** 関連者個人マスタ標準判定結果Repository */
    @Autowired
    private WkTblKanrenshaSeijidantaiMasterResultRepository wkTblKanrenshaSeijidantaiMasterResultRepository;

    /** 関連者個人履歴登録Service */
    @Autowired
    private InsertKanrenshaSeijidantaiHistoryService insertKanrenshaSeijidantaiHistoryService;

    /** 関連者個人マスタRepository */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    /** 関連者個人マスタ住所Repository */
    @Autowired
    private KanrenshaSeijidantaiAddressRepository kanrenshaSeijidantaiAddressRepository;

    /** 関連者個人マスタ住所Repository */
    @Autowired
    private KanrenshaSeijidantaiAccessRepository kanrenshaSeijidantaiAccessRepository;

    /** 関連者個人マスタ(その他属性)Repository */
    @Autowired
    private KanrenshaSeijidantaiPropertyRepository kanrenshaSeijidantaiPropertyRepository;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** 全文自然検索整形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者コード企業・団体用発行Utility */
    @Autowired
    private CreateDokujiCodeForSeijidantaiUtil createDokujiCodeForSeijidantaiUtil;

    /** ユーザ最低限Dto */
    private LeastUserDto userDto;

    /** 空文字 */
    private static final String EMPTY = "";

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public MasterSeijidantaiAddStdRecordItemWriter(
            final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblKanrenshaSeijidantaiMasterEntity> items) {

        final List<WkTblKanrenshaSeijidantaiMasterResultEntity> list = new ArrayList<>();

        // 編集処理
        for (WkTblKanrenshaSeijidantaiMasterEntity entity : items) {

            // 関連者コードを設定
            String kanrenshaCode = createDokujiCodeForSeijidantaiUtil.practice("");

            // マスタ登録
            int masterId = this.insertMaster(entity, kanrenshaCode);
            // マスタの内容を複写した履歴を登録
            int historyId = this.insertHistory(entity, kanrenshaCode);

            // 両方間違いなく更新できたら結果に残す
            if (masterId != 0 && historyId != 0) {
                list.add(this.createJudge(entity));
            }
        }

        wkTblKanrenshaSeijidantaiMasterResultRepository.saveAllAndFlush(list);
    }

    /* マスタ登録処理を行う */
    private int insertMaster(final WkTblKanrenshaSeijidantaiMasterEntity entityWkTbl, final String kanrenshaCode) {

        // マスタ本体登録
        KanrenshaSeijidantaiMasterEntity masterSeijidantaiEntity = new KanrenshaSeijidantaiMasterEntity();
        BeanUtils.copyProperties(entityWkTbl, masterSeijidantaiEntity);
        masterSeijidantaiEntity.setSeijidantaiKanrenshaCode(kanrenshaCode);
        masterSeijidantaiEntity.setCompareNameText(
                formatNaturalSearchTextUtil.practice(masterSeijidantaiEntity.getKanrenshaName()));
        setTableDataHistoryUtil.practiceInsert(userDto, masterSeijidantaiEntity);
        int masterId = kanrenshaSeijidantaiMasterRepository.save(masterSeijidantaiEntity)
                .getKanrenshaSeijidantaiMasterId();

        // マスタ住所登録
        KanrenshaSeijidantaiAddressEntity addressEntity = new KanrenshaSeijidantaiAddressEntity();
        addressEntity.setSeijidantaiKanrenshaCode(kanrenshaCode);
        addressEntity.setKanrenshaSeijidantaiId(masterId);
        BeanUtils.copyProperties(entityWkTbl, addressEntity);
        // 各住所項目に記載がある場合はチェック対象とする
//        if (!EMPTY.equals(entityWkTbl.getAddressPostal())) {
//            addressEntity.setIsPostalEdit(true);
//            addressEntity.setIsPostalAccept(false);
//        }
//        if (!EMPTY.equals(entityWkTbl.getAddressBlock())) {
//            addressEntity.setIsBlockEdit(true);
//            addressEntity.setIsBlockAccept(false);
//        }
//        if (!EMPTY.equals(entityWkTbl.getAddressBuilding())) {
//            addressEntity.setIsBuildingEdit(true);
//            addressEntity.setIsBuildingAccept(false);
//        }
        setTableDataHistoryUtil.practiceInsert(userDto, addressEntity);
        kanrenshaSeijidantaiAddressRepository.save(addressEntity);

        // マスタ連絡先登録
        KanrenshaSeijidantaiAccessEntity accessEntity = new KanrenshaSeijidantaiAccessEntity();
        accessEntity.setSeijidantaiKanrenshaCode(kanrenshaCode);
        accessEntity.setKanrenshaSeijidantaiId(masterId);
        BeanUtils.copyProperties(entityWkTbl, accessEntity);
        setTableDataHistoryUtil.practiceInsert(userDto, accessEntity);
        kanrenshaSeijidantaiAccessRepository.save(accessEntity);

        // マスタ基本登録
        //MasterSeijidantaiBaseEntity baseEntity = new MasterSeijidantaiBaseEntity();
        //baseEntity.setSeijidantaiKanrenshaCode(kanrenshaCode);
        //baseEntity.setMasterSeijidantaiId(masterId);
        //BeanUtils.copyProperties(entityWkTbl, baseEntity);
        //setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);
        //masterSeijidantaiBaseRepository.save(baseEntity);

        // マスタ(その他)属性登録
        KanrenshaSeijidantaiPropertyEntity propertyEntity = new KanrenshaSeijidantaiPropertyEntity();
        propertyEntity.setSeijidantaiKanrenshaCode(kanrenshaCode);
        propertyEntity.setKanrenshaSeijidantaiId(masterId);
        BeanUtils.copyProperties(entityWkTbl, propertyEntity);
        setTableDataHistoryUtil.practiceInsert(userDto, propertyEntity);
        kanrenshaSeijidantaiPropertyRepository.save(propertyEntity);

        return masterId;
    }

    /* マスタ履歴登録処理を行う */
    private int insertHistory(final WkTblKanrenshaSeijidantaiMasterEntity entityWkTbl, final String kanrenshaCode) {

        KanrenshaSeijidantaiHistoryBaseEntity entity = new KanrenshaSeijidantaiHistoryBaseEntity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setSeijidantaiKanrenshaCode(kanrenshaCode);

        return insertKanrenshaSeijidantaiHistoryService.practice(userDto, entity);
    }

    /* ワークテーブル処理結果Entityを作成する */
    private WkTblKanrenshaSeijidantaiMasterResultEntity createJudge(final WkTblKanrenshaSeijidantaiMasterEntity entityWkTbl) {

        WkTblKanrenshaSeijidantaiMasterResultEntity entity = new WkTblKanrenshaSeijidantaiMasterResultEntity();
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setWkTblKanrenshaSeijidantaiMasterId(entityWkTbl.getWkTblKanrenshaSeijidantaiMasterId());

        return entity;
    }
}
