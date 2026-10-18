package net.seijishikin.jp.normalize.manage.kanrensha.logic.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.CustomUserDetails;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;

/**
 * ログイン中ユーザを取得する
 */
@Component
public class GetLoginLeastUserLogic {

    /** ユーザ人物Repository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /** ユーザ権限Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

    /**
     * 処理を行う
     * 
     * @return ユーザ最小限
     */
    public LeastUserDto practice() {

        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // NOPMD LawDemeter

        LeastUserDto userDto = new LeastUserDto();

        // 正常ログイン時
        if (user instanceof CustomUserDetails) {
            CustomUserDetails details = (CustomUserDetails) user;
            BeanUtils.copyProperties(details, userDto);
            for (GrantedAuthority authority : details.getAuthorities()) {
                userDto.getListRoles().add(authority.getAuthority());
            }
        }

        // テスト+@WithMockUser時
        if (user instanceof String) {
            String email = (String) user;
            Optional<UserPersonEntity> optional = userPersonRepository.findLatestByMail(email);
            if (!optional.isEmpty()) {
                UserPersonEntity entity = optional.get();
                userDto.setUserPersonId(entity.getUserPersonId());
                userDto.setUserPersonCode(entity.getUserPersonCode());
                userDto.setUserPersonName(entity.getUserPersonName());
            }

            List<UserRoleEntity> listRole = userRoleRepository.findByEmailAndIsLatestTrue(email);
            for (UserRoleEntity roleEntity : listRole) {
                userDto.getListRoles().add("ROLE_" + roleEntity.getRole());
            }

            // MEMO 関連者コードまたは利用者コードが必要な場合は設定する
        }

        return userDto;
    }

}
