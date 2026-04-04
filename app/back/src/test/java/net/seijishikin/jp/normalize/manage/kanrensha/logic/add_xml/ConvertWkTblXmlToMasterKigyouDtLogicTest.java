package net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtAddMinRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;


/**
 * ConvertWkTblXmlToMasterCorpLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("ConvertWkTblXmlToMasterKigyouDtLogicTest.sql")
class ConvertWkTblXmlToMasterKigyouDtLogicTest {

    /** テスト対象 */
    @Autowired
    private ConvertWkTblXmlToMasterKigyouDtLogic convertWkTblXmlToMasterKigyouDtLogic;

    /** 企業マスタ最小登録ワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaKigyouDtAddMinRepository wkTblKanrenshaKigyouDtAddMinRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        WkTblMasterAllByXmlEntity allByXmlEntity = new WkTblMasterAllByXmlEntity();

        allByXmlEntity.setKanrenshaName("超元素製造組合");
        allByXmlEntity.setAllAddress("山形県実在市湖畔町");
        allByXmlEntity.setOrgDelegate("代表者　花子");
        allByXmlEntity.setHoujinNo("12345");

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        int newId = convertWkTblXmlToMasterKigyouDtLogic.practice(allByXmlEntity, userDto);
        assertNotEquals(0, newId);

        WkTblKanrenshaKigyouDtAddMinEntity minEntity = wkTblKanrenshaKigyouDtAddMinRepository.findById(newId).get();

        assertEquals(allByXmlEntity.getKanrenshaName(), minEntity.getKanrenshaName());
        assertEquals(allByXmlEntity.getAllAddress(), minEntity.getAllAddress());
        assertEquals(allByXmlEntity.getOrgDelegate(), minEntity.getKigyouDtDelegate());
        assertEquals(allByXmlEntity.getHoujinNo(), minEntity.getHoujinNo());
        assertEquals(false, minEntity.getIsFinish());
    }

}
