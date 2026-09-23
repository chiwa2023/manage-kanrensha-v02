package net.seijishikin.jp.normalize.manage.kanrensha.batch.security;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.listener.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserNewRepository;

/**
 * 新規ユーザ登録期限切れコード削除Tasklet
 */
@Component
public class DeleteNewUserLimitRegiCodeTasklet implements Tasklet, StepExecutionListener {

    /** 新規ユーザ登録Repository */
    @Autowired
    private UserNewRepository userNewRepository;

    /** 削除期限日時 */
    private LocalDate limitDate;

    /**
     * 起動条件を設定する
     *
     * @param stepExecution StepExecution
     */
    @BeforeStep
    @Override
    public void beforeStep(final StepExecution stepExecution) {

        JobParameters parameters = stepExecution.getJobParameters();

        limitDate = parameters.getLocalDate("limitDate");
    }

    /**
     * 実行メソッド
     */
    @Override
    @Transactional
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        userNewRepository.deleteByVerifyLimitDateTimeIsNullOrVerifyLimitDateTimeLessThan(
                LocalDateTime.of(limitDate, LocalTime.MIN));

        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
