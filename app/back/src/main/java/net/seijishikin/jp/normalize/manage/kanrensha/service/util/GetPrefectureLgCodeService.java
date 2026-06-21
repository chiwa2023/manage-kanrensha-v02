package net.seijishikin.jp.normalize.manage.kanrensha.service.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionStringDto;

/**
 * 住所から県の地方自治体コード前2桁を返却するService
 */
@Service
public class GetPrefectureLgCodeService {

    /** 010006,北海道 */
    public static final int PREF_01 = 1;
    /** 020001,青森県 */
    public static final int PREF_02 = 2;
    /** 030007,岩手県 */
    public static final int PREF_03 = 3;
    /** 040002,宮城県 */
    public static final int PREF_04 = 4;
    /** 050008,秋田県 */
    public static final int PREF_05 = 5;
    /** 060003,山形県 */
    public static final int PREF_06 = 6;
    /** 070009,福島県 */
    public static final int PREF_07 = 7;
    /** 080004,茨城県 */
    public static final int PREF_08 = 8;
    /** 090000,栃木県 */
    public static final int PREF_09 = 9;
    /** 100005,群馬県 */
    public static final int PREF_10 = 10;
    /** 110001,埼玉県 */
    public static final int PREF_11 = 11;
    /** 120006,千葉県 */
    public static final int PREF_12 = 12;
    /** 130001,東京都 */
    public static final int PREF_13 = 13;
    /** 140007,神奈川県 */
    public static final int PREF_14 = 14;
    /** 150002,新潟県 */
    public static final int PREF_15 = 15;
    /** 160008,富山県 */
    public static final int PREF_16 = 16;
    /** 170003,石川県 */
    public static final int PREF_17 = 17;
    /** 180009,福井県 */
    public static final int PREF_18 = 18;
    /** 190004,山梨県 */
    public static final int PREF_19 = 19;
    /** 200000,長野県 */
    public static final int PREF_20 = 20;
    /** 210005,岐阜県 */
    public static final int PREF_21 = 21;
    /** 220001,静岡県 */
    public static final int PREF_22 = 22;
    /** 230006,愛知県 */
    public static final int PREF_23 = 23;
    /** 240001,三重県 */
    public static final int PREF_24 = 24;
    /** 250007,滋賀県 */
    public static final int PREF_25 = 25;
    /** 260002,京都府 */
    public static final int PREF_26 = 26;
    /** 270008,大阪府 */
    public static final int PREF_27 = 27;
    /** 280003,兵庫県 */
    public static final int PREF_28 = 28;
    /** 290009,奈良県 */
    public static final int PREF_29 = 29;
    /** 300004,和歌山県 */
    public static final int PREF_30 = 30;
    /** 310000,鳥取県 */
    public static final int PREF_31 = 31;
    /** 320005,島根県 */
    public static final int PREF_32 = 32;
    /** 330001,岡山県 */
    public static final int PREF_33 = 33;
    /** 340006,広島県 */
    public static final int PREF_34 = 34;
    /** 350001,山口県 */
    public static final int PREF_35 = 35;
    /** 360007,徳島県 */
    public static final int PREF_36 = 36;
    /** 370002,香川県 */
    public static final int PREF_37 = 37;
    /** 380008,愛媛県 */
    public static final int PREF_38 = 38;
    /** 390003,高知県 */
    public static final int PREF_39 = 39;
    /** 400009,福岡県 */
    public static final int PREF_40 = 40;
    /** 410004,佐賀県 */
    public static final int PREF_41 = 41;
    /** 420000,長崎県 */
    public static final int PREF_42 = 42;
    /** 430005,熊本県 */
    public static final int PREF_43 = 43;
    /** 440001,大分県 */
    public static final int PREF_44 = 44;
    /** 450006,宮崎県 */
    public static final int PREF_45 = 45;
    /** 460001,鹿児島県 */
    public static final int PREF_46 = 46;
    /** 470007,沖縄県 */
    public static final int PREF_47 = 47;
    /** その他 */
    public static final int PREF_99 = 99;

