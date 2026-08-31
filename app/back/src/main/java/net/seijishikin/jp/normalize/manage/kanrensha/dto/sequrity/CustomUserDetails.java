package net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 独自ログインユーザ詳細
 */
public class CustomUserDetails implements UserDetails {


    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** ユーザId */
    private final Integer userPersonId;
    /** ユーザコード */
    private final Integer userPersonCode;
    /** ユーザ名 */
    private final String userPersonName;
    /** ログインユーザ(メールアドレス) */
    private final String username;
    /** パスワード */
    private final String password;
    /** 権限リスト */
    private final Collection<? extends GrantedAuthority> authorities;
    /** アカウント非停止フラグ */
    private final Boolean flgAccountNonExpired;
    /** アカウント非ロックフラフ */
    private final Boolean flgAccountNonLocked;
    /** アカウント非信頼フラグ */
    private final Boolean flgCredentialsNonExpired;
    /** 利用可能フラグ */
    private final Boolean flgEnabled;

    /**
     * コンストラクタ
     * 
     * @param userPersonId            ユーザId
     * @param userPersonCode          ユーザコード
     * @param userPersonName          ユーザ名
     * @param username                ログインユーザ(メールアドレス)
     * @param password                パスワード
     * @param authorities             権限リスト
     * @param isAccountNonExpired     アカウント非停止フラグ
     * @param isAccountNonLocked      アカウント非ロックフラフ
     * @param isCredentialsNonExpired アカウント非信頼フラグ
     * @param isEnabled               利用可能フラグ
     */
    public CustomUserDetails(final Integer userPersonId, final Integer userPersonCode, final String userPersonName,
            final String username, final String password, final Collection<? extends GrantedAuthority> authorities,
            final Boolean isAccountNonExpired, final Boolean isAccountNonLocked, final Boolean isCredentialsNonExpired,
            final Boolean isEnabled) {
        this.userPersonId = userPersonId;
        this.userPersonCode = userPersonCode;
        this.userPersonName = userPersonName;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.flgAccountNonExpired = isAccountNonExpired;
        this.flgAccountNonLocked = isAccountNonLocked;
        this.flgCredentialsNonExpired = isCredentialsNonExpired;
        this.flgEnabled = isEnabled;
    }

    /**
     * ユーザId
     * 
     * @return ユーザId
     */
    public Integer getUserPersonId() {
        return userPersonId;
    }

    /**
     * ユーザコード
     * 
     * @return ユーザコード
     */
    public Integer getUserPersonCode() {
        return userPersonCode;
    }

    /**
     * ユーザ名
     * 
     * @return ユーザ名
     */
    public String getUserPersonName() {
        return userPersonName;
    }

    /**
     * 権限リスト
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * パスワード
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * ログインユーザ(メールアドレス)
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * アカウント非停止フラグ
     */
    @Override
    public boolean isAccountNonExpired() {
        return flgAccountNonExpired;
    }

    /**
     * アカウント非ロックフラグ
     */
    @Override
    public boolean isAccountNonLocked() {
        return flgAccountNonLocked;
    }

    /**
     * アカウント非信頼フラグ
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return flgCredentialsNonExpired;
    }

    /**
     * 利用可能フラグ
     */
    @Override
    public boolean isEnabled() {
        return flgEnabled;
    }
}
