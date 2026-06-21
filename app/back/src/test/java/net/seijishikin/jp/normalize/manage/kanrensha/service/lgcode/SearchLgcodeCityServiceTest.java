package net.seijishikin.jp.normalize.manage.kanrensha.service.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionStringDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchAllCityLgcodeCapsuleDto;

/**
 * SearchLgcodeCityService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchLgcodeCityServiceTest.sql")
class SearchLgcodeCityServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchLgcodeCityService searchLgcodeCityService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        final String blank = "";
        
        SearchAllCityLgcodeCapsuleDto capsuleDto0 = new SearchAllCityLgcodeCapsuleDto();
        capsuleDto0.setLgCode("03");
        capsuleDto0.setIsSearch5Digit(false);

        List<SelectOptionStringDto> listAns0 = searchLgcodeCityService.practice(capsuleDto0);
        assertEquals(10, listAns0.size());

        SelectOptionStringDto optionDto00 = listAns0.get(0);
        assertEquals(blank, optionDto00.getValue());
        assertEquals(blank, optionDto00.getText());

        SelectOptionStringDto optionDto01 = listAns0.get(1);
        assertEquals("032018", optionDto01.getValue());
        assertEquals("岩手県盛岡市", optionDto01.getText());

        // 地方自治体コード順に整列(更新したからといってid順で一番最後になっていない)
        SelectOptionStringDto optionDto04 = listAns0.get(5);
        assertEquals("032134", optionDto04.getValue());
        assertEquals("岩手県大船渡市", optionDto04.getText());

        SelectOptionStringDto optionDto08 = listAns0.get(9);
        assertEquals("033812", optionDto08.getValue());
        assertEquals("岩手県胆沢郡金ケ崎町", optionDto08.getText());

        // 地方自治体コード5桁(法人番号用)
        SearchAllCityLgcodeCapsuleDto capsuleDto1 = new SearchAllCityLgcodeCapsuleDto();
        capsuleDto1.setLgCode("03");
        capsuleDto1.setIsSearch5Digit(true);

        List<SelectOptionStringDto> listAns1 = searchLgcodeCityService.practice(capsuleDto1);
        assertEquals(10, listAns1.size());

        SelectOptionStringDto optionDto10 = listAns1.get(0);
        assertEquals(blank, optionDto10.getValue());
        assertEquals(blank, optionDto10.getText());

        SelectOptionStringDto optionDto11 = listAns1.get(1);
        assertEquals("03201", optionDto11.getValue());
        assertEquals("岩手県盛岡市", optionDto11.getText());

        SelectOptionStringDto optionDto14 = listAns1.get(5);
        assertEquals("03213", optionDto14.getValue());
        assertEquals("岩手県大船渡市", optionDto14.getText());

        SelectOptionStringDto optionDto18 = listAns1.get(9);
        assertEquals("03381", optionDto18.getValue());
        assertEquals("岩手県胆沢郡金ケ崎町", optionDto18.getText());
    }

}
