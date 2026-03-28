package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblPostalCommonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * FixIkaniKeisaiNashiItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("FixIkaniKeisaiNashiItemWriterTest.sql")
class FixIkaniKeisaiNashiItemWriterTest {

    /** テスト対象 */
    @Autowired
    private FixIkaniKeisaiNashiItemWriter fixIkaniKeisaiNashiItemWriter;

    /** 郵便番号ワークテーブルRepository */
    @Autowired
    private WkTblPostalCommonRepository wkTblPostalCommonRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        WkTblPostalCommonEntity worksEntity01 = new WkTblPostalCommonEntity();
        worksEntity01.setLgCode("827637");
        worksEntity01.setPostalcode1("123");
        worksEntity01.setPostalcode2("4567");
        worksEntity01.setAddressOrg("以下に掲載がない場合");
        worksEntity01.setAddressName("北海道架空市湖畔町以下に掲載がない場合");
        worksEntity01.setIsGyoseikuData(true);
        worksEntity01.setIsLatest(false); // 最新データしか抽出していないので実際にはありえない嘘データ

        // 編集対象
        WkTblPostalCommonEntity worksEntity02 = new WkTblPostalCommonEntity();
        worksEntity02.setLgCode("827637");
        worksEntity02.setPostalcode1("123");
        worksEntity02.setPostalcode2("4567");
        worksEntity02.setAddressOrg("以下に掲載がない場合");
        worksEntity02.setAddressName("北海道架空市山麓町以下に掲載がない場合");
        worksEntity02.setIsGyoseikuData(true);
        worksEntity02.setIsLatest(false); // 最新データしか抽出していないので実際にはありえない嘘データ

        List<WkTblPostalCommonEntity> list = new ArrayList<>();
        list.add(worksEntity01);
        list.add(worksEntity02);

        Chunk<? extends WkTblPostalCommonEntity> items = new Chunk<>(list);
        fixIkaniKeisaiNashiItemWriter.beforeStep(this.getStepExecution());
        fixIkaniKeisaiNashiItemWriter.write(items);

        // 02には登録されなくて無視される(山麓町のデータは存在するが郵便番号が割り当たっている)
        List<WkTblPostalCommonEntity> listAns = wkTblPostalCommonRepository.findAll();
        assertEquals(1, listAns.size());

        WkTblPostalCommonEntity entityAns = list.get(0);
        assertEquals(worksEntity01.getLgCode(), entityAns.getLgCode());
        assertEquals(worksEntity01.getPostalcode1(), entityAns.getPostalcode1());
        assertEquals(worksEntity01.getPostalcode2(), entityAns.getPostalcode2());
        assertEquals(worksEntity01.getAddressOrg(), entityAns.getAddressOrg());
        assertEquals(worksEntity01.getAddressName(), entityAns.getAddressName());
        assertEquals(worksEntity01.getIsGyoseikuData(), entityAns.getIsGyoseikuData());
        assertTrue(entityAns.getIsLatest()); // 引き続き使用するデータ
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
