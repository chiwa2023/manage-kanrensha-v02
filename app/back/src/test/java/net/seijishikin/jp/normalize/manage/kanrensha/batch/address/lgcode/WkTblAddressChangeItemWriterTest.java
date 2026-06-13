package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.SQLGrammarException;
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

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtChangeEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtMarkEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtMarkRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * WkTblAddressChangeItemWriter単体テスト
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("WkTblAddressChangeItemWriterTest.sql")
class WkTblAddressChangeItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private WkTblAddressChangeItemWriter wkTblAddressChangeItemWriter;

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

        // 変更
        WkTblAddressRsdtChangeEntity entity0 = new WkTblAddressRsdtChangeEntity();
        entity0.setLgCode(lgCode);
        entity0.setWkTblAddressRsdtChangeId(626);
        final Integer rsdtId0 = 803;
        entity0.setAddressRsdtId(rsdtId0);
        entity0.setPostalcode1("11");
        entity0.setPostalcode2("12");
        entity0.setAddressBlock("13");
        entity0.setAddressBuilding("14");
        entity0.setMachiazaId("15");
        entity0.setBlkId("16");
        entity0.setPrcId("17");
        entity0.setRsdtId("18");
        entity0.setRsdt2Id("19");
        entity0.setEffectDate(LocalDate.of(2025, 6, 24));
        entity0.setAbolishDate(LocalDate.of(2026, 7, 11));

        // 追加
        WkTblAddressRsdtChangeEntity entity1 = new WkTblAddressRsdtChangeEntity();
        entity1.setWkTblAddressRsdtChangeId(627);
        entity1.setLgCode(lgCode);
        entity1.setAddressRsdtId(0);
        entity1.setPostalcode1("21");
        entity1.setPostalcode2("22");
        entity1.setAddressBlock("23");
        entity1.setAddressBuilding("24");
        entity1.setMachiazaId("25");
        entity1.setBlkId("26");
        entity1.setPrcId("27");
        entity1.setRsdtId("28");
        entity1.setRsdt2Id("29");
        entity1.setEffectDate(LocalDate.of(2012, 4, 23));
        entity1.setAbolishDate(LocalDate.of(2013, 2, 9));

        WkTblAddressRsdtChangeEntity entity2 = new WkTblAddressRsdtChangeEntity();
        entity2.setLgCode("99999");
        entity2.setAddressRsdtId(803);

        List<WkTblAddressRsdtChangeEntity> list0 = new ArrayList<>();
        list0.add(entity0);
        list0.add(entity1);
        list0.add(entity2);

        // Chunkを作成してセット
        Chunk<? extends WkTblAddressRsdtChangeEntity> items0 = new Chunk<>(list0);
        wkTblAddressChangeItemWriter.beforeStep(getStepExecution());
        // 存在しない地方自治体コードを指定したときは全体ロールバックする
        assertThrows(SQLGrammarException.class, () -> wkTblAddressChangeItemWriter.write(items0));

        // 例外発生のためこのチャンクはトランザクションロールバックされている
        assertEquals(0L, wkTblAddressRsdtMarkRepository.count());
        AddressRsdtBaseEntity baseEntity00 = this.findRsdtTable(lgCode, rsdtId0);
        assertTrue(baseEntity00.getIsLatest());

        // ここから正常更新パターン
        List<WkTblAddressRsdtChangeEntity> list1 = new ArrayList<>();
        list1.add(entity0);
        list1.add(entity1);
        Chunk<? extends WkTblAddressRsdtChangeEntity> items1 = new Chunk<>(list1);
        wkTblAddressChangeItemWriter.beforeStep(getStepExecution());
        wkTblAddressChangeItemWriter.write(items1);

        List<WkTblAddressRsdtMarkEntity> listMark = wkTblAddressRsdtMarkRepository.findAll();
        WkTblAddressRsdtMarkEntity markEntity0 = listMark.get(0);
        assertEquals(626, markEntity0.getWlRsdtChangeId());
        assertEquals(0, markEntity0.getWlRsdtDeleteId());
        WkTblAddressRsdtMarkEntity markEntity1 = listMark.get(1);
        assertEquals(627, markEntity1.getWlRsdtChangeId());
        assertEquals(0, markEntity1.getWlRsdtDeleteId());

        AddressRsdtBaseEntity baseEntity10 = this.findRsdtTable(lgCode, 812);
        assertTrue(baseEntity10.getIsLatest());
        assertEquals(entity0.getLgCode(), baseEntity10.getLgCode());
        assertEquals(entity0.getPostalcode1(), baseEntity10.getPostalcode1());
        assertEquals(entity0.getPostalcode2(), baseEntity10.getPostalcode2());
        assertEquals(entity0.getAddressBlock(), baseEntity10.getAddressBlock());
        assertEquals(entity0.getAddressBuilding(), baseEntity10.getAddressBuilding());
        assertEquals(entity0.getMachiazaId(), baseEntity10.getMachiazaId());
        assertEquals(entity0.getBlkId(), baseEntity10.getBlkId());
        assertEquals(entity0.getPrcId(), baseEntity10.getPrcId());
        assertEquals(entity0.getRsdtId(), baseEntity10.getRsdtId());
        assertEquals(entity0.getRsdt2Id(), baseEntity10.getRsdt2Id());
        assertEquals(entity0.getEffectDate(), baseEntity10.getEffectDate());
        assertEquals(entity0.getAbolishDate(), baseEntity10.getAbolishDate());

        AddressRsdtBaseEntity baseEntity11 = this.findRsdtTable(lgCode, 813);
        assertTrue(baseEntity11.getIsLatest());
        assertEquals(entity1.getLgCode(), baseEntity11.getLgCode());
        assertEquals(entity1.getPostalcode1(), baseEntity11.getPostalcode1());
        assertEquals(entity1.getPostalcode2(), baseEntity11.getPostalcode2());
        assertEquals(entity1.getAddressBlock(), baseEntity11.getAddressBlock());
        assertEquals(entity1.getAddressBuilding(), baseEntity11.getAddressBuilding());
        assertEquals(entity1.getMachiazaId(), baseEntity11.getMachiazaId());
        assertEquals(entity1.getBlkId(), baseEntity11.getBlkId());
        assertEquals(entity1.getPrcId(), baseEntity11.getPrcId());
        assertEquals(entity1.getRsdtId(), baseEntity11.getRsdtId());
        assertEquals(entity1.getRsdt2Id(), baseEntity11.getRsdt2Id());
        assertEquals(entity1.getEffectDate(), baseEntity11.getEffectDate());
        assertEquals(entity1.getAbolishDate(), baseEntity11.getAbolishDate());
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
