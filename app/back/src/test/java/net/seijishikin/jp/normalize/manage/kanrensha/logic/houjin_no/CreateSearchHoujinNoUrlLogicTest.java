package net.seijishikin.jp.normalize.manage.kanrensha.logic.houjin_no;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.houjin_no.SearchHoujinNoCapsuleDto;

/**
 * CreateSearchHoujinNoUrlLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CreateSearchHoujinNoUrlLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CreateSearchHoujinNoUrlLogic createSearchHoujinNoUrlLogic;

    @Test
    void test() {

        assertThrows(IllegalArgumentException.class, () -> createSearchHoujinNoUrlLogic.pracctice(null, null),
                "接続先の指定がありません");

        final String domainUrl = "http://localhost:7080/4/name?";
        assertThrows(NullPointerException.class, () -> createSearchHoujinNoUrlLogic.pracctice(domainUrl, null),
                "検索条件の指定がありません");

        assertThrows(IllegalArgumentException.class,
                () -> createSearchHoujinNoUrlLogic.pracctice(domainUrl, new SearchHoujinNoCapsuleDto()),
                "アプリケーションIdの指定がありません");

        SearchHoujinNoCapsuleDto capsuleDto00 = new SearchHoujinNoCapsuleDto();
        capsuleDto00.setAppId("123456789");
        assertThrows(IllegalArgumentException.class,
                () -> createSearchHoujinNoUrlLogic.pracctice(domainUrl, capsuleDto00), "検索条件名称の指定がありません");

        // 最低限指定
        SearchHoujinNoCapsuleDto capsuleDto01 = new SearchHoujinNoCapsuleDto();
        capsuleDto01.setAppId("234567890");
        capsuleDto01.setName("サンプル商会");
        assertEquals(
                "http://localhost:7080/4/name?id=234567890&name=%E3%82%B5%E3%83%B3%E3%83%97%E3%83%AB%E5%95%86%E4%BC%9A&type=02",
                createSearchHoujinNoUrlLogic.pracctice(domainUrl, capsuleDto01));

        // 全指定
        SearchHoujinNoCapsuleDto capsuleDto02 = new SearchHoujinNoCapsuleDto();
        capsuleDto02.setAppId("3456789012");
        capsuleDto02.setName("テスト工業");
        capsuleDto02.setMode("1");
        capsuleDto02.setTarget("2");
        capsuleDto02.setAddress("03"); // 2桁
        capsuleDto02.setKind("02");
        capsuleDto02.setChange("0");

        // 登記記録の閉鎖該否(過去データを見る場合、閉鎖を使いうるので常にON)

        capsuleDto02.setFrom(LocalDate.of(2022, 6, 11));
        capsuleDto02.setTo(LocalDate.of(2023, 9, 18));

        // 分割番号(ページングのページ番号)
        // private Integer divide;

        String urlAns = createSearchHoujinNoUrlLogic.pracctice(domainUrl, capsuleDto02);

        assertTrue(urlAns.startsWith(domainUrl));
        assertTrue(urlAns.contains("?id=3456789012"));
        assertTrue(urlAns.contains("&name=%E3%83%86%E3%82%B9%E3%83%88%E5%B7%A5%E6%A5%AD"));
        assertTrue(urlAns.contains("&type=02"));

        // 名称検索方式
        // capsuleDto02.setMode("1");
        assertTrue(urlAns.contains("&mode=1"));

        // 名称検索方式
        // capsuleDto02.setTarget("1");
        assertTrue(urlAns.contains("&target=2"));

        // 市区町村コード (2桁の場合はカットされない)
        // capsuleDto02.setAddress("03");
        assertTrue(urlAns.contains("&address=03"));

        // 6桁の場合（5桁にカットされる）
        capsuleDto02.setAddress("131016");
        String urlAnsWith6DigitAddress = createSearchHoujinNoUrlLogic.pracctice(domainUrl, capsuleDto02);
        assertTrue(urlAnsWith6DigitAddress.contains("&address=13101"));

        // 法人種別(常に未指定として全検索)
        // capsuleDto02.setKind("02");
        assertFalse(urlAns.contains("&kind="));

        // 履歴該否
        // capsuleDto02.setChange("0");
        assertTrue(urlAns.contains("&change=0"));

        // 登記記録の閉鎖該否(過去データを見る場合、閉鎖を使いうるので常にON)
        assertFalse(urlAns.contains("&close="));

        // 指定年月日開始
        // capsuleDto02.setFrom(LocalDate.of(2022, 6, 11));
        assertTrue(urlAns.contains("&from=2022-06-11"));

        // 指定年月日終了
        // capsuleDto02.setTo(LocalDate.of(2023, 9, 18));
        assertTrue(urlAns.contains("&to=2023-09-18"));

        // 分割番号(ページングのページ番号)
        // private Integer divide;
    }

}
