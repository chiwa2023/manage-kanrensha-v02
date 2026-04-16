package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha; // NOPMD

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonHistoryBaseEntity;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory01Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory02Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory03Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory04Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory05Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory06Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory07Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory08Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory09Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory10Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory11Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory12Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory13Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory14Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory15Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory16Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory17Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory18Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory19Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory20Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory21Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory22Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory23Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory24Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory25Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory26Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory27Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory28Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory29Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory30Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory31Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory32Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory33Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory34Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory35Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory36Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory37Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory38Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory39Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory40Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory41Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory42Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory43Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory44Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory45Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory46Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory47Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory99Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory01Repository;
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

/**
 * 関連者個人履歴挿入Service
 */
@Service
public class InsertKanrenshaPersonHistoryService { // NOPMD

    /** 住所から県 地方公共団体コード(2桁)取得Service */
    @Autowired
    private GetPrefectureLgCodeService getPrefectureLgCodeService;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者企業・団体履歴(01)Repository */
    @Autowired
    private KanrenshaPersonHistory01Repository kanrenshaPersonHistory01Repository;
    /** 関連者企業・団体履歴(02)Repository */
    @Autowired
    private KanrenshaPersonHistory02Repository kanrenshaPersonHistory02Repository;
    /** 関連者企業・団体履歴(03)Repository */
    @Autowired
    private KanrenshaPersonHistory03Repository kanrenshaPersonHistory03Repository;
    /** 関連者企業・団体履歴(04)Repository */
    @Autowired
    private KanrenshaPersonHistory04Repository kanrenshaPersonHistory04Repository;
    /** 関連者企業・団体履歴(05)Repository */
    @Autowired
    private KanrenshaPersonHistory05Repository kanrenshaPersonHistory05Repository;
    /** 関連者企業・団体履歴(06)Repository */
    @Autowired
    private KanrenshaPersonHistory06Repository kanrenshaPersonHistory06Repository;
    /** 関連者企業・団体履歴(07)Repository */
    @Autowired
    private KanrenshaPersonHistory07Repository kanrenshaPersonHistory07Repository;
    /** 関連者企業・団体履歴(08)Repository */
    @Autowired
    private KanrenshaPersonHistory08Repository kanrenshaPersonHistory08Repository;
    /** 関連者企業・団体履歴(09)Repository */
    @Autowired
    private KanrenshaPersonHistory09Repository kanrenshaPersonHistory09Repository;
    /** 関連者企業・団体履歴(10)Repository */
    @Autowired
    private KanrenshaPersonHistory10Repository kanrenshaPersonHistory10Repository;
    /** 関連者企業・団体履歴(11)Repository */
    @Autowired
    private KanrenshaPersonHistory11Repository kanrenshaPersonHistory11Repository;
    /** 関連者企業・団体履歴(12)Repository */
    @Autowired
    private KanrenshaPersonHistory12Repository kanrenshaPersonHistory12Repository;
    /** 関連者企業・団体履歴(13)Repository */
    @Autowired
    private KanrenshaPersonHistory13Repository kanrenshaPersonHistory13Repository;
    /** 関連者企業・団体履歴(14)Repository */
    @Autowired
    private KanrenshaPersonHistory14Repository kanrenshaPersonHistory14Repository;
    /** 関連者企業・団体履歴(15)Repository */
    @Autowired
    private KanrenshaPersonHistory15Repository kanrenshaPersonHistory15Repository;
    /** 関連者企業・団体履歴(16)Repository */
    @Autowired
    private KanrenshaPersonHistory16Repository kanrenshaPersonHistory16Repository;
    /** 関連者企業・団体履歴(17)Repository */
    @Autowired
    private KanrenshaPersonHistory17Repository kanrenshaPersonHistory17Repository;
    /** 関連者企業・団体履歴(18)Repository */
    @Autowired
    private KanrenshaPersonHistory18Repository kanrenshaPersonHistory18Repository;
    /** 関連者企業・団体履歴(19)Repository */
    @Autowired
    private KanrenshaPersonHistory19Repository kanrenshaPersonHistory19Repository;
    /** 関連者企業・団体履歴(20)Repository */
    @Autowired
    private KanrenshaPersonHistory20Repository kanrenshaPersonHistory20Repository;
    /** 関連者企業・団体履歴(21)Repository */
    @Autowired
    private KanrenshaPersonHistory21Repository kanrenshaPersonHistory21Repository;
    /** 関連者企業・団体履歴(22)Repository */
    @Autowired
    private KanrenshaPersonHistory22Repository kanrenshaPersonHistory22Repository;
    /** 関連者企業・団体履歴(23)Repository */
    @Autowired
    private KanrenshaPersonHistory23Repository kanrenshaPersonHistory23Repository;
    /** 関連者企業・団体履歴(24)Repository */
    @Autowired
    private KanrenshaPersonHistory24Repository kanrenshaPersonHistory24Repository;
    /** 関連者企業・団体履歴(25)Repository */
    @Autowired
    private KanrenshaPersonHistory25Repository kanrenshaPersonHistory25Repository;
    /** 関連者企業・団体履歴(26)Repository */
    @Autowired
    private KanrenshaPersonHistory26Repository kanrenshaPersonHistory26Repository;
    /** 関連者企業・団体履歴(27)Repository */
    @Autowired
    private KanrenshaPersonHistory27Repository kanrenshaPersonHistory27Repository;
    /** 関連者企業・団体履歴(28)Repository */
    @Autowired
    private KanrenshaPersonHistory28Repository kanrenshaPersonHistory28Repository;
    /** 関連者企業・団体履歴(29)Repository */
    @Autowired
    private KanrenshaPersonHistory29Repository kanrenshaPersonHistory29Repository;
    /** 関連者企業・団体履歴(30)Repository */
    @Autowired
    private KanrenshaPersonHistory30Repository kanrenshaPersonHistory30Repository;
    /** 関連者企業・団体履歴(31)Repository */
    @Autowired
    private KanrenshaPersonHistory31Repository kanrenshaPersonHistory31Repository;
    /** 関連者企業・団体履歴(32)Repository */
    @Autowired
    private KanrenshaPersonHistory32Repository kanrenshaPersonHistory32Repository;
    /** 関連者企業・団体履歴(33)Repository */
    @Autowired
    private KanrenshaPersonHistory33Repository kanrenshaPersonHistory33Repository;
    /** 関連者企業・団体履歴(34)Repository */
    @Autowired
    private KanrenshaPersonHistory34Repository kanrenshaPersonHistory34Repository;
    /** 関連者企業・団体履歴(35)Repository */
    @Autowired
    private KanrenshaPersonHistory35Repository kanrenshaPersonHistory35Repository;
    /** 関連者企業・団体履歴(36)Repository */
    @Autowired
    private KanrenshaPersonHistory36Repository kanrenshaPersonHistory36Repository;
    /** 関連者企業・団体履歴(37)Repository */
    @Autowired
    private KanrenshaPersonHistory37Repository kanrenshaPersonHistory37Repository;
    /** 関連者企業・団体履歴(38)Repository */
    @Autowired
    private KanrenshaPersonHistory38Repository kanrenshaPersonHistory38Repository;
    /** 関連者企業・団体履歴(39)Repository */
    @Autowired
    private KanrenshaPersonHistory39Repository kanrenshaPersonHistory39Repository;
    /** 関連者企業・団体履歴(40)Repository */
    @Autowired
    private KanrenshaPersonHistory40Repository kanrenshaPersonHistory40Repository;
    /** 関連者企業・団体履歴(41)Repository */
    @Autowired
    private KanrenshaPersonHistory41Repository kanrenshaPersonHistory41Repository;
    /** 関連者企業・団体履歴(42)Repository */
    @Autowired
    private KanrenshaPersonHistory42Repository kanrenshaPersonHistory42Repository;
    /** 関連者企業・団体履歴(43)Repository */
    @Autowired
    private KanrenshaPersonHistory43Repository kanrenshaPersonHistory43Repository;
    /** 関連者企業・団体履歴(44)Repository */
    @Autowired
    private KanrenshaPersonHistory44Repository kanrenshaPersonHistory44Repository;
    /** 関連者企業・団体履歴(45)Repository */
    @Autowired
    private KanrenshaPersonHistory45Repository kanrenshaPersonHistory45Repository;
    /** 関連者企業・団体履歴(46)Repository */
    @Autowired
    private KanrenshaPersonHistory46Repository kanrenshaPersonHistory46Repository;
    /** 関連者企業・団体履歴(47)Repository */
    @Autowired
    private KanrenshaPersonHistory47Repository kanrenshaPersonHistory47Repository;
    /** 関連者企業・団体履歴(99)Repository */
    @Autowired
    private KanrenshaPersonHistory99Repository kanrenshaPersonHistory99Repository;

