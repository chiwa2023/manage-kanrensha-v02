package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.YearOptionEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2027.KanrenshaCombineOrg2027Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2027.KanrenshaCombineOrg2027Repository;

/**
 * 関連者紐づけ登録有無選択肢取得Logic(2027)
 */
@Component
public class CheckExistKanrenshaCombineY2027Logic {

    /** 登録年(2027) */
    private static final int YEAR = 2027;

    /** 個人団体紐づけRepository(2027) */
    @Autowired
    private KanrenshaCombineOrg2027Repository kanrenshaCombineOrg2027Repository;

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
        List<KanrenshaCombineOrg2027Entity> list = kanrenshaCombineOrg2027Repository
                .findByPersonKanrenshaCodeAndOrgKanrenshaCodeAndKanrenshaKbnAndIsLatestTrue(personCode, orgCode,
                        kanrenshaKbn);

        YearOptionEntity entity = new YearOptionEntity();
        entity.setSelectedYear(YEAR);
        entity.setIsSelected(!list.isEmpty());

        return entity;
    }
}
