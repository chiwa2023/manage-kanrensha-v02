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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAddressRepository;

/**
 * 関連者政治団体住所マスタ編集Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class EditMasterSeijidantaiAddressLogic {

    /** 政治団体住所マスタリポジトリ */
    @Autowired
    private KanrenshaSeijidantaiAddressRepository kanrenshaSeijidantaiAddressRepository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditSeijidantaiAddressEntityLogic callForEditSeijidantaiAddressEntityLogic;

    /** 関連者政治団体Dto住所変換Logic */
    @Autowired
    private ConvertKanrenshaSeijidantaiDtoToAddressEntityLogic convertKanrenshaSeijidantaiDtoToAddressEntityLogic;

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

        // マスタ最小であれば呼び出す住所がない
        if (0 != poliOrgDto.getAddressId()) {
            KanrenshaSeijidantaiAddressEntity oldEntity = callForEditSeijidantaiAddressEntityLogic.practice(poliOrgDto);

            if (Objects.isNull(oldEntity)) {
                return 0;
            }
            setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
            kanrenshaSeijidantaiAddressRepository.save(oldEntity);
        }

        KanrenshaSeijidantaiAddressEntity newSaveEntity = convertKanrenshaSeijidantaiDtoToAddressEntityLogic
                .practice(poliOrgDto);
        newSaveEntity.setKanrenshaSeijidantaiAddressId(0); // auto increment明記
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);

        return kanrenshaSeijidantaiAddressRepository.save(newSaveEntity).getKanrenshaSeijidantaiAddressId();
    }

}
