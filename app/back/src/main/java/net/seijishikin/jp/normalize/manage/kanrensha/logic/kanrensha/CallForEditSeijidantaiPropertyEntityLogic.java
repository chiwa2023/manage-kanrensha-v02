package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiPropertyRepository;

/**
 * 編集用に政治団体属性マスタを取得する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallForEditSeijidantaiPropertyEntityLogic {

    /** 政治団体属性マスタリポジトリ */
    @Autowired
    private KanrenshaSeijidantaiPropertyRepository kanrenshaSeijidantaiPropertyRepository;

    /**
     * 処理を行う
     *
     * @param kanrenshaSeijidantaiDto 関連者政治団体Dto
     * @return MasterPoliticalOrganizationPropertyEntity or null
     */
    public KanrenshaSeijidantaiPropertyEntity practice(final KanrenshaSeijidantaiDto kanrenshaSeijidantaiDto) {

        // IDをキーにマスタを取得
        KanrenshaSeijidantaiPropertyEntity entity = kanrenshaSeijidantaiPropertyRepository
                .findById(kanrenshaSeijidantaiDto.getPropertyId())
                .orElseThrow(() -> new EmptyResultDataAccessException(
                        "Not found master_political_organization_property. id = "
                                + kanrenshaSeijidantaiDto.getPropertyId(),
                        1));

        // 最新版か確認
        if (!entity.getIsLatest()) {
            throw new ConcurrencyFailureException(
                    "Target data is not the latest version. id = " + kanrenshaSeijidantaiDto.getPropertyId());
        }

        // DTOとEntityの値を比較
        boolean isNotChanged = Objects.equals(entity.getKanrenshaName(),
                kanrenshaSeijidantaiDto.getInputOrgNameDto().getOrgName())
                && Objects.equals(entity.getSeijidantaiKanrenshaCode(),
                        kanrenshaSeijidantaiDto.getSeijidantaiKanrenshaCode())
                && Objects.equals(entity.getOrgNameKana(),
                        kanrenshaSeijidantaiDto.getInputOrgNameDto().getOrgNameKana())
                && Objects.equals(entity.getOrgDelegateCode(),
                        kanrenshaSeijidantaiDto.getOrgDelegateLeastDto().getPersonKanrenshaCode())
                && Objects.equals(entity.getAccountMgrCode(),
                        kanrenshaSeijidantaiDto.getAccounrMgrLeastDto().getPersonKanrenshaCode())
                && Objects.equals(entity.getAccountMgrName(),
                        kanrenshaSeijidantaiDto.getAccounrMgrLeastDto().getPersonName())
                && Objects.equals(entity.getKanrenshaSeijidantaiId(), kanrenshaSeijidantaiDto.getMasterId());

        // 変更がない場合はnullを返却
        if (isNotChanged) {
            return null;
        }

        // 変更がある場合は取得したEntityをそのまま返却
        return entity;
    }
}
