package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.master;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;

/**
 * DumpMasterPersonProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("DumpMasterPersonProcessorTest.sql")
class DumpMasterPersonProcessorTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private DumpMasterPersonProcessor dumpMasterPersonProcessor;

    /** 企業団体マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        KanrenshaPersonMasterEntity item = kanrenshaPersonMasterRepository.findById(1).get();

        DumpKanrenshaPersonMasterDto masterDto = dumpMasterPersonProcessor.process(item);

        assertEquals("DH-mkzsu-2mMW-rB8Y-Pl4zr", masterDto.getPersonKanrenshaCode());
        assertEquals("迂回献金　ミカエル太郎", masterDto.getKanrenshaName());
        assertEquals("宮崎県架空市橘通東２丁目１０−１", masterDto.getAllAddress());
        assertEquals("素浪人", masterDto.getPersonShokugyou());
        assertEquals("0985267132", masterDto.getPhon());
        assertEquals("https://my-portal/index.html", masterDto.getMyPortalUrl());
        assertEquals(LocalDateTime.of(2025, 12, 27, 16, 30, 24), masterDto.getInsertTimestamp());
    }

}
