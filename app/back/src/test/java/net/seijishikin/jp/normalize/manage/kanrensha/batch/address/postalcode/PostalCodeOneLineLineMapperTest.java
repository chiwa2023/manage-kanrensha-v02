package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * PostalCodeOneLineLineMapper単体テスト
 */
class PostalCodeOneLineLineMapperTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        PostalCodeOneLineLineMapper lineMapper = new PostalCodeOneLineLineMapper();
        String line1 = "01428,\"06913\",\"0691336\",\"ホッカイドウ\",\"ユウバリグンナガヌマチョウ\",\"サカエマチ\",\"北海道\",\"夕張郡長沼町\",\"栄町\",0,0,0,0,1,5";

        PostalCodeCsvOneLineDto dto = lineMapper.mapLine(line1, 0);

        assertEquals("01428", dto.getLgCode());
        assertEquals("0691336", dto.getPostalcode());
        assertEquals("北海道", dto.getPref());
        assertEquals("夕張郡長沼町", dto.getCity());
        assertEquals("栄町", dto.getAddressOrg());

    }

}
