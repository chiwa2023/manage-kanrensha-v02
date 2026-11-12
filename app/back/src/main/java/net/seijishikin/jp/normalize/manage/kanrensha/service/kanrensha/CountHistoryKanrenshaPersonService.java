package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import org.springframework.beans.factory.annotation.Autowired; // NOPMD HighImports
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.SearchHistoryKanrenshaCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateSerachWordsBooleanModeUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory02Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory03Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory04Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory05Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory06Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory07Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory08Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory09Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory10Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory11Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory12Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory13Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory14Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory15Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory16Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory17Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory18Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory19Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory20Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory21Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory22Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory23Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory24Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory25Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory26Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory27Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory28Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory29Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory30Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory31Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory32Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory33Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory34Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory35Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory36Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory37Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory38Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory39Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory40Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory41Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory42Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory43Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory44Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory45Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory46Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory47Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory99Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory01Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.GetPrefectureLgCodeService;

/**
 * 関連者個人履歴件数確認(全県)Service
 */
@Service
public class CountHistoryKanrenshaPersonService { // NOPMD CouplingObjects

    /** 関連者個人履歴Repository */
    @Autowired
    private KanrenshaPersonHistory01Repository kanrenshaPersonHistory01Repository;

    /** 関連者個人履歴Repository(02) */
    @Autowired
    private KanrenshaPersonHistory02Repository kanrenshaPersonHistory02Repository;

    /** 関連者個人履歴Repository(03) */
    @Autowired
    private KanrenshaPersonHistory03Repository kanrenshaPersonHistory03Repository;

    /** 関連者個人履歴Repository(04) */
    @Autowired
    private KanrenshaPersonHistory04Repository kanrenshaPersonHistory04Repository;

    /** 関連者個人履歴Repository(05) */
    @Autowired
    private KanrenshaPersonHistory05Repository kanrenshaPersonHistory05Repository;

    /** 関連者個人履歴Repository(06) */
    @Autowired
    private KanrenshaPersonHistory06Repository kanrenshaPersonHistory06Repository;

    /** 関連者個人履歴Repository(07) */
    @Autowired
    private KanrenshaPersonHistory07Repository kanrenshaPersonHistory07Repository;

    /** 関連者個人履歴Repository(08) */
    @Autowired
    private KanrenshaPersonHistory08Repository kanrenshaPersonHistory08Repository;

    /** 関連者個人履歴Repository(09) */
    @Autowired
    private KanrenshaPersonHistory09Repository kanrenshaPersonHistory09Repository;

    /** 関連者個人履歴Repository(10) */
    @Autowired
    private KanrenshaPersonHistory10Repository kanrenshaPersonHistory10Repository;

    /** 関連者個人履歴Repository(11) */
    @Autowired
    private KanrenshaPersonHistory11Repository kanrenshaPersonHistory11Repository;

    /** 関連者個人履歴Repository(12) */
    @Autowired
    private KanrenshaPersonHistory12Repository kanrenshaPersonHistory12Repository;

    /** 関連者個人履歴Repository(13) */
    @Autowired
    private KanrenshaPersonHistory13Repository kanrenshaPersonHistory13Repository;

    /** 関連者個人履歴Repository(14) */
    @Autowired
    private KanrenshaPersonHistory14Repository kanrenshaPersonHistory14Repository;

    /** 関連者個人履歴Repository(15) */
    @Autowired
    private KanrenshaPersonHistory15Repository kanrenshaPersonHistory15Repository;

    /** 関連者個人履歴Repository(16) */
    @Autowired
    private KanrenshaPersonHistory16Repository kanrenshaPersonHistory16Repository;

    /** 関連者個人履歴Repository(17) */
    @Autowired
    private KanrenshaPersonHistory17Repository kanrenshaPersonHistory17Repository;

    /** 関連者個人履歴Repository(18) */
    @Autowired
    private KanrenshaPersonHistory18Repository kanrenshaPersonHistory18Repository;

    /** 関連者個人履歴Repository(19) */
    @Autowired
    private KanrenshaPersonHistory19Repository kanrenshaPersonHistory19Repository;

    /** 関連者個人履歴Repository(20) */
    @Autowired
    private KanrenshaPersonHistory20Repository kanrenshaPersonHistory20Repository;

    /** 関連者個人履歴Repository(21) */
    @Autowired
    private KanrenshaPersonHistory21Repository kanrenshaPersonHistory21Repository;

    /** 関連者個人履歴Repository(22) */
    @Autowired
    private KanrenshaPersonHistory22Repository kanrenshaPersonHistory22Repository;

    /** 関連者個人履歴Repository(23) */
    @Autowired
    private KanrenshaPersonHistory23Repository kanrenshaPersonHistory23Repository;

