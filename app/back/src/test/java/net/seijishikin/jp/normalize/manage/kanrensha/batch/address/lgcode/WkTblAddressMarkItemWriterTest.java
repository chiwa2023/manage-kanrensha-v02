package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
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
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
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

        WkTblAddressRsdtMarkEntity markChangeEntiy = new WkTblAddressRsdtMarkEntity();
        markChangeEntiy.setWlRsdtChangeId(334);

        WkTblAddressRsdtMarkEntity markDeleteEntiy = new WkTblAddressRsdtMarkEntity();
        markDeleteEntiy.setWlRsdtDeleteId(526);

        // 何も呼ばないデータでも例外で落ちないだけ
        WkTblAddressRsdtMarkEntity markNoneEntiy = new WkTblAddressRsdtMarkEntity();
        markNoneEntiy.setWlRsdtChangeId(0);
        markNoneEntiy.setWlRsdtDeleteId(0);

        // 紐づきが正常でなくても例外で落ちないだけ
        WkTblAddressRsdtMarkEntity markWrongIdEntiy = new WkTblAddressRsdtMarkEntity();
        markWrongIdEntiy.setWlRsdtChangeId(1002);

        // 履歴データでも落ちないだけ
        WkTblAddressRsdtMarkEntity markHistoryEntiy = new WkTblAddressRsdtMarkEntity();
        markHistoryEntiy.setWlRsdtChangeId(331);

        List<WkTblAddressRsdtMarkEntity> list = new ArrayList<>();
        list.add(markChangeEntiy);
        list.add(markDeleteEntiy);
        list.add(markNoneEntiy);
        list.add(markWrongIdEntiy);
        list.add(markHistoryEntiy);

        // Chunkを作成してセット
        Chunk<? extends WkTblAddressRsdtMarkEntity> items = new Chunk<>(list);

        wkTblAddressMarkItemWriter.beforeStep(getStepExecution());
        wkTblAddressMarkItemWriter.write(items);

        // 処理後は2件が履歴に更新されている
        WkTblAddressRsdtChangeEntity changeEntity = wkTblAddressRsdtChangeRepository
                .findById(markChangeEntiy.getWlRsdtChangeId()).get();
        assertFalse(changeEntity.getIsLatest());

        WkTblAddressRsdtDeleteEntity deleteEntity = wkTblAddressRsdtDeleteRepository
                .findById(markDeleteEntiy.getWlRsdtDeleteId()).get();
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
