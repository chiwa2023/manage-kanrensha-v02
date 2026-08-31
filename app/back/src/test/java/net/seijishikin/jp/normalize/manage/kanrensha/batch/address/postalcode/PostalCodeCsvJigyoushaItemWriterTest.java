package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * PostalCodeCsvJigyoushaItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("PostalCodeCsvJigyoushaItemWriterTest.sql")
class PostalCodeCsvJigyoushaItemWriterTest {

    /** テスト対象 */
    @Autowired
    private PostalCodeCsvJigyoushaItemWriter postalCodeCsvJigyoushaItemWriter;

    /** 郵便番号不規則Repository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddressPostalIrregularEntity entity00 = new AddressPostalIrregularEntity();
        entity00.setLgCode("965314");
        entity00.setPostalcode1("253");
        entity00.setPostalcode2("9652");
        entity00.setAddressOrg("町字？");
        entity00.setAddressName("都道府県行政区");
        entity00.setAddressPostal("都道府県行政区");
        entity00.setAddressBlock("町字番地建物");
        entity00.setIsAddPostal(true);
        entity00.setIsRepairRsdt(true);

        List<AddressPostalIrregularEntity> list = new ArrayList<>();
        list.add(entity00);

        // Chunkを作成してセット
        Chunk<? extends AddressPostalIrregularEntity> items = new Chunk<>(list);

        postalCodeCsvJigyoushaItemWriter.beforeStep(this.getStepExecution());
        postalCodeCsvJigyoushaItemWriter.write(items);

        List<AddressPostalIrregularEntity> listAns = addressPostalIrregularRepository.findAll();
        assertEquals(1, listAns.size());

        AddressPostalIrregularEntity answerEntity00 = listAns.get(0);

        assertEquals(entity00.getLgCode(), answerEntity00.getLgCode());
        assertEquals(entity00.getPostalcode1(), answerEntity00.getPostalcode1());
        assertEquals(entity00.getPostalcode2(), answerEntity00.getPostalcode2());
        assertEquals(entity00.getAddressOrg(), answerEntity00.getAddressOrg());
        assertEquals(entity00.getAddressName(), answerEntity00.getAddressName());
        assertEquals(entity00.getIsAddPostal(), answerEntity00.getIsAddPostal());
        assertEquals(entity00.getIsRepairRsdt(), answerEntity00.getIsRepairRsdt());

        List<AddressPostalEntity> listAnsPostal = addressPostalRepository.findAll();
        assertEquals(1, listAnsPostal.size());

        AddressPostalEntity answerEntityPostal00 = listAnsPostal.get(0);

        assertEquals(entity00.getLgCode(), answerEntityPostal00.getLgCode());
        assertEquals(entity00.getPostalcode1(), answerEntityPostal00.getPostalcode1());
        assertEquals(entity00.getPostalcode2(), answerEntityPostal00.getPostalcode2());
        assertEquals(entity00.getAddressOrg(), answerEntityPostal00.getAddressOrg());
        assertEquals(entity00.getAddressName(), answerEntityPostal00.getAddressName());
        assertFalse(answerEntityPostal00.getIsGyoseikuData());

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
