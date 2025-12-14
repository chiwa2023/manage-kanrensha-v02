package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiAddMinEntity;

/**
 * KanrenshaSeijidantaiAddMiniCsvProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("KanrenshaSeijidantaiAddMiniCsvProcessorTest.sql")
@Transactional
class KanrenshaSeijidantaiAddMiniCsvProcessorTest {

    /** テスト対象 */
    @Autowired
    private KanrenshaSeijidantaiAddMiniCsvProcessor processor;

    @Test
    @Tag("TableTruncate")
    void testProcess() throws Exception {
        // 1. 必須項目が空の場合
        KanrenshaSeijidantaiAddMiniDto emptyDto = new KanrenshaSeijidantaiAddMiniDto();
        WkTblKanrenshaSeijidantaiAddMinEntity emptyResult = processor.process(emptyDto);
        assertFalse(emptyResult.getIsAffected());
        assertEquals("名称が入力されていません;住所が入力されていません;政治団体区分が入力されていません;", emptyResult.getJudgeReason());

        // 2. 履歴に全く同じデータが存在する場合
        KanrenshaSeijidantaiAddMiniDto duplicateDto = createValidDto();
        duplicateDto.setKanrenshaName("テスト政治団体");
        duplicateDto.setAllAddress("東京都架空市架空町");
        duplicateDto.setSeijidantaiDelegate("代表者A");
        WkTblKanrenshaSeijidantaiAddMinEntity duplicateResult = processor.process(duplicateDto);
        assertFalse(duplicateResult.getIsAffected());
        assertEquals("すでに登録が存在します(3-1310-12-345678-1234567);", duplicateResult.getJudgeReason());

        // 3. マスタに同名の団体が存在する場合
        KanrenshaSeijidantaiAddMiniDto sameNameDto = createValidDto();
        sameNameDto.setKanrenshaName("同じ名前の政治団体");
        sameNameDto.setDantaiKbn("01"); // 有効な団体区分を設定
        WkTblKanrenshaSeijidantaiAddMinEntity sameNameResult = processor.process(sameNameDto);
        assertFalse(sameNameResult.getIsAffected());
        assertEquals("同名の団体があります。確認調査の上、必要に応じて追加してください;", sameNameResult.getJudgeReason());

        // 4. 正常な新規データの場合
        KanrenshaSeijidantaiAddMiniDto validDto = createValidDto();
        WkTblKanrenshaSeijidantaiAddMinEntity validResult = processor.process(validDto);
        assertTrue(validResult.getIsAffected());
        assertEquals("正)", validResult.getJudgeReason());

        // DTOからEntityへの値のコピーを検証
        assertEquals(validDto.getKanrenshaName(), validResult.getKanrenshaName());
        assertEquals(validDto.getAllAddress(), validResult.getAllAddress());
        assertEquals(validDto.getSeijidantaiDelegate(), validResult.getSeijidantaiDelegate());
        assertEquals(validDto.getDantaiKbn(), validResult.getDantaiKbn());
    }

    private KanrenshaSeijidantaiAddMiniDto createValidDto() {
        KanrenshaSeijidantaiAddMiniDto dto = new KanrenshaSeijidantaiAddMiniDto();
        dto.setKanrenshaName("新規政治団体");
        dto.setAllAddress("東京都新規区新規町1-2-3");
        dto.setSeijidantaiDelegate("新規代表");
        dto.setDantaiKbn("01");
        return dto;
    }
}