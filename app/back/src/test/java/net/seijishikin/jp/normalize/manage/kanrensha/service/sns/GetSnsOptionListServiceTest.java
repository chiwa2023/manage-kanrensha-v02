package net.seijishikin.jp.normalize.manage.kanrensha.service.sns;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.sns.SnsServiceOptionDto;

/**
 * GetSnsOptionListService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetSnsOptionListServiceTest.sql")
@Transactional
class GetSnsOptionListServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetSnsOptionListService getSnsOptionListService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        List<SnsServiceOptionDto> list = getSnsOptionListService.practice();
        assertEquals(3, list.size());

        SnsServiceOptionDto dto0 = list.get(0);
        assertEquals(0, dto0.getValue());
        assertEquals("", dto0.getText());
        assertEquals(0, dto0.getServiceCode());
        assertEquals("", dto0.getPortalUrl());

        SnsServiceOptionDto dto1 = list.get(1);
        assertEquals(497, dto1.getValue());
        assertEquals("SNS-A", dto1.getText());
        assertEquals(246, dto1.getServiceCode());
        assertEquals("https://service1.net/", dto1.getPortalUrl());

        SnsServiceOptionDto dto2 = list.get(2);
        assertEquals(498, dto2.getValue());
        assertEquals("SNS-B", dto2.getText());
        assertEquals(129, dto2.getServiceCode());
        assertEquals("https://service2.net/", dto2.getPortalUrl());
    }

}
