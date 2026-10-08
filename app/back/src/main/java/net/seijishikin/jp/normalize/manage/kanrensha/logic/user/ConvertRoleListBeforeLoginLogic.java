package net.seijishikin.jp.normalize.manage.kanrensha.logic.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;

/**
 * ログイン前名称権限リスト変換Logic
 */
@Component
public class ConvertRoleListBeforeLoginLogic {

    /** 権限ログイン時接頭語 */
    private static final String ROLE_WARDS = "ROLE_";

    /** ログイン後SE権限 */
    private static final String roleAdmin = ROLE_WARDS + UserRoleConstants.ADMIN;
    /** ログイン後運営者 */
    private static final String roleManager = ROLE_WARDS + UserRoleConstants.MANAGER;
    /** ログイン後APIパートナー */
    private static final String rolePartner = ROLE_WARDS + UserRoleConstants.PARTNER_API;

    /** ログイン後関連者個人 */
    private static final String rolePerson = ROLE_WARDS + UserRoleConstants.KANRENSHA_PERSON;
    /** ログイン後関連者企業・団体 */
    private static final String roleKigyouDt = ROLE_WARDS + UserRoleConstants.KANRENSHA_KIGYOU_DT;
    /** ログイン後関連者政治団体 */
    private static final String roleSeijiDantai = ROLE_WARDS + UserRoleConstants.KANRENSHA_SEIJIDANTAI;

    /**
     * 処理を行う
     * 
     * @param userDto ユーザ最小限
     * @return ログイン前の権限名リスト
     */
    public List<String> practice(final LeastUserDto userDto) {

        List<String> list = new ArrayList<>();

        for (String role : userDto.getListRoles()) {
            if (roleAdmin.equals(role)) {
                list.add(UserRoleConstants.ADMIN);
            }
            if (roleManager.equals(role)) {
                list.add(UserRoleConstants.MANAGER);
            }
            if (rolePartner.equals(role)) {
                list.add(UserRoleConstants.PARTNER_API);
            }
            if (rolePerson.equals(role)) {
                list.add(UserRoleConstants.KANRENSHA_PERSON);
            }
            if (roleKigyouDt.equals(role)) {
                list.add(UserRoleConstants.KANRENSHA_KIGYOU_DT);
            }
            if (roleSeijiDantai.equals(role)) {
                list.add(UserRoleConstants.KANRENSHA_SEIJIDANTAI);
            }
        }

        return list;
    }

}
