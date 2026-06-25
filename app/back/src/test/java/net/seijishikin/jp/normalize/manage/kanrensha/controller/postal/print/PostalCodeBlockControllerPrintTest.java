package net.seijishikin.jp.normalize.manage.kanrensha.controller.postal.print;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.seijishikin.jp.normalize.common_tool.utils.GetObjectMapperWithTimeModuleUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.PostalCodeCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.WriteLogService;

/**
 * 番地まで住所ログ出力実行(テストではない)
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PostalCodeBlockControllerPrintTest {
    // CHECKSTYLE:OFF MagicNumber

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    /** ログ出力Service */
    @Autowired
    private WriteLogService writeLogService;

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void test() throws Exception {
        PostalCodeCapsuleDto capsuleDto = new PostalCodeCapsuleDto();
        capsuleDto.setSelectedPostal(147214);
        capsuleDto.setIsGyouseikuData(true);

        String path = PathRouteConstants.ROOT + "/postal-search/block";

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String data = mockMvc.perform(post(path).content( // NOPMD LawDemeter
                objectMapper.writeValueAsString(capsuleDto)).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn()
                .getResponse().getContentAsString();

        assertNotNull(data);
        writeLogService.writeInfo(data);

    }

}