    /** 010006,北海道_コード */
    public static final String PREF_01_CODE = "01";
    /** 010006,北海道_名称 */
    public static final String PREF_01_LABEL = "北海道";
    /** 020001,青森県_コード */
    public static final String PREF_02_CODE = "02";
    /** 020001,青森県_名称 */
    public static final String PREF_02_LABEL = "青森県";
    /** 030007,岩手県_コード */
    public static final String PREF_03_CODE = "03";
    /** 030007,岩手県_名称 */
    public static final String PREF_03_LABEL = "岩手県";
    /** 040002,宮城県_コード */
    public static final String PREF_04_CODE = "04";
    /** 040002,宮城県_名称 */
    public static final String PREF_04_LABEL = "宮城県";
    /** 050008,秋田県_コード */
    public static final String PREF_05_CODE = "05";
    /** 050008,秋田県_名称 */
    public static final String PREF_05_LABEL = "秋田県";
    /** 060003,山形県_コード */
    public static final String PREF_06_CODE = "06";
    /** 060003,山形県_名称 */
    public static final String PREF_06_LABEL = "山形県";
    /** 070009,福島県_コード */
    public static final String PREF_07_CODE = "07";
    /** 070009,福島県_名称 */
    public static final String PREF_07_LABEL = "福島県";
    /** 080004,茨城県_コード */
    public static final String PREF_08_CODE = "08";
    /** 080004,茨城県_名称 */
    public static final String PREF_08_LABEL = "茨城県";
    /** 090000,栃木県_コード */
    public static final String PREF_09_CODE = "09";
    /** 090000,栃木県_名称 */
    public static final String PREF_09_LABEL = "栃木県";
    /** 100005,群馬県_コード */
    public static final String PREF_10_CODE = "10";
    /** 100005,群馬県_名称 */
    public static final String PREF_10_LABEL = "群馬県";
    /** 110001,埼玉県_コード */
    public static final String PREF_11_CODE = "11";
    /** 110001,埼玉県_名称 */
    public static final String PREF_11_LABEL = "埼玉県";
    /** 120006,千葉県_コード */
    public static final String PREF_12_CODE = "12";
    /** 120006,千葉県_名称 */
    public static final String PREF_12_LABEL = "千葉県";
    /** 130001,東京都_コード */
    public static final String PREF_13_CODE = "13";
    /** 130001,東京都_名称 */
    public static final String PREF_13_LABEL = "東京都";
    /** 140007,神奈川県_コード */
    public static final String PREF_14_CODE = "14";
    /** 140007,神奈川県_名称 */
    public static final String PREF_14_LABEL = "神奈川県";
    /** 150002,新潟県_コード */
    public static final String PREF_15_CODE = "15";
    /** 150002,新潟県_名称 */
    public static final String PREF_15_LABEL = "新潟県";
    /** 160008,富山県_コード */
    public static final String PREF_16_CODE = "16";
    /** 160008,富山県_名称 */
    public static final String PREF_16_LABEL = "富山県";
    /** 170003,石川県_コード */
    public static final String PREF_17_CODE = "17";
    /** 170003,石川県_名称 */
    public static final String PREF_17_LABEL = "石川県";
    /** 180009,福井県_コード */
    public static final String PREF_18_CODE = "18";
    /** 180009,福井県_名称 */
    public static final String PREF_18_LABEL = "福井県";
    /** 190004,山梨県_コード */
    public static final String PREF_19_CODE = "19";
    /** 190004,山梨県_名称 */
    public static final String PREF_19_LABEL = "山梨県";
    /** 200000,長野県_コード */
    public static final String PREF_20_CODE = "20";
    /** 200000,長野県_名称 */
    public static final String PREF_20_LABEL = "長野県";
    /** 210005,岐阜県_コード */
    public static final String PREF_21_CODE = "21";
    /** 210005,岐阜県_名称 */
    public static final String PREF_21_LABEL = "岐阜県";
    /** 220001,静岡県_コード */
    public static final String PREF_22_CODE = "22";
    /** 220001,静岡県_名称 */
    public static final String PREF_22_LABEL = "静岡県";
    /** 230006,愛知県_コード */
    public static final String PREF_23_CODE = "23";
    /** 230006,愛知県_名称 */
    public static final String PREF_23_LABEL = "愛知県";
    /** 240001,三重県_コード */
    public static final String PREF_24_CODE = "24";
    /** 240001,三重県_名称 */
    public static final String PREF_24_LABEL = "三重県";
    /** 250007,滋賀県_コード */
    public static final String PREF_25_CODE = "25";
    /** 250007,滋賀県_名称 */
    public static final String PREF_25_LABEL = "滋賀県";
    /** 260002,京都府_コード */
    public static final String PREF_26_CODE = "26";
    /** 260002,京都府_名称 */
    public static final String PREF_26_LABEL = "京都府";
    /** 270008,大阪府_コード */
    public static final String PREF_27_CODE = "27";
    /** 270008,大阪府_名称 */
    public static final String PREF_27_LABEL = "大阪府";
    /** 280003,兵庫県_コード */
    public static final String PREF_28_CODE = "28";
    /** 280003,兵庫県_名称 */
    public static final String PREF_28_LABEL = "兵庫県";
    /** 290009,奈良県_コード */
    public static final String PREF_29_CODE = "29";
    /** 290009,奈良県_名称 */
    public static final String PREF_29_LABEL = "奈良県";
    /** 300004,和歌山県_コード */
    public static final String PREF_30_CODE = "30";
    /** 300004,和歌山県_名称 */
    public static final String PREF_30_LABEL = "和歌山県";
    /** 310000,鳥取県_コード */
    public static final String PREF_31_CODE = "31";
    /** 310000,鳥取県_名称 */
    public static final String PREF_31_LABEL = "鳥取県";
    /** 320005,島根県_コード */
    public static final String PREF_32_CODE = "32";
    /** 320005,島根県_名称 */
    public static final String PREF_32_LABEL = "島根県";
    /** 330001,岡山県_コード */
    public static final String PREF_33_CODE = "33";
    /** 330001,岡山県_名称 */
    public static final String PREF_33_LABEL = "岡山県";
    /** 340006,広島県_コード */
    public static final String PREF_34_CODE = "34";
    /** 340006,広島県_名称 */
    public static final String PREF_34_LABEL = "広島県";
    /** 350001,山口県_コード */
    public static final String PREF_35_CODE = "35";
    /** 350001,山口県_名称 */
    public static final String PREF_35_LABEL = "山口県";
    /** 360007,徳島県_コード */
    public static final String PREF_36_CODE = "36";
    /** 360007,徳島県_名称 */
    public static final String PREF_36_LABEL = "徳島県";
    /** 370002,香川県_コード */
    public static final String PREF_37_CODE = "37";
    /** 370002,香川県_名称 */
    public static final String PREF_37_LABEL = "香川県";
    /** 380008,愛媛県_コード */
    public static final String PREF_38_CODE = "38";
    /** 380008,愛媛県_名称 */
    public static final String PREF_38_LABEL = "愛媛県";
    /** 390003,高知県_コード */
    public static final String PREF_39_CODE = "39";
    /** 390003,高知県_名称 */
    public static final String PREF_39_LABEL = "高知県";
    /** 400009,福岡県_コード */
    public static final String PREF_40_CODE = "40";
    /** 400009,福岡県_名称 */
    public static final String PREF_40_LABEL = "福岡県";
    /** 410004,佐賀県_コード */
    public static final String PREF_41_CODE = "41";
    /** 410004,佐賀県_名称 */
    public static final String PREF_41_LABEL = "佐賀県";
    /** 420000,長崎県_コード */
    public static final String PREF_42_CODE = "42";
    /** 420000,長崎県_名称 */
    public static final String PREF_42_LABEL = "長崎県";
    /** 430005,熊本県_コード */
    public static final String PREF_43_CODE = "43";
    /** 430005,熊本県_名称 */
    public static final String PREF_43_LABEL = "熊本県";
    /** 440001,大分県_コード */
    public static final String PREF_44_CODE = "44";
    /** 440001,大分県_名称 */
    public static final String PREF_44_LABEL = "大分県";
    /** 450006,宮崎県_コード */
    public static final String PREF_45_CODE = "45";
    /** 450006,宮崎県_名称 */
    public static final String PREF_45_LABEL = "宮崎県";
    /** 460001,鹿児島県_コード */
    public static final String PREF_46_CODE = "46";
    /** 460001,鹿児島県_名称 */
    public static final String PREF_46_LABEL = "鹿児島県";
    /** 470007,沖縄県_コード */
    public static final String PREF_47_CODE = "47";
    /** 470007,沖縄県_名称 */
    public static final String PREF_47_LABEL = "沖縄県";
    /** その他_コード */
    public static final String PREF_99_CODE = "99";
    /** その他_名称 */
    public static final String PREF_99_LABEL = "その他";

