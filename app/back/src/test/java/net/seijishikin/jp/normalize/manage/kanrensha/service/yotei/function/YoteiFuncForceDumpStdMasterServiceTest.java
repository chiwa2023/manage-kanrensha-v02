package net.seijishikin.jp.normalize.manage.kanrensha.service.yotei.function;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;

/**
 * YoteiFuncForceDumpStdMasterService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("YoteiFuncForceDumpStdMasterServiceTest.sql")
class YoteiFuncForceDumpStdMasterServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private YoteiFuncForceDumpStdMasterService yoteiFuncForceDumpStdMasterService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        ForceDumpCapsuleDto capsuleDto = new ForceDumpCapsuleDto();
        capsuleDto.setDateEnd(LocalDate.of(2023, 12, 31));
        capsuleDto.setIsExecuteKigyouDt(true);
        capsuleDto.setIsExecutePerson(true);
        capsuleDto.setIsExecuteSeijidantai(true);

        assertTrue(yoteiFuncForceDumpStdMasterService.practice(capsuleDto));
    }

}
