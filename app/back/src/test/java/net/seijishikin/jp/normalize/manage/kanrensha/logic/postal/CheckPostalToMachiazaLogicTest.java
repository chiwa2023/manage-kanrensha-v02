package net.seijishikin.jp.normalize.manage.kanrensha.logic.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode.PostalCodeCsvOneLineDto;

/**
 * CheckPostalToMachiazaLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("CheckPostalToMachiazaLogicTest.sql")
class CheckPostalToMachiazaLogicTest {

    /** テスト対象 */
    @Autowired
    private CheckPostalToMachiazaLogic checkPostalToMachiazaLogic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        final String lgCode = "011061";

        // この段階ではとりあえず大通西が存在すればOK
        assertEquals("", checkPostalToMachiazaLogic.practice(this.createPostaldto("大通西（１丁目）"), lgCode));

        // 大字が必要なら大字
        assertEquals("大字", checkPostalToMachiazaLogic.practice(this.createPostaldto("中山通り（１丁目）"), lgCode));

        // 字が必要なら字
        assertEquals("字", checkPostalToMachiazaLogic.practice(this.createPostaldto("山麓（５５番地）"), lgCode));

        // 見つからない場合はとりあえずスルー
        assertEquals("", checkPostalToMachiazaLogic.practice(this.createPostaldto("湖畔町"), lgCode));
    }

    private PostalCodeCsvOneLineDto createPostaldto(final String addressOrg) {

        PostalCodeCsvOneLineDto item0 = new PostalCodeCsvOneLineDto();
        item0.setPref("北海道");
        item0.setCity("札幌市中央区");
        item0.setAddressOrg(addressOrg);

        return item0;
    }

}
