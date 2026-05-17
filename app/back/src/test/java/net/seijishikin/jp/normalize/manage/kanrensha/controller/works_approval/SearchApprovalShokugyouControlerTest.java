package net.seijishikin.jp.normalize.manage.kanrensha.controller.works_approval;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

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
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.seijishikin.jp.normalize.common_tool.utils.GetObjectMapperWithTimeModuleUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.works_approval.SearchWorksApprovalCapsuleDto;

/**
 * SearchApprovalShokugyouControler単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("../../service/works_approval/SearchApprovalShokugyouServiceTest.sql")
class SearchApprovalShokugyouControlerTest {
    // CHECKSTYLE:OFF MagicNumber

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void test() throws Exception {

        // 自動入力のままでない編集があり未承認
        SearchWorksApprovalCapsuleDto capsuleDto = new SearchWorksApprovalCapsuleDto();
        capsuleDto.setIsExcludeFinishedTask(true);
        capsuleDto.setAllCount(0);
        capsuleDto.setLimit(30);
        capsuleDto.setPageNumber(0);
        capsuleDto.setStartDate(LocalDate.of(2025, 2, 1));
        capsuleDto.setEndDate(LocalDate.of(2025, 6, 1));

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = PathRouteConstants.ROOT + "/works-approval/search-shokugyou";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
