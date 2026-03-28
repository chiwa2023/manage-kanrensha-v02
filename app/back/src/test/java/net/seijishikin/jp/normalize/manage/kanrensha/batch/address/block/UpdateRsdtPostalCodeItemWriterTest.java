package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

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

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressRsdtTemplateRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * UpdateRsdtPostalCodeItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("UpdateRsdtPostalCodeItemWriterTest.sql")
// コンポーネントにTransactionがあるので、テストに2重でかけると止まる
class UpdateRsdtPostalCodeItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private UpdateRsdtPostalCodeItemWriter updateRsdtPostalCodeItemWriter;

    /** 住居データRepository */
    @Autowired
    private AddressRsdtTemplateRepository addressRsdtTemplateRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddressPostalEntity postalEntity = new AddressPostalEntity();
        postalEntity.setIsLatest(true);
        postalEntity.setLgCode("template");
        postalEntity.setPostalcode1("995");
        postalEntity.setPostalcode2("9876");
        postalEntity.setAddressName("北海道札幌市豊平区月寒東五条十八丁目");

        List<AddressPostalEntity> list = new ArrayList<>();
        list.add(postalEntity);

        // Chunkを作成してセット
        Chunk<? extends AddressPostalEntity> items = new Chunk<>(list);

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        updateRsdtPostalCodeItemWriter.beforeStep(this.getStepExecution(userDto));
        updateRsdtPostalCodeItemWriter.write(items);

        List<AddressRsdtTemplateEntity> listAns = addressRsdtTemplateRepository.findAll();

        assertEquals(7, listAns.size());

        AddressRsdtTemplateEntity entity0 = listAns.get(0);
        assertEquals(322, entity0.getAddressRsdtId());
        assertEquals(1, entity0.getInsertUserCode());

        AddressRsdtTemplateEntity entity1 = listAns.get(1);
        assertEquals(323, entity1.getAddressRsdtId());
        assertEquals(1, entity1.getInsertUserCode());

        AddressRsdtTemplateEntity entity2 = listAns.get(2);
        assertEquals(324, entity2.getAddressRsdtId());
        assertEquals(1, entity2.getInsertUserCode());

        AddressRsdtTemplateEntity entity3 = listAns.get(3);
        assertEquals(325, entity3.getAddressRsdtId());
        assertEquals(1, entity3.getInsertUserCode());

        // 以下更新対象
        AddressRsdtTemplateEntity entity4 = listAns.get(4);
        assertEquals(326, entity4.getAddressRsdtId());
        assertEquals(userDto.getUserPersonId(), entity4.getInsertUserId());
        assertEquals(userDto.getUserPersonCode(), entity4.getInsertUserCode());
        assertEquals(userDto.getUserPersonName(), entity4.getInsertUserName());
        assertEquals(postalEntity.getPostalcode1(), entity4.getPostalcode1());
        assertEquals(postalEntity.getPostalcode2(), entity4.getPostalcode2());
        

        AddressRsdtTemplateEntity entity5 = listAns.get(5);
        assertEquals(327, entity5.getAddressRsdtId());
        assertEquals(userDto.getUserPersonId(), entity5.getInsertUserId());
        assertEquals(userDto.getUserPersonCode(), entity5.getInsertUserCode());
        assertEquals(userDto.getUserPersonName(), entity5.getInsertUserName());
        assertEquals(postalEntity.getPostalcode1(), entity5.getPostalcode1());
        assertEquals(postalEntity.getPostalcode2(), entity5.getPostalcode2());

        AddressRsdtTemplateEntity entity6 = listAns.get(6);
        assertEquals(328, entity6.getAddressRsdtId());
        assertEquals(userDto.getUserPersonId(), entity6.getInsertUserId());
        assertEquals(userDto.getUserPersonCode(), entity6.getInsertUserCode());
        assertEquals(userDto.getUserPersonName(), entity6.getInsertUserName());
        assertEquals(postalEntity.getPostalcode1(), entity6.getPostalcode1());
        assertEquals(postalEntity.getPostalcode2(), entity6.getPostalcode2());
    }

    private StepExecution getStepExecution(final LeastUserDto userDto) {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
