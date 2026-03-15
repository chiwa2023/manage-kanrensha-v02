package net.seijishikin.jp.normalize.manage.kanrensha.logic.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode.PostalCodeCsvOneLineDto;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode.PostalCodeOneLineLineMapper;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode.PostalCodeOneLineProcessor;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SplitIrregularToutenLogic単体テスト
 */
class SplitIrregularToutenLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** カッコ文字 */
    private static final String KEY_EMP = "（";

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        String line = "01207,\"080  \",\"0800029\",\"ホッカイドウ\",\"オビヒロシ\",\"ニシ１９ジョウミナミ（３５−３８、４１、４２チョウメ）\",\"北海道\",\"帯広市\",\"西十九条南（３５〜３８、４１、４２丁目）\",1,0,1,0,0,0";
        PostalCodeOneLineLineMapper lineMapper = new PostalCodeOneLineLineMapper();
        PostalCodeCsvOneLineDto dto = lineMapper.mapLine(line, 0);
        PostalCodeOneLineProcessor processor = new PostalCodeOneLineProcessor();
        AddressPostalEntity postalEntity = processor.process(dto);

        AddressPostalIrregularEntity entityIrregular = new AddressPostalIrregularEntity();
        BeanUtils.copyProperties(postalEntity, entityIrregular);
        // 単純な複写でないパターンがあれば追加する
        // nameはかっこより前を登録する
        int pos = entityIrregular.getAddressName().indexOf(KEY_EMP);
        entityIrregular.setAddressName(entityIrregular.getAddressName().substring(0, pos));

        SplitIrregularToutenLogic toutenLogic = new SplitIrregularToutenLogic();
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        List<WkTblPostalCommonEntity> listAns = toutenLogic.practice(entityIrregular, userDto);

        assertEquals(3, listAns.size());

        WkTblPostalCommonEntity entityWk0 = listAns.get(0);
        assertEquals(0, entityWk0.getAddressPostalIrregularId());
        assertEquals(true, entityWk0.getIsLatest());
        assertEquals(entityIrregular.getPostalcode1(), entityWk0.getPostalcode1());
        assertEquals(entityIrregular.getPostalcode2(), entityWk0.getPostalcode2());
        assertEquals(entityIrregular.getAddressPostal(), entityWk0.getAddressPostal());
        assertEquals(entityIrregular.getAddressBlock(), entityWk0.getAddressBlock());
        assertEquals(entityIrregular.getAddressName(), entityWk0.getAddressName());
        assertEquals("西十九条南（３５〜３８丁目）", entityWk0.getAddressOrg());

        WkTblPostalCommonEntity entityWk1 = listAns.get(1);
        assertEquals(0, entityWk1.getAddressPostalIrregularId());
        assertEquals(true, entityWk1.getIsLatest());
        assertEquals(entityIrregular.getPostalcode1(), entityWk1.getPostalcode1());
        assertEquals(entityIrregular.getPostalcode2(), entityWk1.getPostalcode2());
        assertEquals(entityIrregular.getAddressPostal(), entityWk1.getAddressPostal());
        assertEquals(entityIrregular.getAddressBlock(), entityWk1.getAddressBlock());
        assertEquals(entityIrregular.getAddressName(), entityWk1.getAddressName());
        assertEquals("西十九条南（４１丁目）", entityWk1.getAddressOrg());

        WkTblPostalCommonEntity entityWk2 = listAns.get(2);
        assertEquals(0, entityWk2.getAddressPostalIrregularId());
        assertEquals(true, entityWk2.getIsLatest());
        assertEquals(entityIrregular.getPostalcode1(), entityWk2.getPostalcode1());
        assertEquals(entityIrregular.getPostalcode2(), entityWk2.getPostalcode2());
        assertEquals(entityIrregular.getAddressPostal(), entityWk2.getAddressPostal());
        assertEquals(entityIrregular.getAddressBlock(), entityWk2.getAddressBlock());
        assertEquals(entityIrregular.getAddressName(), entityWk2.getAddressName());
        assertEquals("西十九条南（４２丁目）", entityWk2.getAddressOrg());
    }

}
