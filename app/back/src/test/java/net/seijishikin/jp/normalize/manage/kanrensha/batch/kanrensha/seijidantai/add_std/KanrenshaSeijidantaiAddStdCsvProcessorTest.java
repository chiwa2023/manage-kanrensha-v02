package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_std;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiMasterEntity;

/**
 * KanrenshaSeijidantaiAddStdCsvProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("KanrenshaSeijidantaiAddStdCsvProcessorTest.sql")
class KanrenshaSeijidantaiAddStdCsvProcessorTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private KanrenshaSeijidantaiAddStdCsvProcessor kanrenshaSeijidantaiAddStdCsvProcessor;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void test() throws Exception {

        // 1. All required fields are empty
        KanrenshaSeijidantaiAddStdDto emptyDto = new KanrenshaSeijidantaiAddStdDto();
        WkTblKanrenshaSeijidantaiMasterEntity resultEntity = kanrenshaSeijidantaiAddStdCsvProcessor.process(emptyDto);
        assertEquals(false, resultEntity.getIsAffected());
        assertEquals(
                "名称が入力されていません;住所が入力されていません;団体区分が入力されていません;住所郵便番号までが入力されていません;住所番地までが入力されていません;電話番号市外局番が入力されていません;電話番号局番が入力されていません;電話番号番号が入力されていません;メールアドレスが入力されていません;",
                resultEntity.getJudgeReason());

        // 2. Fields exceed length limits
        KanrenshaSeijidantaiAddStdDto lengthDto = createValidDto();
        lengthDto.setKanrenshaName("a".repeat(101));
        lengthDto.setAllAddress("a".repeat(101));
        lengthDto.setSeijidantaiDelegate("a".repeat(101));
        lengthDto.setPoliOrgNo("a".repeat(31));
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
        resultEntity = kanrenshaSeijidantaiAddStdCsvProcessor.process(lengthDto);
        assertEquals(false, resultEntity.getIsAffected());
        assertEquals(
                "政治団体名が100文字を超えています;政治団体全住所が100文字を超えています;政治団体代表者が100文字を超えています;政治団体番号が30文字を超えています;メールアドレスが100文字を超えています;電話番号市外局番が10文字を超えています;電話番号局番が10文字を超えています;電話番号番号が10文字を超えています;郵便番号1が8文字を超えています;郵便番号2が8文字を超えています;地方自治体コードが8文字を超えています;町字コードが9文字を超えています;街区コードが5文字を超えています;地番コードが17文字を超えています;住居コードが5文字を超えています;住居2コードが7文字を超えています;",
                resultEntity.getJudgeReason());

        // 3. Duplicate history entry
        KanrenshaSeijidantaiAddStdDto duplicateHistoryDto = createValidDto();
        duplicateHistoryDto.setKanrenshaName("テスト政治団体");
        duplicateHistoryDto.setAllAddress("東京都架空市架空町");
        duplicateHistoryDto.setSeijidantaiDelegate("代表者A");
        resultEntity = kanrenshaSeijidantaiAddStdCsvProcessor.process(duplicateHistoryDto);
        assertEquals(false, resultEntity.getIsAffected());
        assertEquals("すでに登録が存在します(3-1310-12-345678-1234567);", resultEntity.getJudgeReason());

        // 4. Same name exists in master
        KanrenshaSeijidantaiAddStdDto sameNameDto = createValidDto();
        sameNameDto.setKanrenshaName("同じ名前の政治団体");
        resultEntity = kanrenshaSeijidantaiAddStdCsvProcessor.process(sameNameDto);
        assertEquals(false, resultEntity.getIsAffected());
        assertEquals("同名の団体があります。確認調査の上、必要に応じて追加してください;", resultEntity.getJudgeReason());

        // 5. Valid new entry
        KanrenshaSeijidantaiAddStdDto validDto = createValidDto();
        resultEntity = kanrenshaSeijidantaiAddStdCsvProcessor.process(validDto);
        assertEquals(true, resultEntity.getIsAffected());
        assertEquals("正)", resultEntity.getJudgeReason());

        // Check if all values are set correctly
        assertEquals(validDto.getKanrenshaName(), resultEntity.getKanrenshaName());
        assertEquals(validDto.getAllAddress(), resultEntity.getAllAddress());
        assertEquals(validDto.getSeijidantaiDelegate(), resultEntity.getSeijidantaiDelegate());
        assertEquals(validDto.getDantaiKbn(), resultEntity.getDantaiKbn());
        assertEquals(validDto.getPoliOrgNo(), resultEntity.getPoliOrgNo());
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
        assertEquals(validDto.getMyPortalUrl(), resultEntity.getMyPortalUrl());
        assertEquals(validDto.getSnsServiceName(), resultEntity.getSnsServiceName());
        assertEquals(validDto.getSnsAccount(), resultEntity.getSnsAccount());
        assertEquals(validDto.getOrgNameKana(), resultEntity.getOrgNameKana());
        assertEquals(validDto.getOrgDelegateCode(), resultEntity.getOrgDelegateCode());
        assertEquals(validDto.getAccountMgrCode(), resultEntity.getAccountMgrCode());
        assertEquals(validDto.getAccountMgrName(), resultEntity.getAccountMgrName());
    }

    private KanrenshaSeijidantaiAddStdDto createValidDto() {
        KanrenshaSeijidantaiAddStdDto dto = new KanrenshaSeijidantaiAddStdDto();
        dto.setKanrenshaName("新規政治団体");
        dto.setAllAddress("東京都新宿区西新宿2-8-1");
        dto.setSeijidantaiDelegate("新規代表");
        dto.setDantaiKbn("A1");
        dto.setPoliOrgNo("12345");
        dto.setAddressPostal("東京都新宿区西新宿");
        dto.setAddressBlock("2-8-1");
        dto.setAddressBuilding("都庁");
        dto.setPhon1("03");
        dto.setPhon2("5321");
        dto.setPhon3("1111");
        dto.setEmail("test@seiji.com");
        dto.setPostalcode1("163");
        dto.setPostalcode2("8001");
        dto.setLgCode("131041");
        dto.setMachiazaId("123456");
        dto.setBlkId("123");
        dto.setPrcId("1234567890123456");
        dto.setRsdtId("1234");
        dto.setRsdt2Id("123456");
        dto.setMyPortalUrl("http://example.com");
        dto.setSnsServiceName("twitter");
        dto.setSnsAccount("@test");
        dto.setOrgNameKana("シンキセイジダンタイ");
        dto.setOrgDelegateCode("DEL-001");
        dto.setAccountMgrCode("ACC-001");
        dto.setAccountMgrName("会計太郎");
        return dto;
    }
}
