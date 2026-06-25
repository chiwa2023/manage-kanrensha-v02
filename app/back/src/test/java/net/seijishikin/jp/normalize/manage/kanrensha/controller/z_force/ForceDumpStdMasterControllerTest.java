package net.seijishikin.jp.normalize.manage.kanrensha.controller.z_force; // NOPMD High Number Import

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Comparator;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
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
import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.MasterCsvFileNameConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.MasterCsvFileNameConstants.MasterStd;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * ForceDumpStdMasterController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
@Sql("ForceDumpStdMasterControllerTest.sql")
class ForceDumpStdMasterControllerTest {
    // CHECKSTYLE:OFF

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    /** propertiesからインジェクションされたフロントの共通ダンプCSV保存先 */
    private String frontDumpFolder;

    /**
     * フロントの共通ダンプCSV保存先を取得する
     *
     * @return フロントの共通ダンプCSV保存先
     */
    public String getFrontDumpFolder() {
        return frontDumpFolder;
    }

    /**
     * フロントの共通ダンプCSV保存先を設定する
     *
     * @param frontDumpFolder フロントの共通ダンプCSV保存先
     */
    public void setFrontDumpFolder(final String frontDumpFolder) {
        this.frontDumpFolder = frontDumpFolder;
    }

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void testCreate() throws Exception {

        final String pathSaved = Paths.get(GetCurrentResourcePath.getBackSrcPath("")).getParent().getParent()
                .toString();

        // 配下を全削除
        Path pathRoot = Paths.get(pathSaved, frontDumpFolder, MasterCsvFileNameConstants.FOLDER_MASTER);
        if (Files.exists(pathRoot)) {
            Files.walk(pathRoot).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(java.io.File::delete);
        }

        // ファイル否存在確認
        Path pathKigyouDt = Paths.get(pathRoot.toString(), MasterStd.MASTER_STD_KIGYOU);
        Path pathPerson = Paths.get(pathRoot.toString(), MasterStd.MASTER_STD_PERSON);
        Path pathSeijidantai = Paths.get(pathRoot.toString(), MasterStd.MASTER_STD_SEIJIDANTAI);
        assertFalse(Files.exists(pathKigyouDt));
        assertFalse(Files.exists(pathPerson));
        assertFalse(Files.exists(pathSeijidantai));

        ForceDumpCapsuleDto capsuleDto = new ForceDumpCapsuleDto();
        capsuleDto.setDateEnd(LocalDate.of(2023, 12, 31));
        capsuleDto.setIsExecuteKigyouDt(true);
        capsuleDto.setIsExecutePerson(true);
        capsuleDto.setIsExecuteSeijidantai(true);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = PathRouteConstants.ROOT + "/dump-master-std/execute";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());

        // このメソッドのみを単独で動かした場合のみファイルの存在を目視で確認(非同期動作なので、この段階で作業完了しているとは限らない)
    }

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void testNotExecute() throws Exception {

        final String pathSaved = Paths.get(GetCurrentResourcePath.getBackSrcPath("")).getParent().getParent()
                .toString();

        // 配下を全削除
        Path pathRoot = Paths.get(pathSaved, frontDumpFolder, MasterCsvFileNameConstants.FOLDER_MASTER);
        if (Files.exists(pathRoot)) {
            Files.walk(pathRoot).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(java.io.File::delete);
        }

        // ファイル否存在確認
        Path pathKigyouDt = Paths.get(pathRoot.toString(), MasterStd.MASTER_STD_KIGYOU);
        Path pathPerson = Paths.get(pathRoot.toString(), MasterStd.MASTER_STD_PERSON);
        Path pathSeijidantai = Paths.get(pathRoot.toString(), MasterStd.MASTER_STD_SEIJIDANTAI);
        assertFalse(Files.exists(pathKigyouDt));
        assertFalse(Files.exists(pathPerson));
        assertFalse(Files.exists(pathSeijidantai));

        ForceDumpCapsuleDto capsuleDto = new ForceDumpCapsuleDto();
        capsuleDto.setDateEnd(LocalDate.of(2023, 12, 31));
        capsuleDto.setIsExecuteKigyouDt(false);
        capsuleDto.setIsExecutePerson(false);
        capsuleDto.setIsExecuteSeijidantai(false);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = PathRouteConstants.ROOT + "/dump-master-std/execute";

        // 作業実施しない(204)
        assertEquals(HttpStatus.ACCEPTED.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isAccepted()).andReturn().getResponse().getStatus());
    }

}
