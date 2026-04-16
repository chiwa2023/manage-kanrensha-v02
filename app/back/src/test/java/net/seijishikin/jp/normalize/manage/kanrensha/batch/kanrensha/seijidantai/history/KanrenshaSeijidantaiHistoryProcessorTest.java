package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.history;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiHistoryEntity;

/**
 * KanrenshaSeijidantaiHistoryProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("KanrenshaSeijidantaiHistoryProcessorTest.sql")
class KanrenshaSeijidantaiHistoryProcessorTest {

    /** テスト対象 */
    @Autowired
    private KanrenshaSeijidantaiHistoryProcessor processor;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testProcess() throws Exception {
        // 1. 必須項目が空の場合
        KanrenshaSeijidantaiHistoryDto emptyDto = new KanrenshaSeijidantaiHistoryDto();
        WkTblKanrenshaSeijidantaiHistoryEntity emptyResult = processor.process(emptyDto);
        assertFalse(emptyResult.getIsAffected());
        assertEquals("名称が入力されていません;住所が入力されていません;関連者コードが入力されていません;", emptyResult.getJudgeReason());

        // 2. マスターに関連者コードと名称が合致するレコードが存在する場合
        KanrenshaSeijidantaiHistoryDto validDto = new KanrenshaSeijidantaiHistoryDto();
        validDto.setKanrenshaName("テスト政治団体");
        validDto.setAllAddress("東京都テスト区テスト町1-1");
        validDto.setSeijidantaiDelegate("代表テスト");
        validDto.setSeijidantaiKanrenshaCode("S-CODE-001");
        validDto.setOrgDelegateCode("D-CODE-001");
        WkTblKanrenshaSeijidantaiHistoryEntity validResult = processor.process(validDto);
        assertFalse(validResult.getIsAffected());
        assertEquals("コードと名称に合致する関連者が存在します;", validResult.getJudgeReason());

        // 3. 合致するレコードが存在しない場合は登録する
        KanrenshaSeijidantaiHistoryDto nameMismatchDto = new KanrenshaSeijidantaiHistoryDto();
        nameMismatchDto.setKanrenshaName("テスト政治団体ではない");
        nameMismatchDto.setAllAddress("東京都テスト区テスト町1-1");
        nameMismatchDto.setSeijidantaiDelegate("代表テスト");
        nameMismatchDto.setSeijidantaiKanrenshaCode("S-CODE-001");
        WkTblKanrenshaSeijidantaiHistoryEntity nameMismatchResult = processor.process(nameMismatchDto);

        // DTOからEntityへ正しく値がコピーされているか確認
        assertEquals(nameMismatchDto.getKanrenshaName(), nameMismatchResult.getKanrenshaName());
        assertEquals(nameMismatchDto.getAllAddress(), nameMismatchResult.getAllAddress());
        assertEquals(nameMismatchDto.getSeijidantaiDelegate(), nameMismatchResult.getSeijidantaiDelegate());
        assertEquals(nameMismatchDto.getSeijidantaiKanrenshaCode(), nameMismatchResult.getSeijidantaiKanrenshaCode());
        assertEquals(nameMismatchDto.getOrgDelegateCode(), nameMismatchResult.getOrgDelegateCode());
        assertTrue(nameMismatchResult.getIsAffected());
        assertEquals("正)", nameMismatchResult.getJudgeReason());

    }
}
