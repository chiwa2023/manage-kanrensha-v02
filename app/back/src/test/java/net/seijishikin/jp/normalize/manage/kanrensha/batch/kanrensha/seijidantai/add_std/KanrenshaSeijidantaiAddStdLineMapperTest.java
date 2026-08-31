package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_std;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * KanrenshaSeijidantaiAddStdLineMapper単体テスト
 */
class KanrenshaSeijidantaiAddStdLineMapperTest {
    // CHECKSTYLE:OFF MagicNumber

    @Test
    @Tag("TableTruncate")
    void testMapLine() throws Exception {
        KanrenshaSeijidantaiAddStdLineMapper lineMapper = new KanrenshaSeijidantaiAddStdLineMapper();

        // All fields are populated correctly
        // "kanrenshaName","allAddress","seijidantaiDelegate","dantaiKbn","poliOrgNo","addressPostal","addressBlock","addressBuilding","postalcode1","postalcode2","phon1","phon2","phon3","email","myPortalUrl","snsServiceName","snsAccount","orgNameKana","orgDelegateCode","accountMgrCode","accountMgrName","lgCode","machiazaId","blkId","prcId","rsdtId","rsdt2Id"

        final String quote = "\"";
        final String comma = ",";
        StringBuffer buffer = new StringBuffer();
        buffer//
                .append(quote).append("名称").append(quote).append(comma)// "名称",
                .append(quote).append("全住所").append(quote).append(comma)// "全住所",
                .append(quote).append("政治団体代表者").append(quote).append(comma)// "政治団体代表者",
                .append(quote).append("政治団体区分").append(quote).append(comma)// "政治団体区分",
                .append(quote).append("政治団体番号").append(quote).append(comma)// "政治団体番号",
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
                .append(quote).append("SNS名称").append(quote).append(comma)// "SNS名称",
                .append(quote).append("SNSアカウント").append(quote).append(comma)// "SNSアカウント",
                .append(quote).append("関連者団体名称かな").append(quote).append(comma)// "関連者団体名称かな",
                .append(quote).append("団体代表者関連者コード").append(quote).append(comma)// "団体代表者関連者コード",
                .append(quote).append("会計責任者関連者個人コード").append(quote).append(comma)// "会計責任者関連者個人コード",
                .append(quote).append("会計責任者関連者個人氏名").append(quote).append(comma)// "会計責任者関連者個人氏名",
                .append(quote).append("地方公共団体コード").append(quote).append(comma)// "地方公共団体コード",
                .append(quote).append("町字Id").append(quote).append(comma)// "町字Id",
                .append(quote).append("街区Id").append(quote).append(comma)// "街区Id",
                .append(quote).append("地番Id").append(quote).append(comma)// "地番Id",
                .append(quote).append("住居Id").append(quote).append(comma)// "住居Id",
                .append(quote).append("住居2Id").append(quote);// "住居2Id",

        KanrenshaSeijidantaiAddStdDto dto = lineMapper.mapLine(buffer.toString(), 1);

        assertEquals("名称", dto.getKanrenshaName());
        assertEquals("全住所", dto.getAllAddress());
        assertEquals("政治団体代表者", dto.getSeijidantaiDelegate());
        assertEquals("政治団体区分", dto.getDantaiKbn());
        assertEquals("政治団体番号", dto.getPoliOrgNo());
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
        assertEquals("SNS名称", dto.getSnsServiceName());
        assertEquals("SNSアカウント", dto.getSnsAccount());
        assertEquals("関連者団体名称かな", dto.getOrgNameKana());
        assertEquals("団体代表者関連者コード", dto.getOrgDelegateCode());
        assertEquals("会計責任者関連者個人コード", dto.getAccountMgrCode());
        assertEquals("会計責任者関連者個人氏名", dto.getAccountMgrName());
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
