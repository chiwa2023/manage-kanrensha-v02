package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.EditMasterPersonAccessLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.EditMasterPersonAddressLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.EditMasterPersonMasterLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.EditMasterPersonPropertyLogic;

/**
 * 関連者個人を編集する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class EditKanrenshaPersonService {

    /** 関連者個人マスタ編集Logic */
    @Autowired
    private EditMasterPersonMasterLogic editMasterPersonMasterLogic;

    /** 関連者個人連絡先編集Logic */
    @Autowired
    private EditMasterPersonAccessLogic editMasterPersonAccessLogic;

    /** 関連者個人住所マスタ編集Logic */
    @Autowired
    private EditMasterPersonAddressLogic editMasterPersonAddressLogic;

    /** 関連者個人属性マスタ編集Logic */
    @Autowired
    private EditMasterPersonPropertyLogic editMasterPersonPropertyLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件
     * @return 処理結果
     */
    @Transactional
    public Integer practice(final SaveKanrenshaPersonCapsuleDto capsuleDto)
            throws EmptyResultDataAccessException, ConcurrencyFailureException { // NOPMD UncheckedException

        // マスタを更新
        Integer updateId = editMasterPersonMasterLogic.practice(capsuleDto);

        // 変更履歴を実装することがある場合、紐づけが必要になるのでマスタIdを他テーブルに波及
        if (0 != updateId) {
            capsuleDto.getKanrenshaPersonDto().setMasterId(updateId);
        }

        // 連絡先を更新
        updateId += editMasterPersonAccessLogic.practice(capsuleDto);

        // 住所を更新
        updateId += editMasterPersonAddressLogic.practice(capsuleDto);

        // 属性を更新
        updateId += editMasterPersonPropertyLogic.practice(capsuleDto);

        return updateId;
    }

}
