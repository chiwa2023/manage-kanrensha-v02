package net.seijishikin.jp.normalize.manage.kanrensha.controller.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaSeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaSeijidantaiDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditUserKanrenshaSeijidantaiController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("../../service/kanrensha/EditKanrenshaSeijidantaiServiceTest.sql")
class EditUserKanrenshaSeijidantaiControllerTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    /** 政治団体マスタリポジトリ */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    /** 関連者政治団体取得Service */
    @Autowired
    private GetKanrenshaSeijidantaiDtoService getKanrenshaSeijidantaiDtoService;

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void test() throws Exception {

        final Integer editId = 1215;
        KanrenshaSeijidantaiMasterEntity masterEntity = kanrenshaSeijidantaiMasterRepository.findById(editId).get();
        KanrenshaSeijidantaiDto dto = getKanrenshaSeijidantaiDtoService.practice(masterEntity);
        dto.setPoliOrgNo("mnbvc"); // マスタを変更するとIdを変更するために全テーブルに波及
        SaveKanrenshaSeijidantaiCapsuleDto capsuleDto = new SaveKanrenshaSeijidantaiCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setKanrenshaSeijidantaiDto(dto);

        String path = PathRouteConstants.ROOT + "/user-kanrensha/edit-seijidantai";

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
