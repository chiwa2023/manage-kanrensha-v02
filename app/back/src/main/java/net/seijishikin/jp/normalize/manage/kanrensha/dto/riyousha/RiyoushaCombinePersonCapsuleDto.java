package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgEntity;

/**
 * 利用者組織紐づけ削除Dto
 */
public class RiyoushaCombinePersonCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 利用者組織マスタEntity */
    private RiyoushaCombineOrgEntity combineEntity = new RiyoushaCombineOrgEntity();

    /**
     * 利用者組織マスタEntityを取得する
     * 
     * @return 利用者組織マスタEntity
     */
    public RiyoushaCombineOrgEntity getCombineEntity() {
        return combineEntity;
    }

    /**
     * 利用者組織マスタEntityを設定する
     * 
     * @param combineEntity 利用者組織マスタEntity
     */
    public void setCombineEntity(final RiyoushaCombineOrgEntity combineEntity) {
        this.combineEntity = combineEntity;
    }

    /** 個人登録メールアドレス */
    private String email = INIT_STRING;

    /**
     * 個人登録メールアドレスを取得する
     * 
     * @return 個人登録メールアドレス
     */
    public String getEmail() {
        return email;
    }

    /**
     * 個人登録メールアドレスを設定する
     * 
     * @param email 個人登録メールアドレス
     */
    public void setEmail(final String email) {
        this.email = email;
    }

}
