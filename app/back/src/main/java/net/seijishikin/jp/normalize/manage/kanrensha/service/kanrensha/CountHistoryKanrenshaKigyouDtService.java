package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import org.springframework.beans.factory.annotation.Autowired; // NOPMD HighImport
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.SearchHistoryKanrenshaCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory01Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory02Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory03Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory04Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory05Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory06Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory07Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory08Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory09Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory10Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory11Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory12Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory13Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory14Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory15Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory16Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory17Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory18Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory19Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory20Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory21Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory22Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory23Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory24Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory25Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory26Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory27Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory28Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory29Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory30Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory31Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory32Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory33Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory34Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory35Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory36Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory37Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory38Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory39Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory40Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory41Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory42Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory43Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory44Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory45Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory46Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory47Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory99Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.GetPrefectureLgCodeService;

/**
 * 関連者企業・団体履歴件数確認(全県)Service
 */
@Service
public class CountHistoryKanrenshaKigyouDtService { // NOPMD CouplingObjects

    /** 関連者企業・団体履歴Repository(01) */
    @Autowired
    private KanrenshaKigyouDtHistory01Repository kanrenshaKigyouDtHistory01Repository;

    /** 関連者企業・団体履歴Repository(02) */
    @Autowired
    private KanrenshaKigyouDtHistory02Repository kanrenshaKigyouDtHistory02Repository;

    /** 関連者企業・団体履歴Repository(03) */
    @Autowired
    private KanrenshaKigyouDtHistory03Repository kanrenshaKigyouDtHistory03Repository;

    /** 関連者企業・団体履歴Repository(04) */
    @Autowired
    private KanrenshaKigyouDtHistory04Repository kanrenshaKigyouDtHistory04Repository;

    /** 関連者企業・団体履歴Repository(05) */
    @Autowired
    private KanrenshaKigyouDtHistory05Repository kanrenshaKigyouDtHistory05Repository;

    /** 関連者企業・団体履歴Repository(06) */
    @Autowired
    private KanrenshaKigyouDtHistory06Repository kanrenshaKigyouDtHistory06Repository;

    /** 関連者企業・団体履歴Repository(07) */
    @Autowired
    private KanrenshaKigyouDtHistory07Repository kanrenshaKigyouDtHistory07Repository;

    /** 関連者企業・団体履歴Repository(08) */
    @Autowired
    private KanrenshaKigyouDtHistory08Repository kanrenshaKigyouDtHistory08Repository;

    /** 関連者企業・団体履歴Repository(09) */
    @Autowired
    private KanrenshaKigyouDtHistory09Repository kanrenshaKigyouDtHistory09Repository;

    /** 関連者企業・団体履歴Repository(10) */
    @Autowired
    private KanrenshaKigyouDtHistory10Repository kanrenshaKigyouDtHistory10Repository;

    /** 関連者企業・団体履歴Repository(11) */
    @Autowired
    private KanrenshaKigyouDtHistory11Repository kanrenshaKigyouDtHistory11Repository;

    /** 関連者企業・団体履歴Repository(12) */
    @Autowired
    private KanrenshaKigyouDtHistory12Repository kanrenshaKigyouDtHistory12Repository;

    /** 関連者企業・団体履歴Repository(13) */
    @Autowired
    private KanrenshaKigyouDtHistory13Repository kanrenshaKigyouDtHistory13Repository;

    /** 関連者企業・団体履歴Repository(14) */
    @Autowired
    private KanrenshaKigyouDtHistory14Repository kanrenshaKigyouDtHistory14Repository;

    /** 関連者企業・団体履歴Repository(15) */
    @Autowired
    private KanrenshaKigyouDtHistory15Repository kanrenshaKigyouDtHistory15Repository;

    /** 関連者企業・団体履歴Repository(16) */
    @Autowired
    private KanrenshaKigyouDtHistory16Repository kanrenshaKigyouDtHistory16Repository;

