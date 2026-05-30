package net.seijishikin.jp.normalize.manage.kanrensha.logic.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;

/**
 * ConvertAddressBlockPrefLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("ConvertAddressBlockPrefLogicTest.sql")
class ConvertAddressBlockPrefLogicTest {

    /** テスト対象 */
    @Autowired
    private ConvertAddressBlockPrefLogic convertAddressBlockPrefLogic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddressRsdtBaseEntity baseEntity00 = new AddressRsdtBaseEntity();
        baseEntity00.setAddressBlock("100番地の4");
        baseEntity00.setLgCode("abc");
        assertEquals("行政区コードなし:100番地の4", convertAddressBlockPrefLogic.practice(baseEntity00));

        AddressRsdtBaseEntity baseEntity01 = new AddressRsdtBaseEntity();
        baseEntity01.setAddressBlock("100番地の6");
        baseEntity01.setLgCode("112233");
        assertEquals("コード不一致:100番地の6", convertAddressBlockPrefLogic.practice(baseEntity01));

        AddressRsdtBaseEntity baseEntity02 = new AddressRsdtBaseEntity();
        baseEntity02.setAddressBlock("特別区100番地の6");
        baseEntity02.setLgCode("368524");
        assertEquals("宮崎県架空市特別区100番地の6", convertAddressBlockPrefLogic.practice(baseEntity02));

        AddressRsdtBaseEntity baseEntity03 = new AddressRsdtBaseEntity();
        baseEntity03.setAddressBlock("架空市100番地の9");
        baseEntity03.setLgCode("69512");
        assertEquals("宮崎県架空市100番地の9", convertAddressBlockPrefLogic.practice(baseEntity03));

        AddressRsdtBaseEntity baseEntity04 = new AddressRsdtBaseEntity();
        baseEntity04.setAddressBlock("実在郡100番地の7");
        baseEntity04.setLgCode("82763");
        assertEquals("宮崎県実在郡100番地の7", convertAddressBlockPrefLogic.practice(baseEntity04));

        AddressRsdtBaseEntity baseEntity05 = new AddressRsdtBaseEntity();
        baseEntity05.setAddressBlock("宮崎県山麓郡実在町100番地の13");
        baseEntity05.setLgCode("452027");
        assertEquals("宮崎県山麓郡実在町100番地の13", convertAddressBlockPrefLogic.practice(baseEntity05));
    }

}
