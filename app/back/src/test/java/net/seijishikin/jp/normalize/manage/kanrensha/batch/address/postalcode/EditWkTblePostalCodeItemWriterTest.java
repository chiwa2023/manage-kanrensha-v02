package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

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
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalEditEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalEditRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditWkTblePostalCodeItemWriter単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("EditWkTblePostalCodeItemWriterTest.sql")
class EditWkTblePostalCodeItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private EditWkTblePostalCodeItemWriter editWkTblePostalCodeItemWriter;

    /** 郵便番号編集ワークテーブル */
    @Autowired
    private WkTblPostalEditRepository wkTblPostalEditRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        WkTblPostalEditEntity editEntity = new WkTblPostalEditEntity();

        editEntity.setLgCode("04207");
        editEntity.setPostalcode5("982  ");
        editEntity.setPostalcode7("9820046");
        editEntity.setPrefNameKana("ミヤギケン");
        editEntity.setCityNameKana("ナトリシ");
        editEntity.setOrgNameKana("ソウゴダイ");
        editEntity.setPrefName("宮城県");
        editEntity.setCityName("名取市");
        editEntity.setOrgName("相互台");
        editEntity.setFlgProp1("11");
        editEntity.setFlgProp2("12");
        editEntity.setFlgProp3("13");
        editEntity.setFlgProp4("14");
        editEntity.setFlgKoushin("1");
        editEntity.setFlgHenkouRiyu("4");
        editEntity.setFlgEdit("22");
        editEntity.setIsRepair(true);

        editEntity.setAddressPostalId(424);
        editEntity.setIsGyoseikuData(true);
        editEntity.setAddressPostalIrregularId(535);
        editEntity.setWorksText("aaaaa");

        List<WkTblPostalEditEntity> list = new ArrayList<>();
        list.add(editEntity);

        // Chunkを作成してセット
        Chunk<? extends WkTblPostalEditEntity> items = new Chunk<>(list);
        editWkTblePostalCodeItemWriter.beforeStep(this.getStepExecution());
        editWkTblePostalCodeItemWriter.write(items);

        List<WkTblPostalEditEntity> listAns = wkTblPostalEditRepository.findAll();
        assertEquals(1, listAns.size());

        WkTblPostalEditEntity ansEntity = listAns.get(0);

        assertEquals("042072", ansEntity.getLgCode());
        assertEquals(editEntity.getPostalcode5(), ansEntity.getPostalcode5());
        assertEquals(editEntity.getPostalcode7(), ansEntity.getPostalcode7());
        assertEquals(editEntity.getPrefNameKana(), ansEntity.getPrefNameKana());
        assertEquals(editEntity.getCityNameKana(), ansEntity.getCityNameKana());
        assertEquals(editEntity.getOrgNameKana(), ansEntity.getOrgNameKana());
        assertEquals(editEntity.getPrefName(), ansEntity.getPrefName());
        assertEquals(editEntity.getCityName(), ansEntity.getCityName());
        assertEquals(editEntity.getOrgName(), ansEntity.getOrgName());
        assertEquals(editEntity.getFlgProp1(), ansEntity.getFlgProp1());
        assertEquals(editEntity.getFlgProp2(), ansEntity.getFlgProp2());
        assertEquals(editEntity.getFlgProp3(), ansEntity.getFlgProp3());
        assertEquals(editEntity.getFlgProp4(), ansEntity.getFlgProp4());
        assertEquals(editEntity.getFlgKoushin(), ansEntity.getFlgKoushin());
        assertEquals(editEntity.getFlgHenkouRiyu(), ansEntity.getFlgHenkouRiyu());

        assertEquals(editEntity.getFlgEdit(), ansEntity.getFlgEdit());
        assertEquals(editEntity.getIsRepair(), ansEntity.getIsRepair());

        assertEquals(editEntity.getAddressPostalId(), ansEntity.getAddressPostalId());
        assertEquals(editEntity.getIsGyoseikuData(), ansEntity.getIsGyoseikuData());
        assertEquals(editEntity.getAddressPostalIrregularId(), ansEntity.getAddressPostalIrregularId());
        assertEquals(editEntity.getWorksText(), ansEntity.getWorksText());
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