    /** 関連者企業・団体履歴Repository(17) */
    @Autowired
    private KanrenshaKigyouDtHistory17Repository kanrenshaKigyouDtHistory17Repository;

    /** 関連者企業・団体履歴Repository(18) */
    @Autowired
    private KanrenshaKigyouDtHistory18Repository kanrenshaKigyouDtHistory18Repository;

    /** 関連者企業・団体履歴Repository(19) */
    @Autowired
    private KanrenshaKigyouDtHistory19Repository kanrenshaKigyouDtHistory19Repository;

    /** 関連者企業・団体履歴Repository(20) */
    @Autowired
    private KanrenshaKigyouDtHistory20Repository kanrenshaKigyouDtHistory20Repository;

    /** 関連者企業・団体履歴Repository(21) */
    @Autowired
    private KanrenshaKigyouDtHistory21Repository kanrenshaKigyouDtHistory21Repository;

    /** 関連者企業・団体履歴Repository(22) */
    @Autowired
    private KanrenshaKigyouDtHistory22Repository kanrenshaKigyouDtHistory22Repository;

    /** 関連者企業・団体履歴Repository(23) */
    @Autowired
    private KanrenshaKigyouDtHistory23Repository kanrenshaKigyouDtHistory23Repository;

    /** 関連者企業・団体履歴Repository(24) */
    @Autowired
    private KanrenshaKigyouDtHistory24Repository kanrenshaKigyouDtHistory24Repository;

    /** 関連者企業・団体履歴Repository(25) */
    @Autowired
    private KanrenshaKigyouDtHistory25Repository kanrenshaKigyouDtHistory25Repository;

    /** 関連者企業・団体履歴Repository(26) */
    @Autowired
    private KanrenshaKigyouDtHistory26Repository kanrenshaKigyouDtHistory26Repository;

    /** 関連者企業・団体履歴Repository(27) */
    @Autowired
    private KanrenshaKigyouDtHistory27Repository kanrenshaKigyouDtHistory27Repository;

    /** 関連者企業・団体履歴Repository(28) */
    @Autowired
    private KanrenshaKigyouDtHistory28Repository kanrenshaKigyouDtHistory28Repository;

    /** 関連者企業・団体履歴Repository(29) */
    @Autowired
    private KanrenshaKigyouDtHistory29Repository kanrenshaKigyouDtHistory29Repository;

    /** 関連者企業・団体履歴Repository(30) */
    @Autowired
    private KanrenshaKigyouDtHistory30Repository kanrenshaKigyouDtHistory30Repository;

    /** 関連者企業・団体履歴Repository(31) */
    @Autowired
    private KanrenshaKigyouDtHistory31Repository kanrenshaKigyouDtHistory31Repository;

    /** 関連者企業・団体履歴Repository(32) */
    @Autowired
    private KanrenshaKigyouDtHistory32Repository kanrenshaKigyouDtHistory32Repository;

    /** 関連者企業・団体履歴Repository(33) */
    @Autowired
    private KanrenshaKigyouDtHistory33Repository kanrenshaKigyouDtHistory33Repository;

    /** 関連者企業・団体履歴Repository(34) */
    @Autowired
    private KanrenshaKigyouDtHistory34Repository kanrenshaKigyouDtHistory34Repository;

    /** 関連者企業・団体履歴Repository(35) */
    @Autowired
    private KanrenshaKigyouDtHistory35Repository kanrenshaKigyouDtHistory35Repository;

    /** 関連者企業・団体履歴Repository(36) */
    @Autowired
    private KanrenshaKigyouDtHistory36Repository kanrenshaKigyouDtHistory36Repository;

    /** 関連者企業・団体履歴Repository(37) */
    @Autowired
    private KanrenshaKigyouDtHistory37Repository kanrenshaKigyouDtHistory37Repository;

    /** 関連者企業・団体履歴Repository(38) */
    @Autowired
    private KanrenshaKigyouDtHistory38Repository kanrenshaKigyouDtHistory38Repository;

