package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaSeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.EditMasterSeijidantaiAccessLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.EditMasterSeijidantaiAddressLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.EditMasterSeijidantaiMasterLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.EditMasterSeijidantaiPropertyLogic;

/**
 * 関連者政治団体を編集する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class EditKanrenshaSeijidantaiService {

    /** 関連者政治団体マスタ編集Logic */
    @Autowired
    private EditMasterSeijidantaiMasterLogic editMasterSeijidantaiLogic;

    /** 関連者政治団体連絡先編集Logic */
    @Autowired
    private EditMasterSeijidantaiAccessLogic editMasterSeijidantaiAccessLogic;

    /** 関連者政治団体住所マスタ編集Logic */
    @Autowired
    private EditMasterSeijidantaiAddressLogic editMasterSeijidantaiAddressLogic;

    /** 関連者政治団体属性マスタ編集Logic */
    @Autowired
    private EditMasterSeijidantaiPropertyLogic editMasterSeijidantaiPropertyLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件
     * @return 処理結果
     */
    @Transactional
    public Integer practice(final SaveKanrenshaSeijidantaiCapsuleDto capsuleDto)
            throws EmptyResultDataAccessException, ConcurrencyFailureException { // NOPMD UncheckedException

        // マスタを更新
        Integer updateId = editMasterSeijidantaiLogic.practice(capsuleDto);

        // 変更履歴を実装することがある場合、紐づけが必要になるのでマスタIdを他テーブルに波及
        if (0 != updateId) {
            capsuleDto.getKanrenshaSeijidantaiDto().setMasterId(updateId);
        }

        // 連絡先を更新
        updateId += editMasterSeijidantaiAccessLogic.practice(capsuleDto);

        // 住所を更新
        updateId += editMasterSeijidantaiAddressLogic.practice(capsuleDto);

        // 属性を更新
        updateId += editMasterSeijidantaiPropertyLogic.practice(capsuleDto);

        return updateId;
    }

}
