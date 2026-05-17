package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonPropertyRepository;

/**
 * 関連者個人属性マスタ編集Logic
 */
@Component
public class EditMasterPersonPropertyLogic {

    /** 個人属性マスタリポジトリ */
    @Autowired
    private KanrenshaPersonPropertyRepository kanrenshaPersonPropertyRepository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditPersonPropertyEntityLogic callForEditPersonPropertyEntityLogic;

    /** 関連者個人Dto属性変換Logic */
    @Autowired
    private ConvertKanrenshaPersonDtoToPropertyEntityLogic convertKanrenshaPersonDtoToPropertyEntityLogic;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param capsuleDto 関連者個人格納Dto
     * @return 保存後のId
     * @throws EmptyResultDataAccessException テーブルIdが呼び出せなかったときの例外
     * @throws ConcurrencyFailureException    排他例外
     */
    public Integer practice(final SaveKanrenshaPersonCapsuleDto capsuleDto)
            throws EmptyResultDataAccessException, ConcurrencyFailureException { // NOPMD UncheckedException

        KanrenshaPersonDto personDto = capsuleDto.getKanrenshaPersonDto();
        LeastUserDto userDto = capsuleDto.getUserDto();

        if (0 != personDto.getPropertyId()) {
            KanrenshaPersonPropertyEntity oldEntity = callForEditPersonPropertyEntityLogic.practice(personDto);

            if (Objects.isNull(oldEntity)) {
                return 0;
            }
            setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
            kanrenshaPersonPropertyRepository.save(oldEntity);
        }

        KanrenshaPersonPropertyEntity newSaveEntity = convertKanrenshaPersonDtoToPropertyEntityLogic
                .practice(personDto);
        newSaveEntity.setKanrenshaPersonPropertyId(0);
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);

        return kanrenshaPersonPropertyRepository.save(newSaveEntity).getKanrenshaPersonPropertyId();
    }

}
