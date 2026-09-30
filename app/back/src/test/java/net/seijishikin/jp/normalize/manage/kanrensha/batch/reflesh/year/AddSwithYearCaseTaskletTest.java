package net.seijishikin.jp.normalize.manage.kanrensha.batch.reflesh.year;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.repository.YearOptionRepository;

/**
 * AddSwithYearCaseTasklet単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class AddSwithYearCaseTaskletTest {

    /** テスト対象 */
    @Autowired
    private AddSwithYearCaseTasklet addSwithYearCaseTasklet;
    
    /** 紐づけ年選択肢Repository */
    @Autowired
    private YearOptionRepository yearOptionRepository;

    @Test
    void test() throws Exception {

        final Long srcYear = 2025L;
        final Long copyYear = 2019L;

        addSwithYearCaseTasklet.beforeStep(this.getStepExecution(srcYear, copyYear));
        addSwithYearCaseTasklet.execute(null, null);
        
        // 複写年がテーブルに保存されている(複数回実施は考慮しない)
        assertFalse(yearOptionRepository.findById(Math.toIntExact(copyYear)).isEmpty());
    }

    private StepExecution getStepExecution(final Long srcYear, final Long copyYear) {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLocalDateTime("exe_datetitme", LocalDateTime.now()) //
                .addLong(AddSwithYearCaseTasklet.KEY_SRC_YEAR, srcYear) //
                .addLong(AddSwithYearCaseTasklet.KEY_COPY_YEAR, copyYear)
                .addString(AddSwithYearCaseTasklet.KEY_BACKUP, "c:/temp/service")
                .toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
