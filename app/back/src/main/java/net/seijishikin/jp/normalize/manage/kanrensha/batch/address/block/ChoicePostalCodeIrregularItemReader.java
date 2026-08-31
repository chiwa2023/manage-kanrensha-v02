package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.infrastructure.item.data.RepositoryItemReader;
import org.springframework.batch.core.annotation.BeforeStep;
//import org.springframework.batch.infrastructure.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;

/**
 * 不規則未編集郵便番号データ抽出itemreader
 */
@Component
public class ChoicePostalCodeIrregularItemReader extends RepositoryItemReader<AddressPostalIrregularEntity> {

    /**
     * コンストラクタ
     *
     * @param addressPostalIrregularRepository 郵便番号不規則データRespository
     */
    public ChoicePostalCodeIrregularItemReader(
            final @Autowired AddressPostalIrregularRepository addressPostalIrregularRepository) {
        super(addressPostalIrregularRepository, new HashMap<>());
        super.setMethodName("findByLgCodeStartingWithAndAddressOrgLikeAndAddressOrgNotLikeAndIsLatestTrue");

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
        list.add("%〜%");
        list.add("%、%");
        super.setArguments(list);
    }

}
