package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_min;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonAddMinResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonAddMinResultRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.InsertKanrenshaPersonHistoryService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateDokujiCodeForPersonUtil;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業団体マスタ履歴最小登録IterWriter
 */
@Component
public class KanrenshaPersonAddMiniRecordItemWriter extends JpaItemWriter<WkTblKanrenshaPersonAddMinEntity> {

    /** 関連者個人マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /** 関連者個人マスタ履歴処理結果Repository */
    @Autowired
    private WkTblKanrenshaPersonAddMinResultRepository wkTblKanrenshaPersonAddMinResultRepository;

    /** 全文検索用フォーマットUtility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者コード個人用発行Utility */
    @Autowired
    private CreateDokujiCodeForPersonUtil createDokujiCodeForPersonUtil;

    /** 関連者個人履歴登録Service */
    @Autowired
    private InsertKanrenshaPersonHistoryService insertKanrenshaPersonHistoryService;

    /** ユーザ最低限Dto */
    private LeastUserDto userDto;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public KanrenshaPersonAddMiniRecordItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblKanrenshaPersonAddMinEntity> items) {

        final List<WkTblKanrenshaPersonAddMinResultEntity> list = new ArrayList<>();

        // 編集処理
        for (WkTblKanrenshaPersonAddMinEntity entity : items) {

            String kanrenshaCode = createDokujiCodeForPersonUtil.practice("");
            
            int masterId = this.insertMaster(entity, kanrenshaCode);
            int historyId = this.insertHistory(entity, kanrenshaCode);

            // 両方間違いなく更新できたら結果に残す
            if (masterId != 0 && historyId != 0) {
                list.add(this.createResult(entity));
                wkTblKanrenshaPersonAddMinResultRepository.flush();
            }
        }

        wkTblKanrenshaPersonAddMinResultRepository.saveAll(list);
    }

    private int insertMaster(final WkTblKanrenshaPersonAddMinEntity entityWkTbl, final String kanrenshaCode) {

        KanrenshaPersonMasterEntity entity = new KanrenshaPersonMasterEntity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setPersonKanrenshaCode(kanrenshaCode);
        entity.setCompareNameText(formatNaturalSearchTextUtil.practice(entity.getKanrenshaName()));

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonMasterId(0); // auto_increment明示

        return kanrenshaPersonMasterRepository.save(entity).getKanrenshaPersonMasterId();

    }

    private int insertHistory(final WkTblKanrenshaPersonAddMinEntity entityWkTbl, final String kanrenshaCode) {

        KanrenshaPersonHistoryBaseEntity entity = new KanrenshaPersonHistoryBaseEntity();
        entity.setAllName(entityWkTbl.getKanrenshaName());
        entity.setAllAddress(entityWkTbl.getAllAddress());
        entity.setPersonShokugyou(entityWkTbl.getPersonShokugyou());
        entity.setPersonKanrenshaCode(kanrenshaCode);

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示

        return insertKanrenshaPersonHistoryService.practice(userDto, entity);
    }

    private WkTblKanrenshaPersonAddMinResultEntity createResult(final WkTblKanrenshaPersonAddMinEntity entityWkTbl) {
        WkTblKanrenshaPersonAddMinResultEntity entity = new WkTblKanrenshaPersonAddMinResultEntity();
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setWkTblKanrenshaPersonAddMinId(entityWkTbl.getWkTblKanrenshaPersonAddMinId());

        return entity;
    }

}
