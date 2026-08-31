package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;

/**
 * 編集用に企業団体マスタを取得する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallForEditKigyouDtMasterEntityLogic {

    /** 企業団体マスタリポジトリ */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    /**
     * 処理を行う
     *
     * @param kanrenshaKigyouDtDto 関連者企業団体Dto
     * @return MasterKigyouDtorationEntity or null
     */
    public KanrenshaKigyouDtMasterEntity practice(final KanrenshaKigyouDtDto kanrenshaKigyouDtDto) {

        // IDをキーにマスタを取得
        KanrenshaKigyouDtMasterEntity entity = kanrenshaKigyouDtMasterRepository
                .findById(kanrenshaKigyouDtDto.getMasterId()).orElseThrow(() -> new EmptyResultDataAccessException(
                        "Not found master_KigyouDtoration. id = " + kanrenshaKigyouDtDto.getMasterId(), 1));

        // 最新版か確認
        if (!entity.getIsLatest()) {
            throw new ConcurrencyFailureException(
                    "Target data is not the latest version. id = " + kanrenshaKigyouDtDto.getMasterId());
        }

        // DTOとEntityの値を比較
        boolean isNotChanged = Objects.equals(entity.getKanrenshaName(),
                kanrenshaKigyouDtDto.getInputOrgNameDto().getOrgName())
                && Objects.equals(entity.getAllAddress(), kanrenshaKigyouDtDto.getInputAddressDto().getAddressAll())
                && Objects.equals(entity.getHoujinNo(), kanrenshaKigyouDtDto.getHoujinNo())
                && Objects.equals(entity.getKigyouDtDelegate(),
                        kanrenshaKigyouDtDto.getOrgDelegateLeastDto().getPersonName())
                && Objects.equals(entity.getKigyouDtKanrenshaCode(), kanrenshaKigyouDtDto.getKigyouDtKanrenshaCode());

        // 変更がない場合はnullを返却
        if (isNotChanged) {
            return null;
        }

        // 変更がある場合は取得したEntityをそのまま返却
        return entity;
    }
}
