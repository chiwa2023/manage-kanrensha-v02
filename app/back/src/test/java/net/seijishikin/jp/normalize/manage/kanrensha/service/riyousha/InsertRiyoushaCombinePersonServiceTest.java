package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaCombineOrgRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertRiyoushaCombinePersonService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("InsertRiyoushaCombinePersonServiceTest.sql")
class InsertRiyoushaCombinePersonServiceTest {
    // CHECKSTYLE:OFF MagicNUmber

    /** テスト対象 */
    @Autowired
    private InsertRiyoushaCombinePersonService insertRiyoushaCombinePersonService;

    /** 利用者組織紐づけ個人Respoitory */
    @Autowired
    private RiyoushaCombineOrgRepository riyoushaCombineOrgRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 個人権限が取得できないので処理中断
        // RiyoushaCombineOrgEntity orgEntity0 = new RiyoushaCombineOrgEntity();
        // userDto.setRiyoushaRole(UserRoleConstants.KANRENSHA_KIGYOU_DT);
        // userDto.setRiyoushaCode(902);
        // assertThrows(EmptyResultDataAccessException.class,()->
        // insertRiyoushaCombinePersonService.practice(orgEntity0, userDto));

        // 団体情報に誤りがあるので処理中断
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        RiyoushaCombineOrgEntity orgEntity1 = new RiyoushaCombineOrgEntity();
        userDto.setRiyoushaRole(UserRoleConstants.PARTNER_API);
        userDto.setRiyoushaCode(902);
        orgEntity1.setOrgRiyoushaCode(9924);
        assertThrows(EmptyResultDataAccessException.class,
                () -> insertRiyoushaCombinePersonService.practice(orgEntity1, userDto));

        final int orgCode = 216;
        // APIパートナーに誤りがあるので処理中断
        RiyoushaCombineOrgEntity orgEntity2 = new RiyoushaCombineOrgEntity();
        userDto.setRiyoushaRole(UserRoleConstants.PARTNER_API);
        userDto.setRiyoushaCode(902);
        orgEntity2.setOrgRiyoushaCode(orgCode);
        orgEntity2.setPersonCode(1824);
        assertThrows(EmptyResultDataAccessException.class,
                () -> insertRiyoushaCombinePersonService.practice(orgEntity1, userDto));

        // 運営者に誤りがあるので処理中断
        RiyoushaCombineOrgEntity orgEntity3 = new RiyoushaCombineOrgEntity();
        userDto.setRiyoushaRole(UserRoleConstants.MANAGER);
        userDto.setRiyoushaCode(902);
        orgEntity3.setOrgRiyoushaCode(orgCode);
        orgEntity3.setPersonCode(1825);
        assertThrows(EmptyResultDataAccessException.class,
                () -> insertRiyoushaCombinePersonService.practice(orgEntity3, userDto));

        final String orgName = "利用者IT組織";
        // APIパートナーで保存
        RiyoushaCombineOrgEntity orgEntity4 = new RiyoushaCombineOrgEntity();
        orgEntity4.setRiyoushaRole(UserRoleConstants.PARTNER_API);
        orgEntity4.setPersonCode(140);
        orgEntity4.setPersonRiyoushaCode(317);
        orgEntity4.setPersonRiyoushaName("管理者 マリア花子");
        orgEntity4.setOrgName(orgName);
        orgEntity4.setOrgRiyoushaCode(orgCode);

        Integer newId4 = insertRiyoushaCombinePersonService.practice(orgEntity4, userDto);

        RiyoushaCombineOrgEntity ansEntity4 = riyoushaCombineOrgRepository.findById(newId4).get();
        assertEquals(orgEntity4.getOrgName(), ansEntity4.getOrgName());
        assertEquals(orgEntity4.getOrgRiyoushaCode(), ansEntity4.getOrgRiyoushaCode());
        assertEquals(orgEntity4.getPersonCode(), ansEntity4.getPersonCode());
        assertEquals(orgEntity4.getPersonRiyoushaCode(), ansEntity4.getPersonRiyoushaCode());
        assertEquals(orgEntity4.getPersonRiyoushaName(), ansEntity4.getPersonRiyoushaName());
        assertEquals(orgEntity4.getRiyoushaRole(), ansEntity4.getRiyoushaRole());

        // 運営者で保存
        RiyoushaCombineOrgEntity orgEntity5 = new RiyoushaCombineOrgEntity();

        orgEntity5.setRiyoushaRole(UserRoleConstants.MANAGER);
        orgEntity5.setPersonCode(160);
        orgEntity5.setPersonRiyoushaCode(275);
        orgEntity5.setPersonRiyoushaName("運営者 太郎");
        orgEntity5.setOrgName(orgName);
        orgEntity5.setOrgRiyoushaCode(orgCode);
        Integer newId5 = insertRiyoushaCombinePersonService.practice(orgEntity5, userDto);
        RiyoushaCombineOrgEntity ansEntity5 = riyoushaCombineOrgRepository.findById(newId5).get();
        assertEquals(orgEntity5.getOrgName(), ansEntity5.getOrgName());
        assertEquals(orgEntity5.getOrgRiyoushaCode(), ansEntity5.getOrgRiyoushaCode());
        assertEquals(orgEntity5.getPersonCode(), ansEntity5.getPersonCode());
        assertEquals(orgEntity5.getPersonRiyoushaCode(), ansEntity5.getPersonRiyoushaCode());
        assertEquals(orgEntity5.getPersonRiyoushaName(), ansEntity5.getPersonRiyoushaName());
        assertEquals(orgEntity5.getRiyoushaRole(), ansEntity5.getRiyoushaRole());
    }

}
