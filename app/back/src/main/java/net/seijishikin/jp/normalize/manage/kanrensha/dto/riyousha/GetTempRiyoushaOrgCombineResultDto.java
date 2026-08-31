package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgTempEntity;

/**
 * 利用者組織紐づけ仮情報結果Dto
 */
public class GetTempRiyoushaOrgCombineResultDto extends FrameworkMessageAndResultDto {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 承諾利用者所属仮登録Entity */
    private RiyoushaCombineOrgTempEntity combineTempEntity = new RiyoushaCombineOrgTempEntity();

    /**
     * 承諾利用者所属仮登録Entityを取得する
     * 
     * @return 承諾利用者所属仮登録Entity
     */
    public RiyoushaCombineOrgTempEntity getCombineTempEntity() {
        return combineTempEntity;
    }

    /**
     * 承諾利用者所属仮登録Entityを設定する
     * 
     * @param combineTempEntity 承諾利用者所属仮登録Entity
     */
    public void setCombineTempEntity(final RiyoushaCombineOrgTempEntity combineTempEntity) {
        this.combineTempEntity = combineTempEntity;
    }

}
