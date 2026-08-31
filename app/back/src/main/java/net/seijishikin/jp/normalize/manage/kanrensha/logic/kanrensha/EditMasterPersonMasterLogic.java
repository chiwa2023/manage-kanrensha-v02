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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;

/**
 * 関連者個人マスタ編集Logic
 */
@Component
public class EditMasterPersonMasterLogic {

    /** 個人マスタリポジトリ */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditPersonMasterEntityLogic callForEditPersonMasterEntityLogic;

    /** 関連者個人Dto変換Logic */
    @Autowired
    private ConvertKanrenshaPersonDtoToMasterEntityLogic convertKanrenshaPersonDtoToMasterEntityLogic;

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

        LeastUserDto userDto = capsuleDto.getUserDto();
        KanrenshaPersonDto personDto = capsuleDto.getKanrenshaPersonDto();

        KanrenshaPersonMasterEntity oldEntity = callForEditPersonMasterEntityLogic.practice(personDto);

        if (Objects.isNull(oldEntity)) {
            return 0;
        }

        setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
        kanrenshaPersonMasterRepository.save(oldEntity);

        KanrenshaPersonMasterEntity newSaveEntity = convertKanrenshaPersonDtoToMasterEntityLogic.practice(personDto);
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);
        newSaveEntity.setKanrenshaPersonMasterId(0); // auto increment明記

        return kanrenshaPersonMasterRepository.save(newSaveEntity).getKanrenshaPersonMasterId();
    }

}
