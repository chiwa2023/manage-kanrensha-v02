package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
 * SelectPostalCodeSingleAddressItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SelectPostalCodeSingleAddressItemWriterTest.sql")
class SelectPostalCodeSingleAddressItemWriterTest {

    /** テスト対象 */
    @Autowired
    private SelectPostalCodeSingleAddressItemWriter selectPostalCodeSingleAddressItemWriter;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** 郵便番号作業Repository */
    @Autowired
    private WkTblPostalCommonRepository wkTblPostalCommonRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        WkTblPostalCommonEntity worksEntity00 = new WkTblPostalCommonEntity();
        worksEntity00.setLgCode("012351");
        worksEntity00.setAddressName("石狩市八幡町");
        worksEntity00.setAddressOrg("八幡町（五の沢）");
        worksEntity00.setPostalcode1("061");
        worksEntity00.setPostalcode2("3480");

        List<WkTblPostalCommonEntity> list = new ArrayList<>();
        list.add(worksEntity00);

        // 処理前は住居テーブルを参照しない設定
        AddressPostalEntity postalPreEntity = addressPostalRepository
                .findByPostalcode1AndPostalcode2OrderByAddressNameAsc(worksEntity00.getPostalcode1(),
                        worksEntity00.getPostalcode2())
                .get(0);
        assertFalse(postalPreEntity.getIsGyoseikuData());
        assertEquals("石狩市八幡町", postalPreEntity.getAddressName());

        Chunk<? extends WkTblPostalCommonEntity> items = new Chunk<>(list);
        selectPostalCodeSingleAddressItemWriter.beforeStep(this.getStepExecution());
        selectPostalCodeSingleAddressItemWriter.write(items);

        // 作業テーブルに保存されている
        List<WkTblPostalCommonEntity> listWorks = wkTblPostalCommonRepository.findAll();
        assertEquals(1, listWorks.size());
        WkTblPostalCommonEntity worksEntity = list.get(0);
        assertEquals(worksEntity00.getLgCode(), worksEntity.getLgCode());
        assertEquals(worksEntity00.getAddressName(), worksEntity.getAddressName());
        assertEquals(worksEntity00.getAddressOrg(), worksEntity.getAddressOrg());
        assertEquals(worksEntity00.getPostalcode1(), worksEntity.getPostalcode1());
        assertEquals(worksEntity00.getPostalcode2(), worksEntity.getPostalcode2());

        // 処理後は住居テーブルを参照する設定に変更かつ呼び出し住所が更新
        AddressPostalEntity postalProEntity = addressPostalRepository
                .findByPostalcode1AndPostalcode2OrderByAddressNameAsc(worksEntity00.getPostalcode1(),
                        worksEntity00.getPostalcode2())
                .get(0);
        assertTrue(postalProEntity.getIsGyoseikuData());
        assertEquals("石狩市八幡町五の沢", postalProEntity.getAddressName());
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
