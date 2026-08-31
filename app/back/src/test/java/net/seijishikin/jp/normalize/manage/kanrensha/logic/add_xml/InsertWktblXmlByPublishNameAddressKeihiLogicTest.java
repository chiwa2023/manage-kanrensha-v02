package net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml; // NOPMD

import static org.junit.jupiter.api.Assertions.assertEquals; // NOPMD HighImport
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
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheet0705IncomeRelatedToGrantsDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheet0714ConstsDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheet0716RelatedToGrantsDtoDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheetKbn071401Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheetKbn071402Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheetKbn071403Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Row070500RelatedToGrantsDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Row071415OrdinaryExpensesDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Row071600ExpendituresGrantsDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet070500RelatedToGrantsDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071401UtilityCostsDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071402EquipmentCostsDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071403OfficeExpensesDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071600RelatedToGrantsDto;


/**
 * InsertWktblXmlByPublishNameAddressKeihiLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertWktblXmlByPublishNameAddressKeihiLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertWktblXmlByPublishNameAddressKeihiLogic insertWktblXmlByPublishNameAddressKeihiLogic;

    /** XMLから最小マスタ登録Repositry */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /** 判定理由 */
    private static final String JUDGE_REASON = "関連者区分が未決定です;";

    @Test
    @Tag("TableTruncate") // NOPMD
    @Transactional
    @Sql("InsertWktblXmlByPublishNameAddressKeihiLogicTest.sql") // NOPMD
    void test0705() throws Exception {

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();

        allBookDto.setAllSheet0705IncomeRelatedToGrantsDto(new AllSheet0705IncomeRelatedToGrantsDto());
        allBookDto.setAllSheet0714ConstsDto(new AllSheet0714ConstsDto());
        allBookDto.setAllSheet0716RelatedToGrantsDtoDto(new AllSheet0716RelatedToGrantsDtoDto());

        allBookDto.getAllSheet0705IncomeRelatedToGrantsDto()
                .setSheet070500IncomeRelatedToGrantsDto(new Sheet070500RelatedToGrantsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071401Dto(new AllSheetKbn071401Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071401Dto()
                .setSheet071401UtilityCostsDto(new Sheet071401UtilityCostsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071402Dto(new AllSheetKbn071402Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071402Dto()
                .setSheet071402EquipmentCostsDto(new Sheet071402EquipmentCostsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071403Dto(new AllSheetKbn071403Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071403Dto()
                .setSheet071403OfficeExpensesDto(new Sheet071403OfficeExpensesDto());
        allBookDto.getAllSheet0716RelatedToGrantsDtoDto()
                .setSheet071600ExpendituresRelatedToGrantsDto(new Sheet071600RelatedToGrantsDto());

        Row070500RelatedToGrantsDto rowDto = new Row070500RelatedToGrantsDto();
        rowDto.setHonbuShibuName("名称1"); // NOPMD
        rowDto.setJimushoJuusho("事務所住所1"); // NOPMD

        allBookDto.getAllSheet0705IncomeRelatedToGrantsDto().getSheet070500IncomeRelatedToGrantsDto().getList()
                .add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishNameAddressKeihiLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getHonbuShibuName(), entity.getInputSrcName());
        assertEquals(rowDto.getJimushoJuusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getHonbuShibuName(), entity.getKanrenshaName());
        assertEquals(rowDto.getJimushoJuusho(), entity.getAllAddress());
        assertEquals((short) 5, entity.getYoushikiKbn());
        assertEquals((short) 0, entity.getYoushikiEdaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("InsertWktblXmlByPublishNameAddressKeihiLogicTest.sql")
    void test0716() throws Exception {

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();

        allBookDto.setAllSheet0705IncomeRelatedToGrantsDto(new AllSheet0705IncomeRelatedToGrantsDto());
        allBookDto.setAllSheet0714ConstsDto(new AllSheet0714ConstsDto());
        allBookDto.setAllSheet0716RelatedToGrantsDtoDto(new AllSheet0716RelatedToGrantsDtoDto());

        allBookDto.getAllSheet0705IncomeRelatedToGrantsDto()
                .setSheet070500IncomeRelatedToGrantsDto(new Sheet070500RelatedToGrantsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071401Dto(new AllSheetKbn071401Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071401Dto()
                .setSheet071401UtilityCostsDto(new Sheet071401UtilityCostsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071402Dto(new AllSheetKbn071402Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071402Dto()
                .setSheet071402EquipmentCostsDto(new Sheet071402EquipmentCostsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071403Dto(new AllSheetKbn071403Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071403Dto()
                .setSheet071403OfficeExpensesDto(new Sheet071403OfficeExpensesDto());
        allBookDto.getAllSheet0716RelatedToGrantsDtoDto()
                .setSheet071600ExpendituresRelatedToGrantsDto(new Sheet071600RelatedToGrantsDto());

        Row071600ExpendituresGrantsDto rowDto = new Row071600ExpendituresGrantsDto();
        rowDto.setHonShibuName("名称1");
        rowDto.setJusho("事務所住所1");

        allBookDto.getAllSheet0716RelatedToGrantsDtoDto().getSheet071600ExpendituresRelatedToGrantsDto().getList()
                .add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishNameAddressKeihiLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getHonShibuName(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getHonShibuName(), entity.getKanrenshaName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals((short) 16, entity.getYoushikiKbn());
        assertEquals((short) 0, entity.getYoushikiEdaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("InsertWktblXmlByPublishNameAddressKeihiLogicTest.sql")
    void test071401() throws Exception {

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();

        allBookDto.setAllSheet0705IncomeRelatedToGrantsDto(new AllSheet0705IncomeRelatedToGrantsDto());
        allBookDto.setAllSheet0714ConstsDto(new AllSheet0714ConstsDto());
        allBookDto.setAllSheet0716RelatedToGrantsDtoDto(new AllSheet0716RelatedToGrantsDtoDto());

        allBookDto.getAllSheet0705IncomeRelatedToGrantsDto()
                .setSheet070500IncomeRelatedToGrantsDto(new Sheet070500RelatedToGrantsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071401Dto(new AllSheetKbn071401Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071401Dto()
                .setSheet071401UtilityCostsDto(new Sheet071401UtilityCostsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071402Dto(new AllSheetKbn071402Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071402Dto()
                .setSheet071402EquipmentCostsDto(new Sheet071402EquipmentCostsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071403Dto(new AllSheetKbn071403Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071403Dto()
                .setSheet071403OfficeExpensesDto(new Sheet071403OfficeExpensesDto());
        allBookDto.getAllSheet0716RelatedToGrantsDtoDto()
                .setSheet071600ExpendituresRelatedToGrantsDto(new Sheet071600RelatedToGrantsDto());

        Row071415OrdinaryExpensesDto rowDto = new Row071415OrdinaryExpensesDto();
        rowDto.setName("名称1");
        rowDto.setJusho("事務所住所1");

        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071401Dto().getSheet071401UtilityCostsDto().getList()
                .add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishNameAddressKeihiLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getName(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getName(), entity.getKanrenshaName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals((short) 14, entity.getYoushikiKbn());
        assertEquals((short) 2, entity.getYoushikiEdaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("InsertWktblXmlByPublishNameAddressKeihiLogicTest.sql")
    void test071402() throws Exception {

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();

        allBookDto.setAllSheet0705IncomeRelatedToGrantsDto(new AllSheet0705IncomeRelatedToGrantsDto());
        allBookDto.setAllSheet0714ConstsDto(new AllSheet0714ConstsDto());
        allBookDto.setAllSheet0716RelatedToGrantsDtoDto(new AllSheet0716RelatedToGrantsDtoDto());

        allBookDto.getAllSheet0705IncomeRelatedToGrantsDto()
                .setSheet070500IncomeRelatedToGrantsDto(new Sheet070500RelatedToGrantsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071401Dto(new AllSheetKbn071401Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071401Dto()
                .setSheet071401UtilityCostsDto(new Sheet071401UtilityCostsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071402Dto(new AllSheetKbn071402Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071402Dto()
                .setSheet071402EquipmentCostsDto(new Sheet071402EquipmentCostsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071403Dto(new AllSheetKbn071403Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071403Dto()
                .setSheet071403OfficeExpensesDto(new Sheet071403OfficeExpensesDto());
        allBookDto.getAllSheet0716RelatedToGrantsDtoDto()
                .setSheet071600ExpendituresRelatedToGrantsDto(new Sheet071600RelatedToGrantsDto());

        Row071415OrdinaryExpensesDto rowDto = new Row071415OrdinaryExpensesDto();
        rowDto.setName("名称1");
        rowDto.setJusho("事務所住所1");

        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071402Dto().getSheet071402EquipmentCostsDto().getList()
                .add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishNameAddressKeihiLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getName(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getName(), entity.getKanrenshaName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals((short) 14, entity.getYoushikiKbn());
        assertEquals((short) 3, entity.getYoushikiEdaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("InsertWktblXmlByPublishNameAddressKeihiLogicTest.sql")
    void test071403() throws Exception {

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();

        allBookDto.setAllSheet0705IncomeRelatedToGrantsDto(new AllSheet0705IncomeRelatedToGrantsDto());
        allBookDto.setAllSheet0714ConstsDto(new AllSheet0714ConstsDto());
        allBookDto.setAllSheet0716RelatedToGrantsDtoDto(new AllSheet0716RelatedToGrantsDtoDto());

        allBookDto.getAllSheet0705IncomeRelatedToGrantsDto()
                .setSheet070500IncomeRelatedToGrantsDto(new Sheet070500RelatedToGrantsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071401Dto(new AllSheetKbn071401Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071401Dto()
                .setSheet071401UtilityCostsDto(new Sheet071401UtilityCostsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071402Dto(new AllSheetKbn071402Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071402Dto()
                .setSheet071402EquipmentCostsDto(new Sheet071402EquipmentCostsDto());
        allBookDto.getAllSheet0714ConstsDto().setAllSheetKbn071403Dto(new AllSheetKbn071403Dto());
        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071403Dto()
                .setSheet071403OfficeExpensesDto(new Sheet071403OfficeExpensesDto());
        allBookDto.getAllSheet0716RelatedToGrantsDtoDto()
                .setSheet071600ExpendituresRelatedToGrantsDto(new Sheet071600RelatedToGrantsDto());

        Row071415OrdinaryExpensesDto rowDto = new Row071415OrdinaryExpensesDto();
        rowDto.setName("名称1");
        rowDto.setJusho("事務所住所1");

        allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071403Dto().getSheet071403OfficeExpensesDto().getList()
                .add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishNameAddressKeihiLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getName(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getName(), entity.getKanrenshaName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals((short) 14, entity.getYoushikiKbn());
        assertEquals((short) 4, entity.getYoushikiEdaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

}
