package net.seijishikin.jp.normalize.manage.kanrensha.service.util;

import org.springframework.stereotype.Service;

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

    /** 文字コード出力する際の0付加上限 */
    private static final int PLUS_ZERO_NUM = 10;
    /**
     * 処理を行う
     *
     * @param address 住所
     * @return 県の地方自治体コード前2桁
     */
    public int practice(final String address) { // SUPPRESS CHECKSTYLE ReturnCount NOPMD
        
        // 東京都 14,047,594人
        // 130001,東京都
        if (address.startsWith("東京都")) {
            return PREF_13;
        }

        // 神奈川県 9,237,337人
        // 140007,神奈川県
        if (address.startsWith("神奈川県")) {
            return PREF_14;
        }

        // 大阪府 8,837,685人
        // 270008,大阪府
        if (address.startsWith("大阪府")) {
            return PREF_27;
        }

        // 愛知県 7,542,415人
        // 230006,愛知県
        if (address.startsWith("愛知県")) {
            return PREF_23;
        }

        // 埼玉県 7,344,765人
        // 110001,埼玉県
        if (address.startsWith("埼玉県")) {
            return PREF_11;
        }

        // 千葉県 6,284,480人
        // 120006,千葉県
        if (address.startsWith("千葉県")) {
            return PREF_12;
        }

        // 兵庫県 5,465,002人
        // 280003,兵庫県
        if (address.startsWith("兵庫県")) {
            return PREF_28;
        }

        // 北海道 5,224,614人
        // 010006,北海道
        if (address.startsWith("北海道")) {
            return PREF_01;
        }

        // 福岡県 5,135,214人
        // 400009,福岡県
        if (address.startsWith("福岡県")) {
            return PREF_40;
        }

        // 静岡県 3,633,202人
        // 220001,静岡県
        if (address.startsWith("静岡県")) {
            return PREF_22;
        }

        // 茨城県 2,867,009人
        // 080004,茨城県
        if (address.startsWith("茨城県")) {
            return PREF_08;
        }

        // 広島県 2,799,702人
        // 340006,広島県
        if (address.startsWith("広島県")) {
            return PREF_34;
        }

        // 京都府 2,578,087人
        // 260002,京都府
        if (address.startsWith("京都府")) {
            return PREF_26;
        }

        // 宮城県 2,301,996人
        // 040002,宮城県
        if (address.startsWith("宮城県")) {
            return PREF_04;
        }

        // 新潟県 2,201,272人
        // 150002,新潟県
        if (address.startsWith("新潟県")) {
            return PREF_15;
        }

        // 長野県 2,048,011人
        // 200000,長野県
        if (address.startsWith("長野県")) {
            return PREF_20;
        }

        // 岐阜県 1,978,742人
        // 210005,岐阜県
        if (address.startsWith("岐阜県")) {
            return PREF_21;
        }

        // 群馬県 1,939,110人
        // 100005,群馬県
        if (address.startsWith("群馬県")) {
            return PREF_10;
        }

        // 栃木県 1,933,146人
        // 090000,栃木県
        if (address.startsWith("栃木県")) {
            return PREF_09;
        }

        // 岡山県 1,888,432人
        // 330001,岡山県
        if (address.startsWith("岡山県")) {
            return PREF_33;
        }

        // 福島県 1,833,152人
        // 070009,福島県
        if (address.startsWith("福島県")) {
            return PREF_07;
        }

        // 三重県 1,770,254人
        // 240001,三重県
        if (address.startsWith("三重県")) {
            return PREF_24;
        }

        // 熊本県 1,738,301人
        // 430005,熊本県
        if (address.startsWith("熊本県")) {
            return PREF_43;
        }

        // 鹿児島県 1,588,256人
        // 460001,鹿児島県
        if (address.startsWith("鹿児島県")) {
            return PREF_46;
        }

        // 沖縄県 1,467,480人
        // 470007,沖縄県
        if (address.startsWith("沖縄県")) {
            return PREF_47;
        }

        // 滋賀県 1,413,610人
        // 250007,滋賀県
        if (address.startsWith("滋賀県")) {
            return PREF_25;
        }

        // 山口県 1,342,059人
        // 350001,山口県
        if (address.startsWith("山口県")) {
            return PREF_35;
        }

        // 愛媛県 1,334,841人
        // 380008,愛媛県
        if (address.startsWith("愛媛県")) {
            return PREF_38;
        }

        // 奈良県 1,324,473人
        // 290009,奈良県
        if (address.startsWith("奈良県")) {
            return PREF_29;
        }

        // 長崎県 1,312,317人
        // 420000,長崎県
        if (address.startsWith("長崎県")) {
            return PREF_42;
        }

        // 青森県 1,237,984人
        // 020001,青森県
        if (address.startsWith("青森県")) {
            return PREF_02;
        }

        // 岩手県 1,210,534人
        // 030007,岩手県
        if (address.startsWith("岩手県")) {
            return PREF_03;
        }

        // 石川県 1,132,526人
        // 170003,石川県
        if (address.startsWith("石川県")) {
            return PREF_17;
        }

        // 大分県 1,123,852人
        // 440001,大分県
        if (address.startsWith("大分県")) {
            return PREF_44;
        }

        // 宮崎県 1,069,576人
        // 450006,宮崎県
        if (address.startsWith("宮崎県")) {
            return PREF_45;
        }

        // 山形県 1,068,027人
        // 060003,山形県
        if (address.startsWith("山形県")) {
            return PREF_06;
        }

        // 富山県 1,034,814人
        // 160008,富山県
        if (address.startsWith("富山県")) {
            return PREF_16;
        }

        // 秋田県 959,502人
        // 050008,秋田県
        if (address.startsWith("秋田県")) {
            return PREF_05;
        }

        // 香川県 950,244人
        // 370002,香川県
        if (address.startsWith("香川県")) {
            return PREF_37;
        }

        // 和歌山県 922,584人
        // 300004,和歌山県
        if (address.startsWith("和歌山県")) {
            return PREF_30;
        }

        // 佐賀県 811,442人
        // 410004,佐賀県
        if (address.startsWith("佐賀県")) {
            return PREF_41;
        }

        // 山梨県 809,974人
        // 190004,山梨県
        if (address.startsWith("山梨県")) {
            return PREF_19;
        }

        // 福井県 766,863人
        // 180009,福井県
        if (address.startsWith("福井県")) {
            return PREF_18;
        }

        // 徳島県 719,559人
        // 360007,徳島県
        if (address.startsWith("徳島県")) {
            return PREF_36;
        }

        // 高知県 691,527人
        // 390003,高知県,
        if (address.startsWith("高知県")) {
            return PREF_39;
        }

        // 島根県 671,126人
        // 320005,島根県
        if (address.startsWith("島根県")) {
            return PREF_32;
        }

        // 鳥取県 553,407人
        // 310000,鳥取県
        if (address.startsWith("鳥取県")) {
            return PREF_31;
        }

        // その他
        return PREF_99;
    }

    /**
     * 該当する県コードを取得する
     *
     * @param address 住所
     * @return 県コード
     */
    public String practiceString(final String address) {

        int code = this.practice(address);
        if (code < PLUS_ZERO_NUM) {
            return "0" + code;
        } else {
            return String.valueOf(code);
        }
    }

}
