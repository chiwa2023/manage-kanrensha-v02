package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha; // NOPMD

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.GetPrefectureLgCodeService;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory01Repository;

/**
 * 関連者個人の同属性リスト取得Service
 */
@Service
public class GetKanrenshaPersonSameHistoryService {

    /** 住所から県 地方公共団体コード(2桁)取得Service */
    @Autowired
    private GetPrefectureLgCodeService getPrefectureLgCodeService;

    /** 関連者企業・団体履歴Repository(01) */
    @Autowired
    private KanrenshaPersonHistory01Repository kanrenshaPersonHistory01Repository;
    /** 関連者企業・団体履歴Repository(02) */
    @Autowired
    private KanrenshaPersonHistory02Repository kanrenshaPersonHistory02Repository;
    /** 関連者企業・団体履歴Repository(03) */
    @Autowired
    private KanrenshaPersonHistory03Repository kanrenshaPersonHistory03Repository;
    /** 関連者企業・団体履歴Repository(04) */
    @Autowired
    private KanrenshaPersonHistory04Repository kanrenshaPersonHistory04Repository;
    /** 関連者企業・団体履歴Repository(05) */
    @Autowired
    private KanrenshaPersonHistory05Repository kanrenshaPersonHistory05Repository;
    /** 関連者企業・団体履歴Repository(06) */
    @Autowired
    private KanrenshaPersonHistory06Repository kanrenshaPersonHistory06Repository;
    /** 関連者企業・団体履歴Repository(07) */
    @Autowired
    private KanrenshaPersonHistory07Repository kanrenshaPersonHistory07Repository;
    /** 関連者企業・団体履歴Repository(08) */
    @Autowired
    private KanrenshaPersonHistory08Repository kanrenshaPersonHistory08Repository;
    /** 関連者企業・団体履歴Repository(09) */
    @Autowired
    private KanrenshaPersonHistory09Repository kanrenshaPersonHistory09Repository;
    /** 関連者企業・団体履歴Repository(10) */
    @Autowired
    private KanrenshaPersonHistory10Repository kanrenshaPersonHistory10Repository;
    /** 関連者企業・団体履歴Repository(12) */
    @Autowired
    private KanrenshaPersonHistory11Repository kanrenshaPersonHistory11Repository;
    /** 関連者企業・団体履歴Repository(13) */
    @Autowired
    private KanrenshaPersonHistory12Repository kanrenshaPersonHistory12Repository;
    /** 関連者企業・団体履歴Repository(14) */
    @Autowired
    private KanrenshaPersonHistory13Repository kanrenshaPersonHistory13Repository;
    /** 関連者企業・団体履歴Repository(15) */
    @Autowired
    private KanrenshaPersonHistory14Repository kanrenshaPersonHistory14Repository;
    /** 関連者企業・団体履歴Repository(15) */
    @Autowired
    private KanrenshaPersonHistory15Repository kanrenshaPersonHistory15Repository;
    /** 関連者企業・団体履歴Repository(16) */
    @Autowired
    private KanrenshaPersonHistory16Repository kanrenshaPersonHistory16Repository;
    /** 関連者企業・団体履歴Repository(17) */
    @Autowired
    private KanrenshaPersonHistory17Repository kanrenshaPersonHistory17Repository;
    /** 関連者企業・団体履歴Repository(18) */
    @Autowired
    private KanrenshaPersonHistory18Repository kanrenshaPersonHistory18Repository;
    /** 関連者企業・団体履歴Repository(19) */
    @Autowired
    private KanrenshaPersonHistory19Repository kanrenshaPersonHistory19Repository;
    /** 関連者企業・団体履歴Repository(20) */
    @Autowired
    private KanrenshaPersonHistory20Repository kanrenshaPersonHistory20Repository;
    /** 関連者企業・団体履歴Repository(21) */
    @Autowired
    private KanrenshaPersonHistory21Repository kanrenshaPersonHistory21Repository;
    /** 関連者企業・団体履歴Repository(22) */
    @Autowired
    private KanrenshaPersonHistory22Repository kanrenshaPersonHistory22Repository;
    /** 関連者企業・団体履歴Repository(23) */
    @Autowired
    private KanrenshaPersonHistory23Repository kanrenshaPersonHistory23Repository;
    /** 関連者企業・団体履歴Repository(24) */
    @Autowired
    private KanrenshaPersonHistory24Repository kanrenshaPersonHistory24Repository;
    /** 関連者企業・団体履歴Repository(25) */
    @Autowired
    private KanrenshaPersonHistory25Repository kanrenshaPersonHistory25Repository;
    /** 関連者企業・団体履歴Repository(26) */
    @Autowired
    private KanrenshaPersonHistory26Repository kanrenshaPersonHistory26Repository;
    /** 関連者企業・団体履歴Repository(27) */
    @Autowired
    private KanrenshaPersonHistory27Repository kanrenshaPersonHistory27Repository;
    /** 関連者企業・団体履歴Repository(28) */
    @Autowired
    private KanrenshaPersonHistory28Repository kanrenshaPersonHistory28Repository;
    /** 関連者企業・団体履歴Repository(29) */
    @Autowired
    private KanrenshaPersonHistory29Repository kanrenshaPersonHistory29Repository;
    /** 関連者企業・団体履歴Repository(30) */
    @Autowired
    private KanrenshaPersonHistory30Repository kanrenshaPersonHistory30Repository;
    /** 関連者企業・団体履歴Repository(31) */
    @Autowired
    private KanrenshaPersonHistory31Repository kanrenshaPersonHistory31Repository;
    /** 関連者企業・団体履歴Repository(32) */
    @Autowired
    private KanrenshaPersonHistory32Repository kanrenshaPersonHistory32Repository;
    /** 関連者企業・団体履歴Repository(33) */
    @Autowired
    private KanrenshaPersonHistory33Repository kanrenshaPersonHistory33Repository;
    /** 関連者企業・団体履歴Repository(34) */
    @Autowired
    private KanrenshaPersonHistory34Repository kanrenshaPersonHistory34Repository;
    /** 関連者企業・団体履歴Repository(35) */
    @Autowired
    private KanrenshaPersonHistory35Repository kanrenshaPersonHistory35Repository;
    /** 関連者企業・団体履歴Repository(36) */
    @Autowired
    private KanrenshaPersonHistory36Repository kanrenshaPersonHistory36Repository;
    /** 関連者企業・団体履歴Repository(37) */
    @Autowired
    private KanrenshaPersonHistory37Repository kanrenshaPersonHistory37Repository;
    /** 関連者企業・団体履歴Repository(38) */
    @Autowired
    private KanrenshaPersonHistory38Repository kanrenshaPersonHistory38Repository;
    /** 関連者企業・団体履歴Repository(39) */
    @Autowired
    private KanrenshaPersonHistory39Repository kanrenshaPersonHistory39Repository;
    /** 関連者企業・団体履歴Repository(40) */
    @Autowired
    private KanrenshaPersonHistory40Repository kanrenshaPersonHistory40Repository;
    /** 関連者企業・団体履歴Repository(41) */
    @Autowired
    private KanrenshaPersonHistory41Repository kanrenshaPersonHistory41Repository;
    /** 関連者企業・団体履歴Repository(42) */
    @Autowired
    private KanrenshaPersonHistory42Repository kanrenshaPersonHistory42Repository;
    /** 関連者企業・団体履歴Repository(43) */
    @Autowired
    private KanrenshaPersonHistory43Repository kanrenshaPersonHistory43Repository;
    /** 関連者企業・団体履歴Repository(44) */
    @Autowired
    private KanrenshaPersonHistory44Repository kanrenshaPersonHistory44Repository;
    /** 関連者企業・団体履歴Repository(45) */
    @Autowired
    private KanrenshaPersonHistory45Repository kanrenshaPersonHistory45Repository;
    /** 関連者企業・団体履歴Repository(46) */
    @Autowired
    private KanrenshaPersonHistory46Repository kanrenshaPersonHistory46Repository;
    /** 関連者企業・団体履歴Repository(47) */
    @Autowired
    private KanrenshaPersonHistory47Repository kanrenshaPersonHistory47Repository;
    /** 関連者企業・団体履歴Repository(99) */
    @Autowired
    private KanrenshaPersonHistory99Repository kanrenshaPersonHistory99Repository;

