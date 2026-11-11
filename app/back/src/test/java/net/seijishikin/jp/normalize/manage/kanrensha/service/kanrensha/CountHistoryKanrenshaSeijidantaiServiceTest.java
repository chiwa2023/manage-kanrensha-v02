package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.SearchHistoryKanrenshaCapsuleDto;

/**
 * CountHistoryKanrenshaSeijidantaiService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("CountHistoryKanrenshaSeijidantaiServiceTest.sql")
class CountHistoryKanrenshaSeijidantaiServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CountHistoryKanrenshaSeijidantaiService countHistoryKanrenshaSeijidantaiService;

    @Test
    @Tag("TableTruncate") // NOPMD
    void test0() throws Exception {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setSearchNaturalWords("xx");

        assertEquals("", countHistoryKanrenshaSeijidantaiService.practice(capsuleDto));
    }

    @Test
    @Tag("TableTruncate")
    void test1() throws Exception {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setSearchNaturalWords("ca");

        assertEquals("北海道：2件;青森県：2件;岩手県：2件;宮城県：2件;秋田県：2件;山形県：2件;福島県：2件;茨城県：2件;栃木県：2件;群馬県：2件;",
                countHistoryKanrenshaSeijidantaiService.practice(capsuleDto));
    }

    @Test
    @Tag("TableTruncate")
    void test2() throws Exception {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setSearchNaturalWords("cb");

        assertEquals("埼玉県：2件;千葉県：2件;東京都：2件;神奈川県：2件;新潟県：2件;富山県：2件;石川県：2件;福井県：2件;山梨県：2件;長野県：2件;",
                countHistoryKanrenshaSeijidantaiService.practice(capsuleDto));
    }

    @Test
    @Tag("TableTruncate")
    void test3() throws Exception {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setSearchNaturalWords("cc");

        assertEquals("岐阜県：2件;静岡県：2件;愛知県：2件;三重県：2件;滋賀県：2件;京都府：2件;大阪府：2件;兵庫県：2件;奈良県：2件;和歌山県：2件;",
                countHistoryKanrenshaSeijidantaiService.practice(capsuleDto));
    }

    @Test
    @Tag("TableTruncate")
    void test4() throws Exception {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setSearchNaturalWords("cd");

        assertEquals("鳥取県：2件;島根県：2件;岡山県：2件;広島県：2件;山口県：2件;徳島県：2件;香川県：2件;愛媛県：2件;高知県：2件;福岡県：2件;",
                countHistoryKanrenshaSeijidantaiService.practice(capsuleDto));
    }

    @Test
    @Tag("TableTruncate")
    void test5() throws Exception {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setSearchNaturalWords("ce");

        assertEquals("佐賀県：2件;長崎県：2件;熊本県：2件;大分県：2件;宮崎県：2件;鹿児島県：2件;沖縄県：2件;その他：2件;",
                countHistoryKanrenshaSeijidantaiService.practice(capsuleDto));
    }

    private SearchHistoryKanrenshaCapsuleDto createCapsuleDto() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = new SearchHistoryKanrenshaCapsuleDto();
        capsuleDto.setAllCount(124);
        capsuleDto.setPageNumber(18);
        capsuleDto.setLimit(20);

        return capsuleDto;
    }

}
