package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_std;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_std.KanrenshaPersonAddStdCsvProcessor;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_std.UpdateWkTblStdPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonMasterRepository;

/**
 * ワークテーブルマスタ企業／団体標準編集Service
 */
@Service
public class RegistBulkMasterStdPersonService {

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblKanrenshaPersonMasterRepository wkTblKanrenshaPersonMasterRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 編集内容確認Peocssor */
    @Autowired
    private KanrenshaPersonAddStdCsvProcessor kanrenshaPersonAddStdCsvProcessor;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @Transactional
    public WkTblKanrenshaPersonMasterEntity practice(final UpdateWkTblStdPersonCapsuleDto capsuleDto) {

        WkTblKanrenshaPersonMasterEntity entityInput = capsuleDto.getWkTblKanrenshaPersonMasterEntity();

        Optional<WkTblKanrenshaPersonMasterEntity> optional = wkTblKanrenshaPersonMasterRepository
                .findById(entityInput.getWkTblKanrenshaPersonMasterId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return new WkTblKanrenshaPersonMasterEntity();
        }

        // ユーザさんが変更しないと決断したらデータ整合チェックはしないで意図をそのまま通す
        final String notUseText = "使用しないに変更;";
        if (!notUseText.equals(entityInput.getJudgeReason())) {
            entityInput = kanrenshaPersonAddStdCsvProcessor.check(entityInput);
        }

        LeastUserDto userDto = capsuleDto.getUserDto();

        WkTblKanrenshaPersonMasterEntity entitySrc = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        wkTblKanrenshaPersonMasterRepository.save(entitySrc);

        entityInput.setWkTblKanrenshaPersonMasterId(0); // 履歴を積むのでauto_increment
        setTableDataHistoryUtil.practiceInsert(userDto, entityInput);

        return wkTblKanrenshaPersonMasterRepository.save(entityInput);
    }

}
