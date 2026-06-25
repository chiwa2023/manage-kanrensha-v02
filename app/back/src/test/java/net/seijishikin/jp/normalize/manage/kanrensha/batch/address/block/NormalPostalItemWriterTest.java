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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * NormalPostalItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("NormalPostalItemWriterTest.sql")
class NormalPostalItemWriterTest {

    /** テスト対象 */
    @Autowired
    private NormalPostalItemWriter normalPostalItemWriter;

    /** 郵便番号Respository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        final Integer loadId = 146;

        AddressPostalEntity baseEntity = addressPostalRepository.findById(loadId).get();
        AddressPostalEntity updateEntity = new AddressPostalEntity();
        BeanUtils.copyProperties(baseEntity, updateEntity);
        updateEntity.setAddressName("abc");
        updateEntity.setPostalcode2("9876");

        List<AddressPostalEntity> list = new ArrayList<>();
        list.add(updateEntity);

        Chunk<? extends AddressPostalEntity> items = new Chunk<>(list);
        normalPostalItemWriter.beforeStep(this.getStepExecution());
        normalPostalItemWriter.write(items);

        List<AddressPostalEntity> listAns = addressPostalRepository.findAll();
        assertEquals(2, listAns.size()); // 増減していない

        AddressPostalEntity entityAns = listAns.get(1);

        assertEquals(entityAns.getAddressOrg(), updateEntity.getAddressOrg());
        assertEquals(entityAns.getAddressName(), updateEntity.getAddressName());
        assertEquals(entityAns.getIsGyoseikuData(), updateEntity.getIsGyoseikuData());
        assertEquals(entityAns.getLgCode(), updateEntity.getLgCode());
        assertEquals(entityAns.getPostalcode1(), updateEntity.getPostalcode1());
        assertEquals(entityAns.getPostalcode2(), updateEntity.getPostalcode2());
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
