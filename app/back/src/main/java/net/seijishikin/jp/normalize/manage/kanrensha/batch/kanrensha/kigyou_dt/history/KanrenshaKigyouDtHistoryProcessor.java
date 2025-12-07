package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.history;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業・団体CsvからワークテーブルProcessor
 */
@Component
public class KanrenshaKigyouDtHistoryProcessor
        implements ItemProcessor<KanrenshaKigyouDtHistoryDto, WkTblKanrenshaKigyouDtHistoryEntity> {

    /** 空白 */
    private static final String BLANK = "";

    /** 正常登録 */
    private static final String RIGHT = "正)";

    /** 関連者企業・団体マスタRepository */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    /** 自然検索フォーマットUtil */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblKanrenshaKigyouDtHistoryEntity process(final KanrenshaKigyouDtHistoryDto item) throws Exception {

        WkTblKanrenshaKigyouDtHistoryEntity entity = new WkTblKanrenshaKigyouDtHistoryEntity();
        BeanUtils.copyProperties(item, entity);

        return this.check(entity);
    }

    /**
     * チェック処理のみ行う
     *
     * @param entity ワークテーブルEnity
     * @return チェック処理
     */
    public WkTblKanrenshaKigyouDtHistoryEntity check(final WkTblKanrenshaKigyouDtHistoryEntity entity) {

        StringBuilder stringBuilder = this.createCheckMessage(entity);

        if (stringBuilder.isEmpty()) {
            entity.setIsAffected(true);
            entity.setJudgeReason(RIGHT);
        } else {
            // 何らかの未登録メッセージが入っている場合は判定対象外を登録
            entity.setIsAffected(false);
            entity.setJudgeReason(stringBuilder.toString());
        }

        return entity;
    }

    private StringBuilder createCheckMessage(final WkTblKanrenshaKigyouDtHistoryEntity entity) {

        // どれかの値が入力していなければ情報不足として処理対象外
        StringBuilder stringBuilder = new StringBuilder();
        if (BLANK.equals(entity.getKanrenshaName())) {
            stringBuilder.append("名称が入力されていません;");
        }
        if (BLANK.equals(entity.getAllAddress())) {
            stringBuilder.append("住所が入力されていません;");
        }
        // if (BLANK.equals(item.getKigyouDtDelegate())) {
        // stringBuilder.append("代表者が入力されていません;");
        // }
        if (BLANK.equals(entity.getKigyouDtKanrenshaCode())) {
            stringBuilder.append("関連者コードが入力されていません;");
        }

//        if (stringBuilder.isEmpty()) {
//            // 少なくとも団体名と関連者コードが同一でない場合は未登録とみなす
//            List<KanrenshaKigyouDtMasterEntity> listMaster = kanrenshaKigyouDtMasterRepository
//                    .findByKigyouDtKanrenshaCodeAndCompareNameTextAndIsLatest(entity.getKigyouDtKanrenshaCode(),
//                            formatNaturalSearchTextUtil.practice(entity.getKanrenshaName()),
//                            SetTableDataHistoryUtil.INSERT_STATE);
//            if (listMaster.isEmpty()) {
//                stringBuilder.append("コードと名称に合致する関連者が存在しません;");
//            }
//        }

        return stringBuilder;
    }

}
