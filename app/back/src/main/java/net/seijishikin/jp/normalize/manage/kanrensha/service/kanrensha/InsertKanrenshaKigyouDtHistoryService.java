package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha; // NOPMD

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory01Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory02Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory03Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory04Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory05Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory06Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory07Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory08Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory09Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory10Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory11Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory12Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory13Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory14Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory15Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory16Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory17Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory18Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory19Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory20Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory21Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory22Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory23Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory24Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory25Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory26Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory27Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory28Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory29Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory30Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory31Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory32Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory33Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory34Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory35Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory36Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory37Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory38Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory39Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory40Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory41Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory42Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory43Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory44Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory45Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory46Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory47Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory99Entity;




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
 * 関連者企業・団体履歴を挿入する
 */
@Service
public class InsertKanrenshaKigyouDtHistoryService { // NOPMD

    /** 住所から県 地方公共団体コード(2桁)取得Service */
    @Autowired
    private GetPrefectureLgCodeService getPrefectureLgCodeService;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者企業・団体履歴(01)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory01Repository kanrenshaKigyouDtHistory01Repository;
    /** 関連者企業・団体履歴(02)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory02Repository kanrenshaKigyouDtHistory02Repository;
    /** 関連者企業・団体履歴(03)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory03Repository kanrenshaKigyouDtHistory03Repository;
    /** 関連者企業・団体履歴(04)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory04Repository kanrenshaKigyouDtHistory04Repository;
    /** 関連者企業・団体履歴(05)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory05Repository kanrenshaKigyouDtHistory05Repository;
    /** 関連者企業・団体履歴(06)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory06Repository kanrenshaKigyouDtHistory06Repository;
    /** 関連者企業・団体履歴(07)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory07Repository kanrenshaKigyouDtHistory07Repository;
    /** 関連者企業・団体履歴(08)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory08Repository kanrenshaKigyouDtHistory08Repository;
    /** 関連者企業・団体履歴(09)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory09Repository kanrenshaKigyouDtHistory09Repository;
    /** 関連者企業・団体履歴(10)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory10Repository kanrenshaKigyouDtHistory10Repository;
    /** 関連者企業・団体履歴(11)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory11Repository kanrenshaKigyouDtHistory11Repository;
    /** 関連者企業・団体履歴(12)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory12Repository kanrenshaKigyouDtHistory12Repository;
    /** 関連者企業・団体履歴(13)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory13Repository kanrenshaKigyouDtHistory13Repository;
    /** 関連者企業・団体履歴(14)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory14Repository kanrenshaKigyouDtHistory14Repository;
    /** 関連者企業・団体履歴(15)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory15Repository kanrenshaKigyouDtHistory15Repository;
    /** 関連者企業・団体履歴(16)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory16Repository kanrenshaKigyouDtHistory16Repository;
    /** 関連者企業・団体履歴(17)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory17Repository kanrenshaKigyouDtHistory17Repository;
    /** 関連者企業・団体履歴(18)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory18Repository kanrenshaKigyouDtHistory18Repository;
    /** 関連者企業・団体履歴(19)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory19Repository kanrenshaKigyouDtHistory19Repository;
    /** 関連者企業・団体履歴(20)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory20Repository kanrenshaKigyouDtHistory20Repository;
    /** 関連者企業・団体履歴(21)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory21Repository kanrenshaKigyouDtHistory21Repository;
    /** 関連者企業・団体履歴(22)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory22Repository kanrenshaKigyouDtHistory22Repository;
    /** 関連者企業・団体履歴(23)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory23Repository kanrenshaKigyouDtHistory23Repository;
    /** 関連者企業・団体履歴(24)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory24Repository kanrenshaKigyouDtHistory24Repository;
    /** 関連者企業・団体履歴(25)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory25Repository kanrenshaKigyouDtHistory25Repository;
    /** 関連者企業・団体履歴(26)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory26Repository kanrenshaKigyouDtHistory26Repository;
    /** 関連者企業・団体履歴(27)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory27Repository kanrenshaKigyouDtHistory27Repository;
    /** 関連者企業・団体履歴(28)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory28Repository kanrenshaKigyouDtHistory28Repository;
    /** 関連者企業・団体履歴(29)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory29Repository kanrenshaKigyouDtHistory29Repository;
    /** 関連者企業・団体履歴(30)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory30Repository kanrenshaKigyouDtHistory30Repository;
    /** 関連者企業・団体履歴(31)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory31Repository kanrenshaKigyouDtHistory31Repository;
    /** 関連者企業・団体履歴(32)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory32Repository kanrenshaKigyouDtHistory32Repository;
    /** 関連者企業・団体履歴(33)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory33Repository kanrenshaKigyouDtHistory33Repository;
    /** 関連者企業・団体履歴(34)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory34Repository kanrenshaKigyouDtHistory34Repository;
    /** 関連者企業・団体履歴(35)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory35Repository kanrenshaKigyouDtHistory35Repository;
    /** 関連者企業・団体履歴(36)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory36Repository kanrenshaKigyouDtHistory36Repository;
    /** 関連者企業・団体履歴(37)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory37Repository kanrenshaKigyouDtHistory37Repository;
    /** 関連者企業・団体履歴(38)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory38Repository kanrenshaKigyouDtHistory38Repository;
    /** 関連者企業・団体履歴(39)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory39Repository kanrenshaKigyouDtHistory39Repository;
    /** 関連者企業・団体履歴(40)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory40Repository kanrenshaKigyouDtHistory40Repository;
    /** 関連者企業・団体履歴(41)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory41Repository kanrenshaKigyouDtHistory41Repository;
    /** 関連者企業・団体履歴(42)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory42Repository kanrenshaKigyouDtHistory42Repository;
    /** 関連者企業・団体履歴(43)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory43Repository kanrenshaKigyouDtHistory43Repository;
    /** 関連者企業・団体履歴(44)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory44Repository kanrenshaKigyouDtHistory44Repository;
    /** 関連者企業・団体履歴(45)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory45Repository kanrenshaKigyouDtHistory45Repository;
    /** 関連者企業・団体履歴(46)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory46Repository kanrenshaKigyouDtHistory46Repository;
    /** 関連者企業・団体履歴(47)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory47Repository kanrenshaKigyouDtHistory47Repository;
    /** 関連者企業・団体履歴(99)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory99Repository kanrenshaKigyouDtHistory99Repository;

    /**
     * 処理を行う
     *
     * @param userDto    ユーザ最低限Dto
     * @param baseEntity 関連者企業・団体BaseEntity
     */
    public int practice( // NOPMD SUPPRESS CHECKSTYLE ReturbCount
            final LeastUserDto userDto, final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {

        switch (getPrefectureLgCodeService.practice(baseEntity.getAllAddress())) {
            case GetPrefectureLgCodeService.PREF_01:
                return kanrenshaKigyouDtHistory01Repository.saveAndFlush(this.createEntity01(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_02:
                return kanrenshaKigyouDtHistory02Repository.saveAndFlush(this.createEntity02(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_03:
                return kanrenshaKigyouDtHistory03Repository.saveAndFlush(this.createEntity03(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_04:
                return kanrenshaKigyouDtHistory04Repository.saveAndFlush(this.createEntity04(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_05:
                return kanrenshaKigyouDtHistory05Repository.saveAndFlush(this.createEntity05(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_06:
                return kanrenshaKigyouDtHistory06Repository.saveAndFlush(this.createEntity06(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_07:
                return kanrenshaKigyouDtHistory07Repository.saveAndFlush(this.createEntity07(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_08:
                return kanrenshaKigyouDtHistory08Repository.saveAndFlush(this.createEntity08(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_09:
                return kanrenshaKigyouDtHistory09Repository.saveAndFlush(this.createEntity09(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_10:
                return kanrenshaKigyouDtHistory10Repository.saveAndFlush(this.createEntity10(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_11:
                return kanrenshaKigyouDtHistory11Repository.saveAndFlush(this.createEntity11(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_12:
                return kanrenshaKigyouDtHistory12Repository.saveAndFlush(this.createEntity12(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_13:
                return kanrenshaKigyouDtHistory13Repository.saveAndFlush(this.createEntity13(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_14:
                return kanrenshaKigyouDtHistory14Repository.saveAndFlush(this.createEntity14(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_15:
                return kanrenshaKigyouDtHistory15Repository.saveAndFlush(this.createEntity15(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_16:
                return kanrenshaKigyouDtHistory16Repository.saveAndFlush(this.createEntity16(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_17:
                return kanrenshaKigyouDtHistory17Repository.saveAndFlush(this.createEntity17(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_18:
                return kanrenshaKigyouDtHistory18Repository.saveAndFlush(this.createEntity18(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_19:
                return kanrenshaKigyouDtHistory19Repository.saveAndFlush(this.createEntity19(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_20:
                return kanrenshaKigyouDtHistory20Repository.saveAndFlush(this.createEntity20(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_21:
                return kanrenshaKigyouDtHistory21Repository.saveAndFlush(this.createEntity21(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_22:
                return kanrenshaKigyouDtHistory22Repository.saveAndFlush(this.createEntity22(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_23:
                return kanrenshaKigyouDtHistory23Repository.saveAndFlush(this.createEntity23(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_24:
                return kanrenshaKigyouDtHistory24Repository.saveAndFlush(this.createEntity24(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_25:
                return kanrenshaKigyouDtHistory25Repository.saveAndFlush(this.createEntity25(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_26:
                return kanrenshaKigyouDtHistory26Repository.saveAndFlush(this.createEntity26(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_27:
                return kanrenshaKigyouDtHistory27Repository.saveAndFlush(this.createEntity27(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_28:
                return kanrenshaKigyouDtHistory28Repository.saveAndFlush(this.createEntity28(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_29:
                return kanrenshaKigyouDtHistory29Repository.saveAndFlush(this.createEntity29(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_30:
                return kanrenshaKigyouDtHistory30Repository.saveAndFlush(this.createEntity30(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_31:
                return kanrenshaKigyouDtHistory31Repository.saveAndFlush(this.createEntity31(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_32:
                return kanrenshaKigyouDtHistory32Repository.saveAndFlush(this.createEntity32(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_33:
                return kanrenshaKigyouDtHistory33Repository.saveAndFlush(this.createEntity33(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_34:
                return kanrenshaKigyouDtHistory34Repository.saveAndFlush(this.createEntity34(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_35:
                return kanrenshaKigyouDtHistory35Repository.saveAndFlush(this.createEntity35(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_36:
                return kanrenshaKigyouDtHistory36Repository.saveAndFlush(this.createEntity36(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_37:
                return kanrenshaKigyouDtHistory37Repository.saveAndFlush(this.createEntity37(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_38:
                return kanrenshaKigyouDtHistory38Repository.saveAndFlush(this.createEntity38(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_39:
                return kanrenshaKigyouDtHistory39Repository.saveAndFlush(this.createEntity39(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_40:
                return kanrenshaKigyouDtHistory40Repository.saveAndFlush(this.createEntity40(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_41:
                return kanrenshaKigyouDtHistory41Repository.saveAndFlush(this.createEntity41(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_42:
                return kanrenshaKigyouDtHistory42Repository.saveAndFlush(this.createEntity42(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_43:
                return kanrenshaKigyouDtHistory43Repository.saveAndFlush(this.createEntity43(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_44:
                return kanrenshaKigyouDtHistory44Repository.saveAndFlush(this.createEntity44(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_45:
                return kanrenshaKigyouDtHistory45Repository.saveAndFlush(this.createEntity45(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_46:
                return kanrenshaKigyouDtHistory46Repository.saveAndFlush(this.createEntity46(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            case GetPrefectureLgCodeService.PREF_47:
                return kanrenshaKigyouDtHistory47Repository.saveAndFlush(this.createEntity47(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
            default:
                return kanrenshaKigyouDtHistory99Repository.saveAndFlush(this.createEntity99(userDto, baseEntity))
                        .getKanrenshaKigyouDtHistoryId();
        }
    }

    private KanrenshaKigyouDtHistory01Entity createEntity01(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory01Entity entity = new KanrenshaKigyouDtHistory01Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory02Entity createEntity02(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory02Entity entity = new KanrenshaKigyouDtHistory02Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory03Entity createEntity03(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory03Entity entity = new KanrenshaKigyouDtHistory03Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory04Entity createEntity04(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory04Entity entity = new KanrenshaKigyouDtHistory04Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory05Entity createEntity05(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory05Entity entity = new KanrenshaKigyouDtHistory05Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory06Entity createEntity06(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory06Entity entity = new KanrenshaKigyouDtHistory06Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory07Entity createEntity07(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory07Entity entity = new KanrenshaKigyouDtHistory07Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory08Entity createEntity08(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory08Entity entity = new KanrenshaKigyouDtHistory08Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory09Entity createEntity09(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory09Entity entity = new KanrenshaKigyouDtHistory09Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory10Entity createEntity10(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory10Entity entity = new KanrenshaKigyouDtHistory10Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory11Entity createEntity11(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory11Entity entity = new KanrenshaKigyouDtHistory11Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory12Entity createEntity12(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory12Entity entity = new KanrenshaKigyouDtHistory12Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory13Entity createEntity13(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory13Entity entity = new KanrenshaKigyouDtHistory13Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory14Entity createEntity14(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory14Entity entity = new KanrenshaKigyouDtHistory14Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory15Entity createEntity15(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory15Entity entity = new KanrenshaKigyouDtHistory15Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory16Entity createEntity16(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory16Entity entity = new KanrenshaKigyouDtHistory16Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory17Entity createEntity17(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory17Entity entity = new KanrenshaKigyouDtHistory17Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory18Entity createEntity18(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory18Entity entity = new KanrenshaKigyouDtHistory18Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory19Entity createEntity19(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory19Entity entity = new KanrenshaKigyouDtHistory19Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory20Entity createEntity20(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory20Entity entity = new KanrenshaKigyouDtHistory20Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory21Entity createEntity21(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory21Entity entity = new KanrenshaKigyouDtHistory21Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory22Entity createEntity22(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory22Entity entity = new KanrenshaKigyouDtHistory22Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory23Entity createEntity23(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory23Entity entity = new KanrenshaKigyouDtHistory23Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory24Entity createEntity24(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory24Entity entity = new KanrenshaKigyouDtHistory24Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory25Entity createEntity25(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory25Entity entity = new KanrenshaKigyouDtHistory25Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory26Entity createEntity26(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory26Entity entity = new KanrenshaKigyouDtHistory26Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory27Entity createEntity27(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory27Entity entity = new KanrenshaKigyouDtHistory27Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory28Entity createEntity28(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory28Entity entity = new KanrenshaKigyouDtHistory28Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory29Entity createEntity29(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory29Entity entity = new KanrenshaKigyouDtHistory29Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory30Entity createEntity30(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory30Entity entity = new KanrenshaKigyouDtHistory30Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory31Entity createEntity31(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory31Entity entity = new KanrenshaKigyouDtHistory31Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory32Entity createEntity32(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory32Entity entity = new KanrenshaKigyouDtHistory32Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory33Entity createEntity33(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory33Entity entity = new KanrenshaKigyouDtHistory33Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory34Entity createEntity34(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory34Entity entity = new KanrenshaKigyouDtHistory34Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory35Entity createEntity35(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory35Entity entity = new KanrenshaKigyouDtHistory35Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory36Entity createEntity36(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory36Entity entity = new KanrenshaKigyouDtHistory36Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory37Entity createEntity37(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory37Entity entity = new KanrenshaKigyouDtHistory37Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory38Entity createEntity38(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory38Entity entity = new KanrenshaKigyouDtHistory38Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory39Entity createEntity39(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory39Entity entity = new KanrenshaKigyouDtHistory39Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory40Entity createEntity40(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory40Entity entity = new KanrenshaKigyouDtHistory40Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory41Entity createEntity41(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory41Entity entity = new KanrenshaKigyouDtHistory41Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory42Entity createEntity42(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory42Entity entity = new KanrenshaKigyouDtHistory42Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory43Entity createEntity43(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory43Entity entity = new KanrenshaKigyouDtHistory43Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory44Entity createEntity44(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory44Entity entity = new KanrenshaKigyouDtHistory44Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory45Entity createEntity45(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory45Entity entity = new KanrenshaKigyouDtHistory45Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory46Entity createEntity46(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory46Entity entity = new KanrenshaKigyouDtHistory46Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory47Entity createEntity47(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory47Entity entity = new KanrenshaKigyouDtHistory47Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaKigyouDtHistory99Entity createEntity99(final LeastUserDto userDto,
            final KanrenshaKigyouDtHistoryBaseEntity baseEntity) {
        KanrenshaKigyouDtHistory99Entity entity = new KanrenshaKigyouDtHistory99Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaKigyouDtHistoryId(0); // auto_increment明示
        return entity;
    }

}
