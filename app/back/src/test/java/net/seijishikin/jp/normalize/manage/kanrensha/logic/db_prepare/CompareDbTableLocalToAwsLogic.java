package net.seijishikin.jp.normalize.manage.kanrensha.logic.db_prepare;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.service.db_prepare.DbCompareCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.WriteLogService;

/**
 * ローカルデータベースとawsのデータベースの比較Logic
 */
@Component
public class CompareDbTableLocalToAwsLogic {

    /** ログ出力Service */
    @Autowired
    private WriteLogService writeLogService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 処理条件Dto
     * @throws IOException ファイル例外
     */
    public void practice(final DbCompareCapsuleDto capsuleDto) throws IOException {

        List<String> listAws = Files.readAllLines(capsuleDto.getPathAwsTableList());

        List<String> listLocal = Files.readAllLines(capsuleDto.getPathLocalDbTableList());

        // AWSのDBにあってローカルDBにないもの(限りなく絶無)
        this.compare(listLocal, listAws, capsuleDto);

        // ローカルDBにあってAWSのDBにないもの(処理の本命)
        this.compare(listAws, listLocal, capsuleDto);
    }

    private void compare(final List<String> listBase, final List<String> listCompare,
            final DbCompareCapsuleDto capsuleDto) throws IOException {

        for (String tableName : listBase) {
            if (!listCompare.contains(tableName)) {
                // 存在しないときの処理
                // どっちにしてもログを出す
                writeLogService.writeInfo("********" + tableName + "に差異がありました");
                if (this.copyForUpload(tableName, capsuleDto)) {
                    // ローカルにあってAWSにない場合がほとんど
                    writeLogService.writeInfo("--" + tableName + "作成SQLをアップロードに準備しました");
                } else {
                    // 主にAWSにあってローカルにない場合がほとんど
                    writeLogService.writeWarn("--" + tableName + "のDDLがありませんでした。別途手配をしてください");
                }
            }
        }
    }

    private boolean copyForUpload(final String tableName, final DbCompareCapsuleDto capsuleDto) throws IOException {

        // configにDDLが存在するか確認、あればアップロードフォルダに複写
        final String sqlFile = tableName + ".sql";
        Path pathSql = Paths.get(GetCurrentResourcePath.getProject(capsuleDto.getPathTableDdl()).toString(), sqlFile);

        if (Files.exists(pathSql)) {
            // すでにDDLが準備できている場合は、アップロード用のフォルダに格納
            Path copySql = Paths.get(capsuleDto.getPathUpload().toString(), sqlFile); // NOPMD LawDemeter
            Files.copy(pathSql, copySql);
        } else {
            // なければfalse
            return false;
        }

        return true;
    }

}
