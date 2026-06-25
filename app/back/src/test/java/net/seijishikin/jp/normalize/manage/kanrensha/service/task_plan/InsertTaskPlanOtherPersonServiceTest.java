package net.seijishikin.jp.normalize.manage.kanrensha.service.task_plan;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertTaskPlanOtherPersonService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("InsertTaskPlanOtherPersonServiceTest.sql")
class InsertTaskPlanOtherPersonServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private InsertTaskPlanOtherPersonService insertTaskPlanOtherPersonService;

    @Test
    @Tag("ExternalService")
    void test() {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        String email = "xcv@abcdefg.net";

        LocalDateTime createDatetime = LocalDateTime.of(2026, 2, 4, 11, 22, 33);
        LeastUserDto workUserDto = new LeastUserDto();
        workUserDto.setUserPersonCode(400);
        workUserDto.setUserPersonName("利用者　直子");
        
        Map<String, String> mapParam = new TreeMap<>();
        mapParam.put("personCode", String.valueOf(workUserDto.getUserPersonCode()));
        mapParam.put("orgCode", String.valueOf(518));


        // タスク挿入ができないテストはSwitchYearInsertTaskPlanInsertServiceで実施済、
        // 例外が発生時にその例外が伝播することだけを確認
        FrameworkMessageAndResultDto resultDto0 = insertTaskPlanOtherPersonService.practice(email, workUserDto, userDto,
                createDatetime, 2000, mapParam);
        assertTrue(resultDto0.getIsFailure());

        // TODO メールが送信できない場合のテストは別ファイル

        // 正常ケース
        FrameworkMessageAndResultDto resultDto1 = insertTaskPlanOtherPersonService.practice(email, workUserDto, userDto,
                createDatetime, TaskInfoConstants.ORG_COMBINE_PERSON_RIYOUSHA, mapParam);
        assertFalse(resultDto1.getIsFailure());

        // MEMO メールの内容を目視で確認
    }

}
