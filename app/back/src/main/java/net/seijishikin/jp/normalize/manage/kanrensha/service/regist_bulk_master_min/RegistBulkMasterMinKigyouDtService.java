package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_min;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min.KanrenshaKigyouDtAddMiniCsvProcessor;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_min.UpdateWkTblMinKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtAddMinRepository;

/**
 * ワークテーブルマスタ企業／団体最小編集Service
 */
@Service
public class RegistBulkMasterMinKigyouDtService {

    /** ワークテーブルマスタ企業／団体最小Repository */
    @Autowired
    private WkTblKanrenshaKigyouDtAddMinRepository wkTblKanrenshaKigyouDtAddMinRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 編集内容確認Peocssor */
    @Autowired
    private KanrenshaKigyouDtAddMiniCsvProcessor kanrenshaKigyouDtAddMiniCsvProcessor;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @Transactional
    public WkTblKanrenshaKigyouDtAddMinEntity practice(final UpdateWkTblMinKigyouDtCapsuleDto capsuleDto) {

        WkTblKanrenshaKigyouDtAddMinEntity entityInput = capsuleDto.getWkTblKanrenshaKigyouDtAddMinEntity();

        Optional<WkTblKanrenshaKigyouDtAddMinEntity> optional = wkTblKanrenshaKigyouDtAddMinRepository
                .findById(entityInput.getWkTblKanrenshaKigyouDtAddMinId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return new WkTblKanrenshaKigyouDtAddMinEntity();
        }

        // ユーザさんが変更しないと決断したらデータ整合チェックはしないで意図をそのまま通す
        final String notUseText = "使用しないに変更;";
        if (!notUseText.equals(entityInput.getJudgeReason())) {
            entityInput = kanrenshaKigyouDtAddMiniCsvProcessor.check(entityInput);
        }

        LeastUserDto userDto = capsuleDto.getUserDto();

        WkTblKanrenshaKigyouDtAddMinEntity entitySrc = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        wkTblKanrenshaKigyouDtAddMinRepository.save(entitySrc);

        entityInput.setWkTblKanrenshaKigyouDtAddMinId(0); // 履歴を積むのでauto_increment
        setTableDataHistoryUtil.practiceInsert(userDto, entityInput);

        return wkTblKanrenshaKigyouDtAddMinRepository.save(entityInput);
    }

}
