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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtFileEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtFileRepository;

/**
 * ファイルから登録分アドレス・ベース・レジストリワークテーブル読み出し
 */
@Component
public class RsdtWkTblAddressFileLgCodeItemReader extends RepositoryItemReader<WkTblAddressRsdtFileEntity> {

    /**
     * コンストラクタ
     * 
     * @param wkTblAddressRsdtFileRepository 住所ファイルから登録Repository
     */
    public RsdtWkTblAddressFileLgCodeItemReader(
            final @Autowired WkTblAddressRsdtFileRepository wkTblAddressRsdtFileRepository) {

        super(wkTblAddressRsdtFileRepository, new HashMap<String, Direction>());
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
