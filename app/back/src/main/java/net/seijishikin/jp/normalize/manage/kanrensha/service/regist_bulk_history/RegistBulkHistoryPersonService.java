package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_history;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.history.KanrenshaPersonHistoryProcessor;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_history.UpdateWkTblHistoryPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonHistoryRepository;

/**
 * ワークテーブルマスタ企業／団体履歴編集Service
 */
@Service
public class RegistBulkHistoryPersonService {

    /** ワークテーブルマスタ企業／団体履歴Repository */
    @Autowired
    private WkTblKanrenshaPersonHistoryRepository wkTblKanrenshaPersonHistoryRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 編集内容確認Peocssor */
    @Autowired
    private KanrenshaPersonHistoryProcessor kanrenshaPersonHistoryProcessor;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @Transactional
    public WkTblKanrenshaPersonHistoryEntity practice(final UpdateWkTblHistoryPersonCapsuleDto capsuleDto) {

        WkTblKanrenshaPersonHistoryEntity entityInput = capsuleDto.getWkTblKanrenshaPersonHistoryEntity();

        Optional<WkTblKanrenshaPersonHistoryEntity> optional = wkTblKanrenshaPersonHistoryRepository
                .findById(entityInput.getWkKanrenshaPersonHistoryId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return new WkTblKanrenshaPersonHistoryEntity();
        }

        // ユーザさんが変更しないと決断したらデータ整合チェックはしないで意図をそのまま通す
        final String notUseText = "使用しないに変更;";
        if (!notUseText.equals(entityInput.getJudgeReason())) {
            entityInput = kanrenshaPersonHistoryProcessor.check(entityInput);
        }

        LeastUserDto userDto = capsuleDto.getUserDto();

        WkTblKanrenshaPersonHistoryEntity entitySrc = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        wkTblKanrenshaPersonHistoryRepository.save(entitySrc);

        entityInput.setWkKanrenshaPersonHistoryId(0); // 履歴を積むのでauto_increment
        setTableDataHistoryUtil.practiceInsert(userDto, entityInput);

        return wkTblKanrenshaPersonHistoryRepository.save(entityInput);
    }

}
