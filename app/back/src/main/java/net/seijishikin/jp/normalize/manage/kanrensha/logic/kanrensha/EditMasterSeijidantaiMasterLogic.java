package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaSeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;

/**
 * 関連者政治団体マスタ編集Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class EditMasterSeijidantaiMasterLogic {

    /** 政治団体マスタリポジトリ */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditSeijidantaiMasterEntityLogic callForEditSeijidantaiMasterEntityLogic;

    /** 関連者政治団体Dto変換Logic */
    @Autowired
    private ConvertKanrenshaSeijidantaiDtoToMasterEntityLogic convertKanrenshaSeijidantaiDtoToMasterEntityLogic;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param capsuleDto 関連者政治団体格納Dto
     * @return 保存後のId
     * @throws EmptyResultDataAccessException テーブルIdが呼び出せなかったときの例外
     * @throws ConcurrencyFailureException    排他例外
     */
    public Integer practice(final SaveKanrenshaSeijidantaiCapsuleDto capsuleDto)
            throws EmptyResultDataAccessException, ConcurrencyFailureException { // NOPMD UncheckedException

        KanrenshaSeijidantaiDto poliOrgDto = capsuleDto.getKanrenshaSeijidantaiDto();
        LeastUserDto userDto = capsuleDto.getUserDto();

        KanrenshaSeijidantaiMasterEntity oldEntity = callForEditSeijidantaiMasterEntityLogic.practice(poliOrgDto);

        if (Objects.isNull(oldEntity)) {
            return 0;
        }

        setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
        kanrenshaSeijidantaiMasterRepository.save(oldEntity);

        KanrenshaSeijidantaiMasterEntity newSaveEntity = convertKanrenshaSeijidantaiDtoToMasterEntityLogic
                .practice(poliOrgDto);
        newSaveEntity.setKanrenshaSeijidantaiMasterId(0); // auto increment明記
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);

        return kanrenshaSeijidantaiMasterRepository.save(newSaveEntity).getKanrenshaSeijidantaiMasterId();
    }

}
