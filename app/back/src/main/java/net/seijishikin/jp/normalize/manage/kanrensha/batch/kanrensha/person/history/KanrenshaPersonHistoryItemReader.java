package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.history;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;


/**
 * 関連者個人Csv読み込みItemReader
 */
@Component
public class KanrenshaPersonHistoryItemReader extends FlatFileItemReader<KanrenshaPersonHistoryDto> {

    /**
     * コンストラクタ
     *
     * @param lineMapper 関連者個人csv読み取りLineMapper
     */
    public KanrenshaPersonHistoryItemReader(final @Autowired KanrenshaPersonHistoryLineMapper lineMapper) {
        super();
        super.setLineMapper(lineMapper);
        super.setLinesToSkip(1); // ヘッダがあるので1行読み飛ばし
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
