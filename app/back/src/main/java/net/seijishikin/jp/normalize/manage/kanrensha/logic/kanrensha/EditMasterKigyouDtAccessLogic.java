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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtAccessRepository;

/**
 * 関連者企業団体連絡先マスタ編集Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class EditMasterKigyouDtAccessLogic {

    /** マスタ企業団体連絡先レポジトリ */
    @Autowired
    private KanrenshaKigyouDtAccessRepository kanrenshaKigyouDtAccessRepository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditKigyouDtAccessEntityLogic callForEditKigyouDtAccessEntityLogic;

    /** 関連者企業団体Dto連絡先変換Logic */
    @Autowired
    private ConvertKanrenshaKigyouDtDtoToAccessEntityLogic convertKanrenshaKigyouDtDtoToAccessEntityLogic;

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

        // テーブル更新が必要か判定
        KanrenshaKigyouDtDto kigyoDtDto = capsuleDto.getKanrenshaKigyouDtDto();
        LeastUserDto userDto = capsuleDto.getUserDto();

        // マスタ最小登録状態の場合は過去履歴が存在しないので履歴に変更処理をしない

        if (0 != kigyoDtDto.getAccessId()) {
            KanrenshaKigyouDtAccessEntity oldEntity = callForEditKigyouDtAccessEntityLogic.practice(kigyoDtDto);

            // 変更箇所がなく更新の必要がなければ中断
            if (Objects.isNull(oldEntity)) {
                return 0;
            }
            // 元データを履歴に変更
            setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
            kanrenshaKigyouDtAccessRepository.save(oldEntity);
        }

        // 新しい履歴を積み上げ
        KanrenshaKigyouDtAccessEntity newSaveEntity = convertKanrenshaKigyouDtDtoToAccessEntityLogic
                .practice(kigyoDtDto);
        newSaveEntity.setKanrenshaKigyouDtAccessId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);

        // 保存
        return kanrenshaKigyouDtAccessRepository.save(newSaveEntity).getKanrenshaKigyouDtAccessId();
    }
}
