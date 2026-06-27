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

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaManagerMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaManagerMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * CopyInUserRoleRiyoushaManagerLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
// @Transactional
@Sql("ChangeUserRoleLogicTest.sql")
class CopyInUserRoleRiyoushaManagerLogicTest {

    /** テスト対象 */
    @Autowired
    private CopyInUserRoleRiyoushaManagerLogic copyInUserRoleRiyoushaManagerLogic;

    /** 利用者運営者マスタRepository */
    @Autowired
    private RiyoushaManagerMasterRepository riyoushaManagerMasterRepository;

    /** 登録済コードAPIパートナー */
    private static final int partnerCode = 22;
    /** 登録済コード個人 */
    private static final String personCode = "49261-2mMW-rB-Pl4zrX";

    @Test
    @Tag("TableTruncate")
    void testPartner() throws Exception {

        List<String> listKanrensha = new ArrayList<>();
        List<String> listRiyousha = new ArrayList<>();
        listRiyousha.add(UserRoleConstants.PARTNER_API);
        Map<String, UserRoleEntity> map = new TreeMap<>();
        map.put(UserRoleConstants.PARTNER_API, this.createPartnerApi(true));

        // 返却値はコードなので使いにくい
        copyInUserRoleRiyoushaManagerLogic.practice(listKanrensha, listRiyousha, map,
                CreateLeastUserForTestUtil.practice());

        RiyoushaManagerMasterEntity masterEntity = riyoushaManagerMasterRepository.findAll().getLast();
        assertEquals("パートナー パウロ聡", masterEntity.getAllName());
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
        copyInUserRoleRiyoushaManagerLogic.practice(listKanrensha, listRiyousha, map,
                CreateLeastUserForTestUtil.practice());

        RiyoushaManagerMasterEntity masterEntity = riyoushaManagerMasterRepository.findAll().getLast();
        assertEquals("迂回献金　ミカエル太郎", masterEntity.getAllName());
        // 本来は以下検証が続く
    }

    private UserRoleEntity createPartnerApi(final boolean hasDetail) {

        UserRoleEntity roleEntity = new UserRoleEntity();
        roleEntity.setRole(UserRoleConstants.PARTNER_API);

        if (hasDetail) {
            roleEntity.setRiyoushaCode(partnerCode);
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
