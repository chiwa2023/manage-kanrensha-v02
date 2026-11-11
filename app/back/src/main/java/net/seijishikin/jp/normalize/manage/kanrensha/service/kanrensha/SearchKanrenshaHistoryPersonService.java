package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import org.springframework.beans.factory.annotation.Autowired; // NOPMD HighImports
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.SearchHistoryKanrenshaCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.SearchHistoryKanrenshaPersonResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory02Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory03Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory04Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory05Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory06Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory07Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory08Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory09Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory10Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory11Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory12Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory13Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory14Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory15Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory16Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory17Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory18Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory19Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory20Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory21Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory22Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory23Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory24Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory25Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory26Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory27Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory28Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory29Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory30Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory31Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory32Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory33Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory34Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory35Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory36Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory37Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory38Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory39Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory40Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory41Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory42Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory43Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory44Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory45Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory46Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory47Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory99Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaPersonHistory01Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.GetPrefectureLgCodeService;

/**
 * 関連者個人履歴検索Service
 */
@Service
public class SearchKanrenshaHistoryPersonService { // NOPMD TooManyMethods

    /** 関連者個人履歴検索Logic(01) */
    @Autowired
    private SearchKanrenshaPersonHistory01Logic searchKanrenshaPersonHistory01Logic;

    /** 関連者個人履歴検索Logic(02) */
    @Autowired
    private SearchKanrenshaPersonHistory02Logic searchKanrenshaPersonHistory02Logic;

    /** 関連者個人履歴検索Logic(03) */
    @Autowired
    private SearchKanrenshaPersonHistory03Logic searchKanrenshaPersonHistory03Logic;

    /** 関連者個人履歴検索Logic(04) */
    @Autowired
    private SearchKanrenshaPersonHistory04Logic searchKanrenshaPersonHistory04Logic;

    /** 関連者個人履歴検索Logic(05) */
    @Autowired
    private SearchKanrenshaPersonHistory05Logic searchKanrenshaPersonHistory05Logic;

    /** 関連者個人履歴検索Logic(06) */
    @Autowired
    private SearchKanrenshaPersonHistory06Logic searchKanrenshaPersonHistory06Logic;

    /** 関連者個人履歴検索Logic(07) */
    @Autowired
    private SearchKanrenshaPersonHistory07Logic searchKanrenshaPersonHistory07Logic;

    /** 関連者個人履歴検索Logic(08) */
    @Autowired
    private SearchKanrenshaPersonHistory08Logic searchKanrenshaPersonHistory08Logic;

    /** 関連者個人履歴検索Logic(09) */
    @Autowired
    private SearchKanrenshaPersonHistory09Logic searchKanrenshaPersonHistory09Logic;

    /** 関連者個人履歴検索Logic(10) */
    @Autowired
    private SearchKanrenshaPersonHistory10Logic searchKanrenshaPersonHistory10Logic;

    /** 関連者個人履歴検索Logic(11) */
    @Autowired
    private SearchKanrenshaPersonHistory11Logic searchKanrenshaPersonHistory11Logic;

    /** 関連者個人履歴検索Logic(12) */
    @Autowired
    private SearchKanrenshaPersonHistory12Logic searchKanrenshaPersonHistory12Logic;

    /** 関連者個人履歴検索Logic(13) */
    @Autowired
    private SearchKanrenshaPersonHistory13Logic searchKanrenshaPersonHistory13Logic;

    /** 関連者個人履歴検索Logic(14) */
    @Autowired
    private SearchKanrenshaPersonHistory14Logic searchKanrenshaPersonHistory14Logic;

    /** 関連者個人履歴検索Logic(15) */
    @Autowired
    private SearchKanrenshaPersonHistory15Logic searchKanrenshaPersonHistory15Logic;

    /** 関連者個人履歴検索Logic(16) */
    @Autowired
    private SearchKanrenshaPersonHistory16Logic searchKanrenshaPersonHistory16Logic;

    /** 関連者個人履歴検索Logic(17) */
    @Autowired
    private SearchKanrenshaPersonHistory17Logic searchKanrenshaPersonHistory17Logic;

    /** 関連者個人履歴検索Logic(18) */
    @Autowired
    private SearchKanrenshaPersonHistory18Logic searchKanrenshaPersonHistory18Logic;

    /** 関連者個人履歴検索Logic(19) */
    @Autowired
    private SearchKanrenshaPersonHistory19Logic searchKanrenshaPersonHistory19Logic;

