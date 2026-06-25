package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

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

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressCityDeleteEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressCityDeleteRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * AllCityWkTblItemWriter単体テスト
 */
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("AllCityWkTblItemWriterTest.sql")
class AllCityWkTblItemWriterTest {

    /** テスト対象 */
    @Autowired
    private AllCityWkTblItemWriter allCityWkTblItemWriter;

    /** 地方自治体コード削除Repository */
    @Autowired
    private AddressCityDeleteRepository addressCityDeleteRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddressCityDeleteEntity entity01 = new AddressCityDeleteEntity();
        entity01.setLgCode("112233");
        entity01.setOrgName("和歌山県特別区実在市山麓区");

        List<AddressCityDeleteEntity> list = new ArrayList<>();
        list.add(entity01);

        // Chunkを作成してセット
        Chunk<? extends AddressCityDeleteEntity> items = new Chunk<>(list);

        allCityWkTblItemWriter.beforeStep(this.getStepExecution());
        allCityWkTblItemWriter.write(items);

        List<AddressCityDeleteEntity> listAnswer = addressCityDeleteRepository.findAll();
        assertEquals(1, listAnswer.size(), "1件登録");

        AddressCityDeleteEntity answerEntity00 = listAnswer.get(0);

        assertEquals(entity01.getLgCode(), answerEntity00.getLgCode());
        assertEquals(entity01.getOrgName(), answerEntity00.getOrgName());
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
