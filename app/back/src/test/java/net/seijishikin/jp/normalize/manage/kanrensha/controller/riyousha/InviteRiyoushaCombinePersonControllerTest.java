package net.seijishikin.jp.normalize.manage.kanrensha.controller.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals; // NOPMD HighImport
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.seijishikin.jp.normalize.common_tool.utils.GetObjectMapperWithTimeModuleUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.RiyoushaCombinePersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InviteRiyoushaCombinePersonController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("../../service/riyousha/InviteRiyoushaCombinePersonServiceTest.sql")
class InviteRiyoushaCombinePersonControllerTest {
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

    /** 認証プロバイダ */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Test
    @Tag("ExternalService")
    void test() throws Exception {

        String mail = "aaa@politician.balanse.report.net";
        String password = "qwerty1234";

        // ログイン処理(SE権限)他者でも編集可能
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(mail, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 正常に仮テーブルに登録(かつタスク登録もメールも送信できている)
        RiyoushaCombinePersonCapsuleDto capsuleDto = new RiyoushaCombinePersonCapsuleDto();
        capsuleDto.setEmail("bbb@politician.balanse.report.net");
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.getUserDto().setRiyoushaCode(979);
        capsuleDto.getUserDto().setRiyoushaRole(UserRoleConstants.PARTNER_API);
        RiyoushaCombineOrgEntity combineEntity2 = new RiyoushaCombineOrgEntity();
        combineEntity2.setOrgRiyoushaCode(245);
        combineEntity2.setOrgName("イケてる風システム会社");
        combineEntity2.setRiyoushaRole(UserRoleConstants.PARTNER_API);
        combineEntity2.setPersonCode(311);
        combineEntity2.setPersonRiyoushaName("利用者テスト");
        capsuleDto.setCombineEntity(combineEntity2);
        capsuleDto.getUserDto().setUserPersonId(81);
        capsuleDto.getUserDto().setUserPersonCode(80);
        capsuleDto.getUserDto().setUserPersonName("aaa");
        capsuleDto.getUserDto().getListRoles().add("ROLE_" + UserRoleConstants.ADMIN);

        String path = PathRouteConstants.ROOT + "/riyousha-org/invite-person";

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
