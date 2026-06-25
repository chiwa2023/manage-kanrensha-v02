package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

/**
 * アドレス・ベース・レジストリ住居表示－住居Csv(mt_rsdtdsp_rsdt_prefxx.csv)読み取りItemReader
 */
@Component
class AllCityCsvItemReader extends FlatFileItemReader<AllCityCsvDto> {

    /**
     * コンストラクタ
     *
     * @param lineMapper lineMapper
     */
    public AllCityCsvItemReader(final @Autowired AllCityCsvLineMapper lineMapper) {
        super(lineMapper);
        super.setLineMapper(lineMapper);
        super.setLinesToSkip(1); // ヘッダがあるので1行読み飛ばし
        DefaultRecordSeparatorPolicy separatorPolicy = new DefaultRecordSeparatorPolicy();
        separatorPolicy.setQuoteCharacter("\"");
        super.setRecordSeparatorPolicy(separatorPolicy);
    }

    /**
     * BeforeStep(読み取りファイル指定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        String filePath = stepExecution.getJobParameters().getString("readFilePath");

        Path path = Paths.get(filePath);

        super.setResource(new FileSystemResource(path.toFile()));
    }

}