    /** 全文検索検索対象テキスト作成Uttility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 処理を行う
     *
     * @param userDto    ユーザ最低限Dto
     * @param baseEntity 関連者企業・団体BaseEntity
     * @return 保存Id
     */
    public int practice( // NOPMD SUPPRESS CHECKSTYLE ReturnCount
            final LeastUserDto userDto, final KanrenshaPersonHistoryBaseEntity baseEntity) {

        baseEntity.setSearchText(formatNaturalSearchTextUtil
                .practice(baseEntity.getAllName() + baseEntity.getAllAddress() + baseEntity.getPersonShokugyou()));

        switch (getPrefectureLgCodeService.practice(baseEntity.getAllAddress())) {
            case GetPrefectureLgCodeService.PREF_01:
                return kanrenshaPersonHistory01Repository.save(this.createEntity01(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_02:
                return kanrenshaPersonHistory02Repository.save(this.createEntity02(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_03:
                return kanrenshaPersonHistory03Repository.save(this.createEntity03(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_04:
                return kanrenshaPersonHistory04Repository.save(this.createEntity04(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_05:
                return kanrenshaPersonHistory05Repository.save(this.createEntity05(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_06:
                return kanrenshaPersonHistory06Repository.save(this.createEntity06(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_07:
                return kanrenshaPersonHistory07Repository.save(this.createEntity07(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_08:
                return kanrenshaPersonHistory08Repository.save(this.createEntity08(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_09:
                return kanrenshaPersonHistory09Repository.save(this.createEntity09(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_10:
                return kanrenshaPersonHistory10Repository.save(this.createEntity10(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_11:
                return kanrenshaPersonHistory11Repository.save(this.createEntity11(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_12:
                return kanrenshaPersonHistory12Repository.save(this.createEntity12(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_13:
                return kanrenshaPersonHistory13Repository.save(this.createEntity13(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_14:
                return kanrenshaPersonHistory14Repository.save(this.createEntity14(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_15:
                return kanrenshaPersonHistory15Repository.save(this.createEntity15(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_16:
                return kanrenshaPersonHistory16Repository.save(this.createEntity16(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_17:
                return kanrenshaPersonHistory17Repository.save(this.createEntity17(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_18:
                return kanrenshaPersonHistory18Repository.save(this.createEntity18(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_19:
                return kanrenshaPersonHistory19Repository.save(this.createEntity19(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_20:
                return kanrenshaPersonHistory20Repository.save(this.createEntity20(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_21:
                return kanrenshaPersonHistory21Repository.save(this.createEntity21(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_22:
                return kanrenshaPersonHistory22Repository.save(this.createEntity22(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_23:
                return kanrenshaPersonHistory23Repository.save(this.createEntity23(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_24:
                return kanrenshaPersonHistory24Repository.save(this.createEntity24(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_25:
                return kanrenshaPersonHistory25Repository.save(this.createEntity25(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_26:
                return kanrenshaPersonHistory26Repository.save(this.createEntity26(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_27:
                return kanrenshaPersonHistory27Repository.save(this.createEntity27(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_28:
                return kanrenshaPersonHistory28Repository.save(this.createEntity28(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_29:
                return kanrenshaPersonHistory29Repository.save(this.createEntity29(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_30:
                return kanrenshaPersonHistory30Repository.save(this.createEntity30(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_31:
                return kanrenshaPersonHistory31Repository.save(this.createEntity31(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_32:
                return kanrenshaPersonHistory32Repository.save(this.createEntity32(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_33:
                return kanrenshaPersonHistory33Repository.save(this.createEntity33(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_34:
                return kanrenshaPersonHistory34Repository.save(this.createEntity34(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_35:
                return kanrenshaPersonHistory35Repository.save(this.createEntity35(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_36:
                return kanrenshaPersonHistory36Repository.save(this.createEntity36(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_37:
                return kanrenshaPersonHistory37Repository.save(this.createEntity37(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_38:
                return kanrenshaPersonHistory38Repository.save(this.createEntity38(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_39:
                return kanrenshaPersonHistory39Repository.save(this.createEntity39(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_40:
                return kanrenshaPersonHistory40Repository.save(this.createEntity40(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_41:
                return kanrenshaPersonHistory41Repository.save(this.createEntity41(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_42:
                return kanrenshaPersonHistory42Repository.save(this.createEntity42(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_43:
                return kanrenshaPersonHistory43Repository.save(this.createEntity43(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_44:
                return kanrenshaPersonHistory44Repository.save(this.createEntity44(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_45:
                return kanrenshaPersonHistory45Repository.save(this.createEntity45(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_46:
                return kanrenshaPersonHistory46Repository.save(this.createEntity46(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            case GetPrefectureLgCodeService.PREF_47:
                return kanrenshaPersonHistory47Repository.save(this.createEntity47(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
            default:
                return kanrenshaPersonHistory99Repository.save(this.createEntity99(userDto, baseEntity))
                        .getKanrenshaPersonHistoryId();
        }
    }

    private KanrenshaPersonHistory01Entity createEntity01(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory01Entity entity = new KanrenshaPersonHistory01Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory02Entity createEntity02(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory02Entity entity = new KanrenshaPersonHistory02Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory03Entity createEntity03(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory03Entity entity = new KanrenshaPersonHistory03Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory04Entity createEntity04(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory04Entity entity = new KanrenshaPersonHistory04Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory05Entity createEntity05(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory05Entity entity = new KanrenshaPersonHistory05Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory06Entity createEntity06(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory06Entity entity = new KanrenshaPersonHistory06Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory07Entity createEntity07(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory07Entity entity = new KanrenshaPersonHistory07Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory08Entity createEntity08(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory08Entity entity = new KanrenshaPersonHistory08Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory09Entity createEntity09(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory09Entity entity = new KanrenshaPersonHistory09Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory10Entity createEntity10(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory10Entity entity = new KanrenshaPersonHistory10Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory11Entity createEntity11(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory11Entity entity = new KanrenshaPersonHistory11Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory12Entity createEntity12(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory12Entity entity = new KanrenshaPersonHistory12Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory13Entity createEntity13(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory13Entity entity = new KanrenshaPersonHistory13Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory14Entity createEntity14(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory14Entity entity = new KanrenshaPersonHistory14Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory15Entity createEntity15(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory15Entity entity = new KanrenshaPersonHistory15Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory16Entity createEntity16(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory16Entity entity = new KanrenshaPersonHistory16Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory17Entity createEntity17(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory17Entity entity = new KanrenshaPersonHistory17Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory18Entity createEntity18(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory18Entity entity = new KanrenshaPersonHistory18Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory19Entity createEntity19(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory19Entity entity = new KanrenshaPersonHistory19Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory20Entity createEntity20(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory20Entity entity = new KanrenshaPersonHistory20Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory21Entity createEntity21(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory21Entity entity = new KanrenshaPersonHistory21Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory22Entity createEntity22(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory22Entity entity = new KanrenshaPersonHistory22Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory23Entity createEntity23(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory23Entity entity = new KanrenshaPersonHistory23Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory24Entity createEntity24(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory24Entity entity = new KanrenshaPersonHistory24Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory25Entity createEntity25(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory25Entity entity = new KanrenshaPersonHistory25Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory26Entity createEntity26(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory26Entity entity = new KanrenshaPersonHistory26Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory27Entity createEntity27(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory27Entity entity = new KanrenshaPersonHistory27Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory28Entity createEntity28(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory28Entity entity = new KanrenshaPersonHistory28Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory29Entity createEntity29(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory29Entity entity = new KanrenshaPersonHistory29Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory30Entity createEntity30(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory30Entity entity = new KanrenshaPersonHistory30Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory31Entity createEntity31(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory31Entity entity = new KanrenshaPersonHistory31Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory32Entity createEntity32(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory32Entity entity = new KanrenshaPersonHistory32Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory33Entity createEntity33(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory33Entity entity = new KanrenshaPersonHistory33Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory34Entity createEntity34(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory34Entity entity = new KanrenshaPersonHistory34Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory35Entity createEntity35(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory35Entity entity = new KanrenshaPersonHistory35Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory36Entity createEntity36(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory36Entity entity = new KanrenshaPersonHistory36Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory37Entity createEntity37(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory37Entity entity = new KanrenshaPersonHistory37Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory38Entity createEntity38(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory38Entity entity = new KanrenshaPersonHistory38Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory39Entity createEntity39(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory39Entity entity = new KanrenshaPersonHistory39Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory40Entity createEntity40(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory40Entity entity = new KanrenshaPersonHistory40Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory41Entity createEntity41(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory41Entity entity = new KanrenshaPersonHistory41Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory42Entity createEntity42(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory42Entity entity = new KanrenshaPersonHistory42Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory43Entity createEntity43(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory43Entity entity = new KanrenshaPersonHistory43Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory44Entity createEntity44(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory44Entity entity = new KanrenshaPersonHistory44Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory45Entity createEntity45(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory45Entity entity = new KanrenshaPersonHistory45Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory46Entity createEntity46(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory46Entity entity = new KanrenshaPersonHistory46Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory47Entity createEntity47(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory47Entity entity = new KanrenshaPersonHistory47Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaPersonHistory99Entity createEntity99(final LeastUserDto userDto,
            final KanrenshaPersonHistoryBaseEntity baseEntity) {
        KanrenshaPersonHistory99Entity entity = new KanrenshaPersonHistory99Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaPersonHistoryId(0); // auto_increment明示
        return entity;
    }
}
