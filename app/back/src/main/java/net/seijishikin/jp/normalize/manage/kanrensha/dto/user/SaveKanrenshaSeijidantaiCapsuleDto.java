package net.seijishikin.jp.normalize.manage.kanrensha.dto.user;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;

/**
 * 関連者政治団体格納Dto
 */
public class SaveKanrenshaSeijidantaiCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 関連者政治団体Dto */
    private KanrenshaSeijidantaiDto kanrenshaSeijidantaiDto = new KanrenshaSeijidantaiDto();

    /**
     * 関連者政治団体Dtoを取得する
     *
     * @return 関連者政治団体Dto
     */
    public KanrenshaSeijidantaiDto getKanrenshaSeijidantaiDto() {
        return kanrenshaSeijidantaiDto;
    }

    /**
     * 関連者政治団体Dtoを設定する
     *
     * @param kanrenshaSeijidantaiDto 関連者政治団体Dto
     */
    public void setKanrenshaSeijidantaiDto(final KanrenshaSeijidantaiDto kanrenshaSeijidantaiDto) {
        this.kanrenshaSeijidantaiDto = kanrenshaSeijidantaiDto;
    }

}
