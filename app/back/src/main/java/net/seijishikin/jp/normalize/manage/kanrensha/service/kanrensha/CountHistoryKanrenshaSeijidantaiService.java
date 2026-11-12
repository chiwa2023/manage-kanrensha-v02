package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import org.springframework.beans.factory.annotation.Autowired; // NOPMD HightImports
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.SearchHistoryKanrenshaCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateSerachWordsBooleanModeUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory02Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory03Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory04Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory05Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory06Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory07Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory08Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory09Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory10Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory11Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory12Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory13Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory14Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory15Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory16Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory17Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory18Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory19Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory20Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory21Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory22Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory23Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory24Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory25Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory26Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory27Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory28Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory29Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory30Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory31Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory32Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory33Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory34Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory35Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory36Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory37Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory38Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory39Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory40Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory41Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory42Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory43Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory44Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory45Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory46Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory47Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory99Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory01Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.GetPrefectureLgCodeService;

/**
 * 関連者政治団体履歴件数確認(全県)Service
 */
@Service
public class CountHistoryKanrenshaSeijidantaiService { // NOPMD CouplingObjects

    /** 関連者政治団体履歴Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory01Repository kanrenshaSeijidantaiHistory01Repository;

    /** 関連者政治団体履歴Repository(02) */
    @Autowired
    private KanrenshaSeijidantaiHistory02Repository kanrenshaSeijidantaiHistory02Repository;

    /** 関連者政治団体履歴Repository(03) */
    @Autowired
    private KanrenshaSeijidantaiHistory03Repository kanrenshaSeijidantaiHistory03Repository;

    /** 関連者政治団体履歴Repository(04) */
    @Autowired
    private KanrenshaSeijidantaiHistory04Repository kanrenshaSeijidantaiHistory04Repository;

    /** 関連者政治団体履歴Repository(05) */
    @Autowired
    private KanrenshaSeijidantaiHistory05Repository kanrenshaSeijidantaiHistory05Repository;

    /** 関連者政治団体履歴Repository(06) */
    @Autowired
    private KanrenshaSeijidantaiHistory06Repository kanrenshaSeijidantaiHistory06Repository;

    /** 関連者政治団体履歴Repository(07) */
    @Autowired
    private KanrenshaSeijidantaiHistory07Repository kanrenshaSeijidantaiHistory07Repository;

    /** 関連者政治団体履歴Repository(08) */
    @Autowired
    private KanrenshaSeijidantaiHistory08Repository kanrenshaSeijidantaiHistory08Repository;

    /** 関連者政治団体履歴Repository(09) */
    @Autowired
    private KanrenshaSeijidantaiHistory09Repository kanrenshaSeijidantaiHistory09Repository;

    /** 関連者政治団体履歴Repository(10) */
    @Autowired
    private KanrenshaSeijidantaiHistory10Repository kanrenshaSeijidantaiHistory10Repository;

    /** 関連者政治団体履歴Repository(11) */
    @Autowired
    private KanrenshaSeijidantaiHistory11Repository kanrenshaSeijidantaiHistory11Repository;

    /** 関連者政治団体履歴Repository(12) */
    @Autowired
    private KanrenshaSeijidantaiHistory12Repository kanrenshaSeijidantaiHistory12Repository;

    /** 関連者政治団体履歴Repository(13) */
    @Autowired
    private KanrenshaSeijidantaiHistory13Repository kanrenshaSeijidantaiHistory13Repository;

    /** 関連者政治団体履歴Repository(14) */
    @Autowired
    private KanrenshaSeijidantaiHistory14Repository kanrenshaSeijidantaiHistory14Repository;

    /** 関連者政治団体履歴Repository(15) */
    @Autowired
    private KanrenshaSeijidantaiHistory15Repository kanrenshaSeijidantaiHistory15Repository;

