package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.PostalCodeBuildingResultDto;

/**
 * SearchAddressFloorPostalService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SearchAddressFloorPostalServiceTest.sql")
class SearchAddressFloorPostalServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchAddressFloorPostalService searchAddressFloorPostalService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        PostalCodeBuildingResultDto resultDto0 = searchAddressFloorPostalService.practice("131016", "100", "6222");
        List<SelectOptionIntegerDto> list0 = resultDto0.getListOptions();
        assertEquals(3, list0.size());

        SelectOptionIntegerDto optionDto00 = list0.get(0);
        assertEquals("丸の内パシフィックセンチュリープレイス丸の内224号室", optionDto00.getText());

        SelectOptionIntegerDto optionDto01 = list0.get(1);
        assertEquals("丸の内パシフィックセンチュリープレイス丸の内225号室", optionDto01.getText());

        SelectOptionIntegerDto optionDto02 = list0.get(2);
        assertEquals("丸の内パシフィックセンチュリープレイス丸の内226号室", optionDto02.getText());
    }

}
