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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalCommonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * ChoicePostalCodeIrregularItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("ChoicePostalCodeIrregularItemWriterTest.sql")
class ChoicePostalCodeIrregularItemWriterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private ChoicePostalCodeIrregularItemWriter choicePostalCodeIrregularItemWriter;

    /** 郵便番号作業Repository */
    @Autowired
    private WkTblPostalCommonRepository wkTblPostalCommonRepository;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 番地、丁目が２種入っているデータは住所リストを見ながらしないと修正できないので自動修正しない
        WkTblPostalCommonEntity worksEntity01 = new WkTblPostalCommonEntity();
        worksEntity01.setAddressOrg("門静（４丁目５５〜１１４番地）");

        // 編集対象
        WkTblPostalCommonEntity worksEntity06 = new WkTblPostalCommonEntity();
        String postal1 = "074";
        String postal2 = "1271";

        worksEntity06.setAddressPostalIrregularId(185);
        worksEntity06.setPostalcode1(postal1);
        worksEntity06.setPostalcode2(postal2);
        worksEntity06.setLgCode("012289");
        worksEntity06.setAddressOrg("広里町（１〜５丁目）");
        worksEntity06.setAddressName("深川市広里町");
        worksEntity06.setAddressPostal("");
        worksEntity06.setAddressBlock("");
        worksEntity06.setIsAddPostal(true);
        worksEntity06.setIsRepairRsdt(false);

        List<WkTblPostalCommonEntity> list = new ArrayList<>();
        list.add(worksEntity01);
        list.add(worksEntity06);

        // 事前に同一郵便番号件数を確認
        int countPre = addressPostalRepository.findByPostalcode1AndPostalcode2OrderByAddressNameAsc(postal1, postal2)
                .size();
        assertEquals(2, countPre, "作業前件数2");

        // Chunkを作成してセット
        Chunk<? extends WkTblPostalCommonEntity> items = new Chunk<>(list);
        choicePostalCodeIrregularItemWriter.beforeStep(this.getStepExecution());
        choicePostalCodeIrregularItemWriter.write(items);

        // 郵便番号は7件にデータが増えている
        List<AddressPostalEntity> listPro = addressPostalRepository
                .findByPostalcode1AndPostalcode2OrderByAddressNameAsc(postal1, postal2);
        assertEquals(7, listPro.size(), "作業前件数+5");

        AddressPostalEntity postalEntity0 = listPro.get(0);
        assertEquals(postal1, postalEntity0.getPostalcode1());
        assertEquals(postal2, postalEntity0.getPostalcode2());
        assertEquals("深川市広里町", postalEntity0.getAddressName());

        AddressPostalEntity postalEntity1 = listPro.get(1);
        assertEquals(postal1, postalEntity1.getPostalcode1());
        assertEquals(postal2, postalEntity1.getPostalcode2());
        assertEquals("深川市広里町一丁目", postalEntity1.getAddressName());

        AddressPostalEntity postalEntity2 = listPro.get(2);
        assertEquals(postal1, postalEntity2.getPostalcode1());
        assertEquals(postal2, postalEntity2.getPostalcode2());
        assertEquals("深川市広里町三丁目", postalEntity2.getAddressName());

        AddressPostalEntity postalEntity3 = listPro.get(3);
        assertEquals(postal1, postalEntity3.getPostalcode1());
        assertEquals(postal2, postalEntity3.getPostalcode2());
        assertEquals("深川市広里町二丁目", postalEntity3.getAddressName());

        AddressPostalEntity postalEntity4 = listPro.get(4);
        assertEquals(postal1, postalEntity4.getPostalcode1());
        assertEquals(postal2, postalEntity4.getPostalcode2());
        assertEquals("深川市広里町五丁目", postalEntity4.getAddressName());

        AddressPostalEntity postalEntity5 = listPro.get(5);
        assertEquals(postal1, postalEntity5.getPostalcode1());
        assertEquals(postal2, postalEntity5.getPostalcode2());
        assertEquals("深川市広里町四丁目", postalEntity5.getAddressName());

        AddressPostalEntity postalEntity6 = listPro.get(6);
        assertEquals(postal1, postalEntity6.getPostalcode1());
        assertEquals(postal2, postalEntity6.getPostalcode2());
        assertEquals("門静", postalEntity6.getAddressName());

        // 2件渡したが、1件は範囲複写対象外のため1件だけ登録がされた
        List<WkTblPostalCommonEntity> listAnswer = wkTblPostalCommonRepository.findAll();
        assertEquals(1, listAnswer.size(), "1件登録");

        WkTblPostalCommonEntity worksEntity = listAnswer.get(0);

        assertEquals(worksEntity06.getAddressPostalIrregularId(), worksEntity.getAddressPostalIrregularId());
        assertEquals(worksEntity06.getPostalcode1(), worksEntity.getPostalcode1());
        assertEquals(worksEntity06.getPostalcode2(), worksEntity.getPostalcode2());
        assertEquals(worksEntity06.getLgCode(), worksEntity.getLgCode());
        assertEquals(worksEntity06.getAddressOrg(), worksEntity.getAddressOrg());
        assertEquals(worksEntity06.getAddressName(), worksEntity.getAddressName());
        assertEquals(worksEntity06.getAddressPostal(), worksEntity.getAddressPostal());
        assertEquals(worksEntity06.getAddressBlock(), worksEntity.getAddressBlock());
        assertEquals(worksEntity06.getIsAddPostal(), worksEntity.getIsAddPostal());
        assertEquals(worksEntity06.getIsRepairRsdt(), worksEntity.getIsRepairRsdt());
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
