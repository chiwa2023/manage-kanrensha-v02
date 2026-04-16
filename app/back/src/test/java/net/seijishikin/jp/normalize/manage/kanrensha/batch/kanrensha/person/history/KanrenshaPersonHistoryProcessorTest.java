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

    /** テスト対象 */
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

        // 2. マスターに関連者コードと名称が合致するレコードが存在する場合
        KanrenshaPersonHistoryDto foundDto = new KanrenshaPersonHistoryDto();
        foundDto.setKanrenshaName("テスト　太郎");
        foundDto.setAllAddress("東京都テスト区テスト町1-1");
        foundDto.setPersonShokugyou("会社員");
        foundDto.setPersonKanrenshaCode("P-CODE-001");
        WkTblKanrenshaPersonHistoryEntity foundResult = processor.process(foundDto);
        assertFalse(foundResult.getIsAffected());
        assertEquals("コードと名称に合致する関連者が存在します;", foundResult.getJudgeReason());

        // 3. 全くの新規データの場合
        KanrenshaPersonHistoryDto newDto = new KanrenshaPersonHistoryDto();
        newDto.setKanrenshaName("新規　花子");
        newDto.setAllAddress("大阪府新規市新規町2-2");
        newDto.setPersonShokugyou("自営業");
        newDto.setPersonKanrenshaCode("P-CODE-002");
        WkTblKanrenshaPersonHistoryEntity newResult = processor.process(newDto);
        assertTrue(newResult.getIsAffected());
        assertEquals("正)", newResult.getJudgeReason());

        // DTOからEntityへ正しく値がコピーされているか確認
        assertEquals(newDto.getKanrenshaName(), newResult.getKanrenshaName());
        assertEquals(newDto.getAllAddress(), newResult.getAllAddress());
        assertEquals(newDto.getPersonShokugyou(), newResult.getPersonShokugyou());
        assertEquals(newDto.getPersonKanrenshaCode(), newResult.getPersonKanrenshaCode());
    }
}