    /** 関連者個人履歴検索Logic(20) */
    @Autowired
    private SearchKanrenshaPersonHistory20Logic searchKanrenshaPersonHistory20Logic;

    /** 関連者個人履歴検索Logic(21) */
    @Autowired
    private SearchKanrenshaPersonHistory21Logic searchKanrenshaPersonHistory21Logic;

    /** 関連者個人履歴検索Logic(22) */
    @Autowired
    private SearchKanrenshaPersonHistory22Logic searchKanrenshaPersonHistory22Logic;

    /** 関連者個人履歴検索Logic(23) */
    @Autowired
    private SearchKanrenshaPersonHistory23Logic searchKanrenshaPersonHistory23Logic;

    /** 関連者個人履歴検索Logic(24) */
    @Autowired
    private SearchKanrenshaPersonHistory24Logic searchKanrenshaPersonHistory24Logic;

    /** 関連者個人履歴検索Logic(25) */
    @Autowired
    private SearchKanrenshaPersonHistory25Logic searchKanrenshaPersonHistory25Logic;

    /** 関連者個人履歴検索Logic(26) */
    @Autowired
    private SearchKanrenshaPersonHistory26Logic searchKanrenshaPersonHistory26Logic;

    /** 関連者個人履歴検索Logic(27) */
    @Autowired
    private SearchKanrenshaPersonHistory27Logic searchKanrenshaPersonHistory27Logic;

    /** 関連者個人履歴検索Logic(28) */
    @Autowired
    private SearchKanrenshaPersonHistory28Logic searchKanrenshaPersonHistory28Logic;

    /** 関連者個人履歴検索Logic(29) */
    @Autowired
    private SearchKanrenshaPersonHistory29Logic searchKanrenshaPersonHistory29Logic;

    /** 関連者個人履歴検索Logic(30) */
    @Autowired
    private SearchKanrenshaPersonHistory30Logic searchKanrenshaPersonHistory30Logic;

    /** 関連者個人履歴検索Logic(31) */
    @Autowired
    private SearchKanrenshaPersonHistory31Logic searchKanrenshaPersonHistory31Logic;

    /** 関連者個人履歴検索Logic(32) */
    @Autowired
    private SearchKanrenshaPersonHistory32Logic searchKanrenshaPersonHistory32Logic;

    /** 関連者個人履歴検索Logic(33) */
    @Autowired
    private SearchKanrenshaPersonHistory33Logic searchKanrenshaPersonHistory33Logic;

    /** 関連者個人履歴検索Logic(34) */
    @Autowired
    private SearchKanrenshaPersonHistory34Logic searchKanrenshaPersonHistory34Logic;

    /** 関連者個人履歴検索Logic(35) */
    @Autowired
    private SearchKanrenshaPersonHistory35Logic searchKanrenshaPersonHistory35Logic;

    /** 関連者個人履歴検索Logic(36) */
    @Autowired
    private SearchKanrenshaPersonHistory36Logic searchKanrenshaPersonHistory36Logic;

    /** 関連者個人履歴検索Logic(37) */
    @Autowired
    private SearchKanrenshaPersonHistory37Logic searchKanrenshaPersonHistory37Logic;

    /** 関連者個人履歴検索Logic(38) */
    @Autowired
    private SearchKanrenshaPersonHistory38Logic searchKanrenshaPersonHistory38Logic;

    /** 関連者個人履歴検索Logic(39) */
    @Autowired
    private SearchKanrenshaPersonHistory39Logic searchKanrenshaPersonHistory39Logic;

    /** 関連者個人履歴検索Logic(40) */
    @Autowired
    private SearchKanrenshaPersonHistory40Logic searchKanrenshaPersonHistory40Logic;

    /** 関連者個人履歴検索Logic(41) */
    @Autowired
    private SearchKanrenshaPersonHistory41Logic searchKanrenshaPersonHistory41Logic;

    /** 関連者個人履歴検索Logic(42) */
    @Autowired
    private SearchKanrenshaPersonHistory42Logic searchKanrenshaPersonHistory42Logic;

    /** 関連者個人履歴検索Logic(43) */
    @Autowired
    private SearchKanrenshaPersonHistory43Logic searchKanrenshaPersonHistory43Logic;

    /** 関連者個人履歴検索Logic(44) */
    @Autowired
    private SearchKanrenshaPersonHistory44Logic searchKanrenshaPersonHistory44Logic;

