package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * KanrenshaKigyouDtAddMiniLineMapper単体テスト
 */
class KanrenshaKigyouDtAddMiniLineMapperTest {

    @Test
    @Tag("TableTruncate")
    void test()throws Exception {
        
        KanrenshaKigyouDtAddMiniLineMapper lineMapper = new KanrenshaKigyouDtAddMiniLineMapper();
        KanrenshaKigyouDtAddMiniDto dto = lineMapper.mapLine("\"ぼったくり企業\",\"和歌山県架空市実在町\",\"代表者　太郎\",\"1233444\"", 0);

        assertEquals("ぼったくり企業", dto.getKanrenshaName());
        assertEquals("和歌山県架空市実在町", dto.getAllAddress());
        assertEquals("代表者　太郎", dto.getKigyouDtDelegate());
        assertEquals("1233444", dto.getHoujinNo());

        // 法人番号は、とりあえず今後扱いやすいようにハイフン除去のNormalize(正常かどうかは別途判定する)
        KanrenshaKigyouDtAddMiniDto dto1 = lineMapper.mapLine("\"ぼったくり企業\",\"和歌山県架空市実在町\",\"代表者　太郎\",\"１－2-\"", 0);
        assertEquals("12", dto1.getHoujinNo());
        
        // カラム数が足りない場合は処理中断
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> lineMapper.mapLine("\"ぼったくり企業\"", 0));
    }

}
