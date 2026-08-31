package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import java.time.LocalDate;
import java.util.List;

import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressAllCityRepository;

/**
 * アドレス・ベース・レジストリ住居テーブルを展開するTasklet
 */
@Component
public class SabunAddRsdtTableTasklet implements Tasklet, StepExecutionListener {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /** 地方自治体Repository */
    @Autowired
    private AddressAllCityRepository addressAllCityRepository;

    /** 処理単位件数 */
    private static final int CHUNK_SIZE = 250;

    /** 廃止日 */
    private LocalDate abolishDate = DtoEntityInitialValueInterface.INIT_DATE;

    /**
     * BeforeStep(ユーザ情報設定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    @Override
    public void beforeStep(final StepExecution stepExecution) {

        abolishDate = stepExecution.getJobParameters().getLocalDate("abolishDate");
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        long count = addressAllCityRepository.countLgCode(abolishDate);
        for (long loop = 0; loop < (count / CHUNK_SIZE) + 1; loop++) {
            Pageable pageable = Pageable.ofSize(CHUNK_SIZE).withPage(Math.toIntExact(loop));
            List<String> list = addressAllCityRepository.findLgCode(abolishDate, pageable);

            for (String lgCode : list) {
                String table = "address_rsdt_" + lgCode;

                Query queryCreate = entityManager
                        .createNativeQuery("CREATE TABLE IF NOT EXISTS " + table + " LIKE address_rsdt_template");
                queryCreate.executeUpdate();
            }

        }

        // 処理終了
        return RepeatStatus.FINISHED;

    }

}
