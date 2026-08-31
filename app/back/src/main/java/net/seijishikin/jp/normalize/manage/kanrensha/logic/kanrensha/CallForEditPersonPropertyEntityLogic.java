package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonPropertyRepository;

/**
 * 編集用に個人属性マスタを取得する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallForEditPersonPropertyEntityLogic {

    /** 個人属性マスタリポジトリ */
    @Autowired
    private KanrenshaPersonPropertyRepository kanrenshaPersonPropertyRepository;

    /**
     * 処理を行う
     *
     * @param kanrenshaPersonDto 関連者個人Dto
     * @return MasterPersonPropertyEntity or null
     */
    public KanrenshaPersonPropertyEntity practice(final KanrenshaPersonDto kanrenshaPersonDto) {

        // IDをキーにマスタを取得
        KanrenshaPersonPropertyEntity entity = kanrenshaPersonPropertyRepository
                .findById(kanrenshaPersonDto.getPropertyId()).orElseThrow(() -> new EmptyResultDataAccessException(
                        "Not found master_person_property. id = " + kanrenshaPersonDto.getPropertyId(), 1));

        // 最新版か確認
        if (!entity.getIsLatest()) {
            throw new ConcurrencyFailureException(
                    "Target data is not the latest version. id = " + kanrenshaPersonDto.getPropertyId());
        }

        // DTOとEntityの値を比較
        boolean isNotChanged = Objects.equals(entity.getIsForeign(), kanrenshaPersonDto.getIsForeign())
                && Objects.equals(entity.getKanrenshaName(), kanrenshaPersonDto.getInputPersonNameDto().getAllName())
                && Objects.equals(entity.getPersonKanrenshaCode(), kanrenshaPersonDto.getPersonKanrenshaCode())
                && Objects.equals(entity.getAllNameKana(), kanrenshaPersonDto.getInputPersonNameDto().getAllNameKana())
                && Objects.equals(entity.getLastName(), kanrenshaPersonDto.getInputPersonNameDto().getLastName())
                && Objects.equals(entity.getFirstName(), kanrenshaPersonDto.getInputPersonNameDto().getFirstName())
                && Objects.equals(entity.getMiddleName(), kanrenshaPersonDto.getInputPersonNameDto().getMiddleName())
                && Objects.equals(entity.getLastNameKana(),
                        kanrenshaPersonDto.getInputPersonNameDto().getLastNameKana())
                && Objects.equals(entity.getFirstNameKana(),
                        kanrenshaPersonDto.getInputPersonNameDto().getFirstNameKana())
                && Objects.equals(entity.getMiddleNameKana(),
                        kanrenshaPersonDto.getInputPersonNameDto().getMiddleNameKana())
                && Objects.equals(entity.getGyoushu(), kanrenshaPersonDto.getInputShokugyouDto().getGyoushu())
                && Objects.equals(entity.getYakushoku(), kanrenshaPersonDto.getInputShokugyouDto().getYakushoku())
                && Objects.equals(entity.getShokugyouUserWrite(),
                        kanrenshaPersonDto.getInputShokugyouDto().getShokugyouUserWrite())
                && Objects.equals(entity.getKigyouDtNo(), kanrenshaPersonDto.getInputShokugyouDto().getHoujinNo())
                && Objects.equals(entity.getKigyouDtName(), kanrenshaPersonDto.getInputShokugyouDto().getHoujinName())
                && Objects.equals(entity.getKigyouDtAddress(),
                        kanrenshaPersonDto.getInputShokugyouDto().getHoujinAddress())
                && Objects.equals(entity.getKanrenshaPersonId(), kanrenshaPersonDto.getMasterId());

        // 変更がない場合はnullを返却
        if (isNotChanged) {
            return null;
        }

        // 変更がある場合は取得したEntityをそのまま返却
        return entity;
    }
}
