package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.history;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人CsvからワークテーブルProcessor
 */
@Component
public class KanrenshaPersonHistoryProcessor
        implements ItemProcessor<KanrenshaPersonHistoryDto, WkTblKanrenshaPersonHistoryEntity> {

    /** 空白 */
    private static final String BLANK = "";

    /** 正常登録 */
    private static final String RIGHT = "正)";

    /** 関連者個人マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /** 自然検索フォーマットUtil */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblKanrenshaPersonHistoryEntity process(final KanrenshaPersonHistoryDto item) throws Exception {

        WkTblKanrenshaPersonHistoryEntity entity = new WkTblKanrenshaPersonHistoryEntity();

        BeanUtils.copyProperties(item, entity);
        entity.setWkKanrenshaPersonHistoryId(0); // auto_increment明示

        return this.check(entity);
    }

    /**
     * チェック処理のみ行う
     *
     * @param entity ワークテーブルEnity
     * @return チェック処理
     */
    public WkTblKanrenshaPersonHistoryEntity check(final WkTblKanrenshaPersonHistoryEntity entity) {

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

    private StringBuilder createCheckMessage(final WkTblKanrenshaPersonHistoryEntity entity) {

        // どれかの値が入力していなければ情報不足として処理対象外
        StringBuilder stringBuilder = new StringBuilder();
        if (BLANK.equals(entity.getKanrenshaName())) {
            stringBuilder.append("名称が入力されていません;");
        }
        if (BLANK.equals(entity.getAllAddress())) {
            stringBuilder.append("住所が入力されていません;");
        }
        // if (BLANK.equals(item.getPersonShokugyou())) {
        // stringBuilder.append("職業が入力されていません;");
        // }
        if (BLANK.equals(entity.getPersonKanrenshaCode())) {
            stringBuilder.append("関連者コードが入力されていません;");
        }

//        if (stringBuilder.isEmpty()) {
//            // 少なくとも団体名と関連者コードが同一でない場合は未登録とみなす
//            List<KanrenshaPersonMasterEntity> listMaster = kanrenshaPersonMasterRepository
//                    .findByPersonKanrenshaCodeAndCompareNameTextAndIsLatest(entity.getPersonKanrenshaCode(),
//                            formatNaturalSearchTextUtil.practice(entity.getKanrenshaName()),
//                            SetTableDataHistoryUtil.INSERT_STATE);
//            if (listMaster.isEmpty()) {
//                stringBuilder.append("コードと名称に合致する関連者が存在しません;");
//            }
//        }

        return stringBuilder;
    }

}
