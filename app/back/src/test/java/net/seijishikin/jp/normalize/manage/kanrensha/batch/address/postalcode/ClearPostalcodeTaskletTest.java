package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;

/**
 * ClearPostalcodeTasklet単体テスト
 */
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("ClearPostalcodeTaskletTest.sql")
class ClearPostalcodeTaskletTest {

    /** テスト対象 */
    @Autowired
    private ClearPostalcodeTasklet clearPostalcodeTasklet;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        clearPostalcodeTasklet.execute(null, null);

        List<AddressPostalEntity> listPostal = addressPostalRepository.findAll();
        assertTrue(listPostal.isEmpty());

        List<AddressPostalIrregularEntity> listIrregular = addressPostalIrregularRepository.findAll();
        assertTrue(listIrregular.isEmpty());

        // insertしたときのId値は1
        addressPostalRepository.save(new AddressPostalEntity());
        addressPostalIrregularRepository.save(new AddressPostalIrregularEntity());

        assertDoesNotThrow(() -> addressPostalIrregularRepository.findById(1).get());
        assertDoesNotThrow(() -> addressPostalRepository.findById(1).get());
    }

}
