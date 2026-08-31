package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.step.StepExecution;
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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalRepairLogEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepairLogRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * PickupManualWorksInitializeTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("PickupManualWorksInitializeTaskletTest.sql")
class PickupManualWorksInitializeTaskletTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private PickupManualWorksInitializeTasklet pickupManualWorksInitializeTasklet;

    /** 郵便番号修復ログRepository */
    @Autowired
    private AddressPostalRepairLogRepository addressPostalRepairLogRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        pickupManualWorksInitializeTasklet.beforeStep(getStepExecution());
        pickupManualWorksInitializeTasklet.execute(null, null);

        List<AddressPostalRepairLogEntity> list = addressPostalRepairLogRepository.findAll();
        assertEquals(3, list.size()); // 更新しただけなのでデータは増えない

        AddressPostalRepairLogEntity entity0 = list.get(0);
        assertEquals(161, entity0.getAddressPostalRepairLogId());
        assertNotEquals(190, entity0.getDeleteUserCode());
        
        AddressPostalRepairLogEntity entity1 = list.get(1);
        assertEquals(162, entity1.getAddressPostalRepairLogId());
        assertNotEquals(190, entity1.getDeleteUserCode());

        AddressPostalRepairLogEntity entity2 = list.get(2);
        assertEquals(163, entity2.getAddressPostalRepairLogId());
        assertEquals(196, entity2.getDeleteUserId());
        assertEquals(190, entity2.getDeleteUserCode());
        assertEquals("管理人　太郎", entity2.getDeleteUserName());
        assertFalse(entity2.getIsLatest());
    }

    private StepExecution getStepExecution() {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("lgCodePref", "01").addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
