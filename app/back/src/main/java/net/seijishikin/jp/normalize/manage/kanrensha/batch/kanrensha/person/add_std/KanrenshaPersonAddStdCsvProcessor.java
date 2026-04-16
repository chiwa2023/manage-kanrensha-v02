package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_std;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaPersonSameHistoryService;

/**
 * 関連者個人標準登録Processor
 */
@Component
public class KanrenshaPersonAddStdCsvProcessor
        implements ItemProcessor<KanrenshaPersonAddStdDto, WkTblKanrenshaPersonMasterEntity> {

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

    /** 個人名最大桁数 */
    private static final int LIMIT_KANRENSHA_NAME = 100;

    /** 全住所最大桁数 */
    private static final int LIMIT_ALL_ADDRESS = 100;

    /** 職業最大桁数 */
    private static final int LIMIT_PERSON_SHOKUGYOU = 100;

    /** Email最大桁数 */
    private static final int LIMIT_EMAIL = 100;

    /** 電話番号最大桁数 */
    private static final int LIMIT_DIGIT_PHON = 10;

    /** 郵便番号最大桁数 */
    private static final int LIMIT_DIGIT_POSTAL = 8;

    /** 地方自治体コード最大桁数 */
    private static final int LIMIT_LGCODE = 8;

    /** 町字コード最大桁数 */
    private static final int LIMIT_MACHIAZA = 9;

    /** 街区コード最大桁数 */
    private static final int LIMIT_BLK = 5;

    /** 地番コード最大桁数 */
    private static final int LIMIT_PRC = 17;

    /** 住居コード最大桁数 */
    private static final int LIMIT_RSDT = 5;

    /** 住居2コード最大桁数 */
    private static final int LIMIT_RSDT2 = 7;

    /** 文字数超えメッセージ */
    private static final String OVER_MESSAGE = "文字を超えています;";

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblKanrenshaPersonMasterEntity process(final KanrenshaPersonAddStdDto item) throws Exception { // NOPMD

        WkTblKanrenshaPersonMasterEntity entity = new WkTblKanrenshaPersonMasterEntity();
        BeanUtils.copyProperties(item, entity);

        return this.check(entity);
    }

    /**
     * チェック処理のみ行う
     *
     * @param entity ワークテーブルEntity
     * @return 処理後Entity
     */
    public WkTblKanrenshaPersonMasterEntity check(// SUPPRESS CHECKSTYLE NCSS NOPMD
            final WkTblKanrenshaPersonMasterEntity entity) {

        StringBuilder stringBuilder = new StringBuilder();
        // 未入力
        if (BLANK.equals(entity.getKanrenshaName())) {
            stringBuilder.append("名称が入力されていません;");
        }
        if (BLANK.equals(entity.getAllAddress())) {
            stringBuilder.append("住所が入力されていません;");
        }
        // if (BLANK.equals(item.getPersonShokugyou())) {
        // stringBuilder.append("職業が入力されていません;");
        // }
        if (BLANK.equals(entity.getAddressPostal())) {
            stringBuilder.append("住所郵便番号までが入力されていません;");
        }
        if (BLANK.equals(entity.getAddressBlock())) {
            stringBuilder.append("住所番地までが入力されていません;");
        }
        // if (BLANK.equals(entity.getAddressBuilding())) {
        // stringBuilder.append("住所建物までが入力されていません;");
        // }
        if (BLANK.equals(entity.getPhon1())) {
            stringBuilder.append("電話番号市外局番が入力されていません;");
        }
        if (BLANK.equals(entity.getPhon2())) {
            stringBuilder.append("電話番号局番が入力されていません;");
        }
        if (BLANK.equals(entity.getPhon3())) {
            stringBuilder.append("電話番号番号が入力されていません;");
        }
        if (BLANK.equals(entity.getEmail())) {
            stringBuilder.append("メールアドレスが入力されていません;");
        }

        // 文字数制限
        if (LIMIT_KANRENSHA_NAME < entity.getKanrenshaName().length()) {
            stringBuilder.append("個人名が" + LIMIT_KANRENSHA_NAME + OVER_MESSAGE);
        }
        if (LIMIT_ALL_ADDRESS < entity.getAllAddress().length()) {
            stringBuilder.append("個人全住所が" + LIMIT_ALL_ADDRESS + OVER_MESSAGE);
        }
        if (LIMIT_PERSON_SHOKUGYOU < entity.getPersonShokugyou().length()) {
            stringBuilder.append("個人職業が" + LIMIT_PERSON_SHOKUGYOU + OVER_MESSAGE);
        }
        if (LIMIT_EMAIL < entity.getEmail().length()) {
            stringBuilder.append("電子メールが" + LIMIT_EMAIL + OVER_MESSAGE);
        }
        if (LIMIT_DIGIT_PHON < entity.getPhon1().length()) {
            stringBuilder.append("電話番号市外局番が" + LIMIT_DIGIT_PHON + OVER_MESSAGE);
        }
        if (LIMIT_DIGIT_PHON < entity.getPhon2().length()) {
            stringBuilder.append("電話番号局番が" + LIMIT_DIGIT_PHON + OVER_MESSAGE);
        }
        if (LIMIT_DIGIT_PHON < entity.getPhon3().length()) {
            stringBuilder.append("電話番号番号が" + LIMIT_DIGIT_PHON + OVER_MESSAGE);
        }
        if (LIMIT_DIGIT_POSTAL < entity.getPostalcode1().length()) {
            stringBuilder.append("郵便番号1が" + LIMIT_DIGIT_POSTAL + OVER_MESSAGE);
        }
        if (LIMIT_DIGIT_POSTAL < entity.getPostalcode2().length()) {
            stringBuilder.append("郵便番号2が" + LIMIT_DIGIT_POSTAL + OVER_MESSAGE);
        }
        if (LIMIT_LGCODE < entity.getLgCode().length()) {
            stringBuilder.append("地方自治体コードが" + LIMIT_LGCODE + OVER_MESSAGE);
        }
        if (LIMIT_MACHIAZA < entity.getMachiazaId().length()) {
            stringBuilder.append("町字コードが" + LIMIT_MACHIAZA + OVER_MESSAGE);
        }
        if (LIMIT_BLK < entity.getBlkId().length()) {
            stringBuilder.append("街区コードが" + LIMIT_BLK + OVER_MESSAGE);
        }
        if (LIMIT_PRC < entity.getPrcId().length()) {
            stringBuilder.append("地番コードが" + LIMIT_PRC + OVER_MESSAGE);
        }
        if (LIMIT_RSDT < entity.getRsdtId().length()) {
            stringBuilder.append("住居コードが" + LIMIT_RSDT + OVER_MESSAGE);
        }
        if (LIMIT_RSDT2 < entity.getRsdt2Id().length()) {
            stringBuilder.append("住居2コードが" + LIMIT_RSDT2 + OVER_MESSAGE);
        }

        // 全く同じ履歴があるかどうか確認する
        List<KanrenshaPersonHistoryBaseEntity> listHistory = this.selectSameRirekiList(entity.getKanrenshaName(),
                entity.getAllAddress(), entity.getPersonShokugyou());
        if (listHistory.isEmpty()) {
            // 初回はすべてチェック対象だが、自動・人為的に作業対象にした場合は同名チェックを行わない(同名でも登録できる)
            // マスタに同名の団体があるかどうか確認する
            if (!entity.getIsAffected()) {
                List<KanrenshaPersonMasterEntity> listMaster = kanrenshaPersonMasterRepository
                        .findByCompareNameTextAndIsLatest(
                                formatNaturalSearchTextUtil.practice(entity.getKanrenshaName()),
                                SetTableDataHistoryUtil.INSERT_STATE);
                // 初回はすでにチェック対象だが
                if (!listMaster.isEmpty()) { // SUPPRESS CHECKSTYLE NestedIf
                    stringBuilder.append("同名の団体があります。確認調査の上、必要に応じて追加してください;");
                }
            }
        } else {
            stringBuilder.append("すでに登録が存在します(").append(listHistory.get(0).getPersonKanrenshaCode()).append(");");
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
     * 関連者個人履歴リストを取得する
     * 
     * @param name      名称
     * @param address   住所
     * @param shokugyou 職業
     * @return 履歴リスト
     */
    private List<KanrenshaPersonHistoryBaseEntity> selectSameRirekiList(final String name, final String address,
            final String shokugyou) {
        return getKanrenshaPersonSameHistoryService.practice(name, address, shokugyou);
    }

}
