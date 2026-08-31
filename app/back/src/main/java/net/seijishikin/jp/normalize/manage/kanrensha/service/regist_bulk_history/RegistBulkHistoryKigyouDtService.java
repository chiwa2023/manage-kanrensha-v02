package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_history;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.history.KanrenshaKigyouDtHistoryProcessor;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_history.UpdateWkTblHistoryKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtHistoryRepository;

/**
 * ワークテーブルマスタ企業／団体履歴編集Service
 */
@Service
public class RegistBulkHistoryKigyouDtService {

    /** ワークテーブルマスタ企業／団体履歴Repository */
    @Autowired
    private WkTblKanrenshaKigyouDtHistoryRepository wkTblKanrenshaKigyouDtHistoryRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 編集内容確認Peocssor */
    @Autowired
    private KanrenshaKigyouDtHistoryProcessor kanrenshaKigyouDtHistoryProcessor;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @Transactional
    public WkTblKanrenshaKigyouDtHistoryEntity practice(final UpdateWkTblHistoryKigyouDtCapsuleDto capsuleDto) {

        WkTblKanrenshaKigyouDtHistoryEntity entityInput = capsuleDto.getWkTblKanrenshaKigyouDtHistoryEntity();

        Optional<WkTblKanrenshaKigyouDtHistoryEntity> optional = wkTblKanrenshaKigyouDtHistoryRepository
                .findById(entityInput.getWkKanrenshaKigyouDtHistoryId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return new WkTblKanrenshaKigyouDtHistoryEntity();
        }

        // ユーザさんが変更しないと決断したらデータ整合チェックはしないで意図をそのまま通す
        final String notUseText = "使用しないに変更;";
        if (!notUseText.equals(entityInput.getJudgeReason())) {
            entityInput = kanrenshaKigyouDtHistoryProcessor.check(entityInput);
        }

        LeastUserDto userDto = capsuleDto.getUserDto();

        WkTblKanrenshaKigyouDtHistoryEntity entitySrc = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        wkTblKanrenshaKigyouDtHistoryRepository.save(entitySrc);

        entityInput.setWkKanrenshaKigyouDtHistoryId(0); // 履歴を積むのでauto_increment
        setTableDataHistoryUtil.practiceInsert(userDto, entityInput);

        return wkTblKanrenshaKigyouDtHistoryRepository.save(entityInput);
    }

}
