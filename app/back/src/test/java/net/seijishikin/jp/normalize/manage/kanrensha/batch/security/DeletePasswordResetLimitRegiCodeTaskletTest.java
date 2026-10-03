package net.seijishikin.jp.normalize.manage.kanrensha.batch.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPasswordResetEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPasswordResetRepository;

/**
 * DeletePasswordResetLimitRegiCodeTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("DeletePasswordResetLimitRegiCodeTaskletTest.sql")
class DeletePasswordResetLimitRegiCodeTaskletTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private DeletePasswordResetLimitRegiCodeTasklet deletePasswordResetLimitRegiCodeTasklet;

    /** パスワードリセットRepository */
    @Autowired
    private UserPasswordResetRepository userPasswordResetRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void test() throws Exception {

        List<UserPasswordResetEntity> listPre = userPasswordResetRepository.findAll();
        assertEquals(2, listPre.size());

        deletePasswordResetLimitRegiCodeTasklet.beforeStep(this.getStepExecution());
        deletePasswordResetLimitRegiCodeTasklet.execute(null, null);

        List<UserPasswordResetEntity> list = userPasswordResetRepository.findAll();
        assertEquals(1, list.size());

        UserPasswordResetEntity entity0 = list.get(0);
        assertEquals("ddd@seijishikin.net", entity0.getEmail());
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()) //
                .addLocalDate("limitDate", LocalDate.of(2026, 5, 13)).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
