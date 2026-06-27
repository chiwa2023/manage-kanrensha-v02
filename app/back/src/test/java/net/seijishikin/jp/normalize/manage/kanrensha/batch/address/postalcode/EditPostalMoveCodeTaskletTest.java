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

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalEditEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalEditRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditPostalMoveCodeTasklet単体テスト
 */
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("EditPostalMoveCodeTaskletTest.sql")
class EditPostalMoveCodeTaskletTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private EditPostalMoveCodeTasklet editPostalMoveCodeTasklet;

    /** 郵便番号編集ワークテーブルRepository */
    @Autowired
    private WkTblPostalEditRepository wkTblPostalEditRepository;

    /** 正規郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** 正規郵便番号Repository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        editPostalMoveCodeTasklet.beforeStep(this.getStepExecution());
        editPostalMoveCodeTasklet.execute(null, null);

        // ゆりが丘
        WkTblPostalEditEntity editEntity336 = wkTblPostalEditRepository.findById(336).get();
        assertEquals(false, editEntity336.getIsRepair()); // これ以上は自動処理させない
        assertEquals(false, editEntity336.getIsLatest()); // 処理終了が確定

        WkTblPostalEditEntity editEntity422 = wkTblPostalEditRepository.findById(422).get();
        assertEquals(false, editEntity422.getIsRepair()); // これ以上は自動処理させない
        assertEquals(false, editEntity422.getIsLatest()); // 処理終了が確定

        AddressPostalEntity postalAnsEntity050 = addressPostalRepository.findById(50).get();
        assertEquals("982", postalAnsEntity050.getPostalcode1()); // NOPMD DuplicateLiteral
        assertEquals("0045", postalAnsEntity050.getPostalcode2());

        // 下増田(その他)：那智が丘の項に記載
        WkTblPostalEditEntity editEntity334 = wkTblPostalEditRepository.findById(334).get();
        assertEquals(false, editEntity334.getIsRepair()); // これ以上は自動処理させない
        assertEquals(false, editEntity334.getIsLatest()); // 処理終了が確定

        WkTblPostalEditEntity editEntity420 = wkTblPostalEditRepository.findById(420).get();
        assertEquals(false, editEntity420.getIsRepair()); // これ以上は自動処理させない
        assertEquals(false, editEntity420.getIsLatest()); // 処理終了が確定

        AddressPostalEntity postalAnsEntity048 = addressPostalRepository.findById(48).get();
        assertEquals("982", postalAnsEntity048.getPostalcode1());
        assertEquals("0044", postalAnsEntity048.getPostalcode2());

        AddressPostalIrregularEntity irregularlAnsEntity002 = addressPostalIrregularRepository.findById(2).get();
        assertEquals(false, irregularlAnsEntity002.getIsLatest());

        AddressPostalIrregularEntity irregularlAnsEntity003 = addressPostalIrregularRepository.findById(3).get();
        assertEquals("982", irregularlAnsEntity003.getPostalcode1());
        assertEquals("0044", irregularlAnsEntity003.getPostalcode2());

        final String lgCode = "042072";

        // 北海道旭川市神居町西丘1番地(宮城県名取市相互台)
        AddressRsdtTemplateEntity ansRsdtEntity00 = this.getAddressEntity(lgCode, 207287);
        assertEquals(false, ansRsdtEntity00.getIsLatest());

        AddressRsdtTemplateEntity ansRsdtEntity10 = this.getAddressEntity(lgCode, 207305);
        assertEquals(true, ansRsdtEntity10.getIsLatest());
        assertEquals(ansRsdtEntity00.getAddressBlock(), ansRsdtEntity10.getAddressBlock()); // 住所は変わらない
        assertEquals("982", ansRsdtEntity10.getPostalcode1());
        assertEquals("0046", ansRsdtEntity10.getPostalcode2());

        // 北海道旭川市神居町西丘9番地2号(宮城県名取市高舘熊野堂)
        AddressRsdtTemplateEntity ansRsdtEntity01 = this.getAddressEntity(lgCode, 207304);
        assertEquals(false, ansRsdtEntity01.getIsLatest());

        AddressRsdtTemplateEntity ansRsdtEntity11 = this.getAddressEntity(lgCode, 207308);
        assertEquals(true, ansRsdtEntity11.getIsLatest());
        assertEquals(ansRsdtEntity01.getAddressBlock(), ansRsdtEntity11.getAddressBlock()); // 住所は変わらない
        assertEquals("982", ansRsdtEntity11.getPostalcode1());
        assertEquals("0041", ansRsdtEntity11.getPostalcode2());

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

    private AddressRsdtTemplateEntity getAddressEntity(final String lgCode, final Integer rsdtId) {
        String sql = "SELECT * FROM address_rsdt_" + lgCode + "  WHERE address_rsdt_id = " + rsdtId;
        Query query = entityManager.createNativeQuery(sql, AddressRsdtTemplateEntity.class);
        return (AddressRsdtTemplateEntity) query.getSingleResult(); // NOPMD LawDemeter
    }

}
