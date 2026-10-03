package net.seijishikin.jp.normalize.manage.kanrensha.logic.db_prepare;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.service.db_prepare.DbCompareCapsuleDto;

/**
 * テーブルDDL作成Logic
 */
@Component
public class CreateTableDdlLogic {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 処理条件Dto
     * @throws IOException ファイル例外
     */
    public void practice(final DbCompareCapsuleDto capsuleDto) throws IOException {

        List<String> listLocal = Files.readAllLines(capsuleDto.getPathLocalDbTableList());

        for (String tableName : listLocal) {
            if (!tableName.startsWith("address_rsdt")) {

                String sqlFile = tableName + ".sql";
                Path pathSql = Paths.get(GetCurrentResourcePath.getProject(capsuleDto.getPathTableDdl()).toString(),
                        sqlFile);
                // DDLが存在しない場合は作成
                if (!Files.exists(pathSql)) {
                    Query query = entityManager.createNativeQuery("SHOW CREATE TABLE " + tableName);

                    Object[] result = (Object[]) query.getSingleResult(); // NOPMD LawDemeter
                    Files.writeString(pathSql, result[1].toString());
                }
            }
        }

    }
}
