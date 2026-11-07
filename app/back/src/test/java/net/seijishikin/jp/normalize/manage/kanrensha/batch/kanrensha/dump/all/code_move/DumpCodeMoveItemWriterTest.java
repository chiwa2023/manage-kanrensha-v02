package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.code_move;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.ShinseiStatusConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * DumpCodeMoveItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
class DumpCodeMoveItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private DumpCodeMoveItemWriter dumpCodeMoveItemWriter;

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

        String output = "/test/dump/code_move.csv";
        Path path = Paths.get(storageFolder, output);

        StepExecution stepExecution = this.getStepExecution(path.toString());

        dumpCodeMoveItemWriter.beforeStep(stepExecution);

        DumpKanrenshaCodeMoveDto dto = new DumpKanrenshaCodeMoveDto();
        dto.setKanrenshaCodeMoveId(431);
        dto.setKanrenshaCodeMoveCode(932);

        dto.setMoveStatus(ShinseiStatusConstants.ACCEPT);
        dto.setAbolishKanrenshaCode("98765");
        dto.setOriginKanrenshaCode("12345");
        dto.setIsAbolishLast(true); // 廃止コードを最新にする
        dto.setSaishinName("廃止が最新");

        dto.setKanrenshaKbn(KanrenshaKbnConstants.PERSON);
        dto.setKanrenshaKbnName("個人");
        dto.setAbolishKanrenshaName("廃止名称");
        dto.setOriginName("残す名称");
        dto.setMoveReason("移動理由");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        dto.setInsertUserId(userDto.getUserPersonId());
        dto.setInsertUserCode(userDto.getUserPersonCode());
        dto.setInsertUserName(userDto.getUserPersonName());
        dto.setInsertTimestamp(LocalDateTime.of(2024, 9, 13, 11, 22, 33));

        List<DumpKanrenshaCodeMoveDto> list = new ArrayList<>();
        list.add(dto);

        // Chunkを作成してセット
        Chunk<? extends DumpKanrenshaCodeMoveDto> items = new Chunk<>(list);
        dumpCodeMoveItemWriter.open(stepExecution.getExecutionContext());
        dumpCodeMoveItemWriter.write(items);

        List<String> listAns = Files.readAllLines(path);

        assertEquals("\"関連者区分\",\"関連者区分名称\",\"併合先コード\",\"併合先名称\",\"廃止コード\",\"廃止コード名称\",\"最新採用\",\"移動理由\",\"登録日時\"",
                listAns.get(0));

        final String quote = "\"";
        final String comma = ",";

        StringBuilder data = new StringBuilder();
        data //
                .append(quote).append(dto.getKanrenshaKbn()).append(quote).append(comma) //
                .append(quote).append(dto.getKanrenshaKbnName()).append(quote).append(comma) //
                .append(quote).append(dto.getOriginKanrenshaCode()).append(quote).append(comma) //
                .append(quote).append(dto.getOriginName()).append(quote).append(comma) //
                .append(quote).append(dto.getAbolishKanrenshaCode()).append(quote).append(comma) //
                .append(quote).append(dto.getAbolishKanrenshaName()).append(quote).append(comma) //
                .append(quote).append(dto.getSaishinName()).append(quote).append(comma) //
                .append(quote).append(dto.getMoveReason()).append(quote).append(comma) //
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
