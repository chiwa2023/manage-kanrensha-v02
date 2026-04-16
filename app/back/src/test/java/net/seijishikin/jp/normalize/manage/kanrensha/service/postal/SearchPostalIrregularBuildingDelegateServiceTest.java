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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SearchPostalIllegularCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SearchPostalIllegularResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;

/**
 * SearchPostalIrregularBuildingDelegateService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SearchPostalIrregularBuildingDelegateServiceTest.sql")
class SearchPostalIrregularBuildingDelegateServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchPostalIrregularBuildingDelegateService searchPostalIrregularBuildingDelegateService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        SearchPostalIllegularCapsuleDto capsuleDto = new SearchPostalIllegularCapsuleDto();

        SearchPostalIllegularResultDto resultDto = searchPostalIrregularBuildingDelegateService.practice(capsuleDto);

        List<AddressPostalIrregularEntity> list = resultDto.getListItem();
        assertEquals(3, list.size());
        
        AddressPostalIrregularEntity entity0 = list.get(0);
        assertEquals("中央アエル（地階・階層不明）", entity0.getAddressOrg());
        AddressPostalIrregularEntity entity1 = list.get(1);
        assertEquals("中央ＳＳ３０住友生命仙台中央ビル（地階・階層不明）", entity1.getAddressOrg());
        AddressPostalIrregularEntity entity2 = list.get(2);
        assertEquals("中瀬ワールドビジネスガーデン（地階・階層不明）", entity2.getAddressOrg());
               
    }

}
