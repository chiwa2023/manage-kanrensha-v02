package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.infrastructure.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtDeleteEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtDeleteRepository;

/**
 * アドレス・ベース・レジストリワークテーブル削除対象ItemReader
 */
@Component
public class WkTblAddressDeleteItemReader extends RepositoryItemReader<WkTblAddressRsdtDeleteEntity> {

    /**
     * コンストラクタ
     * 
     * @param wkTblAddressRsdtDeleteRepository アドレス・ベース・レジストリワークテーブル削除対象
     */
    public WkTblAddressDeleteItemReader(
            final @Autowired WkTblAddressRsdtDeleteRepository wkTblAddressRsdtDeleteRepository) {

        super(wkTblAddressRsdtDeleteRepository, new HashMap<>());
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
