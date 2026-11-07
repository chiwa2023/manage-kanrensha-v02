package net.seijishikin.jp.normalize.manage.kanrensha.service.dump_record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.DumpRecordEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.DumpRecordRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertDupmRunRecordService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("InsertDupmRunRecordServiceTest.sql")
class InsertDupmRunRecordServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private InsertDupmRunRecordService insertDupmRunRecordService;

    /** ダンプ実行記録Respoitory */
    @Autowired
    private DumpRecordRepository dumpRecordRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 最小マスタを個人・企業／団体・政治団体と3つ一緒に動作させた(最もありうるパターン)
        List<Integer> listTask = new ArrayList<>();
        listTask.add(TaskInfoConstants.DUMP_MIN_KIGYOU);
        listTask.add(TaskInfoConstants.DUMP_MIN_PERSON);
        listTask.add(TaskInfoConstants.DUMP_MIN_SEIJIDANTAI);

        LocalDateTime startDatetime = LocalDateTime.of(2022, 12, 13, 14, 15, 16);
        LocalDateTime endDatetime = LocalDateTime.of(2024, 7, 22, 1, 2, 3);
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        List<Integer> listAddId = insertDupmRunRecordService.practice(listTask, startDatetime, endDatetime, userDto);

        assertEquals(listTask.size(), listAddId.size());
        assertEquals(3, listAddId.size());

        DumpRecordEntity entity0 = dumpRecordRepository.findById(listAddId.get(0)).get();
        assertEquals((int) listTask.get(0), entity0.getTaskInfoCode());
        assertEquals(startDatetime, entity0.getStartDatetime());
        assertEquals(endDatetime, entity0.getEndDatetime());

        DumpRecordEntity entity1 = dumpRecordRepository.findById(listAddId.get(1)).get();
        assertEquals((int) listTask.get(1), entity1.getTaskInfoCode());
        assertEquals(startDatetime, entity1.getStartDatetime());
        assertEquals(endDatetime, entity1.getEndDatetime());

        DumpRecordEntity entity2 = dumpRecordRepository.findById(listAddId.get(2)).get();
        assertEquals((int) listTask.get(2), entity2.getTaskInfoCode());
        assertEquals(startDatetime, entity2.getStartDatetime());
        assertEquals(endDatetime, entity2.getEndDatetime());

        // 最新だったデータは履歴になっている
        assertFalse(dumpRecordRepository.findById(171).get().getIsLatest());
        assertFalse(dumpRecordRepository.findById(172).get().getIsLatest());
        assertFalse(dumpRecordRepository.findById(173).get().getIsLatest());
    }

}
