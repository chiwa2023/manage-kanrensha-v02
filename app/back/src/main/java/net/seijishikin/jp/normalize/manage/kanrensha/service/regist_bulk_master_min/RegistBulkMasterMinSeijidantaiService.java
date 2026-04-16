package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_min;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min.KanrenshaSeijidantaiAddMiniCsvProcessor;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_min.UpdateWkTblMinSeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiAddMinRepository;

/**
 * ワークテーブルマスタ企業／団体最小編集Service
 */
@Service
public class RegistBulkMasterMinSeijidantaiService {

    /** ワークテーブルマスタ企業／団体最小Repository */
    @Autowired
    private WkTblKanrenshaSeijidantaiAddMinRepository wkTblKanrenshaSeijidantaiAddMinRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 編集内容確認Peocssor */
    @Autowired
    private KanrenshaSeijidantaiAddMiniCsvProcessor kanrenshaSeijidantaiAddMiniCsvProcessor;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @Transactional
    public WkTblKanrenshaSeijidantaiAddMinEntity practice(final UpdateWkTblMinSeijidantaiCapsuleDto capsuleDto) {

        WkTblKanrenshaSeijidantaiAddMinEntity entityInput = capsuleDto.getWkTblKanrenshaSeijidantaiAddMinEntity();

        Optional<WkTblKanrenshaSeijidantaiAddMinEntity> optional = wkTblKanrenshaSeijidantaiAddMinRepository
                .findById(entityInput.getWkTblKanrenshaSeijidantaiAddMinId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return new WkTblKanrenshaSeijidantaiAddMinEntity();
        }

        // ユーザさんが変更しないと決断したらデータ整合チェックはしないで意図をそのまま通す
        final String notUseText = "使用しないに変更;";
        if (!notUseText.equals(entityInput.getJudgeReason())) {
            entityInput = kanrenshaSeijidantaiAddMiniCsvProcessor.check(entityInput);
        }

        LeastUserDto userDto = capsuleDto.getUserDto();

        WkTblKanrenshaSeijidantaiAddMinEntity entitySrc = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        wkTblKanrenshaSeijidantaiAddMinRepository.save(entitySrc);

        entityInput.setWkTblKanrenshaSeijidantaiAddMinId(0); // 履歴を積むのでauto_increment
        setTableDataHistoryUtil.practiceInsert(userDto, entityInput);

        return wkTblKanrenshaSeijidantaiAddMinRepository.save(entityInput);
    }

}
