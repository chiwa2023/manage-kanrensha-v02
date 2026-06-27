package net.seijishikin.jp.normalize.manage.kanrensha.service.lgcode;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.EditAddressCityDeleteCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * MoveAddressRsdtByLgcodeService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("MoveAddressRsdtByLgcodeServiceTest.sql")
class MoveAddressRsdtByLgcodeServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private MoveAddressRsdtByLgcodeService moveAddressRsdtByLgcodeService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        EditAddressCityDeleteCapsuleDto capsuleDto = new EditAddressCityDeleteCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.getEditEntity().setLgCode("827637");
        capsuleDto.setMoveLgCode("695123");

        InsertTaskPlanResultDto planDto = new InsertTaskPlanResultDto();
        planDto.setTaskPlanId(453);
        planDto.setTaskPlanCode(187);

        assertDoesNotThrow(() -> moveAddressRsdtByLgcodeService.practice(2026, planDto, capsuleDto));
    }

}
