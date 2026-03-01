package net.seijishikin.jp.normalize.manage.kanrensha.logic.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * JigyoushoBlockNormalizeLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class JigyoushoBlockNormalizeLogicTest {

    /** テスト対象 */
    @Autowired
    private JigyoushoBlockNormalizeLogic jigyoushoBlockNormalizeLogic;
    
    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        String data0 = "aa０１２３４５６７８９bb";
        assertEquals("aa0123456789bb", jigyoushoBlockNormalizeLogic.practice(data0), "全角数字は半角に変換");

        String data1 = "３１丁目２５－４";
        assertEquals("三十一丁目25番地4", jigyoushoBlockNormalizeLogic.practice(data1), "丁目は半角数字に変換");

        String data2 = "３丁目７番地（札幌中央郵便局私書箱第１２１号）";
        assertEquals("三丁目7番地", jigyoushoBlockNormalizeLogic.practice(data2), "私書箱表示はカット");

        String data3 = "５丁目４番地９号庁舎西館";
        assertEquals("五丁目4番地9号　庁舎西館", jigyoushoBlockNormalizeLogic.practice(data3), "最初の号の後は全角スペース");

        String data4 = "５丁目４－９庁舎西館9号館402室";
        assertEquals("五丁目4番地9庁舎西館9号　館402室", jigyoushoBlockNormalizeLogic.practice(data4), "調整が必要なダメケース");

        String data5 = "6丁目7番地8号";
        assertEquals("六丁目7番地8号", jigyoushoBlockNormalizeLogic.practice(data5), "号がデータ末尾の場合は空白を追加しない");

        String data6 = "５丁目４番地幾何図形ビル";
        assertEquals("五丁目4番地　幾何図形ビル", jigyoushoBlockNormalizeLogic.practice(data6), "号表示がなく番地＋建物の場合は空白を追加");

        String data7 = "6丁目7番地";
        assertEquals("六丁目7番地", jigyoushoBlockNormalizeLogic.practice(data7), "番地がデータ末尾の場合は空白を追加しない");

        String data8 = "５丁目幾何図形ビル";
        assertEquals("五丁目　幾何図形ビル", jigyoushoBlockNormalizeLogic.practice(data8), "番地、号表示がなく丁目＋建物の場合は空白を追加");

        String data9 = "6丁目";
        assertEquals("六丁目", jigyoushoBlockNormalizeLogic.practice(data9), "丁目がデータ末尾の場合は空白を追加しない");

        String data10 = "6丁目８－９";
        assertEquals("六丁目8番地9", jigyoushoBlockNormalizeLogic.practice(data10), "ハイフンの両端が数字の場合は番地表記とみなす");

        String data11 = "6丁目８仲良しグル－プ";
        assertEquals("六丁目8仲良しグル－プ", jigyoushoBlockNormalizeLogic.practice(data11), "ハイフンの両端が数字でない場合は長音でない可能性を考慮する。ーの誤字扱い");
        
        assertDoesNotThrow(() -> jigyoushoBlockNormalizeLogic.practice(null));
        
    }

}
