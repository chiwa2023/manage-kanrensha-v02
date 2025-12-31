package net.seijishikin.jp.normalize.manage.kanrensha.service.works_approval;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaAddressBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.works_apploval.UpdateApprovalKanrenshaKigyouDtAddressLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.works_apploval.UpdateApprovalKanrenshaPersonAddressLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.works_apploval.UpdateApprovalKanrenshaSeijidantaiAddressLogic;

/**
 * 住所承認作業保存Service
 */
@Service
public class SaveApprovalAddressService {

    /** 作業承認更新関連者個人Logic */
    @Autowired
    private UpdateApprovalKanrenshaPersonAddressLogic updateApprovalKanrenshaPersonAddressLogic;

    /** 作業承認更新関連者個人Logic */
    @Autowired
    private UpdateApprovalKanrenshaKigyouDtAddressLogic updateApprovalKanrenshaKigyouDtAddressLogic;

    /** 作業承認更新関連者個人Logic */
    @Autowired
    private UpdateApprovalKanrenshaSeijidantaiAddressLogic updateApprovalKanrenshaSeijidantaiAddressLogic;

    /**
     * 処理を行う
     *
     * @param listAddress 承認作業住所リスト
     * @param userDto     ユーザ最小限Dto
     * @return 更新行数
     */
    public Integer practice(final List<KanrenshaAddressBaseEntity> listAddress, final LeastUserDto userDto) {

        int updateCount = 0;

        for (KanrenshaAddressBaseEntity entity : listAddress) {

            switch (entity.getKanrenshaKbn()) {

                case KanrenshaKbnConstants.PERSON:
                    updateCount += updateApprovalKanrenshaPersonAddressLogic.practice(entity, userDto);
                    break;

                case KanrenshaKbnConstants.KIGYOU_DT:
                    updateCount += updateApprovalKanrenshaKigyouDtAddressLogic.practice(entity, userDto);
                    break;

                case KanrenshaKbnConstants.SEIJIDANTAI:
                    updateCount += updateApprovalKanrenshaSeijidantaiAddressLogic.practice(entity, userDto);
                    break;

                default:
                    throw new IllegalArgumentException("関連者区分が指定の値ではありません");
            }
        }

        return updateCount;
    }
}
