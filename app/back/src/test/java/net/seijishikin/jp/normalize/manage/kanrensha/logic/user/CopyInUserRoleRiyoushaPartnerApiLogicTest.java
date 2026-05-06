package net.seijishikin.jp.normalize.manage.kanrensha.logic.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaPartnerApiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaPartnerApiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * CopyInUserRoleRiyoushaPartnerApiLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
// @Transactional
@Sql("ChangeUserRoleLogicTest.sql")
class CopyInUserRoleRiyoushaPartnerApiLogicTest {

    /** テスト対象 */
    @Autowired
    private CopyInUserRoleRiyoushaPartnerApiLogic copyInUserRoleRiyoushaPartnerApiLogic;

    /** 利用者APIパートナーマスタRepository */
    @Autowired
    private RiyoushaPartnerApiMasterRepository riyoushaPartnerApiMasterRepository;

    /** 登録済コード運営者 */
    private static final int managerCode = 12;
    /** 登録済コード個人 */
    private static final String personCode = "49261-2mMW-rB-Pl4zrX";

    @Test
    @Tag("TableTruncate")
    void testManager() throws Exception {

        List<String> listKanrensha = new ArrayList<>();
        List<String> listRiyousha = new ArrayList<>();
        listRiyousha.add(UserRoleConstants.MANAGER);
        Map<String, UserRoleEntity> map = new TreeMap<>();
        map.put(UserRoleConstants.MANAGER, this.createManager(true));

        // 返却値はコードなので使いにくい
        copyInUserRoleRiyoushaPartnerApiLogic.practice(listKanrensha, listRiyousha, map,
                CreateLeastUserForTestUtil.practice());

        RiyoushaPartnerApiMasterEntity masterEntity = riyoushaPartnerApiMasterRepository.findAll().getLast();
        assertEquals("管理者 マリア花子", masterEntity.getAllName());
        // 本来は以下検証が続く
    }

    @Test
    @Tag("TableTruncate")
    void testPerson() throws Exception {

        List<String> listKanrensha = new ArrayList<>();
        listKanrensha.add(UserRoleConstants.KANRENSHA_PERSON);
        List<String> listRiyousha = new ArrayList<>();
        Map<String, UserRoleEntity> map = new TreeMap<>();
        map.put(UserRoleConstants.KANRENSHA_PERSON, this.createPerson(true));

        // 返却値はコードなので使いにくい
        copyInUserRoleRiyoushaPartnerApiLogic.practice(listKanrensha, listRiyousha, map,
                CreateLeastUserForTestUtil.practice());

        RiyoushaPartnerApiMasterEntity masterEntity = riyoushaPartnerApiMasterRepository.findAll().getLast();
        assertEquals("迂回献金　ミカエル太郎", masterEntity.getAllName());
        // 本来は以下検証が続く
    }

    private UserRoleEntity createManager(final boolean hasDetail) {

        UserRoleEntity roleEntity = new UserRoleEntity();
        roleEntity.setRole(UserRoleConstants.MANAGER);

        if (hasDetail) {
            roleEntity.setRiyoushaCode(managerCode);
        }

        return roleEntity;
    }

    private UserRoleEntity createPerson(final boolean hasDetail) {

        UserRoleEntity roleEntity = new UserRoleEntity();
        roleEntity.setRole(UserRoleConstants.KANRENSHA_PERSON);

        if (hasDetail) {
            roleEntity.setKanrenshaCode(personCode);
        }

        return roleEntity;
    }
}
