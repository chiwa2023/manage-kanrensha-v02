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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessTokenEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.PartnerAccessTokenRepository;

/**
 * DeletePartnerApiLimitTokenTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("DeletePartnerApiLimitTokenTaskletTest.sql")
class DeletePartnerApiLimitTokenTaskletTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private DeletePartnerApiLimitTokenTasklet deletePartnerApiLimitTokenTasklet;

    /** APIパートナー長期トークンRepository */
    @Autowired
    private PartnerAccessTokenRepository partnerAccessTokenRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void test() throws Exception {

        List<PartnerAccessTokenEntity> listPre = partnerAccessTokenRepository.findAll();
        assertEquals(3, listPre.size());

        deletePartnerApiLimitTokenTasklet.beforeStep(this.getStepExecution());
        deletePartnerApiLimitTokenTasklet.execute(null, null);

        List<PartnerAccessTokenEntity> list = partnerAccessTokenRepository.findAll();
        assertEquals(1, list.size());

        PartnerAccessTokenEntity entity0 = list.get(0);
        assertEquals(325, entity0.getPartnerAccessTokenId());
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()) //
                .addLocalDate("limitDate", LocalDate.of(2026, 5, 13)).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
