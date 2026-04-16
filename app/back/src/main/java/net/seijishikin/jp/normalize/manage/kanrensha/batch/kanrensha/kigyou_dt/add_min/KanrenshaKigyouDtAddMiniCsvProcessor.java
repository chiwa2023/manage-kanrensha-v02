package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaKigyouDtSameHistoryService;
import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業・団体CsvからワークテーブルProcessor
 */
@Component
public class KanrenshaKigyouDtAddMiniCsvProcessor
        implements ItemProcessor<KanrenshaKigyouDtAddMiniDto, WkTblKanrenshaKigyouDtAddMinEntity> {

    /** 空文字 */
    private static final String BLANK = "";

    /** 正常登録 */
    private static final String RIGHT = "正)";
    /** 法人番号正規表現 */
    private final Pattern patternHoujinNo = Pattern.compile("[0-9]{13}");

    /** 関連者企業・団体同属性取得Service */
    @Autowired
    private GetKanrenshaKigyouDtSameHistoryService getKanrenshaKigyouDtSameHistoryService;

    /** 関連者企業・団体マスタRepository */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    /** 全文検索検索語フォーマットUtil */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblKanrenshaKigyouDtAddMinEntity process(final KanrenshaKigyouDtAddMiniDto item) throws Exception {

        WkTblKanrenshaKigyouDtAddMinEntity entity = new WkTblKanrenshaKigyouDtAddMinEntity();
        BeanUtils.copyProperties(item, entity);

        return this.check(entity);
    }

    /**
     * チェック処理のみ行う
     *
     * @param entity ワークテーブルEntity
     * @return 処理後Entity
     */
    public WkTblKanrenshaKigyouDtAddMinEntity check(final WkTblKanrenshaKigyouDtAddMinEntity entity) {

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
        String houjinNo = entity.getHoujinNo();
        if (BLANK.equals(houjinNo)) {
            stringBuilder.append("法人番号が入力されていません;");
        } else {
            Matcher matcher = patternHoujinNo.matcher(houjinNo);
            if (!matcher.find()) {
                stringBuilder.append("法人番号の形式ではありません(数字13桁);");
            }
        }

        // 全く同じ履歴があるかどうか確認する
        List<KanrenshaKigyouDtHistoryBaseEntity> listHistory = this.selectSameRirekiList(entity.getKanrenshaName(),
                entity.getAllAddress(), entity.getKigyouDtDelegate());
        if (listHistory.isEmpty()) {
            if (!entity.getIsAffected()) {
                // マスタに同名の団体があるかどうか確認する
                List<KanrenshaKigyouDtMasterEntity> listMaster = kanrenshaKigyouDtMasterRepository
                        .findByCompareNameTextAndIsLatest(
                                formatNaturalSearchTextUtil.practice(entity.getKanrenshaName()),
                                SetTableDataHistoryUtil.INSERT_STATE);
                if (!listMaster.isEmpty()) { // SUPPRESS CHECKSTYLE NestedIf
                    stringBuilder.append("同名の団体があります。確認調査の上、必要に応じて追加してください;");
                }
            }
        } else {
            stringBuilder.append("すでに登録が存在します(").append(listHistory.get(0).getKigyouDtKanrenshaCode()).append(");");
        }

        // 入力に問題がある場合は記録だけ残して処理中断
        if (stringBuilder.isEmpty()) {
            entity.setIsAffected(true);
            entity.setIsFinish(false);
            entity.setJudgeReason(RIGHT);
        } else {
            entity.setIsAffected(false);
            entity.setJudgeReason(stringBuilder.toString());
            entity.setIsFinish(false);
        }

        return entity;
    }

    /**
     * 同一履歴を取得する
     * 
     * @param name     名称
     * @param address  住所
     * @param delegate 団体代表者
     * @return 取得できたリスト
     */
    private List<KanrenshaKigyouDtHistoryBaseEntity> selectSameRirekiList(final String name, final String address,
            final String delegate) {

        return getKanrenshaKigyouDtSameHistoryService.practice(name, address, delegate);
    }

}
