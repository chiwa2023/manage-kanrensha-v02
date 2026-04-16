package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.master;

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

/**
 * DumpMasterKigyouDtItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
class DumpMasterKigyouDtItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private DumpMasterKigyouDtItemWriter dumpMasterKigyouDtItemWriter;

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

        String output = "/test/dump/master_kigyou_dt.csv";
        Path path = Paths.get(storageFolder, output);

        StepExecution stepExecution = this.getStepExecution(path.toString());

        dumpMasterKigyouDtItemWriter.beforeStep(stepExecution);

        DumpKanrenshaKigyouDtMasterDto dto = new DumpKanrenshaKigyouDtMasterDto();
        dto.setKigyouDtKanrenshaCode("123-4567");
        dto.setKanrenshaName("超元素製造組合");
        dto.setOrgNameKana("ちょうげんそせいぞうくみあい");
        dto.setAllAddress("和歌山県架空市山麓町");
        dto.setHoujinNo("987-6543");
        dto.setPhon("000-111-2222");
        dto.setMyPortalUrl("https://my-portal/12345");
        dto.setKigyouDtDelegate("団体代表者");
        dto.setOrgDelegateCode("22-333");
        dto.setInsertTimestamp(LocalDateTime.of(2022, 12, 5, 1, 2, 3));

        List<DumpKanrenshaKigyouDtMasterDto> list = new ArrayList<>();
        list.add(dto);

        // Chunkを作成してセット
        Chunk<? extends DumpKanrenshaKigyouDtMasterDto> items = new Chunk<>(list);
        dumpMasterKigyouDtItemWriter.open(stepExecution.getExecutionContext());
        dumpMasterKigyouDtItemWriter.write(items);

        List<String> listAns = Files.readAllLines(path);

        assertEquals("\"関連者番号\",\"名称\",\"名称かな\",\"全住所\",\"法人番号\",\"電話番号\",\"代表URL\",\"代表者コード\",\"代表者名\",\"登録日時\"",
                listAns.get(0));

        final String quote = "\"";
        final String comma = ",";

        StringBuilder data = new StringBuilder();
        data //
                .append(quote).append(dto.getKigyouDtKanrenshaCode()).append(quote).append(comma) //
                .append(quote).append(dto.getKanrenshaName()).append(quote).append(comma) //
                .append(quote).append(dto.getOrgNameKana()).append(quote).append(comma) //
                .append(quote).append(dto.getAllAddress()).append(quote).append(comma) //
                .append(quote).append(dto.getHoujinNo()).append(quote).append(comma) //
                .append(quote).append(dto.getPhon()).append(quote).append(comma) //
                .append(quote).append(dto.getMyPortalUrl()).append(quote).append(comma) //
                .append(quote).append(dto.getOrgDelegateCode()).append(quote).append(comma) //
                .append(quote).append(dto.getKigyouDtDelegate()).append(quote).append(comma) //
                .append(quote).append(dto.getInsertTimestamp()).append(quote);

        assertEquals(data.toString(), listAns.get(1));
    }

    private StepExecution getStepExecution(final String output) {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("writeFilePath", output).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
