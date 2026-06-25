package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionIntegerDto;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * GetRiyoushaOrgCombinOptionsService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetRiyoushaOrgCombinOptionsServiceTest.sql")
class GetRiyoushaOrgCombinOptionsServiceTest {
    // CHECKSTYLE:OFF MagicNUmber

    /** テスト対象 */
    @Autowired
    private GetRiyoushaOrgCombinOptionsService getRiyoushaOrgCombinOptionsService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        List<SelectOptionIntegerDto> list0 = getRiyoushaOrgCombinOptionsService.practice(new FrameworkCapsuleDto());
        assertEquals(1, list0.size());

        SelectOptionIntegerDto dto00 = list0.get(0);
        assertEquals(0, dto00.getValue());
        assertEquals("新規", dto00.getText());

        FrameworkCapsuleDto capsuleDto = new FrameworkCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        List<SelectOptionIntegerDto> list1 = getRiyoushaOrgCombinOptionsService.practice(capsuleDto);
        assertEquals(4, list1.size());

        SelectOptionIntegerDto dto10 = list1.get(0);
        assertEquals(0, dto10.getValue());
        assertEquals("新規", dto10.getText());

        SelectOptionIntegerDto dto11 = list1.get(1);
        assertEquals(483, dto11.getValue());
        assertEquals("組織A", dto11.getText());

        SelectOptionIntegerDto dto12 = list1.get(2);
        assertEquals(484, dto12.getValue());
        assertEquals("組織B", dto12.getText());

        SelectOptionIntegerDto dto13 = list1.get(3);
        assertEquals(485, dto13.getValue());
        assertEquals("組織C", dto13.getText());
    }

}
