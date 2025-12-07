package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_std;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

/**
 * 関連者企業・団体最小登録LineMapper
 */
@Component
public class KanrenshaSeijidantaiAddStdLineMapper implements LineMapper<KanrenshaSeijidantaiAddStdDto> {

    /** 名称カラム位置 */
    private static final int POS_NAME = 0;

    /** 全住所カラム位置 */
    private static final int POS_ADDRESS = 1;

    /** 個人職業カラム位置 */
    private static final int POS_SHOKUGYOU = 2;
    
    /** 政治団体区分 */
    private static final int POS_DANTAI_KBN = 3;

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

    /** SNS名称カラム位置 */
    private static final int POS_SNS_NAME = 14;
    /** SNSアカウントカラム位置 */
    private static final int POS_SNS_ACCOUNT = 15;
    
    /** 関連者団体名称かな */
    private static final int POS_ORG_KANA = 16;
    
    /** 団体代表者関連者コード */
    private static final int POS_DELEGATE_CODE = 17;
    
    /** 会計責任者関連者個人コード */
    private static final int POS_ACCOUNT_MGR_CODE = 18;
    
    /** 会計責任者関連者個人氏名 */
    private static final int POS_ACCOUNT_MGR_NAME = 19;
    
    /** 地方公共団体コード */
    private static final int POS_LGCODE = 20;
    /** 町字Id */
    private static final int POS_MACHIAZA = 21;
    /** 街区Id */
    private static final int POS_BLOCK = 22;
    /** 住居Id */
    private static final int POS_RSDT = 23;
    /** 住居2Id */
    private static final int POS_RSDT2 = 24;

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
        dto.setSeijidantaiDelegate(this.removeQuote(cell[POS_SHOKUGYOU]));
        // 団体区分カラム位置
        dto.setDantaiKbn(this.removeQuote(cell[POS_DANTAI_KBN]));
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
        // SNS名称カラム位置
        dto.setSnsServiceName(this.removeQuote(cell[POS_SNS_NAME]));
        // SNSアカウントサイトカラム位置
        dto.setSnsAccount(this.removeQuote(cell[POS_SNS_ACCOUNT]));
        
        // 関連者団体名称かな
        dto.setOrgNameKana(this.removeQuote(cell[POS_ORG_KANA]));
        // 団体代表者関連者コード
        dto.setOrgDelegateCode(this.removeQuote(cell[POS_DELEGATE_CODE]));
        // 会計責任者関連者個人コード
        dto.setAccountMgrCode(this.removeQuote(cell[POS_ACCOUNT_MGR_CODE]));
        // 会計責任者関連者個人氏名
        dto.setAccountMgrName(this.removeQuote(cell[POS_ACCOUNT_MGR_NAME]));

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
