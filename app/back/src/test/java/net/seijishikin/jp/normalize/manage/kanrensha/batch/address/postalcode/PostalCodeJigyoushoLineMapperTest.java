package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * PostalCodeJigyoushoLineMapper単体テスト
 */
class PostalCodeJigyoushoLineMapperTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        PostalCodeJigyoushoLineMapper lineMapper = new PostalCodeJigyoushoLineMapper();

        String line1 = "01205,\"ﾑﾛﾗﾝｼﾔｸｼﾖ ｺｳｲｷｾﾝﾀ-ﾋﾞﾙﾁﾖｳｼﾔ\",\"室蘭市役所　広域センタービル庁舎\",\"北海道\",\"室蘭市\",\"海岸町\",\"１丁目４番１号むろらん広域センタービル内\",\"0518530\",\"051  \",\"室蘭\",0,0,0";
        PostalCodeCsvJigyoushoDto dto = lineMapper.mapLine(line1, 0);

        assertEquals("01205", dto.getLgCode());
        assertEquals("0518530", dto.getPostalcode());
        assertEquals("北海道", dto.getPref());
        assertEquals("室蘭市", dto.getCity());
        assertEquals("海岸町", dto.getAddressOrg());
        assertEquals("１丁目４番１号むろらん広域センタービル内", dto.getAddressBlock());
    }

}
