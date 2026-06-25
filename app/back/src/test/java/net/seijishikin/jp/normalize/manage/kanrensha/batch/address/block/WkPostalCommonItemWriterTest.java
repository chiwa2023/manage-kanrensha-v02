package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalCommonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * WkPostalCommonItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("WkPostalCommonItemWriterTest.sql")
class WkPostalCommonItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private WkPostalCommonItemWriter wkPostalCommonItemWriter;

    /** 郵便番号終生ワークテーブルRespository */
    @Autowired
    private WkTblPostalCommonRepository wkTblPostalCommonRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        WkTblPostalCommonEntity worksEntity01 = new WkTblPostalCommonEntity();
        worksEntity01.setIsLatest(false);
        worksEntity01.setAddressBlock("11");
        worksEntity01.setAddressName("12");
        worksEntity01.setAddressOrg("13");
        worksEntity01.setAddressPostal("14");
        worksEntity01.setAddressPostalId(15);
        worksEntity01.setAddressPostalIrregularId(16);
        worksEntity01.setIsAddPostal(true);
        worksEntity01.setIsGyoseikuData(true);
        worksEntity01.setIsRepairRsdt(true);
        worksEntity01.setLgCode("15");
        worksEntity01.setPostalcode1("16");
        worksEntity01.setPostalcode2("17");

        WkTblPostalCommonEntity worksEntity02 = new WkTblPostalCommonEntity();
        worksEntity02.setIsLatest(true);
        worksEntity02.setAddressBlock("51");
        worksEntity02.setAddressName("52");
        worksEntity02.setAddressOrg("53");
        worksEntity02.setAddressPostal("54");
        worksEntity02.setAddressPostalId(55);
        worksEntity02.setAddressPostalIrregularId(56);
        worksEntity02.setIsAddPostal(false);
        worksEntity02.setIsGyoseikuData(false);
        worksEntity02.setIsRepairRsdt(false);
        worksEntity02.setLgCode("57");
        worksEntity02.setPostalcode1("58");
        worksEntity02.setPostalcode2("59");

        List<WkTblPostalCommonEntity> list = new ArrayList<>();
        list.add(worksEntity01);
        list.add(worksEntity02);

        // Chunkを作成してセット
        Chunk<? extends WkTblPostalCommonEntity> items = new Chunk<>(list);
        wkPostalCommonItemWriter.beforeStep(this.getStepExecution());
        wkPostalCommonItemWriter.write(items);

        List<WkTblPostalCommonEntity> listAns = wkTblPostalCommonRepository.findAll();
        assertEquals(2, listAns.size());

        WkTblPostalCommonEntity ansEntity01 = listAns.get(0);

        assertEquals(worksEntity01.getAddressBlock(), ansEntity01.getAddressBlock());
        assertEquals(worksEntity01.getAddressName(), ansEntity01.getAddressName());
        assertEquals(worksEntity01.getAddressOrg(), ansEntity01.getAddressOrg());
        assertEquals(worksEntity01.getAddressPostal(), ansEntity01.getAddressPostal());
        assertEquals(worksEntity01.getAddressPostalId(), ansEntity01.getAddressPostalId());
        assertEquals(worksEntity01.getAddressPostalIrregularId(), ansEntity01.getAddressPostalIrregularId());
        assertEquals(worksEntity01.getIsAddPostal(), ansEntity01.getIsAddPostal());
        assertEquals(worksEntity01.getIsGyoseikuData(), ansEntity01.getIsGyoseikuData());
        assertEquals(worksEntity01.getIsLatest(), ansEntity01.getIsLatest());
        assertEquals(worksEntity01.getIsRepairRsdt(), ansEntity01.getIsRepairRsdt());
        assertEquals(worksEntity01.getLgCode(), ansEntity01.getLgCode());
        assertEquals(worksEntity01.getPostalcode1(), ansEntity01.getPostalcode1());
        assertEquals(worksEntity01.getPostalcode2(), ansEntity01.getPostalcode2());

        WkTblPostalCommonEntity ansEntity02 = listAns.get(1);

        assertEquals(worksEntity02.getAddressBlock(), ansEntity02.getAddressBlock());
        assertEquals(worksEntity02.getAddressName(), ansEntity02.getAddressName());
        assertEquals(worksEntity02.getAddressOrg(), ansEntity02.getAddressOrg());
        assertEquals(worksEntity02.getAddressPostal(), ansEntity02.getAddressPostal());
        assertEquals(worksEntity02.getAddressPostalId(), ansEntity02.getAddressPostalId());
        assertEquals(worksEntity02.getAddressPostalIrregularId(), ansEntity02.getAddressPostalIrregularId());
        assertEquals(worksEntity02.getIsAddPostal(), ansEntity02.getIsAddPostal());
        assertEquals(worksEntity02.getIsGyoseikuData(), ansEntity02.getIsGyoseikuData());
        assertEquals(worksEntity02.getIsLatest(), ansEntity02.getIsLatest());
        assertEquals(worksEntity02.getIsRepairRsdt(), ansEntity02.getIsRepairRsdt());
        assertEquals(worksEntity02.getLgCode(), ansEntity02.getLgCode());
        assertEquals(worksEntity02.getPostalcode1(), ansEntity02.getPostalcode1());
        assertEquals(worksEntity02.getPostalcode2(), ansEntity02.getPostalcode2());
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
