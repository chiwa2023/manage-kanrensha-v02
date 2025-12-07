package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_std;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

/**
 * 関連者企業・団体最小登録LineMapper
 */
@Component
public class KanrenshaKigyouDtAddStdLineMapper implements LineMapper<KanrenshaKigyouDtAddStdDto> {

    /** 名称カラム位置 */
    private static final int POS_NAME = 0;

    /** 全住所カラム位置 */
    private static final int POS_ADDRESS = 1;

    /** 団体代表者カラム位置 */
    private static final int POS_ORG_DELEGATE = 2;
    
    /** 法人番号カラム位置 */
    private static final int POS_HOUJIN_NO = 3;

    /** 住所郵便番号までカラム位置 */
    private static final int POS_ADDRESS_POSTAL = 4;

    /** 住所番地までカラム位置 */
    private static final int POS_ADDRESS_BLOCK = 5;

    /** 住所建物までカラム位置 */
    private static final int POS_ADDRESS_BUILDING = 6;

    /** 郵便番号1カラム位置 */
    private static final int POS_POSTAL1 = 7;

    /** 郵便番号2カラム位置 */
    private static final int POS_POSTAL2 = 8;

    /** 電話番号市外局番カラム位置 */
    private static final int POS_PHONE1 = 9;

    /** 電話番号局番カラム位置 */
    private static final int POS_PHONE2 = 10;

    /** 電話番号番号カラム位置 */
    private static final int POS_PHONE3 = 11;

    /** メールアドレスカラム位置 */
    private static final int POS_EMAIL = 12;

    /** 自分の公式サイトカラム位置 */
    private static final int POS_MYPORTAL_URL = 13;

    /** 外国籍該否カラム位置 */
    private static final int POS_FOREIGN = 14;

    /** 法人種別カラム位置 */
    private static final int POS_HOUJIN_SBTS = 15;

    /** 関連者団体名称かなカラム位置 */
    private static final int POS_ORG_NAME_KANA = 16;

    /** 支店該当カラム位置 */
    private static final int POS_IS_SHITEN = 17;

    /** 団体代表者関連者コードカラム位置 */
    private static final int POS_DELEGEATE_CODE = 18;

    /** 団体代表者関連者コードカラム位置 */
    private static final int POS_SNS_NAME = 19;

    /** 団体代表者関連者コードカラム位置 */
    private static final int POS_SNS_ACCOUNT = 20;

    /** 地方公共団体コード */
    private static final int POS_LGCODE = 21;
    /** 町字Id */
    private static final int POS_MACHIAZA = 22;
    /** 街区Id */
    private static final int POS_BLOCK = 23;
    /** 住居Id */
    private static final int POS_RSDT = 24;
    /** 住居2Id */
    private static final int POS_RSDT2 = 25;

    /** 空文字 */
    private static final String EMPTY = "";

    /** 該当文言リスト */
    private final List<String> listGaitou = new ArrayList<>();

    /**
     * コンストラクタ
     */
    public KanrenshaKigyouDtAddStdLineMapper() {
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
    public KanrenshaKigyouDtAddStdDto mapLine(final String line, final int lineNumber) throws Exception {
        KanrenshaKigyouDtAddStdDto dto = new KanrenshaKigyouDtAddStdDto();
        String[] cell = line.split(",");

        // 企業・団体名称カラム位置
        dto.setKanrenshaName(this.removeQuote(cell[POS_NAME]));
        // 全住所カラム位置
        dto.setAllAddress(this.removeQuote(cell[POS_ADDRESS]));
        // 企業・団体代表者カラム位置
        dto.setKigyouDtDelegate(this.removeQuote(cell[POS_ORG_DELEGATE]));
        // 企業・団体代表者カラム位置
        dto.setHoujinNo(this.removeQuote(cell[POS_HOUJIN_NO]));
        
        // 住所郵便番号までカラム位置
        dto.setAddressPostal(this.removeQuote(cell[POS_ADDRESS_POSTAL]));
        // 住所番地までカラム位置
        dto.setAddressBlock(this.removeQuote(cell[POS_ADDRESS_BLOCK]));
        // 住所建物までカラム位置
        dto.setAddressBuilding(this.removeQuote(cell[POS_ADDRESS_BUILDING]));
        // 郵便番号1カラム位置
        dto.setPostal1(this.removeQuote(cell[POS_POSTAL1]));
        // 郵便番号2カラム位置
        dto.setPostal2(this.removeQuote(cell[POS_POSTAL2]));
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
        // 外国籍該否カラム位置
        dto.setIsForeign(listGaitou.contains(this.removeQuote(cell[POS_FOREIGN])));

        // 法人種別カラム位置
        dto.setHoujinSbts(this.removeQuote(cell[POS_HOUJIN_SBTS]));
        // 関連者団体名称かなカラム位置
        dto.setOrgNameKana(this.removeQuote(cell[POS_ORG_NAME_KANA]));
        // 支店該当カラム位置
        dto.setIsShiten(listGaitou.contains(this.removeQuote(cell[POS_IS_SHITEN])));
        // 団体代表者関連者コードカラム位置
        dto.setOrgDelegateCode(this.removeQuote(cell[POS_DELEGEATE_CODE]));
        // SNS名称カラム位置
        dto.setSnsServiceName(this.removeQuote(cell[POS_SNS_NAME]));
        // SNSアカウントカラム位置
        dto.setSnsAccount(this.removeQuote(cell[POS_SNS_ACCOUNT]));
        
        // 地方公共団体コード
        dto.setLgCode(this.removeQuote(cell[POS_LGCODE]));
        // 町字Id
        dto.setMachiazaId(this.removeQuote(cell[POS_MACHIAZA]));
        // 街区Id
        dto.setBlkId(this.removeQuote(cell[POS_BLOCK]));
        // 住居Id
        dto.setRsdtId(this.removeQuote(cell[POS_RSDT]));
        // 住居2Id
        dto.setRsdt2Id(this.removeQuote(cell[POS_RSDT2]));

        return dto;
    }

    private String removeQuote(final String data) {

        String dataNew = data;

        return dataNew.replaceAll("\"", EMPTY);
    }

}
