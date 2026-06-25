package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionStringDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.PostalCodeBlockResultDto;

/**
 * SearchAddressBlockService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchAddressBlockServiceTest.sql")
class SearchAddressBlockServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchAddressBlockService searchAddressBlockService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {
        // 郵便番号選択はIdで表示して、選択されたIdを返してもらっているので最新／履歴は問わない
        PostalCodeBlockResultDto resultDto0 = searchAddressBlockService.practice(645, true);
        assertEquals("011029", resultDto0.getLgCode());
        List<SelectOptionStringDto> list0 = resultDto0.getListOptions();
        assertEquals(33, list0.size());
        SelectOptionStringDto dto00 = list0.get(0);
        assertEquals("札幌市北区北十条西一丁目1番地1号", dto00.getValue());
        assertEquals("1番地1号", dto00.getText());

        PostalCodeBlockResultDto resultDto1 = searchAddressBlockService.practice(1254, false);

        assertFalse(resultDto1.getIsGyouseikuData());
        List<SelectOptionStringDto> list1 = resultDto1.getListOptions();
        assertEquals(1, list1.size());
        SelectOptionStringDto dto10 = list1.get(0);
        assertEquals("東京都千代田区丸の内一丁目11番1号", dto10.getValue());
        assertEquals("一丁目11番1号", dto10.getText());
        
        PostalCodeBlockResultDto resultDto2 = searchAddressBlockService.practice(649, true);
        assertEquals("011029", resultDto2.getLgCode());
        assertTrue(resultDto2.getIsGyouseikuData());
        List<SelectOptionStringDto> list2 = resultDto2.getListOptions();
        assertEquals(1, list2.size());
        SelectOptionStringDto dto20 = list2.get(0);
        assertEquals("札幌市北区北十八条西十一丁目9番地", dto20.getValue());
        assertEquals("十一丁目★9番地", dto20.getText());

    }

}
