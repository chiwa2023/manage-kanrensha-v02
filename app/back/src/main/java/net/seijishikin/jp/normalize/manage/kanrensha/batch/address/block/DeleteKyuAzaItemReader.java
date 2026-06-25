package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.infrastructure.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalRepairLogEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepairLogRepository;

/**
 * 旧字削除ItemReader
 */
@Component
public class DeleteKyuAzaItemReader extends RepositoryItemReader<AddressPostalRepairLogEntity> {

    /**
     * コンストラクタ
     *
     * @param addressPostalRepairLogRepository 郵便番号修復ログRespository
     */
    public DeleteKyuAzaItemReader(final @Autowired AddressPostalRepairLogRepository addressPostalRepairLogRepository) {
        super(addressPostalRepairLogRepository, new HashMap<String, Direction>());
        super.setMethodName("findByLgCodeStartingWithAndIsLatestTrueAndIsConfirmTrueAndAddressNameContaining");

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
        list.add("（");
        super.setArguments(list);
    }

}
