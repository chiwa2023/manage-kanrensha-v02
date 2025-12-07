package net.seijishikin.jp.normalize.manage.kanrensha.service.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * GetPrefectureLgCodeService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class GetPrefectureLgCodeServiceTest {

    /** テスト対象 */
    @Autowired
    private GetPrefectureLgCodeService getPrefectureLgCodeService;

    @Test
    @Tag("TableTruncate")
    void test() {

        // 010006,北海道
        assertEquals(GetPrefectureLgCodeService.PREF_01, getPrefectureLgCodeService.practice("北海道架空市"));

        // 020001,青森県
        assertEquals(GetPrefectureLgCodeService.PREF_02, getPrefectureLgCodeService.practice("青森県架空市"));

        // 030007,岩手県
        assertEquals(GetPrefectureLgCodeService.PREF_03, getPrefectureLgCodeService.practice("岩手県架空市"));

        // 040002,宮城県
        assertEquals(GetPrefectureLgCodeService.PREF_04, getPrefectureLgCodeService.practice("宮城県架空市"));

        // 050008,秋田県
        assertEquals(GetPrefectureLgCodeService.PREF_05, getPrefectureLgCodeService.practice("秋田県架空市"));

        // 060003,山形県
        assertEquals(GetPrefectureLgCodeService.PREF_06, getPrefectureLgCodeService.practice("山形県架空市"));

        // 070009,福島県
        assertEquals(GetPrefectureLgCodeService.PREF_07, getPrefectureLgCodeService.practice("福島県架空市"));

        // 080004,茨城県
        assertEquals(GetPrefectureLgCodeService.PREF_08, getPrefectureLgCodeService.practice("茨城県架空市"));

        // 090000,栃木県
        assertEquals(GetPrefectureLgCodeService.PREF_09, getPrefectureLgCodeService.practice("栃木県架空市"));

        // 100005,群馬県
        assertEquals(GetPrefectureLgCodeService.PREF_10, getPrefectureLgCodeService.practice("群馬県架空市"));

        // 110001,埼玉県
        assertEquals(GetPrefectureLgCodeService.PREF_11, getPrefectureLgCodeService.practice("埼玉県架空市"));

        // 120006,千葉県
        assertEquals(GetPrefectureLgCodeService.PREF_12, getPrefectureLgCodeService.practice("千葉県架空市"));

        // 130001,東京都
        assertEquals(GetPrefectureLgCodeService.PREF_13, getPrefectureLgCodeService.practice("東京都架空市"));

        // 140007,神奈川県
        assertEquals(GetPrefectureLgCodeService.PREF_14, getPrefectureLgCodeService.practice("神奈川県架空市"));

        // 150002,新潟県
        assertEquals(GetPrefectureLgCodeService.PREF_15, getPrefectureLgCodeService.practice("新潟県架空市"));

        // 160008,富山県
        assertEquals(GetPrefectureLgCodeService.PREF_16, getPrefectureLgCodeService.practice("富山県架空市"));

        // 170003,石川県
        assertEquals(GetPrefectureLgCodeService.PREF_17, getPrefectureLgCodeService.practice("石川県架空市"));

        // 180009,福井県
        assertEquals(GetPrefectureLgCodeService.PREF_18, getPrefectureLgCodeService.practice("福井県架空市"));

        // 190004,山梨県
        assertEquals(GetPrefectureLgCodeService.PREF_19, getPrefectureLgCodeService.practice("山梨県架空市"));

        // 200000,長野県
        assertEquals(GetPrefectureLgCodeService.PREF_20, getPrefectureLgCodeService.practice("長野県架空市"));

        // 210005,岐阜県
        assertEquals(GetPrefectureLgCodeService.PREF_21, getPrefectureLgCodeService.practice("岐阜県架空市"));

        // 220001,静岡県
        assertEquals(GetPrefectureLgCodeService.PREF_22, getPrefectureLgCodeService.practice("静岡県架空市"));

        // 230006,愛知県
        assertEquals(GetPrefectureLgCodeService.PREF_23, getPrefectureLgCodeService.practice("愛知県架空市"));

        // 240001,三重県
        assertEquals(GetPrefectureLgCodeService.PREF_24, getPrefectureLgCodeService.practice("三重県架空市"));

        // 250007,滋賀県
        assertEquals(GetPrefectureLgCodeService.PREF_25, getPrefectureLgCodeService.practice("滋賀県架空市"));

        // 260002,京都府
        assertEquals(GetPrefectureLgCodeService.PREF_26, getPrefectureLgCodeService.practice("京都府架空市"));

        // 270008,大阪府
        assertEquals(GetPrefectureLgCodeService.PREF_27, getPrefectureLgCodeService.practice("大阪府架空市"));

        // 280003,兵庫県
        assertEquals(GetPrefectureLgCodeService.PREF_28, getPrefectureLgCodeService.practice("兵庫県架空市"));

        // 290009,奈良県
        assertEquals(GetPrefectureLgCodeService.PREF_29, getPrefectureLgCodeService.practice("奈良県架空市"));

        // 300004,和歌山県
        assertEquals(GetPrefectureLgCodeService.PREF_30, getPrefectureLgCodeService.practice("和歌山県架空市"));

        // 310000,鳥取県
        assertEquals(GetPrefectureLgCodeService.PREF_31, getPrefectureLgCodeService.practice("鳥取県架空市"));

        // 320005,島根県
        assertEquals(GetPrefectureLgCodeService.PREF_32, getPrefectureLgCodeService.practice("島根県架空市"));

        // 330001,岡山県
        assertEquals(GetPrefectureLgCodeService.PREF_33, getPrefectureLgCodeService.practice("岡山県架空市"));

        // 340006,広島県
        assertEquals(GetPrefectureLgCodeService.PREF_34, getPrefectureLgCodeService.practice("広島県架空市"));

        // 350001,山口県
        assertEquals(GetPrefectureLgCodeService.PREF_35, getPrefectureLgCodeService.practice("山口県架空市"));

        // 360007,徳島県
        assertEquals(GetPrefectureLgCodeService.PREF_36, getPrefectureLgCodeService.practice("徳島県架空市"));

        // 370002,香川県
        assertEquals(GetPrefectureLgCodeService.PREF_37, getPrefectureLgCodeService.practice("香川県架空市"));

        // 380008,愛媛県
        assertEquals(GetPrefectureLgCodeService.PREF_38, getPrefectureLgCodeService.practice("愛媛県架空市"));

        // 390003,高知県
        assertEquals(GetPrefectureLgCodeService.PREF_39, getPrefectureLgCodeService.practice("高知県架空市"));

        // 400009,福岡県
        assertEquals(GetPrefectureLgCodeService.PREF_40, getPrefectureLgCodeService.practice("福岡県架空市"));

        // 410004,佐賀県
        assertEquals(GetPrefectureLgCodeService.PREF_41, getPrefectureLgCodeService.practice("佐賀県架空市"));

        // 420000,長崎県
        assertEquals(GetPrefectureLgCodeService.PREF_42, getPrefectureLgCodeService.practice("長崎県架空市"));

        // 430005,熊本県
        assertEquals(GetPrefectureLgCodeService.PREF_43, getPrefectureLgCodeService.practice("熊本県架空市"));

        // 440001,大分県
        assertEquals(GetPrefectureLgCodeService.PREF_44, getPrefectureLgCodeService.practice("大分県架空市"));

        // 450006,宮崎県
        assertEquals(GetPrefectureLgCodeService.PREF_45, getPrefectureLgCodeService.practice("宮崎県架空市"));

        // 460001,鹿児島県
        assertEquals(GetPrefectureLgCodeService.PREF_46, getPrefectureLgCodeService.practice("鹿児島県架空市"));

        // 470007,沖縄県
        assertEquals(GetPrefectureLgCodeService.PREF_47, getPrefectureLgCodeService.practice("沖縄県架空市"));

        // その他
        assertEquals(GetPrefectureLgCodeService.PREF_99, getPrefectureLgCodeService.practice("架空市"));
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

}
