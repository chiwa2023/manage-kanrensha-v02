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
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalRepairLogEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalCommonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * DeleteKyuAzaItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("DeleteKyuAzaItemWriterTest.sql")
class DeleteKyuAzaItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private DeleteKyuAzaItemWriter deleteKyuAzaItemWriter;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** 郵便番号ワークテーブルRepository */
    @Autowired
    private WkTblPostalCommonRepository wkTblPostalCommonRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddressPostalRepairLogEntity logEntity0 = new AddressPostalRepairLogEntity();
        logEntity0.setAddressPostalId(834);
        logEntity0.setLgCode("987"); // 存在しない住居テーブルの場合ログだけ出すので目視確認
        logEntity0.setAddressName("北海道札幌市（架空）");

        final String lgCode = "011029"; 
        AddressPostalRepairLogEntity logEntity1 = new AddressPostalRepairLogEntity();
        logEntity1.setLgCode(lgCode);
        logEntity1.setAddressPostalId(35); // 存在しない郵便番号正規の場合もログを出しておしまい

        AddressPostalRepairLogEntity logEntity2 = new AddressPostalRepairLogEntity();
        logEntity2.setLgCode(lgCode);
        logEntity2.setAddressPostalId(834);
        logEntity2.setAddressName("北海道札幌市（実在"); // かっこが閉じてない

        AddressPostalRepairLogEntity logEntity3 = new AddressPostalRepairLogEntity();
        logEntity3.setLgCode(lgCode);
        logEntity3.setAddressPostalId(834);
        logEntity3.setAddressName("北海道札幌市）実在（"); // かっこの順序が反対

        AddressPostalRepairLogEntity logEntity4 = new AddressPostalRepairLogEntity();
        final Integer postalId = 835;
        logEntity4.setLgCode(lgCode);
        logEntity4.setAddressPostalId(postalId);
        logEntity4.setAddressName("北海道札幌市（特殊）"); // 住所名称に『特殊』が存在しないので処理続行
        logEntity4.setPostalcode1("888");
        logEntity4.setPostalcode2("777");
        logEntity4.setAddressOrg("特殊");
        logEntity4.setAddressPostalRepairLogId(164);

        AddressPostalRepairLogEntity logEntity5 = new AddressPostalRepairLogEntity();
        logEntity5.setLgCode(lgCode);
        logEntity5.setAddressPostalId(834);
        logEntity5.setAddressName("北海道札幌市（架空）"); // 住所名所に『架空』が存在するので処理しない

        List<AddressPostalRepairLogEntity> list = new ArrayList<>();
        list.add(logEntity0);
        list.add(logEntity1);
        list.add(logEntity2);
        list.add(logEntity3);
        list.add(logEntity4);
        list.add(logEntity5);

        // Chunkを作成してセット
        Chunk<? extends AddressPostalRepairLogEntity> items = new Chunk<>(list);
        deleteKyuAzaItemWriter.beforeStep(this.getStepExecution());
        deleteKyuAzaItemWriter.write(items);

        List<WkTblPostalCommonEntity> listWkTbl = wkTblPostalCommonRepository.findAll();
        assertEquals(1, listWkTbl.size()); // 1件だけが処理対象
        WkTblPostalCommonEntity wkTblEntity = listWkTbl.get(0);

        assertEquals(logEntity4.getAddressPostalId(), wkTblEntity.getAddressPostalId()); // ワークテーブルに処理対象郵便番号が保存されている
        assertEquals(logEntity4.getAddressPostalRepairLogId(), wkTblEntity.getAddressPostalRepairLogId()); // 郵便番号修復ログを履歴にするため正しいIdが指定されている

        AddressPostalEntity postalEntity = addressPostalRepository.findById(postalId).get();

        assertEquals(postalId, postalEntity.getAddressPostalId());
        assertEquals(logEntity4.getLgCode(), postalEntity.getLgCode());
        assertEquals(logEntity4.getPostalcode1(), postalEntity.getPostalcode1());
        assertEquals(logEntity4.getPostalcode2(), postalEntity.getPostalcode2());
        assertEquals(logEntity4.getAddressOrg(), postalEntity.getAddressOrg());
        assertEquals("北海道札幌市", postalEntity.getAddressName()); // かっこの内容が除外

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
