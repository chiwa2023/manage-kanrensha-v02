package net.seijishikin.jp.normalize.manage.kanrensha.logic.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;

/**
 * GetRiyoushaRoleLogic単体テスト
 */

class GetRiyoushaRoleLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** 権限ログイン時接頭語 */
    private static final String ROLE_WARDS = "ROLE_";

    @Test
    @Tag("TableTruncate")
    void test() {

        GetRiyoushaRoleLogic logic = new GetRiyoushaRoleLogic();

        LeastUserDto userDto0 = new LeastUserDto();
        userDto0.setRiyoushaCode(124);
        userDto0.setRiyoushaRole(UserRoleConstants.KANRENSHA_KIGYOU_DT);
        // 利用者番号が存在する場合は登録済の権限(実際には関連者出てこないが)
        assertEquals(UserRoleConstants.KANRENSHA_KIGYOU_DT, logic.practice(userDto0));

        LeastUserDto userDto1 = new LeastUserDto();
        userDto1.getListRoles().add(UserRoleConstants.MANAGER);
        assertEquals(UserRoleConstants.MANAGER, logic.practice(userDto1));

        LeastUserDto userDto2 = new LeastUserDto();
        userDto2.getListRoles().add(UserRoleConstants.ADMIN);
        assertEquals(UserRoleConstants.MANAGER, logic.practice(userDto2));

        LeastUserDto userDto3 = new LeastUserDto();
        userDto3.getListRoles().add(UserRoleConstants.PARTNER_API);
        assertEquals(UserRoleConstants.PARTNER_API, logic.practice(userDto3));

        // ログイン処理時には"ROLE_"が権限に勝手に付与するが、仮にその値がそのまま帰ってきた場合の対応
        LeastUserDto userDto4 = new LeastUserDto();
        userDto4.getListRoles().add(ROLE_WARDS + UserRoleConstants.MANAGER);
        assertEquals(UserRoleConstants.MANAGER, logic.practice(userDto4));

        LeastUserDto userDto5 = new LeastUserDto();
        userDto5.getListRoles().add(ROLE_WARDS + UserRoleConstants.ADMIN);
        assertEquals(UserRoleConstants.MANAGER, logic.practice(userDto5));

        LeastUserDto userDto6 = new LeastUserDto();
        userDto6.getListRoles().add(ROLE_WARDS + UserRoleConstants.PARTNER_API);
        assertEquals(UserRoleConstants.PARTNER_API, logic.practice(userDto6));

        // 利用者がない場合は空文字
        LeastUserDto userDto7 = new LeastUserDto();
        userDto7.getListRoles().add(UserRoleConstants.KANRENSHA_PERSON);
        assertEquals("", logic.practice(userDto7));
    }

}
