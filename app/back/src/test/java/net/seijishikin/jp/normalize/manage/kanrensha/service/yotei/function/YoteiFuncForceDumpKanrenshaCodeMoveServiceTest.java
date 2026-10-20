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
 * YoteiFuncForceDumpKanrenshaCodeMoveService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("YoteiFuncForceDumpKanrenshaCodeMoveServiceTest.sql")
class YoteiFuncForceDumpKanrenshaCodeMoveServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private YoteiFuncForceDumpKanrenshaCodeMoveService yoteiFuncForceDumpKanrenshaCodeMoveService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        ForceDumpCapsuleDto capsuleDto01 = new ForceDumpCapsuleDto();
        capsuleDto01.setDateEnd(LocalDate.of(2025, 1, 1));

        assertTrue(yoteiFuncForceDumpKanrenshaCodeMoveService.practice(capsuleDto01));
    }

}
