package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionStringDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.PostalCodeBlockResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;

/**
 * SearchAddressRsdtOtherService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SearchAddressRsdtOtherServiceTest.sql")
class SearchAddressRsdtOtherServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchAddressRsdtOtherService searchAddressRsdtOtherService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddressPostalEntity postalEntity = new AddressPostalEntity();
        postalEntity.setLgCode("012041");
        postalEntity.setAddressName("北海道旭川市神居町西丘");
        postalEntity.setPostalcode1("074");
        postalEntity.setPostalcode2("1181");

        PostalCodeBlockResultDto resultDto = searchAddressRsdtOtherService.practice(postalEntity);

        assertEquals(postalEntity.getLgCode(), resultDto.getLgCode());
        assertTrue(resultDto.getIsGyouseikuData());

        List<SelectOptionStringDto> list = resultDto.getListOptions();
        assertEquals(4, list.size());

        SelectOptionStringDto optionDto0 = list.get(0);
        assertEquals("1番地", optionDto0.getText());
        assertEquals("北海道旭川市神居町西丘1番地", optionDto0.getValue());

        SelectOptionStringDto optionDto1 = list.get(1);
        assertEquals("2番地1号", optionDto1.getText());
        assertEquals("北海道旭川市神居町西丘2番地1号", optionDto1.getValue());

        SelectOptionStringDto optionDto2 = list.get(2);
        assertEquals("2番地2号", optionDto2.getText());
        assertEquals("北海道旭川市神居町西丘2番地2号", optionDto2.getValue());

        SelectOptionStringDto optionDto3 = list.get(3);
        assertEquals("7番地2号", optionDto3.getText());
        assertEquals("北海道旭川市神居町西丘7番地2号", optionDto3.getValue());
    }

}
