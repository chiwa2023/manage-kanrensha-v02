package net.seijishikin.jp.normalize.manage.kanrensha.service.task_info;

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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.task_info.SearchTaskInfoCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task_info.SearchTaskInfoResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskInfoEntity;

/**
 * SearchTaskInfoService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchTaskInfoServiceTest.sql")
@Transactional
class SearchTaskInfoServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchTaskInfoService searchTaskInfoService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        SearchTaskInfoCapsuleDto capsuleDto = new SearchTaskInfoCapsuleDto();
        capsuleDto.setAllCount(100);
        capsuleDto.setLimit(20);
        capsuleDto.setPageNumber(4);
        capsuleDto.setSearchNaturalWords("名称");
        capsuleDto.setTaskType("3");

        SearchTaskInfoResultDto resultDto = searchTaskInfoService.practice(capsuleDto);

        assertEquals(2, resultDto.getAllCount());
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(0, resultDto.getPageNumber()); // 計算が合わないときは初期化

        List<TaskInfoEntity> listAns = resultDto.getListTask();
        assertEquals(2, listAns.size());

        TaskInfoEntity entity0 = listAns.get(0);
        assertEquals(423, entity0.getTaskInfoId());
        TaskInfoEntity entity1 = listAns.get(1);
        assertEquals(424, entity1.getTaskInfoId());
    }

}
