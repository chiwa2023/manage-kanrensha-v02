package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtMarkEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtMarkRepository;

/**
 * アドレス・ベース・レジストリワークテーブル処理マーク対象ItemReader
 */
@Component
public class WkTblAddressMarkItemReader extends RepositoryItemReader<WkTblAddressRsdtMarkEntity> {

    /**
     * コンストラクタ
     * 
     * @param wkTblAddressRsdtMarkRepository アドレス・ベース・レジストリワークテーブル処理マークRepository
     */
    public WkTblAddressMarkItemReader(final @Autowired WkTblAddressRsdtMarkRepository wkTblAddressRsdtMarkRepository) {

        super();
        super.setRepository(wkTblAddressRsdtMarkRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findByInsertUserCodeAndIsLatestTrue");

        List<Object> list = new ArrayList<>();
        super.setArguments(list); // NOPMD
    }

    /**
     * BeforeStep(ユーザコード指定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        Integer userCode = Math.toIntExact(stepExecution.getJobParameters().getLong("userCode"));

        List<Object> list = new ArrayList<>();
        list.add(userCode);

        super.setArguments(list); // NOPMD
    }

}
