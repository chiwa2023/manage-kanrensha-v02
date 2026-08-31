package net.seijishikin.jp.normalize.manage.kanrensha.logic.year;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

/**
 * 個人団体紐づけ登録作業可能年取得ロジック
 */
@Component
public class GetCombineYearListLogic {

    /** 接続文字列 */
    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /**
     * 処理を行う
     *
     * @return 登録可能年リスト
     */
    @SuppressWarnings("unchecked")
    public List<Short> practice() {

        String schemaName = this.getSchemaName(datasourceUrl);

        final String key = "kanrensha_combine_org_";

        String sql = "SELECT table_name FROM information_schema.TABLES WHERE table_schema = '" + schemaName
                + "' AND TABLE_NAME LIKE '" + key + "%'";
        Query query = entityManager.createNativeQuery(sql, String.class);
        List<String> listTableName = (List<String>) query.getResultList();

        List<Short> list = new ArrayList<>();
        for (String table : listTableName) {
            list.add(Short.valueOf(table.substring(key.length(), table.length())));
        }

        list.sort(Comparator.naturalOrder());

        return list;
    }

    private String getSchemaName(final String datasourceUrl) {

        int posEnd = datasourceUrl.indexOf("?");
        int posStart = datasourceUrl.lastIndexOf("/", posEnd) + 1;

        return datasourceUrl.substring(posStart, posEnd);
    }

}
