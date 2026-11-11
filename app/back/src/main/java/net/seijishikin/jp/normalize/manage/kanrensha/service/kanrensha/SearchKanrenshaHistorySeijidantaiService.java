package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import org.springframework.beans.factory.annotation.Autowired; // NOPMD HighImports
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.SearchHistoryKanrenshaCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.SearchHistoryKanrenshaSeijidantaiResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory02Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory03Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory04Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory05Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory06Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory07Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory08Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory09Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory10Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory11Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory12Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory13Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory14Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory15Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory16Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory17Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory18Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory19Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory20Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory21Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory22Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory23Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory24Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory25Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory26Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory27Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory28Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory29Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory30Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory31Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory32Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory33Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory34Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory35Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory36Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory37Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory38Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory39Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory40Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory41Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory42Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory43Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory44Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory45Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory46Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory47Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory99Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaSeijidantaiHistory01Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.GetPrefectureLgCodeService;

/**
 * 関連者政治団体履歴検索Service
 */
@Service
public class SearchKanrenshaHistorySeijidantaiService { // NOPMD TooManyMethods

    /** 関連者政治履歴検索Logic(01) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory01Logic searchKanrenshaSeijidantaiHistory01Logic;

    /** 関連者政治団体履歴検索Logic(02) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory02Logic searchKanrenshaSeijidantaiHistory02Logic;

    /** 関連者政治団体履歴検索Logic(03) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory03Logic searchKanrenshaSeijidantaiHistory03Logic;

    /** 関連者政治団体履歴検索Logic(04) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory04Logic searchKanrenshaSeijidantaiHistory04Logic;

    /** 関連者政治団体履歴検索Logic(05) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory05Logic searchKanrenshaSeijidantaiHistory05Logic;

    /** 関連者政治団体履歴検索Logic(06) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory06Logic searchKanrenshaSeijidantaiHistory06Logic;

    /** 関連者政治団体履歴検索Logic(07) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory07Logic searchKanrenshaSeijidantaiHistory07Logic;

    /** 関連者政治団体履歴検索Logic(08) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory08Logic searchKanrenshaSeijidantaiHistory08Logic;

    /** 関連者政治団体履歴検索Logic(09) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory09Logic searchKanrenshaSeijidantaiHistory09Logic;

    /** 関連者政治団体履歴検索Logic(10) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory10Logic searchKanrenshaSeijidantaiHistory10Logic;

    /** 関連者政治団体履歴検索Logic(11) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory11Logic searchKanrenshaSeijidantaiHistory11Logic;

    /** 関連者政治団体履歴検索Logic(12) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory12Logic searchKanrenshaSeijidantaiHistory12Logic;

    /** 関連者政治団体履歴検索Logic(13) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory13Logic searchKanrenshaSeijidantaiHistory13Logic;

    /** 関連者政治団体履歴検索Logic(14) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory14Logic searchKanrenshaSeijidantaiHistory14Logic;

    /** 関連者政治団体履歴検索Logic(15) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory15Logic searchKanrenshaSeijidantaiHistory15Logic;

    /** 関連者政治団体履歴検索Logic(16) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory16Logic searchKanrenshaSeijidantaiHistory16Logic;

    /** 関連者政治団体履歴検索Logic(17) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory17Logic searchKanrenshaSeijidantaiHistory17Logic;

    /** 関連者政治団体履歴検索Logic(18) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory18Logic searchKanrenshaSeijidantaiHistory18Logic;

    /** 関連者政治団体履歴検索Logic(19) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory19Logic searchKanrenshaSeijidantaiHistory19Logic;

    /** 関連者政治団体履歴検索Logic(20) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory20Logic searchKanrenshaSeijidantaiHistory20Logic;

    /** 関連者政治団体履歴検索Logic(21) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory21Logic searchKanrenshaSeijidantaiHistory21Logic;

    /** 関連者政治団体履歴検索Logic(22) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory22Logic searchKanrenshaSeijidantaiHistory22Logic;

    /** 関連者政治団体履歴検索Logic(23) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory23Logic searchKanrenshaSeijidantaiHistory23Logic;

    /** 関連者政治団体履歴検索Logic(24) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory24Logic searchKanrenshaSeijidantaiHistory24Logic;

    /** 関連者政治団体履歴検索Logic(25) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory25Logic searchKanrenshaSeijidantaiHistory25Logic;

    /** 関連者政治団体履歴検索Logic(26) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory26Logic searchKanrenshaSeijidantaiHistory26Logic;

    /** 関連者政治団体履歴検索Logic(27) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory27Logic searchKanrenshaSeijidantaiHistory27Logic;

    /** 関連者政治団体履歴検索Logic(28) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory28Logic searchKanrenshaSeijidantaiHistory28Logic;

    /** 関連者政治団体履歴検索Logic(29) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory29Logic searchKanrenshaSeijidantaiHistory29Logic;

    /** 関連者政治団体履歴検索Logic(30) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory30Logic searchKanrenshaSeijidantaiHistory30Logic;

    /** 関連者政治団体履歴検索Logic(31) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory31Logic searchKanrenshaSeijidantaiHistory31Logic;

    /** 関連者政治団体履歴検索Logic(32) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory32Logic searchKanrenshaSeijidantaiHistory32Logic;

    /** 関連者政治団体履歴検索Logic(33) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory33Logic searchKanrenshaSeijidantaiHistory33Logic;

    /** 関連者政治団体履歴検索Logic(34) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory34Logic searchKanrenshaSeijidantaiHistory34Logic;

    /** 関連者政治団体履歴検索Logic(35) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory35Logic searchKanrenshaSeijidantaiHistory35Logic;

    /** 関連者政治団体履歴検索Logic(36) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory36Logic searchKanrenshaSeijidantaiHistory36Logic;

    /** 関連者政治団体履歴検索Logic(37) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory37Logic searchKanrenshaSeijidantaiHistory37Logic;

    /** 関連者政治団体履歴検索Logic(38) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory38Logic searchKanrenshaSeijidantaiHistory38Logic;

    /** 関連者政治団体履歴検索Logic(39) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory39Logic searchKanrenshaSeijidantaiHistory39Logic;

    /** 関連者政治団体履歴検索Logic(40) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory40Logic searchKanrenshaSeijidantaiHistory40Logic;

    /** 関連者政治団体履歴検索Logic(41) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory41Logic searchKanrenshaSeijidantaiHistory41Logic;

    /** 関連者政治団体履歴検索Logic(42) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory42Logic searchKanrenshaSeijidantaiHistory42Logic;

    /** 関連者政治団体履歴検索Logic(43) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory43Logic searchKanrenshaSeijidantaiHistory43Logic;

    /** 関連者政治団体履歴検索Logic(44) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory44Logic searchKanrenshaSeijidantaiHistory44Logic;

    /** 関連者政治団体履歴検索Logic(45) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory45Logic searchKanrenshaSeijidantaiHistory45Logic;

    /** 関連者政治団体履歴検索Logic(46) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory46Logic searchKanrenshaSeijidantaiHistory46Logic;

    /** 関連者政治団体履歴検索Logic(47) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory47Logic searchKanrenshaSeijidantaiHistory47Logic;

    /** 関連者政治団体履歴検索Logic(99) */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory99Logic searchKanrenshaSeijidantaiHistory99Logic;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 関連者履歴全文検索条件Dto
     * @return 関連者政治団体履歴検索結果Dto
     */
    public SearchHistoryKanrenshaSeijidantaiResultDto practice(final SearchHistoryKanrenshaCapsuleDto capsuleDto) {

        String prefCode = "01";

        switch (capsuleDto.getPrefCode()) {
            case GetPrefectureLgCodeService.PREF_01_CODE:
                return searchKanrenshaSeijidantaiHistory01Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_02_CODE:
                return searchKanrenshaSeijidantaiHistory02Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_03_CODE:
                return searchKanrenshaSeijidantaiHistory03Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_04_CODE:
                return searchKanrenshaSeijidantaiHistory04Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_05_CODE:
                return searchKanrenshaSeijidantaiHistory05Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_06_CODE:
                return searchKanrenshaSeijidantaiHistory06Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_07_CODE:
                return searchKanrenshaSeijidantaiHistory07Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_08_CODE:
                return searchKanrenshaSeijidantaiHistory08Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_09_CODE:
                return searchKanrenshaSeijidantaiHistory09Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_10_CODE:
                return searchKanrenshaSeijidantaiHistory10Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_11_CODE:
                return searchKanrenshaSeijidantaiHistory11Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_12_CODE:
                return searchKanrenshaSeijidantaiHistory12Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_13_CODE:
                return searchKanrenshaSeijidantaiHistory13Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_14_CODE:
                return searchKanrenshaSeijidantaiHistory14Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_15_CODE:
                return searchKanrenshaSeijidantaiHistory15Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_16_CODE:
                return searchKanrenshaSeijidantaiHistory16Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_17_CODE:
                return searchKanrenshaSeijidantaiHistory17Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_18_CODE:
                return searchKanrenshaSeijidantaiHistory18Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_19_CODE:
                return searchKanrenshaSeijidantaiHistory19Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_20_CODE:
                return searchKanrenshaSeijidantaiHistory20Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_21_CODE:
                return searchKanrenshaSeijidantaiHistory21Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_22_CODE:
                return searchKanrenshaSeijidantaiHistory22Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_23_CODE:
                return searchKanrenshaSeijidantaiHistory23Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_24_CODE:
                return searchKanrenshaSeijidantaiHistory24Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_25_CODE:
                return searchKanrenshaSeijidantaiHistory25Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_26_CODE:
                return searchKanrenshaSeijidantaiHistory26Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_27_CODE:
                return searchKanrenshaSeijidantaiHistory27Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_28_CODE:
                return searchKanrenshaSeijidantaiHistory28Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_29_CODE:
                return searchKanrenshaSeijidantaiHistory29Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_30_CODE:
                return searchKanrenshaSeijidantaiHistory30Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_31_CODE:
                return searchKanrenshaSeijidantaiHistory31Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_32_CODE:
                return searchKanrenshaSeijidantaiHistory32Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_33_CODE:
                return searchKanrenshaSeijidantaiHistory33Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_34_CODE:
                return searchKanrenshaSeijidantaiHistory34Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_35_CODE:
                return searchKanrenshaSeijidantaiHistory35Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_36_CODE:
                return searchKanrenshaSeijidantaiHistory36Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_37_CODE:
                return searchKanrenshaSeijidantaiHistory37Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_38_CODE:
                return searchKanrenshaSeijidantaiHistory38Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_39_CODE:
                return searchKanrenshaSeijidantaiHistory39Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_40_CODE:
                return searchKanrenshaSeijidantaiHistory40Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_41_CODE:
                return searchKanrenshaSeijidantaiHistory41Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_42_CODE:
                return searchKanrenshaSeijidantaiHistory42Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_43_CODE:
                return searchKanrenshaSeijidantaiHistory43Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_44_CODE:
                return searchKanrenshaSeijidantaiHistory44Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_45_CODE:
                return searchKanrenshaSeijidantaiHistory45Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_46_CODE:
                return searchKanrenshaSeijidantaiHistory46Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_47_CODE:
                return searchKanrenshaSeijidantaiHistory47Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_99_CODE:
                return searchKanrenshaSeijidantaiHistory99Logic.practice(capsuleDto);

            default:
                throw new IllegalArgumentException("Unexpected value: " + prefCode);
        }

    }

}
