package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionIntegerDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.PostalCodePostalResultDto;

/**
 * SearchAddressPostalService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SearchAddressPostalServiceTest.sql")
class SearchAddressPostalServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchAddressPostalService searchAddressPostalService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 地方自治体住居取得
        PostalCodePostalResultDto resultDto0 = searchAddressPostalService.practice("001", "0015");
        assertTrue(resultDto0.getIsGyouseikuData());

        List<SelectOptionIntegerDto> list0 = resultDto0.getListOptions();
        assertEquals(5, list0.size());

        SelectOptionIntegerDto dto00 = list0.get(0);
        assertEquals("北海道札幌市北区北十五条西一丁目", dto00.getText());

        SelectOptionIntegerDto dto01 = list0.get(1);
        assertEquals("北海道札幌市北区北十五条西二丁目", dto01.getText());

        SelectOptionIntegerDto dto02 = list0.get(2);
        assertEquals("北海道札幌市北区北十五条西三丁目", dto02.getText());

        SelectOptionIntegerDto dto03 = list0.get(3);
        assertEquals("北海道札幌市北区北十五条西四丁目", dto03.getText());

        SelectOptionIntegerDto dto04 = list0.get(4);
        assertEquals("北海道札幌市北区北十五条西五丁目", dto04.getText());

        PostalCodePostalResultDto resultDto1 = searchAddressPostalService.practice("100", "6222");
        assertFalse(resultDto1.getIsGyouseikuData());
        List<SelectOptionIntegerDto> list1 = resultDto1.getListOptions();
        assertEquals(1, list1.size());

        SelectOptionIntegerDto dto10 = list1.get(0);
        assertEquals("東京都千代田区丸の内", dto10.getText());
    }

}
