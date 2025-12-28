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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;

/**
 * DumpMasterSeijidantaiProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("DumpMasterSeijidantaiProcessorTest.sql")
class DumpMasterSeijidantaiProcessorTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private DumpMasterSeijidantaiProcessor dumpMasterSeijidantaiProcessor;

    /** 政治団体マスタRepository */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        KanrenshaSeijidantaiMasterEntity item = kanrenshaSeijidantaiMasterRepository.findById(1).get();

        DumpKanrenshaSeijidantaiMasterDto masterDto = dumpMasterSeijidantaiProcessor.process(item);

        assertEquals("984-321V-kZNH-uJUw-Alcr1", masterDto.getSeijidantaiKanrenshaCode());
        assertEquals("ちゃらんぽらん政治団体", masterDto.getKanrenshaName());
        assertEquals("ちゃらんぽらんせいじだんたい", masterDto.getOrgNameKana());
        assertEquals("宮崎県架空市橘通東２丁目１０−１", masterDto.getAllAddress());
        assertEquals("98-4321", masterDto.getPoliOrgNo());
        assertEquals("03", masterDto.getDantaiKbn());
        assertEquals("政治資金団体", masterDto.getDantaiKbnLabel());
        assertEquals("0985267132", masterDto.getPhon());
        assertEquals("https://my-portal/index.html", masterDto.getMyPortalUrl());
        assertEquals("代表者　太郎", masterDto.getSeijidantaiDelegate());
        assertEquals("P12345", masterDto.getOrgDelegateCode());
        assertEquals("会計責任者　花子", masterDto.getAccountMgrName());
        assertEquals("P98765", masterDto.getAccountMgrCode());
        assertEquals(LocalDateTime.of(2025, 12, 27, 17, 22, 43), masterDto.getInsertTimestamp());
    }

}
