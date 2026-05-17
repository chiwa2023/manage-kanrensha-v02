package net.seijishikin.jp.normalize.manage.kanrensha.controller.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import net.seijishikin.jp.normalize.manage.kanrensha.constants.SeijidantaiDantaiKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SearchKanrenshaSeijidantaiCapsuleDto;

/**
 * SearchKanrenshaSeijidantaiListController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("../../service/kanrensha/SearchKanrenshaSeijidantaiListServiceTest.sql")
class SearchKanrenshaSeijidantaiListControllerTest {
    // CHECKSTYLE:OFF MagicNumber

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void test() throws Exception {

        SearchKanrenshaSeijidantaiCapsuleDto capsuleDto = new SearchKanrenshaSeijidantaiCapsuleDto();
        capsuleDto.setAllCount(124);
        capsuleDto.setLimit(20);
        capsuleDto.setPageNumber(4);
        capsuleDto.setPoliOrgNo("98-");
        capsuleDto.setName("ちゃらん");
        capsuleDto.setAddress("宮崎県架空市");
        capsuleDto.setDelegate("代表");
        List<String> listKbn = new ArrayList<>();
        listKbn.add(SeijidantaiDantaiKbnConstants.DANTAI_KBN_04);
        capsuleDto.setListDantaiKbn(listKbn);

        String path = PathRouteConstants.ROOT + "/user-kanrensha/search-seijidantai";

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
