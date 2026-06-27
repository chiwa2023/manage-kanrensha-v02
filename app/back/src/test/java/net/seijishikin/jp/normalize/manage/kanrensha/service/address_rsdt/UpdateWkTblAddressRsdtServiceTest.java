package net.seijishikin.jp.normalize.manage.kanrensha.service.address_rsdt;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearInsertTaskPlanService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * UpdateWkTblAddressRsdtService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("UpdateWkTblAddressRsdtServiceTest.sql")
class UpdateWkTblAddressRsdtServiceTest {
    // CHECKSTYLE:OFF MagicNumbber

    /** テスト対象 */
    @Autowired
    private UpdateWkTblAddressRsdtService updateWkTblAddressRsdtService;

    /** 年切り替えタスク計画挿入Servce */
    @Autowired
    private SwitchYearInsertTaskPlanService switchYearInsertTaskPlanService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        LocalDateTime start = LocalDateTime.of(2026, 02, 11, 12, 34, 56);
        InsertTaskPlanResultDto taskResultDto = switchYearInsertTaskPlanService.practice(null, userDto, start,
                TaskInfoConstants.CHANGE_ADDRESS_BASE_CSV, null);

        assertDoesNotThrow(() -> updateWkTblAddressRsdtService.practice(userDto, taskResultDto));
    }

}
