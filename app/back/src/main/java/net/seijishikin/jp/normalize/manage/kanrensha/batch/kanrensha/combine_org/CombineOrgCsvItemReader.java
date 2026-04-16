package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

/**
 * 関連者企業・団体最小登録Csv読み取りItemReader
 */
@Component
public class CombineOrgCsvItemReader extends FlatFileItemReader<KanrenshaCombineOrgDto> {

    /**
     * コンストラクタ
     *
     * @param lineMapper 関連者企業・団体csv読み取りLineMapper
     */
    public CombineOrgCsvItemReader(final @Autowired KanrenshaCombineOrgLineMapper lineMapper) {
        super();
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
