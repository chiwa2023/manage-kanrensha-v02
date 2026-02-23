package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
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

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressRsdtTemplateRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RsdtAddressItemWriter単体テスト
 */
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("RsdtAddressItemWriterTest.sql")
class RsdtAddressItemWriterTest {
    // CHECKSTYLE:OFF MagicNumnber

    /** テスト対象 */
    @Autowired
    private RsdtAddressItemWriter rsdtAddressItemWriter;

    /** 住居データRepository */
    @Autowired
    private AddressRsdtTemplateRepository addressRsdtTemplateRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddressRsdtBaseEntity baseEntity = new AddressRsdtBaseEntity();

        baseEntity.setLgCode("template"); // 登録対象をtemplate
        baseEntity.setPostalcode1("123"); // 以降の処理で追加だがここではとりあえず値が入るのを確認
        baseEntity.setPostalcode2("3456"); // 以降の処理で追加だがここではとりあえず値が入るのを確認
        baseEntity.setMachiazaId("0013018");
        baseEntity.setPrcId("017");
        baseEntity.setBlkId("334");
        baseEntity.setRsdtId("556");
        baseEntity.setRsdt2Id("778");
        baseEntity.setEffectDate(LocalDate.of(2022, 11, 19));
        baseEntity.setAbolishDate(LocalDate.of(2041, 2, 6));
        baseEntity.setAddressBlock("札幌市豊平区月寒東五条十八丁目aaa17番地11号");
        baseEntity.setAddressBuilding("99号室");

        // 地番CSV内で住居フラグオンのため、登録しないために空Entityを返してきた想定
        AddressRsdtBaseEntity escapeEntity = new AddressRsdtBaseEntity();

        List<AddressRsdtBaseEntity> list = new ArrayList<>();
        list.add(baseEntity);
        list.add(escapeEntity);

        // Chunkを作成してセット
        Chunk<? extends AddressRsdtBaseEntity> items = new Chunk<>(list);
        rsdtAddressItemWriter.beforeStep(this.getStepExecution());
        rsdtAddressItemWriter.write(items);

        List<AddressRsdtTemplateEntity> listAnswer = addressRsdtTemplateRepository.findAll();
        assertEquals(1, listAnswer.size());

        AddressRsdtTemplateEntity entityAnswer = listAnswer.get(0);

        assertEquals(baseEntity.getLgCode(), entityAnswer.getLgCode());
        assertEquals(baseEntity.getPostalcode1(), entityAnswer.getPostalcode1());
        assertEquals(baseEntity.getPostalcode2(), entityAnswer.getPostalcode2());
        assertEquals(baseEntity.getMachiazaId(), entityAnswer.getMachiazaId());
        assertEquals(baseEntity.getPrcId(), entityAnswer.getPrcId());
        assertEquals(baseEntity.getBlkId(), entityAnswer.getBlkId());
        assertEquals(baseEntity.getRsdtId(), entityAnswer.getRsdtId());
        assertEquals(baseEntity.getRsdt2Id(), entityAnswer.getRsdt2Id());
        assertEquals(baseEntity.getEffectDate(), entityAnswer.getEffectDate());
        assertEquals(baseEntity.getAbolishDate(), entityAnswer.getAbolishDate());
        assertEquals(baseEntity.getAddressBlock(), entityAnswer.getAddressBlock());
        assertEquals(baseEntity.getAddressBuilding(), entityAnswer.getAddressBuilding());
        assertTrue(entityAnswer.getIsLatest());

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
