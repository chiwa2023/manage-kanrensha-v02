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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonAccessRepository;

/**
 * 関連者個人連絡先マスタ編集Logic
 */
@Component
public class EditMasterPersonAccessLogic {

    /** マスタ個人連絡先レポジトリ */
    @Autowired
    private KanrenshaPersonAccessRepository kanrenshaPersonAccessRepository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditPersonAccessEntityLogic callForEditPersonAccessEntityLogic;

    /** 関連者個人Dto連絡先変換Logic */
    @Autowired
    private ConvertKanrenshaPersonDtoToAccessEntityLogic convertKanrenshaPersonDtoToAccessEntityLogic;

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

        // テーブル更新が必要か判定
        KanrenshaPersonDto personDto = capsuleDto.getKanrenshaPersonDto();
        LeastUserDto userDto = capsuleDto.getUserDto();

        // マスタ最小登録状態の場合は過去履歴が存在しないので履歴に変更処理をしない
        if (0 != personDto.getAccessId()) {
            KanrenshaPersonAccessEntity accessEntity = callForEditPersonAccessEntityLogic.practice(personDto);
            // 変更箇所がなく更新の必要がなければ中断
            if (Objects.isNull(accessEntity)) {
                return 0;
            }
            // 元データを履歴に変更
            setTableDataHistoryUtil.practiceDelete(userDto, accessEntity);
            kanrenshaPersonAccessRepository.save(accessEntity);
        }

        // 新しい履歴を積み上げ
        KanrenshaPersonAccessEntity newSaveEntity = convertKanrenshaPersonDtoToAccessEntityLogic.practice(personDto);
        newSaveEntity.setKanrenshaPersonAccessId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);

        // 保存
        return kanrenshaPersonAccessRepository.save(newSaveEntity).getKanrenshaPersonAccessId();
    }

}
