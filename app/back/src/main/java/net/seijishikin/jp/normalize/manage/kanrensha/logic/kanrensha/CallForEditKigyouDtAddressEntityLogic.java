package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtAddressRepository;

/**
 * 編集用に企業団体住所マスタを取得する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallForEditKigyouDtAddressEntityLogic {

    /** 企業団体住所マスタリポジトリ */
    @Autowired
    private KanrenshaKigyouDtAddressRepository kanrenshaKigyouDtAddressRepository;

    /**
     * 処理を行う
     *
     * @param kanrenshaKigyouDtDto 関連者企業団体Dto
     * @return MasterKigyouDtorationAddressEntity or null
     */
    public KanrenshaKigyouDtAddressEntity practice(final KanrenshaKigyouDtDto kanrenshaKigyouDtDto) {

        // IDをキーにマスタを取得
        KanrenshaKigyouDtAddressEntity entity = kanrenshaKigyouDtAddressRepository
                .findById(kanrenshaKigyouDtDto.getAddressId()).orElseThrow(() -> new EmptyResultDataAccessException(
                        "Not found master_KigyouDtoration_address. id = " + kanrenshaKigyouDtDto.getAddressId(), 1));

        // 最新版か確認
        if (!entity.getIsLatest()) {
            throw new ConcurrencyFailureException(
                    "Target data is not the latest version. id = " + kanrenshaKigyouDtDto.getAddressId());
        }

        // DTOとEntityの値を比較
        final InputAddressDto inputAddressDto = kanrenshaKigyouDtDto.getInputAddressDto();

        boolean isNotChanged = Objects.equals(entity.getKanrenshaName(),
                kanrenshaKigyouDtDto.getInputOrgNameDto().getOrgName())
                && Objects.equals(entity.getAddressPostal(), inputAddressDto.getAddressPostal())
                && Objects.equals(entity.getAddressBlock(), inputAddressDto.getAddressBlock())
                && Objects.equals(entity.getAddressBuilding(), inputAddressDto.getAddressBuilding())
                && Objects.equals(entity.getPostalcode1(), inputAddressDto.getPostalcode1())
                && Objects.equals(entity.getPostalcode2(), inputAddressDto.getPostalcode2())
                && Objects.equals(entity.getLgCode(), inputAddressDto.getLgCode())
                && Objects.equals(entity.getMachiazaId(), inputAddressDto.getMachiazaId())
                && Objects.equals(entity.getBlkId(), inputAddressDto.getBlkId())
                && Objects.equals(entity.getPrcId(), inputAddressDto.getPrcId())
                && Objects.equals(entity.getRsdtId(), inputAddressDto.getRsdtId())
                && Objects.equals(entity.getRsdt2Id(), inputAddressDto.getRsdt2Id())
                && Objects.equals(entity.getIsPostalEdit(), inputAddressDto.getIsPostalEdit())
                && Objects.equals(entity.getIsBlockEdit(), inputAddressDto.getIsBlockEdit())
                && Objects.equals(entity.getIsBuildingEdit(), inputAddressDto.getIsBuildingEdit())
                && Objects.equals(entity.getKanrenshaKigyouDtId(), kanrenshaKigyouDtDto.getMasterId());

        // 変更がない場合はnullを返却
        if (isNotChanged) {
            return null;
        }

        // 変更がある場合は取得したEntityをそのまま返却
        return entity;
    }
}
