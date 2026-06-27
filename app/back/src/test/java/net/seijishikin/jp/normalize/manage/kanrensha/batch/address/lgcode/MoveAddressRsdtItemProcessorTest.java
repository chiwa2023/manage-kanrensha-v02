package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.task_plan.RecordTaskPlanJobExecutionListner;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * MoveAddressRsdtItemProcessor単体テスト
 */
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class MoveAddressRsdtItemProcessorTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private MoveAddressRsdtItemProcessor moveAddressRsdtItemProcessor;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddressRsdtBaseEntity baseEntity0 = new AddressRsdtBaseEntity();

        baseEntity0.setLgCode("99999");
        baseEntity0.setPostalcode1("123"); // 以降の処理で追加だがここではとりあえず値が入るのを確認
        baseEntity0.setPostalcode2("3456"); // 以降の処理で追加だがここではとりあえず値が入るのを確認
        baseEntity0.setMachiazaId("0013018");
        baseEntity0.setPrcId("017");
        baseEntity0.setBlkId("334");
        baseEntity0.setRsdtId("556");
        baseEntity0.setRsdt2Id("778");
        baseEntity0.setEffectDate(LocalDate.of(2022, 11, 19));
        baseEntity0.setAbolishDate(LocalDate.of(2041, 2, 6));
        baseEntity0.setAddressBlock("地方1東五条十八丁目aaa17番地11号");
        baseEntity0.setAddressBuilding("99号室");

        moveAddressRsdtItemProcessor.beforeStep(this.getStepExecution());
        AddressRsdtTemplateEntity ansEntity = moveAddressRsdtItemProcessor.process(baseEntity0);

        assertEquals("695123", ansEntity.getLgCode());
        assertEquals(baseEntity0.getPostalcode1(), ansEntity.getPostalcode1());
        assertEquals(baseEntity0.getPostalcode2(), ansEntity.getPostalcode2());
        assertEquals(baseEntity0.getMachiazaId(), ansEntity.getMachiazaId());
        assertEquals(baseEntity0.getPrcId(), ansEntity.getPrcId());
        assertEquals(baseEntity0.getBlkId(), ansEntity.getBlkId());
        assertEquals(baseEntity0.getRsdtId(), ansEntity.getRsdtId());
        assertEquals(baseEntity0.getRsdt2Id(), ansEntity.getRsdt2Id());
        assertEquals(baseEntity0.getEffectDate(), ansEntity.getEffectDate());
        assertEquals(baseEntity0.getAbolishDate(), ansEntity.getAbolishDate());
        assertEquals("地方2東五条十八丁目aaa17番地11号", ansEntity.getAddressBlock());
        assertEquals(baseEntity0.getAddressBuilding(), ansEntity.getAddressBuilding());
    }

    private StepExecution getStepExecution() {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()) //
                .addString("srcLgCode", "827637").addString("copyLgCode", "695123")//
                .addString("srcLgName", "地方1").addString("copyLgName", "地方2")
                .addLong(CreateUserLeastDtoByBatchParamUtil.USER_ID_PARAM, (long) userDto.getUserPersonId())
                .addLong(CreateUserLeastDtoByBatchParamUtil.USER_CODE_PARAM, (long) userDto.getUserPersonCode())
                .addString(CreateUserLeastDtoByBatchParamUtil.USER_NAME_PARAM, userDto.getUserPersonName())
                .addLong(RecordTaskPlanJobExecutionListner.KEY_YEAR, (long) 2026) //
                .addLong(RecordTaskPlanJobExecutionListner.KEY_ID, (long) 459) //
                .addLong(RecordTaskPlanJobExecutionListner.KEY_CODE, (long) 153).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
