package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAddressRepository;

/**
 * 編集用に政治団体住所マスタを取得する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallForEditSeijidantaiAddressEntityLogic {

    /** 政治団体住所マスタリポジトリ */
    @Autowired
    private KanrenshaSeijidantaiAddressRepository kanrenshaSeijidantaiAddressRepository;

    /**
     * 処理を行う
     *
     * @param kanrenshaSeijidantaiDto 関連者政治団体Dto
     * @return MasterPoliticalOrganizationAddressEntity or null
     */
    public KanrenshaSeijidantaiAddressEntity practice(final KanrenshaSeijidantaiDto kanrenshaSeijidantaiDto) {

        // IDをキーにマスタを取得
        KanrenshaSeijidantaiAddressEntity entity = kanrenshaSeijidantaiAddressRepository
                .findById(kanrenshaSeijidantaiDto.getAddressId())
                .orElseThrow(() -> new EmptyResultDataAccessException(
                        "Not found master_political_organization_address. id = "
                                + kanrenshaSeijidantaiDto.getAddressId(),
                        1));

        // 最新版か確認
        if (!entity.getIsLatest()) {
            throw new ConcurrencyFailureException(
                    "Target data is not the latest version. id = " + kanrenshaSeijidantaiDto.getAddressId());
        }

        // DTOとEntityの値を比較
        final InputAddressDto inputAddressDto = kanrenshaSeijidantaiDto.getInputAddressDto();

        boolean isNotChanged = Objects.equals(entity.getKanrenshaName(),
                kanrenshaSeijidantaiDto.getInputOrgNameDto().getOrgName())
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
                && Objects.equals(entity.getKanrenshaSeijidantaiId(), kanrenshaSeijidantaiDto.getMasterId());

        // 変更がない場合はnullを返却
        if (isNotChanged) {
            return null;
        }

        // 変更がある場合は取得したEntityをそのまま返却
        return entity;
    }
}
