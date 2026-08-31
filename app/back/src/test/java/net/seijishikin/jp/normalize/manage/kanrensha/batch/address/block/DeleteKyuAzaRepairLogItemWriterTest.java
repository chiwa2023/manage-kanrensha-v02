package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalRepairLogEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepairLogRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * DeleteKyuAzaRepairLogItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("DeleteKyuAzaRepairLogItemWriterTest.sql")
class DeleteKyuAzaRepairLogItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private DeleteKyuAzaRepairLogItemWriter deleteKyuAzaRepairLogItemWriter;

    /** 郵便番号修復ログRepository */
    @Autowired
    private AddressPostalRepairLogRepository addressPostalRepairLogRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        WkTblPostalCommonEntity wkTblEntity = new WkTblPostalCommonEntity();
        wkTblEntity.setAddressPostalRepairLogId(163);
        wkTblEntity.setLgCode("12345");
        wkTblEntity.setPostalcode1("234");
        wkTblEntity.setPostalcode2("34567");
        wkTblEntity.setAddressOrg("abcde");
        wkTblEntity.setAddressName("fghi");
        wkTblEntity.setIsConfirm(true);
        wkTblEntity.setIsLatest(true);

        List<WkTblPostalCommonEntity> list = new ArrayList<>();
        list.add(wkTblEntity);

        // Chunkを作成してセット
        Chunk<? extends WkTblPostalCommonEntity> items = new Chunk<>(list);
        deleteKyuAzaRepairLogItemWriter.beforeStep(this.getStepExecution());
        deleteKyuAzaRepairLogItemWriter.write(items);

        List<AddressPostalRepairLogEntity> listAns = addressPostalRepairLogRepository.findAll();

        assertEquals(5, listAns.size()); // サイズは増えない

        AddressPostalRepairLogEntity logEntity = listAns.get(2);
        assertEquals(logEntity.getAddressPostalRepairLogId(), wkTblEntity.getAddressPostalRepairLogId());
        // 作業終了で履歴で保存
        assertFalse(logEntity.getIsLatest());
        // 立てたフラグはおろす
        assertEquals(logEntity.getIsConfirm(), wkTblEntity.getIsConfirm());
        // 修復ログをワークテーブルに複写しているので、実際にはここまで更新されることはないが更新されることを確認
        assertEquals(logEntity.getLgCode(), wkTblEntity.getLgCode());
        assertEquals(logEntity.getPostalcode1(), wkTblEntity.getPostalcode1());
        assertEquals(logEntity.getPostalcode2(), wkTblEntity.getPostalcode2());
        assertEquals(logEntity.getAddressOrg(), wkTblEntity.getAddressOrg());
        assertEquals(logEntity.getAddressName(), wkTblEntity.getAddressName());
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
