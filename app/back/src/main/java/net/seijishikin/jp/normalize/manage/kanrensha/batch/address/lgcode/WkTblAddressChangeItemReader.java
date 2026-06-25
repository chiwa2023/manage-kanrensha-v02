package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.infrastructure.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtChangeEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtChangeRepository;

/**
 * アドレス・ベース・レジストリワークテーブル更新対象ItemReader
 */
@Component
public class WkTblAddressChangeItemReader extends RepositoryItemReader<WkTblAddressRsdtChangeEntity> {

    /**
     * コンストラクタ
     * 
     * @param wkTblAddressRsdtChangeRepository アドレス・ベース・レジストリワークテーブル更新対象Repository
     */
    public WkTblAddressChangeItemReader(
            final @Autowired WkTblAddressRsdtChangeRepository wkTblAddressRsdtChangeRepository) {
        super(wkTblAddressRsdtChangeRepository, new HashMap<String, Direction>());
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
