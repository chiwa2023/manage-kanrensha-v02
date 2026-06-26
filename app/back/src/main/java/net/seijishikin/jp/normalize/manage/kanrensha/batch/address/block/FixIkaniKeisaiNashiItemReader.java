package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.infrastructure.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;

/**
 * 以下に掲載がない場合修正ItemReader
 */
@Component
public class FixIkaniKeisaiNashiItemReader extends RepositoryItemReader<AddressPostalEntity> {

    /**
     * コンストラクタ
     *
     * @param addressPostalRepository 郵便番号不規則データRespository
     */
    public FixIkaniKeisaiNashiItemReader(final @Autowired AddressPostalRepository addressPostalRepository) {
        super(addressPostalRepository, new HashMap<>());
        super.setMethodName("findByLgCodeStartingWithAndIsLatestTrueAndAddressNameEndingWith");

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
        list.add("以下に掲載がない場合");
        super.setArguments(list);
    }

}
