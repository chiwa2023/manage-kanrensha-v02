package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import java.io.File;
import java.io.IOException;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * アドレス・ベース・レジストリ住居複数ファイル連続読みItemReader
 */
@Component
public class MultiRsdtCsvFileReader extends MultiResourceItemReader<RsdtAddressCsvDto> {

    /**
     * コンストラクタ
     *
     * @param rsdtAddressCsvItemReader 住居住所LineMapper
     */
    public MultiRsdtCsvFileReader(final @Autowired RsdtAddressCsvItemReader rsdtAddressCsvItemReader) {
        super();
        super.setDelegate(rsdtAddressCsvItemReader);
        super.setStrict(false);
    }

    /**
     * 起動条件(ディレクトリ)から処理リストを作成する
     *
     * @param stepExecution 処理例外
     * @throws IOException ファイル例外
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) throws IOException {

        String dirPath = stepExecution.getJobParameters().getString("readDirectory");

        File[] fileGroups = new File(dirPath).listFiles();
        Resource[] array = new FileSystemResource[fileGroups.length];
        for (int index = 0; index < fileGroups.length; index++) {
            array[index] = new FileSystemResource(fileGroups[index].getAbsolutePath());
        }

        this.setResources(array);
    }

}
