package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2024.KanrenshaCombineOrg2024Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2024.KanrenshaCombineOrg2024Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertCombineOrgY2024Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("InsertCombineOrgY2024LogicTest.sql")
class InsertCombineOrgY2024LogicTest {

    /** テスト対象 */
    @Autowired
    private InsertCombineOrgY2024Logic insertCombineOrgY2024Logic;

    /** 個人団体紐づけRepository(2024) */
    @Autowired
    private KanrenshaCombineOrg2024Repository kanrenshaCombineOrg2024Repository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        WkTblKanrenshaCombineOrgEntity entity00 = new WkTblKanrenshaCombineOrgEntity();

        entity00.setKanrenshaKbn(KanrenshaKbnConstants.KIGYOU_DT);
        entity00.setPersonKanrenshaCode("111-222");
        entity00.setPersonName("迂回献金　太郎");
        entity00.setOrgKanrenshaCode("333-555");
        entity00.setOrgName("ちゃらんぽらん政治団体");
        entity00.setStartYear(Short.valueOf("2024"));
        entity00.setEndYear(Short.valueOf("2024"));
        entity00.setYearArrayText("2024");
        entity00.setIsAffected(true);
        entity00.setIsFinish(false);
        entity00.setJudgeReason("理由");

        Integer insertId = insertCombineOrgY2024Logic.practice(entity00, CreateLeastUserForTestUtil.practice());
        assertNotEquals(0, insertId); // 正常に登録

        KanrenshaCombineOrg2024Entity entityAns = kanrenshaCombineOrg2024Repository.findById(insertId).get();

        assertEquals(entity00.getKanrenshaKbn(), entityAns.getKanrenshaKbn());
        assertEquals(entity00.getPersonKanrenshaCode(), entityAns.getPersonKanrenshaCode());
        assertEquals(entity00.getPersonName(), entityAns.getPersonName());
        assertEquals(entity00.getOrgKanrenshaCode(), entityAns.getOrgKanrenshaCode());
        assertEquals(entity00.getOrgName(), entityAns.getOrgName());
    }

}
