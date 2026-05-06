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

import jakarta.transaction.Transactional;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * CopyInUserRoleKanrenshaPersonLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("ChangeUserRoleLogicTest.sql")
class CopyInUserRoleKanrenshaPersonLogicTest {

    /** テスト対象 */
    @Autowired
    private CopyInUserRoleKanrenshaPersonLogic copyInUserRoleKanrenshaPersonLogic;

    /** 関連者個人マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /** 登録済コード運営者 */
    private static final int managerCode = 12;
    /** 登録済コードAPIパートナー */
    private static final int partnerCode = 22;

    @Test
    @Tag("TableTruncate")
    void testManager() throws Exception {

        List<String> listRiyousha = new ArrayList<>();
        listRiyousha.add(UserRoleConstants.MANAGER);
        Map<String, UserRoleEntity> map = new TreeMap<>();
        map.put(UserRoleConstants.MANAGER, this.createManager(true));

        // 返却値はコードなので使いにくい
        copyInUserRoleKanrenshaPersonLogic.practice(listRiyousha, map, CreateLeastUserForTestUtil.practice());

        KanrenshaPersonMasterEntity masterEntity = kanrenshaPersonMasterRepository.findAll().getLast();
        assertEquals("管理者 マリア花子", masterEntity.getKanrenshaName());
        // 本来は以下検証が続く
    }

    @Test
    @Tag("TableTruncate")
    void testPartner() throws Exception {

        List<String> listRiyousha = new ArrayList<>();
        listRiyousha.add(UserRoleConstants.PARTNER_API);
        Map<String, UserRoleEntity> map = new TreeMap<>();
        map.put(UserRoleConstants.PARTNER_API, this.createPartnerApi(true));

        // 返却値はコードなので使いにくい
        copyInUserRoleKanrenshaPersonLogic.practice(listRiyousha, map, CreateLeastUserForTestUtil.practice());

        KanrenshaPersonMasterEntity masterEntity = kanrenshaPersonMasterRepository.findAll().getLast();
        assertEquals("パートナー パウロ聡", masterEntity.getKanrenshaName());
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

    private UserRoleEntity createPartnerApi(final boolean hasDetail) {

        UserRoleEntity roleEntity = new UserRoleEntity();
        roleEntity.setRole(UserRoleConstants.PARTNER_API);

        if (hasDetail) {
            roleEntity.setRiyoushaCode(partnerCode);
        }

        return roleEntity;
    }

}
