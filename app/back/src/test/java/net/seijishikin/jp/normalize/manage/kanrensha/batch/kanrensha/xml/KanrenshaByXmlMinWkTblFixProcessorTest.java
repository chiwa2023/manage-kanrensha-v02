package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlResultEntity;

import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

/**
 * KanrenshaByXmlMinWkTblFixProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("KanrenshaByXmlMinWkTblFixProcessorTest.sql")
class KanrenshaByXmlMinWkTblFixProcessorTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private KanrenshaByXmlMinWkTblFixProcessor kanrenshaByXmlMinWkTblFixProcessor;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {
        WkTblMasterAllByXmlResultEntity item = new WkTblMasterAllByXmlResultEntity();
        item.setWkTblMasterAllByXmlId(344);

        WkTblMasterAllByXmlEntity entity = kanrenshaByXmlMinWkTblFixProcessor.process(item);

        assertEquals(item.getWkTblMasterAllByXmlId(), entity.getWkTblMasterAllByXmlId());
        assertEquals(true, entity.getIsFinish());
    }

}
