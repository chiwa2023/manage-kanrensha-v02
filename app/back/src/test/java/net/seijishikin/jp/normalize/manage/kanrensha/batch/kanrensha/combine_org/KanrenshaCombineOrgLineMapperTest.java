package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * KanrenshaCombineOrgLineMapper単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class KanrenshaCombineOrgLineMapperTest {

    @Test
    void test() throws Exception {

        KanrenshaCombineOrgLineMapper lineMapper = new KanrenshaCombineOrgLineMapper();

        KanrenshaCombineOrgDto dto = lineMapper.mapLine(
                "\"12-34567-8901-2345-67890\",\"迂回献金　太郎\",\"1-2345-67-890123-4567890\",\"ふんだくり企業\",\"2021\",\"abcd\"",
                0);

        assertEquals("12-34567-8901-2345-67890", dto.getPersonKanrenshaCode());
        assertEquals("迂回献金　太郎", dto.getPersonName());
        assertEquals("1-2345-67-890123-4567890", dto.getOrgKanrenshaCode());
        assertEquals("ふんだくり企業", dto.getOrgName());
        assertEquals(Short.valueOf("2021"), dto.getStartYear());
        assertEquals(Short.valueOf("-1"), dto.getEndYear()); // 数字変換できないときは-1
    }


}
