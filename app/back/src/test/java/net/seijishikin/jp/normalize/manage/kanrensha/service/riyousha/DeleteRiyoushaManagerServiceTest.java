package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaCombineOrgRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaManagerMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaPersonPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * DeleteRiyoushaManagerService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("DeleteRiyoushaManagerServiceTest.sql")
class DeleteRiyoushaManagerServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private DeleteRiyoushaManagerService deleteRiyoushaManagerService;

    /** 利用者運営者マスタRepository */
    @Autowired
    private RiyoushaManagerMasterRepository riyoushaManagerMasterRepository;

    /** 利用者個人属性Repository */
    @Autowired
    private RiyoushaPersonPropertyRepository riyoushaPersonPropertyRepository;

    /** 利用者組織紐づけ個人Respoitory */
    @Autowired
    private RiyoushaCombineOrgRepository riyoushaCombineOrgRepository;

    /** ユーザ権限Respoitory */
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        final Integer riyoushaCode0 = 124;
        Integer result0 = deleteRiyoushaManagerService.practice(riyoushaCode0, userDto);
        assertEquals(0, result0); // 実装ミスでのコード指定の誤りあるいは削除済

        final Integer riyoushaCode1 = 661;
        Integer result1 = deleteRiyoushaManagerService.practice(riyoushaCode1, userDto);
        assertEquals(7, result1); // 実際にそんなデータはないが、紐づき2,属性2,マスタ2,ユーザ1履歴化

        assertFalse(riyoushaCombineOrgRepository.findById(754).get().getIsLatest());
        assertFalse(riyoushaCombineOrgRepository.findById(755).get().getIsLatest());
        assertFalse(riyoushaManagerMasterRepository.findById(651).get().getIsLatest());
        assertFalse(riyoushaManagerMasterRepository.findById(652).get().getIsLatest());
        assertFalse(riyoushaPersonPropertyRepository.findById(743).get().getIsLatest());
        assertFalse(riyoushaPersonPropertyRepository.findById(744).get().getIsLatest());
        assertFalse(userRoleRepository.findById(82).get().getIsLatest());

        // 組織紐づけなしの標準的な利用者運営者の削除
        final Integer riyoushaCode2 = 843;
        Integer result2 = deleteRiyoushaManagerService.practice(riyoushaCode2, userDto);
        assertEquals(4, result2); // 組織との紐づきなし。マスタ1,属性1,マスタ2,ユーザ2利用だけをはず
        assertFalse(riyoushaManagerMasterRepository.findById(649).get().getIsLatest());
        assertFalse(riyoushaPersonPropertyRepository.findById(741).get().getIsLatest());
        assertFalse(userRoleRepository.findById(80).get().getIsLatest());
        UserRoleEntity roleEntity2 = userRoleRepository.findById(191).get();
        assertTrue(roleEntity2.getIsLatest());
        assertEquals(0, roleEntity2.getRiyoushaCode()); // マスタとの紐づきを外す

    }

}
