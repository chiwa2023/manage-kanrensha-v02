package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.AddHistoryKanrenshaKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtHistoryBaseEntity;

/**
 * 関連者企業・団体履歴追加Service
 */
@Service
public class AddKanrenshaHistoryKigyouDtService {

    /** 関連者企業・団体履歴を挿入Service */
    @Autowired
    private InsertKanrenshaKigyouDtHistoryService insertKanrenshaKigyouDtHistoryService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 関連者企業・団体履歴追加Dto
     * @return 保存Id
     */
    @Transactional
    public int practice(final AddHistoryKanrenshaKigyouDtCapsuleDto capsuleDto) {

        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        BeanUtils.copyProperties(capsuleDto.getEditDto(), baseEntity);

        return insertKanrenshaKigyouDtHistoryService.practice(capsuleDto.getUserDto(), baseEntity);
    }

}
