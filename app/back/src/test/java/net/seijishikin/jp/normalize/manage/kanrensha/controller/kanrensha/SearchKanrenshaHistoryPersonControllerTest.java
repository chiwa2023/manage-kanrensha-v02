package net.seijishikin.jp.normalize.manage.kanrensha.controller.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.SearchHistoryKanrenshaCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.utils.GetObjectMapperWithTimeModuleUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * SearchKanrenshaHistoryPersonController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("../../service/kanrensha/SearchKanrenshaHistoryPersonServiceTest.sql")
class SearchKanrenshaHistoryPersonControllerTest {
    // CHECKSTYLE:OFF MagicNumber

    /** WebApplicationContext */
    @Autowired
    private WebApplicationContext context;

    /** MockMvc */
    private MockMvc mockMvc;

    /** setup */
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context) //
                .apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void test() throws Exception {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = new SearchHistoryKanrenshaCapsuleDto();
        capsuleDto.setAllCount(124);
        capsuleDto.setPageNumber(18);
        capsuleDto.setLimit(20);
        capsuleDto.setPrefCode("01");
        capsuleDto.setSearchNaturalWords("太郎");

        String path = PathRouteConstants.ROOT + "/kanrensha-history/search-person";

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
