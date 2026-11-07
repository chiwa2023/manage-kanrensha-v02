package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.YearOptionEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2023.KanrenshaCombineOrg2023Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2023.KanrenshaCombineOrg2023Repository;

/**
 * 関連者紐づけ登録有無選択肢取得Logic(2023)
 */
@Component
public class CheckExistKanrenshaCombineY2023Logic {

    /** 登録年(2023) */
    private static final int YEAR = 2023;

    /** 個人団体紐づけRepository(2023) */
    @Autowired
    private KanrenshaCombineOrg2023Repository kanrenshaCombineOrg2023Repository;

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
        List<KanrenshaCombineOrg2023Entity> list = kanrenshaCombineOrg2023Repository
                .findByPersonKanrenshaCodeAndOrgKanrenshaCodeAndKanrenshaKbnAndIsLatestTrue(personCode, orgCode,
                        kanrenshaKbn);

        YearOptionEntity entity = new YearOptionEntity();
        entity.setSelectedYear(YEAR);
        entity.setIsSelected(!list.isEmpty());

        return entity;
    }
}
