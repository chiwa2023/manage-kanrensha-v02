package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.master;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallPersonAccessEntityLogic;

/**
 * 関連者個人CsvDto変換Processor
 */
@Component
public class DumpMasterPersonProcessor
        implements ItemProcessor<KanrenshaPersonMasterEntity, DumpKanrenshaPersonMasterDto> {

    /** 個人連絡先呼び出しLogic */
    @Autowired
    private CallPersonAccessEntityLogic callPersonAccessEntityLogic;

    /**
     * 変換処理を実行する
     */
    @Override
    public DumpKanrenshaPersonMasterDto process(final KanrenshaPersonMasterEntity item) throws Exception {

        DumpKanrenshaPersonMasterDto dto = new DumpKanrenshaPersonMasterDto();
        BeanUtils.copyProperties(item, dto);

        KanrenshaPersonAccessEntity accessEntity = callPersonAccessEntityLogic.practice(item.getPersonKanrenshaCode());

        // 電話番号
        dto.setPhon(accessEntity.getPhon1() + accessEntity.getPhon2() + accessEntity.getPhon3());
        // 公式代表ページ
        dto.setMyPortalUrl(accessEntity.getMyPortalUrl());

        return dto;
    }

}
