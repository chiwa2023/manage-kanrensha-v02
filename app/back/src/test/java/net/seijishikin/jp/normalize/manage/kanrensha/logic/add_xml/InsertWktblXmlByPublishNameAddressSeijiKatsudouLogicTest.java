package net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml; // NOPMD

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllBookShushiV05Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheet0715ExpenseDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheetKbn071501Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheetKbn071502Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheetKbn071503Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheetKbn071504Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheetKbn071505Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheetKbn071506Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheetKbn071507Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheetKbn071508Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheetKbn071509Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Row071415OrdinaryExpensesDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071501OrganizationalActivityDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071502ElectionRelatedExpensesDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071503MagazinePublicationDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071504AdvertisingExpensesDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071505PartyHostingFeeDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071506OtherBusinessExpensesDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071507ResearchExpensesDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071508DonationsGrantsDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071509OtherExpensesDto;


/**
 * InsertWktblXmlByPublishNameAddressSeijiKatsudouLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertWktblXmlByPublishNameAddressSeijiKatsudouLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertWktblXmlByPublishNameAddressSeijiKatsudouLogic insertWktblXmlByPublishNameAddressSeijiKatsudouLogic;

    /** XMLから最小マスタ登録Repositry */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /** 判定理由 */
    private static final String JUDGE_REASON = "関連者区分が未決定です;";

    @Test
    @Tag("TableTruncate") // NOPMD
    @Sql("InsertWktblXmlByPublishNameAddressSeijiKatsudouLogicTest.sql") // NOPMD
    @Transactional
    void test01() {

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();

        allBookDto.setAllSheet0715ExpenseDto(new AllSheet0715ExpenseDto());

        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071501Dto(new AllSheetKbn071501Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071501Dto().getList()
                .add(new Sheet071501OrganizationalActivityDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071502Dto(new AllSheetKbn071502Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071502Dto().getList()
                .add(new Sheet071502ElectionRelatedExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071503Dto(new AllSheetKbn071503Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071503Dto().getList()
                .add(new Sheet071503MagazinePublicationDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071504Dto(new AllSheetKbn071504Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071504Dto().getList()
                .add(new Sheet071504AdvertisingExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071505Dto(new AllSheetKbn071505Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071505Dto().getList()
                .add(new Sheet071505PartyHostingFeeDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071506Dto(new AllSheetKbn071506Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071506Dto().getList()
                .add(new Sheet071506OtherBusinessExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071507Dto(new AllSheetKbn071507Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071507Dto().getList()
                .add(new Sheet071507ResearchExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071508Dto(new AllSheetKbn071508Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071508Dto().getList()
                .add(new Sheet071508DonationsGrantsDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071509Dto(new AllSheetKbn071509Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071509Dto().getList()
                .add(new Sheet071509OtherExpensesDto());

        Row071415OrdinaryExpensesDto rowDto = new Row071415OrdinaryExpensesDto();
        rowDto.setName("名称1"); // NOPMD
        rowDto.setJusho("事務所住所1"); // NOPMD

        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071501Dto().getList().get(0).getList().add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishNameAddressSeijiKatsudouLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getName(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getName(), entity.getKanrenshaName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals((short) 15, entity.getYoushikiKbn());
        assertEquals((short) 1, entity.getYoushikiEdaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Sql("InsertWktblXmlByPublishNameAddressSeijiKatsudouLogicTest.sql")
    @Transactional
    void test02() {

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();

        allBookDto.setAllSheet0715ExpenseDto(new AllSheet0715ExpenseDto());

        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071501Dto(new AllSheetKbn071501Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071501Dto().getList()
                .add(new Sheet071501OrganizationalActivityDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071502Dto(new AllSheetKbn071502Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071502Dto().getList()
                .add(new Sheet071502ElectionRelatedExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071503Dto(new AllSheetKbn071503Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071503Dto().getList()
                .add(new Sheet071503MagazinePublicationDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071504Dto(new AllSheetKbn071504Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071504Dto().getList()
                .add(new Sheet071504AdvertisingExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071505Dto(new AllSheetKbn071505Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071505Dto().getList()
                .add(new Sheet071505PartyHostingFeeDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071506Dto(new AllSheetKbn071506Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071506Dto().getList()
                .add(new Sheet071506OtherBusinessExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071507Dto(new AllSheetKbn071507Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071507Dto().getList()
                .add(new Sheet071507ResearchExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071508Dto(new AllSheetKbn071508Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071508Dto().getList()
                .add(new Sheet071508DonationsGrantsDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071509Dto(new AllSheetKbn071509Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071509Dto().getList()
                .add(new Sheet071509OtherExpensesDto());

        Row071415OrdinaryExpensesDto rowDto = new Row071415OrdinaryExpensesDto();
        rowDto.setName("名称1");
        rowDto.setJusho("事務所住所1");

        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071502Dto().getList().get(0).getList().add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishNameAddressSeijiKatsudouLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getName(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getName(), entity.getKanrenshaName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals((short) 15, entity.getYoushikiKbn());
        assertEquals((short) 2, entity.getYoushikiEdaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Sql("InsertWktblXmlByPublishNameAddressSeijiKatsudouLogicTest.sql")
    @Transactional
    void test03() {

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();

        allBookDto.setAllSheet0715ExpenseDto(new AllSheet0715ExpenseDto());

        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071501Dto(new AllSheetKbn071501Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071501Dto().getList()
                .add(new Sheet071501OrganizationalActivityDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071502Dto(new AllSheetKbn071502Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071502Dto().getList()
                .add(new Sheet071502ElectionRelatedExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071503Dto(new AllSheetKbn071503Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071503Dto().getList()
                .add(new Sheet071503MagazinePublicationDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071504Dto(new AllSheetKbn071504Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071504Dto().getList()
                .add(new Sheet071504AdvertisingExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071505Dto(new AllSheetKbn071505Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071505Dto().getList()
                .add(new Sheet071505PartyHostingFeeDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071506Dto(new AllSheetKbn071506Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071506Dto().getList()
                .add(new Sheet071506OtherBusinessExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071507Dto(new AllSheetKbn071507Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071507Dto().getList()
                .add(new Sheet071507ResearchExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071508Dto(new AllSheetKbn071508Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071508Dto().getList()
                .add(new Sheet071508DonationsGrantsDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071509Dto(new AllSheetKbn071509Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071509Dto().getList()
                .add(new Sheet071509OtherExpensesDto());

        Row071415OrdinaryExpensesDto rowDto = new Row071415OrdinaryExpensesDto();
        rowDto.setName("名称1");
        rowDto.setJusho("事務所住所1");

        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071503Dto().getList().get(0).getList().add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishNameAddressSeijiKatsudouLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getName(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getName(), entity.getKanrenshaName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals((short) 15, entity.getYoushikiKbn());
        assertEquals((short) 3, entity.getYoushikiEdaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Sql("InsertWktblXmlByPublishNameAddressSeijiKatsudouLogicTest.sql")
    @Transactional
    void test04() {

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();

        allBookDto.setAllSheet0715ExpenseDto(new AllSheet0715ExpenseDto());

        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071501Dto(new AllSheetKbn071501Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071501Dto().getList()
                .add(new Sheet071501OrganizationalActivityDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071502Dto(new AllSheetKbn071502Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071502Dto().getList()
                .add(new Sheet071502ElectionRelatedExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071503Dto(new AllSheetKbn071503Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071503Dto().getList()
                .add(new Sheet071503MagazinePublicationDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071504Dto(new AllSheetKbn071504Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071504Dto().getList()
                .add(new Sheet071504AdvertisingExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071505Dto(new AllSheetKbn071505Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071505Dto().getList()
                .add(new Sheet071505PartyHostingFeeDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071506Dto(new AllSheetKbn071506Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071506Dto().getList()
                .add(new Sheet071506OtherBusinessExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071507Dto(new AllSheetKbn071507Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071507Dto().getList()
                .add(new Sheet071507ResearchExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071508Dto(new AllSheetKbn071508Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071508Dto().getList()
                .add(new Sheet071508DonationsGrantsDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071509Dto(new AllSheetKbn071509Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071509Dto().getList()
                .add(new Sheet071509OtherExpensesDto());

        Row071415OrdinaryExpensesDto rowDto = new Row071415OrdinaryExpensesDto();
        rowDto.setName("名称1");
        rowDto.setJusho("事務所住所1");

        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071504Dto().getList().get(0).getList().add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishNameAddressSeijiKatsudouLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getName(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getName(), entity.getKanrenshaName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals((short) 15, entity.getYoushikiKbn());
        assertEquals((short) 4, entity.getYoushikiEdaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Sql("InsertWktblXmlByPublishNameAddressSeijiKatsudouLogicTest.sql")
    @Transactional
    void test05() {

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();

        allBookDto.setAllSheet0715ExpenseDto(new AllSheet0715ExpenseDto());

        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071501Dto(new AllSheetKbn071501Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071501Dto().getList()
                .add(new Sheet071501OrganizationalActivityDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071502Dto(new AllSheetKbn071502Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071502Dto().getList()
                .add(new Sheet071502ElectionRelatedExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071503Dto(new AllSheetKbn071503Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071503Dto().getList()
                .add(new Sheet071503MagazinePublicationDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071504Dto(new AllSheetKbn071504Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071504Dto().getList()
                .add(new Sheet071504AdvertisingExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071505Dto(new AllSheetKbn071505Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071505Dto().getList()
                .add(new Sheet071505PartyHostingFeeDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071506Dto(new AllSheetKbn071506Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071506Dto().getList()
                .add(new Sheet071506OtherBusinessExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071507Dto(new AllSheetKbn071507Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071507Dto().getList()
                .add(new Sheet071507ResearchExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071508Dto(new AllSheetKbn071508Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071508Dto().getList()
                .add(new Sheet071508DonationsGrantsDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071509Dto(new AllSheetKbn071509Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071509Dto().getList()
                .add(new Sheet071509OtherExpensesDto());

        Row071415OrdinaryExpensesDto rowDto = new Row071415OrdinaryExpensesDto();
        rowDto.setName("名称1");
        rowDto.setJusho("事務所住所1");

        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071505Dto().getList().get(0).getList().add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishNameAddressSeijiKatsudouLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getName(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getName(), entity.getKanrenshaName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals((short) 15, entity.getYoushikiKbn());
        assertEquals((short) 5, entity.getYoushikiEdaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Sql("InsertWktblXmlByPublishNameAddressSeijiKatsudouLogicTest.sql")
    @Transactional
    void test06() {

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();

        allBookDto.setAllSheet0715ExpenseDto(new AllSheet0715ExpenseDto());

        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071501Dto(new AllSheetKbn071501Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071501Dto().getList()
                .add(new Sheet071501OrganizationalActivityDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071502Dto(new AllSheetKbn071502Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071502Dto().getList()
                .add(new Sheet071502ElectionRelatedExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071503Dto(new AllSheetKbn071503Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071503Dto().getList()
                .add(new Sheet071503MagazinePublicationDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071504Dto(new AllSheetKbn071504Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071504Dto().getList()
                .add(new Sheet071504AdvertisingExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071505Dto(new AllSheetKbn071505Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071505Dto().getList()
                .add(new Sheet071505PartyHostingFeeDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071506Dto(new AllSheetKbn071506Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071506Dto().getList()
                .add(new Sheet071506OtherBusinessExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071507Dto(new AllSheetKbn071507Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071507Dto().getList()
                .add(new Sheet071507ResearchExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071508Dto(new AllSheetKbn071508Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071508Dto().getList()
                .add(new Sheet071508DonationsGrantsDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071509Dto(new AllSheetKbn071509Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071509Dto().getList()
                .add(new Sheet071509OtherExpensesDto());

        Row071415OrdinaryExpensesDto rowDto = new Row071415OrdinaryExpensesDto();
        rowDto.setName("名称1");
        rowDto.setJusho("事務所住所1");

        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071506Dto().getList().get(0).getList().add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishNameAddressSeijiKatsudouLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getName(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getName(), entity.getKanrenshaName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals((short) 15, entity.getYoushikiKbn());
        assertEquals((short) 6, entity.getYoushikiEdaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Sql("InsertWktblXmlByPublishNameAddressSeijiKatsudouLogicTest.sql")
    @Transactional
    void test07() {

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();

        allBookDto.setAllSheet0715ExpenseDto(new AllSheet0715ExpenseDto());

        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071501Dto(new AllSheetKbn071501Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071501Dto().getList()
                .add(new Sheet071501OrganizationalActivityDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071502Dto(new AllSheetKbn071502Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071502Dto().getList()
                .add(new Sheet071502ElectionRelatedExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071503Dto(new AllSheetKbn071503Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071503Dto().getList()
                .add(new Sheet071503MagazinePublicationDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071504Dto(new AllSheetKbn071504Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071504Dto().getList()
                .add(new Sheet071504AdvertisingExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071505Dto(new AllSheetKbn071505Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071505Dto().getList()
                .add(new Sheet071505PartyHostingFeeDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071506Dto(new AllSheetKbn071506Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071506Dto().getList()
                .add(new Sheet071506OtherBusinessExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071507Dto(new AllSheetKbn071507Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071507Dto().getList()
                .add(new Sheet071507ResearchExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071508Dto(new AllSheetKbn071508Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071508Dto().getList()
                .add(new Sheet071508DonationsGrantsDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071509Dto(new AllSheetKbn071509Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071509Dto().getList()
                .add(new Sheet071509OtherExpensesDto());

        Row071415OrdinaryExpensesDto rowDto = new Row071415OrdinaryExpensesDto();
        rowDto.setName("名称1");
        rowDto.setJusho("事務所住所1");

        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071507Dto().getList().get(0).getList().add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishNameAddressSeijiKatsudouLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getName(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getName(), entity.getKanrenshaName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals((short) 15, entity.getYoushikiKbn());
        assertEquals((short) 7, entity.getYoushikiEdaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Sql("InsertWktblXmlByPublishNameAddressSeijiKatsudouLogicTest.sql")
    @Transactional
    void test08() {

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();

        allBookDto.setAllSheet0715ExpenseDto(new AllSheet0715ExpenseDto());

        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071501Dto(new AllSheetKbn071501Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071501Dto().getList()
                .add(new Sheet071501OrganizationalActivityDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071502Dto(new AllSheetKbn071502Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071502Dto().getList()
                .add(new Sheet071502ElectionRelatedExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071503Dto(new AllSheetKbn071503Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071503Dto().getList()
                .add(new Sheet071503MagazinePublicationDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071504Dto(new AllSheetKbn071504Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071504Dto().getList()
                .add(new Sheet071504AdvertisingExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071505Dto(new AllSheetKbn071505Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071505Dto().getList()
                .add(new Sheet071505PartyHostingFeeDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071506Dto(new AllSheetKbn071506Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071506Dto().getList()
                .add(new Sheet071506OtherBusinessExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071507Dto(new AllSheetKbn071507Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071507Dto().getList()
                .add(new Sheet071507ResearchExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071508Dto(new AllSheetKbn071508Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071508Dto().getList()
                .add(new Sheet071508DonationsGrantsDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071509Dto(new AllSheetKbn071509Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071509Dto().getList()
                .add(new Sheet071509OtherExpensesDto());

        Row071415OrdinaryExpensesDto rowDto = new Row071415OrdinaryExpensesDto();
        rowDto.setName("名称1");
        rowDto.setJusho("事務所住所1");

        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071508Dto().getList().get(0).getList().add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishNameAddressSeijiKatsudouLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getName(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getName(), entity.getKanrenshaName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals((short) 15, entity.getYoushikiKbn());
        assertEquals((short) 8, entity.getYoushikiEdaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Sql("InsertWktblXmlByPublishNameAddressSeijiKatsudouLogicTest.sql")
    @Transactional
    void test09() {

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();

        allBookDto.setAllSheet0715ExpenseDto(new AllSheet0715ExpenseDto());

        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071501Dto(new AllSheetKbn071501Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071501Dto().getList()
                .add(new Sheet071501OrganizationalActivityDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071502Dto(new AllSheetKbn071502Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071502Dto().getList()
                .add(new Sheet071502ElectionRelatedExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071503Dto(new AllSheetKbn071503Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071503Dto().getList()
                .add(new Sheet071503MagazinePublicationDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071504Dto(new AllSheetKbn071504Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071504Dto().getList()
                .add(new Sheet071504AdvertisingExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071505Dto(new AllSheetKbn071505Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071505Dto().getList()
                .add(new Sheet071505PartyHostingFeeDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071506Dto(new AllSheetKbn071506Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071506Dto().getList()
                .add(new Sheet071506OtherBusinessExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071507Dto(new AllSheetKbn071507Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071507Dto().getList()
                .add(new Sheet071507ResearchExpensesDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071508Dto(new AllSheetKbn071508Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071508Dto().getList()
                .add(new Sheet071508DonationsGrantsDto());
        allBookDto.getAllSheet0715ExpenseDto().setAllSheetKbn071509Dto(new AllSheetKbn071509Dto());
        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071509Dto().getList()
                .add(new Sheet071509OtherExpensesDto());

        Row071415OrdinaryExpensesDto rowDto = new Row071415OrdinaryExpensesDto();
        rowDto.setName("名称1");
        rowDto.setJusho("事務所住所1");

        allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071509Dto().getList().get(0).getList().add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishNameAddressSeijiKatsudouLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getName(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getName(), entity.getKanrenshaName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals((short) 15, entity.getYoushikiKbn());
        assertEquals((short) 9, entity.getYoushikiEdaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

}
