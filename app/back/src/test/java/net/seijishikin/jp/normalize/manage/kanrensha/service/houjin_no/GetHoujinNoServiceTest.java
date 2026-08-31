package net.seijishikin.jp.normalize.manage.kanrensha.service.houjin_no;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.houjin_no.HoujinNoDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.houjin_no.SearchHoujinNoCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.houjin_no.SearchHoujinNoResultDto;

/**
 * GetHoujinNoService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class GetHoujinNoServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetHoujinNoService getHoujinNoService;

    /** テストタグ */
    private static final String TEST_TAG = "ExternalService";

    /** アプリケーションId */
    private static final String APP_ID = "dummyId";

    /**
     * テストメソッド
     */
    @Test
    @Tag(TEST_TAG)
    void test403() {
        final SearchHoujinNoCapsuleDto capsuleDto = new SearchHoujinNoCapsuleDto();
        capsuleDto.setAppId(APP_ID);
        capsuleDto.setName("国税かもめ商事");
        capsuleDto.setType("02");

        final SearchHoujinNoResultDto resultDto = getHoujinNoService.pratice(capsuleDto);
        assertTrue(resultDto.getIsFailure());
        assertEquals("このアプリケーションIDに対して機能制限がされています", resultDto.getMessage());
    }

    /**
     * 404レスポンス
     */
    @Test
    @Tag(TEST_TAG)
    void test404() {
        final SearchHoujinNoCapsuleDto capsuleDto = new SearchHoujinNoCapsuleDto();
        capsuleDto.setAppId(APP_ID);
        capsuleDto.setName("国税きつつき商事");
        capsuleDto.setType("02");

        final SearchHoujinNoResultDto resultDto = getHoujinNoService.pratice(capsuleDto);
        assertTrue(resultDto.getIsFailure());
        assertEquals("アプリケーションIDが登録されていません", resultDto.getMessage());
    }

    /**
     * 500レスポンス
     */
    @Test
    @Tag(TEST_TAG)
    void test500() {
        final SearchHoujinNoCapsuleDto capsuleDto = new SearchHoujinNoCapsuleDto();
        capsuleDto.setAppId(APP_ID);
        capsuleDto.setName("国税くじゃく商事");
        capsuleDto.setType("02");

        final SearchHoujinNoResultDto resultDto = getHoujinNoService.pratice(capsuleDto);
        assertTrue(resultDto.getIsFailure());
        assertEquals("法人番号公表機能に障害等が発生しました", resultDto.getMessage());
    }

    /**
     * その他レスポンス
     */
    @Test
    @Tag(TEST_TAG)
    void testOther() {
        // 基本この仕様はない
        final SearchHoujinNoCapsuleDto capsuleDto = new SearchHoujinNoCapsuleDto();
        capsuleDto.setAppId(APP_ID);
        capsuleDto.setName("国税けろっぴ商事");
        capsuleDto.setType("02");

        final SearchHoujinNoResultDto resultDto = getHoujinNoService.pratice(capsuleDto);
        assertTrue(resultDto.getIsFailure());
        assertEquals("HTTP error code: 504", resultDto.getMessage());
    }

    /**
     * その他レスポンス
     */
    @Test
    @Tag(TEST_TAG)
    void test400WithCode() {
        // ボディにエラーコードとメッセージが載ってくる
        final SearchHoujinNoCapsuleDto capsuleDto = new SearchHoujinNoCapsuleDto();
        capsuleDto.setAppId(APP_ID);
        capsuleDto.setName("国税こだま商事");
        capsuleDto.setType("02");

        final SearchHoujinNoResultDto resultDto = getHoujinNoService.pratice(capsuleDto);
        assertTrue(resultDto.getIsFailure());
        assertEquals("Error from API: 所在地は半角数字で指定してください(042)", resultDto.getMessage());
    }

    /**
     * 正常リスト取得(2件)
     */
    @Test
    @Tag(TEST_TAG)
    void test200MinData() {
        // csv形式のボディを解析
        final SearchHoujinNoCapsuleDto capsuleDto = new SearchHoujinNoCapsuleDto();
        capsuleDto.setAppId(APP_ID);
        capsuleDto.setName("国税商事"); // default_csv
        capsuleDto.setType("02");
        capsuleDto.setDivide(2);

        final SearchHoujinNoResultDto resultDto = getHoujinNoService.pratice(capsuleDto);
        assertFalse(resultDto.getIsFailure());

        // 集計ヘッダ行
        assertEquals("2017-05-18", resultDto.getUpdateDate());
        assertEquals(19457, resultDto.getTotalCount());
        assertEquals(2, resultDto.getDivideNumber());
        assertEquals(10, resultDto.getDivideCount());

        // データリスト
        List<HoujinNoDto> listHoujinNo = resultDto.getHoujinNoList();
        assertEquals(2, listHoujinNo.size());

        HoujinNoDto dto1 = listHoujinNo.get(1);
        assertEquals("8:1234567", dto1.getHoujinNo());
        assertEquals("13:国税商事", dto1.getHoujinName());
        assertEquals("15:401", dto1.getKind());
        assertEquals("9:3", dto1.getProcess());
        assertEquals("16:和歌山県", dto1.getPrefectureName());
        assertEquals("17:架空市", dto1.getCityName());
    }

    /**
     * 正常リスト取得(2件)
     */
    @Test
    @Tag(TEST_TAG)
    void test200MaxData() {
        // csv形式のボディを解析
        final SearchHoujinNoCapsuleDto capsuleDto = new SearchHoujinNoCapsuleDto();
        capsuleDto.setAppId(APP_ID);
        capsuleDto.setName("国税あいがも商事"); // 01_csv
        capsuleDto.setType("02");
        capsuleDto.setDivide(2);

        final SearchHoujinNoResultDto resultDto = getHoujinNoService.pratice(capsuleDto);
        assertFalse(resultDto.getIsFailure());

        // 集計ヘッダ行
        assertEquals("2017-05-18", resultDto.getUpdateDate());
        assertEquals(19457, resultDto.getTotalCount());
        assertEquals(2, resultDto.getDivideNumber());
        assertEquals(10, resultDto.getDivideCount());

        // データリスト
        List<HoujinNoDto> listHoujinNo = resultDto.getHoujinNoList();
        assertEquals(2000, listHoujinNo.size());

        final String houjinNo = "1234567";

        HoujinNoDto dto0 = listHoujinNo.get(0);
        assertEquals(houjinNo, dto0.getHoujinNo());
        assertEquals("国税商事1", dto0.getHoujinName());
        assertEquals("401", dto0.getKind());

        HoujinNoDto dto100 = listHoujinNo.get(100);
        assertEquals(houjinNo, dto100.getHoujinNo());
        assertEquals("国税商事101", dto100.getHoujinName());
        assertEquals("501", dto100.getKind());

        HoujinNoDto dto1000 = listHoujinNo.get(1000);
        assertEquals(houjinNo, dto1000.getHoujinNo());
        assertEquals("国税商事1001", dto1000.getHoujinName());
        assertEquals("1401", dto1000.getKind());

        HoujinNoDto dto1999 = listHoujinNo.get(1999);
        assertEquals(houjinNo, dto1999.getHoujinNo());
        assertEquals("国税商事2000", dto1999.getHoujinName());
        assertEquals("2400", dto1999.getKind());
    }

}
