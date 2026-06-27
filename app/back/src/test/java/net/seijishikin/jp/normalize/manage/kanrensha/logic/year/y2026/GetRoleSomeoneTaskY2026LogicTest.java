package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * GetRoleSomeoneTaskY2026Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetRoleSomeoneTaskY2026Logic.sql")
class GetRoleSomeoneTaskY2026LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetRoleSomeoneTaskY2026Logic getRoleSomeoneTaskY2026Logic;

    /** テストタグ */
    private static final String TEST_TAG = "TableTruncate";

    @Test
    @Tag(TEST_TAG)
    void testAdmin() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        userDto.getListRoles().add("ROLE_admin");
        List<TaskPlanBaseEntity> listAns = getRoleSomeoneTaskY2026Logic.practice(userDto);

        assertEquals(1, listAns.size());
        assertEquals(461, listAns.get(0).getTaskPlanId());
    }

    @Test
    @Tag(TEST_TAG)
    void testManager() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        userDto.getListRoles().add("ROLE_manager");
        List<TaskPlanBaseEntity> listAns = getRoleSomeoneTaskY2026Logic.practice(userDto);

        assertEquals(1, listAns.size());
        assertEquals(462, listAns.get(0).getTaskPlanId());
    }

    @Test
    @Tag(TEST_TAG)
    void testPartner() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        userDto.getListRoles().add("ROLE_partner_api");
        List<TaskPlanBaseEntity> listAns = getRoleSomeoneTaskY2026Logic.practice(userDto);

        assertEquals(1, listAns.size());
        assertEquals(463, listAns.get(0).getTaskPlanId());
    }

    @Test
    @Tag(TEST_TAG)
    void testPerson() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        userDto.getListRoles().add("ROLE_kanrensha_person");
        List<TaskPlanBaseEntity> listAns = getRoleSomeoneTaskY2026Logic.practice(userDto);

        assertEquals(1, listAns.size());
        assertEquals(464, listAns.get(0).getTaskPlanId());
    }

    @Test
    @Tag(TEST_TAG)
    void testKigyouDt() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        userDto.getListRoles().add("ROLE_kanrensha_kigyou_dt");
        List<TaskPlanBaseEntity> listAns = getRoleSomeoneTaskY2026Logic.practice(userDto);

        assertEquals(1, listAns.size());
        assertEquals(465, listAns.get(0).getTaskPlanId());
    }

    @Test
    @Tag(TEST_TAG)
    void testSeijidantai() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        userDto.getListRoles().add("ROLE_kanrensha_seijidantai");
        List<TaskPlanBaseEntity> listAns = getRoleSomeoneTaskY2026Logic.practice(userDto);

        assertEquals(1, listAns.size());
        assertEquals(466, listAns.get(0).getTaskPlanId());
    }

    @Test
    @Tag(TEST_TAG)
    void testAll() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        userDto.getListRoles().add("ROLE_admin");
        userDto.getListRoles().add("ROLE_manager");
        userDto.getListRoles().add("ROLE_partner_api");
        userDto.getListRoles().add("ROLE_kanrensha_person");
        userDto.getListRoles().add("ROLE_kanrensha_kigyou_dt");
        userDto.getListRoles().add("ROLE_kanrensha_seijidantai");
        List<TaskPlanBaseEntity> listAns = getRoleSomeoneTaskY2026Logic.practice(userDto);

        assertEquals(5, listAns.size());
        assertEquals(461, listAns.get(0).getTaskPlanId());
        assertEquals(462, listAns.get(1).getTaskPlanId());
        assertEquals(463, listAns.get(2).getTaskPlanId());
        assertEquals(464, listAns.get(3).getTaskPlanId());
        assertEquals(465, listAns.get(4).getTaskPlanId());
        // assertEquals(466, listAns.get(5).getTaskPlanId()); 制限が5件まで
    }

    @Test
    @Tag(TEST_TAG)
    void testNothing() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        userDto.getListRoles().add("ROLE_aaa");
        List<TaskPlanBaseEntity> listAns = getRoleSomeoneTaskY2026Logic.practice(userDto);

        // 空リストが戻るだけ
        assertEquals(0, listAns.size());
    }

}
