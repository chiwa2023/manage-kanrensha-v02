package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;

/**
 * 編集用に政治団体マスタを取得する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallForEditSeijidantaiMasterEntityLogic {

    /** 政治団体マスタリポジトリ */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    /**
     * 処理を行う
     *
     * @param kanrenshaSeijidantaiDto 関連者政治団体Dto
     * @return MasterPoliticalOrganizationEntity or null
     */
    public KanrenshaSeijidantaiMasterEntity practice(final KanrenshaSeijidantaiDto kanrenshaSeijidantaiDto) {

        // IDをキーにマスタを取得
        KanrenshaSeijidantaiMasterEntity entity = kanrenshaSeijidantaiMasterRepository
                .findById(kanrenshaSeijidantaiDto.getMasterId()).orElseThrow(() -> new EmptyResultDataAccessException(
                        "Not found master_political_organization. id = " + kanrenshaSeijidantaiDto.getMasterId(), 1));

        // 最新版か確認
        if (!entity.getIsLatest()) {
            throw new ConcurrencyFailureException(
                    "Target data is not the latest version. id = " + kanrenshaSeijidantaiDto.getMasterId());
        }

        // DTOとEntityの値を比較
        boolean isNotChanged = Objects.equals(entity.getKanrenshaName(),
                kanrenshaSeijidantaiDto.getInputOrgNameDto().getOrgName())
                && Objects.equals(entity.getAllAddress(), kanrenshaSeijidantaiDto.getInputAddressDto().getAddressAll())
                && Objects.equals(entity.getSeijidantaiDelegate(),
                        kanrenshaSeijidantaiDto.getOrgDelegateLeastDto().getPersonName())
                && Objects.equals(entity.getSeijidantaiKanrenshaCode(),
                        kanrenshaSeijidantaiDto.getSeijidantaiKanrenshaCode())
                && Objects.equals(entity.getPoliOrgNo(), kanrenshaSeijidantaiDto.getPoliOrgNo())
                && Objects.equals(entity.getDantaiKbn(), kanrenshaSeijidantaiDto.getDantaiKbn());

        // 変更がない場合はnullを返却
        if (isNotChanged) {
            return null;
        }

        // 変更がある場合は取得したEntityをそのまま返却
        return entity;
    }
}
