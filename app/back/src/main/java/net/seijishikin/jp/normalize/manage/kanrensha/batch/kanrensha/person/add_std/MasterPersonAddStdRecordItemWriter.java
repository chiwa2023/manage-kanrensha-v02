package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_std; // NOPMD

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonMasterResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonMasterResultRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.InsertKanrenshaPersonHistoryService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateDokujiCodeForPersonUtil;

/**
 * 関連者個人標準登録マスタ複写ItemWriter
 */
@Component
public class MasterPersonAddStdRecordItemWriter extends JpaItemWriter<WkTblKanrenshaPersonMasterEntity> {

    /** 関連者個人マスタ標準判定結果Repository */
    @Autowired
    private WkTblKanrenshaPersonMasterResultRepository wkTblKanrenshaPersonMasterResultRepository;

    /** 関連者個人履歴登録Service */
    @Autowired
    private InsertKanrenshaPersonHistoryService insertKanrenshaPersonHistoryService;

    /** 関連者個人マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository masterPersonRepository;

    /** 関連者個人マスタ住所Repository */
    @Autowired
    private KanrenshaPersonAddressRepository masterPersonaddAddressRepository;

    /** 関連者個人マスタ住所Repository */
    @Autowired
    private KanrenshaPersonAccessRepository masterPersonAccessRepository;

    /** 関連者個人マスタ(その他属性)Repository */
    @Autowired
    private KanrenshaPersonPropertyRepository masterPersonPropertyRepository;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** 全文自然検索整形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者コード個人用発行Utility */
    @Autowired
    private CreateDokujiCodeForPersonUtil createDokujiCodeForPersonUtil;

    /** ユーザ最低限Dto */
    private LeastUserDto userDto;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public MasterPersonAddStdRecordItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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

        final List<WkTblKanrenshaPersonMasterResultEntity> list = new ArrayList<>();

        // 編集処理
        for (WkTblKanrenshaPersonMasterEntity entity : items) {

            // 関連者コードを設定
            String kanrenshaCode = createDokujiCodeForPersonUtil.practice("");

            // マスタ登録
            int masterId = this.insertMaster(entity, kanrenshaCode);
            // マスタの内容を複写した履歴を登録
            int historyId = this.insertHistory(entity, kanrenshaCode);

            // 両方間違いなく更新できたら結果に残す
            if (masterId != 0 && historyId != 0) {
                list.add(this.createJudge(entity));
            }
        }

        wkTblKanrenshaPersonMasterResultRepository.saveAll(list);
    }

    /* マスタ登録処理を行う */
    private int insertMaster(final WkTblKanrenshaPersonMasterEntity entityWkTbl, final String kanrenshaCode) {

        // マスタ本体登録
        KanrenshaPersonMasterEntity masterPersonEntity = new KanrenshaPersonMasterEntity();
        BeanUtils.copyProperties(entityWkTbl, masterPersonEntity);
        masterPersonEntity.setPersonKanrenshaCode(kanrenshaCode);
        setTableDataHistoryUtil.practiceInsert(userDto, masterPersonEntity);
        masterPersonEntity
                .setCompareNameText(formatNaturalSearchTextUtil.practice(masterPersonEntity.getKanrenshaName()));
        int masterId = masterPersonRepository.save(masterPersonEntity).getKanrenshaPersonMasterId();

        // マスタ住所登録
        KanrenshaPersonAddressEntity addressEntity = new KanrenshaPersonAddressEntity();
        addressEntity.setPersonKanrenshaCode(kanrenshaCode);
        addressEntity.setKanrenshaPersonId(masterId);
        BeanUtils.copyProperties(entityWkTbl, addressEntity);

        // TODO 住所編集フラグはサイト独自形式になっていることが周知されたらcsvにフラグで出す
        // 承諾は住所整形済、、整形済でないにかかわらず承諾なし
        boolean isEdit = !entityWkTbl.getIsJhushoFormat();
        addressEntity.setIsPostalEdit(isEdit);
        addressEntity.setIsBlockEdit(isEdit);
        addressEntity.setIsBuildingEdit(isEdit);

        setTableDataHistoryUtil.practiceInsert(userDto, addressEntity);
        masterPersonaddAddressRepository.save(addressEntity);

        // マスタ連絡先登録
        KanrenshaPersonAccessEntity accessEntity = new KanrenshaPersonAccessEntity();
        accessEntity.setPersonKanrenshaCode(kanrenshaCode);
        accessEntity.setKanrenshaPersonId(masterId);
        BeanUtils.copyProperties(entityWkTbl, accessEntity);
        setTableDataHistoryUtil.practiceInsert(userDto, accessEntity);
        masterPersonAccessRepository.save(accessEntity);

        // マスタ属性登録
        KanrenshaPersonPropertyEntity propertyEntity = new KanrenshaPersonPropertyEntity();
        propertyEntity.setPersonKanrenshaCode(kanrenshaCode);
        propertyEntity.setKanrenshaPersonId(masterId);
        BeanUtils.copyProperties(entityWkTbl, propertyEntity);
        propertyEntity.setIsShokyouEdit(true);
        setTableDataHistoryUtil.practiceInsert(userDto, propertyEntity);
        masterPersonPropertyRepository.save(propertyEntity);

        return masterId;
    }

    /* マスタ履歴登録処理を行う */
    private int insertHistory(final WkTblKanrenshaPersonMasterEntity entityWkTbl, final String kanrenshaCode) {

        KanrenshaPersonHistoryBaseEntity entity = new KanrenshaPersonHistoryBaseEntity();
        entity.setAllName(entityWkTbl.getKanrenshaName());
        entity.setAllAddress(entityWkTbl.getAllAddress());
        entity.setPersonShokugyou(entityWkTbl.getPersonShokugyou());

        entity.setPersonKanrenshaCode(kanrenshaCode);

        return insertKanrenshaPersonHistoryService.practice(userDto, entity);
    }

    /* ワークテーブル処理結果Entityを作成する */
    private WkTblKanrenshaPersonMasterResultEntity createJudge(final WkTblKanrenshaPersonMasterEntity entityWkTbl) {

        WkTblKanrenshaPersonMasterResultEntity entity = new WkTblKanrenshaPersonMasterResultEntity();
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setWkTblKanrenshaPersonMasterId(entityWkTbl.getWkTblKanrenshaPersonMasterId());

        return entity;
    }
}
