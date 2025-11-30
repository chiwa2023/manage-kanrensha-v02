package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaManagerCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaManagerResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaManagerMasterEntity;

/**
 * SearchRiyoushaManagerService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchRiyoushaManagerServiceTest.sql")
class SearchRiyoushaManagerServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchRiyoushaManagerService searchRiyoushaManagerService;

    @Test
    @Tag("NaturalSearch")
    void test() throws Exception {

        SearchRiyoushaManagerCapsuleDto capsuleDto = new SearchRiyoushaManagerCapsuleDto();
        capsuleDto.setLimit(20);
        capsuleDto.setSearchNaturalWords("管理者");
        capsuleDto.setPageNumber(0);

        SearchRiyoushaManagerResultDto resultDto = searchRiyoushaManagerService.practice(capsuleDto);

        assertEquals(2, resultDto.getAllCount());
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(0, resultDto.getPageNumber());

        assertNotNull(resultDto.getListRiyoushaManager());

        List<RiyoushaManagerMasterEntity> list = resultDto.getListRiyoushaManager();

        RiyoushaManagerMasterEntity entity00 = list.get(0);
        RiyoushaManagerMasterEntity entity01 = list.get(1);

        assertEquals(1, entity00.getRiyoushaManagerMasterId());
        assertEquals(3, entity01.getRiyoushaManagerMasterId());

        // 無茶なページ番号は補正
        capsuleDto.setPageNumber(66);
        SearchRiyoushaManagerResultDto resultDto1 = searchRiyoushaManagerService.practice(capsuleDto);
        assertEquals(2, resultDto1.getAllCount());
        assertEquals(capsuleDto.getLimit(), resultDto1.getLimit());
        assertEquals(0, resultDto1.getPageNumber());
        assertEquals(2, resultDto1.getListRiyoushaManager().size());

        // 検索できなくても必要なDtoは戻る
        capsuleDto.setSearchNaturalWords("検索できない");
        capsuleDto.setPageNumber(3);
        SearchRiyoushaManagerResultDto resultDto2 = searchRiyoushaManagerService.practice(capsuleDto);
        assertEquals(0, resultDto2.getAllCount());
        assertEquals(capsuleDto.getLimit(), resultDto2.getLimit());
        assertEquals(0, resultDto2.getPageNumber());
        assertTrue(resultDto2.getListRiyoushaManager().isEmpty());
    }

}
