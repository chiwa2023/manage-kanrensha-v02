package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import org.springframework.beans.factory.annotation.Autowired; // NOPMD HighImports
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.SearchHistoryKanrenshaCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.SearchHistoryKanrenshaKigyouDtResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory01Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory02Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory03Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory04Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory05Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory06Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory07Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory08Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory09Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory10Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory11Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory12Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory13Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory14Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory15Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory16Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory17Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory18Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory19Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory20Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory21Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory22Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory23Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory24Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory25Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory26Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory27Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory28Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory29Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory30Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory31Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory32Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory33Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory34Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory35Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory36Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory37Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory38Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory39Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory40Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory41Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory42Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory43Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory44Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory45Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory46Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory47Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode.SearchKanrenshaKigyouDtHistory99Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.GetPrefectureLgCodeService;

/**
 * 関連者企業・団体履歴検索Service
 */
@Service
public class SearchKanrenshaHistoryKigyouDtService { // NOPMD CouplingObjects

    /** 関連者企業・団体履歴検索Logic(01) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory01Logic searchKanrenshaKigyouDtHistory01Logic;

    /** 関連者企業・団体履歴検索Logic(02) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory02Logic searchKanrenshaKigyouDtHistory02Logic;

    /** 関連者企業・団体履歴検索Logic(03) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory03Logic searchKanrenshaKigyouDtHistory03Logic;

    /** 関連者企業・団体履歴検索Logic(04) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory04Logic searchKanrenshaKigyouDtHistory04Logic;

    /** 関連者企業・団体履歴検索Logic(05) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory05Logic searchKanrenshaKigyouDtHistory05Logic;

    /** 関連者企業・団体履歴検索Logic(06) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory06Logic searchKanrenshaKigyouDtHistory06Logic;

    /** 関連者企業・団体履歴検索Logic(07) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory07Logic searchKanrenshaKigyouDtHistory07Logic;

    /** 関連者企業・団体履歴検索Logic(08) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory08Logic searchKanrenshaKigyouDtHistory08Logic;

    /** 関連者企業・団体履歴検索Logic(09) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory09Logic searchKanrenshaKigyouDtHistory09Logic;

    /** 関連者企業・団体履歴検索Logic(10) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory10Logic searchKanrenshaKigyouDtHistory10Logic;

    /** 関連者企業・団体履歴検索Logic(11) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory11Logic searchKanrenshaKigyouDtHistory11Logic;

    /** 関連者企業・団体履歴検索Logic(12) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory12Logic searchKanrenshaKigyouDtHistory12Logic;

    /** 関連者企業・団体履歴検索Logic(13) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory13Logic searchKanrenshaKigyouDtHistory13Logic;

    /** 関連者企業・団体履歴検索Logic(14) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory14Logic searchKanrenshaKigyouDtHistory14Logic;

    /** 関連者企業・団体履歴検索Logic(15) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory15Logic searchKanrenshaKigyouDtHistory15Logic;

    /** 関連者企業・団体履歴検索Logic(16) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory16Logic searchKanrenshaKigyouDtHistory16Logic;

    /** 関連者企業・団体履歴検索Logic(17) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory17Logic searchKanrenshaKigyouDtHistory17Logic;

    /** 関連者企業・団体履歴検索Logic(18) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory18Logic searchKanrenshaKigyouDtHistory18Logic;

    /** 関連者企業・団体履歴検索Logic(19) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory19Logic searchKanrenshaKigyouDtHistory19Logic;

    /** 関連者企業・団体履歴検索Logic(20) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory20Logic searchKanrenshaKigyouDtHistory20Logic;

    /** 関連者企業・団体履歴検索Logic(21) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory21Logic searchKanrenshaKigyouDtHistory21Logic;

    /** 関連者企業・団体履歴検索Logic(22) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory22Logic searchKanrenshaKigyouDtHistory22Logic;

    /** 関連者企業・団体履歴検索Logic(23) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory23Logic searchKanrenshaKigyouDtHistory23Logic;

    /** 関連者企業・団体履歴検索Logic(24) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory24Logic searchKanrenshaKigyouDtHistory24Logic;

    /** 関連者企業・団体履歴検索Logic(25) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory25Logic searchKanrenshaKigyouDtHistory25Logic;

    /** 関連者企業・団体履歴検索Logic(26) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory26Logic searchKanrenshaKigyouDtHistory26Logic;

    /** 関連者企業・団体履歴検索Logic(27) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory27Logic searchKanrenshaKigyouDtHistory27Logic;

    /** 関連者企業・団体履歴検索Logic(28) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory28Logic searchKanrenshaKigyouDtHistory28Logic;

    /** 関連者企業・団体履歴検索Logic(29) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory29Logic searchKanrenshaKigyouDtHistory29Logic;

    /** 関連者企業・団体履歴検索Logic(30) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory30Logic searchKanrenshaKigyouDtHistory30Logic;

    /** 関連者企業・団体履歴検索Logic(31) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory31Logic searchKanrenshaKigyouDtHistory31Logic;

    /** 関連者企業・団体履歴検索Logic(32) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory32Logic searchKanrenshaKigyouDtHistory32Logic;

    /** 関連者企業・団体履歴検索Logic(33) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory33Logic searchKanrenshaKigyouDtHistory33Logic;

    /** 関連者企業・団体履歴検索Logic(34) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory34Logic searchKanrenshaKigyouDtHistory34Logic;

    /** 関連者企業・団体履歴検索Logic(35) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory35Logic searchKanrenshaKigyouDtHistory35Logic;

    /** 関連者企業・団体履歴検索Logic(36) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory36Logic searchKanrenshaKigyouDtHistory36Logic;

    /** 関連者企業・団体履歴検索Logic(37) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory37Logic searchKanrenshaKigyouDtHistory37Logic;

    /** 関連者企業・団体履歴検索Logic(38) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory38Logic searchKanrenshaKigyouDtHistory38Logic;

    /** 関連者企業・団体履歴検索Logic(39) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory39Logic searchKanrenshaKigyouDtHistory39Logic;

    /** 関連者企業・団体履歴検索Logic(40) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory40Logic searchKanrenshaKigyouDtHistory40Logic;

    /** 関連者企業・団体履歴検索Logic(41) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory41Logic searchKanrenshaKigyouDtHistory41Logic;

    /** 関連者企業・団体履歴検索Logic(42) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory42Logic searchKanrenshaKigyouDtHistory42Logic;

    /** 関連者企業・団体履歴検索Logic(43) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory43Logic searchKanrenshaKigyouDtHistory43Logic;

    /** 関連者企業・団体履歴検索Logic(44) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory44Logic searchKanrenshaKigyouDtHistory44Logic;

    /** 関連者企業・団体履歴検索Logic(45) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory45Logic searchKanrenshaKigyouDtHistory45Logic;

    /** 関連者企業・団体履歴検索Logic(46) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory46Logic searchKanrenshaKigyouDtHistory46Logic;

    /** 関連者企業・団体履歴検索Logic(47) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory47Logic searchKanrenshaKigyouDtHistory47Logic;

    /** 関連者企業・団体履歴検索Logic(99) */
    @Autowired
    private SearchKanrenshaKigyouDtHistory99Logic searchKanrenshaKigyouDtHistory99Logic;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 関連者履歴全文検索条件Dto
     * @return 関連者企業・団体履歴検索結果Dto
     */
    public SearchHistoryKanrenshaKigyouDtResultDto practice(final SearchHistoryKanrenshaCapsuleDto capsuleDto) {

        String prefCode = capsuleDto.getPrefCode();

        switch (prefCode) {
            case GetPrefectureLgCodeService.PREF_01_CODE:
                return searchKanrenshaKigyouDtHistory01Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_02_CODE:
                return searchKanrenshaKigyouDtHistory02Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_03_CODE:
                return searchKanrenshaKigyouDtHistory03Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_04_CODE:
                return searchKanrenshaKigyouDtHistory04Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_05_CODE:
                return searchKanrenshaKigyouDtHistory05Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_06_CODE:
                return searchKanrenshaKigyouDtHistory06Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_07_CODE:
                return searchKanrenshaKigyouDtHistory07Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_08_CODE:
                return searchKanrenshaKigyouDtHistory08Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_09_CODE:
                return searchKanrenshaKigyouDtHistory09Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_10_CODE:
                return searchKanrenshaKigyouDtHistory10Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_11_CODE:
                return searchKanrenshaKigyouDtHistory11Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_12_CODE:
                return searchKanrenshaKigyouDtHistory12Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_13_CODE:
                return searchKanrenshaKigyouDtHistory13Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_14_CODE:
                return searchKanrenshaKigyouDtHistory14Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_15_CODE:
                return searchKanrenshaKigyouDtHistory15Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_16_CODE:
                return searchKanrenshaKigyouDtHistory16Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_17_CODE:
                return searchKanrenshaKigyouDtHistory17Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_18_CODE:
                return searchKanrenshaKigyouDtHistory18Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_19_CODE:
                return searchKanrenshaKigyouDtHistory19Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_20_CODE:
                return searchKanrenshaKigyouDtHistory20Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_21_CODE:
                return searchKanrenshaKigyouDtHistory21Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_22_CODE:
                return searchKanrenshaKigyouDtHistory22Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_23_CODE:
                return searchKanrenshaKigyouDtHistory23Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_24_CODE:
                return searchKanrenshaKigyouDtHistory24Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_25_CODE:
                return searchKanrenshaKigyouDtHistory25Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_26_CODE:
                return searchKanrenshaKigyouDtHistory26Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_27_CODE:
                return searchKanrenshaKigyouDtHistory27Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_28_CODE:
                return searchKanrenshaKigyouDtHistory28Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_29_CODE:
                return searchKanrenshaKigyouDtHistory29Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_30_CODE:
                return searchKanrenshaKigyouDtHistory30Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_31_CODE:
                return searchKanrenshaKigyouDtHistory31Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_32_CODE:
                return searchKanrenshaKigyouDtHistory32Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_33_CODE:
                return searchKanrenshaKigyouDtHistory33Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_34_CODE:
                return searchKanrenshaKigyouDtHistory34Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_35_CODE:
                return searchKanrenshaKigyouDtHistory35Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_36_CODE:
                return searchKanrenshaKigyouDtHistory36Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_37_CODE:
                return searchKanrenshaKigyouDtHistory37Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_38_CODE:
                return searchKanrenshaKigyouDtHistory38Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_39_CODE:
                return searchKanrenshaKigyouDtHistory39Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_40_CODE:
                return searchKanrenshaKigyouDtHistory40Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_41_CODE:
                return searchKanrenshaKigyouDtHistory41Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_42_CODE:
                return searchKanrenshaKigyouDtHistory42Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_43_CODE:
                return searchKanrenshaKigyouDtHistory43Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_44_CODE:
                return searchKanrenshaKigyouDtHistory44Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_45_CODE:
                return searchKanrenshaKigyouDtHistory45Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_46_CODE:
                return searchKanrenshaKigyouDtHistory46Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_47_CODE:
                return searchKanrenshaKigyouDtHistory47Logic.practice(capsuleDto);

            case GetPrefectureLgCodeService.PREF_99_CODE:
                return searchKanrenshaKigyouDtHistory99Logic.practice(capsuleDto);

            default:
                throw new IllegalArgumentException("Unexpected value: " + prefCode);
        }

    }

}
