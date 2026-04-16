package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_std;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_std.KanrenshaSeijidantaiAddStdCsvProcessor;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_std.UpdateWkTblStdSeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiMasterRepository;

/**
 * ワークテーブルマスタ企業／団体標準編集Service
 */
@Service
public class RegistBulkMasterStdSeijidantaiService {

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblKanrenshaSeijidantaiMasterRepository wkTblKanrenshaSeijidantaiMasterRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 編集内容確認Peocssor */
    @Autowired
    private KanrenshaSeijidantaiAddStdCsvProcessor kanrenshaSeijidantaiAddStdCsvProcessor;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @Transactional
    public WkTblKanrenshaSeijidantaiMasterEntity practice(final UpdateWkTblStdSeijidantaiCapsuleDto capsuleDto) {

        WkTblKanrenshaSeijidantaiMasterEntity entityInput = capsuleDto.getWkTblKanrenshaSeijidantaiMasterEntity();

        Optional<WkTblKanrenshaSeijidantaiMasterEntity> optional = wkTblKanrenshaSeijidantaiMasterRepository
                .findById(entityInput.getWkTblKanrenshaSeijidantaiMasterId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return new WkTblKanrenshaSeijidantaiMasterEntity();
        }

        // ユーザさんが変更しないと決断したらデータ整合チェックはしないで意図をそのまま通す
        final String notUseText = "使用しないに変更;";
        if (!notUseText.equals(entityInput.getJudgeReason())) {
            entityInput = kanrenshaSeijidantaiAddStdCsvProcessor.check(entityInput);
        }

        LeastUserDto userDto = capsuleDto.getUserDto();

        WkTblKanrenshaSeijidantaiMasterEntity entitySrc = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        wkTblKanrenshaSeijidantaiMasterRepository.save(entitySrc);

        entityInput.setWkTblKanrenshaSeijidantaiMasterId(0); // 履歴を積むのでauto_increment
        setTableDataHistoryUtil.practiceInsert(userDto, entityInput);

        return wkTblKanrenshaSeijidantaiMasterRepository.save(entityInput);
    }

}
