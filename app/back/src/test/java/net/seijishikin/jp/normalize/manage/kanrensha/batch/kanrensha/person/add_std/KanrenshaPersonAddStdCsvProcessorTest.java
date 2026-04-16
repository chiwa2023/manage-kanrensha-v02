package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_std;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonMasterEntity;

/**
 * KanrenshaPersonAddStdCsvProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("KanrenshaPersonAddStdCsvProcessorTest.sql")
class KanrenshaPersonAddStdCsvProcessorTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private KanrenshaPersonAddStdCsvProcessor kanrenshaPersonAddStdCsvProcessor;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void test() throws Exception {

        // 1. All required fields are empty
        KanrenshaPersonAddStdDto emptyDto = new KanrenshaPersonAddStdDto();
        WkTblKanrenshaPersonMasterEntity resultEntity = kanrenshaPersonAddStdCsvProcessor.process(emptyDto);
        assertEquals(false, resultEntity.getIsAffected());
        assertEquals(
                "名称が入力されていません;住所が入力されていません;住所郵便番号までが入力されていません;住所番地までが入力されていません;電話番号市外局番が入力されていません;電話番号局番が入力されていません;電話番号番号が入力されていません;メールアドレスが入力されていません;",
                resultEntity.getJudgeReason());

        // 2. Fields exceed length limits
        KanrenshaPersonAddStdDto lengthDto = createValidDto();
        lengthDto.setKanrenshaName("a".repeat(101));
        lengthDto.setAllAddress("a".repeat(101));
        lengthDto.setPersonShokugyou("a".repeat(101));
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
        resultEntity = kanrenshaPersonAddStdCsvProcessor.process(lengthDto);
        assertEquals(false, resultEntity.getIsAffected());
        assertEquals(
                "個人名が100文字を超えています;個人全住所が100文字を超えています;個人職業が100文字を超えています;電子メールが100文字を超えています;電話番号市外局番が10文字を超えています;電話番号局番が10文字を超えています;電話番号番号が10文字を超えています;郵便番号1が8文字を超えています;郵便番号2が8文字を超えています;地方自治体コードが8文字を超えています;町字コードが9文字を超えています;街区コードが5文字を超えています;地番コードが17文字を超えています;住居コードが5文字を超えています;住居2コードが7文字を超えています;",
                resultEntity.getJudgeReason());

        // 3. Duplicate history entry
        KanrenshaPersonAddStdDto duplicateHistoryDto = createValidDto();
        duplicateHistoryDto.setKanrenshaName("テスト　太郎");
        duplicateHistoryDto.setAllAddress("福岡県架空市山麓町");
        duplicateHistoryDto.setPersonShokugyou("無職");
        resultEntity = kanrenshaPersonAddStdCsvProcessor.process(duplicateHistoryDto);
        assertEquals(false, resultEntity.getIsAffected());
        assertEquals("すでに登録が存在します(2-2345-67-890123-4567890);", resultEntity.getJudgeReason());

        // 4. Same name exists in master
        KanrenshaPersonAddStdDto sameNameDto = createValidDto();
        sameNameDto.setKanrenshaName("テスト　次郎");
        resultEntity = kanrenshaPersonAddStdCsvProcessor.process(sameNameDto);
        assertEquals(false, resultEntity.getIsAffected());
        assertEquals("同名の団体があります。確認調査の上、必要に応じて追加してください;", resultEntity.getJudgeReason());

        // 5. Valid new entry
        KanrenshaPersonAddStdDto validDto = createValidDto();
        resultEntity = kanrenshaPersonAddStdCsvProcessor.process(validDto);
        assertEquals(true, resultEntity.getIsAffected());
        assertEquals("正)", resultEntity.getJudgeReason());

        // Check if all values are set correctly
        assertEquals(validDto.getKanrenshaName(), resultEntity.getKanrenshaName());
        assertEquals(validDto.getAllAddress(), resultEntity.getAllAddress());
        assertEquals(validDto.getPersonShokugyou(), resultEntity.getPersonShokugyou());
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
        assertEquals(validDto.getLastName(), resultEntity.getLastName());
        assertEquals(validDto.getFirstName(), resultEntity.getFirstName());
        assertEquals(validDto.getMiddleName(), resultEntity.getMiddleName());
        assertEquals(validDto.getLastNameKana(), resultEntity.getLastNameKana());
        assertEquals(validDto.getFirstNameKana(), resultEntity.getFirstNameKana());
        assertEquals(validDto.getMiddleNameKana(), resultEntity.getMiddleNameKana());
        assertEquals(validDto.getGyoushu(), resultEntity.getGyoushu());
        assertEquals(validDto.getYakushoku(), resultEntity.getYakushoku());
        assertEquals(validDto.getShokugyouUserWrite(), resultEntity.getShokugyouUserWrite());
        assertEquals(validDto.getKigyouDtNo(), resultEntity.getKigyouDtNo());
        assertEquals(validDto.getKigyouDtAddress(), resultEntity.getKigyouDtAddress());
        assertEquals(validDto.getKigyouDtName(), resultEntity.getKigyouDtName());
        assertEquals(validDto.getMyPortalUrl(), resultEntity.getMyPortalUrl());
        assertEquals(validDto.getSnsServiceName(), resultEntity.getSnsServiceName());
        assertEquals(validDto.getSnsAccount(), resultEntity.getSnsAccount());
        assertEquals(validDto.getIsForeign(), resultEntity.getIsForeign());
    }

    private KanrenshaPersonAddStdDto createValidDto() {
        KanrenshaPersonAddStdDto dto = new KanrenshaPersonAddStdDto();
        dto.setKanrenshaName("テスト　三郎");
        dto.setAllAddress("東京都千代田区1-1 テストビル1F");
        dto.setPersonShokugyou("会社員");
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
        dto.setLastName("テスト");
        dto.setFirstName("三郎");
        dto.setMiddleName("");
        dto.setLastNameKana("テスト");
        dto.setFirstNameKana("サブロウ");
        dto.setMiddleNameKana("");
        dto.setGyoushu("IT");
        dto.setYakushoku("エンジニア");
        dto.setShokugyouUserWrite("ソフトウェアエンジニア");
        dto.setKigyouDtNo("KIGYOU001");
        dto.setKigyouDtAddress("東京都千代田区");
        dto.setKigyouDtName("テスト株式会社");
        dto.setMyPortalUrl("https://www.example.com");
        dto.setSnsServiceName("Twitter");
        dto.setSnsAccount("@testaccount");
        dto.setIsForeign(false);
        return dto;
    }
}