    /** 関連者個人履歴Repository(24) */
    @Autowired
    private KanrenshaPersonHistory24Repository kanrenshaPersonHistory24Repository;

    /** 関連者個人履歴Repository(25) */
    @Autowired
    private KanrenshaPersonHistory25Repository kanrenshaPersonHistory25Repository;

    /** 関連者個人履歴Repository(26) */
    @Autowired
    private KanrenshaPersonHistory26Repository kanrenshaPersonHistory26Repository;

    /** 関連者個人履歴Repository(27) */
    @Autowired
    private KanrenshaPersonHistory27Repository kanrenshaPersonHistory27Repository;

    /** 関連者個人履歴Repository(28) */
    @Autowired
    private KanrenshaPersonHistory28Repository kanrenshaPersonHistory28Repository;

    /** 関連者個人履歴Repository(29) */
    @Autowired
    private KanrenshaPersonHistory29Repository kanrenshaPersonHistory29Repository;

    /** 関連者個人履歴Repository(30) */
    @Autowired
    private KanrenshaPersonHistory30Repository kanrenshaPersonHistory30Repository;

    /** 関連者個人履歴Repository(31) */
    @Autowired
    private KanrenshaPersonHistory31Repository kanrenshaPersonHistory31Repository;

    /** 関連者個人履歴Repository(32) */
    @Autowired
    private KanrenshaPersonHistory32Repository kanrenshaPersonHistory32Repository;

    /** 関連者個人履歴Repository(33) */
    @Autowired
    private KanrenshaPersonHistory33Repository kanrenshaPersonHistory33Repository;

    /** 関連者個人履歴Repository(34) */
    @Autowired
    private KanrenshaPersonHistory34Repository kanrenshaPersonHistory34Repository;

    /** 関連者個人履歴Repository(35) */
    @Autowired
    private KanrenshaPersonHistory35Repository kanrenshaPersonHistory35Repository;

    /** 関連者個人履歴Repository(36) */
    @Autowired
    private KanrenshaPersonHistory36Repository kanrenshaPersonHistory36Repository;

    /** 関連者個人履歴Repository(37) */
    @Autowired
    private KanrenshaPersonHistory37Repository kanrenshaPersonHistory37Repository;

    /** 関連者個人履歴Repository(38) */
    @Autowired
    private KanrenshaPersonHistory38Repository kanrenshaPersonHistory38Repository;

    /** 関連者個人履歴Repository(39) */
    @Autowired
    private KanrenshaPersonHistory39Repository kanrenshaPersonHistory39Repository;

    /** 関連者個人履歴Repository(40) */
    @Autowired
    private KanrenshaPersonHistory40Repository kanrenshaPersonHistory40Repository;

    /** 関連者個人履歴Repository(41) */
    @Autowired
    private KanrenshaPersonHistory41Repository kanrenshaPersonHistory41Repository;

    /** 関連者個人履歴Repository(42) */
    @Autowired
    private KanrenshaPersonHistory42Repository kanrenshaPersonHistory42Repository;

    /** 関連者個人履歴Repository(43) */
    @Autowired
    private KanrenshaPersonHistory43Repository kanrenshaPersonHistory43Repository;

    /** 関連者個人履歴Repository(44) */
    @Autowired
    private KanrenshaPersonHistory44Repository kanrenshaPersonHistory44Repository;

    /** 関連者個人履歴Repository(45) */
    @Autowired
    private KanrenshaPersonHistory45Repository kanrenshaPersonHistory45Repository;

    /** 関連者個人履歴Repository(46) */
    @Autowired
    private KanrenshaPersonHistory46Repository kanrenshaPersonHistory46Repository;

    /** 関連者個人履歴Repository(47) */
    @Autowired
    private KanrenshaPersonHistory47Repository kanrenshaPersonHistory47Repository;

    /** 関連者個人履歴Repository(99) */
    @Autowired
    private KanrenshaPersonHistory99Repository kanrenshaPersonHistory99Repository;

    /** 全文検索フォーマットUtil */
    @Autowired
    private CreateSerachWordsBooleanModeUtil createSerachWordsBooleanModeUtil;

    /** 空白文字 */
    private static final String BLANK = "";

