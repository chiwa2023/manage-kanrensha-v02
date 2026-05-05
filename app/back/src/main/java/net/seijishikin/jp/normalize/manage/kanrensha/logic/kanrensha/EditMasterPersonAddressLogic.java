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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonAddressRepository;

/**
 * 関連者個人住所マスタ編集Logic
 */
@Component
public class EditMasterPersonAddressLogic {

    /** 個人住所マスタリポジトリ */
    @Autowired
    private KanrenshaPersonAddressRepository kanrenshaPersonAddressRepository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditPersonAddressEntityLogic callForEditPersonAddressEntityLogic;

    /** 関連者個人Dto住所変換Logic */
    @Autowired
    private ConvertKanrenshaPersonDtoToAddressEntityLogic convertKanrenshaPersonDtoToAddressEntityLogic;

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

        // マスタ最小であれば呼び出す住所がない
        if (0 != personDto.getAddressId()) {
            KanrenshaPersonAddressEntity oldEntity = callForEditPersonAddressEntityLogic.practice(personDto);

            if (Objects.isNull(oldEntity)) {
                return 0;
            }
            setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
            kanrenshaPersonAddressRepository.save(oldEntity);
        }

        KanrenshaPersonAddressEntity newSaveEntity = convertKanrenshaPersonDtoToAddressEntityLogic.practice(personDto);
        newSaveEntity.setKanrenshaPersonAddressId(0); // auto increment明記
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);

        return kanrenshaPersonAddressRepository.save(newSaveEntity).getKanrenshaPersonAddressId();
    }

}
