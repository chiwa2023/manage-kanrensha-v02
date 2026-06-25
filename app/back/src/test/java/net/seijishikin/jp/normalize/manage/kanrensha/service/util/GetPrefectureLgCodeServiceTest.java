package net.seijishikin.jp.normalize.manage.kanrensha.service.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionStringDto;

/**
 * GetPrefectureLgCodeService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class GetPrefectureLgCodeServiceTest {
    // CHECKSTYLE:OFF MagiNumber

    /** テスト対象 */
    @Autowired
    private GetPrefectureLgCodeService getPrefectureLgCodeService;

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

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 010006,北海道
        assertEquals(1, getPrefectureLgCodeService.practice("北海道架空市"));

        // 020001,青森県
        assertEquals(2, getPrefectureLgCodeService.practice("青森県架空市"));

        // 030007,岩手県
        assertEquals(3, getPrefectureLgCodeService.practice("岩手県架空市"));

        // 040002,宮城県
        assertEquals(4, getPrefectureLgCodeService.practice("宮城県架空市"));

        // 050008,秋田県
        assertEquals(5, getPrefectureLgCodeService.practice("秋田県架空市"));

        // 060003,山形県
        assertEquals(6, getPrefectureLgCodeService.practice("山形県架空市"));

        // 070009,福島県
        assertEquals(7, getPrefectureLgCodeService.practice("福島県架空市"));

        // 080004,茨城県
        assertEquals(8, getPrefectureLgCodeService.practice("茨城県架空市"));

        // 090000,栃木県
        assertEquals(9, getPrefectureLgCodeService.practice("栃木県架空市"));

        // 100005,群馬県
        assertEquals(10, getPrefectureLgCodeService.practice("群馬県架空市"));

        // 110001,埼玉県
        assertEquals(11, getPrefectureLgCodeService.practice("埼玉県架空市"));

        // 120006,千葉県
        assertEquals(12, getPrefectureLgCodeService.practice("千葉県架空市"));

        // 130001,東京都
        assertEquals(13, getPrefectureLgCodeService.practice("東京都架空市"));

        // 140007,神奈川県
        assertEquals(14, getPrefectureLgCodeService.practice("神奈川県架空市"));

        // 150002,新潟県
        assertEquals(15, getPrefectureLgCodeService.practice("新潟県架空市"));

        // 160008,富山県
        assertEquals(16, getPrefectureLgCodeService.practice("富山県架空市"));

        // 170003,石川県
        assertEquals(17, getPrefectureLgCodeService.practice("石川県架空市"));

        // 180009,福井県
        assertEquals(18, getPrefectureLgCodeService.practice("福井県架空市"));

        // 190004,山梨県
        assertEquals(19, getPrefectureLgCodeService.practice("山梨県架空市"));

        // 200000,長野県
        assertEquals(20, getPrefectureLgCodeService.practice("長野県架空市"));

        // 210005,岐阜県
        assertEquals(21, getPrefectureLgCodeService.practice("岐阜県架空市"));

        // 220001,静岡県
        assertEquals(22, getPrefectureLgCodeService.practice("静岡県架空市"));

        // 230006,愛知県
        assertEquals(23, getPrefectureLgCodeService.practice("愛知県架空市"));

        // 240001,三重県
        assertEquals(24, getPrefectureLgCodeService.practice("三重県架空市"));

        // 250007,滋賀県
        assertEquals(25, getPrefectureLgCodeService.practice("滋賀県架空市"));

        // 260002,京都府
        assertEquals(26, getPrefectureLgCodeService.practice("京都府架空市"));

        // 270008,大阪府
        assertEquals(27, getPrefectureLgCodeService.practice("大阪府架空市"));

        // 280003,兵庫県
        assertEquals(28, getPrefectureLgCodeService.practice("兵庫県架空市"));

        // 290009,奈良県
        assertEquals(29, getPrefectureLgCodeService.practice("奈良県架空市"));

        // 300004,和歌山県
        assertEquals(30, getPrefectureLgCodeService.practice("和歌山県架空市"));

        // 310000,鳥取県
        assertEquals(31, getPrefectureLgCodeService.practice("鳥取県架空市"));

        // 320005,島根県
        assertEquals(32, getPrefectureLgCodeService.practice("島根県架空市"));

        // 330001,岡山県
        assertEquals(33, getPrefectureLgCodeService.practice("岡山県架空市"));

        // 340006,広島県
        assertEquals(34, getPrefectureLgCodeService.practice("広島県架空市"));

        // 350001,山口県
        assertEquals(35, getPrefectureLgCodeService.practice("山口県架空市"));

        // 360007,徳島県
        assertEquals(36, getPrefectureLgCodeService.practice("徳島県架空市"));

        // 370002,香川県
        assertEquals(37, getPrefectureLgCodeService.practice("香川県架空市"));

        // 380008,愛媛県
        assertEquals(38, getPrefectureLgCodeService.practice("愛媛県架空市"));

        // 390003,高知県
        assertEquals(39, getPrefectureLgCodeService.practice("高知県架空市"));

        // 400009,福岡県
        assertEquals(40, getPrefectureLgCodeService.practice("福岡県架空市"));

        // 410004,佐賀県
        assertEquals(41, getPrefectureLgCodeService.practice("佐賀県架空市"));

        // 420000,長崎県
        assertEquals(42, getPrefectureLgCodeService.practice("長崎県架空市"));

        // 430005,熊本県
        assertEquals(43, getPrefectureLgCodeService.practice("熊本県架空市"));

        // 440001,大分県
        assertEquals(44, getPrefectureLgCodeService.practice("大分県架空市"));

        // 450006,宮崎県
        assertEquals(45, getPrefectureLgCodeService.practice("宮崎県架空市"));

        // 460001,鹿児島県
        assertEquals(46, getPrefectureLgCodeService.practice("鹿児島県架空市"));

        // 470007,沖縄県
        assertEquals(47, getPrefectureLgCodeService.practice("沖縄県架空市"));

        // その他
        assertEquals(99, getPrefectureLgCodeService.practice("架空市"));
    }

    @Test
    void testString() {

        // 010006,北海道
        assertEquals("01", getPrefectureLgCodeService.practiceString("北海道架空市"));

        // 020001,青森県
        assertEquals("02", getPrefectureLgCodeService.practiceString("青森県架空市"));

        // 030007,岩手県
        assertEquals("03", getPrefectureLgCodeService.practiceString("岩手県架空市"));

        // 040002,宮城県
        assertEquals("04", getPrefectureLgCodeService.practiceString("宮城県架空市"));

        // 050008,秋田県
        assertEquals("05", getPrefectureLgCodeService.practiceString("秋田県架空市"));

        // 060003,山形県
        assertEquals("06", getPrefectureLgCodeService.practiceString("山形県架空市"));

        // 070009,福島県
        assertEquals("07", getPrefectureLgCodeService.practiceString("福島県架空市"));

        // 080004,茨城県
        assertEquals("08", getPrefectureLgCodeService.practiceString("茨城県架空市"));

        // 090000,栃木県
        assertEquals("09", getPrefectureLgCodeService.practiceString("栃木県架空市"));

        // 100005,群馬県
        assertEquals("10", getPrefectureLgCodeService.practiceString("群馬県架空市"));

        // 110001,埼玉県
        assertEquals("11", getPrefectureLgCodeService.practiceString("埼玉県架空市"));

        // 120006,千葉県
        assertEquals("12", getPrefectureLgCodeService.practiceString("千葉県架空市"));

        // 130001,東京都
        assertEquals("13", getPrefectureLgCodeService.practiceString("東京都架空市"));

        // 140007,神奈川県
        assertEquals("14", getPrefectureLgCodeService.practiceString("神奈川県架空市"));

        // 150002,新潟県
        assertEquals("15", getPrefectureLgCodeService.practiceString("新潟県架空市"));

        // 160008,富山県
        assertEquals("16", getPrefectureLgCodeService.practiceString("富山県架空市"));

        // 170003,石川県
        assertEquals("17", getPrefectureLgCodeService.practiceString("石川県架空市"));

        // 180009,福井県
        assertEquals("18", getPrefectureLgCodeService.practiceString("福井県架空市"));

        // 190004,山梨県
        assertEquals("19", getPrefectureLgCodeService.practiceString("山梨県架空市"));

        // 200000,長野県
        assertEquals("20", getPrefectureLgCodeService.practiceString("長野県架空市"));

        // 210005,岐阜県
        assertEquals("21", getPrefectureLgCodeService.practiceString("岐阜県架空市"));

        // 220001,静岡県
        assertEquals("22", getPrefectureLgCodeService.practiceString("静岡県架空市"));

        // 230006,愛知県
        assertEquals("23", getPrefectureLgCodeService.practiceString("愛知県架空市"));

        // 240001,三重県
        assertEquals("24", getPrefectureLgCodeService.practiceString("三重県架空市"));

        // 250007,滋賀県
        assertEquals("25", getPrefectureLgCodeService.practiceString("滋賀県架空市"));

        // 260002,京都府
        assertEquals("26", getPrefectureLgCodeService.practiceString("京都府架空市"));

        // 270008,大阪府
        assertEquals("27", getPrefectureLgCodeService.practiceString("大阪府架空市"));

        // 280003,兵庫県
        assertEquals("28", getPrefectureLgCodeService.practiceString("兵庫県架空市"));

        // 290009,奈良県
        assertEquals("29", getPrefectureLgCodeService.practiceString("奈良県架空市"));

        // 300004,和歌山県
        assertEquals("30", getPrefectureLgCodeService.practiceString("和歌山県架空市"));

        // 310000,鳥取県
        assertEquals("31", getPrefectureLgCodeService.practiceString("鳥取県架空市"));

        // 320005,島根県
        assertEquals("32", getPrefectureLgCodeService.practiceString("島根県架空市"));

        // 330001,岡山県
        assertEquals("33", getPrefectureLgCodeService.practiceString("岡山県架空市"));

        // 340006,広島県
        assertEquals("34", getPrefectureLgCodeService.practiceString("広島県架空市"));

        // 350001,山口県
        assertEquals("35", getPrefectureLgCodeService.practiceString("山口県架空市"));

        // 360007,徳島県
        assertEquals("36", getPrefectureLgCodeService.practiceString("徳島県架空市"));

        // 370002,香川県
        assertEquals("37", getPrefectureLgCodeService.practiceString("香川県架空市"));

        // 380008,愛媛県
        assertEquals("38", getPrefectureLgCodeService.practiceString("愛媛県架空市"));

        // 390003,高知県
        assertEquals("39", getPrefectureLgCodeService.practiceString("高知県架空市"));

        // 400009,福岡県
        assertEquals("40", getPrefectureLgCodeService.practiceString("福岡県架空市"));

        // 410004,佐賀県
        assertEquals("41", getPrefectureLgCodeService.practiceString("佐賀県架空市"));

        // 420000,長崎県
        assertEquals("42", getPrefectureLgCodeService.practiceString("長崎県架空市"));

        // 430005,熊本県
        assertEquals("43", getPrefectureLgCodeService.practiceString("熊本県架空市"));

        // 440001,大分県
        assertEquals("44", getPrefectureLgCodeService.practiceString("大分県架空市"));

        // 450006,宮崎県
        assertEquals("45", getPrefectureLgCodeService.practiceString("宮崎県架空市"));

        // 460001,鹿児島県
        assertEquals("46", getPrefectureLgCodeService.practiceString("鹿児島県架空市"));

        // 470007,沖縄県
        assertEquals("47", getPrefectureLgCodeService.practiceString("沖縄県架空市"));

        // その他
        assertEquals("99", getPrefectureLgCodeService.practiceString("架空市"));
    }

    @Test
    @Tag("TableTruncate")
    void testName() {

        // 010006,北海道
        assertEquals("北海道", getPrefectureLgCodeService.practiceName("01"));

        // 020001,青森県
        assertEquals("青森県", getPrefectureLgCodeService.practiceName("02"));

        // 030007,岩手県
        assertEquals("岩手県", getPrefectureLgCodeService.practiceName("03"));

        // 040002,宮城県
        assertEquals("宮城県", getPrefectureLgCodeService.practiceName("04"));

        // 050008,秋田県
        assertEquals("秋田県", getPrefectureLgCodeService.practiceName("05"));

        // 060003,山形県
        assertEquals("山形県", getPrefectureLgCodeService.practiceName("06"));

        // 070009,福島県
        assertEquals("福島県", getPrefectureLgCodeService.practiceName("07"));

        // 080004,茨城県
        assertEquals("茨城県", getPrefectureLgCodeService.practiceName("08"));

        // 090000,栃木県
        assertEquals("栃木県", getPrefectureLgCodeService.practiceName("09"));

        // 100005,群馬県
        assertEquals("群馬県", getPrefectureLgCodeService.practiceName("10"));

        // 110001,埼玉県
        assertEquals("埼玉県", getPrefectureLgCodeService.practiceName("11"));

        // 120006,千葉県
        assertEquals("千葉県", getPrefectureLgCodeService.practiceName("12"));

        // 130001,東京都
        assertEquals("東京都", getPrefectureLgCodeService.practiceName("13"));

        // 140007,神奈川県
        assertEquals("神奈川県", getPrefectureLgCodeService.practiceName("14"));

        // 150002,新潟県
        assertEquals("新潟県", getPrefectureLgCodeService.practiceName("15"));

        // 160008,富山県
        assertEquals("富山県", getPrefectureLgCodeService.practiceName("16"));

        // 170003,石川県
        assertEquals("石川県", getPrefectureLgCodeService.practiceName("17"));

        // 180009,福井県
        assertEquals("福井県", getPrefectureLgCodeService.practiceName("18"));

        // 190004,山梨県
        assertEquals("山梨県", getPrefectureLgCodeService.practiceName("19"));

        // 200000,長野県
        assertEquals("長野県", getPrefectureLgCodeService.practiceName("20"));

        // 210005,岐阜県
        assertEquals("岐阜県", getPrefectureLgCodeService.practiceName("21"));

        // 220001,静岡県
        assertEquals("静岡県", getPrefectureLgCodeService.practiceName("22"));

        // 230006,愛知県
        assertEquals("愛知県", getPrefectureLgCodeService.practiceName("23"));

        // 240001,三重県
        assertEquals("三重県", getPrefectureLgCodeService.practiceName("24"));

        // 250007,滋賀県
        assertEquals("滋賀県", getPrefectureLgCodeService.practiceName("25"));

        // 260002,京都府
        assertEquals("京都府", getPrefectureLgCodeService.practiceName("26"));

        // 270008,大阪府
        assertEquals("大阪府", getPrefectureLgCodeService.practiceName("27"));

        // 280003,兵庫県
        assertEquals("兵庫県", getPrefectureLgCodeService.practiceName("28"));

        // 290009,奈良県
        assertEquals("奈良県", getPrefectureLgCodeService.practiceName("29"));

        // 300004,和歌山県
        assertEquals("和歌山県", getPrefectureLgCodeService.practiceName("30"));

        // 310000,鳥取県
        assertEquals("鳥取県", getPrefectureLgCodeService.practiceName("31"));

        // 320005,島根県
        assertEquals("島根県", getPrefectureLgCodeService.practiceName("32"));

        // 330001,岡山県
        assertEquals("岡山県", getPrefectureLgCodeService.practiceName("33"));

        // 340006,広島県
        assertEquals("広島県", getPrefectureLgCodeService.practiceName("34"));

        // 350001,山口県
        assertEquals("山口県", getPrefectureLgCodeService.practiceName("35"));

        // 360007,徳島県
        assertEquals("徳島県", getPrefectureLgCodeService.practiceName("36"));

        // 370002,香川県
        assertEquals("香川県", getPrefectureLgCodeService.practiceName("37"));

        // 380008,愛媛県
        assertEquals("愛媛県", getPrefectureLgCodeService.practiceName("38"));

        // 390003,高知県
        assertEquals("高知県", getPrefectureLgCodeService.practiceName("39"));

        // 400009,福岡県
        assertEquals("福岡県", getPrefectureLgCodeService.practiceName("40"));

        // 410004,佐賀県
        assertEquals("佐賀県", getPrefectureLgCodeService.practiceName("41"));

        // 420000,長崎県
        assertEquals("長崎県", getPrefectureLgCodeService.practiceName("42"));

        // 430005,熊本県
        assertEquals("熊本県", getPrefectureLgCodeService.practiceName("43"));

        // 440001,大分県
        assertEquals("大分県", getPrefectureLgCodeService.practiceName("44"));

        // 450006,宮崎県
        assertEquals("宮崎県", getPrefectureLgCodeService.practiceName("45"));

        // 460001,鹿児島県
        assertEquals("鹿児島県", getPrefectureLgCodeService.practiceName("46"));

        // 470007,沖縄県
        assertEquals("沖縄県", getPrefectureLgCodeService.practiceName("47"));

        // その他
        assertEquals("その他", getPrefectureLgCodeService.practiceName("48"));
    }

    @Test
    @Tag("TableTruncate")
    void testOption() throws Exception {

        List<SelectOptionStringDto> list = getPrefectureLgCodeService.getOptions();
        assertEquals(49, list.size());

        SelectOptionStringDto option00 = list.get(0);
        assertEquals("", option00.getValue());
        assertEquals("", option00.getText());

        SelectOptionStringDto option01 = list.get(1);
        assertEquals(PREF_01_CODE, option01.getValue());
        assertEquals(PREF_01_LABEL, option01.getText());

        SelectOptionStringDto option02 = list.get(2);
        assertEquals(PREF_02_CODE, option02.getValue());
        assertEquals(PREF_02_LABEL, option02.getText());

        SelectOptionStringDto option03 = list.get(3);
        assertEquals(PREF_03_CODE, option03.getValue());
        assertEquals(PREF_03_LABEL, option03.getText());

        SelectOptionStringDto option04 = list.get(4);
        assertEquals(PREF_04_CODE, option04.getValue());
        assertEquals(PREF_04_LABEL, option04.getText());

        SelectOptionStringDto option05 = list.get(5);
        assertEquals(PREF_05_CODE, option05.getValue());
        assertEquals(PREF_05_LABEL, option05.getText());

        SelectOptionStringDto option06 = list.get(6);
        assertEquals(PREF_06_CODE, option06.getValue());
        assertEquals(PREF_06_LABEL, option06.getText());

        SelectOptionStringDto option07 = list.get(7);
        assertEquals(PREF_07_CODE, option07.getValue());
        assertEquals(PREF_07_LABEL, option07.getText());

        SelectOptionStringDto option08 = list.get(8);
        assertEquals(PREF_08_CODE, option08.getValue());
        assertEquals(PREF_08_LABEL, option08.getText());

        SelectOptionStringDto option09 = list.get(9);
        assertEquals(PREF_09_CODE, option09.getValue());
        assertEquals(PREF_09_LABEL, option09.getText());

        SelectOptionStringDto option10 = list.get(10);
        assertEquals(PREF_10_CODE, option10.getValue());
        assertEquals(PREF_10_LABEL, option10.getText());

        SelectOptionStringDto option11 = list.get(11);
        assertEquals(PREF_11_CODE, option11.getValue());
        assertEquals(PREF_11_LABEL, option11.getText());

        SelectOptionStringDto option12 = list.get(12);
        assertEquals(PREF_12_CODE, option12.getValue());
        assertEquals(PREF_12_LABEL, option12.getText());

        SelectOptionStringDto option13 = list.get(13);
        assertEquals(PREF_13_CODE, option13.getValue());
        assertEquals(PREF_13_LABEL, option13.getText());

        SelectOptionStringDto option14 = list.get(14);
        assertEquals(PREF_14_CODE, option14.getValue());
        assertEquals(PREF_14_LABEL, option14.getText());

        SelectOptionStringDto option15 = list.get(15);
        assertEquals(PREF_15_CODE, option15.getValue());
        assertEquals(PREF_15_LABEL, option15.getText());

        SelectOptionStringDto option16 = list.get(16);
        assertEquals(PREF_16_CODE, option16.getValue());
        assertEquals(PREF_16_LABEL, option16.getText());

        SelectOptionStringDto option17 = list.get(17);
        assertEquals(PREF_17_CODE, option17.getValue());
        assertEquals(PREF_17_LABEL, option17.getText());

        SelectOptionStringDto option18 = list.get(18);
        assertEquals(PREF_18_CODE, option18.getValue());
        assertEquals(PREF_18_LABEL, option18.getText());

        SelectOptionStringDto option19 = list.get(19);
        assertEquals(PREF_19_CODE, option19.getValue());
        assertEquals(PREF_19_LABEL, option19.getText());

        SelectOptionStringDto option20 = list.get(20);
        assertEquals(PREF_20_CODE, option20.getValue());
        assertEquals(PREF_20_LABEL, option20.getText());

        SelectOptionStringDto option21 = list.get(21);
        assertEquals(PREF_21_CODE, option21.getValue());
        assertEquals(PREF_21_LABEL, option21.getText());

        SelectOptionStringDto option22 = list.get(22);
        assertEquals(PREF_22_CODE, option22.getValue());
        assertEquals(PREF_22_LABEL, option22.getText());

        SelectOptionStringDto option23 = list.get(23);
        assertEquals(PREF_23_CODE, option23.getValue());
        assertEquals(PREF_23_LABEL, option23.getText());

        SelectOptionStringDto option24 = list.get(24);
        assertEquals(PREF_24_CODE, option24.getValue());
        assertEquals(PREF_24_LABEL, option24.getText());

        SelectOptionStringDto option25 = list.get(25);
        assertEquals(PREF_25_CODE, option25.getValue());
        assertEquals(PREF_25_LABEL, option25.getText());

        SelectOptionStringDto option26 = list.get(26);
        assertEquals(PREF_26_CODE, option26.getValue());
        assertEquals(PREF_26_LABEL, option26.getText());

        SelectOptionStringDto option27 = list.get(27);
        assertEquals(PREF_27_CODE, option27.getValue());
        assertEquals(PREF_27_LABEL, option27.getText());

        SelectOptionStringDto option28 = list.get(28);
        assertEquals(PREF_28_CODE, option28.getValue());
        assertEquals(PREF_28_LABEL, option28.getText());

        SelectOptionStringDto option29 = list.get(29);
        assertEquals(PREF_29_CODE, option29.getValue());
        assertEquals(PREF_29_LABEL, option29.getText());

        SelectOptionStringDto option30 = list.get(30);
        assertEquals(PREF_30_CODE, option30.getValue());
        assertEquals(PREF_30_LABEL, option30.getText());

        SelectOptionStringDto option31 = list.get(31);
        assertEquals(PREF_31_CODE, option31.getValue());
        assertEquals(PREF_31_LABEL, option31.getText());

        SelectOptionStringDto option32 = list.get(32);
        assertEquals(PREF_32_CODE, option32.getValue());
        assertEquals(PREF_32_LABEL, option32.getText());

        SelectOptionStringDto option33 = list.get(33);
        assertEquals(PREF_33_CODE, option33.getValue());
        assertEquals(PREF_33_LABEL, option33.getText());

        SelectOptionStringDto option34 = list.get(34);
        assertEquals(PREF_34_CODE, option34.getValue());
        assertEquals(PREF_34_LABEL, option34.getText());

        SelectOptionStringDto option35 = list.get(35);
        assertEquals(PREF_35_CODE, option35.getValue());
        assertEquals(PREF_35_LABEL, option35.getText());

        SelectOptionStringDto option36 = list.get(36);
        assertEquals(PREF_36_CODE, option36.getValue());
        assertEquals(PREF_36_LABEL, option36.getText());

        SelectOptionStringDto option37 = list.get(37);
        assertEquals(PREF_37_CODE, option37.getValue());
        assertEquals(PREF_37_LABEL, option37.getText());

        SelectOptionStringDto option38 = list.get(38);
        assertEquals(PREF_38_CODE, option38.getValue());
        assertEquals(PREF_38_LABEL, option38.getText());

        SelectOptionStringDto option39 = list.get(39);
        assertEquals(PREF_39_CODE, option39.getValue());
        assertEquals(PREF_39_LABEL, option39.getText());

        SelectOptionStringDto option40 = list.get(40);
        assertEquals(PREF_40_CODE, option40.getValue());
        assertEquals(PREF_40_LABEL, option40.getText());

        SelectOptionStringDto option41 = list.get(41);
        assertEquals(PREF_41_CODE, option41.getValue());
        assertEquals(PREF_41_LABEL, option41.getText());

        SelectOptionStringDto option42 = list.get(42);
        assertEquals(PREF_42_CODE, option42.getValue());
        assertEquals(PREF_42_LABEL, option42.getText());

        SelectOptionStringDto option43 = list.get(43);
        assertEquals(PREF_43_CODE, option43.getValue());
        assertEquals(PREF_43_LABEL, option43.getText());

        SelectOptionStringDto option44 = list.get(44);
        assertEquals(PREF_44_CODE, option44.getValue());
        assertEquals(PREF_44_LABEL, option44.getText());

        SelectOptionStringDto option45 = list.get(45);
        assertEquals(PREF_45_CODE, option45.getValue());
        assertEquals(PREF_45_LABEL, option45.getText());

        SelectOptionStringDto option46 = list.get(46);
        assertEquals(PREF_46_CODE, option46.getValue());
        assertEquals(PREF_46_LABEL, option46.getText());

        SelectOptionStringDto option47 = list.get(47);
        assertEquals(PREF_47_CODE, option47.getValue());
        assertEquals(PREF_47_LABEL, option47.getText());

        SelectOptionStringDto option99 = list.get(48);
        assertEquals(PREF_99_CODE, option99.getValue());
        assertEquals(PREF_99_LABEL, option99.getText());

    }

}
