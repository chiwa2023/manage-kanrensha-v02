package net.seijishikin.jp.normalize.manage.kanrensha.logic.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

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
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SplitIrregularToutenLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SplitIrregularToutenLogic splitIrregularToutenLogic;

    /** 郵便番号Csv変換Processor */
    @Autowired
    private PostalCodeOneLineProcessor postalCodeOneLineProcessor;

    /** カッコ文字 */
    private static final String KEY_EMP = "（";

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        String line = "01207,\"080  \",\"0800029\",\"ホッカイドウ\",\"オビヒロシ\",\"ニシ１９ジョウミナミ（３５−３８、４１、４２チョウメ）\",\"北海道\",\"帯広市\",\"西十九条南（３５〜３８、４１、４２丁目）\",1,0,1,0,0,0";
        PostalCodeOneLineLineMapper lineMapper = new PostalCodeOneLineLineMapper();
        PostalCodeCsvOneLineDto dto = lineMapper.mapLine(line, 0);
        AddressPostalEntity postalEntity = postalCodeOneLineProcessor.process(dto);

        AddressPostalIrregularEntity entityIrregular = new AddressPostalIrregularEntity();
        BeanUtils.copyProperties(postalEntity, entityIrregular);
        // 単純な複写でないパターンがあれば追加する
        // nameはかっこより前を登録する
        int pos = entityIrregular.getAddressName().indexOf(KEY_EMP);
        entityIrregular.setAddressName(entityIrregular.getAddressName().substring(0, pos));

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        List<WkTblPostalCommonEntity> listAns = splitIrregularToutenLogic.practice(entityIrregular, userDto);

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

    @Test
    @Tag("TableTruncate")
    void testJitsureiFix() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        AddressPostalIrregularEntity entityIrregular0 = new AddressPostalIrregularEntity();
        entityIrregular0.setAddressOrg(
                "協和（８８−２、２７１−１０、３４３−２、４０４−１、４２７−３、４３１−１２、４４３−６、６０８−２、６４１−８、８１４、８４２−５、１１３７−３、１３９２、１６５７、１７５２番地）");
        List<WkTblPostalCommonEntity> listAns = splitIrregularToutenLogic.practice(entityIrregular0, userDto);

        assertEquals(15, listAns.size());

        // ８８−２、
        WkTblPostalCommonEntity ansEntity00 = listAns.get(0);
        assertEquals("協和（８８−２番地）", ansEntity00.getAddressOrg());

        // ２７１−１０、
        WkTblPostalCommonEntity ansEntity01 = listAns.get(1);
        assertEquals("協和（２７１−１０番地）", ansEntity01.getAddressOrg());

        // ３４３−２、
        WkTblPostalCommonEntity ansEntity02 = listAns.get(2);
        assertEquals("協和（３４３−２番地）", ansEntity02.getAddressOrg());

        // ４０４−１、
        WkTblPostalCommonEntity ansEntity03 = listAns.get(3);
        assertEquals("協和（４０４−１番地）", ansEntity03.getAddressOrg());

        // ４２７−３、
        WkTblPostalCommonEntity ansEntity04 = listAns.get(4);
        assertEquals("協和（４２７−３番地）", ansEntity04.getAddressOrg());

        // ４３１−１２、
        WkTblPostalCommonEntity ansEntity05 = listAns.get(5);
        assertEquals("協和（４３１−１２番地）", ansEntity05.getAddressOrg());

        // ４４３−６、
        WkTblPostalCommonEntity ansEntity06 = listAns.get(6);
        assertEquals("協和（４４３−６番地）", ansEntity06.getAddressOrg());

        // ６０８−２、
        WkTblPostalCommonEntity ansEntity07 = listAns.get(7);
        assertEquals("協和（６０８−２番地）", ansEntity07.getAddressOrg());

        // ６４１−８、
        WkTblPostalCommonEntity ansEntity08 = listAns.get(8);
        assertEquals("協和（６４１−８番地）", ansEntity08.getAddressOrg());

        // ８１４、
        WkTblPostalCommonEntity ansEntity09 = listAns.get(9);
        assertEquals("協和（８１４番地）", ansEntity09.getAddressOrg());

        // ８４２−５、
        WkTblPostalCommonEntity ansEntity10 = listAns.get(10);
        assertEquals("協和（８４２−５番地）", ansEntity10.getAddressOrg());

        // １１３７−３、
        WkTblPostalCommonEntity ansEntity11 = listAns.get(11);
        assertEquals("協和（１１３７−３番地）", ansEntity11.getAddressOrg());

        // １３９２、
        WkTblPostalCommonEntity ansEntity12 = listAns.get(12);
        assertEquals("協和（１３９２番地）", ansEntity12.getAddressOrg());

        // １６５７、
        WkTblPostalCommonEntity ansEntity13 = listAns.get(13);
        assertEquals("協和（１６５７番地）", ansEntity13.getAddressOrg());

        // １７５２番地
        WkTblPostalCommonEntity ansEntity14 = listAns.get(14);
        assertEquals("協和（１７５２番地）", ansEntity14.getAddressOrg());
    }

}