    /**
     * 処理を行う
     *
     * @param address 住所
     * @return 県の地方自治体コード前2桁
     */
    public String practiceString(final String address) { // SUPPRESS CHECKSTYLE ReturnCount NOPMD

        // 東京都 14,047,594人
        // 130001,東京都
        if (address.startsWith(PREF_13_LABEL)) {
            return PREF_13_CODE;
        }

        // 神奈川県 9,237,337人
        // 140007,神奈川県
        if (address.startsWith(PREF_14_LABEL)) {
            return PREF_14_CODE;
        }

        // 大阪府 8,837,685人
        // 270008,大阪府
        if (address.startsWith(PREF_27_LABEL)) {
            return PREF_27_CODE;
        }

        // 愛知県 7,542,415人
        // 230006,愛知県
        if (address.startsWith(PREF_23_LABEL)) {
            return PREF_23_CODE;
        }

        // 埼玉県 7,344,765人
        // 110001,埼玉県
        if (address.startsWith(PREF_11_LABEL)) {
            return PREF_11_CODE;
        }

        // 千葉県 6,284,480人
        // 120006,千葉県
        if (address.startsWith(PREF_12_LABEL)) {
            return PREF_12_CODE;
        }

        // 兵庫県 5,465,002人
        // 280003,兵庫県
        if (address.startsWith(PREF_28_LABEL)) {
            return PREF_28_CODE;
        }

        // 北海道 5,224,614人
        // 010006,北海道
        if (address.startsWith(PREF_01_LABEL)) {
            return PREF_01_CODE;
        }

        // 福岡県 5,135,214人
        // 400009,福岡県
        if (address.startsWith(PREF_40_LABEL)) {
            return PREF_40_CODE;
        }

        // 静岡県 3,633,202人
        // 220001,静岡県
        if (address.startsWith(PREF_22_LABEL)) {
            return PREF_22_CODE;
        }

        // 茨城県 2,867,009人
        // 080004,茨城県
        if (address.startsWith(PREF_08_LABEL)) {
            return PREF_08_CODE;
        }

        // 広島県 2,799,702人
        // 340006,広島県
        if (address.startsWith(PREF_34_LABEL)) {
            return PREF_34_CODE;
        }

        // 京都府 2,578,087人
        // 260002,京都府
        if (address.startsWith(PREF_26_LABEL)) {
            return PREF_26_CODE;
        }

        // 宮城県 2,301,996人
        // 040002,宮城県
        if (address.startsWith(PREF_04_LABEL)) {
            return PREF_04_CODE;
        }

        // 新潟県 2,201,272人
        // 150002,新潟県
        if (address.startsWith(PREF_15_LABEL)) {
            return PREF_15_CODE;
        }

        // 長野県 2,048,011人
        // 200000,長野県
        if (address.startsWith(PREF_20_LABEL)) {
            return PREF_20_CODE;
        }

        // 岐阜県 1,978,742人
        // 210005,岐阜県
        if (address.startsWith(PREF_21_LABEL)) {
            return PREF_21_CODE;
        }

        // 群馬県 1,939,110人
        // 100005,群馬県
        if (address.startsWith(PREF_10_LABEL)) {
            return PREF_10_CODE;
        }

        // 栃木県 1,933,146人
        // 090000,栃木県
        if (address.startsWith(PREF_09_LABEL)) {
            return PREF_09_CODE;
        }

        // 岡山県 1,888,432人
        // 330001,岡山県
        if (address.startsWith(PREF_33_LABEL)) {
            return PREF_33_CODE;
        }

        // 福島県 1,833,152人
        // 070009,福島県
        if (address.startsWith(PREF_07_LABEL)) {
            return PREF_07_CODE;
        }

        // 三重県 1,770,254人
        // 240001,三重県
        if (address.startsWith(PREF_24_LABEL)) {
            return PREF_24_CODE;
        }

        // 熊本県 1,738,301人
        // 430005,熊本県
        if (address.startsWith(PREF_43_LABEL)) {
            return PREF_43_CODE;
        }

        // 鹿児島県 1,588,256人
        // 460001,鹿児島県
        if (address.startsWith(PREF_46_LABEL)) {
            return PREF_46_CODE;
        }

        // 沖縄県 1,467,480人
        // 470007,沖縄県
        if (address.startsWith(PREF_47_LABEL)) {
            return PREF_47_CODE;
        }

        // 滋賀県 1,413,610人
        // 250007,滋賀県
        if (address.startsWith(PREF_25_LABEL)) {
            return PREF_25_CODE;
        }

        // 山口県 1,342,059人
        // 350001,山口県
        if (address.startsWith(PREF_35_LABEL)) {
            return PREF_35_CODE;
        }

        // 愛媛県 1,334,841人
        // 380008,愛媛県
        if (address.startsWith(PREF_38_LABEL)) {
            return PREF_38_CODE;
        }

        // 奈良県 1,324,473人
        // 290009,奈良県
        if (address.startsWith(PREF_29_LABEL)) {
            return PREF_29_CODE;
        }

        // 長崎県 1,312,317人
        // 420000,長崎県
        if (address.startsWith(PREF_42_LABEL)) {
            return PREF_42_CODE;
        }

        // 青森県 1,237,984人
        // 020001,青森県
        if (address.startsWith(PREF_02_LABEL)) {
            return PREF_02_CODE;
        }

        // 岩手県 1,210,534人
        // 030007,岩手県
        if (address.startsWith(PREF_03_LABEL)) {
            return PREF_03_CODE;
        }

        // 石川県 1,132,526人
        // 170003,石川県
        if (address.startsWith(PREF_17_LABEL)) {
            return PREF_17_CODE;
        }

        // 大分県 1,123,852人
        // 440001,大分県
        if (address.startsWith(PREF_44_LABEL)) {
            return PREF_44_CODE;
        }

        // 宮崎県 1,069,576人
        // 450006,宮崎県
        if (address.startsWith(PREF_45_LABEL)) {
            return PREF_45_CODE;
        }

        // 山形県 1,068,027人
        // 060003,山形県
        if (address.startsWith(PREF_06_LABEL)) {
            return PREF_06_CODE;
        }

        // 富山県 1,034,814人
        // 160008,富山県
        if (address.startsWith(PREF_16_LABEL)) {
            return PREF_16_CODE;
        }

        // 秋田県 959,502人
        // 050008,秋田県
        if (address.startsWith(PREF_05_LABEL)) {
            return PREF_05_CODE;
        }

        // 香川県 950,244人
        // 370002,香川県
        if (address.startsWith(PREF_37_LABEL)) {
            return PREF_37_CODE;
        }

        // 和歌山県 922,584人
        // 300004,和歌山県
        if (address.startsWith(PREF_30_LABEL)) {
            return PREF_30_CODE;
        }

        // 佐賀県 811,442人
        // 410004,佐賀県
        if (address.startsWith(PREF_41_LABEL)) {
            return PREF_41_CODE;
        }

        // 山梨県 809,974人
        // 190004,山梨県
        if (address.startsWith(PREF_19_LABEL)) {
            return PREF_19_CODE;
        }

        // 福井県 766,863人
        // 180009,福井県
        if (address.startsWith(PREF_18_LABEL)) {
            return PREF_18_CODE;
        }

        // 徳島県 719,559人
        // 360007,徳島県
        if (address.startsWith(PREF_36_LABEL)) {
            return PREF_36_CODE;
        }

        // 高知県 691,527人
        // 390003,高知県,
        if (address.startsWith(PREF_39_LABEL)) {
            return PREF_39_CODE;
        }

        // 島根県 671,126人
        // 320005,島根県
        if (address.startsWith(PREF_32_LABEL)) {
            return PREF_32_CODE;
        }

        // 鳥取県 553,407人
        // 310000,鳥取県
        if (address.startsWith(PREF_31_LABEL)) {
            return PREF_31_CODE;
        }

        // その他
        return PREF_99_CODE;
    }