    /**
     * 処理を行う
     *
     * @param name      個人名
     * @param address   住所
     * @param shokugyou 個人職業
     * @return 検索結果
     */
    public List<KanrenshaPersonHistoryBaseEntity> practice( // SUPPRESS CHECKSTYLE ReturnCount NOPMD
            final String name, final String address, final String shokugyou) {

        switch (getPrefectureLgCodeService.practice(address)) {
            case GetPrefectureLgCodeService.PREF_01:
                return kanrenshaPersonHistory01Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_02:
                return kanrenshaPersonHistory02Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_03:
                return kanrenshaPersonHistory03Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_04:
                return kanrenshaPersonHistory04Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_05:
                return kanrenshaPersonHistory05Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_06:
                return kanrenshaPersonHistory06Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_07:
                return kanrenshaPersonHistory07Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_08:
                return kanrenshaPersonHistory08Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_09:
                return kanrenshaPersonHistory09Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_10:
                return kanrenshaPersonHistory10Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_11:
                return kanrenshaPersonHistory11Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_12:
                return kanrenshaPersonHistory12Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_13:
                return kanrenshaPersonHistory13Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_14:
                return kanrenshaPersonHistory14Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_15:
                return kanrenshaPersonHistory15Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_16:
                return kanrenshaPersonHistory16Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_17:
                return kanrenshaPersonHistory17Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_18:
                return kanrenshaPersonHistory18Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_19:
                return kanrenshaPersonHistory19Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_20:
                return kanrenshaPersonHistory20Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_21:
                return kanrenshaPersonHistory21Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_22:
                return kanrenshaPersonHistory22Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_23:
                return kanrenshaPersonHistory23Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_24:
                return kanrenshaPersonHistory24Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_25:
                return kanrenshaPersonHistory25Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_26:
                return kanrenshaPersonHistory26Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_27:
                return kanrenshaPersonHistory27Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_28:
                return kanrenshaPersonHistory28Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_29:
                return kanrenshaPersonHistory29Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_30:
                return kanrenshaPersonHistory30Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_31:
                return kanrenshaPersonHistory31Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_32:
                return kanrenshaPersonHistory32Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_33:
                return kanrenshaPersonHistory33Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_34:
                return kanrenshaPersonHistory34Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_35:
                return kanrenshaPersonHistory35Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_36:
                return kanrenshaPersonHistory36Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_37:
                return kanrenshaPersonHistory37Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_38:
                return kanrenshaPersonHistory38Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_39:
                return kanrenshaPersonHistory39Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_40:
                return kanrenshaPersonHistory40Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_41:
                return kanrenshaPersonHistory41Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_42:
                return kanrenshaPersonHistory42Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_43:
                return kanrenshaPersonHistory43Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_44:
                return kanrenshaPersonHistory44Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_45:
                return kanrenshaPersonHistory45Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_46:
                return kanrenshaPersonHistory46Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_47:
                return kanrenshaPersonHistory47Repository.selectByProperty(name, address, shokugyou);
            default:
                return kanrenshaPersonHistory99Repository.selectByProperty(name, address, shokugyou);
        }
    }

}
