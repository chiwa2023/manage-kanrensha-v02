package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;

/**
 * 郵便番号（その他）を抽出する(郵便番号更新用)ItemReader
 */
@Component
public class UpdatePostalCodeOtherItemReader extends RepositoryItemReader<AddressPostalEntity> {

    /**
     * コンストラクタ
     *
     * @param addressPostalRepository 郵便番号データRespository
     */
    public UpdatePostalCodeOtherItemReader(final @Autowired AddressPostalRepository addressPostalRepository) {
        super();
        super.setRepository(addressPostalRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findByLgCodeStartingWithAndIsLatestTrueAndAddressOrgContaining");

        List<Object> list = new ArrayList<>();
        super.setArguments(list); // NOPMD
    }

    /**
     * 起動条件を設定する
     *
     * @param stepExecution StepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        String lgCodePref = stepExecution.getJobParameters().getString("lgCodePref");

        List<Object> list = new ArrayList<>();
        list.add(lgCodePref);
        list.add("（その他）");
        super.setArguments(list);
    }

}
