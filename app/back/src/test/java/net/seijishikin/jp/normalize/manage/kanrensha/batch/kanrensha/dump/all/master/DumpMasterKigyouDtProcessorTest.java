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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;

/**
 * DumpMasterKigyouDtProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("DumpMasterKigyouDtProcessorTest.sql")
class DumpMasterKigyouDtProcessorTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private DumpMasterKigyouDtProcessor dumpMasterKigyouDtProcessor;

    /** 企業団体マスタRepository */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        KanrenshaKigyouDtMasterEntity item = kanrenshaKigyouDtMasterRepository.findById(1).get();

        DumpKanrenshaKigyouDtMasterDto masterDto = dumpMasterKigyouDtProcessor.process(item);

        assertEquals("1-2345-67-7RJC4W-BLXyCUF", masterDto.getKigyouDtKanrenshaCode());
        assertEquals("テスト株式会社", masterDto.getKanrenshaName());
        assertEquals("テストカブシキガイシャ", masterDto.getOrgNameKana());
        assertEquals("宮崎県架空市橘通東２丁目１０−１", masterDto.getAllAddress());
        assertEquals("1234567", masterDto.getHoujinNo());
        assertEquals("0985267132", masterDto.getPhon());
        assertEquals("https://my-portal/index.html", masterDto.getMyPortalUrl());
        assertEquals("代表者　太郎", masterDto.getKigyouDtDelegate());
        assertEquals("P12345", masterDto.getOrgDelegateCode());
        assertEquals(LocalDateTime.of(2025, 12, 27, 14, 51, 37), masterDto.getInsertTimestamp());
    }

}
