package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.SeijidantaiDantaiKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaSeijidantaiSameHistoryService;
import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 関連者政治団体CsvからワークテーブルProcessor
 */
@Component
public class KanrenshaSeijidantaiAddMiniCsvProcessor
        implements ItemProcessor<KanrenshaSeijidantaiAddMiniDto, WkTblKanrenshaSeijidantaiAddMinEntity> {

    /** 空文字 */
    private static final String BLANK = "";

    /** 正常登録 */
    private static final String RIGHT = "正)";

    /** 関連者政治団体同属性取得Service */
    @Autowired
    private GetKanrenshaSeijidantaiSameHistoryService getKanrenshaSeijidantaiSameHistoryService;

    /** 関連者政治団体マスタRepository */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    /** 全文検索検索語フォーマットUtil */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblKanrenshaSeijidantaiAddMinEntity process(final KanrenshaSeijidantaiAddMiniDto item) throws Exception {

        WkTblKanrenshaSeijidantaiAddMinEntity entity = new WkTblKanrenshaSeijidantaiAddMinEntity();
        BeanUtils.copyProperties(item, entity);

        return this.check(entity);
    }

    /**
     * チェック処理のみ行う
     *
     * @param entity ワークテーブルEntity
     * @return 処理後Entity
     */
    public WkTblKanrenshaSeijidantaiAddMinEntity check(final WkTblKanrenshaSeijidantaiAddMinEntity entity) {

        StringBuilder stringBuilder = new StringBuilder();
        if (BLANK.equals(entity.getKanrenshaName())) {
            stringBuilder.append("名称が入力されていません;");
        }
        if (BLANK.equals(entity.getAllAddress())) {
            stringBuilder.append("住所が入力されていません;");
        }
        // if (BLANK.equals(entity.getSeijidantaiDelegate())) {
        // stringBuilder.append("代表者が入力されていません;");
        // }
        String dantaiKbn = entity.getDantaiKbn();
        List<String> listDantaiKbn = SeijidantaiDantaiKbnConstants.getList();
        if (BLANK.equals(dantaiKbn)) {
            stringBuilder.append("政治団体区分が入力されていません;");
        } else {
            if (!listDantaiKbn.contains(dantaiKbn)) {
                stringBuilder.append("政治団体区分の値が不正です;");
            }
        }

        // 全く同じ履歴があるかどうか確認する
//        List<KanrenshaSeijidantaiHistoryBaseEntity> listHistory = this.selectSameRirekiList(entity.getKanrenshaName(),
//                entity.getAllAddress(), entity.getSeijidantaiDelegate());
//        if (listHistory.isEmpty()) {
//            if (!entity.getIsAffected()) {
//                // マスタに同名の団体があるかどうか確認する
//                List<KanrenshaSeijidantaiMasterEntity> listMaster = kanrenshaSeijidantaiMasterRepository
//                        .findByCompareNameTextAndIsLatest(formatNaturalSearchTextUtil.practice(entity.getKanrenshaName()),
//                                SetTableDataHistoryUtil.INSERT_STATE);
//                if (!listMaster.isEmpty()) { // SUPPRESS CHECKSTYLE NestedIf
//                    stringBuilder.append("同名の団体があります。確認調査の上、必要に応じて追加してください;");
//                }
//            }
//
//        } else {
//            stringBuilder.append("すでに登録が存在します(").append(listHistory.get(0).getSeijidantaiKanrenshaCode()).append(");");
//        }

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

    /*
     * 同属性リストを取得する
     *
     * @param name 団体名称
     * 
     * @param address 全住所
     * 
     * @param delegate 代表者名
     * 
     * @return 検索結果
     */
    private List<KanrenshaSeijidantaiHistoryBaseEntity> selectSameRirekiList(final String name, final String address,
            final String delegate) {

        return getKanrenshaSeijidantaiSameHistoryService.practice(name, address, delegate);
    }

}
