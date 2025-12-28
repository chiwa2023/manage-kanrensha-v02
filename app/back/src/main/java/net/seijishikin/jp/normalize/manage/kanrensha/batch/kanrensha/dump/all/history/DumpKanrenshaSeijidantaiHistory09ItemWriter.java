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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory09Entity;

/**
 * 関連者政治団体団体履歴(09)ItemWriter
 */
@Component
public class DumpKanrenshaSeijidantaiHistory09ItemWriter
        extends FlatFileItemWriter<KanrenshaSeijidantaiHistory09Entity> {

    /**
     * コンストラクタ
     */
    public DumpKanrenshaSeijidantaiHistory09ItemWriter() {
        super();
        DelimitedLineAggregator<KanrenshaSeijidantaiHistory09Entity> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(","); // 区切り文字をカンマに設定
        lineAggregator.setQuoteCharacter("\"");
        BeanWrapperFieldExtractor<KanrenshaSeijidantaiHistory09Entity> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(DumpHistoryWriteItemConstants.Seijidantai.NAMES); // 書き出すフィールド名を設定
        lineAggregator.setFieldExtractor(fieldExtractor);
        super.setHeaderCallback(
                writer1 -> writer1.write(String.join(",", DumpHistoryWriteItemConstants.Seijidantai.HEADERS)));
        super.setLineAggregator(lineAggregator);
    }

    /**
     * BeforeStep(読み取りファイル指定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        String filePath = stepExecution.getJobParameters().getString("writeFilePath09");
        Path path = Paths.get(filePath);
        super.setResource(new FileSystemResource(path.toFile()));
    }

}
