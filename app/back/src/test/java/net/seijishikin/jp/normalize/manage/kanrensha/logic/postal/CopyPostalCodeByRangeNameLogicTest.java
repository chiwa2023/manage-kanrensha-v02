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

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * CopyPostalCodeByRangeNameLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("CopyPostalCodeByRangeNameLogicTest.sql")
class CopyPostalCodeByRangeNameLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CopyPostalCodeByRangeNameLogic copyPostalCodeByRangeNameLogic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // データに句読点、が入っている並列データはテーブルから抽出する時点で扱わない

        // ～が２種入っているデータは住所リストを見ながらしないと修正できないので自動修正しない

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        WkTblPostalCommonEntity worksEntity00 = new WkTblPostalCommonEntity();
        worksEntity00.setAddressOrg("富士町（西４〜８線４９〜７８番地）");
        List<AddressPostalEntity> list0 = copyPostalCodeByRangeNameLogic.practice(worksEntity00, userDto);
        assertTrue(list0.isEmpty());

        // 番地、丁目が２種入っているデータは住所リストを見ながらしないと修正できないので自動修正しない
        WkTblPostalCommonEntity worksEntity01 = new WkTblPostalCommonEntity();
        worksEntity01.setAddressOrg("門静（４丁目５５〜１１４番地）");
        List<AddressPostalEntity> list1 = copyPostalCodeByRangeNameLogic.practice(worksEntity01, userDto);
        assertTrue(list1.isEmpty());

        // 「線」は除外
        WkTblPostalCommonEntity worksEntity02 = new WkTblPostalCommonEntity();
        worksEntity02.setAddressOrg("士幌西（１線〜３線）");
        List<AddressPostalEntity> list2 = copyPostalCodeByRangeNameLogic.practice(worksEntity02, userDto);
        assertTrue(list2.isEmpty());

        // 「条」は除外
        WkTblPostalCommonEntity worksEntity03 = new WkTblPostalCommonEntity();
        worksEntity03.setAddressOrg("士幌西（１条〜３条）");
        List<AddressPostalEntity> list3 = copyPostalCodeByRangeNameLogic.practice(worksEntity03, userDto);
        assertTrue(list3.isEmpty());

        // 「-」が入っているデータは複雑すぎ
        WkTblPostalCommonEntity worksEntity04 = new WkTblPostalCommonEntity();
        worksEntity04.setAddressOrg("追名牛（６７−４〜１１３−７番地）");
        List<AddressPostalEntity> list4 = copyPostalCodeByRangeNameLogic.practice(worksEntity04, userDto);
        assertTrue(list4.isEmpty());

        // もっとも単純な範囲データ(番地)
        WkTblPostalCommonEntity worksEntity05 = new WkTblPostalCommonEntity();
        worksEntity05.setAddressOrg("東旭川町豊田（１〜９番地）");
        worksEntity05.setLgCode("012041");
        worksEntity05.setAddressName("旭川市東旭川町豊田");
        List<AddressPostalEntity> list5 = copyPostalCodeByRangeNameLogic.practice(worksEntity05, userDto);
        assertEquals(9, list5.size());
        AddressPostalEntity postalEntity50 = list5.get(0);
        assertEquals("東旭川町豊田", postalEntity50.getAddressOrg());
        assertEquals("旭川市東旭川町豊田1番地", postalEntity50.getAddressName());
        AddressPostalEntity postalEntity51 = list5.get(1);
        assertEquals("東旭川町豊田", postalEntity51.getAddressOrg());
        assertEquals("旭川市東旭川町豊田2番地", postalEntity51.getAddressName());

        // もっとも単純な範囲データ(丁目)
        WkTblPostalCommonEntity worksEntity06 = new WkTblPostalCommonEntity();
        worksEntity06.setAddressOrg("広里町（１〜５丁目）");
        worksEntity06.setLgCode("012289");
        worksEntity06.setAddressName("深川市広里町");

        List<AddressPostalEntity> list6 = copyPostalCodeByRangeNameLogic.practice(worksEntity06, userDto);
        assertEquals(4, list6.size());

        AddressPostalEntity postalEntity60 = list6.get(0);
        assertEquals("広里町", postalEntity60.getAddressOrg());
        assertEquals("深川市広里町一丁目", postalEntity60.getAddressName());

    }

}
