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
    // CHECKSTYLE:OFF MagiNumber

    /** テスト対象 */
    @Autowired
    private GetPrefectureLgCodeService getPrefectureLgCodeService;

    @Test
    @Tag("TableTruncate")
    void test() {

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
        assertEquals("宮城県" ,getPrefectureLgCodeService.practiceName("04"));

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

}