    /** 関連者企業・団体履歴Repository(39) */
    @Autowired
    private KanrenshaKigyouDtHistory39Repository kanrenshaKigyouDtHistory39Repository;

    /** 関連者企業・団体履歴Repository(40) */
    @Autowired
    private KanrenshaKigyouDtHistory40Repository kanrenshaKigyouDtHistory40Repository;

    /** 関連者企業・団体履歴Repository(41) */
    @Autowired
    private KanrenshaKigyouDtHistory41Repository kanrenshaKigyouDtHistory41Repository;

    /** 関連者企業・団体履歴Repository(42) */
    @Autowired
    private KanrenshaKigyouDtHistory42Repository kanrenshaKigyouDtHistory42Repository;

    /** 関連者企業・団体履歴Repository(43) */
    @Autowired
    private KanrenshaKigyouDtHistory43Repository kanrenshaKigyouDtHistory43Repository;

    /** 関連者企業・団体履歴Repository(44) */
    @Autowired
    private KanrenshaKigyouDtHistory44Repository kanrenshaKigyouDtHistory44Repository;

    /** 関連者企業・団体履歴Repository(45) */
    @Autowired
    private KanrenshaKigyouDtHistory45Repository kanrenshaKigyouDtHistory45Repository;

    /** 関連者企業・団体履歴Repository(46) */
    @Autowired
    private KanrenshaKigyouDtHistory46Repository kanrenshaKigyouDtHistory46Repository;

    /** 関連者企業・団体履歴Repository(47) */
    @Autowired
    private KanrenshaKigyouDtHistory47Repository kanrenshaKigyouDtHistory47Repository;

    /** 関連者企業・団体履歴Repository(99) */
    @Autowired
    private KanrenshaKigyouDtHistory99Repository kanrenshaKigyouDtHistory99Repository;

    /** 空白文字 */
    private static final String BLANK = "";

