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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalCommonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SplitToutenOrgItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SplitToutenOrgItemWriterTest.sql")
class SplitToutenOrgItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SplitToutenOrgItemWriter splitToutenOrgItemWriter;
    
    /** 郵便番号不規則Repository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    /** 郵便番号作業Repository */
    @Autowired
    private WkTblPostalCommonRepository wkTblPostalCommonRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddressPostalIrregularEntity entityIrregular = addressPostalIrregularRepository.findById(830).get();
        List<AddressPostalIrregularEntity> list = new ArrayList<>();
        list.add(entityIrregular);

        // Chunkを作成してセット
        Chunk<? extends AddressPostalIrregularEntity> items = new Chunk<>(list);
        splitToutenOrgItemWriter.beforeStep(this.getStepExecution());
        splitToutenOrgItemWriter.write(items);
        
        List<WkTblPostalCommonEntity> listAns = wkTblPostalCommonRepository.findAll();
        assertEquals(4, listAns.size());
        WkTblPostalCommonEntity entityWk0 = listAns.get(0);
        assertEquals(0, entityWk0.getAddressPostalIrregularId());
        assertEquals(true, entityWk0.getIsLatest());
        assertEquals(entityIrregular.getPostalcode1(), entityWk0.getPostalcode1());
        assertEquals(entityIrregular.getPostalcode2(), entityWk0.getPostalcode2());
        assertEquals(entityIrregular.getAddressPostal(), entityWk0.getAddressPostal());
        assertEquals(entityIrregular.getAddressBlock(), entityWk0.getAddressBlock());
        assertEquals(entityIrregular.getAddressName(), entityWk0.getAddressName());
        assertEquals("西十九条南（３５〜３８丁目）", entityWk0.getAddressOrg());

        WkTblPostalCommonEntity entityWk1 = listAns.get(1);
        assertEquals(0, entityWk1.getAddressPostalIrregularId());
        assertEquals(true, entityWk1.getIsLatest());
        assertEquals(entityIrregular.getPostalcode1(), entityWk1.getPostalcode1());
        assertEquals(entityIrregular.getPostalcode2(), entityWk1.getPostalcode2());
        assertEquals(entityIrregular.getAddressPostal(), entityWk1.getAddressPostal());
        assertEquals(entityIrregular.getAddressBlock(), entityWk1.getAddressBlock());
        assertEquals(entityIrregular.getAddressName(), entityWk1.getAddressName());
        assertEquals("西十九条南（４１丁目）", entityWk1.getAddressOrg());

        WkTblPostalCommonEntity entityWk2 = listAns.get(2);
        assertEquals(0, entityWk2.getAddressPostalIrregularId());
        assertEquals(true, entityWk2.getIsLatest());
        assertEquals(entityIrregular.getPostalcode1(), entityWk2.getPostalcode1());
        assertEquals(entityIrregular.getPostalcode2(), entityWk2.getPostalcode2());
        assertEquals(entityIrregular.getAddressPostal(), entityWk2.getAddressPostal());
        assertEquals(entityIrregular.getAddressBlock(), entityWk2.getAddressBlock());
        assertEquals(entityIrregular.getAddressName(), entityWk2.getAddressName());
        assertEquals("西十九条南（４２丁目）", entityWk2.getAddressOrg());


        // 履歴にするだけであとは何も変わっていない
        WkTblPostalCommonEntity entityWk3 = listAns.get(3);
        assertEquals(entityIrregular.getAddressPostalIrregularId(), entityWk3.getAddressPostalIrregularId());
        assertEquals(false, entityWk3.getIsLatest());
        assertEquals(entityIrregular.getPostalcode1(), entityWk3.getPostalcode1());
        assertEquals(entityIrregular.getPostalcode2(), entityWk3.getPostalcode2());
        assertEquals(entityIrregular.getAddressPostal(), entityWk3.getAddressPostal());
        assertEquals(entityIrregular.getAddressBlock(), entityWk3.getAddressBlock());
        assertEquals(entityIrregular.getAddressName(), entityWk3.getAddressName());
        assertEquals(entityIrregular.getAddressOrg(), entityWk3.getAddressOrg());
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
