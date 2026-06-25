package net.seijishikin.jp.normalize.manage.kanrensha.logic.add_xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiAddMinRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;


/**
 * ConvertWkTblXmlToMasterPoliOrgLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("ConvertWkTblXmlToMasterSeijidantaiLogicTest.sql")
class ConvertWkTblXmlToMasterSeijidantaiLogicTest {

    /** テスト対象 */
    @Autowired
    private ConvertWkTblXmlToMasterSeijidantaiLogic convertWkTblXmlToMasterSeijidantaiLogic;

    /** 政治団体マスタ最小登録ワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaSeijidantaiAddMinRepository wkTblKanrenshaSeijidantaiAddMinRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        WkTblMasterAllByXmlEntity allByXmlEntity = new WkTblMasterAllByXmlEntity();

        allByXmlEntity.setKanrenshaName("超元素製造組合");
        allByXmlEntity.setAllAddress("山形県実在市湖畔町");
        allByXmlEntity.setOrgDelegate("代表者　花子");
        allByXmlEntity.setDantaiKbn("05");

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        int newId = convertWkTblXmlToMasterSeijidantaiLogic.practice(allByXmlEntity, userDto);
        assertNotEquals(0, newId);

        WkTblKanrenshaSeijidantaiAddMinEntity minEntity = wkTblKanrenshaSeijidantaiAddMinRepository.findById(newId).get();

        assertEquals(allByXmlEntity.getKanrenshaName(), minEntity.getKanrenshaName());
        assertEquals(allByXmlEntity.getAllAddress(), minEntity.getAllAddress());
        assertEquals(allByXmlEntity.getOrgDelegate(), minEntity.getSeijidantaiDelegate());
        assertEquals(allByXmlEntity.getDantaiKbn(), minEntity.getDantaiKbn());
        assertEquals(false, minEntity.getIsFinish());
    }

}
