package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * KanrenshaByXmlMinWkTblFixItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("KanrenshaByXmlMinWkTblFixItemWriterTest.sql")
class KanrenshaByXmlMinWkTblFixItemWriterTest {

    /** テスト対象 */
    @Autowired
    private KanrenshaByXmlMinWkTblFixItemWriter kanrenshaByXmlMinWkTblFixItemWriter;

    /** 関連者政治団体登録最小限Repository */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /** テスト用ユーザ最小限Dto */
    private final LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

    @Test
    @Tag("TableTruncate")
    void test() {
        List<WkTblMasterAllByXmlEntity> listLoad = wkTblMasterAllByXmlRepository
                .findByInsertUserCodeAndIsLatestAndIsAffectedAndIsFinish(userDto.getUserPersonCode(),
                        SetTableDataHistoryUtil.INSERT_STATE, true, false, Pageable.unpaged())
                .toList();

        WkTblMasterAllByXmlEntity entity00 = listLoad.get(0);
        entity00.setIsAffected(true);
        entity00.setIsFinish(true);
        entity00.setJudgeReason("正常");

        List<WkTblMasterAllByXmlEntity> list = new ArrayList<>();
        list.addAll(listLoad);

        // Chunkを作成してセット
        Chunk<? extends WkTblMasterAllByXmlEntity> items = new Chunk<>(listLoad);

        kanrenshaByXmlMinWkTblFixItemWriter.beforeStep(this.getStepExecution());
        kanrenshaByXmlMinWkTblFixItemWriter.write(items);

        WkTblMasterAllByXmlEntity entityAns = wkTblMasterAllByXmlRepository
                .findById(entity00.getWkTblMasterAllByXmlId()).get();

        assertEquals(entity00.getIsAffected(), entityAns.getIsAffected());
        assertEquals(entity00.getIsFinish(), entityAns.getIsFinish());
        assertEquals(entity00.getJudgeReason(), entityAns.getJudgeReason());
    }

    private StepExecution getStepExecution() {
        JobParameters jobParameters = new JobParametersBuilder().addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
