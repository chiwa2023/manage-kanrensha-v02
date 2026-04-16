package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_std;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_std.KanrenshaKigyouDtAddStdCsvProcessor;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_std.UpdateWkTblStdKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtMasterRepository;

/**
 * ワークテーブルマスタ企業／団体標準編集Service
 */
@Service
public class RegistBulkMasterStdKigyouDtService {

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblKanrenshaKigyouDtMasterRepository wkTblKanrenshaKigyouDtMasterRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 編集内容確認Peocssor */
    @Autowired
    private KanrenshaKigyouDtAddStdCsvProcessor kanrenshaKigyouDtAddStdCsvProcessor;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @Transactional
    public WkTblKanrenshaKigyouDtMasterEntity practice(final UpdateWkTblStdKigyouDtCapsuleDto capsuleDto) {

        WkTblKanrenshaKigyouDtMasterEntity entityInput = capsuleDto.getWkTblKanrenshaKigyouDtMasterEntity();

        Optional<WkTblKanrenshaKigyouDtMasterEntity> optional = wkTblKanrenshaKigyouDtMasterRepository
                .findById(entityInput.getWkTblKanrenshaKigyouDtMasterId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return new WkTblKanrenshaKigyouDtMasterEntity();
        }

        // ユーザさんが変更しないと決断したらデータ整合チェックはしないで意図をそのまま通す
        final String notUseText = "使用しないに変更;";
        if (!notUseText.equals(entityInput.getJudgeReason())) {
            entityInput = kanrenshaKigyouDtAddStdCsvProcessor.check(entityInput);
        }

        LeastUserDto userDto = capsuleDto.getUserDto();

        WkTblKanrenshaKigyouDtMasterEntity entitySrc = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        wkTblKanrenshaKigyouDtMasterRepository.save(entitySrc);

        entityInput.setWkTblKanrenshaKigyouDtMasterId(0); // 履歴を積むのでauto_increment
        setTableDataHistoryUtil.practiceInsert(userDto, entityInput);

        return wkTblKanrenshaKigyouDtMasterRepository.save(entityInput);
    }

}
