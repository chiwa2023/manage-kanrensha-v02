package net.seijishikin.jp.normalize.manage.kanrensha.batch.reflesh.schema_table;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Year;
import java.util.stream.Stream;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.WriteLogService;

/**
 * 未作成のテーブルDDLに基づき作成する
 */
@Component
public class CreateTableOnlyNothingTasklet implements Tasklet {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** ログ出力Service */
    @Autowired
    private WriteLogService writeLogService;

    /**
     * 実行メソッド
     */
    @Override
    @Transactional
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        Path pathBackRoot = Paths.get(GetCurrentResourcePath.getBackSrcPath(""));
        Path pathApp = pathBackRoot.getParent().getParent().getParent();
        Path pathDdl = Paths.get(pathApp.toString(), "config/database/DDL");

        writeLogService.writeInfo("DDL読み取り:" + pathDdl.toString());

        try (Stream<Path> stream = Files.list(pathDdl)) {

            stream.forEach(ph -> {
                try {
                    String sql = Files.readString(ph);
                    sql = sql.replaceAll("CREATE TABLE", "CREATE TABLE IF NOT EXISTS");
                    writeLogService.writeInfo("実行SQL:" + sql);

                    Query queryCreate = entityManager.createNativeQuery(sql);
                    queryCreate.executeUpdate();
                } catch (IOException e) {
                    saveStackTraceService.practice(e, Year.now().getValue(), -1);
                }
            });
        }

        // 処理終了
        return RepeatStatus.FINISHED;
    }
}
