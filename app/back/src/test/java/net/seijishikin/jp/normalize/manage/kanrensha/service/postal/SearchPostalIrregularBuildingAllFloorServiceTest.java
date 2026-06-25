package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.GetDetailPostalIllegularCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.GetDetailPostalIllegularResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;

/**
 * SearchPostalIrregularBuildingAllFloorService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SearchPostalIrregularBuildingAllFloorServiceTest.sql")
class SearchPostalIrregularBuildingAllFloorServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchPostalIrregularBuildingAllFloorService searchPostalIrregularBuildingAllFloorService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        GetDetailPostalIllegularCapsuleDto capsuleDto = new GetDetailPostalIllegularCapsuleDto();
        capsuleDto.setAddressWords("新都心明治安田生命さいたま新都心ビル");

        GetDetailPostalIllegularResultDto resultDto = searchPostalIrregularBuildingAllFloorService.practice(capsuleDto);

        List<AddressPostalIrregularEntity> list = resultDto.getListIrregular();
        assertEquals(12, list.size());

        AddressPostalIrregularEntity entity = list.get(3); // 4番目= 3階
        assertEquals("330", entity.getPostalcode1());
        assertEquals("6003", entity.getPostalcode2());
        assertEquals("新都心明治安田生命さいたま新都心ビル（３階）", entity.getAddressOrg());
    }

}
