package net.seijishikin.jp.normalize.manage.kanrensha.logic.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;

/**
 * CheckExistPostalCodeByOtherLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CheckExistPostalCodeByOtherLogicTest.sql")
class CheckExistPostalCodeByOtherLogicTest {

    /** テスト対象 */
    @Autowired
    private CheckExistPostalCodeByOtherLogic checkExistPostalCodeByOtherLogic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        WkTblPostalCommonEntity worksEntity00 = new WkTblPostalCommonEntity();
        worksEntity00.setLgCode("011061");
        worksEntity00.setAddressName("札幌市南区真駒内");
        worksEntity00.setAddressOrg("真駒内（その他）"); // 処理には不要だが、どの郵便番号の操作をしているかを明記するため
        worksEntity00.setPostalcode1("005");
        worksEntity00.setPostalcode2("0861");

        List<AddressPostalEntity> list = checkExistPostalCodeByOtherLogic.practice(worksEntity00);
        assertEquals(1, list.size());
        AddressPostalEntity postalEntity0 = list.get(0);
        assertEquals("005", postalEntity0.getPostalcode1());
        assertEquals("0861", postalEntity0.getPostalcode2());
        assertEquals("真駒内（その他）", postalEntity0.getAddressOrg());

        WkTblPostalCommonEntity worksEntity01 = new WkTblPostalCommonEntity();
        worksEntity01.setLgCode("011061");
        worksEntity01.setAddressName("札幌市北区真駒内"); // 処理ミスでデータが狂った
        worksEntity01.setAddressOrg("真駒内（その他）"); // 処理には不要だが、どの郵便番号の操作をしているかを明記するため
        worksEntity01.setPostalcode1("005");
        worksEntity01.setPostalcode2("0861");
        List<AddressPostalEntity> list1 = checkExistPostalCodeByOtherLogic.practice(worksEntity01);

        assertTrue(list1.isEmpty());
    }

}
