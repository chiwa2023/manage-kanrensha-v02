package net.seijishikin.jp.normalize.manage.kanrensha.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;

/**
 * CreateAllShokugyoUtil単体テスト
 */
class CreateAllShokugyoUtilTest {

    @Test
    @Tag("TableTruncate")
    void test() {

        // entityがnullは実装ミスなので早期発見して実装修正
        assertThrows(IllegalArgumentException.class, () -> CreateAllShokugyoUtil.practice(null));

        final String BLANK = "";

        // 最小登録で職業詳細入力がなくてもUtilityに通してしまった場合は空文字、
        assertEquals(BLANK, CreateAllShokugyoUtil.practice(new KanrenshaPersonPropertyEntity()));

        // 自己申告最優先
        KanrenshaPersonPropertyEntity entity0 = new KanrenshaPersonPropertyEntity();
        entity0.setShokugyouUserWrite("素浪人");
        assertEquals("素浪人", CreateAllShokugyoUtil.practice(entity0));


        // 定職なしは画面上で自己申告入力だけを受け付けている。自己申告に記載がなければ空文字
        KanrenshaPersonPropertyEntity entity1 = new KanrenshaPersonPropertyEntity();
        entity1.setGyoushu("林業");
        entity1.setYakushoku("定職なし");
        assertEquals(BLANK, CreateAllShokugyoUtil.practice(entity1));

        //final String yakushokuFree = "所属なし";
        KanrenshaPersonPropertyEntity entity2 = new KanrenshaPersonPropertyEntity();
        entity2.setGyoushu("水産");
        entity2.setYakushoku("所属なし");
        assertEquals("水産業従事者", CreateAllShokugyoUtil.practice(entity2));

        //final String yakushokuGeneral = "一般職員";
        KanrenshaPersonPropertyEntity entity3 = new KanrenshaPersonPropertyEntity();
        entity3.setGyoushu("水産");
        entity3.setYakushoku("一般職員");
        assertEquals("水産業社員・職員", CreateAllShokugyoUtil.practice(entity3));

        
        //final String yakushokuDirector = "役職者";社名拒否
        KanrenshaPersonPropertyEntity entity4 = new KanrenshaPersonPropertyEntity();
        entity4.setGyoushu("水産");
        entity4.setYakushoku("役職者");
        assertEquals("水産業役職者(社名記載拒否)", CreateAllShokugyoUtil.practice(entity4));

        //final String yakushokuDirector = "役職者";社名拒否
        KanrenshaPersonPropertyEntity entity5 = new KanrenshaPersonPropertyEntity();
        entity5.setGyoushu("水産");
        entity5.setYakushoku("役職者");
        entity5.setKigyouDtNo("1234");
        entity5.setKigyouDtName("迂回鉄鋼");
        entity5.setKigyouDtAddress("山梨県架空市");
        assertEquals("水産業:迂回鉄鋼(山梨県架空市)役員", CreateAllShokugyoUtil.practice(entity5));
    }

}
