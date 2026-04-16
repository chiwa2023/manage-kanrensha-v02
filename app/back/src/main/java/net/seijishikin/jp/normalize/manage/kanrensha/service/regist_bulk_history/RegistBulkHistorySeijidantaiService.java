package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_history;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.history.KanrenshaSeijidantaiHistoryProcessor;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_history.UpdateWkTblHistorySeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiHistoryRepository;

/**
 * ワークテーブルマスタ企業／団体履歴編集Service
 */
@Service
public class RegistBulkHistorySeijidantaiService {

    /** ワークテーブルマスタ企業／団体履歴Repository */
    @Autowired
    private WkTblKanrenshaSeijidantaiHistoryRepository wkTblKanrenshaSeijidantaiHistoryRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 編集内容確認Peocssor */
    @Autowired
    private KanrenshaSeijidantaiHistoryProcessor kanrenshaSeijidantaiHistoryProcessor;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @Transactional
    public WkTblKanrenshaSeijidantaiHistoryEntity practice(final UpdateWkTblHistorySeijidantaiCapsuleDto capsuleDto) {

        WkTblKanrenshaSeijidantaiHistoryEntity entityInput = capsuleDto.getWkTblKanrenshaSeijidantaiHistoryEntity();

        Optional<WkTblKanrenshaSeijidantaiHistoryEntity> optional = wkTblKanrenshaSeijidantaiHistoryRepository
                .findById(entityInput.getWkKanrenshaSeijidantaiHistoryId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return new WkTblKanrenshaSeijidantaiHistoryEntity();
        }

        // ユーザさんが変更しないと決断したらデータ整合チェックはしないで意図をそのまま通す
        final String notUseText = "使用しないに変更;";
        if (!notUseText.equals(entityInput.getJudgeReason())) {
            entityInput = kanrenshaSeijidantaiHistoryProcessor.check(entityInput);
        }

        LeastUserDto userDto = capsuleDto.getUserDto();

        WkTblKanrenshaSeijidantaiHistoryEntity entitySrc = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        wkTblKanrenshaSeijidantaiHistoryRepository.save(entitySrc);

        entityInput.setWkKanrenshaSeijidantaiHistoryId(0); // 履歴を積むのでauto_increment
        setTableDataHistoryUtil.practiceInsert(userDto, entityInput);

        return wkTblKanrenshaSeijidantaiHistoryRepository.save(entityInput);
    }

}
