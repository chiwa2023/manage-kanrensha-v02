package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.code_move;

/**
 * csvダンプカラム定義定数マスタ用
 */
public class DumpKanrenshaCodeMoveWriteItemConstants {

    /**
     * 企業団体用カラム定義定数
     */
    public final class Std { // NOPMD ShortClassName

        /**
         * コンストラクタ
         */
        private Std() {

        }

        /** 書き出すカラム列名 */
        public static final String[] NAMES = { "kanrenshaKbn", "kanrenshaKbnName", "originKanrenshaCode", "originName",
                "abolishKanrenshaCode", "abolishKanrenshaName", "saishinName", "moveReason", "insertTimestamp" };

        /** 書き出すカラム表示名称 */
        public static final String[] HEADERS = { "\"関連者区分\"", "\"関連者区分名称\"", "\"併合先コード\"", "\"併合先名称\"", "\"廃止コード\"",
                "\"廃止コード名称\"", "\"最新採用\"", "\"移動理由\"", "\"登録日時\"" };
    }

}
