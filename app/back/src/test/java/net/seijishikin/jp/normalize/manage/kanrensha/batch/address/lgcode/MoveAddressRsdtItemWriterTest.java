package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * MoveAddressRsdtItemWriter単体テスト
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("MoveAddressRsdtItemWriterTest.sql")
class MoveAddressRsdtItemWriterTest {
    // CHECKSTYLE:OFF MagciNumber

    /** テスト対象 */
    @Autowired
    private MoveAddressRsdtItemWriter moveAddressRsdtItemWriter;

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /** コピー対象地方自治体コード */
    private static final String COPY_LGCODE = "695123";

    @Test
    @Tag("TableTruncate")
    @SuppressWarnings("unchecked")
    void test() throws Exception {

        AddressRsdtBaseEntity baseEntity = new AddressRsdtBaseEntity();

        baseEntity.setLgCode("442"); // 存在しそうにないテーブル
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

        List<AddressRsdtBaseEntity> list = new ArrayList<>();
        list.add(baseEntity);

        // Chunkを作成してセット
        Chunk<? extends AddressRsdtBaseEntity> items = new Chunk<>(list);
        moveAddressRsdtItemWriter.beforeStep(this.getStepExecution());
        moveAddressRsdtItemWriter.write(items);

        String sql = "SELECT * FROM address_rsdt_" + COPY_LGCODE + "  WHERE is_latest = 1";
        Query query = entityManager.createNativeQuery(sql, AddressRsdtTemplateEntity.class);
        List<AddressRsdtTemplateEntity> listAnswer = //
                (List<AddressRsdtTemplateEntity>) query.getResultList(); // NOPMD LawDemeter

        assertEquals(1, listAnswer.size());

        AddressRsdtTemplateEntity entityAnswer = listAnswer.get(0);

        assertEquals(COPY_LGCODE, entityAnswer.getLgCode());
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

    private StepExecution getStepExecution() throws URISyntaxException, IOException {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()) //
                .addString("srcLgCode", "827637").addString("copyLgCode", COPY_LGCODE)
                .addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
