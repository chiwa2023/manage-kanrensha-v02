package net.seijishikin.jp.normalize.manage.kanrensha.logic.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertCombineUserRiyoushaLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("InsertCombineUserKanrenshaLogicTest.sql")
class InsertCombineUserRiyoushaLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private InsertCombineUserRiyoushaLogic insertCombineUserRiyoushaLogic;

    /** ユーザ権限Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Test
    @Tag("TableTruncate")
    void testChangeMaster() throws Exception {

        final String role = "manager";
        final Integer code = 739;

        // ユーザが未登録
        LeastUserDto userDto1 = CreateLeastUserForTestUtil.practice();
        userDto1.setUserPersonId(133);
        assertThrows(EmptyResultDataAccessException.class,
                () -> insertCombineUserRiyoushaLogic.practcie(role, code, userDto1));

        // roleが未登録(常に履歴が存在済)
        LeastUserDto userDto2 = CreateLeastUserForTestUtil.practice();
        userDto2.setUserPersonId(81);
        assertThrows(EmptyResultDataAccessException.class,
                () -> insertCombineUserRiyoushaLogic.practcie("partner", code, userDto2));

        // 最新roleが複数あって不整合
        LeastUserDto userDto3 = CreateLeastUserForTestUtil.practice();
        userDto3.setUserPersonId(82);
        assertThrows(DuplicateKeyException.class, () -> insertCombineUserRiyoushaLogic.practcie(role, code, userDto3));

        // 最新データが関連者の登録があって不整合
        LeastUserDto userDto4 = CreateLeastUserForTestUtil.practice();
        userDto4.setUserPersonId(80);
        assertThrows(IllegalStateException.class, () -> insertCombineUserRiyoushaLogic.practcie(role, code, userDto4));

        // 最新データが利用者の登録があって不整合
        LeastUserDto userDto5 = CreateLeastUserForTestUtil.practice();
        userDto5.setUserPersonId(81);
        assertThrows(IllegalStateException.class, () -> insertCombineUserRiyoushaLogic.practcie(role, code, userDto5));

        LeastUserDto userDto6 = CreateLeastUserForTestUtil.practice();
        Integer newId = insertCombineUserRiyoushaLogic.practcie(role, code, userDto6);

        UserRoleEntity entityNew = userRoleRepository.findById(newId).get();

        assertEquals("ccc@politician.balanse.report.net", entityNew.getEmail());
        assertEquals(code, entityNew.getRiyoushaCode());
        assertEquals("", entityNew.getKanrenshaCode());
    }

}
