package net.seijishikin.jp.normalize.manage.kanrensha.service.file;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.batch.address.file.DeleteStorageTempFileBatchConfiguration;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * ストレージ一時ファイル削除Service
 */
@Service
public class DeleteStorageTempFileService {

    /** 起動をするJob */
    @Qualifier(DeleteStorageTempFileBatchConfiguration.JOB_NAME)
    @Autowired
    private Job deleteStorageTempFile;

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobOperator jobOperator;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** 処理間隔(月) */
    private static final int INTERVAL = 6;

    /**
     * 処理を行う
     * 
     * @param month 処理月
     */
    @Async
    public void practice(final Integer month) {

        JobParameters jobParameters = new JobParametersBuilder(
                deleteStorageTempFile.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()).addLong("month", (long) this.getMonth())
                .toJobParameters();
        try {
            jobOperator.run(deleteStorageTempFile, jobParameters);

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            // ここで捕捉できる例外はバッチ起動に関する例外のみで、バッチ動作に関する例外は別で処理する
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
        }
    }

    private Integer getMonth() {
        int month = LocalDate.now().getMonthValue();

        if (month > INTERVAL) {
            return month - INTERVAL;
        } else {
            return month + INTERVAL;
        }
    }
}
