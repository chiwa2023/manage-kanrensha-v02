package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2022.KanrenshaCombineOrg2022Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2022.KanrenshaCombineOrg2022Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertCombineOrgY2022Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("InsertCombineOrgY2022LogicTest.sql")
class InsertCombineOrgY2022LogicTest {

    /** テスト対象 */
    @Autowired
    private InsertCombineOrgY2022Logic insertCombineOrgY2022Logic;

    /** 個人団体紐づけRepository(2022) */
    @Autowired
    private KanrenshaCombineOrg2022Repository kanrenshaCombineOrg2022Repository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        WkTblKanrenshaCombineOrgEntity entity00 = new WkTblKanrenshaCombineOrgEntity();

        entity00.setKanrenshaKbn(KanrenshaKbnConstants.KIGYOU_DT);
        entity00.setPersonKanrenshaCode("111-222");
        entity00.setPersonName("迂回献金　太郎");
        entity00.setOrgKanrenshaCode("333-555");
        entity00.setOrgName("ちゃらんぽらん政治団体");
        entity00.setStartYear(Short.valueOf("2022"));
        entity00.setEndYear(Short.valueOf("2022"));
        entity00.setYearArrayText("2022");
        entity00.setIsAffected(true);
        entity00.setIsFinish(false);
        entity00.setJudgeReason("理由");

        Integer insertId = insertCombineOrgY2022Logic.practice(entity00, CreateLeastUserForTestUtil.practice());
        assertNotEquals(0, insertId); // 正常に登録

        KanrenshaCombineOrg2022Entity entityAns = kanrenshaCombineOrg2022Repository.findById(insertId).get();

        assertEquals(entity00.getKanrenshaKbn(), entityAns.getKanrenshaKbn());
        assertEquals(entity00.getPersonKanrenshaCode(), entityAns.getPersonKanrenshaCode());
        assertEquals(entity00.getPersonName(), entityAns.getPersonName());
        assertEquals(entity00.getOrgKanrenshaCode(), entityAns.getOrgKanrenshaCode());
        assertEquals(entity00.getOrgName(), entityAns.getOrgName());
    }

}
