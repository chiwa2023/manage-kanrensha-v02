package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;

/**
 * 関連者企業団体マスタ編集Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class EditMasterKigyouDtMasterLogic {

    /** 企業団体マスタリポジトリ */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditKigyouDtMasterEntityLogic callForEditKigyouDtMasterEntityLogic;

    /** 関連者企業団体Dto変換Logic */
    @Autowired
    private ConvertKanrenshaKigyouDtDtoToMasterEntityLogic convertKanrenshaKigyouDtDtoToMasterEntityLogic;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param capsuleDto 関連者企業団体格納Dto
     * @return 保存後のId
     * @throws EmptyResultDataAccessException テーブルIdが呼び出せなかったときの例外
     * @throws ConcurrencyFailureException    排他例外
     */
    public Integer practice(final SaveKanrenshaKigyouDtCapsuleDto capsuleDto)
            throws EmptyResultDataAccessException, ConcurrencyFailureException { // NOPMD UncheckedException

        KanrenshaKigyouDtDto corpDto = capsuleDto.getKanrenshaKigyouDtDto();
        LeastUserDto userDto = capsuleDto.getUserDto();
        KanrenshaKigyouDtMasterEntity oldEntity = callForEditKigyouDtMasterEntityLogic.practice(corpDto);

        if (Objects.isNull(oldEntity)) {
            return 0;
        }

        setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
        kanrenshaKigyouDtMasterRepository.save(oldEntity);

        KanrenshaKigyouDtMasterEntity newSaveEntity = convertKanrenshaKigyouDtDtoToMasterEntityLogic.practice(corpDto);
        newSaveEntity.setKanrenshaKigyouDtMasterId(0); // auto increment明記
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);

        return kanrenshaKigyouDtMasterRepository.save(newSaveEntity).getKanrenshaKigyouDtMasterId();
    }

}
