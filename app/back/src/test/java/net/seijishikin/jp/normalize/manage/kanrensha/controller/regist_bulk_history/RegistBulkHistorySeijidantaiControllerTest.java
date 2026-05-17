package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_bulk_history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
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

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_history.UpdateWkTblHistorySeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiHistoryRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import net.seijishikin.jp.normalize.common_tool.utils.GetObjectMapperWithTimeModuleUtil;

/**
 * RegistBulkMasterStdSeijidantaiController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("../../service/regist_bulk_history/RegistBulkHistorySeijidantaiServiceTest.sql")
class RegistBulkHistorySeijidantaiControllerTest {
    // CHECKSTYLE:OFF

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblKanrenshaSeijidantaiHistoryRepository wkTblKanrenshaSeijidantaiHistoryRepository;

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void test() throws Exception {

        UpdateWkTblHistorySeijidantaiCapsuleDto capsuleDto01 = new UpdateWkTblHistorySeijidantaiCapsuleDto();
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto01.setUserDto(userDto);
        WkTblKanrenshaSeijidantaiHistoryEntity entityInput01 = wkTblKanrenshaSeijidantaiHistoryRepository.findById(313)
                .get();
        WkTblKanrenshaSeijidantaiHistoryEntity entityBase = new WkTblKanrenshaSeijidantaiHistoryEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setAllAddress("山ビル2F");
        entityBase.setKanrenshaName("");
        capsuleDto01.setWkTblKanrenshaSeijidantaiHistoryEntity(entityBase);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = PathRouteConstants.ROOT + "/regist-bulk-history/update-seijidantai";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto01)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
