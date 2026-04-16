package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.master;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.SeijidantaiDantaiKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallSeijidantaiAccessEntityLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallSeijidantaiPropertyEntityLogic;

/**
 * 関連者政治団体CsvDto変換Processor
 */
@Component
public class DumpMasterSeijidantaiProcessor
        implements ItemProcessor<KanrenshaSeijidantaiMasterEntity, DumpKanrenshaSeijidantaiMasterDto> {

    /** 政治団体属性呼び出しLogic */
    @Autowired
    private CallSeijidantaiPropertyEntityLogic callSeijidantaiPropertyEntityLogic;

    /** 政治団体連絡先呼び出しLogic */
    @Autowired
    private CallSeijidantaiAccessEntityLogic callSeijidantaiAccessEntityLogic;

    /**
     * 変換処理を実行する
     */
    @Override
    public DumpKanrenshaSeijidantaiMasterDto process(final KanrenshaSeijidantaiMasterEntity item) throws Exception {

        DumpKanrenshaSeijidantaiMasterDto dto = new DumpKanrenshaSeijidantaiMasterDto();
        BeanUtils.copyProperties(item, dto);
        dto.setDantaiKbnLabel(SeijidantaiDantaiKbnConstants.getLabel(dto.getDantaiKbn()));
        String kanrenshaCode = item.getSeijidantaiKanrenshaCode();

        KanrenshaSeijidantaiPropertyEntity propertyEntity = callSeijidantaiPropertyEntityLogic.practice(kanrenshaCode);

        // 団体名かな
        dto.setOrgNameKana(propertyEntity.getOrgNameKana());
        // 代表者関連者番号
        dto.setOrgDelegateCode(propertyEntity.getOrgDelegateCode());
        // 会計責任者名
        dto.setAccountMgrName(propertyEntity.getAccountMgrName());
        // 会計責任者関連者番号
        dto.setAccountMgrCode(propertyEntity.getAccountMgrCode());

        KanrenshaSeijidantaiAccessEntity accessEntity = callSeijidantaiAccessEntityLogic.practice(kanrenshaCode);

        // 電話番号
        dto.setPhon(accessEntity.getPhon1() + accessEntity.getPhon2() + accessEntity.getPhon3());
        // 公式代表ページ
        dto.setMyPortalUrl(accessEntity.getMyPortalUrl());

        return dto;
    }

}
