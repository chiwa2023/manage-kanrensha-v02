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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonAddMinRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;


/**
 * ConvertWkTblXmlToMasterPersonLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("ConvertWkTblXmlToMasterPersonLogicTest.sql")
class ConvertWkTblXmlToMasterPersonLogicTest {

    /** テスト対象 */
    @Autowired
    private ConvertWkTblXmlToMasterPersonLogic convertWkTblXmlToMasterPersonLogic;

    /** 個人マスタ最小登録ワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaPersonAddMinRepository wkTblKanrenshaPersonAddMinRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        WkTblMasterAllByXmlEntity allByXmlEntity = new WkTblMasterAllByXmlEntity();

        allByXmlEntity.setKanrenshaName("超元素製造組合");
        allByXmlEntity.setAllAddress("山形県実在市湖畔町");
        allByXmlEntity.setPersonShokugyou("団体役員");

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        int newId = convertWkTblXmlToMasterPersonLogic.practice(allByXmlEntity, userDto);
        assertNotEquals(0, newId);

        WkTblKanrenshaPersonAddMinEntity minEntity = wkTblKanrenshaPersonAddMinRepository.findById(newId).get();

        assertEquals(allByXmlEntity.getKanrenshaName(), minEntity.getKanrenshaName());
        assertEquals(allByXmlEntity.getAllAddress(), minEntity.getAllAddress());
        assertEquals(allByXmlEntity.getPersonShokugyou(), minEntity.getPersonShokugyou());
        assertEquals(false, minEntity.getIsFinish());
    }

}
