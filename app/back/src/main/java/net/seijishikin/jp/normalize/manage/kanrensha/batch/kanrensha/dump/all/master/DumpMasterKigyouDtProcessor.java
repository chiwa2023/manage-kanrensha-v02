package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.master;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallKigyouDtAccessEntityLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallKigyouDtPropertyEntityLogic;

/**
 * 関連者企業・団体CsvDto変換Processor TODO 管理テーブルが増えたら、ここで必要な値を補う
 * 現時点ではentityをそのままDtoに変換しているだけなので無駄に見えるが、拡張性を考慮した措置
 */
@Component
public class DumpMasterKigyouDtProcessor
        implements ItemProcessor<KanrenshaKigyouDtMasterEntity, DumpKanrenshaKigyouDtMasterDto> {

    /** 企業団体属性呼び出しLogic */
    @Autowired
    private CallKigyouDtPropertyEntityLogic callKigyouDtPropertyEntityLogic;

    /** 企業団体連絡先呼び出しLogic */
    @Autowired
    private CallKigyouDtAccessEntityLogic callKigyouDtAccessEntityLogic;

    /**
     * 変換処理を実行する
     */
    @Override
    public DumpKanrenshaKigyouDtMasterDto process(final KanrenshaKigyouDtMasterEntity item) throws Exception {

        DumpKanrenshaKigyouDtMasterDto dto = new DumpKanrenshaKigyouDtMasterDto();
        BeanUtils.copyProperties(item, dto);
        String kanrenshaCode = item.getKigyouDtKanrenshaCode();

        KanrenshaKigyouDtPropertyEntity propertyEntity = callKigyouDtPropertyEntityLogic.practice(kanrenshaCode);
        // 団体名かな
        dto.setOrgNameKana(propertyEntity.getOrgNameKana());
        // 代表者関連者番号
        dto.setOrgDelegateCode(propertyEntity.getOrgDelegateCode());
        // 代表者関連コード
        dto.setOrgDelegateCode(propertyEntity.getOrgDelegateCode());

        KanrenshaKigyouDtAccessEntity accessEntity = callKigyouDtAccessEntityLogic.practice(kanrenshaCode);
        // 電話番号
        dto.setPhon(accessEntity.getPhon1() + accessEntity.getPhon2() + accessEntity.getPhon3());
        // 公式代表ページ
        dto.setMyPortalUrl(accessEntity.getMyPortalUrl());

        return dto;
    }

}
