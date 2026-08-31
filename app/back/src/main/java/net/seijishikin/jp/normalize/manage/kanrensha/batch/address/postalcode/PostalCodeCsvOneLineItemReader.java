package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

/**
 * 郵便番号Csv読み取りItemreader
 */
@Component
public class PostalCodeCsvOneLineItemReader extends FlatFileItemReader<PostalCodeCsvOneLineDto> {

    /**
     * コンストラクタ
     *
     * @param lineMapper lineMapper
     */
    public PostalCodeCsvOneLineItemReader(final @Autowired PostalCodeOneLineLineMapper lineMapper) {
        super(lineMapper);
        super.setStrict(false);
    }

    /**
     * BeforeStep(読み取りファイル指定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        String filePath = stepExecution.getJobParameters().getString("readFilePathOneLine");

        Path path = Paths.get(filePath);

        super.setResource(new FileSystemResource(path.toFile()));
    }

}
