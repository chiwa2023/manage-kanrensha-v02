package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.KanrenshaCombineOrg2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.KanrenshaCombineOrg2026Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * UpdateCombineOrgY2026Logic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional // 悲観ロック用
@Sql("UpdateCombineOrgY2026LogicTest.sql")
class UpdateCombineOrgY2026LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private UpdateCombineOrgY2026Logic updateCombineOrgY2026Logic;

    /** 個人団体紐づけRepository(2026) */
    @Autowired
    private KanrenshaCombineOrg2026Repository kanrenshaCombineOrg2026Repository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        // 保存する・すでにデータが存在する
        WkTblKanrenshaCombineOrgEntity entityWkTbl0 = new WkTblKanrenshaCombineOrgEntity();
        entityWkTbl0.setPersonKanrenshaCode("101");
        entityWkTbl0.setOrgKanrenshaCode("102");
        entityWkTbl0.setKanrenshaKbn((short)2);
        Integer updateId0 = updateCombineOrgY2026Logic.practice(entityWkTbl0, true, userDto);
        assertEquals(0, updateId0);

        // 保存する・データが存在しない
        WkTblKanrenshaCombineOrgEntity entityWkTbl1 = new WkTblKanrenshaCombineOrgEntity();
        entityWkTbl1.setPersonKanrenshaCode("301");
        entityWkTbl1.setPersonName("301名称");
        entityWkTbl1.setOrgKanrenshaCode("302");
        entityWkTbl1.setOrgName("302名称");

        // 履歴が保存されている
        Integer updateId1 = updateCombineOrgY2026Logic.practice(entityWkTbl1, true, userDto);
        assertNotEquals(0, updateId1);
        KanrenshaCombineOrg2026Entity entityAns1 = kanrenshaCombineOrg2026Repository.findById(updateId1).get();
        assertTrue(entityAns1.getIsLatest());
        assertEquals(entityWkTbl1.getPersonKanrenshaCode(), entityAns1.getPersonKanrenshaCode());
        assertEquals(entityWkTbl1.getPersonName(), entityAns1.getPersonName());
        assertEquals(entityWkTbl1.getOrgKanrenshaCode(), entityAns1.getOrgKanrenshaCode());
        assertEquals(entityWkTbl1.getOrgName(), entityAns1.getOrgName());

        // 保存しない・最新データが存在する
        WkTblKanrenshaCombineOrgEntity entityWkTbl2 = new WkTblKanrenshaCombineOrgEntity();
        entityWkTbl2.setPersonKanrenshaCode("201");
        entityWkTbl2.setOrgKanrenshaCode("202");
        entityWkTbl2.setKanrenshaKbn((short)3);

        // 履歴に更新
        Integer updateId2 = updateCombineOrgY2026Logic.practice(entityWkTbl2, false, userDto);
        assertEquals(1, updateId2); // 複数更新可能性あり、1件更新
        KanrenshaCombineOrg2026Entity entityAns2 = kanrenshaCombineOrg2026Repository.findById(3).get();
        assertFalse(entityAns2.getIsLatest());

        // 保存しない・データが存在しない
        WkTblKanrenshaCombineOrgEntity entityWkTbl3 = new WkTblKanrenshaCombineOrgEntity();
        entityWkTbl3.setPersonKanrenshaCode("401");
        entityWkTbl3.setOrgKanrenshaCode("402");
        Integer updateId3 = updateCombineOrgY2026Logic.practice(entityWkTbl3, false, userDto);
        assertEquals(0, updateId3);
    }

}
