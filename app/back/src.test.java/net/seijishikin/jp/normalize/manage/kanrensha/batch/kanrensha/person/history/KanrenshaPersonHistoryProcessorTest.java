package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonHistoryEntity;

/**
 * KanrenshaPersonHistoryProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("KanrenshaPersonHistoryProcessorTest.sql")
class KanrenshaPersonHistoryProcessorTest {

    @Autowired
    private KanrenshaPersonHistoryProcessor processor;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testProcess() throws Exception {
        // 1. 必須項目が空の場合
        KanrenshaPersonHistoryDto emptyDto = new KanrenshaPersonHistoryDto();
        WkTblKanrenshaPersonHistoryEntity emptyResult = processor.process(emptyDto);
        assertFalse(emptyResult.getIsAffected());
        assertEquals("名称が入力されていません;住所が入力されていません;関連者コードが入力されていません;", emptyResult.getJudgeReason());

        // 2. マスターに関連者コードと名称が合致するレコードが存在しない場合
        KanrenshaPersonHistoryDto notFoundDto = new KanrenshaPersonHistoryDto();
        notFoundDto.setKanrenshaName("存在しない太郎");
        notFoundDto.setAllAddress("どこかの住所");
        notFoundDto.setPersonKanrenshaCode("P-CODE-999");
        WkTblKanrenshaPersonHistoryEntity notFoundResult = processor.process(notFoundDto);
        assertFalse(notFoundResult.getIsAffected());
        assertEquals("コードと名称に合致する関連者が存在しません;", notFoundResult.getJudgeReason());

        // 3. 関連者コードは存在するが名称が合致しない場合
        KanrenshaPersonHistoryDto nameMismatchDto = new KanrenshaPersonHistoryDto();
        nameMismatchDto.setKanrenshaName("テスト　太郎ではない");
        nameMismatchDto.setAllAddress("東京都テスト区テスト町1-1");
        nameMismatchDto.setPersonKanrenshaCode("P-CODE-001");
        WkTblKanrenshaPersonHistoryEntity nameMismatchResult = processor.process(nameMismatchDto);
        assertFalse(nameMismatchResult.getIsAffected());
        assertEquals("コードと名称に合致する関連者が存在しません;", nameMismatchResult.getJudgeReason());

        // 4. 正常なデータの場合
        KanrenshaPersonHistoryDto validDto = new KanrenshaPersonHistoryDto();
        validDto.setKanrenshaName("テスト　太郎");
        validDto.setAllAddress("東京都テスト区テスト町1-1");
        validDto.setPersonShokugyou("会社員");
        validDto.setPersonKanrenshaCode("P-CODE-001");
        WkTblKanrenshaPersonHistoryEntity validResult = processor.process(validDto);
        assertTrue(validResult.getIsAffected());
        assertEquals("正)", validResult.getJudgeReason());

        // DTOからEntityへ正しく値がコピーされているか確認
        assertEquals(validDto.getKanrenshaName(), validResult.getKanrenshaName());
        assertEquals(validDto.getAllAddress(), validResult.getAllAddress());
        assertEquals(validDto.getPersonShokugyou(), validResult.getPersonShokugyou());
        assertEquals(validDto.getPersonKanrenshaCode(), validResult.getPersonKanrenshaCode());
    }
}
