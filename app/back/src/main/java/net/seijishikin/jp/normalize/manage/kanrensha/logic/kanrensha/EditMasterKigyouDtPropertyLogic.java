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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtPropertyRepository;

/**
 * 関連者企業団体属性マスタ編集Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class EditMasterKigyouDtPropertyLogic {

    /** 企業団体属性マスタリポジトリ */
    @Autowired
    private KanrenshaKigyouDtPropertyRepository kanrenshaKigyouDtPropertyRepository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditKigyouDtPropertyEntityLogic callForEditKigyouDtPropertyEntityLogic;

    /** 関連者企業団体Dto属性変換Logic */
    @Autowired
    private ConvertKanrenshaKigyouDtDtoToPropertyEntityLogic convertKanrenshaKigyouDtDtoToPropertyEntityLogic;

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

        if (0 != corpDto.getPropertyId()) {
            KanrenshaKigyouDtPropertyEntity oldEntity = callForEditKigyouDtPropertyEntityLogic.practice(corpDto);

            if (Objects.isNull(oldEntity)) {
                return 0;
            }
            setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
            kanrenshaKigyouDtPropertyRepository.save(oldEntity);
        }

        KanrenshaKigyouDtPropertyEntity newSaveEntity = convertKanrenshaKigyouDtDtoToPropertyEntityLogic
                .practice(corpDto);

        newSaveEntity.setKanrenshaKigyouDtPropertyId(0); // auto increment明記
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);

        return kanrenshaKigyouDtPropertyRepository.save(newSaveEntity).getKanrenshaKigyouDtPropertyId();
    }

}
