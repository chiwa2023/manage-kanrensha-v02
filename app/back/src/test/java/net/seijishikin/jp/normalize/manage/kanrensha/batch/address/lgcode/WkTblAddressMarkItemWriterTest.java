package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtChangeEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtDeleteEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtMarkEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtChangeRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtDeleteRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * WkTblAddressMarkItemWriter単体テスト
 */
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("WkTblAddressMarkItemWriterTest.sql")
class WkTblAddressMarkItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private WkTblAddressMarkItemWriter wkTblAddressMarkItemWriter;

    /** アドレス・ベース・レジストリ新規・変更用ワークテーブル */
    @Autowired
    private WkTblAddressRsdtChangeRepository wkTblAddressRsdtChangeRepository;

    /** アドレス・ベース・レジストリ新規・変更用ワークテーブル */
    @Autowired
    private WkTblAddressRsdtDeleteRepository wkTblAddressRsdtDeleteRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        WkTblAddressRsdtMarkEntity markChangeEntity = new WkTblAddressRsdtMarkEntity();
        markChangeEntity.setWlRsdtChangeId(334);

        WkTblAddressRsdtMarkEntity markDeleteEntity = new WkTblAddressRsdtMarkEntity();
        markDeleteEntity.setWlRsdtDeleteId(526);

        // 何も呼ばないデータでも例外で落ちないだけ
        WkTblAddressRsdtMarkEntity markNoneEntity = new WkTblAddressRsdtMarkEntity();
        markNoneEntity.setWlRsdtChangeId(0);
        markNoneEntity.setWlRsdtDeleteId(0);

        // 紐づきが正常でなくても例外で落ちないだけ
        WkTblAddressRsdtMarkEntity markWrongIdEntity = new WkTblAddressRsdtMarkEntity();
        markWrongIdEntity.setWlRsdtChangeId(1002);

        // 履歴データでも落ちないだけ
        WkTblAddressRsdtMarkEntity markHistoryEntity = new WkTblAddressRsdtMarkEntity();
        markHistoryEntity.setWlRsdtChangeId(331);

        List<WkTblAddressRsdtMarkEntity> list = new ArrayList<>();
        list.add(markChangeEntity);
        list.add(markDeleteEntity);
        list.add(markNoneEntity);
        list.add(markWrongIdEntity);
        list.add(markHistoryEntity);

        // Chunkを作成してセット
        Chunk<? extends WkTblAddressRsdtMarkEntity> items = new Chunk<>(list);

        wkTblAddressMarkItemWriter.beforeStep(getStepExecution());
        wkTblAddressMarkItemWriter.write(items);

        // 処理後は2件が履歴に更新されている
        WkTblAddressRsdtChangeEntity changeEntity = wkTblAddressRsdtChangeRepository
                .findById(markChangeEntity.getWlRsdtChangeId()).get();
        assertFalse(changeEntity.getIsLatest());

        WkTblAddressRsdtDeleteEntity deleteEntity = wkTblAddressRsdtDeleteRepository
                .findById(markDeleteEntity.getWlRsdtDeleteId()).get();
        assertFalse(deleteEntity.getIsLatest());
    }

    private StepExecution getStepExecution() {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
