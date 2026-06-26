package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.infrastructure.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalCommonRepository;

/**
 * 登録された郵便番号作業を全抽出する
 */
@Component
public class WorksPostalItemReader extends RepositoryItemReader<WkTblPostalCommonEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblPostalCommonRepository 郵便番号作業Respository
     */
    public WorksPostalItemReader(final @Autowired WkTblPostalCommonRepository wkTblPostalCommonRepository) {

        super(wkTblPostalCommonRepository, new HashMap<>());
        super.setMethodName("findAll");

        List<Object> list = new ArrayList<>();
        super.setArguments(list); // NOPMD
    }

}
