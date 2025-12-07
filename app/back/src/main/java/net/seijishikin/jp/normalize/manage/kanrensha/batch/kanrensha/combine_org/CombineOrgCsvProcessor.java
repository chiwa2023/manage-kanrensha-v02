package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org;

import java.util.Optional;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 個人団体紐づけCsvからワークテーブルProcessor
 */
@Component
public class CombineOrgCsvProcessor
        implements ItemProcessor<KanrenshaCombineOrgDto, WkTblKanrenshaCombineOrgEntity>, StepExecutionListener {

    /** 関連者個人マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /** 関連者企業団体マスタRepository */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    /** 関連者政治団体マスタRepository */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /** 登録年区切り文字 */
    public static final String YEAR_SPLITER = ":";

    /** 空文字 */
    private static final String BLANK = "";

    /** 正常登録 */
    private static final String RIGHT = "正)";

    /** 関連者区分 */
    private Short kanrenshaKbn;
    /** 最小登録年 */
    private Short yearMin;
    /** 最大登録年 */
    private Short yearMax;

    /**
     * 実行前処理(Csv処理の時のみ関連者区分を設定する)
     */
    @BeforeStep
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        kanrenshaKbn = Short.valueOf(stepExecution.getJobParameters().getString("kanrenshaKbn"));
        yearMin = Short.valueOf(stepExecution.getJobParameters().getString("yearMin"));
        yearMax = Short.valueOf(stepExecution.getJobParameters().getString("yearMax"));
    }

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblKanrenshaCombineOrgEntity process(final KanrenshaCombineOrgDto item) throws Exception {

        WkTblKanrenshaCombineOrgEntity entity = new WkTblKanrenshaCombineOrgEntity();
        BeanUtils.copyProperties(item, entity);
        entity.setKanrenshaKbn(kanrenshaKbn);
        return this.check(entity, yearMin, yearMax);
    }

    /**
     * チェック処理のみ行う
     *
     * @param entity ワークテーブルEntity
     * @return 処理後Entity
     */
    public WkTblKanrenshaCombineOrgEntity check(final WkTblKanrenshaCombineOrgEntity entity, final Short yMin,
            final Short yMax) {

        StringBuilder stringBuilder = new StringBuilder();

        // 未入力チェック
        this.checkSetValue(stringBuilder, entity);

        // 登録年チェック(front側で生成した場合はチェックをスキップ)
        if (BLANK.equals(entity.getYearArrayText())) {
            if (this.checkSetYear(stringBuilder, entity, yMin, yMax)) {
                short start = entity.getStartYear();
                short end = entity.getEndYear();

                StringBuilder builder = new StringBuilder();
                for (short year = start; year <= end; year++) {
                    builder.append(year).append(YEAR_SPLITER);
                }

                if (builder.isEmpty()) { // SUPPRESS CHECKSTYLE IfNest
                    stringBuilder.append("終了年より開始年が大きい値です;");
                } else {
                    String text = builder.toString();
                    entity.setYearArrayText(text.substring(0, text.length() - 1));
                }
            }

        } else {
            entity.setYearArrayText(BLANK); // 最初の指定時はよかったのに、編集時にダメにした場合の対策
        }
        
        // 作成予定の各年のテーブルについてチェックを行う
        if (!BLANK.equals(entity.getYearArrayText())) {
            String conditon = this.createCondition(entity);
            for (String year : entity.getYearArrayText().split(YEAR_SPLITER)) {
                String table = "kanrensha_combine_org_" + year;
                String sql = "SELECT COUNT(*) FROM " + table + " WHERE is_latest =1 AND " + conditon;
                Query query = entityManager.createNativeQuery(sql, Integer.class);
                Integer count = (Integer) query.getResultList().get(0);

                if (0 < count) {
                    stringBuilder.append("すでに登録があります(").append(year).append(")年;");
                }
            }

        }

        // コード存在チェック
        this.checkExistCode(stringBuilder, entity);

        // 入力に問題がある場合は記録だけ残して処理中断
        if (stringBuilder.isEmpty()) {
            entity.setIsAffected(true);
            entity.setJudgeReason(RIGHT);
            entity.setIsFinish(false);
        } else {
            entity.setIsAffected(false);
            entity.setJudgeReason(stringBuilder.toString());
            entity.setIsFinish(false);
        }

        return entity;
    }

    private void checkSetValue(final StringBuilder stringBuilder, final WkTblKanrenshaCombineOrgEntity entity) {
        if (BLANK.equals(entity.getPersonKanrenshaCode())) {
            stringBuilder.append("個人関連者コードが入力されていません;");
        }
        if (BLANK.equals(entity.getPersonName())) {
            stringBuilder.append("個人姓名が入力されていません;");
        }
        if (BLANK.equals(entity.getOrgKanrenshaCode())) {
            stringBuilder.append("団体関連者コードが入力されていません;");
        }
        if (BLANK.equals(entity.getOrgName())) {
            stringBuilder.append("団体名称が入力されていません;");
        }

    }

    private boolean checkSetYear(final StringBuilder stringBuilder, final WkTblKanrenshaCombineOrgEntity entity,
            final Short yMin, final Short yMax) {

        final Short notSetyear = Short.valueOf("-1");
        boolean isRecord = true;
        if (notSetyear.equals(entity.getStartYear())) {
            stringBuilder.append("登録開始年が正常に指定されませんでした;");
            isRecord = false;
        } else {
            if (yMin > entity.getStartYear() || yMax < entity.getStartYear()) {
                stringBuilder.append("登録開始年がシステム登録可能年の範囲でありません;");
                isRecord = false;
            }
        }
        if (notSetyear.equals(entity.getEndYear())) {
            stringBuilder.append("登録終了年が正常に指定されませんでした;");
            isRecord = false;
        } else {
            if (yMin > entity.getEndYear() || yMax < entity.getEndYear()) {
                stringBuilder.append("登録終了年がシステム登録可能年の範囲でありません;");
                isRecord = false;
            }
        }

        return isRecord;

    }

    private void checkExistCode(final StringBuilder stringBuilder, final WkTblKanrenshaCombineOrgEntity entity) {

//        // 個人コード存在確認(最初の1件を呼んでいるようだが実質1件しか存在しない運用をする)
//        Optional<KanrenshaPersonMasterEntity> optionalPerson = kanrenshaPersonMasterRepository.findFirstByPersonKanrenshaCodeAndIsLatest(
//                entity.getPersonKanrenshaCode(), SetTableDataHistoryUtil.INSERT_STATE);
//        if (optionalPerson.isEmpty()) {
//            stringBuilder.append("指定された個人関連者コードが存在しません;");
//        }
//
//        // 紐づけ団体が企業団体の場合
//        if (KanrenshaKbnConstants.KIGYOU_DT== entity.getKanrenshaKbn()) { 
//            Optional<KanrenshaKigyouDtMasterEntity> optionalKigyouDt = kanrenshaKigyouDtMasterRepository
//                    .findFirstByKigyouDtKanrenshaCodeAndIsLatest(entity.getOrgKanrenshaCode(),
//                            SetTableDataHistoryUtil.INSERT_STATE);
//            if (optionalKigyouDt.isEmpty()) {
//                stringBuilder.append("指定された企業／団体関連者コードが存在しません;");
//            }
//        }
//
//        // 紐づけ団体が政治団体の場合
//        if (KanrenshaKbnConstants.SEIJIDANTAI == entity.getKanrenshaKbn()) {
//            Optional<KanrenshaSeijidantaiMasterEntity> optionalSeijidantai = kanrenshaSeijidantaiMasterRepository
//                    .findFirstBySeijidantaiKanrenshaCodeAndIsLatest(entity.getOrgKanrenshaCode(),
//                            SetTableDataHistoryUtil.INSERT_STATE);
//            if (optionalSeijidantai.isEmpty()) {
//                stringBuilder.append("指定された政治団体団体関連者コードが存在しません;");
//            }
//        }

    }

    private String createCondition(final WkTblKanrenshaCombineOrgEntity entity) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("kanrensha_kbn='").append(entity.getKanrenshaKbn()) //
                .append("' AND person_kanrensha_code='").append(entity.getPersonKanrenshaCode()) //
                .append("' AND org_kanrensha_code='").append(entity.getOrgKanrenshaCode()).append('\'');

        return stringBuilder.toString();
    }

}
