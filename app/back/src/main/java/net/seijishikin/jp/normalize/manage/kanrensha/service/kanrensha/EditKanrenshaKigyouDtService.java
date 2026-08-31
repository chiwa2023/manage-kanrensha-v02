package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.EditMasterKigyouDtAccessLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.EditMasterKigyouDtAddressLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.EditMasterKigyouDtMasterLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.EditMasterKigyouDtPropertyLogic;

/**
 * 関連者企業・団体を編集する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class EditKanrenshaKigyouDtService {

    /** 関連者企業団体マスタ編集Logic */
    @Autowired
    private EditMasterKigyouDtMasterLogic editMasterKigyouDtMasterLogic;

    /** 関連者企業団体連絡先編集Logic */
    @Autowired
    private EditMasterKigyouDtAccessLogic editMasterKigyouDtAccessLogic;

    /** 関連者企業団体住所マスタ編集Logic */
    @Autowired
    private EditMasterKigyouDtAddressLogic editMasterKigyouDtAddressLogic;

    /** 関連者企業団体属性マスタ編集Logic */
    @Autowired
    private EditMasterKigyouDtPropertyLogic editMasterKigyouDtPropertyLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件
     * @return 処理結果
     */
    @Transactional
    public Integer practice(final SaveKanrenshaKigyouDtCapsuleDto capsuleDto)
            throws EmptyResultDataAccessException, ConcurrencyFailureException { // NOPMD UncheckedException

        // マスタを更新
        Integer updateId = editMasterKigyouDtMasterLogic.practice(capsuleDto);

        // 変更履歴を実装することがある場合、紐づけが必要になるのでマスタIdを他テーブルに波及
        if (0 != updateId) {
            capsuleDto.getKanrenshaKigyouDtDto().setMasterId(updateId);
        }

        // 連絡先を更新
        updateId += editMasterKigyouDtAccessLogic.practice(capsuleDto);

        // 住所を更新
        updateId += editMasterKigyouDtAddressLogic.practice(capsuleDto);

        // 属性を更新
        updateId += editMasterKigyouDtPropertyLogic.practice(capsuleDto);

        return updateId;
    }

}
