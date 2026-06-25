package net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllBookShushiV05Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheet0703JournalAndOtherDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheet0704BorrowedMoneyDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheet0706OtherIncomeDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Row070300JournalAndOtherDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Row070400BorrowedMoneyDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Row070600OtherIncomeDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet070300JournalAndOtherDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet070400BorrowedMoneyDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet070600OtherIncomeDto;


/**
 * InsertWktblXmlByPublishBikouLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertWktblXmlByPublishBikouLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertWktblXmlByPublishBikouLogic insertWktblXmlByPublishBikouLogic;

    /** XMLから最小マスタ登録Repositry */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /** 判定理由 */
    private static final String JUDGE_REASON = "関連者区分が未決定です;";

    @Test
    @Tag("TableTruncate")
    @Sql("InsertWktblXmlByPublishBikouLogicTest.sql")
    @Transactional
    void testYoushiki3() throws Exception {

        assertEquals(0L, wkTblMasterAllByXmlRepository.count());

        Row070300JournalAndOtherDto rowDto = new Row070300JournalAndOtherDto();
        rowDto.setBikou("備考1");

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();
        allBookDto.setAllSheet0703JournalAndOtherDto(new AllSheet0703JournalAndOtherDto());
        allBookDto.setAllSheet0704BorrowedMoneyDto(new AllSheet0704BorrowedMoneyDto());
        allBookDto.setAllSheet0706OtherIncomeDto(new AllSheet0706OtherIncomeDto());

        allBookDto.getAllSheet0703JournalAndOtherDto()
                .setSheet070300JournalAndOtherDto(new Sheet070300JournalAndOtherDto());
        allBookDto.getAllSheet0704BorrowedMoneyDto().setSheet070400BorrowedMoneyDto(new Sheet070400BorrowedMoneyDto());
        allBookDto.getAllSheet0706OtherIncomeDto().setSheet070600OtherIncomeDto(new Sheet070600OtherIncomeDto());

        allBookDto.getAllSheet0703JournalAndOtherDto().getSheet070300JournalAndOtherDto().getList().add(rowDto);

        assertDoesNotThrow(
                () -> insertWktblXmlByPublishBikouLogic.practice(allBookDto, CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getBikou(), entity.getBikou());
        assertEquals((short) 3, entity.getYoushikiKbn());
        assertEquals((short) 0, entity.getYoushikiEdaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Sql("InsertWktblXmlByPublishBikouLogicTest.sql")
    @Transactional
    void testYoushiki4() throws Exception {

        assertEquals(0L, wkTblMasterAllByXmlRepository.count());

        Row070400BorrowedMoneyDto rowDto = new Row070400BorrowedMoneyDto();
        rowDto.setBikou("備考2");

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();
        allBookDto.setAllSheet0703JournalAndOtherDto(new AllSheet0703JournalAndOtherDto());
        allBookDto.setAllSheet0704BorrowedMoneyDto(new AllSheet0704BorrowedMoneyDto());
        allBookDto.setAllSheet0706OtherIncomeDto(new AllSheet0706OtherIncomeDto());

        allBookDto.getAllSheet0703JournalAndOtherDto()
                .setSheet070300JournalAndOtherDto(new Sheet070300JournalAndOtherDto());
        allBookDto.getAllSheet0704BorrowedMoneyDto().setSheet070400BorrowedMoneyDto(new Sheet070400BorrowedMoneyDto());
        allBookDto.getAllSheet0706OtherIncomeDto().setSheet070600OtherIncomeDto(new Sheet070600OtherIncomeDto());

        allBookDto.getAllSheet0704BorrowedMoneyDto().getSheet070400BorrowedMoneyDto().getList().add(rowDto);

        assertDoesNotThrow(
                () -> insertWktblXmlByPublishBikouLogic.practice(allBookDto, CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getBikou(), entity.getBikou());
        assertEquals((short) 4, entity.getYoushikiKbn());
        assertEquals((short) 0, entity.getYoushikiEdaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Sql("InsertWktblXmlByPublishBikouLogicTest.sql")
    @Transactional
    void testYoushiki6() throws Exception {

        assertEquals(0L, wkTblMasterAllByXmlRepository.count());

        Row070600OtherIncomeDto rowDto = new Row070600OtherIncomeDto();
        rowDto.setBikou("備考3");

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();
        allBookDto.setAllSheet0703JournalAndOtherDto(new AllSheet0703JournalAndOtherDto());
        allBookDto.setAllSheet0704BorrowedMoneyDto(new AllSheet0704BorrowedMoneyDto());
        allBookDto.setAllSheet0706OtherIncomeDto(new AllSheet0706OtherIncomeDto());

        allBookDto.getAllSheet0703JournalAndOtherDto()
                .setSheet070300JournalAndOtherDto(new Sheet070300JournalAndOtherDto());
        allBookDto.getAllSheet0704BorrowedMoneyDto().setSheet070400BorrowedMoneyDto(new Sheet070400BorrowedMoneyDto());
        allBookDto.getAllSheet0706OtherIncomeDto().setSheet070600OtherIncomeDto(new Sheet070600OtherIncomeDto());

        allBookDto.getAllSheet0706OtherIncomeDto().getSheet070600OtherIncomeDto().getList().add(rowDto);

        assertDoesNotThrow(
                () -> insertWktblXmlByPublishBikouLogic.practice(allBookDto, CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getBikou(), entity.getBikou());
        assertEquals((short) 6, entity.getYoushikiKbn());
        assertEquals((short) 0, entity.getYoushikiEdaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

}
