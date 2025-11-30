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

import net.seijishikin.jp.normalize.common_tool.utils.GetObjectMapperWithTimeModuleUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaAdminMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaAdminMasterRepository;

/**
 * GetRiyoushaAdminDtoController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("../../service/riyousha/SaveRiyoushaAdminEntityServiceTest.sql")
class GetRiyoushaAdminDtoControllerTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    /** 利用者運営者マスタRepository */
    @Autowired
    private RiyoushaAdminMasterRepository riyoushaAdminMasterRepository;

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void test() throws Exception {

        RiyoushaAdminMasterEntity masterEntity = riyoushaAdminMasterRepository.findById(1).get();

        String path = "/riyousha/get-admin";

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(masterEntity)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
