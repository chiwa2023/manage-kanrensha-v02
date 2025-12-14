package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.history;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtHistoryEntity;

/**
 * KanrenshaKigyouDtHistoryProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("KanrenshaKigyouDtHistoryProcessorTest.sql")
@Transactional
class KanrenshaKigyouDtHistoryProcessorTest {

    /** テスト対象 */
    @Autowired
    private KanrenshaKigyouDtHistoryProcessor processor;

    @Test
    @Tag("TableTruncate")
    void testProcess() throws Exception {

        // 1. 必須項目が空の場合
        KanrenshaKigyouDtHistoryDto emptyDto = new KanrenshaKigyouDtHistoryDto();
        WkTblKanrenshaKigyouDtHistoryEntity emptyResult = processor.process(emptyDto);
        assertFalse(emptyResult.getIsAffected());
        assertEquals("名称が入力されていません;住所が入力されていません;関連者コードが入力されていません;", emptyResult.getJudgeReason());

        // 2. マスターに関連者コードと名称が合致するレコードが存在する場合
        KanrenshaKigyouDtHistoryDto validDto = new KanrenshaKigyouDtHistoryDto();
        validDto.setKanrenshaName("株式会社テスト");
        validDto.setAllAddress("東京都テスト区テスト町1-1");
        validDto.setKigyouDtDelegate("代表テスト");
        validDto.setKigyouDtKanrenshaCode("K-CODE-001");
        validDto.setOrgDelegateCode("D-CODE-001");
        WkTblKanrenshaKigyouDtHistoryEntity validResult = processor.process(validDto);
        assertFalse(validResult.getIsAffected());
        assertEquals("コードと名称に合致する関連者が存在します;", validResult.getJudgeReason());

        // 3. マスタに登録がなく正常に保存
        KanrenshaKigyouDtHistoryDto nameMismatchDto = new KanrenshaKigyouDtHistoryDto();
        nameMismatchDto.setKanrenshaName("株式会社テストではない");
        nameMismatchDto.setAllAddress("東京都テスト区テスト町1-1");
        nameMismatchDto.setKigyouDtDelegate("代表テスト");
        nameMismatchDto.setKigyouDtKanrenshaCode("K-CODE-001");
        WkTblKanrenshaKigyouDtHistoryEntity nameMismatchResult = processor.process(nameMismatchDto);
        // DTOからEntityへ正しく値がコピーされているか確認
        assertEquals(nameMismatchDto.getKanrenshaName(), nameMismatchResult.getKanrenshaName());
        assertEquals(nameMismatchDto.getAllAddress(), nameMismatchResult.getAllAddress());
        assertEquals(nameMismatchDto.getKigyouDtDelegate(), nameMismatchResult.getKigyouDtDelegate());
        assertEquals(nameMismatchDto.getKigyouDtKanrenshaCode(), nameMismatchResult.getKigyouDtKanrenshaCode());
        assertEquals(nameMismatchDto.getOrgDelegateCode(), nameMismatchResult.getOrgDelegateCode());
        assertTrue(nameMismatchResult.getIsAffected());
        assertEquals("正)", nameMismatchResult.getJudgeReason());

    }
}
