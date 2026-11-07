package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * PostalCodeOneLineProcessor単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PostalCodeOneLineProcessorTest {

    /** テスト対象 */
    @Autowired
    private PostalCodeOneLineProcessor postalCodeOneLineProcessor;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        postalCodeOneLineProcessor.beforeStep(this.getStepExecution());
        
        PostalCodeCsvOneLineDto item = new PostalCodeCsvOneLineDto();
        item.setLgCode("26366");
        item.setPostalcode("6190248");
        item.setPref("京都府");
        item.setCity("相楽郡精華町");
        item.setAddressOrg("新精台");

        AddressPostalEntity postalEntity = postalCodeOneLineProcessor.process(item);

        assertEquals("263664", postalEntity.getLgCode());
        assertEquals("619", postalEntity.getPostalcode1());
        assertEquals("0248", postalEntity.getPostalcode2());
        assertEquals("京都府相楽郡精華町新精台", postalEntity.getAddressName());
        assertEquals(item.getAddressOrg(), postalEntity.getAddressOrg());
        assertTrue(postalEntity.getIsGyoseikuData());
    }

    private StepExecution getStepExecution() {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName())
                .addString("lgCode", "26366")
                .toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
