package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
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
 * PostalCodeCsvOneLineItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("PostalCodeCsvOneLineItemWriterTest.sql")
class PostalCodeCsvOneLineItemWriterTest {

    /** テスト対象 */
    @Autowired
    private PostalCodeCsvOneLineItemWriter postalCodeCsvOneLineItemWriter;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;


    /** 郵便番号不規則Repository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddressPostalEntity entity00 = new AddressPostalEntity();
        entity00.setLgCode("965314");
        entity00.setPostalcode1("253");
        entity00.setPostalcode2("9162");
        entity00.setAddressOrg("町字？");
        entity00.setAddressName("都道府県行政区");
        entity00.setIsGyoseikuData(true);

        List<AddressPostalEntity> list = new ArrayList<>();
        list.add(entity00);

        AddressPostalEntity entity01 = new AddressPostalEntity();
        entity01.setLgCode("965314");
        entity01.setPostalcode1("253");
        entity01.setPostalcode2("9162");
        entity01.setAddressOrg("町字（特殊設定）");
        entity01.setAddressName("都道府県行政区");
        entity01.setIsGyoseikuData(true);
        list.add(entity01);

        
        
        // Chunkを作成してセット
        Chunk<? extends AddressPostalEntity> items = new Chunk<>(list);

        postalCodeCsvOneLineItemWriter.beforeStep(this.getStepExecution());
        postalCodeCsvOneLineItemWriter.write(items);

        List<AddressPostalEntity> listAns = addressPostalRepository.findAll();
        assertEquals(2, listAns.size());

        AddressPostalEntity answerEntity00 = listAns.get(0);

        assertEquals(entity00.getLgCode(), answerEntity00.getLgCode());
        assertEquals(entity00.getPostalcode1(), answerEntity00.getPostalcode1());
        assertEquals(entity00.getPostalcode2(), answerEntity00.getPostalcode2());
        assertEquals(entity00.getAddressOrg(), answerEntity00.getAddressOrg());
        assertEquals(entity00.getAddressName(), answerEntity00.getAddressName());
        assertEquals(entity00.getIsGyoseikuData(), answerEntity00.getIsGyoseikuData());

        AddressPostalEntity answerEntity01 = listAns.get(1);

        assertEquals(entity01.getLgCode(), answerEntity01.getLgCode());
        assertEquals(entity01.getPostalcode1(), answerEntity01.getPostalcode1());
        assertEquals(entity01.getPostalcode2(), answerEntity01.getPostalcode2());
        assertEquals(entity01.getAddressOrg(), answerEntity01.getAddressOrg());
        assertEquals(entity01.getAddressName(), answerEntity01.getAddressName());
        assertEquals(entity01.getIsGyoseikuData(), answerEntity01.getIsGyoseikuData());

        
        // TODO （ありの場合は正規と不規則双方に登録する

        
        List<AddressPostalIrregularEntity> listIrregular = addressPostalIrregularRepository.findAll();
        assertEquals(1, listIrregular.size());

        AddressPostalIrregularEntity answerEntity10 = listIrregular.get(0);

        
        assertEquals(entity01.getLgCode(), answerEntity10.getLgCode());
        assertEquals(entity01.getPostalcode1(), answerEntity10.getPostalcode1());
        assertEquals(entity01.getPostalcode2(), answerEntity10.getPostalcode2());
        assertEquals(entity01.getAddressOrg(), answerEntity10.getAddressOrg());
        assertEquals(entity01.getAddressName(), answerEntity10.getAddressName());
        
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
