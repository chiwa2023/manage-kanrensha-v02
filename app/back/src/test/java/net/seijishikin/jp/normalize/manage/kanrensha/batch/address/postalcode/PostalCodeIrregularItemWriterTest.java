package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

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
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * PostalCodeIrregularItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("PostalCodeIrregularItemWriterTest.sql")
class PostalCodeIrregularItemWriterTest {

    /** テスト対象 */
    @Autowired
    private PostalCodeIrregularItemWriter postalCodeIrregularItemWriter;

    /** 郵便番号不規則データRepository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddressPostalIrregularEntity entity = new AddressPostalIrregularEntity();
        entity.setLgCode("123123");
        entity.setPostalcode1("987");
        entity.setPostalcode2("6543");
        entity.setAddressOrg("山麓町（字小山、字大山）");
        entity.setAddressName("架空市山麓町");
        entity.setAddressPostal("宮崎県架空市山麓町");
        entity.setAddressBlock("字小山１９５番地３");

        List<AddressPostalIrregularEntity> list = new ArrayList<>();
        list.add(entity);

        // Chunkを作成してセット
        Chunk<? extends AddressPostalIrregularEntity> items = new Chunk<>(list);
        postalCodeIrregularItemWriter.beforeStep(this.getStepExecution());
        postalCodeIrregularItemWriter.write(items);

        List<AddressPostalIrregularEntity> listAnswer = addressPostalIrregularRepository.findAll();
        assertEquals(1, listAnswer.size(), "1件登録");

        AddressPostalIrregularEntity answerEntity00 = listAnswer.get(0);

        assertEquals(entity.getLgCode(), answerEntity00.getLgCode());
        assertEquals(entity.getPostalcode1(), answerEntity00.getPostalcode1());
        assertEquals(entity.getPostalcode2(), answerEntity00.getPostalcode2());
        assertEquals(entity.getAddressOrg(), answerEntity00.getAddressOrg());
        assertEquals(entity.getAddressName(), answerEntity00.getAddressName());
        assertEquals(entity.getAddressPostal(), answerEntity00.getAddressPostal());
        assertEquals(entity.getAddressBlock(), answerEntity00.getAddressBlock());
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
