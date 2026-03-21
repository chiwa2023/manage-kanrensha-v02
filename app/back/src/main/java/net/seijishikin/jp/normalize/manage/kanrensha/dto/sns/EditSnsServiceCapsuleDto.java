package net.seijishikin.jp.normalize.manage.kanrensha.dto.sns;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.SnsServiceEntity;

/**
 * SNSサービス編集内容Dto
 */
public class EditSnsServiceCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象SNSサービス */
    private SnsServiceEntity snsServiceEntity;

    /**
     * 編集対象SNSサービスを取得する
     * 
     * @return 編集対象SNSサービス
     */
    public SnsServiceEntity getSnsServiceEntity() {
        return snsServiceEntity;
    }

    /**
     * 編集対象SNSサービスを設定する
     * 
     * @param snsServiceEntity 編集対象SNSサービス
     */
    public void setSnsServiceEntity(final SnsServiceEntity snsServiceEntity) {
        this.snsServiceEntity = snsServiceEntity;
    }

}
