package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha; // NOPMD

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory01Repository;
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
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.GetPrefectureLgCodeService;

/**
 * 関連者政治団体の同属性リスト取得Service
 */
@Service
public class GetKanrenshaSeijidantaiSameHistoryService {

    /** 住所から県 地方公共団体コード(2桁)取得Service */
    @Autowired
    private GetPrefectureLgCodeService getPrefectureLgCodeService;

    /** 関連者企業・団体履歴Repository(01) */
    @Autowired
    private KanrenshaSeijidantaiHistory01Repository kanrenshaSeijidantaiHistory01Repository;
    /** 関連者企業・団体履歴Repository(02) */
    @Autowired
    private KanrenshaSeijidantaiHistory02Repository kanrenshaSeijidantaiHistory02Repository;
    /** 関連者企業・団体履歴Repository(03) */
    @Autowired
    private KanrenshaSeijidantaiHistory03Repository kanrenshaSeijidantaiHistory03Repository;
    /** 関連者企業・団体履歴Repository(04) */
    @Autowired
    private KanrenshaSeijidantaiHistory04Repository kanrenshaSeijidantaiHistory04Repository;
    /** 関連者企業・団体履歴Repository(05) */
    @Autowired
    private KanrenshaSeijidantaiHistory05Repository kanrenshaSeijidantaiHistory05Repository;
    /** 関連者企業・団体履歴Repository(06) */
    @Autowired
    private KanrenshaSeijidantaiHistory06Repository kanrenshaSeijidantaiHistory06Repository;
    /** 関連者企業・団体履歴Repository(07) */
    @Autowired
    private KanrenshaSeijidantaiHistory07Repository kanrenshaSeijidantaiHistory07Repository;
    /** 関連者企業・団体履歴Repository(08) */
    @Autowired
    private KanrenshaSeijidantaiHistory08Repository kanrenshaSeijidantaiHistory08Repository;
    /** 関連者企業・団体履歴Repository(09) */
    @Autowired
    private KanrenshaSeijidantaiHistory09Repository kanrenshaSeijidantaiHistory09Repository;
    /** 関連者企業・団体履歴Repository(10) */
    @Autowired
    private KanrenshaSeijidantaiHistory10Repository kanrenshaSeijidantaiHistory10Repository;
    /** 関連者企業・団体履歴Repository(12) */
    @Autowired
    private KanrenshaSeijidantaiHistory11Repository kanrenshaSeijidantaiHistory11Repository;
    /** 関連者企業・団体履歴Repository(13) */
    @Autowired
    private KanrenshaSeijidantaiHistory12Repository kanrenshaSeijidantaiHistory12Repository;
    /** 関連者企業・団体履歴Repository(14) */
    @Autowired
    private KanrenshaSeijidantaiHistory13Repository kanrenshaSeijidantaiHistory13Repository;
    /** 関連者企業・団体履歴Repository(15) */
    @Autowired
    private KanrenshaSeijidantaiHistory14Repository kanrenshaSeijidantaiHistory14Repository;
    /** 関連者企業・団体履歴Repository(15) */
    @Autowired
    private KanrenshaSeijidantaiHistory15Repository kanrenshaSeijidantaiHistory15Repository;
    /** 関連者企業・団体履歴Repository(16) */
    @Autowired
    private KanrenshaSeijidantaiHistory16Repository kanrenshaSeijidantaiHistory16Repository;
    /** 関連者企業・団体履歴Repository(17) */
    @Autowired
    private KanrenshaSeijidantaiHistory17Repository kanrenshaSeijidantaiHistory17Repository;
    /** 関連者企業・団体履歴Repository(18) */
    @Autowired
    private KanrenshaSeijidantaiHistory18Repository kanrenshaSeijidantaiHistory18Repository;
    /** 関連者企業・団体履歴Repository(19) */
    @Autowired
    private KanrenshaSeijidantaiHistory19Repository kanrenshaSeijidantaiHistory19Repository;
    /** 関連者企業・団体履歴Repository(20) */
    @Autowired
    private KanrenshaSeijidantaiHistory20Repository kanrenshaSeijidantaiHistory20Repository;
    /** 関連者企業・団体履歴Repository(21) */
    @Autowired
    private KanrenshaSeijidantaiHistory21Repository kanrenshaSeijidantaiHistory21Repository;
    /** 関連者企業・団体履歴Repository(22) */
    @Autowired
    private KanrenshaSeijidantaiHistory22Repository kanrenshaSeijidantaiHistory22Repository;
    /** 関連者企業・団体履歴Repository(23) */
    @Autowired
    private KanrenshaSeijidantaiHistory23Repository kanrenshaSeijidantaiHistory23Repository;
    /** 関連者企業・団体履歴Repository(24) */
    @Autowired
    private KanrenshaSeijidantaiHistory24Repository kanrenshaSeijidantaiHistory24Repository;
    /** 関連者企業・団体履歴Repository(25) */
    @Autowired
    private KanrenshaSeijidantaiHistory25Repository kanrenshaSeijidantaiHistory25Repository;
    /** 関連者企業・団体履歴Repository(26) */
    @Autowired
    private KanrenshaSeijidantaiHistory26Repository kanrenshaSeijidantaiHistory26Repository;
    /** 関連者企業・団体履歴Repository(27) */
    @Autowired
    private KanrenshaSeijidantaiHistory27Repository kanrenshaSeijidantaiHistory27Repository;
    /** 関連者企業・団体履歴Repository(28) */
    @Autowired
    private KanrenshaSeijidantaiHistory28Repository kanrenshaSeijidantaiHistory28Repository;
    /** 関連者企業・団体履歴Repository(29) */
    @Autowired
    private KanrenshaSeijidantaiHistory29Repository kanrenshaSeijidantaiHistory29Repository;
    /** 関連者企業・団体履歴Repository(30) */
    @Autowired
    private KanrenshaSeijidantaiHistory30Repository kanrenshaSeijidantaiHistory30Repository;
    /** 関連者企業・団体履歴Repository(31) */
    @Autowired
    private KanrenshaSeijidantaiHistory31Repository kanrenshaSeijidantaiHistory31Repository;
    /** 関連者企業・団体履歴Repository(32) */
    @Autowired
    private KanrenshaSeijidantaiHistory32Repository kanrenshaSeijidantaiHistory32Repository;
    /** 関連者企業・団体履歴Repository(33) */
    @Autowired
    private KanrenshaSeijidantaiHistory33Repository kanrenshaSeijidantaiHistory33Repository;
    /** 関連者企業・団体履歴Repository(34) */
    @Autowired
    private KanrenshaSeijidantaiHistory34Repository kanrenshaSeijidantaiHistory34Repository;
    /** 関連者企業・団体履歴Repository(35) */
    @Autowired
    private KanrenshaSeijidantaiHistory35Repository kanrenshaSeijidantaiHistory35Repository;
    /** 関連者企業・団体履歴Repository(36) */
    @Autowired
    private KanrenshaSeijidantaiHistory36Repository kanrenshaSeijidantaiHistory36Repository;
    /** 関連者企業・団体履歴Repository(37) */
    @Autowired
    private KanrenshaSeijidantaiHistory37Repository kanrenshaSeijidantaiHistory37Repository;
    /** 関連者企業・団体履歴Repository(38) */
    @Autowired
    private KanrenshaSeijidantaiHistory38Repository kanrenshaSeijidantaiHistory38Repository;
    /** 関連者企業・団体履歴Repository(39) */
    @Autowired
    private KanrenshaSeijidantaiHistory39Repository kanrenshaSeijidantaiHistory39Repository;
    /** 関連者企業・団体履歴Repository(40) */
    @Autowired
    private KanrenshaSeijidantaiHistory40Repository kanrenshaSeijidantaiHistory40Repository;
    /** 関連者企業・団体履歴Repository(41) */
    @Autowired
    private KanrenshaSeijidantaiHistory41Repository kanrenshaSeijidantaiHistory41Repository;
    /** 関連者企業・団体履歴Repository(42) */
    @Autowired
    private KanrenshaSeijidantaiHistory42Repository kanrenshaSeijidantaiHistory42Repository;
    /** 関連者企業・団体履歴Repository(43) */
    @Autowired
    private KanrenshaSeijidantaiHistory43Repository kanrenshaSeijidantaiHistory43Repository;
    /** 関連者企業・団体履歴Repository(44) */
    @Autowired
    private KanrenshaSeijidantaiHistory44Repository kanrenshaSeijidantaiHistory44Repository;
    /** 関連者企業・団体履歴Repository(45) */
    @Autowired
    private KanrenshaSeijidantaiHistory45Repository kanrenshaSeijidantaiHistory45Repository;
    /** 関連者企業・団体履歴Repository(46) */
    @Autowired
    private KanrenshaSeijidantaiHistory46Repository kanrenshaSeijidantaiHistory46Repository;
    /** 関連者企業・団体履歴Repository(47) */
    @Autowired
    private KanrenshaSeijidantaiHistory47Repository kanrenshaSeijidantaiHistory47Repository;
    /** 関連者企業・団体履歴Repository(99) */
    @Autowired
    private KanrenshaSeijidantaiHistory99Repository kanrenshaSeijidantaiHistory99Repository;

