package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;

/**
 * 編集用に個人マスタを取得する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallForEditPersonMasterEntityLogic {

    /** 個人マスタリポジトリ */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /**
     * 処理を行う
     *
     * @param kanrenshaPersonDto 関連者個人Dto
     * @return MasterPersonEntity or null
     */
    public KanrenshaPersonMasterEntity practice(final KanrenshaPersonDto kanrenshaPersonDto) {

        // IDをキーにマスタを取得
        KanrenshaPersonMasterEntity entity = kanrenshaPersonMasterRepository.findById(kanrenshaPersonDto.getMasterId())
                .orElseThrow(() -> new EmptyResultDataAccessException(
                        "Not found master_person. id = " + kanrenshaPersonDto.getMasterId(), 1));

        // 最新版か確認
        if (!entity.getIsLatest()) {
            throw new ConcurrencyFailureException(
                    "Target data is not the latest version. id = " + kanrenshaPersonDto.getMasterId());
        }

        // DTOとEntityの値を比較
        boolean isNotChanged = Objects.equals(entity.getKanrenshaName(),
                kanrenshaPersonDto.getInputPersonNameDto().getAllName())
                && Objects.equals(entity.getAllAddress(), kanrenshaPersonDto.getInputAddressDto().getAddressAll())
                && Objects.equals(entity.getPersonShokugyou(),
                        kanrenshaPersonDto.getInputShokugyouDto().getAllShokugyou())
                && Objects.equals(entity.getPersonKanrenshaCode(), kanrenshaPersonDto.getPersonKanrenshaCode());

        // 変更がない場合はnullを返却
        if (isNotChanged) {
            return null;
        }

        // 変更がある場合は取得したEntityをそのまま返却
        return entity;
    }
}
