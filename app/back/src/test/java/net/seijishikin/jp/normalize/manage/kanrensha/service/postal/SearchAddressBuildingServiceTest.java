package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionIntegerDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.PostalCodeBuildingResultDto;

/**
 * SearchAddressBuildingService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SearchAddressBuildingServiceTest.sql")
class SearchAddressBuildingServiceTest {

    /** テスト対象 */
    @Autowired
    private SearchAddressBuildingService searchAddressBuildingService;
    
    @Test
    @Tag("TableTruncate")
    void test() throws Exception {
        
        // 建物欄に設定がない場合は空リストを返す
        PostalCodeBuildingResultDto resultDto0 = searchAddressBuildingService.practice("011029", "北区北十五条西一丁目1番地1号");
        assertTrue(resultDto0.getListOptions().isEmpty());

        // 建物欄に設定がある場合
        PostalCodeBuildingResultDto resultDto1 = searchAddressBuildingService.practice("011029", "北十五条西一丁目1番地2号");
        List<SelectOptionIntegerDto> list1 = resultDto1.getListOptions();
        assertEquals(2, list1.size());
        SelectOptionIntegerDto optionDto10 = list1.get(0);
        assertEquals("AAマンション101号室", optionDto10.getText());
        SelectOptionIntegerDto optionDto11 = list1.get(1);
        assertEquals("AAマンション102号室", optionDto11.getText());
    }

}
