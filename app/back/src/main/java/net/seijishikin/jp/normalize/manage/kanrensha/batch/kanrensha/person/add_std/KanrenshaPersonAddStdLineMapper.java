package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_std;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

/**
 * 関連者個人最小登録LineMapper
 */
@Component
public class KanrenshaPersonAddStdLineMapper implements LineMapper<KanrenshaPersonAddStdDto> {

    /** 名称カラム位置 */
    private static final int POS_NAME = 0;

    /** 全住所カラム位置 */
    private static final int POS_ADDRESS = 1;

    /** 個人職業カラム位置 */
    private static final int POS_SHOKUGYOU = 2;

    /** 住所郵便番号までカラム位置 */
    private static final int POS_ADDRESS_POSTAL = 3;

    /** 住所番地までカラム位置 */
    private static final int POS_ADDRESS_BLOCK = 4;

    /** 住所建物までカラム位置 */
    private static final int POS_ADDRESS_BUILDING = 5;

    /** 郵便番号1カラム位置 */
    private static final int POS_POSTAL1 = 6;

    /** 郵便番号2カラム位置 */
    private static final int POS_POSTAL2 = 7;

    /** 電話番号市外局番カラム位置 */
    private static final int POS_PHONE1 = 8;

    /** 電話番号局番カラム位置 */
    private static final int POS_PHONE2 = 9;

    /** 電話番号番号カラム位置 */
    private static final int POS_PHONE3 = 10;

    /** メールアドレスカラム位置 */
    private static final int POS_EMAIL = 11;

    /** 自分の公式サイトカラム位置 */
    private static final int POS_MYPORTAL_URL = 12;

    /** 外国籍該否カラム位置 */
    private static final int POS_FOREIGN = 13;

    /** 姓名の姓 */
    private static final int POS_LAST_NAME = 14;

    /** 姓名の名 */
    private static final int POS_FIRST_NAME = 15;

    /** 姓名のミドルネーム */
    private static final int POS_MIDDLE_NAME = 16;

    /** 姓名の姓のかな */
    private static final int POS_LAST_NAME_KANA = 17;

    /** 姓名の名のかな */
    private static final int POS_FIRST_NAME_KANA = 18;

    /** 姓名のミドルネームのかな */
    private static final int POS_MIDDLE_KANA = 19;

    /** 職業の業種 */
    private static final int POS_GYOUSHU = 20;

    /** 職業の役職 */
    private static final int POS_YAKUSHOKU = 21;

    /** 職業のユーザ記載 */
    private static final int POS_USER_WRITE = 22;

    /** 職業法人番号 */
    private static final int POS_CORP_NO = 23;

    /** 職業法人住所 */
    private static final int POS_CORP_ADDRESS = 24;

    /** 職業法人名 */
    private static final int POS_CORP_NAME = 25;

    /** 地方公共団体コード */
    private static final int POS_LGCODE = 26;
    /** 町字Id */
    private static final int POS_MACHIAZA = 27;
    /** 街区Id */
    private static final int POS_BLOCK = 28;
    /** 住居Id */
    private static final int POS_RSDT = 29;
    /** 住居2Id */
    private static final int POS_RSDT2 = 30;

    /** 空文字 */
    private static final String EMPTY = "";

    /** 該当文言リスト */
    private final List<String> listGaitou = new ArrayList<>();

    /**
     * コンストラクタ
     */
    public KanrenshaPersonAddStdLineMapper() {
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
    public KanrenshaPersonAddStdDto mapLine(final String line, final int lineNumber) throws Exception {
        KanrenshaPersonAddStdDto dto = new KanrenshaPersonAddStdDto();
        String[] cell = line.split(",");

        // 個人名称カラム位置
        dto.setKanrenshaName(this.removeQuote(cell[POS_NAME]));
        // 全住所カラム位置
        dto.setAllAddress(this.removeQuote(cell[POS_ADDRESS]));
        // 個人職業カラム位置
        dto.setPersonShokugyou(this.removeQuote(cell[POS_SHOKUGYOU]));

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

        // 姓名の姓
        dto.setLastName(this.removeQuote(cell[POS_LAST_NAME]));
        // 姓名の名
        dto.setFirstName(this.removeQuote(cell[POS_FIRST_NAME]));
        // 姓名のミドルネーム
        dto.setMiddleName(this.removeQuote(cell[POS_MIDDLE_NAME]));
        // 姓名の姓のかな
        dto.setLastNameKana(this.removeQuote(cell[POS_LAST_NAME_KANA]));
        // 姓名の名のかな
        dto.setFirstNameKana(this.removeQuote(cell[POS_FIRST_NAME_KANA]));
        // 姓名のミドルネームのかな
        dto.setMiddleNameKana(this.removeQuote(cell[POS_MIDDLE_KANA]));
        // 職業の業種
        dto.setGyoushu(this.removeQuote(cell[POS_GYOUSHU]));
        // 職業の役職
        dto.setYakushoku(this.removeQuote(cell[POS_YAKUSHOKU]));
        // 職業のユーザ記載
        dto.setShokugyouUserWrite(this.removeQuote(cell[POS_USER_WRITE]));
        // 職業法人番号
        dto.setKigyouDtNo(this.removeQuote(cell[POS_CORP_NO]));
        // 職業法人住所
        dto.setKigyouDtAddress(this.removeQuote(cell[POS_CORP_ADDRESS]));
        // 職業法人名
        dto.setKigyouDtName(this.removeQuote(cell[POS_CORP_NAME]));

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
