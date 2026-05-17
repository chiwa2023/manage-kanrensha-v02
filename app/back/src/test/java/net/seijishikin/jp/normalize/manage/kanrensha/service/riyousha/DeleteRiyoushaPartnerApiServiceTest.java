package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaCombineOrgRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaPartnerApiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaPersonPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * DeleteRiyoushaPartnerApiService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("DeleteRiyoushaPartnerApiServiceTest.sql")
class DeleteRiyoushaPartnerApiServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private DeleteRiyoushaPartnerApiService deleteRiyoushaPartnerApiService;

    /** 利用者APIパートナーマスタRepository */
    @Autowired
    private RiyoushaPartnerApiMasterRepository riyoushaPartnerApiMasterRepository;

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
        Integer result0 = deleteRiyoushaPartnerApiService.practice(riyoushaCode0, userDto);
        assertEquals(0, result0); // 実装ミスでのコード指定の誤りあるいは削除済

        final Integer riyoushaCode1 = 255;
        Integer result1 = deleteRiyoushaPartnerApiService.practice(riyoushaCode1, userDto);
        assertEquals(7, result1); // 実際にそんなデータはないが、紐づき2,属性2,マスタ2,ユーザ1更新

        assertFalse(riyoushaCombineOrgRepository.findById(755).get().getIsLatest());
        assertFalse(riyoushaCombineOrgRepository.findById(756).get().getIsLatest());
        assertFalse(riyoushaPartnerApiMasterRepository.findById(295).get().getIsLatest());
        assertFalse(riyoushaPartnerApiMasterRepository.findById(296).get().getIsLatest());
        assertFalse(riyoushaPersonPropertyRepository.findById(741).get().getIsLatest());
        assertFalse(riyoushaPersonPropertyRepository.findById(742).get().getIsLatest());
        assertFalse(userRoleRepository.findById(84).get().getIsLatest());

        // 組織紐づけなしの標準的な利用者運営者の削除
        final Integer riyoushaCode2 = 913;
        Integer result2 = deleteRiyoushaPartnerApiService.practice(riyoushaCode2, userDto);
        assertEquals(4, result2); // 組織との紐づきなし。マスタ1,属性1,マスタ2,ユーザ2利用だけをはず
        assertFalse(riyoushaPartnerApiMasterRepository.findById(293).get().getIsLatest());
        assertFalse(riyoushaPersonPropertyRepository.findById(744).get().getIsLatest());
        assertFalse(userRoleRepository.findById(80).get().getIsLatest());
        UserRoleEntity roleEntity2 = userRoleRepository.findById(191).get();
        assertTrue(roleEntity2.getIsLatest());
        assertEquals(0, roleEntity2.getRiyoushaCode()); // マスタとの紐づきを外す
    }

}
