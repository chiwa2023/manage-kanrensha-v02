package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonAddressRepository;

/**
 * 編集用に個人住所マスタを取得する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallForEditPersonAddressEntityLogic {

    /** 個人住所マスタリポジトリ */
    @Autowired
    private KanrenshaPersonAddressRepository kanrenshaPersonAddressRepository;

    /**
     * 処理を行う
     *
     * @param kanrenshaPersonDto 関連者個人Dto
     * @return MasterPersonAddressEntity or null
     */
    public KanrenshaPersonAddressEntity practice(final KanrenshaPersonDto kanrenshaPersonDto) {

        // IDをキーにマスタを取得
        KanrenshaPersonAddressEntity entity = kanrenshaPersonAddressRepository
                .findById(kanrenshaPersonDto.getAddressId()).orElseThrow(() -> new EmptyResultDataAccessException(
                        "Not found master_person_address. id = " + kanrenshaPersonDto.getAddressId(), 1));

        // 最新版か確認
        if (!entity.getIsLatest()) {
            throw new ConcurrencyFailureException(
                    "Target data is not the latest version. id = " + kanrenshaPersonDto.getAddressId());
        }

        // DTOとEntityの値を比較
        final InputAddressDto inputAddressDto = kanrenshaPersonDto.getInputAddressDto();

        boolean isNotChanged = Objects.equals(entity.getKanrenshaName(),
                kanrenshaPersonDto.getInputPersonNameDto().getAllName())
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
                && Objects.equals(entity.getKanrenshaPersonId(), kanrenshaPersonDto.getMasterId());

        // 変更がない場合はnullを返却
        if (isNotChanged) {
            return null;
        }

        // 変更がある場合は取得したEntityをそのまま返却
        return entity;
    }
}
