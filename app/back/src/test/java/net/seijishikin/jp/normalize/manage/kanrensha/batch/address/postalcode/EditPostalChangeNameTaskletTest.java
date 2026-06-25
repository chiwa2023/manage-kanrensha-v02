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
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalEditEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalEditRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditPostalChangeNameTasklet単体テスト
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("EditPostalChangeNameTaskletTest.sql")
class EditPostalChangeNameTaskletTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private EditPostalChangeNameTasklet editPostalChangeNameTasklet;

    /** 正規郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** 正規郵便番号Repository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    /** 郵便番号編集ワークテーブルRepository */
    @Autowired
    private WkTblPostalEditRepository wkTblPostalEditRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        editPostalChangeNameTasklet.beforeStep(this.getStepExecution());
        editPostalChangeNameTasklet.execute(null, null);

        // 福岡県福岡市早良区干隈
        WkTblPostalEditEntity editEntity499 = wkTblPostalEditRepository.findById(499).get();
        assertEquals(false, editEntity499.getIsRepair()); // これ以上は自動処理させない
        assertEquals(false, editEntity499.getIsLatest()); // カナだけ変更の場合は何もしないことが確定

        // 城見ＪＹＯタワー（３８階）
        WkTblPostalEditEntity editEntity497 = wkTblPostalEditRepository.findById(497).get();
        assertEquals(false, editEntity497.getIsRepair()); // これ以上は自動処理させない
        assertEquals(true, editEntity497.getIsLatest()); // 自動処理はできないがひきつづき対象(住居)

        AddressPostalEntity postalAnsEntity651 = addressPostalRepository.findById(651).get();
        assertEquals("城見ＪＹＯタワー（３８階）", postalAnsEntity651.getAddressOrg());
        assertEquals("大阪府大阪市中央区城見ＪＹＯタワー（３８階）", postalAnsEntity651.getAddressName());
        assertEquals(false, postalAnsEntity651.getIsGyoseikuData()); // 不規則データであることは変わらない

        AddressPostalIrregularEntity irregularlAnsEntity651 = addressPostalIrregularRepository.findById(345).get();
        assertEquals("城見ＪＹＯタワー（３８階）", irregularlAnsEntity651.getAddressOrg());
        assertEquals("大阪府大阪市中央区城見ＪＹＯタワー", irregularlAnsEntity651.getAddressName());
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