    /**
     * 処理を行う
     *
     * @param name     団体名
     * @param address  住所
     * @param delegate 代表者名
     * @return 検索結果
     */
    public List<KanrenshaSeijidantaiHistoryBaseEntity> practice( // SUPPRESS CHECKSTYLE ReturnCount NOPMD
            final String name, final String address, final String delegate) {

        switch (getPrefectureLgCodeService.practice(address)) {
            case GetPrefectureLgCodeService.PREF_01:
                return kanrenshaSeijidantaiHistory01Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_02:
                return kanrenshaSeijidantaiHistory02Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_03:
                return kanrenshaSeijidantaiHistory03Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_04:
                return kanrenshaSeijidantaiHistory04Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_05:
                return kanrenshaSeijidantaiHistory05Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_06:
                return kanrenshaSeijidantaiHistory06Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_07:
                return kanrenshaSeijidantaiHistory07Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_08:
                return kanrenshaSeijidantaiHistory08Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_09:
                return kanrenshaSeijidantaiHistory09Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_10:
                return kanrenshaSeijidantaiHistory10Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_11:
                return kanrenshaSeijidantaiHistory11Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_12:
                return kanrenshaSeijidantaiHistory12Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_13:
                return kanrenshaSeijidantaiHistory13Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_14:
                return kanrenshaSeijidantaiHistory14Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_15:
                return kanrenshaSeijidantaiHistory15Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_16:
                return kanrenshaSeijidantaiHistory16Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_17:
                return kanrenshaSeijidantaiHistory17Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_18:
                return kanrenshaSeijidantaiHistory18Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_19:
                return kanrenshaSeijidantaiHistory19Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_20:
                return kanrenshaSeijidantaiHistory20Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_21:
                return kanrenshaSeijidantaiHistory21Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_22:
                return kanrenshaSeijidantaiHistory22Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_23:
                return kanrenshaSeijidantaiHistory23Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_24:
                return kanrenshaSeijidantaiHistory24Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_25:
                return kanrenshaSeijidantaiHistory25Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_26:
                return kanrenshaSeijidantaiHistory26Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_27:
                return kanrenshaSeijidantaiHistory27Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_28:
                return kanrenshaSeijidantaiHistory28Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_29:
                return kanrenshaSeijidantaiHistory29Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_30:
                return kanrenshaSeijidantaiHistory30Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_31:
                return kanrenshaSeijidantaiHistory31Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_32:
                return kanrenshaSeijidantaiHistory32Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_33:
                return kanrenshaSeijidantaiHistory33Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_34:
                return kanrenshaSeijidantaiHistory34Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_35:
                return kanrenshaSeijidantaiHistory35Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_36:
                return kanrenshaSeijidantaiHistory36Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_37:
                return kanrenshaSeijidantaiHistory37Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_38:
                return kanrenshaSeijidantaiHistory38Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_39:
                return kanrenshaSeijidantaiHistory39Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_40:
                return kanrenshaSeijidantaiHistory40Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_41:
                return kanrenshaSeijidantaiHistory41Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_42:
                return kanrenshaSeijidantaiHistory42Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_43:
                return kanrenshaSeijidantaiHistory43Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_44:
                return kanrenshaSeijidantaiHistory44Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_45:
                return kanrenshaSeijidantaiHistory45Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_46:
                return kanrenshaSeijidantaiHistory46Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_47:
                return kanrenshaSeijidantaiHistory47Repository.selectByProperty(name, address, delegate);
            default:
                return kanrenshaSeijidantaiHistory99Repository.selectByProperty(name, address, delegate);
        }
    }

}
