package net.seijishikin.jp.normalize.manage.kanrensha.service.year_option;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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
 * GetYearOptionService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetYearOptionServiceTest.sql")
class GetYearOptionServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetYearOptionService getYearOptionService;

    @Test
    @Tag("TableTruncate")
    void test() {

        List<YearOptionEntity> list = getYearOptionService.practice();

        assertEquals(3, list.size());

        YearOptionEntity entity0 = list.get(0);
        assertEquals(2020, entity0.getSelectedYear());

        YearOptionEntity entity1 = list.get(1);
        assertEquals(2022, entity1.getSelectedYear());

        YearOptionEntity entity2 = list.get(2);
        assertEquals(2024, entity2.getSelectedYear());
    }

}
