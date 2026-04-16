package net.seijishikin.jp.normalize.manage.kanrensha.batch.reflesh.lgcode;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/**
 * 元ソースから県の地方自治体コード(2桁)分のファイルを複写する
 */
@Component
public class CopyFileByPrefectureTasklet implements Tasklet, StepExecutionListener {

    /** 出力フォルダパス */
    private String dirOutput;

    /** 読み取りファイルパス */
    private String pathReadFile;

    /** 読み取りファイルパス */
    private String replaceKey;

    /**
     * 起動条件を設定する
     *
     * @param stepExecution StepExecution
     */
    @BeforeStep
    @Override
    public void beforeStep(final StepExecution stepExecution) {

        dirOutput = stepExecution.getJobParameters().getString("outputPath");
        pathReadFile = stepExecution.getJobParameters().getString("readPath");
        replaceKey = stepExecution.getJobParameters().getString("key");

    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        final int countPref = 47;

        Path pathRead = Paths.get(pathReadFile);
        String fileName = pathRead.getFileName().toString();

        String body = Files.readString(pathRead);

        for (int index = 1; index <= countPref; index++) {

            String numText = this.getNumberText(index);

            Path path = Paths.get(dirOutput, fileName.replaceAll(replaceKey, numText));
            Files.writeString(path, body.replaceAll(replaceKey, numText));
        }

        // 処理終了
        return RepeatStatus.FINISHED;
    }

    // 2桁前0する
    private String getNumberText(final int index) {
        final int dig = 10;
        if (index < dig) {
            return "0" + index;
        } else {
            return String.valueOf(index);
        }
    }

}
