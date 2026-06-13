package net.seijishikin.jp.normalize.manage.kanrensha.logic.houjin_no;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.houjin_no.SearchHoujinNoCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressAllCityEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressAllCityRepository;

/**
 * ValidateCreateSearchHoujinNoUrlLogic単体テスト
 */
class ValidateCreateSearchHoujinNoUrlLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @InjectMocks
    private ValidateCreateSearchHoujinNoUrlLogic validateLogic;

    /** 地方自治体コードReppositoryMock */
    @Mock
    private AddressAllCityRepository addressAllCityRepository;

    /** アクセスUrl */
    private static final String VALID_URL = "http://localhost:8080/search";
    /** 検索条件Dto */
    private SearchHoujinNoCapsuleDto validDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // デフォルトで妥当なDTOを作成
        validDto = new SearchHoujinNoCapsuleDto();
        validDto.setAppId("app123");
        validDto.setName("テスト商事");
        validDto.setMode("");
        validDto.setTarget("");
        validDto.setAddress("");
        validDto.setKind("");
        validDto.setChange("");
        validDto.setFrom(DtoEntityInitialValueInterface.INIT_DATE);
        validDto.setTo(DtoEntityInitialValueInterface.INIT_DATE);
    }

    @Test
    void testDomainUrlNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validateLogic.practice(null, validDto);
        });
        assertEquals("接続URLが未指定です", exception.getMessage());
    }

    @Test
    void testDomainUrlEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validateLogic.practice("", validDto);
        });
        assertEquals("接続URLが未指定です", exception.getMessage());
    }

    @Test
    void testCapsuleDtoNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validateLogic.practice(VALID_URL, null);
        });
        assertEquals("検索条件が未指定です", exception.getMessage());
    }

    @Test
    void testAppIdNull() {
        validDto.setAppId(null);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validateLogic.practice(VALID_URL, validDto);
        });
        assertEquals("アプリケーションIdが未指定です", exception.getMessage());
    }

    @Test
    void testAppIdEmpty() {
        validDto.setAppId("");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validateLogic.practice(VALID_URL, validDto);
        });
        assertEquals("アプリケーションIdが未指定です", exception.getMessage());
    }

    @Test
    void testNameNull() {
        validDto.setName(null);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validateLogic.practice(VALID_URL, validDto);
        });
        assertEquals("検索条件名称が未指定です", exception.getMessage());
    }

    @Test
    void testNameEmpty() {
        validDto.setName("");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validateLogic.practice(VALID_URL, validDto);
        });
        assertEquals("検索条件名称が未指定です", exception.getMessage());
    }

    @Test
    void testModeValid() {
        validDto.setMode("1");
        assertDoesNotThrow(() -> validateLogic.practice(VALID_URL, validDto));

        validDto.setMode("2");
        assertDoesNotThrow(() -> validateLogic.practice(VALID_URL, validDto));
    }

    @Test
    void testModeInvalid() {
        validDto.setMode("3");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validateLogic.practice(VALID_URL, validDto);
        });
        assertEquals("名称検索方式の指定値が不正です", exception.getMessage());
    }

    @Test
    void testTargetValid() {
        validDto.setTarget("1");
        assertDoesNotThrow(() -> validateLogic.practice(VALID_URL, validDto));

        validDto.setTarget("2");
        assertDoesNotThrow(() -> validateLogic.practice(VALID_URL, validDto));

        validDto.setTarget("3");
        assertDoesNotThrow(() -> validateLogic.practice(VALID_URL, validDto));
    }

    @Test
    void testTargetInvalid() {
        validDto.setTarget("4");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validateLogic.practice(VALID_URL, validDto);
        });
        assertEquals("名称検索対象の指定値が不正です", exception.getMessage());
    }

    @Test
    void testAddressInvalidLength() {
        validDto.setAddress("1"); // 1桁
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validateLogic.practice(VALID_URL, validDto);
        });
        assertEquals("市区町村コードの桁数が不正です", exception.getMessage());
    }

    @Test
    void testAddressInvalidFormat() {
        validDto.setAddress("1a"); // 2桁だが非数字
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validateLogic.practice(VALID_URL, validDto);
        });
        assertEquals("市区町村コードの形式が不正です", exception.getMessage());
    }

    @Test
    void testAddressPrefCodeValid() {
        validDto.setAddress("13"); // 2桁
        when(addressAllCityRepository.findByLgCodeStartingWith("13")).thenReturn(List.of(new AddressAllCityEntity()));

        assertDoesNotThrow(() -> validateLogic.practice(VALID_URL, validDto));
        verify(addressAllCityRepository, times(1)).findByLgCodeStartingWith("13");
    }

    @Test
    void testAddressPrefCodeNotFound() {
        validDto.setAddress("99"); // 2桁
        when(addressAllCityRepository.findByLgCodeStartingWith("99")).thenReturn(Collections.emptyList());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validateLogic.practice(VALID_URL, validDto);
        });
        assertEquals("存在しない市区町村コードが指定されています", exception.getMessage());
    }

    @Test
    void testAddressCityCode5Valid() {
        validDto.setAddress("13101"); // 5桁
        when(addressAllCityRepository.findByLgCodeStartingWith("13101"))
                .thenReturn(List.of(new AddressAllCityEntity()));

        assertDoesNotThrow(() -> validateLogic.practice(VALID_URL, validDto));
        verify(addressAllCityRepository, times(1)).findByLgCodeStartingWith("13101");
    }

    @Test
    void testAddressCityCode6Valid() {
        validDto.setAddress("131016"); // 6桁
        when(addressAllCityRepository.findByLgCodeAndIsLatestTrue("131016"))
                .thenReturn(List.of(new AddressAllCityEntity()));

        assertDoesNotThrow(() -> validateLogic.practice(VALID_URL, validDto));
        verify(addressAllCityRepository, times(1)).findByLgCodeAndIsLatestTrue("131016");
    }

    @Test
    void testAddressCityCode6NotFound() {
        validDto.setAddress("999999"); // 6桁
        when(addressAllCityRepository.findByLgCodeAndIsLatestTrue("999999")).thenReturn(Collections.emptyList());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validateLogic.practice(VALID_URL, validDto);
        });
        assertEquals("存在しない市区町村コードが指定されています", exception.getMessage());
    }

    @Test
    void testKindValid() {
        validDto.setKind("01");
        assertDoesNotThrow(() -> validateLogic.practice(VALID_URL, validDto));

        validDto.setKind("02");
        assertDoesNotThrow(() -> validateLogic.practice(VALID_URL, validDto));

        validDto.setKind("03");
        assertDoesNotThrow(() -> validateLogic.practice(VALID_URL, validDto));

        validDto.setKind("04");
        assertDoesNotThrow(() -> validateLogic.practice(VALID_URL, validDto));
    }

    @Test
    void testKindInvalid() {
        validDto.setKind("05");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validateLogic.practice(VALID_URL, validDto);
        });
        assertEquals("法人種別の指定値が不正です", exception.getMessage());
    }

    @Test
    void testChangeValid() {
        validDto.setChange("0");
        assertDoesNotThrow(() -> validateLogic.practice(VALID_URL, validDto));

        validDto.setChange("1");
        assertDoesNotThrow(() -> validateLogic.practice(VALID_URL, validDto));
    }

    @Test
    void testChangeInvalid() {
        validDto.setChange("2");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validateLogic.practice(VALID_URL, validDto);
        });
        assertEquals("履歴該否の指定値が不正です", exception.getMessage());
    }

    @Test
    void testCloseValid() {
        SearchHoujinNoCapsuleDto mockDto = mock(SearchHoujinNoCapsuleDto.class);
        when(mockDto.getAppId()).thenReturn("app123");
        when(mockDto.getName()).thenReturn("テスト商事");
        when(mockDto.getClose()).thenReturn("0");

        assertDoesNotThrow(() -> validateLogic.practice(VALID_URL, mockDto));

        when(mockDto.getClose()).thenReturn("1");
        assertDoesNotThrow(() -> validateLogic.practice(VALID_URL, mockDto));
    }

    @Test
    void testCloseInvalid() {
        SearchHoujinNoCapsuleDto mockDto = mock(SearchHoujinNoCapsuleDto.class);
        when(mockDto.getAppId()).thenReturn("app123");
        when(mockDto.getName()).thenReturn("テスト商事");
        when(mockDto.getClose()).thenReturn("2");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validateLogic.practice(VALID_URL, mockDto);
        });
        assertEquals("登記記録の閉鎖該否の指定値が不正です", exception.getMessage());
    }

    @Test
    void testFromDateValid() {
        validDto.setFrom(LocalDate.of(2025, 10, 6)); // 制度開始日の翌日
        assertDoesNotThrow(() -> validateLogic.practice(VALID_URL, validDto));
    }

    @Test
    void testFromDateInvalidOnEffectDate() {
        validDto.setFrom(LocalDate.of(2025, 10, 5)); // 制度開始日当日（法施行前2025-10-05以前を指定するとエラー）
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validateLogic.practice(VALID_URL, validDto);
        });
        assertEquals("指定年月日開始に2025-10-05以前の日付は指定できません", exception.getMessage());
    }

    @Test
    void testFromDateInvalidBeforeEffectDate() {
        validDto.setFrom(LocalDate.of(2025, 10, 4)); // 制度開始日の前日
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validateLogic.practice(VALID_URL, validDto);
        });
        assertEquals("指定年月日開始に2025-10-05以前の日付は指定できません", exception.getMessage());
    }

    @Test
    void testToDateValid() {
        validDto.setTo(LocalDate.of(2025, 10, 6)); // 制度開始日の翌日
        assertDoesNotThrow(() -> validateLogic.practice(VALID_URL, validDto));
    }

    @Test
    void testToDateInvalidOnEffectDate() {
        validDto.setTo(LocalDate.of(2025, 10, 5)); // 制度開始日当日
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validateLogic.practice(VALID_URL, validDto);
        });
        assertEquals("指定年月日終了に2025-10-05以前の日付は指定できません", exception.getMessage());
    }

    @Test
    void testToDateInvalidBeforeEffectDate() {
        validDto.setTo(LocalDate.of(2025, 10, 4)); // 制度開始日の前日
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validateLogic.practice(VALID_URL, validDto);
        });
        assertEquals("指定年月日終了に2025-10-05以前の日付は指定できません", exception.getMessage());
    }

    @Test
    void testAllFieldsValid() {
        validDto.setMode("1");
        validDto.setTarget("2");
        validDto.setAddress("131016");
        validDto.setKind("03");
        validDto.setChange("1");
        validDto.setFrom(LocalDate.of(2026, 6, 7));
        validDto.setTo(LocalDate.of(2026, 6, 8));

        when(addressAllCityRepository.findByLgCodeAndIsLatestTrue("131016"))
                .thenReturn(List.of(new AddressAllCityEntity()));

        assertDoesNotThrow(() -> validateLogic.practice(VALID_URL, validDto));
    }
}
