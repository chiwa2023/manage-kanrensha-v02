package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_min;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_min.KanrenshaPersonAddMiniCsvProcessor;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_min.UpdateWkTblMinPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonAddMinRepository;

/**
 * ワークテーブルマスタ企業／団体最小編集Service
 */
@Service
public class RegistBulkMasterMinPersonService {

    /** ワークテーブルマスタ企業／団体最小Repository */
    @Autowired
    private WkTblKanrenshaPersonAddMinRepository wkTblKanrenshaPersonAddMinRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 編集内容確認Peocssor */
    @Autowired
    private KanrenshaPersonAddMiniCsvProcessor kanrenshaPersonAddMiniCsvProcessor;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @Transactional
    public WkTblKanrenshaPersonAddMinEntity practice(final UpdateWkTblMinPersonCapsuleDto capsuleDto) {

        WkTblKanrenshaPersonAddMinEntity entityInput = capsuleDto.getWkTblKanrenshaPersonAddMinEntity();

        Optional<WkTblKanrenshaPersonAddMinEntity> optional = wkTblKanrenshaPersonAddMinRepository
                .findById(entityInput.getWkTblKanrenshaPersonAddMinId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return new WkTblKanrenshaPersonAddMinEntity();
        }

        // ユーザさんが変更しないと決断したらデータ整合チェックはしないで意図をそのまま通す
        final String notUseText = "使用しないに変更;";
        if (!notUseText.equals(entityInput.getJudgeReason())) {
            entityInput = kanrenshaPersonAddMiniCsvProcessor.check(entityInput);
        }

        LeastUserDto userDto = capsuleDto.getUserDto();

        WkTblKanrenshaPersonAddMinEntity entitySrc = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        wkTblKanrenshaPersonAddMinRepository.save(entitySrc);

        entityInput.setWkTblKanrenshaPersonAddMinId(0); // 履歴を積むのでauto_increment
        setTableDataHistoryUtil.practiceInsert(userDto, entityInput);

        return wkTblKanrenshaPersonAddMinRepository.save(entityInput);
    }

}
