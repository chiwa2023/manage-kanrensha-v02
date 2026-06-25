package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;

/**
 * PostalCodeIrregularItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("PostalCodeIrregularItemReaderTest.sql")
class PostalCodeIrregularItemReaderTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private PostalCodeIrregularItemReader postalCodeIrregularItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 取得できる規則データ
        AddressPostalEntity entity0 = postalCodeIrregularItemReader.read();
        assertEquals(834, entity0.getAddressPostalId());

        AddressPostalEntity entity1 = postalCodeIrregularItemReader.read();
        assertEquals(835, entity1.getAddressPostalId());

        AddressPostalEntity entity2 = postalCodeIrregularItemReader.read();
        assertEquals(836, entity2.getAddressPostalId());

        assertNull(postalCodeIrregularItemReader.read());
    }

}
