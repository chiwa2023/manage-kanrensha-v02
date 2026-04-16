package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_combine_org;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org.CombineOrgCsvProcessor;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_combine.UpdateWkTblCombineOrgCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.GetCombineYearListLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaCombineOrgRepository;

/**
 * 個人企業団体紐づけワークテーブル編集Controller
 */
@Service
public class RegistCombineOrganizationService {

    /** ワークテーブル個人団体紐づけRepository */
    @Autowired
    private WkTblKanrenshaCombineOrgRepository wkTblKanrenshaCombineOrgRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 個人団体紐づけ入力内容Processor */
    @Autowired
    private CombineOrgCsvProcessor combineOrgCsvProcessor;

    /** 個人団体紐づけ登録可能年リスト */
    @Autowired
    private GetCombineYearListLogic getCombineYearListLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集条件Dto
     * @return 新規Id
     */
    public WkTblKanrenshaCombineOrgEntity practice(final UpdateWkTblCombineOrgCapsuleDto capsuleDto) {

        WkTblKanrenshaCombineOrgEntity entityInput = capsuleDto.getWkTblKanrenshaCombineOrgEntity();

        Optional<WkTblKanrenshaCombineOrgEntity> optional = wkTblKanrenshaCombineOrgRepository
                .findById(entityInput.getWkTblKanrenshaCombineOrgId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return new WkTblKanrenshaCombineOrgEntity();
        }

        // 登録作業年を取得してprocessorによるチェックにセット
        List<Short> listYear = getCombineYearListLogic.practice();
        entityInput = combineOrgCsvProcessor.check(entityInput, listYear.getFirst(), listYear.getLast());

        LeastUserDto userDto = capsuleDto.getUserDto();

        WkTblKanrenshaCombineOrgEntity entitySrc = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        wkTblKanrenshaCombineOrgRepository.save(entitySrc);

        entityInput.setWkTblKanrenshaCombineOrgId(0); // 履歴を積むのでauto_increment
        setTableDataHistoryUtil.practiceInsert(userDto, entityInput);

        return wkTblKanrenshaCombineOrgRepository.save(entityInput);
    }

}
