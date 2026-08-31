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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiPropertyRepository;

/**
 * 関連者政治団体属性マスタ編集Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class EditMasterSeijidantaiPropertyLogic {

    /** 関連者政治団体住所リポジトリ */
    @Autowired
    private KanrenshaSeijidantaiPropertyRepository kanrenshaSeijidantaiPropertyRepository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditSeijidantaiPropertyEntityLogic callForEditSeijidantaiPropertyEntityLogic;

    /** 関連者政治団体Dto属性変換Logic */
    @Autowired
    private ConvertKanrenshaSeijidantaiDtoToPropertyEntityLogic convertKanrenshaSeijidantaiDtoToPropertyEntityLogic;

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

        if (0 != poliOrgDto.getPropertyId()) {
            KanrenshaSeijidantaiPropertyEntity oldEntity = callForEditSeijidantaiPropertyEntityLogic
                    .practice(poliOrgDto);

            if (Objects.isNull(oldEntity)) {
                return 0;
            }
            setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
            kanrenshaSeijidantaiPropertyRepository.save(oldEntity);
        }

        KanrenshaSeijidantaiPropertyEntity newSaveEntity = convertKanrenshaSeijidantaiDtoToPropertyEntityLogic
                .practice(poliOrgDto);
        newSaveEntity.setKanrenshaSeijidantaiPropertyId(0); // auto increment明記
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);

        return kanrenshaSeijidantaiPropertyRepository.save(newSaveEntity).getKanrenshaSeijidantaiPropertyId();
    }

}
