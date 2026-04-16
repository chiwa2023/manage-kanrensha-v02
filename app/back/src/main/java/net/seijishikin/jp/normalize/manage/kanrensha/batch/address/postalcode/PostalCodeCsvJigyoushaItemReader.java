package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

/**
 * 郵便番号事業者Csv読み取りItemreader
 */
@Component
public class PostalCodeCsvJigyoushaItemReader extends FlatFileItemReader<PostalCodeCsvJigyoushoDto> {

    /**
     * コンストラクタ
     *
     * @param lineMapper lineMapper
     */
    public PostalCodeCsvJigyoushaItemReader(final @Autowired PostalCodeJigyoushoLineMapper lineMapper) {
        super();
        super.setLineMapper(lineMapper);
        super.setStrict(false);
    }

    /**
     * BeforeStep(読み取りファイル指定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        String filePath = stepExecution.getJobParameters().getString("readFilePathJigyousha");

        Path path = Paths.get(filePath);

        super.setResource(new FileSystemResource(path.toFile()));
    }

}