    /** 関連者個人履歴検索Logic(45) */
    @Autowired
    private SearchKanrenshaPersonHistory45Logic searchKanrenshaPersonHistory45Logic;

    /** 関連者個人履歴検索Logic(46) */
    @Autowired
    private SearchKanrenshaPersonHistory46Logic searchKanrenshaPersonHistory46Logic;

    /** 関連者個人履歴検索Logic(47) */
    @Autowired
    private SearchKanrenshaPersonHistory47Logic searchKanrenshaPersonHistory47Logic;

    /** 関連者個人履歴検索Logic(99) */
    @Autowired
    private SearchKanrenshaPersonHistory99Logic searchKanrenshaPersonHistory99Logic;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 関連者履歴全文検索条件Dto
     * @return 関連者個人履歴検索結果Dto
     */
    public SearchHistoryKanrenshaPersonResultDto practice(final SearchHistoryKanrenshaCapsuleDto capsuleDto) {

        String prefCode = capsuleDto.getPrefCode();

        switch (prefCode) {
            case GetPrefectureLgCodeService.PREF_01_CODE:
                return searchKanrenshaPersonHistory01Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_02_CODE:
                return searchKanrenshaPersonHistory02Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_03_CODE:
                return searchKanrenshaPersonHistory03Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_04_CODE:
                return searchKanrenshaPersonHistory04Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_05_CODE:
                return searchKanrenshaPersonHistory05Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_06_CODE:
                return searchKanrenshaPersonHistory06Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_07_CODE:
                return searchKanrenshaPersonHistory07Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_08_CODE:
                return searchKanrenshaPersonHistory08Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_09_CODE:
                return searchKanrenshaPersonHistory09Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_10_CODE:
                return searchKanrenshaPersonHistory10Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_11_CODE:
                return searchKanrenshaPersonHistory11Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_12_CODE:
                return searchKanrenshaPersonHistory12Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_13_CODE:
                return searchKanrenshaPersonHistory13Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_14_CODE:
                return searchKanrenshaPersonHistory14Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_15_CODE:
                return searchKanrenshaPersonHistory15Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_16_CODE:
                return searchKanrenshaPersonHistory16Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_17_CODE:
                return searchKanrenshaPersonHistory17Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_18_CODE:
                return searchKanrenshaPersonHistory18Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_19_CODE:
                return searchKanrenshaPersonHistory19Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_20_CODE:
                return searchKanrenshaPersonHistory20Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_21_CODE:
                return searchKanrenshaPersonHistory21Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_22_CODE:
                return searchKanrenshaPersonHistory22Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_23_CODE:
                return searchKanrenshaPersonHistory23Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_24_CODE:
                return searchKanrenshaPersonHistory24Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_25_CODE:
                return searchKanrenshaPersonHistory25Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_26_CODE:
                return searchKanrenshaPersonHistory26Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_27_CODE:
                return searchKanrenshaPersonHistory27Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_28_CODE:
                return searchKanrenshaPersonHistory28Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_29_CODE:
                return searchKanrenshaPersonHistory29Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_30_CODE:
                return searchKanrenshaPersonHistory30Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_31_CODE:
                return searchKanrenshaPersonHistory31Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_32_CODE:
                return searchKanrenshaPersonHistory32Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_33_CODE:
                return searchKanrenshaPersonHistory33Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_34_CODE:
                return searchKanrenshaPersonHistory34Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_35_CODE:
                return searchKanrenshaPersonHistory35Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_36_CODE:
                return searchKanrenshaPersonHistory36Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_37_CODE:
                return searchKanrenshaPersonHistory37Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_38_CODE:
                return searchKanrenshaPersonHistory38Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_39_CODE:
                return searchKanrenshaPersonHistory39Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_40_CODE:
                return searchKanrenshaPersonHistory40Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_41_CODE:
                return searchKanrenshaPersonHistory41Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_42_CODE:
                return searchKanrenshaPersonHistory42Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_43_CODE:
                return searchKanrenshaPersonHistory43Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_44_CODE:
                return searchKanrenshaPersonHistory44Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_45_CODE:
                return searchKanrenshaPersonHistory45Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_46_CODE:
                return searchKanrenshaPersonHistory46Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_47_CODE:
                return searchKanrenshaPersonHistory47Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_99_CODE:
                return searchKanrenshaPersonHistory99Logic.practice(capsuleDto);

            default:
                throw new IllegalArgumentException("Unexpected value: " + prefCode);
        }
    }

}
