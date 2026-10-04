package net.seijishikin.jp.normalize.manage.kanrensha.service.dump_record;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.DumpRecordEntity;

/**
 * GetDupmRunRecordService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetDupmRunRecordServiceTest.sql")
class GetDupmRunRecordServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetDupmRunRecordService getDupmRunRecordService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        List<Integer> listCode = new ArrayList<>();
        listCode.add(TaskInfoConstants.DUMP_HISTORY_PERSON);
        listCode.add(TaskInfoConstants.DUMP_HISTORY_KIGYOU);
        listCode.add(TaskInfoConstants.DUMP_HISTORY_SEIJIDANTAI);

        List<DumpRecordEntity> listAns = getDupmRunRecordService.practice(listCode);
        // 3件のうち1件たまたま指定したタスク情報に該当した実行記録がなかった
        assertEquals(2, listAns.size());

        DumpRecordEntity entity0 = listAns.get(0);
        assertEquals(171, entity0.getDumpRecordId());

        DumpRecordEntity entity1 = listAns.get(1);
        assertEquals(173, entity1.getDumpRecordId());
    }

}
