package net.seijishikin.jp.normalize.manage.kanrensha.logic.postal;

/**
 * かっこの前まで取得Logic
 * 
 * <p>
 * かっこが村座しないときは引数を返す
 * </p>
 */
public final class GetPreParenthesesLogic {

    /** かっこなし時index pos */
    private static final int POS_NO_EMP = -1;

    /**
     * コンストラクタ インスタンス生成除け
     */
    private GetPreParenthesesLogic() {

    }

    /**
     * 処理を行う
     * 
     * @param data 処理対象
     * @return かっこがあれば、かっこより前の値
     */
    public static String practice(final String data) {
        int pos = data.indexOf("（") ;
        if (POS_NO_EMP == pos) {
            return data;
        } else {
            return data.substring(0, pos);
        }
    }

}
