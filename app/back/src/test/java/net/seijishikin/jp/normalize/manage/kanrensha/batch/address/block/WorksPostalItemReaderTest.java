package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;

/**
 * WorksPostalItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("WorksPostalItemReaderTest.sql")
class WorksPostalItemReaderTest {
    // CHECKSTYLE:OFF MagicNumebr

    /** テスト対象 */
    @Autowired
    private WorksPostalItemReader worksPostalItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        WkTblPostalCommonEntity entity0 = worksPostalItemReader.read();
        assertEquals(103, entity0.getWkTblPostalCommonId());

        WkTblPostalCommonEntity entity1 = worksPostalItemReader.read();
        assertEquals(104, entity1.getWkTblPostalCommonId());

        WkTblPostalCommonEntity entity2 = worksPostalItemReader.read();
        assertEquals(105, entity2.getWkTblPostalCommonId());

        assertNull(worksPostalItemReader.read());
    }

}
