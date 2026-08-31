package net.seijishikin.jp.normalize.manage.kanrensha.dto.yotei;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TimerYoteiEntity;

/**
 * 定期実行編集条件Dto
 */
public class EditTimerYoteiCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 定期実行Entity */
    private TimerYoteiEntity timerYoteiEntity = new TimerYoteiEntity();

    /**
     * 定期実行Entityを取得する
     * 
     * @return 定期実行Entity
     */
    public TimerYoteiEntity getTimerYoteiEntity() {
        return timerYoteiEntity;
    }

    /**
     * 定期実行Entityを設定する
     * 
     * @param timerYoteiEntity 定期実行Entity
     */
    public void setTimerYoteiEntity(final TimerYoteiEntity timerYoteiEntity) {
        this.timerYoteiEntity = timerYoteiEntity;
    }

}
