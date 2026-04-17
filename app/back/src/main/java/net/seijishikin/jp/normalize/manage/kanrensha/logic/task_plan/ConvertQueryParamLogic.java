package net.seijishikin.jp.normalize.manage.kanrensha.logic.task_plan;

import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

/**
 * クエリパラメータ作成Logic
 */
@Component
public class ConvertQueryParamLogic {

    /** マップのキーsplitter */
    public static final String SPLITTER = ",";

    /** マップのキーsplitter */
    private static final String BLANK = "";

    /** マップのキーsplitter */
    private static final String ERROR_MESSAGE = "タスク情報でパラメータが必要ですが、データがありません";

    /**
     * 処理を行う
     * 
     * @param keyArrayString 取得キー文字列
     * @param mapParam       データ
     * @return クエリ文字列
     */
    public String practice(final String keyArrayString, final Map<String, String> mapParam) {
        // タスク情報にクエリパラメータが想定されていない
        if (BLANK.equals(keyArrayString)) {
            return BLANK;
        }
        // データが渡っていない
        if (Objects.isNull(mapParam)) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        if (mapParam.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }

        StringBuilder builder = new StringBuilder("?");
        String[] keys = keyArrayString.split(SPLITTER);
        for (String key : keys) {
            if (!mapParam.containsKey(key)) {
                throw new IllegalArgumentException(ERROR_MESSAGE + "(" + key + ");");
            }
            builder.append(key).append('=').append(mapParam.get(key)).append('&');
        }

        String query = builder.toString();

        return query.substring(0, query.length() - 1);
    }
}
