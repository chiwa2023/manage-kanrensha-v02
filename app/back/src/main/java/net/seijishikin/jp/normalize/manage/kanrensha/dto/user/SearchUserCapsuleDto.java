package net.seijishikin.jp.normalize.manage.kanrensha.dto.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * ユーザ検索条件Dto
 */
public class SearchUserCapsuleDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 名称 */
    private String name = INIT_STRING;

    /** 権限リスト */
    private List<String> listRole = new ArrayList<>();

    /**
     * 名称を取得する
     * 
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称を設定する
     * 
     * @param name 名称
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * 権限リストを取得する
     * 
     * @return 権限リスト
     */
    public List<String> getListRole() {
        return listRole;
    }

    /**
     * 権限リストを設定する
     * 
     * @param listRole 権限リスト
     */
    public void setListRole(final List<String> listRole) {
        this.listRole = listRole;
    }

}
