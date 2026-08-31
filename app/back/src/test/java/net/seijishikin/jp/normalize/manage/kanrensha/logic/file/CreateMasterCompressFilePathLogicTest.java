package net.seijishikin.jp.normalize.manage.kanrensha.logic.file;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.MasterCsvFileNameConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.MasterCsvFileNameConstants.MasterStd;

/**
 * CreateMasterCompressFilePathLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
class CreateMasterCompressFilePathLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CreateMasterCompressFilePathLogic createMasterCompressFilePathLogic;

    @Test
    @Tag("TableTruncate")
    void test() {

        List<Path> list = createMasterCompressFilePathLogic.practiceFileList(MasterCsvFileNameConstants.FOLDER_MASTER,
                MasterStd.MASTER_STD_KIGYOU, MasterStd.MASTER_STD_PERSON, MasterStd.MASTER_STD_SEIJIDANTAI);

        assertEquals(3, list.size());

        Path path0 = list.get(0);
        assertEquals(
                "C:\\home\\app\\store_storage\\test\\kanrensha-v02\\front\\public\\dump\\dump_master\\master_kigyou_dt_std.csv",
                path0.toAbsolutePath().toString());
        Path path1 = list.get(1);
        assertEquals(
                "C:\\home\\app\\store_storage\\test\\kanrensha-v02\\front\\public\\dump\\dump_master\\master_person_std.csv",
                path1.toAbsolutePath().toString());
        Path path2 = list.get(2);
        assertEquals(
                "C:\\home\\app\\store_storage\\test\\kanrensha-v02\\front\\public\\dump\\dump_master\\master_seijidantai_std.csv",
                path2.toAbsolutePath().toString());
    }

}