    /**
     * 処理を行う
     * 
     * @param capsuleDto 件数確認条件Dto
     * @return 検索結果表示文字列
     */
    public String practice(final SearchHistoryKanrenshaCapsuleDto capsuleDto) {

        // String searchWords =
        // createSerachWordsBooleanModeUtil.practice(capsuleDto.getSearchNaturalWords());
        // TODO MATCH AGAINST
        String searchWords = "%" + capsuleDto.getSearchNaturalWords() + "%";

        StringBuilder builder = new StringBuilder();

        // 01
        builder.append(this.createText(kanrenshaKigyouDtHistory01Repository.countFullText(searchWords),
                GetPrefectureLgCodeService.PREF_01_LABEL)) //
                // 02
                .append(this.createText(kanrenshaKigyouDtHistory02Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_02_LABEL)) //
                // 03
                .append(this.createText(kanrenshaKigyouDtHistory03Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_03_LABEL)) //
                // 04
                .append(this.createText(kanrenshaKigyouDtHistory04Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_04_LABEL)) //
                // 05
                .append(this.createText(kanrenshaKigyouDtHistory05Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_05_LABEL)) //
                // 06
                .append(this.createText(kanrenshaKigyouDtHistory06Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_06_LABEL)) //
                // 07
                .append(this.createText(kanrenshaKigyouDtHistory07Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_07_LABEL)) //
                // 08
                .append(this.createText(kanrenshaKigyouDtHistory08Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_08_LABEL)) //
                // 09
                .append(this.createText(kanrenshaKigyouDtHistory09Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_09_LABEL)) //
                // 10
                .append(this.createText(kanrenshaKigyouDtHistory10Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_10_LABEL)) //
                // 11
                .append(this.createText(kanrenshaKigyouDtHistory11Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_11_LABEL)) //
                // 12
                .append(this.createText(kanrenshaKigyouDtHistory12Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_12_LABEL)) //
                // 13
                .append(this.createText(kanrenshaKigyouDtHistory13Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_13_LABEL)) //
                // 14
                .append(this.createText(kanrenshaKigyouDtHistory14Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_14_LABEL)) //
                // 15
                .append(this.createText(kanrenshaKigyouDtHistory15Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_15_LABEL)) //
                // 16
                .append(this.createText(kanrenshaKigyouDtHistory16Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_16_LABEL)) //
                // 17
                .append(this.createText(kanrenshaKigyouDtHistory17Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_17_LABEL)) //
                // 18
                .append(this.createText(kanrenshaKigyouDtHistory18Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_18_LABEL)) //
                // 19
                .append(this.createText(kanrenshaKigyouDtHistory19Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_19_LABEL)) //
                // 20
                .append(this.createText(kanrenshaKigyouDtHistory20Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_20_LABEL)) //
                // 21
                .append(this.createText(kanrenshaKigyouDtHistory21Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_21_LABEL)) //
                // 22
                .append(this.createText(kanrenshaKigyouDtHistory22Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_22_LABEL)) //
                // 23
                .append(this.createText(kanrenshaKigyouDtHistory23Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_23_LABEL)) //
                // 24
                .append(this.createText(kanrenshaKigyouDtHistory24Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_24_LABEL)) //
                // 25
                .append(this.createText(kanrenshaKigyouDtHistory25Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_25_LABEL)) //
                // 26
                .append(this.createText(kanrenshaKigyouDtHistory26Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_26_LABEL)) //
                // 27
                .append(this.createText(kanrenshaKigyouDtHistory27Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_27_LABEL)) //
                // 28
                .append(this.createText(kanrenshaKigyouDtHistory28Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_28_LABEL)) //
                // 29
                .append(this.createText(kanrenshaKigyouDtHistory29Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_29_LABEL)) //
                // 30
                .append(this.createText(kanrenshaKigyouDtHistory30Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_30_LABEL)) //
                // 31
                .append(this.createText(kanrenshaKigyouDtHistory31Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_31_LABEL)) //
                // 32
                .append(this.createText(kanrenshaKigyouDtHistory32Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_32_LABEL)) //
                // 33
                .append(this.createText(kanrenshaKigyouDtHistory33Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_33_LABEL)) //
                // 34
                .append(this.createText(kanrenshaKigyouDtHistory34Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_34_LABEL)) //
                // 35
                .append(this.createText(kanrenshaKigyouDtHistory35Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_35_LABEL)) //
                // 36
                .append(this.createText(kanrenshaKigyouDtHistory36Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_36_LABEL)) //
                // 37
                .append(this.createText(kanrenshaKigyouDtHistory37Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_37_LABEL)) //
                // 38
                .append(this.createText(kanrenshaKigyouDtHistory38Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_38_LABEL)) //
                // 39
                .append(this.createText(kanrenshaKigyouDtHistory39Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_39_LABEL)) //
                // 40
                .append(this.createText(kanrenshaKigyouDtHistory40Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_40_LABEL)) //
                // 41
                .append(this.createText(kanrenshaKigyouDtHistory41Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_41_LABEL)) //
                // 42
                .append(this.createText(kanrenshaKigyouDtHistory42Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_42_LABEL)) //
                // 43
                .append(this.createText(kanrenshaKigyouDtHistory43Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_43_LABEL)) //
                // 44
                .append(this.createText(kanrenshaKigyouDtHistory44Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_44_LABEL)) //
                // 45
                .append(this.createText(kanrenshaKigyouDtHistory45Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_45_LABEL)) //
                // 46
                .append(this.createText(kanrenshaKigyouDtHistory46Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_46_LABEL)) //
                // 47
                .append(this.createText(kanrenshaKigyouDtHistory47Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_47_LABEL)) //
                // 99
                .append(this.createText(kanrenshaKigyouDtHistory99Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_99_LABEL));

        return builder.toString();
    }

    private String createText(final int count, final String pref) {

        if (0 == count) {
            return BLANK;
        } else {
            return pref + "：" + count + "件;";
        }
    }

}
