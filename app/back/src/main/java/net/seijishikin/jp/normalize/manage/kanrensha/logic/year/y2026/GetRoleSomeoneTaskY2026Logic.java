package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.TaskPlan2026Repository;

/**
 * 役割指定タスク計画を取得する(2026)
 */
@Component
public class GetRoleSomeoneTaskY2026Logic {

    /** タスク計画Repository */
    @Autowired
    private TaskPlan2026Repository taskPlan2026Repository;

    /** 権限ログイン時接頭語 */
    private static final String ROLE_WARDS = "ROLE_";

    /**
     * 処理を行う
     * 
     * @param userDto ユーザ最小限Dto
     * @return 検索結果
     */
    public List<TaskPlanBaseEntity> practice(final LeastUserDto userDto) {

        final int limit = 5;
        Pageable pageable = Pageable.ofSize(limit).withPage(0);
        return taskPlan2026Repository.findRoleSomeoneTask(this.getRoleList(userDto), pageable);
    }

    private List<String> getRoleList(final LeastUserDto userDto) {

        List<String> list = new ArrayList<>();

        final String roleAdmin = ROLE_WARDS + UserRoleConstants.ADMIN;
        final String roleManager = ROLE_WARDS + UserRoleConstants.MANAGER;
        final String rolePartner = ROLE_WARDS + UserRoleConstants.PARTNER_API;

        final String rolePerson = ROLE_WARDS + UserRoleConstants.KANRENSHA_PERSON;
        final String roleKigyouDt = ROLE_WARDS + UserRoleConstants.KANRENSHA_KIGYOU_DT;
        final String roleSeijiDantai = ROLE_WARDS + UserRoleConstants.KANRENSHA_SEIJIDANTAI;

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
