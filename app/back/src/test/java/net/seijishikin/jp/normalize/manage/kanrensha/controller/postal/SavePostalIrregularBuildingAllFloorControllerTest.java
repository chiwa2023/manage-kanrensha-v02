package net.seijishikin.jp.normalize.manage.kanrensha.controller.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.seijishikin.jp.normalize.common_tool.utils.GetObjectMapperWithTimeModuleUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SavePostalIrregularCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SavePostalIrregularBuildingAllFloorController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("../../service/postal/SavePostalIrregularBuildingAllFloorServiceTest.sql")
class SavePostalIrregularBuildingAllFloorControllerTest {
    // CHECKSTYLE:OFF MagicNumber

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    /** 郵便番号不規則データRepository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void test() throws Exception {

        AddressPostalIrregularEntity entityEdit = addressPostalIrregularRepository.findById(776).get();

        entityEdit.setAddressPostal("埼玉県さいたま市中央区新都心");
        entityEdit.setAddressBlock("11番2");

        SavePostalIrregularCapsuleDto capsuleDto = new SavePostalIrregularCapsuleDto();
        capsuleDto.setAddressPostalIrregularEntity(entityEdit);
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        String path = PathRouteConstants.ROOT + "/postal-irregular/save-building";

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
