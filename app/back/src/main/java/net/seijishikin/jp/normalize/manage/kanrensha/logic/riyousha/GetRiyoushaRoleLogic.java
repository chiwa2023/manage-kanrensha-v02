package net.seijishikin.jp.normalize.manage.kanrensha.logic.riyousha;

import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;

/**
 * 利用者の権限を取得する
 */
@Component
public class GetRiyoushaRoleLogic {

    /** 空白文字 */
    private static final String BLANK = "";

    /** 権限ログイン時接頭語 */
    private static final String ROLE_WARDS = "ROLE_";

    /**
     * 処理を行う
     * 
     * @param userDto ユーザ最小限Dto
     * @return 利用者権限
     */
    public String practice(final LeastUserDto userDto) {
        final Integer ZERO = 0;
        if (ZERO.equals(userDto.getRiyoushaCode())) {
            if (userDto.getListRoles().contains(ROLE_WARDS + UserRoleConstants.MANAGER)
                    || userDto.getListRoles().contains(UserRoleConstants.MANAGER)) {
                return UserRoleConstants.MANAGER;
            }
            // 基本的にadminに詳細情報を登録することはない
            if (userDto.getListRoles().contains(ROLE_WARDS + UserRoleConstants.ADMIN)
                    || userDto.getListRoles().contains(UserRoleConstants.ADMIN)) {
                return UserRoleConstants.MANAGER;
            }
            if (userDto.getListRoles().contains(ROLE_WARDS + UserRoleConstants.PARTNER_API)
                    || userDto.getListRoles().contains(UserRoleConstants.PARTNER_API)) {
                return UserRoleConstants.PARTNER_API;
            }
        } else {
            return userDto.getRiyoushaRole().replace(ROLE_WARDS, BLANK);
        }

        return BLANK;
    }

}
