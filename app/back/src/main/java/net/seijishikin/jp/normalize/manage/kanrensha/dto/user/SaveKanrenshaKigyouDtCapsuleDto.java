package net.seijishikin.jp.normalize.manage.kanrensha.dto.user;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;


/**
 * 関連者企業団体格納Dto
 */
public class SaveKanrenshaKigyouDtCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 関連者企業団体Dto */
    private KanrenshaKigyouDtDto kanrenshaKigyouDtDto = new KanrenshaKigyouDtDto();

    /**
     * 関連者企業団体Dtoを取得する
     *
     * @return 関連者企業団体Dto
     */
    public KanrenshaKigyouDtDto getKanrenshaKigyouDtDto() {
        return kanrenshaKigyouDtDto;
    }

    /**
     * 関連者企業団体Dtoを設定する
     *
     * @param kanrenshaKigyouDtDto 関連者企業団体Dto
     */
    public void setKanrenshaKigyouDtDto(final KanrenshaKigyouDtDto kanrenshaKigyouDtDto) {
        this.kanrenshaKigyouDtDto = kanrenshaKigyouDtDto;
    }

}
