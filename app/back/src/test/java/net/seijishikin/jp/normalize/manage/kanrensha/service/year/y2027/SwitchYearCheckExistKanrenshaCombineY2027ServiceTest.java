package net.seijishikin.jp.normalize.manage.kanrensha.service.year.y2027;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine.SearchKanrenshaCombineCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.year_option.YearOptionResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.YearOptionEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.YearOptionRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearCheckExistKanrenshaCombineService;

/**
 * SwitchYearCheckExistKanrenshaCombineService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SwitchYearCheckExistKanrenshaCombineY2027ServiceTest.sql")
class SwitchYearCheckExistKanrenshaCombineY2027ServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearCheckExistKanrenshaCombineService switchYearCheckExistKanrenshaCombineService;

    /** 年選択肢Repository */
    @Autowired
    private YearOptionRepository yearOptionRepository;

    @Test
    @Tag("TableTruncate")
    void test2027() {

        SearchKanrenshaCombineCapsuleDto capsuleDto = new SearchKanrenshaCombineCapsuleDto();
        capsuleDto.setPersonCode("101");
        capsuleDto.setOrgCode("102");
        capsuleDto.setKanrenshaKbn((short) 2);

        YearOptionResultDto resultDto = switchYearCheckExistKanrenshaCombineService.practice(capsuleDto);

        List<YearOptionEntity> list = resultDto.getListEntity();
        // 常に選択肢数に一致する
        assertEquals(Math.toIntExact(yearOptionRepository.count()), list.size());
        // データが存在する場合は選択済
        assertTrue(this.getEntity(2027, list).getIsSelected());
    }

    private YearOptionEntity getEntity(final Integer year, final List<YearOptionEntity> list) {

        for (YearOptionEntity entity : list) {
            if (year.equals(entity.getSelectedYear())) {
                return entity;
            }
        }

        return null;
    }

}