    /**
     * 処理を行う
     * 
     * @param capsuleDto 件数確認条件Dto
     * @return 検索結果表示文字列
     */
    public String practice(final SearchHistoryKanrenshaCapsuleDto capsuleDto) {

        String searchWords = createSerachWordsBooleanModeUtil.practice(capsuleDto.getSearchNaturalWords());

        StringBuilder builder = new StringBuilder();

        // 01
        builder.append(this.createText(kanrenshaPersonHistory01Repository.countFullText(searchWords),
                GetPrefectureLgCodeService.PREF_01_LABEL)) //
                // 02
                .append(this.createText(kanrenshaPersonHistory02Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_02_LABEL)) //
                // 03
                .append(this.createText(kanrenshaPersonHistory03Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_03_LABEL)) //
                // 04
                .append(this.createText(kanrenshaPersonHistory04Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_04_LABEL)) //
                // 05
                .append(this.createText(kanrenshaPersonHistory05Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_05_LABEL)) //
                // 06
                .append(this.createText(kanrenshaPersonHistory06Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_06_LABEL)) //
                // 07
                .append(this.createText(kanrenshaPersonHistory07Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_07_LABEL)) //
                // 08
                .append(this.createText(kanrenshaPersonHistory08Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_08_LABEL)) //
                // 09
                .append(this.createText(kanrenshaPersonHistory09Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_09_LABEL)) //
                // 10
                .append(this.createText(kanrenshaPersonHistory10Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_10_LABEL)) //
                // 11
                .append(this.createText(kanrenshaPersonHistory11Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_11_LABEL)) //
                // 12
                .append(this.createText(kanrenshaPersonHistory12Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_12_LABEL)) //
                // 13
                .append(this.createText(kanrenshaPersonHistory13Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_13_LABEL)) //
                // 14
                .append(this.createText(kanrenshaPersonHistory14Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_14_LABEL)) //
                // 15
                .append(this.createText(kanrenshaPersonHistory15Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_15_LABEL)) //
                // 16
                .append(this.createText(kanrenshaPersonHistory16Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_16_LABEL)) //
                // 17
                .append(this.createText(kanrenshaPersonHistory17Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_17_LABEL)) //
                // 18
                .append(this.createText(kanrenshaPersonHistory18Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_18_LABEL)) //
                // 19
                .append(this.createText(kanrenshaPersonHistory19Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_19_LABEL)) //
                // 20
                .append(this.createText(kanrenshaPersonHistory20Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_20_LABEL)) //
                // 21
                .append(this.createText(kanrenshaPersonHistory21Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_21_LABEL)) //
                // 22
                .append(this.createText(kanrenshaPersonHistory22Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_22_LABEL)) //
                // 23
                .append(this.createText(kanrenshaPersonHistory23Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_23_LABEL)) //
                // 24
                .append(this.createText(kanrenshaPersonHistory24Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_24_LABEL)) //
                // 25
                .append(this.createText(kanrenshaPersonHistory25Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_25_LABEL)) //
                // 26
                .append(this.createText(kanrenshaPersonHistory26Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_26_LABEL)) //
                // 27
                .append(this.createText(kanrenshaPersonHistory27Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_27_LABEL)) //
                // 28
                .append(this.createText(kanrenshaPersonHistory28Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_28_LABEL)) //
                // 29
                .append(this.createText(kanrenshaPersonHistory29Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_29_LABEL)) //
                // 30
                .append(this.createText(kanrenshaPersonHistory30Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_30_LABEL)) //
                // 31
                .append(this.createText(kanrenshaPersonHistory31Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_31_LABEL)) //
                // 32
                .append(this.createText(kanrenshaPersonHistory32Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_32_LABEL)) //
                // 33
                .append(this.createText(kanrenshaPersonHistory33Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_33_LABEL)) //
                // 34
                .append(this.createText(kanrenshaPersonHistory34Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_34_LABEL)) //
                // 35
                .append(this.createText(kanrenshaPersonHistory35Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_35_LABEL)) //
                // 36
                .append(this.createText(kanrenshaPersonHistory36Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_36_LABEL)) //
                // 37
                .append(this.createText(kanrenshaPersonHistory37Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_37_LABEL)) //
                // 38
                .append(this.createText(kanrenshaPersonHistory38Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_38_LABEL)) //
                // 39
                .append(this.createText(kanrenshaPersonHistory39Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_39_LABEL)) //
                // 40
                .append(this.createText(kanrenshaPersonHistory40Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_40_LABEL)) //
                // 41
                .append(this.createText(kanrenshaPersonHistory41Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_41_LABEL)) //
                // 42
                .append(this.createText(kanrenshaPersonHistory42Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_42_LABEL)) //
                // 43
                .append(this.createText(kanrenshaPersonHistory43Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_43_LABEL)) //
                // 44
                .append(this.createText(kanrenshaPersonHistory44Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_44_LABEL)) //
                // 45
                .append(this.createText(kanrenshaPersonHistory45Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_45_LABEL)) //
                // 46
                .append(this.createText(kanrenshaPersonHistory46Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_46_LABEL)) //
                // 47
                .append(this.createText(kanrenshaPersonHistory47Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_47_LABEL)) //
                // 99
                .append(this.createText(kanrenshaPersonHistory99Repository.countFullText(searchWords),
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