    /** 関連者政治団体履歴Repository(16) */
    @Autowired
    private KanrenshaSeijidantaiHistory16Repository kanrenshaSeijidantaiHistory16Repository;

    /** 関連者政治団体履歴Repository(17) */
    @Autowired
    private KanrenshaSeijidantaiHistory17Repository kanrenshaSeijidantaiHistory17Repository;

    /** 関連者政治団体履歴Repository(18) */
    @Autowired
    private KanrenshaSeijidantaiHistory18Repository kanrenshaSeijidantaiHistory18Repository;

    /** 関連者政治団体履歴Repository(19) */
    @Autowired
    private KanrenshaSeijidantaiHistory19Repository kanrenshaSeijidantaiHistory19Repository;

    /** 関連者政治団体履歴Repository(20) */
    @Autowired
    private KanrenshaSeijidantaiHistory20Repository kanrenshaSeijidantaiHistory20Repository;

    /** 関連者政治団体履歴Repository(21) */
    @Autowired
    private KanrenshaSeijidantaiHistory21Repository kanrenshaSeijidantaiHistory21Repository;

    /** 関連者政治団体履歴Repository(22) */
    @Autowired
    private KanrenshaSeijidantaiHistory22Repository kanrenshaSeijidantaiHistory22Repository;

    /** 関連者政治団体履歴Repository(23) */
    @Autowired
    private KanrenshaSeijidantaiHistory23Repository kanrenshaSeijidantaiHistory23Repository;

    /** 関連者政治団体履歴Repository(24) */
    @Autowired
    private KanrenshaSeijidantaiHistory24Repository kanrenshaSeijidantaiHistory24Repository;

    /** 関連者政治団体履歴Repository(25) */
    @Autowired
    private KanrenshaSeijidantaiHistory25Repository kanrenshaSeijidantaiHistory25Repository;

    /** 関連者政治団体履歴Repository(26) */
    @Autowired
    private KanrenshaSeijidantaiHistory26Repository kanrenshaSeijidantaiHistory26Repository;

    /** 関連者政治団体履歴Repository(27) */
    @Autowired
    private KanrenshaSeijidantaiHistory27Repository kanrenshaSeijidantaiHistory27Repository;

    /** 関連者政治団体履歴Repository(28) */
    @Autowired
    private KanrenshaSeijidantaiHistory28Repository kanrenshaSeijidantaiHistory28Repository;

    /** 関連者政治団体履歴Repository(29) */
    @Autowired
    private KanrenshaSeijidantaiHistory29Repository kanrenshaSeijidantaiHistory29Repository;

    /** 関連者政治団体履歴Repository(30) */
    @Autowired
    private KanrenshaSeijidantaiHistory30Repository kanrenshaSeijidantaiHistory30Repository;

    /** 関連者政治団体履歴Repository(31) */
    @Autowired
    private KanrenshaSeijidantaiHistory31Repository kanrenshaSeijidantaiHistory31Repository;

    /** 関連者政治団体履歴Repository(32) */
    @Autowired
    private KanrenshaSeijidantaiHistory32Repository kanrenshaSeijidantaiHistory32Repository;

    /** 関連者政治団体履歴Repository(33) */
    @Autowired
    private KanrenshaSeijidantaiHistory33Repository kanrenshaSeijidantaiHistory33Repository;

    /** 関連者政治団体履歴Repository(34) */
    @Autowired
    private KanrenshaSeijidantaiHistory34Repository kanrenshaSeijidantaiHistory34Repository;

    /** 関連者政治団体履歴Repository(35) */
    @Autowired
    private KanrenshaSeijidantaiHistory35Repository kanrenshaSeijidantaiHistory35Repository;

    /** 関連者政治団体履歴Repository(36) */
    @Autowired
    private KanrenshaSeijidantaiHistory36Repository kanrenshaSeijidantaiHistory36Repository;

