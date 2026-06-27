package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.springframework.test.context.jdbc.Sql;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalEditEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalEditRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditPreparePostalCodeTasklet単体テスト
 */
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("EditPreparePostalCodeTaskletTest.sql")
class EditPreparePostalCodeTaskletTest {
    // CHECKSTYLE:OFF MagicNumber

    /** 単体テスト */
    @Autowired
    private EditPreparePostalCodeTasklet editPreparePostalCodeTasklet;

    /** 郵便番号変種ワークテーブルRepository */
    @Autowired
    private WkTblPostalEditRepository wkTblPostalEditRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        editPreparePostalCodeTasklet.beforeStep(this.getStepExecution());
        editPreparePostalCodeTasklet.execute(null, null);

        // ====住所変更連動4440819
        WkTblPostalEditEntity entity010 = wkTblPostalEditRepository.findById(10).get();
        assertEquals(true, entity010.getIsLatest());
        assertEquals(false, entity010.getIsRepair());
        // ====住所変更連動4440850
        // ====住所変更連動4440818

        // ====郵便番号内調整9820046
        WkTblPostalEditEntity entity001 = wkTblPostalEditRepository.findById(1).get();
        assertEquals(true, entity001.getIsLatest());
        assertEquals(true, entity001.getIsRepair());

        // ====郵便番号内調整9820048
        WkTblPostalEditEntity entity087 = wkTblPostalEditRepository.findById(87).get();
        assertEquals(true, entity087.getIsLatest());
        assertEquals(true, entity087.getIsRepair());
        // ====郵便番号内調整9820041

        // ====郵便番号内調整9820044
        // ====郵便番号内調整9820047
        // ====郵便番号内調整9820045
        // ====郵便番号内調整3900851

        // ====表記訂正4620841(カナだけ)
        WkTblPostalEditEntity entity007 = wkTblPostalEditRepository.findById(7).get();
        assertEquals(false, entity007.getIsLatest());
        assertEquals(false, entity007.getIsRepair());

        WkTblPostalEditEntity entity093 = wkTblPostalEditRepository.findById(93).get();
        assertEquals(false, entity093.getIsLatest());
        assertEquals(false, entity093.getIsRepair());

        // ====表記訂正0613151
        WkTblPostalEditEntity entity018 = wkTblPostalEditRepository.findById(18).get();
        assertEquals(true, entity018.getIsLatest());
        assertEquals(true, entity018.getIsRepair());

        WkTblPostalEditEntity entity101 = wkTblPostalEditRepository.findById(101).get();
        assertEquals(true, entity101.getIsLatest());
        assertEquals(true, entity101.getIsRepair());

        // ペアになっていないものは処理の方法がPGで確定するのは難しい
        WkTblPostalEditEntity entity045 = wkTblPostalEditRepository.findById(45).get();
        assertEquals(true, entity045.getIsLatest());
        assertEquals(false, entity045.getIsRepair());
        assertEquals("処理未確定", entity045.getWorksText());
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