    /**
     * 該当する県コードを取得する
     *
     * @param address 住所
     * @return 県コード
     */
    public int practice(final String address) {

        return Integer.parseInt(this.practiceString(address));
    }

    /**
     * 処理を行う
     *
     * @param code 県コード
     * @return 都道府県名
     */
    public String practiceName(final String code) { // SUPPRESS CHECKSTYLE ReturnCount NOPMD

        switch (code) {

            // 東京都 14,047,594人
            // 130001,東京都
            case PREF_13_CODE:
                return PREF_13_LABEL;

            // 神奈川県 9,237,337人
            // 140007,神奈川県
            case PREF_14_CODE:
                return PREF_14_LABEL;

            // 大阪府 8,837,685人
            // 270008,大阪府
            case PREF_27_CODE:
                return PREF_27_LABEL;

            // 愛知県 7,542,415人
            // 230006,愛知県
            case PREF_23_CODE:
                return PREF_23_LABEL;

            // 埼玉県 7,344,765人
            // 110001,埼玉県
            case PREF_11_CODE:
                return PREF_11_LABEL;

            // 千葉県 6,284,480人
            // 120006,千葉県
            case PREF_12_CODE:
                return PREF_12_LABEL;

            // 兵庫県 5,465,002人
            // 280003,兵庫県
            case PREF_28_CODE:
                return PREF_28_LABEL;

            // 北海道 5,224,614人
            // 010006,北海道
            case PREF_01_CODE:
                return PREF_01_LABEL;

            // 福岡県 5,135,214人
            // 400009,福岡県
            case PREF_40_CODE:
                return PREF_40_LABEL;

            // 静岡県 3,633,202人
            // 220001,静岡県
            case PREF_22_CODE:
                return PREF_22_LABEL;

            // 茨城県 2,867,009人
            // 080004,茨城県
            case PREF_08_CODE:
                return PREF_08_LABEL;

            // 広島県 2,799,702人
            // 340006,広島県
            case PREF_34_CODE:
                return PREF_34_LABEL;

            // 京都府 2,578,087人
            // 260002,京都府
            case PREF_26_CODE:
                return PREF_26_LABEL;

            // 宮城県 2,301,996人
            // 040002,宮城県
            case PREF_04_CODE:
                return PREF_04_LABEL;

            // 新潟県 2,201,272人
            // 150002,新潟県
            case PREF_15_CODE:
                return PREF_15_LABEL;

            // 長野県 2,048,011人
            // 200000,長野県
            case PREF_20_CODE:
                return PREF_20_LABEL;

            // 岐阜県 1,978,742人
            // 210005,岐阜県
            case PREF_21_CODE:
                return PREF_21_LABEL;

            // 群馬県 1,939,110人
            // 100005,群馬県
            case PREF_10_CODE:
                return PREF_10_LABEL;

            // 栃木県 1,933,146人
            // 090000,栃木県
            case PREF_09_CODE:
                return PREF_09_LABEL;

            // 岡山県 1,888,432人
            // 330001,岡山県
            case PREF_33_CODE:
                return PREF_33_LABEL;

            // 福島県 1,833,152人
            // 070009,福島県
            case PREF_07_CODE:
                return PREF_07_LABEL;

            // 三重県 1,770,254人
            // 240001,三重県
            case PREF_24_CODE:
                return PREF_24_LABEL;

            // 熊本県 1,738,301人
            // 430005,熊本県
            case PREF_43_CODE:
                return PREF_43_LABEL;

            // 鹿児島県 1,588,256人
            // 460001,鹿児島県
            case PREF_46_CODE:
                return PREF_46_LABEL;

            // 沖縄県 1,467,480人
            // 470007,沖縄県
            case PREF_47_CODE:
                return PREF_47_LABEL;

            // 滋賀県 1,413,610人
            // 250007,滋賀県
            case PREF_25_CODE:
                return PREF_25_LABEL;

            // 山口県 1,342,059人
            // 350001,山口県
            case PREF_35_CODE:
                return PREF_35_LABEL;

            // 愛媛県 1,334,841人
            // 380008,愛媛県
            case PREF_38_CODE:
                return PREF_38_LABEL;

            // 奈良県 1,324,473人
            // 290009,奈良県
            case PREF_29_CODE:
                return PREF_29_LABEL;

            // 長崎県 1,312,317人
            // 420000,長崎県
            case PREF_42_CODE:
                return PREF_42_LABEL;

            // 青森県 1,237,984人
            // 020001,青森県
            case PREF_02_CODE:
                return PREF_02_LABEL;

            // 岩手県 1,210,534人
            // 030007,岩手県
            case PREF_03_CODE:
                return PREF_03_LABEL;

            // 石川県 1,132,526人
            // 170003,石川県
            case PREF_17_CODE:
                return PREF_17_LABEL;

            // 大分県 1,123,852人
            // 440001,大分県
            case PREF_44_CODE:
                return PREF_44_LABEL;

            // 宮崎県 1,069,576人
            // 450006,宮崎県
            case PREF_45_CODE:
                return PREF_45_LABEL;

            // 山形県 1,068,027人
            // 060003,山形県
            case PREF_06_CODE:
                return PREF_06_LABEL;

            // 富山県 1,034,814人
            // 160008,富山県
            case PREF_16_CODE:
                return PREF_16_LABEL;

            // 秋田県 959,502人
            // 050008,秋田県
            case PREF_05_CODE:
                return PREF_05_LABEL;

            // 香川県 950,244人
            // 370002,香川県
            case PREF_37_CODE:
                return PREF_37_LABEL;

            // 和歌山県 922,584人
            // 300004,和歌山県
            case PREF_30_CODE:
                return PREF_30_LABEL;

            // 佐賀県 811,442人
            // 410004,佐賀県
            case PREF_41_CODE:
                return PREF_41_LABEL;

            // 山梨県 809,974人
            // 190004,山梨県
            case PREF_19_CODE:
                return PREF_19_LABEL;

            // 福井県 766,863人
            // 180009,福井県
            case PREF_18_CODE:
                return PREF_18_LABEL;

            // 徳島県 719,559人
            // 360007,徳島県
            case PREF_36_CODE:
                return PREF_36_LABEL;

            // 高知県 691,527人
            // 390003,高知県,
            case PREF_39_CODE:
                return PREF_39_LABEL;

            // 島根県 671,126人
            // 320005,島根県
            case PREF_32_CODE:
                return PREF_32_LABEL;

            // 鳥取県 553,407人
            // 310000,鳥取県
            case PREF_31_CODE:
                return PREF_31_LABEL;

            // その他
            default:
                return PREF_99_LABEL;
        }

    }

