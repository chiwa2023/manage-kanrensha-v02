package net.seijishikin.jp.normalize.manage.kanrensha.controller.kanrensha;

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaPersonDtoService;

/**
 * EditUserKanrenshaPersonController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("EditKanrenshaPersonServiceTest.sql")
class EditUserKanrenshaPersonControllerTest {
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

    /** 個人マスタリポジトリ */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /** 関連者個人取得Service */
    @Autowired
    private GetKanrenshaPersonDtoService getKanrenshaPersonDtoService;

    /** 認証プロバイダ */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Test
    @Tag("TableTruncate") // NOPMD
    void testRiyousha() throws Exception {

        /* 運営者でログインするときはどの関連者であっても編集可能 */
        String mail = "aaa@politician.balanse.report.net";
        String password = "qwerty1234";

        // ログイン処理(運営者)
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(mail, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final Integer editId = 391;
        KanrenshaPersonMasterEntity masterEntity = kanrenshaPersonMasterRepository.findById(editId).get();
        KanrenshaPersonDto dto = getKanrenshaPersonDtoService.practice(masterEntity);
        dto.getInputPersonNameDto().setAllName("sdlfkz"); // NOPMD
        SaveKanrenshaPersonCapsuleDto capsuleDto = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto.setKanrenshaPersonDto(dto);

        capsuleDto.getUserDto().setUserPersonId(81);
        capsuleDto.getUserDto().setUserPersonCode(80);
        capsuleDto.getUserDto().setUserPersonName("aaa");
        capsuleDto.getUserDto().getListRoles().add("ROLE_" + UserRoleConstants.MANAGER); // NOPMD

        String path = PathRouteConstants.ROOT + "/user-kanrensha/edit-person"; // NOPMD

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

    @Test
    @Tag("TableTruncate")
    void testMySelf() throws Exception {

        /* 関連者個人自分自身は編集可能 */

        String mail = "nnnn@politician.balanse.report.net";
        String password = "nnnn"; // NOPMD

        // ログイン処理(運営者)
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(mail, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final Integer editId = 390;
        KanrenshaPersonMasterEntity masterEntity = kanrenshaPersonMasterRepository.findById(editId).get();
        KanrenshaPersonDto dto = getKanrenshaPersonDtoService.practice(masterEntity);
        dto.getInputPersonNameDto().setAllName("sdlfkz");
        SaveKanrenshaPersonCapsuleDto capsuleDto = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto.setKanrenshaPersonDto(dto);

        capsuleDto.getUserDto().setUserPersonId(208);
        capsuleDto.getUserDto().setUserPersonCode(196);
        capsuleDto.getUserDto().setUserPersonName("nnnn");
        capsuleDto.getUserDto().getListRoles().add("ROLE_" + UserRoleConstants.KANRENSHA_PERSON);

        String path = PathRouteConstants.ROOT + "/user-kanrensha/edit-person";

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());

    }

    @Test
    @Tag("TableTruncate")
    void testWrongCode() throws Exception {

        /* 関連者個人コードが自分自身でないので編集できない */

        String mail = "nnnn@politician.balanse.report.net";
        String password = "nnnn";

        // ログイン処理(運営者)
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(mail, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final Integer editId = 391; // コードが異なる
        KanrenshaPersonMasterEntity masterEntity = kanrenshaPersonMasterRepository.findById(editId).get();
        KanrenshaPersonDto dto = getKanrenshaPersonDtoService.practice(masterEntity);
        dto.getInputPersonNameDto().setAllName("sdlfkz");
        SaveKanrenshaPersonCapsuleDto capsuleDto = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto.setKanrenshaPersonDto(dto);

        capsuleDto.getUserDto().setUserPersonId(208);
        capsuleDto.getUserDto().setUserPersonCode(196);
        capsuleDto.getUserDto().setUserPersonName("nnnn");
        capsuleDto.getUserDto().getListRoles().add("ROLE_" + UserRoleConstants.KANRENSHA_PERSON);

        String path = PathRouteConstants.ROOT + "/user-kanrensha/edit-person";

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        // status401
        assertEquals(HttpStatus.UNAUTHORIZED.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isUnauthorized()).andReturn().getResponse().getStatus());
    }

    @Test
    @Tag("TableTruncate")
    void testWrongRole() throws Exception {

        /* 権限が異なる場合は編集不能 */

        String mail = "llll@politician.balanse.report.net";
        String password = "llll";

        // ログイン処理(運営者)
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(mail, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final Integer editId = 390;
        KanrenshaPersonMasterEntity masterEntity = kanrenshaPersonMasterRepository.findById(editId).get();
        KanrenshaPersonDto dto = getKanrenshaPersonDtoService.practice(masterEntity);
        dto.getInputPersonNameDto().setAllName("sdlfkz");
        SaveKanrenshaPersonCapsuleDto capsuleDto = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto.setKanrenshaPersonDto(dto);

        capsuleDto.getUserDto().setUserPersonId(206);
        capsuleDto.getUserDto().setUserPersonCode(194);
        capsuleDto.getUserDto().setUserPersonName("llll");
        capsuleDto.getUserDto().getListRoles().add("ROLE_" + UserRoleConstants.KANRENSHA_KIGYOU_DT);

        String path = PathRouteConstants.ROOT + "/user-kanrensha/edit-person";

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        // status401
        assertEquals(HttpStatus.UNAUTHORIZED.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isUnauthorized()).andReturn().getResponse().getStatus());
    }

}
