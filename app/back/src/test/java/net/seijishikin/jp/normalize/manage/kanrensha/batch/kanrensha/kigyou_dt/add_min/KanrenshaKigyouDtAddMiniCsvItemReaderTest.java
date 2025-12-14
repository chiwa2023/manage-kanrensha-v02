package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;

/**
 * KanrenshaKigyouDtAddMiniCsvItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class KanrenshaKigyouDtAddMiniCsvItemReaderTest {

    /** テスト対象 */
    @Autowired
    private KanrenshaKigyouDtAddMiniCsvItemReader kanrenshaKigyouDtAddMiniCsvItemReader;
    
    @Test
    @Tag("TableTruncate")
    void test()throws Exception {
        
        StepExecution stepExecution = this.getStepExecutionFormat();
        kanrenshaKigyouDtAddMiniCsvItemReader.beforeStep(stepExecution);
        kanrenshaKigyouDtAddMiniCsvItemReader.open(stepExecution.getExecutionContext());

        KanrenshaKigyouDtAddMiniDto dto01 = kanrenshaKigyouDtAddMiniCsvItemReader.read();
        // "ぼったくり企業","和歌山県架空市実在町","代表者 太郎","1233444"
        assertEquals("ぼったくり企業", dto01.getKanrenshaName()); // NOPMD
        assertEquals("和歌山県架空市実在町", dto01.getAllAddress());
        assertEquals("代表者　太郎", dto01.getKigyouDtDelegate()); // NOPMD
        assertEquals("1233444", dto01.getHoujinNo()); // NOPMD

        KanrenshaKigyouDtAddMiniDto dto02 = kanrenshaKigyouDtAddMiniCsvItemReader.read();
        // "ぼったくり企業","和歌山県架空市実在町","代表者 太郎","1233444"
        assertEquals("ぼったくり企業", dto02.getKanrenshaName());
        assertEquals("和歌山県架空市実在町", dto02.getAllAddress());
        assertEquals("代表者　太郎", dto02.getKigyouDtDelegate());
        assertEquals("1233444", dto02.getHoujinNo());

        // 改行対応
        KanrenshaKigyouDtAddMiniDto dto03 = kanrenshaKigyouDtAddMiniCsvItemReader.read();
        // "ぼったくり企業","和歌山県架空
        // 市実在町","代表者 太郎","1233444"
        assertEquals("ぼったくり企業", dto03.getKanrenshaName());
        assertEquals("和歌山県架\n空市実在町", dto03.getAllAddress());
        assertEquals("代表者　太郎", dto03.getKigyouDtDelegate());
        assertEquals("1233444", dto03.getHoujinNo());

        // "がなくても大丈夫
        KanrenshaKigyouDtAddMiniDto dto04 = kanrenshaKigyouDtAddMiniCsvItemReader.read();
        // ぼったくり企業,"和歌山県\"架空市実在町",代表者 太郎,1233444
        assertEquals("ぼったくり企業", dto04.getKanrenshaName());
        assertEquals("和歌山県架空市実在町", dto04.getAllAddress());
        assertEquals("代表者　太郎", dto04.getKigyouDtDelegate());
        assertEquals("1233444", dto04.getHoujinNo());

        KanrenshaKigyouDtAddMiniDto dto05 = kanrenshaKigyouDtAddMiniCsvItemReader.read();
        // "ふんだくり企業","宮崎県架空市実在町","代表者 次郎","23445677"
        assertEquals("ふんだくり企業", dto05.getKanrenshaName());
        assertEquals("宮崎県架空市実在町", dto05.getAllAddress());
        assertEquals("代表者　次郎", dto05.getKigyouDtDelegate());
        assertEquals("23445677", dto05.getHoujinNo());

        KanrenshaKigyouDtAddMiniDto dto06 = kanrenshaKigyouDtAddMiniCsvItemReader.read();
        // "職業組合A","山形県架空市実在町","代表者 三郎","3989865"
        assertEquals("職業組合A", dto06.getKanrenshaName());
        assertEquals("山形県架空市実在町", dto06.getAllAddress());
        assertEquals("代表者　三郎", dto06.getKigyouDtDelegate());
        assertEquals("3989865", dto06.getHoujinNo());

        KanrenshaKigyouDtAddMiniDto dto07 = kanrenshaKigyouDtAddMiniCsvItemReader.read();
        // "職業組合B","山梨県架空市実在町","代表者 四郎","4878754"
        assertEquals("職業組合B", dto07.getKanrenshaName());
        assertEquals("山梨県架空市実在町", dto07.getAllAddress());
        assertEquals("代表者　四郎", dto07.getKigyouDtDelegate());
        assertEquals("4878754", dto07.getHoujinNo());

        // 行読み取り時にエスケープ考慮がない模様のため、引用文字はデータに使えなくなると思われる
        assertThrows(FlatFileParseException.class,() -> kanrenshaKigyouDtAddMiniCsvItemReader.read());
    }

    private StepExecution getStepExecutionFormat() {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "file/batch/kanrensha",
                "関連者企業団体形式テスト.csv");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
