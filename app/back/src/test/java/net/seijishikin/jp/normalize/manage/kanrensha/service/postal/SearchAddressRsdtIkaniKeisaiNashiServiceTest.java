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

import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionStringDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.PostalCodeBlockResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;

/**
 * SearchAddressRsdtIkaniKeisaiNashiService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SearchAddressRsdtIkaniKeisaiNashiServiceTest.sql")
class SearchAddressRsdtIkaniKeisaiNashiServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchAddressRsdtIkaniKeisaiNashiService searchAddressRsdtIkaniKeisaiNashiService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddressPostalEntity postalEntity = new AddressPostalEntity();
        postalEntity.setLgCode("011037");
        postalEntity.setAddressName("北海道札幌市東区");
        postalEntity.setPostalcode1("065");
        postalEntity.setPostalcode2("0000");

        PostalCodeBlockResultDto resultDto = searchAddressRsdtIkaniKeisaiNashiService.practice(postalEntity);

        assertEquals(postalEntity.getLgCode(), resultDto.getLgCode());
        assertTrue(resultDto.getIsGyouseikuData());

        List<SelectOptionStringDto> list = resultDto.getListOptions();
        assertEquals(4, list.size());

        SelectOptionStringDto optionDto0 = list.get(0);
        assertEquals("雁来町9番地20号", optionDto0.getText());
        assertEquals("北海道札幌市東区雁来町9番地20号", optionDto0.getValue());

        SelectOptionStringDto optionDto1 = list.get(1);
        assertEquals("雁来町10番地", optionDto1.getText());
        assertEquals("北海道札幌市東区雁来町10番地", optionDto1.getValue());

        SelectOptionStringDto optionDto2 = list.get(2);
        assertEquals("雁来町10番地1号", optionDto2.getText());
        assertEquals("北海道札幌市東区雁来町10番地1号", optionDto2.getValue());

        SelectOptionStringDto optionDto3 = list.get(3);
        assertEquals("雁来町11番地1号", optionDto3.getText());
        assertEquals("北海道札幌市東区雁来町11番地1号", optionDto3.getValue());
    }

}
