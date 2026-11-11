package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.AddHistoryKanrenshaPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonHistoryBaseEntity;

/**
 * 関連者個人履歴追加Service
 */
@Service
public class AddKanrenshaHistoryPersonService {

    /** 関連者個人履歴を挿入Service */
    @Autowired
    private InsertKanrenshaPersonHistoryService insertKanrenshaPersonHistoryService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 関連者個人履歴追加Dto
     * @return 保存Id
     */
    @Transactional
    public int practice(final AddHistoryKanrenshaPersonCapsuleDto capsuleDto) {

        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        BeanUtils.copyProperties(capsuleDto.getEditDto(), baseEntity);

        return insertKanrenshaPersonHistoryService.practice(capsuleDto.getUserDto(), baseEntity);
    }

}
