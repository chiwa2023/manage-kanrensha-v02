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
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalRepairLogEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepairLogRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * PickupManualWorksItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("PickupManualWorksItemWriterTest.sql")
class PickupManualWorksItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private PickupManualWorksItemWriter pickupManualWorksItemWriter;

    /** 郵便番号修復ログRepository */
    @Autowired
    private AddressPostalRepairLogRepository addressPostalRepairLogRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddressPostalRepairLogEntity repairLogEntity = new AddressPostalRepairLogEntity();

        repairLogEntity.setAddressName("1");
        repairLogEntity.setAddressOrg("2");
        repairLogEntity.setAddressPostalId(148);
        repairLogEntity.setIsConfirm(true);
        repairLogEntity.setIsGyoseikuData(true);
        repairLogEntity.setLgCode("3");
        repairLogEntity.setPostalcode1("4");
        repairLogEntity.setPostalcode2("5");
        repairLogEntity.setStatusText("6");

        List<AddressPostalRepairLogEntity> list = new ArrayList<>();
        list.add(repairLogEntity);

        // Chunkを作成してセット
        Chunk<? extends AddressPostalRepairLogEntity> items = new Chunk<>(list);

        pickupManualWorksItemWriter.beforeStep(this.getStepExecution());
        pickupManualWorksItemWriter.write(items);

        List<AddressPostalRepairLogEntity> listAns = addressPostalRepairLogRepository.findAll();
        assertEquals(1, listAns.size());

        AddressPostalRepairLogEntity entityAns = listAns.get(0);

        assertEquals(repairLogEntity.getAddressName(), entityAns.getAddressName());
        assertEquals(repairLogEntity.getAddressOrg(), entityAns.getAddressOrg());
        assertEquals(repairLogEntity.getAddressPostalId(), entityAns.getAddressPostalId());
        assertEquals(repairLogEntity.getIsConfirm(), entityAns.getIsConfirm());
        assertEquals(repairLogEntity.getIsGyoseikuData(), entityAns.getIsGyoseikuData());
        assertEquals(repairLogEntity.getLgCode(), entityAns.getLgCode());
        assertEquals(repairLogEntity.getPostalcode1(), entityAns.getPostalcode1());
        assertEquals(repairLogEntity.getPostalcode2(), entityAns.getPostalcode2());
        assertEquals(repairLogEntity.getStatusText(), entityAns.getStatusText());
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
