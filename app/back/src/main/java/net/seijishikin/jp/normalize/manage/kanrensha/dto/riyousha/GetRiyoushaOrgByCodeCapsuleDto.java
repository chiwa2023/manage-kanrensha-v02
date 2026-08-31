package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * 運営者ユーザー格納Dto
 */
public class GetRiyoushaOrgByCodeCapsuleDto implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 選択中コード */
    private Integer selectedCode = INIT_INTEGER;

    /**
     * 選択中コードを取得する
     * 
     * @return 選択中コード
     */
    public Integer getSelectedCode() {
        return selectedCode;
    }

    /**
     * 選択中コードを設定する
     * 
     * @param selectedCode 選択中コード
     */
    public void setSelectedCode(final Integer selectedCode) {
        this.selectedCode = selectedCode;
    }

}
