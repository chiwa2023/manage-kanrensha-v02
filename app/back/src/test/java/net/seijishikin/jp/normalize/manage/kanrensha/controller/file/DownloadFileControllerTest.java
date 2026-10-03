package net.seijishikin.jp.normalize.manage.kanrensha.controller.file;

import static org.junit.jupiter.api.Assertions.assertEquals; // NOPMD HighImports
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

import net.seijishikin.jp.normalize.common_tool.utils.GetObjectMapperWithTimeModuleUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.DownloadFileCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.UploadContentCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.UploadFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.SaveFileStorage2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.task_plan.CreateQueryParamDummyUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.SaveFileStorage2026Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.file.FileUploadServcie;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * DownloadFileController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("DownloadFileControllerTest.sql")
class DownloadFileControllerTest {
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

    /** ファイルアップロードService */
    @Autowired
    private FileUploadServcie fileUploadServcie;

    /** 保存ファイルRepository(2026) */
    @Autowired
    private SaveFileStorage2026Repository saveFileStorage2026Repository;

    /** 認証プロバイダ */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void test() throws Exception {

        String fileName = "mt_city_all.csv";

        UploadContentCapsuleDto capsuleDtoUpload = new UploadContentCapsuleDto();
        capsuleDtoUpload.setUserDto(CreateLeastUserForTestUtil.practice());
        UploadFileDto uploadFileDto = new UploadFileDto();
        uploadFileDto.setFileName(fileName);
        // ファイルをバイナリで呼び出し
        Path pathFile = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file", fileName);
        byte[] bytes = Files.readAllBytes(pathFile);
        uploadFileDto.setFileContent(Base64.getEncoder().encodeToString(bytes));
        capsuleDtoUpload.setUploadFileDto(uploadFileDto);

        LocalDateTime dateTimeStart = LocalDateTime.of(2026, 7, 26, 12, 22, 56);

        Path pathSaved = fileUploadServcie.practice(dateTimeStart, capsuleDtoUpload,
                CreateQueryParamDummyUtil.practice());

        // コピー成功
        assertTrue(Files.exists(pathSaved));

        List<SaveFileStorage2026Entity> listSave = saveFileStorage2026Repository.findAll();
        assertEquals(1, listSave.size()); // 空に対して1件追加
        final Integer storageId = listSave.get(0).getSaveFileStorageId();
        assertEquals(111, storageId); // テスト前にストレージ情報をクリアしたときに auto incrementを110まで使用している設定

        DownloadFileCapsuleDto capsuleDto = new DownloadFileCapsuleDto();
        capsuleDto.setStorageYear(2026);
        capsuleDto.setStorageId(storageId);

        /* 運営者でログインするときはどの関連者であっても参照可能 */
        String mail = "aaa@politician.balanse.report.net";
        String password = "qwerty1234";

        // ログイン処理(運営者)
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(mail, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        capsuleDto.getUserDto().setUserPersonId(81);
        capsuleDto.getUserDto().setUserPersonCode(80);
        capsuleDto.getUserDto().setUserPersonName("aaa");
        capsuleDto.getUserDto().getListRoles().add("ROLE_" + UserRoleConstants.MANAGER);
        capsuleDto.setKanrenshaKbn(KanrenshaKbnConstants.KIGYOU_DT);

        String path = PathRouteConstants.ROOT + "/file/download";

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
