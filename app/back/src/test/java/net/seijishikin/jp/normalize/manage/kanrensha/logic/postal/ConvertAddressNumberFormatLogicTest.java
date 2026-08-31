package net.seijishikin.jp.normalize.manage.kanrensha.logic.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * ConvertAddressNumberFormatLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ConvertAddressNumberFormatLogicTest {

    /** テスト対象 */
    @Autowired
    private ConvertAddressNumberFormatLogic convertAddressNumberFormatLogic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        assertEquals("", convertAddressNumberFormatLogic.practice(null), "nullの場合は空文字");

        assertEquals("ABCＡＢＣ田舎町", convertAddressNumberFormatLogic.practice("ABCＡＢＣ田舎町"), "数字が含まれない場合はそのままを返す");

        assertEquals("田舎町81番地", convertAddressNumberFormatLogic.practice("田舎町８１番地"), "全角数字は半角に変換");

        assertEquals("田舎町十四丁目", convertAddressNumberFormatLogic.practice("田舎町１４丁目"), "丁目は漢数字に変換");

        assertEquals("田舎町十一丁目81番地", convertAddressNumberFormatLogic.practice("田舎町１１丁目８１番地"), "番地と丁目が混じっていても問題なし");

        // 五十一丁目以上は現状存在しないと思われるが、存在した場合には改修必要なので早く発見するため例外
        assertThrows(IllegalArgumentException.class, () -> convertAddressNumberFormatLogic.practice("田舎町９２丁目８１番地"));
    }

}
