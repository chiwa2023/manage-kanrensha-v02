package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

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

import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaCodeMoveHistoryDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeHistoryCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeHistoryResultDto;

/**
 * MoveCodeKanrenshaHistoryService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("MoveCodeKanrenshaHistoryServiceTest.sql")
class MoveCodeKanrenshaHistoryServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private MoveCodeKanrenshaHistoryService moveCodeKanrenshaHistoryService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        MoveKanrenshaCodeHistoryCapsuleDto capsuleDto = new MoveKanrenshaCodeHistoryCapsuleDto();
        capsuleDto.setKanrenshaKbn(KanrenshaKbnConstants.PERSON);
        capsuleDto.setCodeOrgin("12345"); // NOPMD
        capsuleDto.setCodeAbolish("98765"); // NOPMD

        MoveKanrenshaCodeHistoryResultDto resultDto = moveCodeKanrenshaHistoryService.practice(capsuleDto);

        // 存続コードユーザリスト
        List<KanrenshaCodeMoveHistoryDto> listUserOrgin = resultDto.getListUserOrgin();
        assertEquals(7, listUserOrgin.size());

        KanrenshaCodeMoveHistoryDto dto00 = listUserOrgin.get(0);
        assertEquals("qqqq", dto00.getUserPersonName()); // NOPMD
        assertEquals("ff-ff", dto00.getKanrenshaCode());

        KanrenshaCodeMoveHistoryDto dto01 = listUserOrgin.get(1);
        assertEquals("llll", dto01.getUserPersonName());
        assertEquals("dd-dd", dto01.getKanrenshaCode());

        KanrenshaCodeMoveHistoryDto dto02 = listUserOrgin.get(2);
        assertEquals("qqqq", dto02.getUserPersonName());
        assertEquals("cc-cc", dto02.getKanrenshaCode());

        KanrenshaCodeMoveHistoryDto dto03 = listUserOrgin.get(3);
        assertEquals("mmmm", dto03.getUserPersonName());
        assertEquals("ee-ee", dto03.getKanrenshaCode());

        KanrenshaCodeMoveHistoryDto dto04 = listUserOrgin.get(4);
        assertEquals("llll", dto04.getUserPersonName());
        assertEquals("12345", dto04.getKanrenshaCode());

        KanrenshaCodeMoveHistoryDto dto05 = listUserOrgin.get(5);
        assertEquals("qqqq", dto05.getUserPersonName());
        assertEquals("12345", dto05.getKanrenshaCode());

        KanrenshaCodeMoveHistoryDto dto06 = listUserOrgin.get(6);
        assertEquals("mmmm", dto06.getUserPersonName());
        assertEquals("12345", dto06.getKanrenshaCode());

        // 廃止コードユーザリスト
        List<KanrenshaCodeMoveHistoryDto> listUserAbolish = resultDto.getListUserAbolish();

        KanrenshaCodeMoveHistoryDto dto10 = listUserAbolish.get(0);
        assertEquals("oooo", dto10.getUserPersonName());
        assertEquals("aa-aa", dto10.getKanrenshaCode());

        KanrenshaCodeMoveHistoryDto dto11 = listUserAbolish.get(1);
        assertEquals("pppp", dto11.getUserPersonName());
        assertEquals("bb-bb", dto11.getKanrenshaCode());

        KanrenshaCodeMoveHistoryDto dto12 = listUserAbolish.get(2);
        assertEquals("nnnn", dto12.getUserPersonName());
        assertEquals("98765", dto12.getKanrenshaCode());

        KanrenshaCodeMoveHistoryDto dto13 = listUserAbolish.get(3);
        assertEquals("oooo", dto13.getUserPersonName());
        assertEquals("98765", dto13.getKanrenshaCode());

        KanrenshaCodeMoveHistoryDto dto14 = listUserAbolish.get(4);
        assertEquals("pppp", dto14.getUserPersonName());
        assertEquals("98765", dto14.getKanrenshaCode());

        // 存続コードコードリスト
        List<KanrenshaCodeMoveHistoryDto> listCodeOrgin = resultDto.getListCodeOrgin();
        assertEquals(3, listCodeOrgin.size());

        KanrenshaCodeMoveHistoryDto dto20 = listCodeOrgin.get(0);
        assertEquals("llll", dto20.getUserPersonName());
        KanrenshaCodeMoveHistoryDto dto21 = listCodeOrgin.get(1);
        assertEquals("qqqq", dto21.getUserPersonName());
        KanrenshaCodeMoveHistoryDto dto22 = listCodeOrgin.get(2);
        assertEquals("mmmm", dto22.getUserPersonName());

        // 廃止コードコードリスト
        List<KanrenshaCodeMoveHistoryDto> listCodeAbolish = resultDto.getListCodeAbolish();
        assertEquals(3, listCodeAbolish.size());

        KanrenshaCodeMoveHistoryDto dto30 = listCodeAbolish.get(0);
        assertEquals("nnnn", dto30.getUserPersonName());
        KanrenshaCodeMoveHistoryDto dto31 = listCodeAbolish.get(1);
        assertEquals("oooo", dto31.getUserPersonName());
        KanrenshaCodeMoveHistoryDto dto32 = listCodeAbolish.get(2);
        assertEquals("pppp", dto32.getUserPersonName());
    }

}
