package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021;

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
 * CheckExistKanrenshaCombineY2021Logic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("CheckExistKanrenshaCombineY2021LogicTest.sql")
class CheckExistKanrenshaCombineY2021LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CheckExistKanrenshaCombineY2021Logic checkExistKanrenshaCombineY2021Logic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        YearOptionEntity optionEntity = checkExistKanrenshaCombineY2021Logic.practice("101", "102", (short) 2);

        assertEquals(2021, optionEntity.getSelectedYear());
        assertTrue(optionEntity.getIsSelected());
    }

}
