package net.seijishikin.jp.normalize.manage.kanrensha.logic.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;

/**
 * ユーザ権限変更Logic
 */
@Component
public class ChangeUserRoleLogic {

    /** ユーザ権限変更時運営者へ複写Logic */
    @Autowired
    private CopyInUserRoleRiyoushaManagerLogic copyInUserRoleRiyoushaManagerLogic;

    /** ユーザ権限変更時運営者へ複写Logic */
    @Autowired
    private CopyInUserRoleRiyoushaPartnerApiLogic copyInUserRoleRiyoushaPartnerApiLogic;

    /** ユーザ権限変更時運営者へ複写Logic */
    @Autowired
    private CopyInUserRoleKanrenshaPersonLogic copyInUserRoleKanrenshaPersonLogic;

    /**
     * 処理を行う
     * 
     * @param listOld 現在権限Entityリスト
     * @param listNew 新規権限名リスト
     * @return 更新すべき新権限リスト
     */
    public List<UserRoleEntity> practice(final List<UserRoleEntity> listOld, final List<String> listNew,
            final LeastUserDto userDto) {

        // 現行のロール情報をマップ化
        Map<String, UserRoleEntity> roleMap = new TreeMap<>();
        for (UserRoleEntity entity : listOld) {
            roleMap.put(entity.getRole(), entity);
        }

        // 同一性のチェック（listAll と listNew が同一の場合空リストを返して処理中断）
        List<String> currentRoles = listOld.stream().map(UserRoleEntity::getRole).toList();
        if (new HashSet<>(currentRoles).equals(new HashSet<>(listNew))) {
            return new ArrayList<>();
        }

        // 詳細情報（コード）を持っている既存の権限リスト
        List<String> rolesWithRiyoushaCode = listOld.stream().filter(e -> e.getRiyoushaCode() != 0)
                .map(UserRoleEntity::getRole).toList();
        List<String> rolesWithKanrenshaCode = listOld.stream()
                .filter(e -> e.getKanrenshaCode() != null && !e.getKanrenshaCode().isEmpty())
                .map(UserRoleEntity::getRole).toList();

        // 既存のコードをそのまま引き継げる権限が、新しいリストにも含まれているか？
        boolean hasRetainedRiyousha = listNew.stream().anyMatch(rolesWithRiyoushaCode::contains);
        boolean hasRetainedKanrensha = listNew.stream().anyMatch(rolesWithKanrenshaCode::contains);

        // 引き継げない場合は、新規発番（または別権限からの複写）が必要
        boolean needsNewRiyoushaCode = !hasRetainedRiyousha;
        boolean needsNewKanrenshaCode = !hasRetainedKanrensha;

        List<UserRoleEntity> resultList = new ArrayList<>();
        for (String role : listNew) {
            // 1. まずベースとなる権限エンティティを作成（既存なら情報を引き継ぐ）
            UserRoleEntity entity = createRoleEntity(role, roleMap);

            // 2. 新規発番が必要な場合のみ、各権限に応じたコード割り当てを行う
            switch (role) {
                case UserRoleConstants.MANAGER:
                    if (needsNewRiyoushaCode) {
                        entity.setRiyoushaCode(copyInUserRoleRiyoushaManagerLogic.practice(rolesWithKanrenshaCode,
                                rolesWithRiyoushaCode, roleMap, userDto));
                        needsNewRiyoushaCode = false; // 発番は1回のみ（リストの先頭優先）
                    }
                    resultList.add(entity);

                    // 特例: ADMIN権限の維持（MANAGERが存在し、かつ以前からADMINを持っていた場合）
                    if (roleMap.containsKey(UserRoleConstants.ADMIN)) {
                        resultList.add(createRoleEntity(UserRoleConstants.ADMIN, roleMap));
                    }
                    break;

                case UserRoleConstants.PARTNER_API:
                    if (needsNewRiyoushaCode) {
                        entity.setRiyoushaCode(copyInUserRoleRiyoushaPartnerApiLogic.practice(rolesWithKanrenshaCode,
                                rolesWithRiyoushaCode, roleMap, userDto));
                        needsNewRiyoushaCode = false; // 発番は1回のみ（リストの先頭優先）
                    }
                    resultList.add(entity);
                    break;

                case UserRoleConstants.KANRENSHA_PERSON:
                    if (needsNewKanrenshaCode) {
                        entity.setKanrenshaCode(
                                copyInUserRoleKanrenshaPersonLogic.practice(rolesWithRiyoushaCode, roleMap, userDto));
                        needsNewKanrenshaCode = false; // 発番は1回のみ（リストの先頭優先）
                    }
                    resultList.add(entity);
                    break;
                // 企業団体と政治団体は変更を許可していないのでデッドコード
                case UserRoleConstants.KANRENSHA_KIGYOU_DT:
                case UserRoleConstants.KANRENSHA_SEIJIDANTAI:
                    // 発番ルールがないものはそのまま追加
                    resultList.add(entity);
                    break;

                default:
                    break;
            }
        }

        return resultList;
    }

    private UserRoleEntity createRoleEntity(final String role, final Map<String, UserRoleEntity> roleMap) {
        UserRoleEntity entity = new UserRoleEntity();
        entity.setRole(role);

        UserRoleEntity oldEntity = roleMap.get(role);
        if (oldEntity != null) {
            if (role.startsWith("kanrensha_")) {
                entity.setKanrenshaCode(oldEntity.getKanrenshaCode());
            } else {
                entity.setRiyoushaCode(oldEntity.getRiyoushaCode());
            }
        }
        return entity;
    }
}
