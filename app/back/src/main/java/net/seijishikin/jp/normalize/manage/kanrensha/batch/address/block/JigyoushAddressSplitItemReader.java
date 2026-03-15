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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;

/**
 * 事業所で空白があり分離可能でデータを抽出ItemReader
 */
@Component
public class JigyoushAddressSplitItemReader extends RepositoryItemReader<AddressPostalIrregularEntity> {

    /**
     * コンストラクタ
     *
     * @param addressPostalIrregularRepository 郵便番号不規則データRespository
     */
    public JigyoushAddressSplitItemReader(
            final @Autowired AddressPostalIrregularRepository addressPostalIrregularRepository) {
        super();
        super.setRepository(addressPostalIrregularRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findByLgCodeStartingWithAndAddressBlockLikeAndIsLatestTrue");

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
        list.add("%　%");
        super.setArguments(list);
    }

}
