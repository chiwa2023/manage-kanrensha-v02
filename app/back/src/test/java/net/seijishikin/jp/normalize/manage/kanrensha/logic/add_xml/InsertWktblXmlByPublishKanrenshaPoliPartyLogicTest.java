package net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml; // NOPMD

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
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheet0711ConsiderationPartyDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheet0712PartyMediationDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheetKbn071101Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheetKbn071102Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheetKbn071103Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheetKbn071201Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheetKbn071202Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.AllSheetKbn071203Dto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Row070711DonateDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Row070812MediationDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071101ConsiderationPartyPerspnalDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071102ConsiderationPartyGroupDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071103ConsiderationPartyPoliticOrgDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071201MediationPartyPersonalDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071202MediationPartyGroupDto;
import net.seijishikin.jp.normalize.shuushi_doc.v05.dto.Sheet071203MediationPartyPoliOrgDto;


/**
 * InsertWktblXmlByPublishKanrenshaPoliPartyLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertWktblXmlByPublishKanrenshaPoliPartyLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertWktblXmlByPublishKanrenshaPoliPartyLogic insertWktblXmlByPublishKanrenshaPoliPartyLogic;

    /** XMLから最小マスタ登録Repositry */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /** 判定理由 */
    private static final String JUDGE_REASON = "別テ)";

    @Test
    @Tag("TableTruncate") // NOPMD
    @Sql("InsertWktblXmlByPublishKanrenshaPoliPartyLogicTest.sql") // NOPMD
    @Transactional
    void test1101() {

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();
        allBookDto.setAllSheet0711ConsiderationPartyDto(new AllSheet0711ConsiderationPartyDto());
        allBookDto.setAllSheet0712PartyMediationDto(new AllSheet0712PartyMediationDto());

        allBookDto.getAllSheet0711ConsiderationPartyDto().setAllSheetKbn071101Dto(new AllSheetKbn071101Dto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().setAllSheetKbn071102Dto(new AllSheetKbn071102Dto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().setAllSheetKbn071103Dto(new AllSheetKbn071103Dto());
        allBookDto.getAllSheet0712PartyMediationDto().setAllSheetKbn071201Dto(new AllSheetKbn071201Dto());
        allBookDto.getAllSheet0712PartyMediationDto().setAllSheetKbn071202Dto(new AllSheetKbn071202Dto());
        allBookDto.getAllSheet0712PartyMediationDto().setAllSheetKbn071203Dto(new AllSheetKbn071203Dto());

        allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071101Dto().getList()
                .add(new Sheet071101ConsiderationPartyPerspnalDto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071102Dto().getList()
                .add(new Sheet071102ConsiderationPartyGroupDto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071103Dto().getList()
                .add(new Sheet071103ConsiderationPartyPoliticOrgDto());
        allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071201Dto().getList()
                .add(new Sheet071201MediationPartyPersonalDto());
        allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071202Dto().getList()
                .add(new Sheet071202MediationPartyGroupDto());
        allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071203Dto().getList()
                .add(new Sheet071203MediationPartyPoliOrgDto());

        Row070711DonateDto rowDto = new Row070711DonateDto();
        rowDto.setKifusha("名称A"); // NOPMD
        rowDto.setJusho("事務所住所B"); // NOPMD
        rowDto.setShokugyou("職業または団体代表者C"); // NOPMD

        allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071101Dto().getList().get(0).getList()
                .add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishKanrenshaPoliPartyLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getKifusha(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getKifusha(), entity.getKanrenshaName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals(rowDto.getShokugyou(), entity.getInputSrcKey());
        assertEquals(rowDto.getShokugyou(), entity.getPersonShokugyou());
        assertEquals((short) 11, entity.getYoushikiKbn());
        assertEquals((short) 1, entity.getYoushikiEdaKbn());
        assertEquals((short) 1, entity.getKanrenshaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("InsertWktblXmlByPublishKanrenshaPoliPartyLogicTest.sql")
    void test1102() {

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();
        allBookDto.setAllSheet0711ConsiderationPartyDto(new AllSheet0711ConsiderationPartyDto());
        allBookDto.setAllSheet0712PartyMediationDto(new AllSheet0712PartyMediationDto());

        allBookDto.getAllSheet0711ConsiderationPartyDto().setAllSheetKbn071101Dto(new AllSheetKbn071101Dto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().setAllSheetKbn071102Dto(new AllSheetKbn071102Dto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().setAllSheetKbn071103Dto(new AllSheetKbn071103Dto());
        allBookDto.getAllSheet0712PartyMediationDto().setAllSheetKbn071201Dto(new AllSheetKbn071201Dto());
        allBookDto.getAllSheet0712PartyMediationDto().setAllSheetKbn071202Dto(new AllSheetKbn071202Dto());
        allBookDto.getAllSheet0712PartyMediationDto().setAllSheetKbn071203Dto(new AllSheetKbn071203Dto());

        allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071101Dto().getList()
                .add(new Sheet071101ConsiderationPartyPerspnalDto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071102Dto().getList()
                .add(new Sheet071102ConsiderationPartyGroupDto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071103Dto().getList()
                .add(new Sheet071103ConsiderationPartyPoliticOrgDto());
        allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071201Dto().getList()
                .add(new Sheet071201MediationPartyPersonalDto());
        allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071202Dto().getList()
                .add(new Sheet071202MediationPartyGroupDto());
        allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071203Dto().getList()
                .add(new Sheet071203MediationPartyPoliOrgDto());

        Row070711DonateDto rowDto = new Row070711DonateDto();
        rowDto.setKifusha("名称A");
        rowDto.setJusho("事務所住所B");
        rowDto.setShokugyou("職業または団体代表者C");

        allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071102Dto().getList().get(0).getList()
                .add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishKanrenshaPoliPartyLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getKifusha(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getKifusha(), entity.getKanrenshaName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals(rowDto.getShokugyou(), entity.getInputSrcKey());
        assertEquals(rowDto.getShokugyou(), entity.getPersonShokugyou());
        assertEquals((short) 11, entity.getYoushikiKbn());
        assertEquals((short) 2, entity.getYoushikiEdaKbn());
        assertEquals((short) 2, entity.getKanrenshaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("InsertWktblXmlByPublishKanrenshaPoliPartyLogicTest.sql")
    void test1103() {

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();
        allBookDto.setAllSheet0711ConsiderationPartyDto(new AllSheet0711ConsiderationPartyDto());
        allBookDto.setAllSheet0712PartyMediationDto(new AllSheet0712PartyMediationDto());

        allBookDto.getAllSheet0711ConsiderationPartyDto().setAllSheetKbn071101Dto(new AllSheetKbn071101Dto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().setAllSheetKbn071102Dto(new AllSheetKbn071102Dto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().setAllSheetKbn071103Dto(new AllSheetKbn071103Dto());
        allBookDto.getAllSheet0712PartyMediationDto().setAllSheetKbn071201Dto(new AllSheetKbn071201Dto());
        allBookDto.getAllSheet0712PartyMediationDto().setAllSheetKbn071202Dto(new AllSheetKbn071202Dto());
        allBookDto.getAllSheet0712PartyMediationDto().setAllSheetKbn071203Dto(new AllSheetKbn071203Dto());

        allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071101Dto().getList()
                .add(new Sheet071101ConsiderationPartyPerspnalDto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071102Dto().getList()
                .add(new Sheet071102ConsiderationPartyGroupDto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071103Dto().getList()
                .add(new Sheet071103ConsiderationPartyPoliticOrgDto());
        allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071201Dto().getList()
                .add(new Sheet071201MediationPartyPersonalDto());
        allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071202Dto().getList()
                .add(new Sheet071202MediationPartyGroupDto());
        allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071203Dto().getList()
                .add(new Sheet071203MediationPartyPoliOrgDto());

        Row070711DonateDto rowDto = new Row070711DonateDto();
        rowDto.setKifusha("名称A");
        rowDto.setJusho("事務所住所B");
        rowDto.setShokugyou("職業または団体代表者C");

        allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071103Dto().getList().get(0).getList()
                .add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishKanrenshaPoliPartyLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getKifusha(), entity.getInputSrcName());
        assertEquals(rowDto.getJusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getKifusha(), entity.getKanrenshaName());
        assertEquals(rowDto.getJusho(), entity.getAllAddress());
        assertEquals(rowDto.getShokugyou(), entity.getInputSrcKey());
        assertEquals(rowDto.getShokugyou(), entity.getPersonShokugyou());
        assertEquals((short) 11, entity.getYoushikiKbn());
        assertEquals((short) 3, entity.getYoushikiEdaKbn());
        assertEquals((short) 3, entity.getKanrenshaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("InsertWktblXmlByPublishKanrenshaPoliPartyLogicTest.sql")
    void test1201() {

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();
        allBookDto.setAllSheet0711ConsiderationPartyDto(new AllSheet0711ConsiderationPartyDto());
        allBookDto.setAllSheet0712PartyMediationDto(new AllSheet0712PartyMediationDto());

        allBookDto.getAllSheet0711ConsiderationPartyDto().setAllSheetKbn071101Dto(new AllSheetKbn071101Dto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().setAllSheetKbn071102Dto(new AllSheetKbn071102Dto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().setAllSheetKbn071103Dto(new AllSheetKbn071103Dto());
        allBookDto.getAllSheet0712PartyMediationDto().setAllSheetKbn071201Dto(new AllSheetKbn071201Dto());
        allBookDto.getAllSheet0712PartyMediationDto().setAllSheetKbn071202Dto(new AllSheetKbn071202Dto());
        allBookDto.getAllSheet0712PartyMediationDto().setAllSheetKbn071203Dto(new AllSheetKbn071203Dto());

        allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071101Dto().getList()
                .add(new Sheet071101ConsiderationPartyPerspnalDto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071102Dto().getList()
                .add(new Sheet071102ConsiderationPartyGroupDto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071103Dto().getList()
                .add(new Sheet071103ConsiderationPartyPoliticOrgDto());
        allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071201Dto().getList()
                .add(new Sheet071201MediationPartyPersonalDto());
        allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071202Dto().getList()
                .add(new Sheet071202MediationPartyGroupDto());
        allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071203Dto().getList()
                .add(new Sheet071203MediationPartyPoliOrgDto());

        Row070812MediationDto rowDto = new Row070812MediationDto();
        rowDto.setName("名称A");
        rowDto.setJuusho("事務所住所B");
        rowDto.setShokugyou("職業または団体代表者C");

        allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071201Dto().getList().get(0).getList().add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishKanrenshaPoliPartyLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getName(), entity.getInputSrcName());
        assertEquals(rowDto.getJuusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getName(), entity.getKanrenshaName());
        assertEquals(rowDto.getJuusho(), entity.getAllAddress());
        assertEquals(rowDto.getShokugyou(), entity.getInputSrcKey());
        assertEquals(rowDto.getShokugyou(), entity.getPersonShokugyou());
        assertEquals((short) 12, entity.getYoushikiKbn());
        assertEquals((short) 1, entity.getYoushikiEdaKbn());
        assertEquals((short) 1, entity.getKanrenshaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("InsertWktblXmlByPublishKanrenshaPoliPartyLogicTest.sql")
    void test1202() {

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();
        allBookDto.setAllSheet0711ConsiderationPartyDto(new AllSheet0711ConsiderationPartyDto());
        allBookDto.setAllSheet0712PartyMediationDto(new AllSheet0712PartyMediationDto());

        allBookDto.getAllSheet0711ConsiderationPartyDto().setAllSheetKbn071101Dto(new AllSheetKbn071101Dto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().setAllSheetKbn071102Dto(new AllSheetKbn071102Dto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().setAllSheetKbn071103Dto(new AllSheetKbn071103Dto());
        allBookDto.getAllSheet0712PartyMediationDto().setAllSheetKbn071201Dto(new AllSheetKbn071201Dto());
        allBookDto.getAllSheet0712PartyMediationDto().setAllSheetKbn071202Dto(new AllSheetKbn071202Dto());
        allBookDto.getAllSheet0712PartyMediationDto().setAllSheetKbn071203Dto(new AllSheetKbn071203Dto());

        allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071101Dto().getList()
                .add(new Sheet071101ConsiderationPartyPerspnalDto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071102Dto().getList()
                .add(new Sheet071102ConsiderationPartyGroupDto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071103Dto().getList()
                .add(new Sheet071103ConsiderationPartyPoliticOrgDto());
        allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071201Dto().getList()
                .add(new Sheet071201MediationPartyPersonalDto());
        allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071202Dto().getList()
                .add(new Sheet071202MediationPartyGroupDto());
        allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071203Dto().getList()
                .add(new Sheet071203MediationPartyPoliOrgDto());

        Row070812MediationDto rowDto = new Row070812MediationDto();
        rowDto.setName("名称A");
        rowDto.setJuusho("事務所住所B");
        rowDto.setShokugyou("職業または団体代表者C");

        allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071202Dto().getList().get(0).getList().add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishKanrenshaPoliPartyLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getName(), entity.getInputSrcName());
        assertEquals(rowDto.getJuusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getName(), entity.getKanrenshaName());
        assertEquals(rowDto.getJuusho(), entity.getAllAddress());
        assertEquals(rowDto.getShokugyou(), entity.getInputSrcKey());
        assertEquals(rowDto.getShokugyou(), entity.getPersonShokugyou());
        assertEquals((short) 12, entity.getYoushikiKbn());
        assertEquals((short) 2, entity.getYoushikiEdaKbn());
        assertEquals((short) 2, entity.getKanrenshaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("InsertWktblXmlByPublishKanrenshaPoliPartyLogicTest.sql")
    void test1203() {

        AllBookShushiV05Dto allBookDto = new AllBookShushiV05Dto();
        allBookDto.setAllSheet0711ConsiderationPartyDto(new AllSheet0711ConsiderationPartyDto());
        allBookDto.setAllSheet0712PartyMediationDto(new AllSheet0712PartyMediationDto());

        allBookDto.getAllSheet0711ConsiderationPartyDto().setAllSheetKbn071101Dto(new AllSheetKbn071101Dto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().setAllSheetKbn071102Dto(new AllSheetKbn071102Dto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().setAllSheetKbn071103Dto(new AllSheetKbn071103Dto());
        allBookDto.getAllSheet0712PartyMediationDto().setAllSheetKbn071201Dto(new AllSheetKbn071201Dto());
        allBookDto.getAllSheet0712PartyMediationDto().setAllSheetKbn071202Dto(new AllSheetKbn071202Dto());
        allBookDto.getAllSheet0712PartyMediationDto().setAllSheetKbn071203Dto(new AllSheetKbn071203Dto());

        allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071101Dto().getList()
                .add(new Sheet071101ConsiderationPartyPerspnalDto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071102Dto().getList()
                .add(new Sheet071102ConsiderationPartyGroupDto());
        allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071103Dto().getList()
                .add(new Sheet071103ConsiderationPartyPoliticOrgDto());
        allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071201Dto().getList()
                .add(new Sheet071201MediationPartyPersonalDto());
        allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071202Dto().getList()
                .add(new Sheet071202MediationPartyGroupDto());
        allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071203Dto().getList()
                .add(new Sheet071203MediationPartyPoliOrgDto());

        Row070812MediationDto rowDto = new Row070812MediationDto();
        rowDto.setName("名称A");
        rowDto.setJuusho("事務所住所B");
        rowDto.setShokugyou("職業または団体代表者C");

        allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071203Dto().getList().get(0).getList().add(rowDto);

        assertDoesNotThrow(() -> insertWktblXmlByPublishKanrenshaPoliPartyLogic.practice(allBookDto,
                CreateLeastUserForTestUtil.practice()));

        List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlEntity entity = list.get(0);
        assertEquals(rowDto.getName(), entity.getInputSrcName());
        assertEquals(rowDto.getJuusho(), entity.getInputSrcAddress());
        assertEquals(rowDto.getName(), entity.getKanrenshaName());
        assertEquals(rowDto.getJuusho(), entity.getAllAddress());
        assertEquals(rowDto.getShokugyou(), entity.getInputSrcKey());
        assertEquals(rowDto.getShokugyou(), entity.getPersonShokugyou());
        assertEquals((short) 12, entity.getYoushikiKbn());
        assertEquals((short) 3, entity.getYoushikiEdaKbn());
        assertEquals((short) 3, entity.getKanrenshaKbn());
        assertEquals(JUDGE_REASON, entity.getJudgeReason());
    }

}
