package net.seijishikin.jp.normalize.manage.kanrensha.controller.z_force;

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * ForceDumpHistorySabunController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
@Sql("ForceDumpHistorySabunControllerTest.sql")
class ForceDumpHistorySabunControllerTest {
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
    void test() throws Exception {

        final String pathSaved = Paths.get(GetCurrentResourcePath.getBackSrcPath("")).getParent().getParent()
                .toString();

        // 配下を全削除
        Path pathRootKigyouDt = Paths.get(pathSaved, frontDumpFolder, "/dump_history_sabun_corp");
        if (Files.exists(pathRootKigyouDt)) {
            Files.walk(pathRootKigyouDt).sorted(Comparator.reverseOrder()).map(Path::toFile)
                    .forEach(java.io.File::delete);
        }
        Path pathRootPerson = Paths.get(pathSaved, frontDumpFolder, "/dump_history_sabun_person");
        if (Files.exists(pathRootPerson)) {
            Files.walk(pathRootPerson).sorted(Comparator.reverseOrder()).map(Path::toFile)
                    .forEach(java.io.File::delete);
        }
        Path pathRootSeijidantai = Paths.get(pathSaved, frontDumpFolder, "/dump_history_sabun_poli_org");
        if (Files.exists(pathRootSeijidantai)) {
            Files.walk(pathRootSeijidantai).sorted(Comparator.reverseOrder()).map(Path::toFile)
                    .forEach(java.io.File::delete);
        }

        Path pathRootKigyouDtZip = Paths.get(pathSaved, frontDumpFolder, "dump_history_sabun_corp.zip");
        Files.deleteIfExists(pathRootKigyouDtZip);
        Path pathRootPersonZip = Paths.get(pathSaved, frontDumpFolder, "dump_history_sabun_person.zip");
        Files.deleteIfExists(pathRootPersonZip);
        Path pathRootSeijidantaiZip = Paths.get(pathSaved, frontDumpFolder, "dump_history_sabun_poli_org.zip");
        Files.deleteIfExists(pathRootSeijidantaiZip);

        // ファイル否存在確認
        assertFalse(Files.exists(pathRootKigyouDt));
        assertFalse(Files.exists(pathRootPerson));
        assertFalse(Files.exists(pathRootSeijidantai));
        assertFalse(Files.exists(pathRootKigyouDtZip));
        assertFalse(Files.exists(pathRootPersonZip));
        assertFalse(Files.exists(pathRootSeijidantaiZip));

        ForceDumpCapsuleDto capsuleDto00 = new ForceDumpCapsuleDto();
        capsuleDto00.setDateStart(LocalDate.of(2024, 1, 1));
        capsuleDto00.setDateEnd(LocalDate.of(2024, 12, 31));
        capsuleDto00.setIsExecuteKigyouDt(false);
        capsuleDto00.setIsExecutePerson(false);
        capsuleDto00.setIsExecuteSeijidantai(false);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = PathRouteConstants.ROOT + "/dump-history-sabun/execute";

        // サーバステータスがOK(202)
        assertEquals(HttpStatus.ACCEPTED.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto00)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isAccepted()).andReturn().getResponse().getStatus());

        /* 処理実施 */
        ForceDumpCapsuleDto capsuleDto01 = new ForceDumpCapsuleDto();
        capsuleDto01.setDateStart(LocalDate.of(2024, 1, 1));
        capsuleDto01.setDateEnd(LocalDate.of(2024, 12, 31));
        capsuleDto01.setIsExecuteKigyouDt(true);
        capsuleDto01.setIsExecutePerson(true);
        capsuleDto01.setIsExecuteSeijidantai(true);

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto01)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());

        // ファイルの存在を目視で確認(この段階では作業は終了していない)
    }

}
