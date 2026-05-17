package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.HoujinShubetsuConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtPropertyRepository;

/**
 * 編集用に企業団体属性マスタを取得する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallForEditKigyouDtPropertyEntityLogic {

    /** 企業団体属性マスタリポジトリ */
    @Autowired
    private KanrenshaKigyouDtPropertyRepository kanrenshaKigyouDtPropertyRepository;

    /**
     * 処理を行う
     *
     * @param kanrenshaKigyouDtDto 関連者企業団体Dto
     * @return MasterKigyouDtorationPropertyEntity or null
     */
    public KanrenshaKigyouDtPropertyEntity practice(final KanrenshaKigyouDtDto kanrenshaKigyouDtDto) {

        // IDをキーにマスタを取得
        KanrenshaKigyouDtPropertyEntity entity = kanrenshaKigyouDtPropertyRepository
                .findById(kanrenshaKigyouDtDto.getPropertyId()).orElseThrow(() -> new EmptyResultDataAccessException(
                        "Not found master_corporation_property. id = " + kanrenshaKigyouDtDto.getPropertyId(), 1));

        // 最新版か確認
        if (!entity.getIsLatest()) {
            throw new ConcurrencyFailureException(
                    "Target data is not the latest version. id = " + kanrenshaKigyouDtDto.getPropertyId());
        }

        // DTOとEntityの値を比較
        boolean isNotChanged = Objects.equals(entity.getKanrenshaName(),
                kanrenshaKigyouDtDto.getInputOrgNameDto().getOrgName())
                && Objects.equals(entity.getKigyouDtKanrenshaCode(), kanrenshaKigyouDtDto.getKigyouDtKanrenshaCode())
                && Objects.equals(entity.getHoujinSbts(), kanrenshaKigyouDtDto.getHoujinSbts())
                && Objects.equals(entity.getIsForeign(),
                        HoujinShubetsuConstants.GAIKOKU_KAISHA.equals(kanrenshaKigyouDtDto.getHoujinSbts()))
                && Objects.equals(entity.getIsShiten(), kanrenshaKigyouDtDto.getIsShiten())
                && Objects.equals(entity.getOrgDelegateCode(),
                        kanrenshaKigyouDtDto.getOrgDelegateLeastDto().getPersonKanrenshaCode())
                && Objects.equals(entity.getKanrenshaKigyouDtId(), kanrenshaKigyouDtDto.getMasterId());

        // 変更がない場合はnullを返却
        if (isNotChanged) {
            return null;
        }

        // 変更がある場合は取得したEntityをそのまま返却
        return entity;
    }
}
