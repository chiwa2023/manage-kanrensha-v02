package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.AddHistoryKanrenshaSeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiHistoryBaseEntity;

/**
 * 関連者政治団体履歴追加Service
 */
@Service
public class AddKanrenshaHistorySeijidantaiService {

    /** 関連者政治団体履歴を挿入Service */
    @Autowired
    private InsertKanrenshaSeijidantaiHistoryService insertKanrenshaSeijidantaiHistoryService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 関連者政治団体履歴追加Dto
     * @return 保存Id
     */
    @Transactional
    public int practice(final AddHistoryKanrenshaSeijidantaiCapsuleDto capsuleDto) {

        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        BeanUtils.copyProperties(capsuleDto.getEditDto(), baseEntity);

        return insertKanrenshaSeijidantaiHistoryService.practice(capsuleDto.getUserDto(), baseEntity);
    }

}
