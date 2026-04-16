package net.seijishikin.jp.normalize.manage.kanrensha.dto.works_approval;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaAddressBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;

/**
 * 作業承認内容保存Dto
 */
public class SaveWorksApprovalCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 住所承認作業リスト */
    private List<KanrenshaAddressBaseEntity> listAddress = new ArrayList<>();

    /** 承認作業用職業リスト */
    private List<KanrenshaPersonPropertyEntity> listShokugyou = new ArrayList<>();

    /**
     * 住所承認作業リストを取得する
     *
     * @return 住所承認作業リスト
     */
    public List<KanrenshaAddressBaseEntity> getListAddress() {
        return listAddress;
    }

    /**
     * 住所承認作業リストを設定する
     *
     * @param listAddress 住所承認作業リスト
     */
    public void setListAddress(final List<KanrenshaAddressBaseEntity> listAddress) {
        this.listAddress = listAddress;
    }

    /**
     * 承認作業用職業リストを取得する
     *
     * @return 承認作業用職業リスト
     */
    public List<KanrenshaPersonPropertyEntity> getListShokugyou() {
        return listShokugyou;
    }

    /**
     * 承認作業用職業リストを設定する
     *
     * @param listShokugyou 承認作業用職業リスト
     */
    public void setListShokugyou(final List<KanrenshaPersonPropertyEntity> listShokugyou) {
        this.listShokugyou = listShokugyou;
    }

}
