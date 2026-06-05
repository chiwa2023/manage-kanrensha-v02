package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * EditPostalCodeLineMapper単体テスト
 */
class EditPostalCodeLineMapperTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        EditPostalCodeLineMapper lineMapper = new EditPostalCodeLineMapper();

        String line1 = "04207,\"982  \",\"9820046\",\"ミヤギケン\",\"ナトリシ\",\"ソウゴダイ\",\"宮城県\",\"名取市\",\"相互台\",0,0,1,0,1,4";

        EditPostalCodeOneLineDto dto = lineMapper.mapLine(line1, 0);

        assertEquals("04207", dto.getLgCode());
        assertEquals("982  ", dto.getPostalcode5());
        assertEquals("9820046", dto.getPostalcode7());
        assertEquals("ミヤギケン", dto.getPrefNameKana());
        assertEquals("ナトリシ", dto.getCityNameKana());
        assertEquals("ソウゴダイ", dto.getOrgNameKana());
        assertEquals("宮城県", dto.getPrefName());
        assertEquals("名取市", dto.getCityName());
        assertEquals("相互台", dto.getOrgName());

        assertEquals("0", dto.getFlgProp1());
        assertEquals("0", dto.getFlgProp2());
        assertEquals("1", dto.getFlgProp3());
        assertEquals("0", dto.getFlgProp4());

        assertEquals("1", dto.getFlgKoushin());
        assertEquals("4", dto.getFlgHenkouRiyu());
    }

}
