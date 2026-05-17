package net.seijishikin.jp.normalize.manage.kanrensha.logic.task_plan;

import java.util.Map;
import java.util.TreeMap;

/**
 * 専用パラメータダミー作成Uttil
 */
public final class CreateQueryParamDummyUtil {

    /**
     * インスタンス作成除け
     */
    private CreateQueryParamDummyUtil() {

    }

    /**
     * 処理を行う
     * 
     * @return ダミー遷移クエリパラメータマップ
     */
    public static Map<String, String> practice() {
        Map<String, String> map = new TreeMap<>();
        map.put("asd", "123");
        map.put("zxc", "456");
        return map;
    }

}
