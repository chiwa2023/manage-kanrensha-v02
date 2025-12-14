package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.history;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 関連者政治団体CsvからワークテーブルProcessor
 */
@Component
public class KanrenshaSeijidantaiHistoryProcessor
        implements ItemProcessor<KanrenshaSeijidantaiHistoryDto, WkTblKanrenshaSeijidantaiHistoryEntity> {

    /** 空白 */
    private static final String BLANK = "";

    /** 正常登録 */
    private static final String RIGHT = "正)";

    /** 関連者政治団体マスタRepository */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    /** 自然検索フォーマットUtil */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblKanrenshaSeijidantaiHistoryEntity process(final KanrenshaSeijidantaiHistoryDto item) throws Exception {

        WkTblKanrenshaSeijidantaiHistoryEntity entity = new WkTblKanrenshaSeijidantaiHistoryEntity();
        BeanUtils.copyProperties(item, entity);

        return this.check(entity);
    }

    /**
     * チェック処理のみ行う
     *
     * @param entity ワークテーブルEnity
     * @return チェック処理
     */
    public WkTblKanrenshaSeijidantaiHistoryEntity check(final WkTblKanrenshaSeijidantaiHistoryEntity entity) {

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

    private StringBuilder createCheckMessage(final WkTblKanrenshaSeijidantaiHistoryEntity entity) {

        // どれかの値が入力していなければ情報不足として処理対象外
        StringBuilder stringBuilder = new StringBuilder();
        if (BLANK.equals(entity.getKanrenshaName())) {
            stringBuilder.append("名称が入力されていません;");
        }
        if (BLANK.equals(entity.getAllAddress())) {
            stringBuilder.append("住所が入力されていません;");
        }
        // if (BLANK.equals(item.getSeijidantaiDelegate())) {
        // stringBuilder.append("代表者が入力されていません;");
        // }
        if (BLANK.equals(entity.getSeijidantaiKanrenshaCode())) {
            stringBuilder.append("関連者コードが入力されていません;");
        }

        if (stringBuilder.isEmpty()) {
            // 少なくとも団体名と関連者コードが同一でない場合は未登録とみなす
            List<KanrenshaSeijidantaiMasterEntity> listMaster = kanrenshaSeijidantaiMasterRepository
                    .findBySeijidantaiKanrenshaCodeAndCompareNameTextAndIsLatest(entity.getSeijidantaiKanrenshaCode(),
                            formatNaturalSearchTextUtil.practice(entity.getKanrenshaName()),
                            SetTableDataHistoryUtil.INSERT_STATE);
            if (!listMaster.isEmpty()) {
                stringBuilder.append("コードと名称に合致する関連者が存在します;");
            }
        }

        return stringBuilder;
    }

}
