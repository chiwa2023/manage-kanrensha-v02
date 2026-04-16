package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiAddMinResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiAddMinResultRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.InsertKanrenshaSeijidantaiHistoryService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateDokujiCodeForSeijidantaiUtil;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業団体マスタ履歴最小登録IterWriter
 */
@Component
public class KanrenshaSeijidantaiAddMiniRecordItemWriter extends JpaItemWriter<WkTblKanrenshaSeijidantaiAddMinEntity> {

    /** 関連者政治団体マスタRepository */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    /** 関連者政治団体マスタ履歴処理結果Repository */
    @Autowired
    private WkTblKanrenshaSeijidantaiAddMinResultRepository wkTblKanrenshaSeijidantaiAddMinResultRepository;

    /** 全文検索用フォーマットUtility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者コード政治団体用発行Utility */
    @Autowired
    private CreateDokujiCodeForSeijidantaiUtil createDokujiCodeForSeijidantaiUtil;

    /** 関連者個人履歴登録Service */
    @Autowired
    private InsertKanrenshaSeijidantaiHistoryService insertKanrenshaSeijidantaiHistoryService;

    /** ユーザ最低限Dto */
    private LeastUserDto userDto;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public KanrenshaSeijidantaiAddMiniRecordItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblKanrenshaSeijidantaiAddMinEntity> items) {

        final List<WkTblKanrenshaSeijidantaiAddMinResultEntity> list = new ArrayList<>();

        // 編集処理
        for (WkTblKanrenshaSeijidantaiAddMinEntity entity : items) {

            String kanrenshaCode = createDokujiCodeForSeijidantaiUtil.practice(entity.getPoliOrgNo());

            int masterId = this.insertMaster(entity, kanrenshaCode);
            int historyId = this.insertHistory(entity, kanrenshaCode);

            // 両方間違いなく更新できたら結果に残す
            if (masterId != 0 && historyId != 0) {
                list.add(this.createResult(entity));
                wkTblKanrenshaSeijidantaiAddMinResultRepository.flush();
            }
        }

        wkTblKanrenshaSeijidantaiAddMinResultRepository.saveAll(list);
    }

    private int insertMaster(final WkTblKanrenshaSeijidantaiAddMinEntity entityWkTbl, final String kanrenshaCode) {

        KanrenshaSeijidantaiMasterEntity entity = new KanrenshaSeijidantaiMasterEntity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setSeijidantaiKanrenshaCode(kanrenshaCode);
        entity.setCompareNameText(formatNaturalSearchTextUtil.practice(entity.getKanrenshaName()));
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiMasterId(0); // auto_increment明示

        return kanrenshaSeijidantaiMasterRepository.save(entity).getKanrenshaSeijidantaiMasterId();

    }

    private int insertHistory(final WkTblKanrenshaSeijidantaiAddMinEntity entityWkTbl, final String kanrenshaCode) {

        KanrenshaSeijidantaiHistoryBaseEntity entity = new KanrenshaSeijidantaiHistoryBaseEntity();
        entity.setAllName(entityWkTbl.getKanrenshaName());
        entity.setAllAddress(entityWkTbl.getAllAddress());
        entity.setOrgDelegateName(entityWkTbl.getSeijidantaiDelegate());
        entity.setSeijidantaiKanrenshaCode(kanrenshaCode);

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示

        return insertKanrenshaSeijidantaiHistoryService.practice(userDto, entity);

    }

    private WkTblKanrenshaSeijidantaiAddMinResultEntity createResult(
            final WkTblKanrenshaSeijidantaiAddMinEntity entityWkTbl) {
        WkTblKanrenshaSeijidantaiAddMinResultEntity entity = new WkTblKanrenshaSeijidantaiAddMinResultEntity();
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setWkTblKanrenshaSeijidantaiAddMinId(entityWkTbl.getWkTblKanrenshaSeijidantaiAddMinId());

        return entity;
    }

}