    /** 関連者政治団体履歴Repository(37) */
    @Autowired
    private KanrenshaSeijidantaiHistory37Repository kanrenshaSeijidantaiHistory37Repository;

    /** 関連者政治団体履歴Repository(38) */
    @Autowired
    private KanrenshaSeijidantaiHistory38Repository kanrenshaSeijidantaiHistory38Repository;

    /** 関連者政治団体履歴Repository(39) */
    @Autowired
    private KanrenshaSeijidantaiHistory39Repository kanrenshaSeijidantaiHistory39Repository;

    /** 関連者政治団体履歴Repository(40) */
    @Autowired
    private KanrenshaSeijidantaiHistory40Repository kanrenshaSeijidantaiHistory40Repository;

    /** 関連者政治団体履歴Repository(41) */
    @Autowired
    private KanrenshaSeijidantaiHistory41Repository kanrenshaSeijidantaiHistory41Repository;

    /** 関連者政治団体履歴Repository(42) */
    @Autowired
    private KanrenshaSeijidantaiHistory42Repository kanrenshaSeijidantaiHistory42Repository;

    /** 関連者政治団体履歴Repository(43) */
    @Autowired
    private KanrenshaSeijidantaiHistory43Repository kanrenshaSeijidantaiHistory43Repository;

    /** 関連者政治団体履歴Repository(44) */
    @Autowired
    private KanrenshaSeijidantaiHistory44Repository kanrenshaSeijidantaiHistory44Repository;

    /** 関連者政治団体履歴Repository(45) */
    @Autowired
    private KanrenshaSeijidantaiHistory45Repository kanrenshaSeijidantaiHistory45Repository;

    /** 関連者政治団体履歴Repository(46) */
    @Autowired
    private KanrenshaSeijidantaiHistory46Repository kanrenshaSeijidantaiHistory46Repository;

    /** 関連者政治団体履歴Repository(47) */
    @Autowired
    private KanrenshaSeijidantaiHistory47Repository kanrenshaSeijidantaiHistory47Repository;

    /** 関連者政治団体履歴Repository(99) */
    @Autowired
    private KanrenshaSeijidantaiHistory99Repository kanrenshaSeijidantaiHistory99Repository;

    /** 空白文字 */
    private static final String BLANK = "";

