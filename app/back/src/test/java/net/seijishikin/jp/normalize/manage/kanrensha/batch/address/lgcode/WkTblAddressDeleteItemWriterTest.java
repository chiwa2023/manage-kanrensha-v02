package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.SQLGrammarException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtDeleteEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtMarkEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtMarkRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * WkTblAddressDeleteItemWriter単体テスト
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("WkTblAddressDeleteItemWriterTest.sql")
class WkTblAddressDeleteItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private WkTblAddressDeleteItemWriter wkTblAddressDeleteItemWriter;

    /** アドレス・ベース・レジストリワークテーブル処理マークRepository */
    @Autowired
    private WkTblAddressRsdtMarkRepository wkTblAddressRsdtMarkRepository;

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        final String lgCode = "011045";

        WkTblAddressRsdtDeleteEntity entity0 = new WkTblAddressRsdtDeleteEntity();
        entity0.setLgCode(lgCode);
        entity0.setWkTblAddressRsdtDeleteId(626);
        final Integer rsdtId0 = 803;
        entity0.setAddressRsdtId(rsdtId0);

        WkTblAddressRsdtDeleteEntity entity1 = new WkTblAddressRsdtDeleteEntity();
        entity1.setWkTblAddressRsdtDeleteId(627);
        entity1.setLgCode(lgCode);
        final Integer rsdtId1 = 804;
        entity1.setAddressRsdtId(rsdtId1);

        WkTblAddressRsdtDeleteEntity entity2 = new WkTblAddressRsdtDeleteEntity();
        entity2.setLgCode("99999");
        entity2.setAddressRsdtId(803);

        List<WkTblAddressRsdtDeleteEntity> list0 = new ArrayList<>();
        list0.add(entity0);
        list0.add(entity1);
        list0.add(entity2);

        // Chunkを作成してセット
        Chunk<? extends WkTblAddressRsdtDeleteEntity> items0 = new Chunk<>(list0);
        wkTblAddressDeleteItemWriter.beforeStep(getStepExecution());
        // 存在しない地方自治体コードを指定したときは全体ロールバックする
        assertThrows(SQLGrammarException.class, () -> wkTblAddressDeleteItemWriter.write(items0));

        // 例外発生のためこのチャンクはトランザクションロールバックされている
        assertEquals(0L, wkTblAddressRsdtMarkRepository.count());
        AddressRsdtBaseEntity baseEntity00 = this.findRsdtTable(lgCode, rsdtId0);
        assertTrue(baseEntity00.getIsLatest());
        AddressRsdtBaseEntity baseEntity01 = this.findRsdtTable(lgCode, rsdtId1);
        assertTrue(baseEntity01.getIsLatest());

        // ここから正常更新パターン
        List<WkTblAddressRsdtDeleteEntity> list1 = new ArrayList<>();
        list1.add(entity0);
        list1.add(entity1);
        Chunk<? extends WkTblAddressRsdtDeleteEntity> items1 = new Chunk<>(list1);
        wkTblAddressDeleteItemWriter.beforeStep(getStepExecution());
        wkTblAddressDeleteItemWriter.write(items1);

        List<WkTblAddressRsdtMarkEntity> listMark = wkTblAddressRsdtMarkRepository.findAll();
        WkTblAddressRsdtMarkEntity markEntity0 = listMark.get(0);
        assertEquals(626, markEntity0.getWlRsdtDeleteId());
        assertEquals(0, markEntity0.getWlRsdtChangeId());
        WkTblAddressRsdtMarkEntity markEntity1 = listMark.get(1);
        assertEquals(627, markEntity1.getWlRsdtDeleteId());
        assertEquals(0, markEntity1.getWlRsdtChangeId());
        AddressRsdtBaseEntity baseEntity10 = this.findRsdtTable(lgCode, rsdtId0);
        assertFalse(baseEntity10.getIsLatest());
        AddressRsdtBaseEntity baseEntity11 = this.findRsdtTable(lgCode, rsdtId1);
        assertFalse(baseEntity11.getIsLatest());
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

    @SuppressWarnings("unchecked")
    private AddressRsdtBaseEntity findRsdtTable(final String lgCode, final Integer rsdtId) {

        String sql = "SELECT * FROM address_rsdt_" + lgCode + " WHERE address_rsdt_id = " + rsdtId;

        Query query = entityManager.createNativeQuery(sql, AddressRsdtBaseEntity.class);
        List<AddressRsdtBaseEntity> results = (List<AddressRsdtBaseEntity>) query.getResultList();

        // idを使って呼び出しているので必ず1件だけｈが取得
        assertEquals(1, results.size());

        return results.get(0);
    }
}
