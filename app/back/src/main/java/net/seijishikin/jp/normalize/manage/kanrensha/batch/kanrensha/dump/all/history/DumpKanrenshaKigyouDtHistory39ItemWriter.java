package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory39Entity;

/**
 * 関連者企業・団体履歴(39)ItemWriter
 */
@Component
public class DumpKanrenshaKigyouDtHistory39ItemWriter extends FlatFileItemWriter<KanrenshaKigyouDtHistory39Entity> {

    /**
     * コンストラクタ
     */
    public DumpKanrenshaKigyouDtHistory39ItemWriter() {
        super();
        DelimitedLineAggregator<KanrenshaKigyouDtHistory39Entity> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(","); // 区切り文字をカンマに設定
        lineAggregator.setQuoteCharacter("\"");
        BeanWrapperFieldExtractor<KanrenshaKigyouDtHistory39Entity> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(DumpHistoryWriteItemConstants.KigyouDt.NAMES); // 書き出すフィールド名を設定
        lineAggregator.setFieldExtractor(fieldExtractor);
        super.setHeaderCallback(
                writer1 -> writer1.write(String.join(",", DumpHistoryWriteItemConstants.KigyouDt.HEADERS)));
        super.setLineAggregator(lineAggregator);
    }

    /**
     * BeforeStep(読み取りファイル指定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        String filePath = stepExecution.getJobParameters().getString("writeFilePath39");
        Path path = Paths.get(filePath);
        super.setResource(new FileSystemResource(path.toFile()));
    }

}
