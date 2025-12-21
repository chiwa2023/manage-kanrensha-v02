package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.xml; // NOPMD

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlResultEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlResultRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.InsertKanrenshaKigyouDtHistoryService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.InsertKanrenshaPersonHistoryService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.InsertKanrenshaSeijidantaiHistoryService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateDokujiCodeForKigyouDtUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateDokujiCodeForPersonUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateDokujiCodeForSeijidantaiUtil;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業団体マスタ履歴最小登録IterWriter
 */
@Component
public class KanrenshaByXmlMinRecordItemWriter extends JpaItemWriter<WkTblMasterAllByXmlEntity> {

    /** 関連者個人マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /** 関連者企業・団体マスタRepository */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    /** 関連者政治団体マスタRepository */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    /** XMl登録最小マスタ判定Repository */
    @Autowired
    private WkTblMasterAllByXmlResultRepository wkTblMasterAllByXmlResultRepository;

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
    private CreateDokujiCodeForPersonUtil createDokujiCodeForPersonUtil;

    /** 関連者コード政治団体用発行Utility */
    @Autowired
    private CreateDokujiCodeForKigyouDtUtil createDokujiCodeForKigyouDtUtil;

    /** 関連者コード政治団体用発行Utility */
    @Autowired
    private CreateDokujiCodeForSeijidantaiUtil createDokujiCodeForSeijidantaiUtil;

    /** ユーザ最低限Dto */
    private LeastUserDto userDto;

    /** 関連者企業団体履歴挿入Service */
    @Autowired
    private InsertKanrenshaKigyouDtHistoryService insertKanrenshaKigyouDtHistoryService;

    /** 関連者個人履歴登録Service */
    @Autowired
    private InsertKanrenshaPersonHistoryService insertKanrenshaPersonHistoryService;

