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
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * IrregularPostalItemWriter単体テスト
 */
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("IrregularPostalItemWriterTest.sql")
class IrregularPostalItemWriterTest {

    /** テスト対象 */
    @Autowired
    private IrregularPostalItemWriter irregularPostalItemWriter;

    /** 郵便番号不規則Respository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        final int dataId = 749;

        AddressPostalIrregularEntity irregularPreEntity = addressPostalIrregularRepository.findById(dataId).get();
        assertEquals(false, irregularPreEntity.getIsAddPostal());
        assertEquals(false, irregularPreEntity.getIsRepairRsdt());

        AddressPostalIrregularWorksProcessor worksProcessor = new AddressPostalIrregularWorksProcessor();

        WkTblPostalCommonEntity worksEntity = worksProcessor.process(irregularPreEntity);
        worksEntity.setIsAddPostal(true);
        worksEntity.setIsRepairRsdt(true);

        AddressPostalWorksIrregularProcessor irregularProcessor = new AddressPostalWorksIrregularProcessor();
        AddressPostalIrregularEntity irregularEditEntity = irregularProcessor.process(worksEntity);

        List<AddressPostalIrregularEntity> list = new ArrayList<>();
        list.add(irregularEditEntity);

        // Chunkを作成してセット
        Chunk<? extends AddressPostalIrregularEntity> items = new Chunk<>(list);

        irregularPostalItemWriter.beforeStep(this.getStepExecution());
        irregularPostalItemWriter.write(items);

        AddressPostalIrregularEntity irregularProEntity = addressPostalIrregularRepository.findById(dataId).get();
        assertEquals(true, irregularProEntity.getIsAddPostal());
        assertEquals(true, irregularProEntity.getIsRepairRsdt());
        assertEquals(worksEntity.getIsLatest(), irregularProEntity.getIsLatest());
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
