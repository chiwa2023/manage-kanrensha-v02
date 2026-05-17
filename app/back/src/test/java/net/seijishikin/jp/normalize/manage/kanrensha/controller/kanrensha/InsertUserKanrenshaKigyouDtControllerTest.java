package net.seijishikin.jp.normalize.manage.kanrensha.controller.kanrensha;

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
import net.seijishikin.jp.normalize.common_tool.dto.input.InputOrgNameDto;
import net.seijishikin.jp.normalize.common_tool.utils.GetObjectMapperWithTimeModuleUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.HoujinShubetsuConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.InputKanrenshaPersonLeastDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertUserKanrenshaKigyouDtController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("../../service/kanrensha/InsertKanrenshaKigyouDtServiceTest.sql")
class InsertUserKanrenshaKigyouDtControllerTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void test() throws Exception {

        KanrenshaKigyouDtDto kanrenshaKigyouDtDto = new KanrenshaKigyouDtDto();

        // DTOの準備
        kanrenshaKigyouDtDto.setHoujinNo("1234567");
        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("テスト株式会社");
        inputOrgNameDto.setOrgNameKana("テストカブシキガイシャ");
        kanrenshaKigyouDtDto.setInputOrgNameDto(inputOrgNameDto);

        InputAddressDto inputAddressDto = new InputAddressDto();
        inputAddressDto.setPostalcode1("880");
        inputAddressDto.setPostalcode2("8501");
        inputAddressDto.setAddressAll("宮崎県架空市橘通東２丁目１０−１");
        inputAddressDto.setAddressPostal("宮崎県架空市橘通東");
        inputAddressDto.setAddressBlock("２丁目１０−１");
        inputAddressDto.setAddressBuilding("宮崎県庁");
        inputAddressDto.setLgCode("131016");
        inputAddressDto.setMachiazaId("324");
        inputAddressDto.setBlkId("131");
        inputAddressDto.setPrcId("249");
        inputAddressDto.setRsdtId("136");
        inputAddressDto.setRsdt2Id("978");
        inputAddressDto.setIsPostalEdit(true);
        inputAddressDto.setIsBlockEdit(true);
        inputAddressDto.setIsBuildingEdit(true);

        kanrenshaKigyouDtDto.setInputAddressDto(inputAddressDto);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("0985");
        inputAccessDto.setPhon2("26");
        inputAccessDto.setPhon3("7132");
        inputAccessDto.setEmail("test@example.com");
        inputAccessDto.setMyPortalUrl("https://my-portal/index.html");
        inputAccessDto.setSnsServiceName("弱小SNS");
        inputAccessDto.setSnsPortalUrl("https://jyakusho-sns/");
        inputAccessDto.setSnsAccount("@taro123456");
        kanrenshaKigyouDtDto.setInputAccessDto(inputAccessDto);

        InputKanrenshaPersonLeastDto orgDelegateLeastDto = new InputKanrenshaPersonLeastDto();
        orgDelegateLeastDto.setPersonKanrenshaCode("P12345");
        orgDelegateLeastDto.setPersonName("代表者　太郎");
        kanrenshaKigyouDtDto.setOrgDelegateLeastDto(orgDelegateLeastDto);

        kanrenshaKigyouDtDto.setHoujinSbts(HoujinShubetsuConstants.GAIKOKU_KAISHA); // 外国籍
        kanrenshaKigyouDtDto.setIsShiten(true);

        SaveKanrenshaKigyouDtCapsuleDto capsuleDto = new SaveKanrenshaKigyouDtCapsuleDto();
        capsuleDto.setKanrenshaKigyouDtDto(kanrenshaKigyouDtDto);
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        String path = PathRouteConstants.ROOT + "/user-kanrensha/add-kigyou-dt";

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