    /** 関連者個人履歴登録Service */
    @Autowired
    private InsertKanrenshaSeijidantaiHistoryService insertKanrenshaSeijidantaiHistoryService;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public KanrenshaByXmlMinRecordItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblMasterAllByXmlEntity> items) {

        final List<WkTblMasterAllByXmlResultEntity> list = new ArrayList<>();

        // 編集処理
        for (WkTblMasterAllByXmlEntity entity : items) {

            int masterId = 0;
            int historyId = 0;
            String kanrenshaCode;
            switch (entity.getKanrenshaKbn()) {
                case 1:
                    // 個人登録
                    kanrenshaCode = createDokujiCodeForPersonUtil.practice("");

                    masterId = this.insertMasterPerson(entity, kanrenshaCode);
                    historyId = this.insertHistoryPerson(entity, kanrenshaCode);
                    break;

                case 2:
                    // 企業団体登録
                    kanrenshaCode = createDokujiCodeForKigyouDtUtil.practice(entity.getHoujinNo());

                    masterId = this.insertMasterKigyouDt(entity, kanrenshaCode);
                    historyId = this.insertHistoryKigyouDt(entity, kanrenshaCode);
                    break;

                case 3: // SUPPRESS CHECKSTYLE MagicNumber
                    // 政治団体登録
                    kanrenshaCode = createDokujiCodeForSeijidantaiUtil.practice(entity.getPoliOrgNo());

                    masterId = this.insertMasterSeijidantai(entity, kanrenshaCode);
                    historyId = this.insertHistorySeijidantai(entity, kanrenshaCode);
                    break;

                default:
                    throw new IllegalArgumentException("Unexpected value: " + entity.getKanrenshaKbn());
            }

            // 両方間違いなく更新できたら結果に残す
            if (masterId != 0 && historyId != 0) {
                list.add(this.createResult(entity));
                wkTblMasterAllByXmlResultRepository.flush();
            }
        }

        wkTblMasterAllByXmlResultRepository.saveAll(list);
    }

    private int insertMasterKigyouDt(final WkTblMasterAllByXmlEntity entityWkTbl, final String kanrenshaCode) {

        KanrenshaKigyouDtMasterEntity entity = new KanrenshaKigyouDtMasterEntity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setKigyouDtKanrenshaCode(kanrenshaCode);
        entity.setKigyouDtDelegate(entityWkTbl.getOrgDelegate());

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtMasterId(0); // auto_increment明示

        return kanrenshaKigyouDtMasterRepository.save(entity).getKanrenshaKigyouDtMasterId();

    }

    private int insertHistoryKigyouDt(final WkTblMasterAllByXmlEntity entityWkTbl, final String kanrenshaCode) {

        KanrenshaKigyouDtHistoryBaseEntity entity = new KanrenshaKigyouDtHistoryBaseEntity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setAllName(entityWkTbl.getKanrenshaName());
        entity.setKigyouDtKanrenshaCode(kanrenshaCode);
        entity.setOrgDelegateName(entityWkTbl.getOrgDelegate());

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示

        // 検索テキストはServiceで設定
        return insertKanrenshaKigyouDtHistoryService.practice(userDto, entity);

    }

    private int insertMasterPerson(final WkTblMasterAllByXmlEntity entityWkTbl, final String kanrenshaCode) {

        KanrenshaPersonMasterEntity entity = new KanrenshaPersonMasterEntity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setPersonKanrenshaCode(kanrenshaCode);
        entity.setCompareNameText(formatNaturalSearchTextUtil.practice(entity.getKanrenshaName()));

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonMasterId(0); // auto_increment明示

        return kanrenshaPersonMasterRepository.save(entity).getKanrenshaPersonMasterId();

    }

    private int insertHistoryPerson(final WkTblMasterAllByXmlEntity entityWkTbl, final String kanrenshaCode) {

        KanrenshaPersonHistoryBaseEntity entity = new KanrenshaPersonHistoryBaseEntity();
        entity.setAllName(entityWkTbl.getKanrenshaName());
        entity.setAllAddress(entityWkTbl.getAllAddress());
        entity.setPersonShokugyou(entityWkTbl.getPersonShokugyou());
        entity.setPersonKanrenshaCode(kanrenshaCode);

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示

        return insertKanrenshaPersonHistoryService.practice(userDto, entity);

    }

    private int insertMasterSeijidantai(final WkTblMasterAllByXmlEntity entityWkTbl, final String kanrenshaCode) {

        KanrenshaSeijidantaiMasterEntity entity = new KanrenshaSeijidantaiMasterEntity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setSeijidantaiKanrenshaCode(kanrenshaCode);
        entity.setSeijidantaiDelegate(entityWkTbl.getOrgDelegate());

        entity.setCompareNameText(formatNaturalSearchTextUtil.practice(entity.getKanrenshaName()));

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiMasterId(0); // auto_increment明示

        return kanrenshaSeijidantaiMasterRepository.save(entity).getKanrenshaSeijidantaiMasterId();

    }

    private int insertHistorySeijidantai(final WkTblMasterAllByXmlEntity entityWkTbl, final String kanrenshaCode) {

        KanrenshaSeijidantaiHistoryBaseEntity entity = new KanrenshaSeijidantaiHistoryBaseEntity();
        entity.setAllName(entityWkTbl.getKanrenshaName());
        entity.setAllAddress(entityWkTbl.getAllAddress());
        entity.setOrgDelegateName(entityWkTbl.getOrgDelegate());
        entity.setSeijidantaiKanrenshaCode(kanrenshaCode);

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示

        return insertKanrenshaSeijidantaiHistoryService.practice(userDto, entity);

    }

    private WkTblMasterAllByXmlResultEntity createResult(final WkTblMasterAllByXmlEntity entityWkTbl) {
        WkTblMasterAllByXmlResultEntity entity = new WkTblMasterAllByXmlResultEntity();
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setIsAffected(true);
        entity.setWkTblMasterAllByXmlId(entityWkTbl.getWkTblMasterAllByXmlId());

        return entity;
    }

}