    /**
     * 県をselectbox形式で取得する
     * 
     * @return 県コードリスト
     */
    public List<SelectOptionStringDto> getOptions() {
        List<SelectOptionStringDto> list = new ArrayList<>();

        list.add(new SelectOptionStringDto("", ""));

        list.add(new SelectOptionStringDto(PREF_01_CODE, PREF_01_LABEL));
        list.add(new SelectOptionStringDto(PREF_02_CODE, PREF_02_LABEL));
        list.add(new SelectOptionStringDto(PREF_03_CODE, PREF_03_LABEL));
        list.add(new SelectOptionStringDto(PREF_04_CODE, PREF_04_LABEL));
        list.add(new SelectOptionStringDto(PREF_05_CODE, PREF_05_LABEL));
        list.add(new SelectOptionStringDto(PREF_06_CODE, PREF_06_LABEL));
        list.add(new SelectOptionStringDto(PREF_07_CODE, PREF_07_LABEL));
        list.add(new SelectOptionStringDto(PREF_08_CODE, PREF_08_LABEL));
        list.add(new SelectOptionStringDto(PREF_09_CODE, PREF_09_LABEL));
        list.add(new SelectOptionStringDto(PREF_10_CODE, PREF_10_LABEL));
        list.add(new SelectOptionStringDto(PREF_11_CODE, PREF_11_LABEL));
        list.add(new SelectOptionStringDto(PREF_12_CODE, PREF_12_LABEL));
        list.add(new SelectOptionStringDto(PREF_13_CODE, PREF_13_LABEL));
        list.add(new SelectOptionStringDto(PREF_14_CODE, PREF_14_LABEL));
        list.add(new SelectOptionStringDto(PREF_15_CODE, PREF_15_LABEL));
        list.add(new SelectOptionStringDto(PREF_16_CODE, PREF_16_LABEL));
        list.add(new SelectOptionStringDto(PREF_17_CODE, PREF_17_LABEL));
        list.add(new SelectOptionStringDto(PREF_18_CODE, PREF_18_LABEL));
        list.add(new SelectOptionStringDto(PREF_19_CODE, PREF_19_LABEL));
        list.add(new SelectOptionStringDto(PREF_20_CODE, PREF_20_LABEL));
        list.add(new SelectOptionStringDto(PREF_21_CODE, PREF_21_LABEL));
        list.add(new SelectOptionStringDto(PREF_22_CODE, PREF_22_LABEL));
        list.add(new SelectOptionStringDto(PREF_23_CODE, PREF_23_LABEL));
        list.add(new SelectOptionStringDto(PREF_24_CODE, PREF_24_LABEL));
        list.add(new SelectOptionStringDto(PREF_25_CODE, PREF_25_LABEL));
        list.add(new SelectOptionStringDto(PREF_26_CODE, PREF_26_LABEL));
        list.add(new SelectOptionStringDto(PREF_27_CODE, PREF_27_LABEL));
        list.add(new SelectOptionStringDto(PREF_28_CODE, PREF_28_LABEL));
        list.add(new SelectOptionStringDto(PREF_29_CODE, PREF_29_LABEL));
        list.add(new SelectOptionStringDto(PREF_30_CODE, PREF_30_LABEL));
        list.add(new SelectOptionStringDto(PREF_31_CODE, PREF_31_LABEL));
        list.add(new SelectOptionStringDto(PREF_32_CODE, PREF_32_LABEL));
        list.add(new SelectOptionStringDto(PREF_33_CODE, PREF_33_LABEL));
        list.add(new SelectOptionStringDto(PREF_34_CODE, PREF_34_LABEL));
        list.add(new SelectOptionStringDto(PREF_35_CODE, PREF_35_LABEL));
        list.add(new SelectOptionStringDto(PREF_36_CODE, PREF_36_LABEL));

        list.add(new SelectOptionStringDto(PREF_37_CODE, PREF_37_LABEL));
        list.add(new SelectOptionStringDto(PREF_38_CODE, PREF_38_LABEL));
        list.add(new SelectOptionStringDto(PREF_39_CODE, PREF_39_LABEL));
        list.add(new SelectOptionStringDto(PREF_40_CODE, PREF_40_LABEL));
        list.add(new SelectOptionStringDto(PREF_41_CODE, PREF_41_LABEL));
        list.add(new SelectOptionStringDto(PREF_42_CODE, PREF_42_LABEL));
        list.add(new SelectOptionStringDto(PREF_43_CODE, PREF_43_LABEL));
        list.add(new SelectOptionStringDto(PREF_44_CODE, PREF_44_LABEL));
        list.add(new SelectOptionStringDto(PREF_45_CODE, PREF_45_LABEL));
        list.add(new SelectOptionStringDto(PREF_46_CODE, PREF_46_LABEL));
        list.add(new SelectOptionStringDto(PREF_47_CODE, PREF_47_LABEL));

        list.add(new SelectOptionStringDto(PREF_99_CODE, PREF_99_LABEL));

        return list;
    }

}
