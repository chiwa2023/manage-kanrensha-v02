package net.seijishikin.jp.normalize.manage.kanrensha.dto.user;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;

/**
 * 関連者個人格納Dto
 */
public class SaveKanrenshaPersonCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 関連者個人Dto */
    private KanrenshaPersonDto kanrenshaPersonDto = new KanrenshaPersonDto();

    /**
     * 関連者個人Dto
     *
     * @return 関連者個人Dto
     */
    public KanrenshaPersonDto getKanrenshaPersonDto() {
        return kanrenshaPersonDto;
    }

    /**
     * 関連者個人Dto
     *
     * @param kanrenshaPersonDto 関連者個人Dto
     */
    public void setKanrenshaPersonDto(final KanrenshaPersonDto kanrenshaPersonDto) {
        this.kanrenshaPersonDto = kanrenshaPersonDto;
    }

}
