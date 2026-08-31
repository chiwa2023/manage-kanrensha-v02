package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAccessDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtAccessRepository;

/**
 * 編集用にマスタ企業団体連絡先取得Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallForEditKigyouDtAccessEntityLogic {

    /** マスタ企業団体連絡先レポジトリ */
    @Autowired
    private KanrenshaKigyouDtAccessRepository kanrenshaKigyouDtAccessRepository;

    /**
     * 処理を行う
     *
     * @param dto 関連者企業団体Dto
     * @return 更新が必要ない場合はnull、必要な場合はDBから取得したEntity
     */
    public KanrenshaKigyouDtAccessEntity practice(final KanrenshaKigyouDtDto dto)
            throws EmptyResultDataAccessException, ConcurrencyFailureException { // NOPMD UncheckedException

        // accessIdを使ってEntityを取得
        KanrenshaKigyouDtAccessEntity entity = kanrenshaKigyouDtAccessRepository.findById(dto.getAccessId())
                .orElseThrow(
                        () -> new EmptyResultDataAccessException("No entity found with id: " + dto.getAccessId(), 1));

        // isLatestがfalseの場合は排他エラー
        if (!entity.getIsLatest()) {
            throw new ConcurrencyFailureException("The record with id " + dto.getAccessId() + " is not the latest.");
        }

        InputAccessDto accessDto = dto.getInputAccessDto();
        boolean isNotChanged = Objects.equals(entity.getKanrenshaName(), dto.getInputOrgNameDto().getOrgName())
                && Objects.equals(entity.getPhon1(), accessDto.getPhon1())
                && Objects.equals(entity.getPhon2(), accessDto.getPhon2())
                && Objects.equals(entity.getPhon3(), accessDto.getPhon3())
                && Objects.equals(entity.getEmail(), accessDto.getEmail())
                && Objects.equals(entity.getMyPortalUrl(), accessDto.getMyPortalUrl())
                && Objects.equals(entity.getSnsServiceId(), accessDto.getSnsServiceId())
                && Objects.equals(entity.getSnsServiceCode(), accessDto.getSnsServiceCode())
                && Objects.equals(entity.getSnsServiceName(), accessDto.getSnsServiceName())
                && Objects.equals(entity.getSnsPortalUrl(), accessDto.getSnsPortalUrl())
                && Objects.equals(entity.getSnsAccount(), accessDto.getSnsAccount())
                && Objects.equals(entity.getKanrenshaKigyouDtId(), dto.getMasterId());

        // DTOとEntityの値を比較
        if (isNotChanged) {
            // 変更がない場合はnullを返す
            return null;
        } else {
            // 変更がある場合は、DBから取得したEntityをそのまま返す
            return entity;
        }
    }

}
