package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_std;

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
 * 関連者企業・団体マスタ標準Csv登録ItemReadrer
 */
@Component
public class MasterKigyouDtAddStdCsvItemReader extends FlatFileItemReader<KanrenshaKigyouDtAddStdDto> {

    /**
     * コンストラクタ
     *
     * @param lineMapper 関連者企業・団体csv読み取りLineMapper
     */
    public MasterKigyouDtAddStdCsvItemReader(final @Autowired KanrenshaKigyouDtAddStdLineMapper lineMapper) {
        super();
        super.setLineMapper(lineMapper);
        super.setLinesToSkip(1); // ヘッダがあるので1行読み飛ばし
        DefaultRecordSeparatorPolicy separatorPolicy = new DefaultRecordSeparatorPolicy();
        separatorPolicy.setQuoteCharacter("\"");
        super.setRecordSeparatorPolicy(separatorPolicy);
    }

    /**
     * BeforeStep
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
