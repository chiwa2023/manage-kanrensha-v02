package net.seijishikin.jp.normalize.manage.kanrensha.controller.riyousha;

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

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAccessDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputPersonNameDto;
import net.seijishikin.jp.normalize.common_tool.utils.GetObjectMapperWithTimeModuleUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SaveRiyoushaPartnerApiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SaveRiyoushaPartnerApiEntityController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("../../service/riyousha/SaveRiyoushaPartnerApiEntityServiceTest.sql")
class SaveRiyoushaPartnerApiEntityControllerTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void test() throws Exception {

        SaveRiyoushaPartnerApiCapsuleDto capsuleDto = new SaveRiyoushaPartnerApiCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        InputPersonNameDto nameDto = this.createPersonName();
        InputAddressDto addressDto = this.createAddress();
        InputAccessDto accessDto = this.createAccess();

        capsuleDto.getRiyoushaPartnerApiDto().setInputPersonNameDto(nameDto);
        capsuleDto.getRiyoushaPartnerApiDto().setInputAddressDto(addressDto);
        capsuleDto.getRiyoushaPartnerApiDto().setInputAccessDto(accessDto);

        String path = "/riyousha/save-partner-api";

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

    /**
     * 個人氏名Dtoを作成する
     * 
     * @return 個人氏名Dto
     */
    private InputPersonNameDto createPersonName() {
        InputPersonNameDto dto = new InputPersonNameDto();

        dto.setFirstName("太郎");
        dto.setFirstNameKana("たろう");
        dto.setLastName("運営者");
        dto.setLastNameKana("うんえいしゃ");
        dto.setMiddleName("ミカエル");
        dto.setMiddleNameKana("みかえる");

        dto.setAllName("運営者　ミカエル太郎");
        dto.setAllNameKana("うんえいしゃ　みかえるたろう");

        return dto;
    }

    /**
     * 住所Dtoを作成する
     * 
     * @return 住所Dto
     */
    private InputAddressDto createAddress() {
        InputAddressDto dto = new InputAddressDto();

        dto.setAddressPostal("和歌山県架空市湖畔町");
        dto.setAddressBlock("100番地の7");
        dto.setAddressBuilding("星形ビル444");
        dto.setPostalcode1("987");
        dto.setPostalcode2("5432");
        dto.setLgCode("123456");
        dto.setMachiazaId("2345");
        dto.setBlkId("3456");
        dto.setPrcId("4567");
        dto.setRsdtId("5678");
        dto.setRsdt2Id("6789");
        dto.setIsPostalEdit(true);
        dto.setIsBlockEdit(true);
        dto.setIsBuildingEdit(true);

        dto.setAddressAll("和歌山県架空市湖畔町100番地の7星形ビル444");

        return dto;

    }

    /**
     * 連絡先Dtoを作成する
     * 
     * @return 連絡先Dto
     */
    private InputAccessDto createAccess() {
        InputAccessDto dto = new InputAccessDto();

        dto.setPhon1("567");
        dto.setPhon2("8901");
        dto.setPhon3("2345");
        dto.setEmail("test@example.com");
        dto.setMyPortalUrl("http://example.com/");

        dto.setSnsServiceId(1);
        dto.setSnsServiceCode(2);
        dto.setSnsServiceName("弱小SNS");
        dto.setSnsPortalUrl("http://jakushou.sns.net/");
        dto.setSnsAccount("@taro9898");

        return dto;
    }

}
