package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha; // NOPMD

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory01Repository;

/**
 * 関連者企業・団体の同属性リスト取得Service
 */
@Service
public class GetKanrenshaKigyouDtSameHistoryService {

    /** 住所から県 地方公共団体コード(2桁)取得Service */
    @Autowired
    private GetPrefectureLgCodeService getPrefectureLgCodeService;

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
    /** 関連者企業・団体履歴Repository(12) */
    @Autowired
    private KanrenshaKigyouDtHistory11Repository kanrenshaKigyouDtHistory11Repository;
    /** 関連者企業・団体履歴Repository(13) */
    @Autowired
    private KanrenshaKigyouDtHistory12Repository kanrenshaKigyouDtHistory12Repository;
    /** 関連者企業・団体履歴Repository(14) */
    @Autowired
    private KanrenshaKigyouDtHistory13Repository kanrenshaKigyouDtHistory13Repository;
    /** 関連者企業・団体履歴Repository(15) */
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

    /**
     * 処理を行う
     *
     * @param name     団体名
     * @param address  住所
     * @param delegate 代表者名
     * @return 検索結果
     */
    public List<KanrenshaKigyouDtHistoryBaseEntity> practice( // SUPPRESS CHECKSTYLE ReturnCount NOPMD
            final String name, final String address, final String delegate) {

        switch (getPrefectureLgCodeService.practice(address)) {
            case GetPrefectureLgCodeService.PREF_01:
                return kanrenshaKigyouDtHistory01Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_02:
                return kanrenshaKigyouDtHistory02Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_03:
                return kanrenshaKigyouDtHistory03Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_04:
                return kanrenshaKigyouDtHistory04Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_05:
                return kanrenshaKigyouDtHistory05Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_06:
                return kanrenshaKigyouDtHistory06Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_07:
                return kanrenshaKigyouDtHistory07Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_08:
                return kanrenshaKigyouDtHistory08Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_09:
                return kanrenshaKigyouDtHistory09Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_10:
                return kanrenshaKigyouDtHistory10Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_11:
                return kanrenshaKigyouDtHistory11Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_12:
                return kanrenshaKigyouDtHistory12Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_13:
                return kanrenshaKigyouDtHistory13Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_14:
                return kanrenshaKigyouDtHistory14Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_15:
                return kanrenshaKigyouDtHistory15Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_16:
                return kanrenshaKigyouDtHistory16Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_17:
                return kanrenshaKigyouDtHistory17Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_18:
                return kanrenshaKigyouDtHistory18Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_19:
                return kanrenshaKigyouDtHistory19Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_20:
                return kanrenshaKigyouDtHistory20Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_21:
                return kanrenshaKigyouDtHistory21Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_22:
                return kanrenshaKigyouDtHistory22Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_23:
                return kanrenshaKigyouDtHistory23Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_24:
                return kanrenshaKigyouDtHistory24Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_25:
                return kanrenshaKigyouDtHistory25Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_26:
                return kanrenshaKigyouDtHistory26Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_27:
                return kanrenshaKigyouDtHistory27Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_28:
                return kanrenshaKigyouDtHistory28Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_29:
                return kanrenshaKigyouDtHistory29Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_30:
                return kanrenshaKigyouDtHistory30Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_31:
                return kanrenshaKigyouDtHistory31Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_32:
                return kanrenshaKigyouDtHistory32Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_33:
                return kanrenshaKigyouDtHistory33Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_34:
                return kanrenshaKigyouDtHistory34Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_35:
                return kanrenshaKigyouDtHistory35Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_36:
                return kanrenshaKigyouDtHistory36Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_37:
                return kanrenshaKigyouDtHistory37Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_38:
                return kanrenshaKigyouDtHistory38Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_39:
                return kanrenshaKigyouDtHistory39Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_40:
                return kanrenshaKigyouDtHistory40Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_41:
                return kanrenshaKigyouDtHistory41Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_42:
                return kanrenshaKigyouDtHistory42Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_43:
                return kanrenshaKigyouDtHistory43Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_44:
                return kanrenshaKigyouDtHistory44Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_45:
                return kanrenshaKigyouDtHistory45Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_46:
                return kanrenshaKigyouDtHistory46Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_47:
                return kanrenshaKigyouDtHistory47Repository.selectByProperty(name, address, delegate);
            default:
                return kanrenshaKigyouDtHistory99Repository.selectByProperty(name, address, delegate);
        }
    }
}
