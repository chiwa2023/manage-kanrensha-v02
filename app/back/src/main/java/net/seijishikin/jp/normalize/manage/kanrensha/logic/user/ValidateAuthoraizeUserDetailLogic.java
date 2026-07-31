package net.seijishikin.jp.normalize.manage.kanrensha.logic.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.CustomUserDetails;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;

/**
 * 最小限ユーザ検証Logic
 */
@Component
public class ValidateAuthoraizeUserDetailLogic {

    /** ユーザ権限Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

    /**
     * 処理を行う
     * 
     * @param userDto ユーザ最小限
     * @return 検証結果
     */
    public String practice(final LeastUserDto userDto) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext() // NOPMD LawDemeter
                .getAuthentication().getPrincipal();

        // テストで@withMockUserを使用し、仮ログイン状態でなければ有効
        if (userDetails instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
            this.checkUserInfo(customUserDetails, userDto);
        }

        return userDetails.getUsername();
    }

    /**
     * 処理を行う
     * 
     * @param userDto           最小限ユーザ
     * @param editKanrenshaCode 関連者コード
     * @param baseRole          編集対象権限
     * @param roles             許可権限配列
     * @return 検証結果
     */
    public Boolean practiceKanrensha(final LeastUserDto userDto, final String editKanrenshaCode, final String baseRole,
            final String... roles) {

        String mailAddress = this.practice(userDto);

        // 他者編集は指定権限に一致しないと許可しない
        // →他者編集時には実際のログインをしないと、テスト不合格とするでOK
        if (!Objects.isNull(editKanrenshaCode)) {

            // 許可権限があれば編集対象コードにかかわらず許可
            if (this.hasRole(userDto, baseRole, roles)) {
                return true;
            }

            List<UserRoleEntity> litRole = userRoleRepository.findByEmailAndIsLatestTrue(mailAddress);

            // 優先権限でない場合はユーザ一致していれば編集許可
            for (UserRoleEntity roleEntity : litRole) {
                if (editKanrenshaCode.equals(roleEntity.getKanrenshaCode()) && baseRole.equals(roleEntity.getRole())) {
                    return true;
                }
            }
            return false;
        }

        return true;

    }

    /**
     * 処理を行う
     * 
     * @param userDto          最小限ユーザ
     * @param editRiyoushaCode 編集Id
     * @param baseRole         編集対象権限
     * @param roles            許可権限配列
     * @return 検証結果
     */
    public Boolean practiceRiyousha(final LeastUserDto userDto, final Integer editRiyoushaCode, final String baseRole,
            final String... roles) {

        String mailAddress = this.practice(userDto);

        // 他者編集は指定権限に一致しないと許可しない
        // →他者編集時には実際のログインをしないと、テスト不合格とするでOK
        if (!Objects.isNull(editRiyoushaCode)) {

            // 許可権限があれば編集対象コードにかかわらず許可
            if (this.hasRole(userDto, baseRole, roles)) {
                return true;
            }

            List<UserRoleEntity> litRole = userRoleRepository.findByEmailAndIsLatestTrue(mailAddress);

            // 優先権限でない場合はユーザ一致していれば編集許可
            for (UserRoleEntity roleEntity : litRole) {
                if (editRiyoushaCode.equals(roleEntity.getRiyoushaCode()) && baseRole.equals(roleEntity.getRole())) {
                    return true;
                }
            }

            // 権限指定し忘れ、または最後まで走査しても該当権限がなかった場合
            return false;
        }

        return true;
    }

    private boolean checkUserInfo(final CustomUserDetails customUserDetails, final LeastUserDto userDto)
            throws UsernameNotFoundException { // NOPMD UncheckedException

        // 送ってきたユーザとTokenから抽出したログインユーザが合わない
        if (!customUserDetails.getUserPersonId().equals(userDto.getUserPersonId())) {
            throw new UsernameNotFoundException("トークンとログインIdが一致しません");
        }
        if (!customUserDetails.getUserPersonCode().equals(userDto.getUserPersonCode())) {
            throw new UsernameNotFoundException("トークンとログインCodeが一致しません");
        }
        if (!customUserDetails.getUserPersonName().equals(userDto.getUserPersonName())) {
            throw new UsernameNotFoundException("トークンとログイン名が一致しません");
        }

        // 送ってきたユーザと権限が合わない
        List<String> roleList = this.convertRoleList(customUserDetails.getAuthorities());

        if (roleList.size() != userDto.getListRoles().size()) {
            throw new UsernameNotFoundException("トークンと権限が一致しません(リストサイズ)");
        }
        for (String role : userDto.getListRoles()) {
            if (!roleList.contains(role)) {
                throw new UsernameNotFoundException("トークンと権限が一致しません");
            }
        }
        return true;
    }

    private List<String> convertRoleList(final Collection<? extends GrantedAuthority> collections) {
        List<String> list = new ArrayList<>();
        for (GrantedAuthority authority : collections) {
            list.add(authority.getAuthority());
        }
        return list;
    }

    private boolean hasRole(final LeastUserDto userDto, final String baseRole, final String[] roles) { // NOPMD VarArgs
        if (Objects.isNull(baseRole)) {
            throw new IllegalArgumentException("取得する権限が指定されていません");
        }

        // 権限が他者編集許可であれば、権限の有無を確認して許可
        for (String role : roles) {
            if (userDto.getListRoles().contains("ROLE_" + role)) {
                return true;
            }
        }

        return false;
    }
}
