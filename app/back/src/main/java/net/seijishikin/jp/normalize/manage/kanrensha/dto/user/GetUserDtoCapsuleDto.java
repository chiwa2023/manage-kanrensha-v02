package net.seijishikin.jp.normalize.manage.kanrensha.dto.user;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * 編集対象ユーザ取得Dto
 */
public class GetUserDtoCapsuleDto // NOPMD DataClass
        implements DtoEntityInitialValueInterface, Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集ユーザId */
    private Integer editUserid;

    /**
     * 編集ユーザIdを取得
     * 
     * @return 編集ユーザId
     */
    public Integer getEditUserid() {
        return editUserid;
    }

    /**
     * 編集ユーザIdを設定
     * 
     * @param editUserid 編集ユーザId
     */
    public void setEditUserid(final Integer editUserid) {
        this.editUserid = editUserid;
    }

}
