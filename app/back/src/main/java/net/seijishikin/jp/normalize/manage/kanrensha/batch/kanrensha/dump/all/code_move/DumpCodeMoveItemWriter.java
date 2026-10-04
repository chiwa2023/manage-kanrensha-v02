package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.code_move;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.infrastructure.item.file.FlatFileItemWriter;
import org.springframework.batch.infrastructure.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.infrastructure.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

/**
 * 関連者コード移動書き出しItemWriter
 */
@Component
public class DumpCodeMoveItemWriter extends FlatFileItemWriter<DumpKanrenshaCodeMoveDto> {

    /**
     * コンストラクタ
     *
     */
    public DumpCodeMoveItemWriter() {
        DelimitedLineAggregator<DumpKanrenshaCodeMoveDto> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(","); // 区切り文字をカンマに設定
        lineAggregator.setQuoteCharacter("\"");
        BeanWrapperFieldExtractor<DumpKanrenshaCodeMoveDto> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(DumpKanrenshaCodeMoveWriteItemConstants.Std.NAMES); // 書き出すフィールド名を設定
        lineAggregator.setFieldExtractor(fieldExtractor);
        super(lineAggregator);
        super.setHeaderCallback(
                writer1 -> writer1.write(String.join(",", DumpKanrenshaCodeMoveWriteItemConstants.Std.HEADERS)));
        super.setLineAggregator(lineAggregator);
    }

    /**
     * BeforeStep(書き出しファイル指定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        String filePath = stepExecution.getJobParameters().getString("writeFilePath");
        Path path = Paths.get(filePath);
        super.setResource(new FileSystemResource(path.toFile()));
    }

}
