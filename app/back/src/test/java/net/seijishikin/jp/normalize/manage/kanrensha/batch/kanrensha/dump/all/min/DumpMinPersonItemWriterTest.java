package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.min;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;

/**
 * DumpMinPersonItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
class DumpMinPersonItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private DumpMinPersonItemWriter dumpMinPersonItemWriter;

    /** propertiesからインジェクションされた最上位保存フォルダ絶対パス */
    private String storageFolder;

    /**
     * 最上位保存フォルダ絶対パスを取得する
     *
     * @return 最上位保存フォルダ絶対パス
     */
    public String getStorageFolder() {
        return storageFolder;
    }

    /**
     * 最上位保存フォルダ絶対パスを設定する
     *
     * @param storageFolder 最上位保存フォルダ絶対パス
     */
    public void setStorageFolder(final String storageFolder) {
        this.storageFolder = storageFolder;
    }

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        String output = "/test/dump/min_person.csv";
        Path path = Paths.get(storageFolder, output);

        StepExecution stepExecution = this.getStepExecution(path.toString());

        dumpMinPersonItemWriter.beforeStep(stepExecution);

        KanrenshaPersonMasterEntity masterEntity = new KanrenshaPersonMasterEntity();
        masterEntity.setPersonKanrenshaCode("12-3456");
        masterEntity.setKanrenshaName("迂回献金　太郎");
        masterEntity.setAllAddress("和歌山県架空市山麓町");
        masterEntity.setPersonShokugyou("素浪人");
        masterEntity.setInsertTimestamp(LocalDateTime.of(2022, 12, 5, 12, 34, 56));

        List<KanrenshaPersonMasterEntity> list = new ArrayList<>();
        list.add(masterEntity);

        // Chunkを作成してセット
        Chunk<? extends KanrenshaPersonMasterEntity> items = new Chunk<>(list);
        dumpMinPersonItemWriter.open(stepExecution.getExecutionContext());
        dumpMinPersonItemWriter.write(items);

        List<String> listAns = Files.readAllLines(path);

        assertEquals("\"関連者番号\",\"名称\",\"全住所\",\"個人職業\",\"登録日時\"", listAns.get(0));

        final String quote = "\"";
        final String comma = ",";

        StringBuilder data = new StringBuilder();
        data //
                .append(quote).append(masterEntity.getPersonKanrenshaCode()).append(quote).append(comma) //
                .append(quote).append(masterEntity.getKanrenshaName()).append(quote).append(comma) //
                .append(quote).append(masterEntity.getAllAddress()).append(quote).append(comma) //
                .append(quote).append(masterEntity.getPersonShokugyou()).append(quote).append(comma) //
                .append(quote).append(masterEntity.getInsertTimestamp()).append(quote);

        assertEquals(data.toString(), listAns.get(1));

    }

    private StepExecution getStepExecution(final String output) {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("writeFilePath", output).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