    /** 全文検索フォーマットUtil */
    @Autowired
    private CreateSerachWordsBooleanModeUtil createSerachWordsBooleanModeUtil;

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
        builder.append(this.createText(kanrenshaSeijidantaiHistory01Repository.countFullText(searchWords),
                GetPrefectureLgCodeService.PREF_01_LABEL)) //
                // 02
                .append(this.createText(kanrenshaSeijidantaiHistory02Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_02_LABEL)) //
                // 03
                .append(this.createText(kanrenshaSeijidantaiHistory03Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_03_LABEL)) //
                // 04
                .append(this.createText(kanrenshaSeijidantaiHistory04Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_04_LABEL)) //
                // 05
                .append(this.createText(kanrenshaSeijidantaiHistory05Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_05_LABEL)) //
                // 06
                .append(this.createText(kanrenshaSeijidantaiHistory06Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_06_LABEL)) //
                // 07
                .append(this.createText(kanrenshaSeijidantaiHistory07Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_07_LABEL)) //
                // 08
                .append(this.createText(kanrenshaSeijidantaiHistory08Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_08_LABEL)) //
                // 09
                .append(this.createText(kanrenshaSeijidantaiHistory09Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_09_LABEL)) //
                // 10
                .append(this.createText(kanrenshaSeijidantaiHistory10Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_10_LABEL)) //
                // 11
                .append(this.createText(kanrenshaSeijidantaiHistory11Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_11_LABEL)) //
                // 12
                .append(this.createText(kanrenshaSeijidantaiHistory12Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_12_LABEL)) //
                // 13
                .append(this.createText(kanrenshaSeijidantaiHistory13Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_13_LABEL)) //
                // 14
                .append(this.createText(kanrenshaSeijidantaiHistory14Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_14_LABEL)) //
                // 15
                .append(this.createText(kanrenshaSeijidantaiHistory15Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_15_LABEL)) //
                // 16
                .append(this.createText(kanrenshaSeijidantaiHistory16Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_16_LABEL)) //
                // 17
                .append(this.createText(kanrenshaSeijidantaiHistory17Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_17_LABEL)) //
                // 18
                .append(this.createText(kanrenshaSeijidantaiHistory18Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_18_LABEL)) //
                // 19
                .append(this.createText(kanrenshaSeijidantaiHistory19Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_19_LABEL)) //
                // 20
                .append(this.createText(kanrenshaSeijidantaiHistory20Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_20_LABEL)) //
                // 21
                .append(this.createText(kanrenshaSeijidantaiHistory21Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_21_LABEL)) //
                // 22
                .append(this.createText(kanrenshaSeijidantaiHistory22Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_22_LABEL)) //
                // 23
                .append(this.createText(kanrenshaSeijidantaiHistory23Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_23_LABEL)) //
                // 24
                .append(this.createText(kanrenshaSeijidantaiHistory24Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_24_LABEL)) //
                // 25
                .append(this.createText(kanrenshaSeijidantaiHistory25Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_25_LABEL)) //
                // 26
                .append(this.createText(kanrenshaSeijidantaiHistory26Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_26_LABEL)) //
                // 27
                .append(this.createText(kanrenshaSeijidantaiHistory27Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_27_LABEL)) //
                // 28
                .append(this.createText(kanrenshaSeijidantaiHistory28Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_28_LABEL)) //
                // 29
                .append(this.createText(kanrenshaSeijidantaiHistory29Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_29_LABEL)) //
                // 30
                .append(this.createText(kanrenshaSeijidantaiHistory30Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_30_LABEL)) //
                // 31
                .append(this.createText(kanrenshaSeijidantaiHistory31Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_31_LABEL)) //
                // 32
                .append(this.createText(kanrenshaSeijidantaiHistory32Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_32_LABEL)) //
                // 33
                .append(this.createText(kanrenshaSeijidantaiHistory33Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_33_LABEL)) //
                // 34
                .append(this.createText(kanrenshaSeijidantaiHistory34Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_34_LABEL)) //
                // 35
                .append(this.createText(kanrenshaSeijidantaiHistory35Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_35_LABEL)) //
                // 36
                .append(this.createText(kanrenshaSeijidantaiHistory36Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_36_LABEL)) //
                // 37
                .append(this.createText(kanrenshaSeijidantaiHistory37Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_37_LABEL)) //
                // 38
                .append(this.createText(kanrenshaSeijidantaiHistory38Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_38_LABEL)) //
                // 39
                .append(this.createText(kanrenshaSeijidantaiHistory39Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_39_LABEL)) //
                // 40
                .append(this.createText(kanrenshaSeijidantaiHistory40Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_40_LABEL)) //
                // 41
                .append(this.createText(kanrenshaSeijidantaiHistory41Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_41_LABEL)) //
                // 42
                .append(this.createText(kanrenshaSeijidantaiHistory42Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_42_LABEL)) //
                // 43
                .append(this.createText(kanrenshaSeijidantaiHistory43Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_43_LABEL)) //
                // 44
                .append(this.createText(kanrenshaSeijidantaiHistory44Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_44_LABEL)) //
                // 45
                .append(this.createText(kanrenshaSeijidantaiHistory45Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_45_LABEL)) //
                // 46
                .append(this.createText(kanrenshaSeijidantaiHistory46Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_46_LABEL)) //
                // 47
                .append(this.createText(kanrenshaSeijidantaiHistory47Repository.countFullText(searchWords),
                        GetPrefectureLgCodeService.PREF_47_LABEL)) //
                // 99
                .append(this.createText(kanrenshaSeijidantaiHistory99Repository.countFullText(searchWords),
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
