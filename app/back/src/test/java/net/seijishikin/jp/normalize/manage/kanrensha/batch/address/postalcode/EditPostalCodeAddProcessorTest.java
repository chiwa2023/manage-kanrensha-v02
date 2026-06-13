package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalEditEntity;

/**
 * EditPostalCodeAddProcessor単体テスト
 */
class EditPostalCodeAddProcessorTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        EditPostalCodeAddProcessor processor = new EditPostalCodeAddProcessor();

        EditPostalCodeLineMapper lineMapper = new EditPostalCodeLineMapper();
        String line1 = "04207,\"982  \",\"9820046\",\"ミヤギケン\",\"ナトリシ\",\"ソウゴダイ\",\"宮城県\",\"名取市\",\"相互台\",0,0,1,0,1,4";

        EditPostalCodeOneLineDto dto = lineMapper.mapLine(line1, 0);

        WkTblPostalEditEntity editEntity = processor.process(dto);

        // Dtoの内容がそのまま複写
        assertEquals(dto.getLgCode(), editEntity.getLgCode());
        assertEquals(dto.getPostalcode5(), editEntity.getPostalcode5());
        assertEquals(dto.getPostalcode7(), editEntity.getPostalcode7());
        assertEquals(dto.getPrefNameKana(), editEntity.getPrefNameKana());
        assertEquals(dto.getCityNameKana(), editEntity.getCityNameKana());
        assertEquals(dto.getOrgNameKana(), editEntity.getOrgNameKana());
        assertEquals(dto.getPrefName(), editEntity.getPrefName());
        assertEquals(dto.getCityName(), editEntity.getCityName());
        assertEquals(dto.getOrgName(), editEntity.getOrgName());
        assertEquals(dto.getFlgProp1(), editEntity.getFlgProp1());
        assertEquals(dto.getFlgProp2(), editEntity.getFlgProp2());
        assertEquals(dto.getFlgProp3(), editEntity.getFlgProp3());
        assertEquals(dto.getFlgProp4(), editEntity.getFlgProp4());
        assertEquals(dto.getFlgKoushin(), editEntity.getFlgKoushin());
        assertEquals(dto.getFlgHenkouRiyu(), editEntity.getFlgHenkouRiyu());

        assertEquals(EditPostalConstants.ADD, editEntity.getFlgEdit());
        assertNull(editEntity.getIsRepair());
    }

}
