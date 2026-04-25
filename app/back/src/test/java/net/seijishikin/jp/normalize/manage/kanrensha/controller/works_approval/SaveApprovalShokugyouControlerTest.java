package net.seijishikin.jp.normalize.manage.kanrensha.controller.works_approval;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.works_approval.SaveWorksApprovalCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SaveApprovalShokugyouControler単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("../../service/works_approval/SaveApprovalShokugyouServiceTest.sql")
class SaveApprovalShokugyouControlerTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    /** 関連者個人属性Repository */
    @Autowired
    private KanrenshaPersonPropertyRepository kanrenshaPersonPropertyRepository;

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void test() throws Exception {

        SaveWorksApprovalCapsuleDto capsuleDto = new SaveWorksApprovalCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        List<KanrenshaPersonPropertyEntity> listShokugyou = kanrenshaPersonPropertyRepository.findAll();

        final String newGyoushu = "小売";
        listShokugyou.get(0).setGyoushu(newGyoushu);
        // 画面上は変更できない姓名に関する事項を変更しても変更対象ではない
        final String notGyoushu = "職業";
        listShokugyou.get(1).setFirstName(notGyoushu);

        listShokugyou.get(2).setShokugyouUserWrite("素浪人");
        listShokugyou.get(2).setKigyouDtNo("9988");

        capsuleDto.setListShokugyou(listShokugyou);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = PathRouteConstants.ROOT + "/works-approval/save-shokugyou";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
