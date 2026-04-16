package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinEntity;

/**
 * KanrenshaKigyouDtAddMiniCsvProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("KanrenshaKigyouDtAddMiniCsvProcessorTest.sql")
class KanrenshaKigyouDtAddMiniCsvProcessorTest {

    /** テスト対象 */
    @Autowired
    private KanrenshaKigyouDtAddMiniCsvProcessor kanrenshaKigyouDtAddMiniCsvProcessor;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void test() throws Exception {

        // 未入力カラムがあると追加作業をしません
        WkTblKanrenshaKigyouDtAddMinEntity minEntity00 = kanrenshaKigyouDtAddMiniCsvProcessor
                .process(new KanrenshaKigyouDtAddMiniDto());
        assertEquals(false, minEntity00.getIsAffected());
        assertEquals("名称が入力されていません;住所が入力されていません;法人番号が入力されていません;", minEntity00.getJudgeReason());

        // 法人番号が形式通り(ハイフンを除いて数字13桁)でなければ追加作業をしません
        KanrenshaKigyouDtAddMiniDto dto01 = new KanrenshaKigyouDtAddMiniDto();
        dto01.setKanrenshaName("いいかげん政治団体");
        dto01.setAllAddress("宮崎県架空市");
        dto01.setKigyouDtDelegate("代表者　次郎");
        dto01.setHoujinNo("123abcd45");

        WkTblKanrenshaKigyouDtAddMinEntity minEntity01 = kanrenshaKigyouDtAddMiniCsvProcessor.process(dto01);
        assertEquals(false, minEntity01.getIsAffected());
        assertEquals("法人番号の形式ではありません(数字13桁);", minEntity01.getJudgeReason());

        // 完全一致する履歴がある場合は追加できません
        KanrenshaKigyouDtAddMiniDto dto02 = new KanrenshaKigyouDtAddMiniDto();
        dto02.setKanrenshaName("ぼったくり企業");
        dto02.setAllAddress("和歌山県架空市山麓町");
        dto02.setKigyouDtDelegate("代表者　太郎");
        dto02.setHoujinNo("1234567890123");

        WkTblKanrenshaKigyouDtAddMinEntity minEntity02 = kanrenshaKigyouDtAddMiniCsvProcessor.process(dto02);
        assertEquals(false, minEntity02.getIsAffected());
        assertEquals("すでに登録が存在します(1-2345-67-890123-4567890);", minEntity02.getJudgeReason());

        // 同名の団体が存在する場合はケースバイケースですが、バッチによる自動登録はできません
        KanrenshaKigyouDtAddMiniDto dto03 = new KanrenshaKigyouDtAddMiniDto();
        dto03.setKanrenshaName("ふんだくり企業");
        dto03.setAllAddress("和歌山県架空市山麓町");
        dto03.setKigyouDtDelegate("代表者　太郎");
        dto03.setHoujinNo("1234567890123");

        WkTblKanrenshaKigyouDtAddMinEntity minEntity03 = kanrenshaKigyouDtAddMiniCsvProcessor.process(dto03);
        assertEquals(false, minEntity03.getIsAffected());
        assertEquals("同名の団体があります。確認調査の上、必要に応じて追加してください;", minEntity03.getJudgeReason());

        // 全くの新規であれば追加作業します
        KanrenshaKigyouDtAddMiniDto dto04 = new KanrenshaKigyouDtAddMiniDto();
        dto04.setKanrenshaName("超元素製造組合");
        dto04.setAllAddress("宮崎県実在市湖畔町");
        dto04.setKigyouDtDelegate("組合長　花子");
        dto04.setHoujinNo("9876543210987");

        WkTblKanrenshaKigyouDtAddMinEntity minEntity04 = kanrenshaKigyouDtAddMiniCsvProcessor.process(dto04);
        assertEquals(true, minEntity04.getIsAffected());
        assertEquals("正)", minEntity04.getJudgeReason());

        // 正常データですべての値が設定されているかチェックする
        assertEquals(dto04.getAllAddress(), minEntity04.getAllAddress());
        assertEquals(dto04.getHoujinNo(), minEntity04.getHoujinNo());
        assertEquals(false, minEntity04.getIsFinish(), "未入力のままで問題なし");
        assertEquals(false, minEntity04.getIsLatest(), "writerで設定するので問題なし");
        assertEquals(dto04.getKanrenshaName(), minEntity04.getKanrenshaName());
        assertEquals(dto04.getKigyouDtDelegate(), minEntity04.getKigyouDtDelegate());
    }
}
