package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

/**
 * アドレス・ベース・レジストリ地番CsvファイルItemReader
 */
@Component
public class ParcelAddressCsvItemReader extends FlatFileItemReader<ParcelAddressCsvDto> {

    /**
     * コンストラクタ
     *
     * @param parcelAddressCsvLineMapper 地番LineMapper
     */
    public ParcelAddressCsvItemReader(final @Autowired ParcelAddressCsvLineMapper parcelAddressCsvLineMapper) {
        super();
        super.setLineMapper(parcelAddressCsvLineMapper);
        super.setLinesToSkip(1); // ヘッダがあるので1行読み飛ばし
    }

    /**
     * BeforeStep(読み取りファイル指定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        String filePath = stepExecution.getJobParameters().getString("readFilePathParcel");

        Path path = Paths.get(filePath);

        super.setResource(new FileSystemResource(path.toFile()));
    }

}
