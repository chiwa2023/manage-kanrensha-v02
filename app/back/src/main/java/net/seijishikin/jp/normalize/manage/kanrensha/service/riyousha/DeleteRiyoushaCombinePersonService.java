package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.RiyoushaCombinePersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaCombineOrgRepository;

/**
 * 利用者組織個人紐づけ削除Service
 */
@Service
public class DeleteRiyoushaCombinePersonService {

    /** 利用者組織紐づけ個人Respoitory */
    @Autowired
    private RiyoushaCombineOrgRepository riyoushaCombineOrgRepository;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param capsuleDto 削除条件Dto
     * @return 処理結果Dto
     */
    public FrameworkMessageAndResultDto practice(final RiyoushaCombinePersonCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();

        Optional<RiyoushaCombineOrgEntity> optional = riyoushaCombineOrgRepository
                .findById(capsuleDto.getCombineEntity().getRiyoushaCombineOrgId());
        if (optional.isEmpty()) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("指定された個人を削除できませんでした。");
            return resultDto;
        }

        RiyoushaCombineOrgEntity entityDelete = optional.get();
        setTableDataHistoryUtil.practiceDelete(capsuleDto.getUserDto(), entityDelete);

        riyoushaCombineOrgRepository.save(entityDelete);
        resultDto.setMessage("正常に削除できました");

        return resultDto;
    }

}
