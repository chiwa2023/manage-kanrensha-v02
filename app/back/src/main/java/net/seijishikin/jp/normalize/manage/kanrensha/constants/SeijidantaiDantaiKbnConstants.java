package net.seijishikin.jp.normalize.manage.kanrensha.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 政治団体区分定数
 */
public final class SeijidantaiDantaiKbnConstants { // NOPMD DataClass

    /** 団体区分01:政党 */
    public static final String DANTAI_KBN_01 = "01";
    /** 団体区分02:政党支部 */
    public static final String DANTAI_KBN_02 = "02";
    /** 団体区分03:政治資金団体 */
    public static final String DANTAI_KBN_03 = "03";
    /** 団体区分04:18条2項1規定団体 */
    public static final String DANTAI_KBN_04 = "04";
    /** 団体区分05:その他の政治団体 */
    public static final String DANTAI_KBN_05 = "05";
    /** 団体区分06:その他の政治団体支部 */
    public static final String DANTAI_KBN_06 = "06";

    /** 団体区分リスト */
    private static final List<String> list = new ArrayList<>();

    /**
     * 政治団体区分定数リストを取得する
     *
     * @return 政治団体区分定数リスト
     */
    public static List<String> getList() {

        if (list.isEmpty()) {
            list.add(DANTAI_KBN_01);
            list.add(DANTAI_KBN_02);
            list.add(DANTAI_KBN_03);
            list.add(DANTAI_KBN_04);
            list.add(DANTAI_KBN_05);
            list.add(DANTAI_KBN_06);
        }

        return list;
    }

}
