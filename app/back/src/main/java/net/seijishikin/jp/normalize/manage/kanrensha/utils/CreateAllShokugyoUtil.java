package net.seijishikin.jp.normalize.manage.kanrensha.utils;

import java.util.Objects;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;

/**
 * 個人職業作成Utility
 */
public final class CreateAllShokugyoUtil {

    /**
     * コンストラクタ(インスタンス生成除け)
     */
    private CreateAllShokugyoUtil() {

    }

    /**
     * 処理を行う
     * 
     * @param propertyEntity 関連者個人属性Entity
     * @return 職業名
     */
    public static String practice(final KanrenshaPersonPropertyEntity propertyEntity) {

        if (Objects.isNull(propertyEntity)) {
            throw new IllegalArgumentException("個人属性がnullです");
        }

        final String BLANK = "";

        String userShokugyou = propertyEntity.getShokugyouUserWrite();

        // ユーザ記載職業にnullの場合は空文字
        if (Objects.isNull(userShokugyou)) {
            return BLANK;
        }

        // ユーザが職業に記入したらその値が最優先
        if (!BLANK.equals(userShokugyou)) {
            return userShokugyou;
        }

        final String yakushokuFree = "所属なし";
        final String yakushokuGeneral = "一般職員";
        final String yakushokuDirector = "役職者";
        final String yakushokuNoJob = "定職なし";

        switch (propertyEntity.getYakushoku()) {
            // フリーランス
            case yakushokuFree:
                return propertyEntity.getGyoushu() + "業従事者";

            // 団体所属者
            case yakushokuGeneral:
                return propertyEntity.getGyoushu() + "業社員・職員";

            // 役職者
            case yakushokuDirector:
                if (BLANK.equals(propertyEntity.getKigyouDtNo())) {
                    return propertyEntity.getGyoushu() + "業役職者(社名記載拒否)";
                } else {
                    return propertyEntity.getGyoushu() + "業:" + propertyEntity.getKigyouDtName() + "("
                            + propertyEntity.getKigyouDtAddress() + ")" + "役員";
                }

                // 定職なし
            case yakushokuNoJob:
                break;

            default:
                break;
        }

        return BLANK;
    }

}
