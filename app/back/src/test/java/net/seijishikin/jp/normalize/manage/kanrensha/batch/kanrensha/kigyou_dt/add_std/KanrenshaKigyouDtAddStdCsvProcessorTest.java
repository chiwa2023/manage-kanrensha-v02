package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_std;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtMasterEntity;

/**
 * KanrenshaKigyouDtAddStdCsvProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("KanrenshaKigyouDtAddStdCsvProcessorTest.sql")
class KanrenshaKigyouDtAddStdCsvProcessorTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private KanrenshaKigyouDtAddStdCsvProcessor kanrenshaKigyouDtAddStdCsvProcessor;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void test() throws Exception {

        // 1. All required fields are empty
        KanrenshaKigyouDtAddStdDto emptyDto = new KanrenshaKigyouDtAddStdDto();
        WkTblKanrenshaKigyouDtMasterEntity resultEntity = kanrenshaKigyouDtAddStdCsvProcessor.process(emptyDto);
        assertEquals(false, resultEntity.getIsAffected());
        assertEquals(
                "名称が入力されていません;住所が入力されていません;法人番号が入力されていません;住所郵便番号までが入力されていません;住所番地までが入力されていません;電話番号市外局番が入力されていません;電話番号局番が入力されていません;電話番号番号が入力されていません;メールアドレスが入力されていません;",
                resultEntity.getJudgeReason());

        // 2. Fields exceed length limits
        KanrenshaKigyouDtAddStdDto lengthDto = createValidDto();
        lengthDto.setKanrenshaName("a".repeat(101));
        lengthDto.setAllAddress("a".repeat(101));
        lengthDto.setKigyouDtDelegate("a".repeat(101));
        lengthDto.setHoujinNo("a".repeat(31));
        lengthDto.setEmail("a".repeat(101));
        lengthDto.setPhon1("1".repeat(11));
        lengthDto.setPhon2("1".repeat(11));
        lengthDto.setPhon3("1".repeat(11));
        lengthDto.setPostalcode1("1".repeat(9));
        lengthDto.setPostalcode2("1".repeat(9));
        lengthDto.setLgCode("1".repeat(9));
        lengthDto.setMachiazaId("1".repeat(10));
        lengthDto.setBlkId("1".repeat(6));
        lengthDto.setPrcId("1".repeat(18));
        lengthDto.setRsdtId("1".repeat(6));
        lengthDto.setRsdt2Id("1".repeat(8));
        resultEntity = kanrenshaKigyouDtAddStdCsvProcessor.process(lengthDto);
        assertEquals(false, resultEntity.getIsAffected());
        assertEquals(
                "企業・団体名が100文字を超えています;企業・団体全住所が100文字を超えています;企業・団体代表者が100文字を超えています;法人番号が30文字を超えています;メールアドレスが100文字を超えています;電話番号市外局番が10文字を超えています;電話番号局番が10文字を超えています;電話番号番号が10文字を超えています;郵便番号1が8文字を超えています;郵便番号2が8文字を超えています;地方自治体コードが8文字を超えています;町字コードが9文字を超えています;街区コードが5文字を超えています;地番コードが17文字を超えています;住居コードが5文字を超えています;住居2コードが7文字を超えています;",
                resultEntity.getJudgeReason());

        // 3. Duplicate history entry
        KanrenshaKigyouDtAddStdDto duplicateHistoryDto = createValidDto();
        duplicateHistoryDto.setKanrenshaName("ぼったくり企業");
        duplicateHistoryDto.setAllAddress("福岡県架空市山麓町");
        duplicateHistoryDto.setKigyouDtDelegate("代表者　太郎");
        resultEntity = kanrenshaKigyouDtAddStdCsvProcessor.process(duplicateHistoryDto);
        assertEquals(false, resultEntity.getIsAffected());
        assertEquals("すでに登録が存在します(1-2345-67-890123-4567890);", resultEntity.getJudgeReason());

        // 4. Same name exists in master
        KanrenshaKigyouDtAddStdDto sameNameDto = createValidDto();
        sameNameDto.setKanrenshaName("ふんだくり企業");
        resultEntity = kanrenshaKigyouDtAddStdCsvProcessor.process(sameNameDto);
        assertEquals(false, resultEntity.getIsAffected());
        assertEquals("同名の団体があります。確認調査の上、必要に応じて追加してください;", resultEntity.getJudgeReason());

        // 5. Valid new entry
        KanrenshaKigyouDtAddStdDto validDto = createValidDto();
        resultEntity = kanrenshaKigyouDtAddStdCsvProcessor.process(validDto);
        assertEquals(true, resultEntity.getIsAffected());
        assertEquals("正)", resultEntity.getJudgeReason());

        // Check if all values are set correctly
        assertEquals(validDto.getKanrenshaName(), resultEntity.getKanrenshaName());
        assertEquals(validDto.getAllAddress(), resultEntity.getAllAddress());
        assertEquals(validDto.getKigyouDtDelegate(), resultEntity.getKigyouDtDelegate());
        assertEquals(validDto.getHoujinNo(), resultEntity.getHoujinNo());
        assertEquals(validDto.getAddressPostal(), resultEntity.getAddressPostal());
        assertEquals(validDto.getAddressBlock(), resultEntity.getAddressBlock());
        assertEquals(validDto.getAddressBuilding(), resultEntity.getAddressBuilding());
        assertEquals(validDto.getPhon1(), resultEntity.getPhon1());
        assertEquals(validDto.getPhon2(), resultEntity.getPhon2());
        assertEquals(validDto.getPhon3(), resultEntity.getPhon3());
        assertEquals(validDto.getEmail(), resultEntity.getEmail());
        assertEquals(validDto.getPostalcode1(), resultEntity.getPostalcode1());
        assertEquals(validDto.getPostalcode2(), resultEntity.getPostalcode2());
        assertEquals(validDto.getLgCode(), resultEntity.getLgCode());
        assertEquals(validDto.getMachiazaId(), resultEntity.getMachiazaId());
        assertEquals(validDto.getBlkId(), resultEntity.getBlkId());
        assertEquals(validDto.getPrcId(), resultEntity.getPrcId());
        assertEquals(validDto.getRsdtId(), resultEntity.getRsdtId());
        assertEquals(validDto.getRsdt2Id(), resultEntity.getRsdt2Id());
        assertEquals(validDto.getOrgNameKana(), resultEntity.getOrgNameKana());
        assertEquals(validDto.getIsShiten(), resultEntity.getIsShiten());
        assertEquals(validDto.getOrgDelegateCode(), resultEntity.getOrgDelegateCode());
        assertEquals(validDto.getMyPortalUrl(), resultEntity.getMyPortalUrl());
        assertEquals(validDto.getSnsServiceName(), resultEntity.getSnsServiceName());
        assertEquals(validDto.getSnsAccount(), resultEntity.getSnsAccount());
        assertEquals(validDto.getHoujinSbts(), resultEntity.getHoujinSbts());
        assertEquals(validDto.getIsForeign(), resultEntity.getIsForeign());
    }

    private KanrenshaKigyouDtAddStdDto createValidDto() {
        KanrenshaKigyouDtAddStdDto dto = new KanrenshaKigyouDtAddStdDto();
        dto.setKanrenshaName("株式会社テスト");
        dto.setAllAddress("東京都千代田区1-1 テストビル1F");
        dto.setKigyouDtDelegate("テスト代表");
        dto.setHoujinNo("1234567890123");
        dto.setAddressPostal("東京都千代田区");
        dto.setAddressBlock("1-1");
        dto.setAddressBuilding("テストビル1F");
        dto.setPhon1("03");
        dto.setPhon2("1234");
        dto.setPhon3("5678");
        dto.setEmail("test@example.com");
        dto.setPostalcode1("100");
        dto.setPostalcode2("0001");
        dto.setLgCode("131012");
        dto.setMachiazaId("123456789");
        dto.setBlkId("12345");
        dto.setPrcId("12345678901234567");
        dto.setRsdtId("12345");
        dto.setRsdt2Id("1234567");
        dto.setOrgNameKana("カブシキガイシャテスト");
        dto.setIsShiten(false);
        dto.setOrgDelegateCode("ORG_DELEGATE_CODE_TEST");
        dto.setMyPortalUrl("https://www.example.com");
        dto.setSnsServiceName("Twitter");
        dto.setSnsAccount("@testaccount");
        dto.setHoujinSbts("株式会社");
        dto.setIsForeign(false);
        return dto;
    }
}
