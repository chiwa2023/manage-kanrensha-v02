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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAccessRepository;

/**
 * 関連者政治団体連絡先マスタ編集Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class EditMasterSeijidantaiAccessLogic {

    /** マスタ政治団体連絡先レポジトリ */
    @Autowired
    private KanrenshaSeijidantaiAccessRepository kanrenshaSeijidantaiAccessRepository;

    /** 編集元Entity呼び出しLogic */
    @Autowired
    private CallForEditSeijidantaiAccessEntityLogic callForEditSeijidantaiAccessEntityLogic;

    /** 関連者政治団体Dto連絡先変換Logic */
    @Autowired
    private ConvertKanrenshaSeijidantaiDtoToAccessEntityLogic convertKanrenshaSeijidantaiDtoToAccessEntityLogic;

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

        // テーブル更新が必要か判定
        KanrenshaSeijidantaiDto poliOrgDto = capsuleDto.getKanrenshaSeijidantaiDto();
        LeastUserDto userDto = capsuleDto.getUserDto();

        // マスタ最小登録状態の場合は過去履歴が存在しないので履歴に変更処理をしない
        if (0 != poliOrgDto.getAccessId()) {
            KanrenshaSeijidantaiAccessEntity accessEntity = callForEditSeijidantaiAccessEntityLogic
                    .practice(poliOrgDto);
            // 変更箇所がなく更新の必要がなければ中断
            if (Objects.isNull(accessEntity)) {
                return 0;
            }
            // 元データを履歴に変更
            setTableDataHistoryUtil.practiceDelete(userDto, accessEntity);
            kanrenshaSeijidantaiAccessRepository.save(accessEntity);
        }

        // 新しい履歴を積み上げ
        KanrenshaSeijidantaiAccessEntity newSaveEntity = convertKanrenshaSeijidantaiDtoToAccessEntityLogic
                .practice(poliOrgDto);
        newSaveEntity.setKanrenshaSeijidantaiAccessId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, newSaveEntity);

        // 保存
        return kanrenshaSeijidantaiAccessRepository.save(newSaveEntity).getKanrenshaSeijidantaiAccessId();
    }

}
