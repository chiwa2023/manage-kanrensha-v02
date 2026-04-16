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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressAllCityEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressAllCityRepository;

/**
 * 地方行政区コード更新ワークテーブルItemReader
 */
@Component
public class AllCityWkTblItemReader extends RepositoryItemReader<AddressAllCityEntity> {

    /**
     * コンストラクタ
     * 
     * @param addressAllCityRepository 全市町村Repository
     */
    public AllCityWkTblItemReader(final @Autowired AddressAllCityRepository addressAllCityRepository) {

        super();
        super.setRepository(addressAllCityRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findAllCityNotIn");

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
