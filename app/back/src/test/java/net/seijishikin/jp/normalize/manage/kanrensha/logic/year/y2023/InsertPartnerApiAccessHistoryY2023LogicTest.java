package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2023.PartnerAccessHistory2023Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2023.PartnerAccessHistory2023Repository;

/**
 * InsertPartnerApiAccessHistoryY2023Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
// @Transactional
@Sql("InsertPartnerApiAccessHistoryY2023LogicTest.sql")
class InsertPartnerApiAccessHistoryY2023LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private InsertPartnerApiAccessHistoryY2023Logic insertPartnerApiAccessHistoryY2023Logic;

    /** APIパートナー履歴Repository(2023) */
    @Autowired
    private PartnerAccessHistory2023Repository partnerAccessHistory2023Repository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        PartnerAccessHistoryBaseEntity baseEntity = new PartnerAccessHistoryBaseEntity();
        baseEntity.setAccessUrl("/123/456");
        baseEntity.setAttemptTime(LocalDateTime.of(2023, 4, 19, 12, 3, 45));
        baseEntity.setIpAddress("127.0.0.1"); // NOPMD
        baseEntity.setIsSuccess(true);
        baseEntity.setUserAgent("NetScape");
        baseEntity.setUserCode(190);
        baseEntity.setUserName("管理者　太郎");

        Integer newId = insertPartnerApiAccessHistoryY2023Logic.practice(baseEntity);

        PartnerAccessHistory2023Entity entity0 = partnerAccessHistory2023Repository.findById(newId).get();

        assertEquals(newId, entity0.getPartnerAccessHistoryId());
        assertEquals(baseEntity.getAccessUrl(), entity0.getAccessUrl());
        assertEquals(baseEntity.getAttemptTime(), entity0.getAttemptTime());
        assertEquals(baseEntity.getIpAddress(), entity0.getIpAddress());
        assertEquals(baseEntity.getIsSuccess(), entity0.getIsSuccess());
        assertEquals(baseEntity.getUserAgent(), entity0.getUserAgent());
        assertEquals(baseEntity.getUserCode(), entity0.getUserCode());
        assertEquals(baseEntity.getUserName(), entity0.getUserName());
    }

}
