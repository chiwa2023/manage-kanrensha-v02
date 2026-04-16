package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_std;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * KanrenshaPersonAddStdLineMapper単体テスト
 */
class KanrenshaPersonAddStdLineMapperTest {
    // CHECKSTYLE:OFF MagicNumber

    @Test
    @Tag("TableTruncate")
    void testMapLine() throws Exception {
        KanrenshaPersonAddStdLineMapper lineMapper = new KanrenshaPersonAddStdLineMapper();

        // All fields are populated correctly

        final String quote = "\"";
        final String comma = ",";
        StringBuffer buffer = new StringBuffer();
        buffer//
                .append(quote).append("名称").append(quote).append(comma)// "名称",
                .append(quote).append("全住所").append(quote).append(comma)// "全住所",
                .append(quote).append("個人職業").append(quote).append(comma)// "個人職業",
                .append(quote).append("住所郵便番号まで").append(quote).append(comma)// "住所郵便番号まで",
                .append(quote).append("住所番地まで").append(quote).append(comma)// "住所番地まで",
                .append(quote).append("住所建物まで").append(quote).append(comma)// "住所建物まで",
                .append(quote).append("郵便番号1").append(quote).append(comma)// "郵便番号1",
                .append(quote).append("郵便番号2").append(quote).append(comma)// "郵便番号2",
                .append(quote).append("電話番号市外局番").append(quote).append(comma)// "電話番号市外局番",
                .append(quote).append("電話番号局番").append(quote).append(comma)// "電話番号局番",
                .append(quote).append("電話番号番号").append(quote).append(comma)// "電話番号番号",
                .append(quote).append("メールアドレス").append(quote).append(comma)// "メールアドレス",
                .append(quote).append("自分の公式サイト").append(quote).append(comma)// "自分の公式サイト",
                .append(quote).append("SNSサービス名称").append(quote).append(comma)// "SNSサービス名称",
                .append(quote).append("SNSサービスアカウント").append(quote).append(comma)// "SNSサービスアカウント",
                .append(quote).append("該当").append(quote).append(comma)// "外国籍該否",
                .append(quote).append("姓名の姓").append(quote).append(comma)// "姓名の姓",
                .append(quote).append("姓名の名").append(quote).append(comma)// "姓名の名",
                .append(quote).append("姓名のミドルネーム").append(quote).append(comma)// "姓名のミドルネーム",
                .append(quote).append("姓名の姓のかな").append(quote).append(comma)// "姓名の姓のかな",
                .append(quote).append("姓名の名のかな").append(quote).append(comma)// "姓名の名のかな",
                .append(quote).append("姓名のミドルネームのかな").append(quote).append(comma)// "姓名のミドルネームのかな",
                .append(quote).append("職業の業種").append(quote).append(comma)// "職業の業種",
                .append(quote).append("職業の役職").append(quote).append(comma)// "職業の役職",
                .append(quote).append("職業のユーザ記載").append(quote).append(comma)// "職業のユーザ記載",
                .append(quote).append("職業法人番号").append(quote).append(comma)// "職業法人番号",
                .append(quote).append("職業法人住所").append(quote).append(comma)// "職業法人住所",
                .append(quote).append("職業法人名").append(quote).append(comma)// "職業法人名",
                .append(quote).append("地方公共団体コード").append(quote).append(comma)// "地方公共団体コード",
                .append(quote).append("町字Id").append(quote).append(comma)// "町字Id",
                .append(quote).append("街区Id").append(quote).append(comma)// "街区Id",
                .append(quote).append("地番Id").append(quote).append(comma)// "地番Id",
                .append(quote).append("住居Id").append(quote).append(comma)// "住居Id",
                .append(quote).append("住居2Id").append(quote);// "住居2Id"

        KanrenshaPersonAddStdDto dto = lineMapper.mapLine(buffer.toString(), 1);

        assertEquals("名称", dto.getKanrenshaName());
        assertEquals("全住所", dto.getAllAddress());
        assertEquals("個人職業", dto.getPersonShokugyou());
        assertEquals("住所郵便番号まで", dto.getAddressPostal());
        assertEquals("住所番地まで", dto.getAddressBlock());
        assertEquals("住所建物まで", dto.getAddressBuilding());
        assertEquals("郵便番号1", dto.getPostalcode1());
        assertEquals("郵便番号2", dto.getPostalcode2());
        assertEquals("電話番号市外局番", dto.getPhon1());
        assertEquals("電話番号局番", dto.getPhon2());
        assertEquals("電話番号番号", dto.getPhon3());
        assertEquals("メールアドレス", dto.getEmail());
        assertEquals("自分の公式サイト", dto.getMyPortalUrl());
        assertEquals("SNSサービス名称", dto.getSnsServiceName());
        assertEquals("SNSサービスアカウント", dto.getSnsAccount());
        assertTrue(dto.getIsForeign());
        assertEquals("姓名の姓", dto.getLastName());
        assertEquals("姓名の名", dto.getFirstName());
        assertEquals("姓名のミドルネーム", dto.getMiddleName());
        assertEquals("姓名の姓のかな", dto.getLastNameKana());
        assertEquals("姓名の名のかな", dto.getFirstNameKana());
        assertEquals("姓名のミドルネームのかな", dto.getMiddleNameKana());
        assertEquals("職業の業種", dto.getGyoushu());
        assertEquals("職業の役職", dto.getYakushoku());
        assertEquals("職業のユーザ記載", dto.getShokugyouUserWrite());
        assertEquals("職業法人番号", dto.getKigyouDtNo());
        assertEquals("職業法人住所", dto.getKigyouDtAddress());
        assertEquals("職業法人名", dto.getKigyouDtName());
        assertEquals("地方公共団体コード", dto.getLgCode());
        assertEquals("町字Id", dto.getMachiazaId());
        assertEquals("街区Id", dto.getBlkId());
        assertEquals("地番Id", dto.getPrcId());
        assertEquals("住居Id", dto.getRsdtId());
        assertEquals("住居2Id", dto.getRsdt2Id());

        // Test with insufficient columns
        String incompleteLine = "\"Test Name\"";
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> lineMapper.mapLine(incompleteLine, 4));
    }
}