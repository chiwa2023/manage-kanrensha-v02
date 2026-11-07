package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.YearOptionEntity;

/**
 * CheckExistKanrenshaCombineY2022Logic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("CheckExistKanrenshaCombineY2022LogicTest.sql")
class CheckExistKanrenshaCombineY2022LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CheckExistKanrenshaCombineY2022Logic checkExistKanrenshaCombineY2022Logic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        YearOptionEntity optionEntity = checkExistKanrenshaCombineY2022Logic.practice("101", "102", (short) 2);

        assertEquals(2022, optionEntity.getSelectedYear());
        assertTrue(optionEntity.getIsSelected());
    }

}
