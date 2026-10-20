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
 * YoteiFuncForceDumpHistorySabunService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("YoteiFuncForceDumpHistorySabunServiceTest.sql")
class YoteiFuncForceDumpHistorySabunServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private YoteiFuncForceDumpHistorySabunService yoteiFuncForceDumpHistorySabunService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        /* 処理実施 */
        ForceDumpCapsuleDto capsuleDto01 = new ForceDumpCapsuleDto();
        capsuleDto01.setDateStart(LocalDate.of(2024, 1, 1));
        capsuleDto01.setDateEnd(LocalDate.of(2024, 12, 31));
        capsuleDto01.setIsExecuteKigyouDt(true);
        capsuleDto01.setIsExecutePerson(true);
        capsuleDto01.setIsExecuteSeijidantai(true);

        assertTrue(yoteiFuncForceDumpHistorySabunService.practice(capsuleDto01));
    }

}
