package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_std;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

/**
 * 関連者政治団体標準登録LineMapper
 */
@Component
public class KanrenshaSeijidantaiAddStdLineMapper implements LineMapper<KanrenshaSeijidantaiAddStdDto> {

    /** 名称カラム位置 */
    private static final int POS_NAME = 0;

    /** 全住所カラム位置 */
    private static final int POS_ADDRESS = 1;

    /** 政治団体代表者カラム位置 */
    private static final int POS_SEIJIDANTAI_DELEGATE = 2;

    /** 政治団体区分 */
    private static final int POS_DANTAI_KBN = 3;

    /** 政治団体番号カラム位置 */
    private static final int POS_POLI_ORG_NO = 4;

    /** 住所郵便番号までカラム位置 */
    private static final int POS_ADDRESS_POSTAL = 5;

    /** 住所番地までカラム位置 */
    private static final int POS_ADDRESS_BLOCK = 6;

    /** 住所建物までカラム位置 */
    private static final int POS_ADDRESS_BUILDING = 7;

    /** 郵便番号1カラム位置 */
    private static final int POS_POSTALCODE1 = 8;

    /** 郵便番号2カラム位置 */
    private static final int POS_POSTALCODE2 = 9;

    /** 電話番号市外局番カラム位置 */
    private static final int POS_PHONE1 = 10;

    /** 電話番号局番カラム位置 */
    private static final int POS_PHONE2 = 11;

    /** 電話番号番号カラム位置 */
    private static final int POS_PHONE3 = 12;

    /** メールアドレスカラム位置 */
    private static final int POS_EMAIL = 13;

    /** 自分の公式サイトカラム位置 */
    private static final int POS_MYPORTAL_URL = 14;

    /** SNS名称カラム位置 */
    private static final int POS_SNS_NAME = 15;
    
    /** SNSアカウントカラム位置 */
    private static final int POS_SNS_ACCOUNT = 16;

    /** 関連者団体名称かなカラム位置 */
    private static final int POS_ORG_KANA = 17;

    /** 団体代表者関連者コードカラム位置 */
    private static final int POS_ORG_DELEGATE_CODE = 18;

    /** 会計責任者関連者個人コードカラム位置 */
    private static final int POS_ACCOUNT_MGR_CODE = 19;

    /** 会計責任者関連者個人氏名カラム位置 */
    private static final int POS_ACCOUNT_MGR_NAME = 20;

    /** 地方公共団体コードカラム位置 */
    private static final int POS_LGCODE = 21;
    /** 町字Idカラム位置 */
    private static final int POS_MACHIAZA = 22;
    /** 街区Idカラム位置 */
    private static final int POS_BLK = 23;
    /** 地番Idカラム位置 */
    private static final int POS_PRC = 24;
    /** 住居Idカラム位置 */
    private static final int POS_RSDT = 25;
    /** 住居2Idカラム位置 */
    private static final int POS_RSDT2 = 26;

    /** 空文字 */
    private static final String EMPTY = "";

    /** 該当文言リスト */
    private final List<String> listGaitou = new ArrayList<>();

    /**
     * コンストラクタ
     */
    public KanrenshaSeijidantaiAddStdLineMapper() {
        listGaitou.add("はい");
        listGaitou.add("1");
        listGaitou.add("１");
        listGaitou.add("true");
        listGaitou.add("該当");
    }

    /**
     * 処理を行う
     */
    @Override
    public KanrenshaSeijidantaiAddStdDto mapLine(final String line, final int lineNumber) throws Exception {
        KanrenshaSeijidantaiAddStdDto dto = new KanrenshaSeijidantaiAddStdDto();
        String[] cell = line.split(",");

        // 個人名称カラム位置
        dto.setKanrenshaName(this.removeQuote(cell[POS_NAME]));
        // 全住所カラム位置
        dto.setAllAddress(this.removeQuote(cell[POS_ADDRESS]));
        // 政治団体代表者名カラム位置
        dto.setSeijidantaiDelegate(this.removeQuote(cell[POS_SEIJIDANTAI_DELEGATE]));
        // 団体区分カラム位置
        dto.setDantaiKbn(this.removeQuote(cell[POS_DANTAI_KBN]));
        // 政治団体番号カラム位置
        dto.setPoliOrgNo(this.removeQuote(cell[POS_POLI_ORG_NO]));
        
        // 住所郵便番号までカラム位置
        dto.setAddressPostal(this.removeQuote(cell[POS_ADDRESS_POSTAL]));
        // 住所番地までカラム位置
        dto.setAddressBlock(this.removeQuote(cell[POS_ADDRESS_BLOCK]));
        // 住所建物までカラム位置
        dto.setAddressBuilding(this.removeQuote(cell[POS_ADDRESS_BUILDING]));
        // 郵便番号1カラム位置
        dto.setPostalcode1(this.removeQuote(cell[POS_POSTALCODE1]));
        // 郵便番号2カラム位置
        dto.setPostalcode2(this.removeQuote(cell[POS_POSTALCODE2]));
        // 電話番号市外局番カラム位置
        dto.setPhon1(this.removeQuote(cell[POS_PHONE1]));
        // 電話番号局番カラム位置
        dto.setPhon2(this.removeQuote(cell[POS_PHONE2]));
        // 電話番号番号カラム位置
        dto.setPhon3(this.removeQuote(cell[POS_PHONE3]));
        // メールアドレスカラム位置
        dto.setEmail(this.removeQuote(cell[POS_EMAIL]));
        // 自分の公式サイトカラム位置
        dto.setMyPortalUrl(this.removeQuote(cell[POS_MYPORTAL_URL]));
        // SNS名称カラム位置
        dto.setSnsServiceName(this.removeQuote(cell[POS_SNS_NAME]));
        // SNSアカウントサイトカラム位置
        dto.setSnsAccount(this.removeQuote(cell[POS_SNS_ACCOUNT]));

        // 関連者団体名称かなカラム位置
        dto.setOrgNameKana(this.removeQuote(cell[POS_ORG_KANA]));
        // 団体代表者関連者コードカラム位置
        dto.setOrgDelegateCode(this.removeQuote(cell[POS_ORG_DELEGATE_CODE]));
        // 会計責任者関連者個人コードカラム位置
        dto.setAccountMgrCode(this.removeQuote(cell[POS_ACCOUNT_MGR_CODE]));
        // 会計責任者関連者個人氏名カラム位置
        dto.setAccountMgrName(this.removeQuote(cell[POS_ACCOUNT_MGR_NAME]));

        // 地方公共団体コードカラム位置
        dto.setLgCode(this.removeQuote(cell[POS_LGCODE]));
        // 町字Idカラム位置
        dto.setMachiazaId(this.removeQuote(cell[POS_MACHIAZA]));
        // 街区Idカラム位置
        dto.setBlkId(this.removeQuote(cell[POS_BLK]));
        // 地番Idカラム位置
        dto.setPrcId(this.removeQuote(cell[POS_PRC]));
        // 住居Idカラム位置
        dto.setRsdtId(this.removeQuote(cell[POS_RSDT]));
        // 住居2Idカラム位置
        dto.setRsdt2Id(this.removeQuote(cell[POS_RSDT2]));

        return dto;
    }

    private String removeQuote(final String data) {

        String dataNew = data;

        return dataNew.replaceAll("\"", EMPTY);
    }

}
