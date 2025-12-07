package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_min;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaPersonSameHistoryService;
import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人CsvからワークテーブルProcessor
 */
@Component
public class KanrenshaPersonAddMiniCsvProcessor
        implements ItemProcessor<KanrenshaPersonAddMiniDto, WkTblKanrenshaPersonAddMinEntity> {

    /** 空文字 */
    private static final String BLANK = "";

    /** 正常登録 */
    private static final String RIGHT = "正)";

    /** 関連者個人同属性取得Service */
    @Autowired
    private GetKanrenshaPersonSameHistoryService getKanrenshaPersonSameHistoryService;

    /** 関連者個人マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /** 全文検索検索語フォーマットUtil */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblKanrenshaPersonAddMinEntity process(final KanrenshaPersonAddMiniDto item) throws Exception {

        WkTblKanrenshaPersonAddMinEntity entity = new WkTblKanrenshaPersonAddMinEntity();
        BeanUtils.copyProperties(item, entity);

        return this.check(entity);
    }

    /**
     * チェック処理のみ行う
     *
     * @param entity ワークテーブルEntity
     * @return 処理後Entity
     */
    public WkTblKanrenshaPersonAddMinEntity check(final WkTblKanrenshaPersonAddMinEntity entity) {

        StringBuilder stringBuilder = new StringBuilder();
        if (BLANK.equals(entity.getKanrenshaName())) {
            stringBuilder.append("名称が入力されていません;");
        }
        if (BLANK.equals(entity.getAllAddress())) {
            stringBuilder.append("住所が入力されていません;");
        }
        // if (BLANK.equals(entity.getPersonShokugyou())) {
        // stringBuilder.append("職業が入力されていません;");
        // }

        // 全く同じ履歴があるかどうか確認する
//        List<KanrenshaPersonHistoryBaseEntity> listHistory = this.selectSameRirekiList(entity.getKanrenshaName(),
//                entity.getAllAddress(), entity.getPersonShokugyou());
//        if (listHistory.isEmpty()) {
//            // マスタに同名の団体があるかどうか確認する
//            if (!entity.getIsAffected()) {
//                // マスタに同名の団体があるかどうか確認する
//                List<KanrenshaPersonMasterEntity> listMaster = kanrenshaPersonMasterRepository.findByCompareNameTextAndIsLatest(
//                        formatNaturalSearchTextUtil.practice(entity.getKanrenshaName()),
//                        SetTableDataHistoryUtil.INSERT_STATE);
//                if (!listMaster.isEmpty()) { // SUPPRESS CHECKSTYLE NestedIf
//                    stringBuilder.append("同名の個人があります。確認調査の上、必要に応じて追加してください;");
//                }
//            }
//
//        } else {
//            stringBuilder.append("すでに登録が存在します(").append(listHistory.get(0).getPersonKanrenshaCode()).append(");");
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
    private List<KanrenshaPersonHistoryBaseEntity> selectSameRirekiList(final String name, final String address,
            final String delegate) {

        return getKanrenshaPersonSameHistoryService.practice(name, address, delegate);
    }

}
