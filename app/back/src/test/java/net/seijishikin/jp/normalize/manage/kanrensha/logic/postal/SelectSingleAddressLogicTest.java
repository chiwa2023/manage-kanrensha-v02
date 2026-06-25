package net.seijishikin.jp.normalize.manage.kanrensha.logic.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SelectSingleAddressLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SelectSingleAddressLogicTest.sql")
class SelectSingleAddressLogicTest {

    /** テスト対象 */
    @Autowired
    private SelectSingleAddressLogic selectSingleAddressLogic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {
        WkTblPostalCommonEntity worksEntity00 = new WkTblPostalCommonEntity();
        worksEntity00.setLgCode("012351");
        worksEntity00.setAddressName("石狩市八幡町");
        worksEntity00.setAddressOrg("八幡町（五の沢）");
        worksEntity00.setPostalcode1("061");
        worksEntity00.setPostalcode2("3480");

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        List<AddressPostalEntity> list00 = selectSingleAddressLogic.practice(worksEntity00, userDto);
        assertEquals(1, list00.size());

        // 郵便番号0613480の呼び出し住所(address_name)が石狩市八幡町から石狩市八幡町五の沢に変更、かつ住居データに参照可能に
        AddressPostalEntity entityPostal00 = list00.get(0);
        assertEquals("061", entityPostal00.getPostalcode1());
        assertEquals("3480", entityPostal00.getPostalcode2());
        assertEquals("石狩市八幡町五の沢", entityPostal00.getAddressName());
        assertEquals(true, entityPostal00.getIsGyoseikuData());

        WkTblPostalCommonEntity worksEntity01 = new WkTblPostalCommonEntity();
        worksEntity01.setLgCode("012351");
        worksEntity01.setAddressName("札幌市八幡町"); // 編集中にデータが破損した
        worksEntity01.setAddressOrg("八幡町（五の沢）");
        worksEntity01.setPostalcode1("061");
        worksEntity01.setPostalcode2("3480");

        List<AddressPostalEntity> list01 = selectSingleAddressLogic.practice(worksEntity01, userDto);
        assertEquals(0, list01.size());

    }

}
