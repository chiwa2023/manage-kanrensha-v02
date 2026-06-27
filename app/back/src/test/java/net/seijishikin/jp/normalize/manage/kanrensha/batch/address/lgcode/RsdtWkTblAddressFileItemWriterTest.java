package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtFileEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtFileRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RsdtWkTblAddressFileItemWriter単体テスト
 */
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("RsdtWkTblAddressFileItemWriterTest.sql")
class RsdtWkTblAddressFileItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private RsdtWkTblAddressFileItemWriter rsdtWkTblAddressFileItemWriter;

    /** アドレス・ベース・レジストリワークテーブルRepository */
    @Autowired
    private WkTblAddressRsdtFileRepository wkTblAddressRsdtFileRepository;

    /** 地方自治体コード */
    private static final String LG_CODE = "452027";

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddressRsdtBaseEntity baseEntity0 = new AddressRsdtBaseEntity();

        baseEntity0.setLgCode(LG_CODE);
        baseEntity0.setPostalcode1("123"); // 以降の処理で追加だがここではとりあえず値が入るのを確認
        baseEntity0.setPostalcode2("3456"); // 以降の処理で追加だがここではとりあえず値が入るのを確認
        baseEntity0.setMachiazaId("0013018");
        baseEntity0.setPrcId("017");
        baseEntity0.setBlkId("334");
        baseEntity0.setRsdtId("556");
        baseEntity0.setRsdt2Id("778");
        baseEntity0.setEffectDate(LocalDate.of(2022, 11, 19));
        baseEntity0.setAbolishDate(LocalDate.of(2041, 2, 6));
        baseEntity0.setAddressBlock("架空市東五条十八丁目aaa17番地11号");
        baseEntity0.setAddressBuilding("99号室");

        // 指定地方自治体コードでないので登録しない
        AddressRsdtBaseEntity baseEntity1 = new AddressRsdtBaseEntity();
        baseEntity1.setLgCode("template"); // 登録対象をtemplate
        baseEntity0.setPostalcode1("777");
        baseEntity0.setPostalcode2("8888");

        List<AddressRsdtBaseEntity> list = new ArrayList<>();
        list.add(baseEntity0);
        list.add(baseEntity1);

        // Chunkを作成してセット
        Chunk<? extends AddressRsdtBaseEntity> items = new Chunk<>(list);

        rsdtWkTblAddressFileItemWriter.beforeStep(this.getStepExecution());
        rsdtWkTblAddressFileItemWriter.write(items);

        List<WkTblAddressRsdtFileEntity> listAns = wkTblAddressRsdtFileRepository.findAll();
        assertEquals(1, listAns.size());

        WkTblAddressRsdtFileEntity ansEntity = listAns.get(0);
        assertEquals(baseEntity0.getLgCode(), ansEntity.getLgCode());
        assertEquals(baseEntity0.getPostalcode1(), ansEntity.getPostalcode1());
        assertEquals(baseEntity0.getPostalcode2(), ansEntity.getPostalcode2());
        assertEquals(baseEntity0.getMachiazaId(), ansEntity.getMachiazaId());
        assertEquals(baseEntity0.getPrcId(), ansEntity.getPrcId());
        assertEquals(baseEntity0.getBlkId(), ansEntity.getBlkId());
        assertEquals(baseEntity0.getRsdtId(), ansEntity.getRsdtId());
        assertEquals(baseEntity0.getRsdt2Id(), ansEntity.getRsdt2Id());
        assertEquals(baseEntity0.getEffectDate(), ansEntity.getEffectDate());
        assertEquals(baseEntity0.getAbolishDate(), ansEntity.getAbolishDate());
        assertEquals("宮崎県架空市東五条十八丁目aaa17番地11号", ansEntity.getAddressBlock());
        assertEquals(baseEntity0.getAddressBuilding(), ansEntity.getAddressBuilding());
    }

    private StepExecution getStepExecution() {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()) //
                .addString("lgCode", LG_CODE).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
