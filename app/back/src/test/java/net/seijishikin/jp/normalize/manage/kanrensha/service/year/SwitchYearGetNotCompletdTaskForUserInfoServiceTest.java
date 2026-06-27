package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SwitchYearGetNotCompletdTaskForUserInfoService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SwitchYearGetNotCompletdTaskForUserInfoServiceTest.sql")
class SwitchYearGetNotCompletdTaskForUserInfoServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearGetNotCompletdTaskForUserInfoService switchYearGetNotCompletdTaskForUserInfoService;

    @Test
    @Tag("TableTruncate")
    void test2026() {

        List<TaskPlanBaseEntity> list = switchYearGetNotCompletdTaskForUserInfoService.practice(2026,
                CreateLeastUserForTestUtil.practice());

        // Service内で5件指定にしているので4件取れる
        assertEquals(4, list.size());

        assertEquals(427, list.get(0).getTaskPlanId());
        assertEquals(426, list.get(1).getTaskPlanId());
        assertEquals(425, list.get(2).getTaskPlanId());
        assertEquals(424, list.get(3).getTaskPlanId());

        // 対象がなくても正常に空リストが返るだけ
        LeastUserDto leastUserDto = new LeastUserDto();
        leastUserDto.setUserPersonCode(1249);
        List<TaskPlanBaseEntity> list1 = switchYearGetNotCompletdTaskForUserInfoService.practice(2026, leastUserDto);
        assertTrue(list1.isEmpty());
    }

}
