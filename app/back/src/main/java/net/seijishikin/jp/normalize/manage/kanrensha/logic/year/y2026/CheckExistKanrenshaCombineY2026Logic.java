package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.YearOptionEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.KanrenshaCombineOrg2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.KanrenshaCombineOrg2026Repository;

/**
 * 関連者紐づけ登録有無選択肢取得Logic(2026)
 */
@Component
public class CheckExistKanrenshaCombineY2026Logic {

    /** 登録年(2026) */
    private static final int YEAR = 2026;

    /** 個人団体紐づけRepository(2026) */
    @Autowired
    private KanrenshaCombineOrg2026Repository kanrenshaCombineOrg2026Repository;

    /**
     * 処理を行う
     * 
     * @param personCode   個人関連者コード
     * @param orgCode      組織個人関連者コード
     * @param kanrenshaKbn 関連者区分
     * @return 年選択肢
     */
    public YearOptionEntity practice(final String personCode, final String orgCode, final Short kanrenshaKbn) {

        // 編集対象のデータが存在する確認
        List<KanrenshaCombineOrg2026Entity> list = kanrenshaCombineOrg2026Repository
                .findByPersonKanrenshaCodeAndOrgKanrenshaCodeAndKanrenshaKbnAndIsLatestTrue(personCode, orgCode,
                        kanrenshaKbn);

        YearOptionEntity entity = new YearOptionEntity();
        entity.setSelectedYear(YEAR);
        entity.setIsSelected(!list.isEmpty());

        return entity;
    }
}
