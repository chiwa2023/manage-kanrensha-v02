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

import net.seijishikin.jp.normalize.common_tool.utils.GetObjectMapperWithTimeModuleUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaPersonDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditUserKanrenshaPersonController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("../../service/kanrensha/EditKanrenshaPersonServiceTest.sql")
class EditUserKanrenshaPersonControllerTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    /** 個人マスタリポジトリ */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /** 関連者個人取得Service */
    @Autowired
    private GetKanrenshaPersonDtoService getKanrenshaPersonDtoService;

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void test() throws Exception {

        final Integer editId = 391;
        KanrenshaPersonMasterEntity masterEntity = kanrenshaPersonMasterRepository.findById(editId).get();
        KanrenshaPersonDto dto = getKanrenshaPersonDtoService.practice(masterEntity);
        dto.getInputPersonNameDto().setAllName("sdlfkz");
        SaveKanrenshaPersonCapsuleDto capsuleDto = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setKanrenshaPersonDto(dto);

        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        String path = PathRouteConstants.ROOT + "/user-kanrensha/edit-person";

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
