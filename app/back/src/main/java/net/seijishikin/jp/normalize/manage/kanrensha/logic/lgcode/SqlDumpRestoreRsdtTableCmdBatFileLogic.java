package net.seijishikin.jp.normalize.manage.kanrensha.logic.lgcode;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

/**
 * 住居テーブルダンプバッチ作成Util
 */
@Component
public class SqlDumpRestoreRsdtTableCmdBatFileLogic {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /** 操作対象データベース */
    private static final String DATABASE_NAME = "test_manage_kanrensha_v02";

    /**
     * 処理を行う
     *
     * @throws IOException ファイル例外
     */
    @SuppressWarnings("unchecked")
    public void practice(final String lgCodePref, final String batDir) throws IOException {

        List<String> listDump = new ArrayList<>();
        List<String> listRestore = new ArrayList<>();
        listDump.add("cd MysqlインストールDir");
        listRestore.add("cd MysqlインストールDir");
        listDump.add("setlocal");
        listRestore.add("setlocal");
        listDump.add("set myPass=パスワード");
        listRestore.add("set myPass=パスワード");

        String sql = "SELECT table_name FROM information_schema.tables WHERE table_schema = '" + DATABASE_NAME
                + "' AND table_name LIKE 'address_rsdt_" + lgCodePref + "%' ";
        Query queryCount = entityManager.createNativeQuery(sql, String.class);
        List<String> listTable = (List<String>) queryCount.getResultList();

        for (String table : listTable) {
            String fileName = "c:\\temp\\sql\\" + lgCodePref + "\\" + table + ".sql";
            listDump.add("mysqldump -u root -p%myPass% " + DATABASE_NAME + " " + table + " > " + fileName);
            listRestore.add("mysql -u root -p%myPass% " + DATABASE_NAME + " < " + fileName);
        }
        listDump.add("endlocal");
        listRestore.add("endlocal");
        listDump.add("PAUSE");
        listRestore.add("PAUSE");

        Path pathDump = Paths.get(batDir, lgCodePref + "rsdt_dump.bat");
        Files.write(pathDump, listDump, Charset.forName("cp932"));

        Path pathRestore = Paths.get(batDir, lgCodePref + "rsdt_restore.bat");
        Files.write(pathRestore, listRestore, Charset.forName("cp932"));
    }

}
