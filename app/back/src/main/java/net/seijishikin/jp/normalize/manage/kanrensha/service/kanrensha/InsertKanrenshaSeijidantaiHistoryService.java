package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha; // NOPMD

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory01Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory02Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory03Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory04Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory05Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory06Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory07Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory08Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory09Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory10Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory11Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory12Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory13Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory14Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory15Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory16Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory17Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory18Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory19Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory20Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory21Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory22Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory23Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory24Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory25Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory26Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory27Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory28Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory29Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory30Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory31Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory32Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory33Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory34Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory35Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory36Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory37Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory38Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory39Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory40Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory41Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory42Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory43Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory44Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory45Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory46Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory47Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory99Entity;
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
 * 関連者政治団体挿入Service
 */
@Service
public class InsertKanrenshaSeijidantaiHistoryService { // NOPMD

    /** 住所から県 地方公共団体コード(2桁)取得Service */
    @Autowired
    private GetPrefectureLgCodeService getPrefectureLgCodeService;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者企業・団体履歴(01)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory01Repository kanrenshaSeijidantaiHistory01Repository;
    /** 関連者企業・団体履歴(02)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory02Repository kanrenshaSeijidantaiHistory02Repository;
    /** 関連者企業・団体履歴(03)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory03Repository kanrenshaSeijidantaiHistory03Repository;
    /** 関連者企業・団体履歴(04)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory04Repository kanrenshaSeijidantaiHistory04Repository;
    /** 関連者企業・団体履歴(05)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory05Repository kanrenshaSeijidantaiHistory05Repository;
    /** 関連者企業・団体履歴(06)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory06Repository kanrenshaSeijidantaiHistory06Repository;
    /** 関連者企業・団体履歴(07)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory07Repository kanrenshaSeijidantaiHistory07Repository;
    /** 関連者企業・団体履歴(08)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory08Repository kanrenshaSeijidantaiHistory08Repository;
    /** 関連者企業・団体履歴(09)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory09Repository kanrenshaSeijidantaiHistory09Repository;
    /** 関連者企業・団体履歴(10)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory10Repository kanrenshaSeijidantaiHistory10Repository;
    /** 関連者企業・団体履歴(11)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory11Repository kanrenshaSeijidantaiHistory11Repository;
    /** 関連者企業・団体履歴(12)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory12Repository kanrenshaSeijidantaiHistory12Repository;
    /** 関連者企業・団体履歴(13)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory13Repository kanrenshaSeijidantaiHistory13Repository;
    /** 関連者企業・団体履歴(14)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory14Repository kanrenshaSeijidantaiHistory14Repository;
    /** 関連者企業・団体履歴(15)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory15Repository kanrenshaSeijidantaiHistory15Repository;
    /** 関連者企業・団体履歴(16)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory16Repository kanrenshaSeijidantaiHistory16Repository;
    /** 関連者企業・団体履歴(17)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory17Repository kanrenshaSeijidantaiHistory17Repository;
    /** 関連者企業・団体履歴(18)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory18Repository kanrenshaSeijidantaiHistory18Repository;
    /** 関連者企業・団体履歴(19)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory19Repository kanrenshaSeijidantaiHistory19Repository;
    /** 関連者企業・団体履歴(20)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory20Repository kanrenshaSeijidantaiHistory20Repository;
    /** 関連者企業・団体履歴(21)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory21Repository kanrenshaSeijidantaiHistory21Repository;
    /** 関連者企業・団体履歴(22)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory22Repository kanrenshaSeijidantaiHistory22Repository;
    /** 関連者企業・団体履歴(23)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory23Repository kanrenshaSeijidantaiHistory23Repository;
    /** 関連者企業・団体履歴(24)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory24Repository kanrenshaSeijidantaiHistory24Repository;
    /** 関連者企業・団体履歴(25)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory25Repository kanrenshaSeijidantaiHistory25Repository;
    /** 関連者企業・団体履歴(26)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory26Repository kanrenshaSeijidantaiHistory26Repository;
    /** 関連者企業・団体履歴(27)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory27Repository kanrenshaSeijidantaiHistory27Repository;
    /** 関連者企業・団体履歴(28)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory28Repository kanrenshaSeijidantaiHistory28Repository;
    /** 関連者企業・団体履歴(29)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory29Repository kanrenshaSeijidantaiHistory29Repository;
    /** 関連者企業・団体履歴(30)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory30Repository kanrenshaSeijidantaiHistory30Repository;
    /** 関連者企業・団体履歴(31)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory31Repository kanrenshaSeijidantaiHistory31Repository;
    /** 関連者企業・団体履歴(32)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory32Repository kanrenshaSeijidantaiHistory32Repository;
    /** 関連者企業・団体履歴(33)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory33Repository kanrenshaSeijidantaiHistory33Repository;
    /** 関連者企業・団体履歴(34)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory34Repository kanrenshaSeijidantaiHistory34Repository;
    /** 関連者企業・団体履歴(35)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory35Repository kanrenshaSeijidantaiHistory35Repository;
    /** 関連者企業・団体履歴(36)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory36Repository kanrenshaSeijidantaiHistory36Repository;
    /** 関連者企業・団体履歴(37)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory37Repository kanrenshaSeijidantaiHistory37Repository;
    /** 関連者企業・団体履歴(38)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory38Repository kanrenshaSeijidantaiHistory38Repository;
    /** 関連者企業・団体履歴(39)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory39Repository kanrenshaSeijidantaiHistory39Repository;
    /** 関連者企業・団体履歴(40)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory40Repository kanrenshaSeijidantaiHistory40Repository;
    /** 関連者企業・団体履歴(41)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory41Repository kanrenshaSeijidantaiHistory41Repository;
    /** 関連者企業・団体履歴(42)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory42Repository kanrenshaSeijidantaiHistory42Repository;
    /** 関連者企業・団体履歴(43)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory43Repository kanrenshaSeijidantaiHistory43Repository;
    /** 関連者企業・団体履歴(44)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory44Repository kanrenshaSeijidantaiHistory44Repository;
    /** 関連者企業・団体履歴(45)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory45Repository kanrenshaSeijidantaiHistory45Repository;
    /** 関連者企業・団体履歴(46)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory46Repository kanrenshaSeijidantaiHistory46Repository;
    /** 関連者企業・団体履歴(47)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory47Repository kanrenshaSeijidantaiHistory47Repository;
    /** 関連者企業・団体履歴(99)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory99Repository kanrenshaSeijidantaiHistory99Repository;

    /** 全文検索検索対象テキスト作成Uttility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 処理を行う
     *
     * @param userDto    ユーザ最低限Dto
     * @param baseEntity 関連者企業・団体BaseEntity
     */
    public int practice( // NOPMD SUPPRESS CHECKSTYLE ReturnCount
            final LeastUserDto userDto, final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {

        baseEntity.setSearchText(formatNaturalSearchTextUtil
                .practice(baseEntity.getAllName() + baseEntity.getAllAddress() + baseEntity.getOrgDelegateName()));

        switch (getPrefectureLgCodeService.practice(baseEntity.getAllAddress())) {
            case GetPrefectureLgCodeService.PREF_01:
                return kanrenshaSeijidantaiHistory01Repository.save(this.createEntity01(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_02:
                return kanrenshaSeijidantaiHistory02Repository.save(this.createEntity02(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_03:
                return kanrenshaSeijidantaiHistory03Repository.save(this.createEntity03(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_04:
                return kanrenshaSeijidantaiHistory04Repository.save(this.createEntity04(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_05:
                return kanrenshaSeijidantaiHistory05Repository.save(this.createEntity05(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_06:
                return kanrenshaSeijidantaiHistory06Repository.save(this.createEntity06(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_07:
                return kanrenshaSeijidantaiHistory07Repository.save(this.createEntity07(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_08:
                return kanrenshaSeijidantaiHistory08Repository.save(this.createEntity08(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_09:
                return kanrenshaSeijidantaiHistory09Repository.save(this.createEntity09(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_10:
                return kanrenshaSeijidantaiHistory10Repository.save(this.createEntity10(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_11:
                return kanrenshaSeijidantaiHistory11Repository.save(this.createEntity11(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_12:
                return kanrenshaSeijidantaiHistory12Repository.save(this.createEntity12(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_13:
                return kanrenshaSeijidantaiHistory13Repository.save(this.createEntity13(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_14:
                return kanrenshaSeijidantaiHistory14Repository.save(this.createEntity14(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_15:
                return kanrenshaSeijidantaiHistory15Repository.save(this.createEntity15(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_16:
                return kanrenshaSeijidantaiHistory16Repository.save(this.createEntity16(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_17:
                return kanrenshaSeijidantaiHistory17Repository.save(this.createEntity17(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_18:
                return kanrenshaSeijidantaiHistory18Repository.save(this.createEntity18(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_19:
                return kanrenshaSeijidantaiHistory19Repository.save(this.createEntity19(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_20:
                return kanrenshaSeijidantaiHistory20Repository.save(this.createEntity20(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_21:
                return kanrenshaSeijidantaiHistory21Repository.save(this.createEntity21(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_22:
                return kanrenshaSeijidantaiHistory22Repository.save(this.createEntity22(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_23:
                return kanrenshaSeijidantaiHistory23Repository.save(this.createEntity23(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_24:
                return kanrenshaSeijidantaiHistory24Repository.save(this.createEntity24(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_25:
                return kanrenshaSeijidantaiHistory25Repository.save(this.createEntity25(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_26:
                return kanrenshaSeijidantaiHistory26Repository.save(this.createEntity26(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_27:
                return kanrenshaSeijidantaiHistory27Repository.save(this.createEntity27(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_28:
                return kanrenshaSeijidantaiHistory28Repository.save(this.createEntity28(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_29:
                return kanrenshaSeijidantaiHistory29Repository.save(this.createEntity29(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_30:
                return kanrenshaSeijidantaiHistory30Repository.save(this.createEntity30(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_31:
                return kanrenshaSeijidantaiHistory31Repository.save(this.createEntity31(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_32:
                return kanrenshaSeijidantaiHistory32Repository.save(this.createEntity32(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_33:
                return kanrenshaSeijidantaiHistory33Repository.save(this.createEntity33(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_34:
                return kanrenshaSeijidantaiHistory34Repository.save(this.createEntity34(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_35:
                return kanrenshaSeijidantaiHistory35Repository.save(this.createEntity35(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_36:
                return kanrenshaSeijidantaiHistory36Repository.save(this.createEntity36(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_37:
                return kanrenshaSeijidantaiHistory37Repository.save(this.createEntity37(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_38:
                return kanrenshaSeijidantaiHistory38Repository.save(this.createEntity38(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_39:
                return kanrenshaSeijidantaiHistory39Repository.save(this.createEntity39(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_40:
                return kanrenshaSeijidantaiHistory40Repository.save(this.createEntity40(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_41:
                return kanrenshaSeijidantaiHistory41Repository.save(this.createEntity41(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_42:
                return kanrenshaSeijidantaiHistory42Repository.save(this.createEntity42(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_43:
                return kanrenshaSeijidantaiHistory43Repository.save(this.createEntity43(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_44:
                return kanrenshaSeijidantaiHistory44Repository.save(this.createEntity44(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_45:
                return kanrenshaSeijidantaiHistory45Repository.save(this.createEntity45(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_46:
                return kanrenshaSeijidantaiHistory46Repository.save(this.createEntity46(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            case GetPrefectureLgCodeService.PREF_47:
                return kanrenshaSeijidantaiHistory47Repository.save(this.createEntity47(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
            default:
                return kanrenshaSeijidantaiHistory99Repository.save(this.createEntity99(userDto, baseEntity))
                        .getKanrenshaSeijidantaiHistoryId();
        }
    }

    private KanrenshaSeijidantaiHistory01Entity createEntity01(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory01Entity entity = new KanrenshaSeijidantaiHistory01Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory02Entity createEntity02(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory02Entity entity = new KanrenshaSeijidantaiHistory02Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory03Entity createEntity03(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory03Entity entity = new KanrenshaSeijidantaiHistory03Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory04Entity createEntity04(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory04Entity entity = new KanrenshaSeijidantaiHistory04Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory05Entity createEntity05(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory05Entity entity = new KanrenshaSeijidantaiHistory05Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory06Entity createEntity06(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory06Entity entity = new KanrenshaSeijidantaiHistory06Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory07Entity createEntity07(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory07Entity entity = new KanrenshaSeijidantaiHistory07Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory08Entity createEntity08(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory08Entity entity = new KanrenshaSeijidantaiHistory08Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory09Entity createEntity09(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory09Entity entity = new KanrenshaSeijidantaiHistory09Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory10Entity createEntity10(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory10Entity entity = new KanrenshaSeijidantaiHistory10Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory11Entity createEntity11(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory11Entity entity = new KanrenshaSeijidantaiHistory11Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory12Entity createEntity12(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory12Entity entity = new KanrenshaSeijidantaiHistory12Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory13Entity createEntity13(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory13Entity entity = new KanrenshaSeijidantaiHistory13Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory14Entity createEntity14(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory14Entity entity = new KanrenshaSeijidantaiHistory14Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory15Entity createEntity15(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory15Entity entity = new KanrenshaSeijidantaiHistory15Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory16Entity createEntity16(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory16Entity entity = new KanrenshaSeijidantaiHistory16Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory17Entity createEntity17(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory17Entity entity = new KanrenshaSeijidantaiHistory17Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory18Entity createEntity18(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory18Entity entity = new KanrenshaSeijidantaiHistory18Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory19Entity createEntity19(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory19Entity entity = new KanrenshaSeijidantaiHistory19Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory20Entity createEntity20(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory20Entity entity = new KanrenshaSeijidantaiHistory20Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory21Entity createEntity21(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory21Entity entity = new KanrenshaSeijidantaiHistory21Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory22Entity createEntity22(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory22Entity entity = new KanrenshaSeijidantaiHistory22Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory23Entity createEntity23(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory23Entity entity = new KanrenshaSeijidantaiHistory23Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory24Entity createEntity24(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory24Entity entity = new KanrenshaSeijidantaiHistory24Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory25Entity createEntity25(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory25Entity entity = new KanrenshaSeijidantaiHistory25Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory26Entity createEntity26(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory26Entity entity = new KanrenshaSeijidantaiHistory26Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory27Entity createEntity27(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory27Entity entity = new KanrenshaSeijidantaiHistory27Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory28Entity createEntity28(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory28Entity entity = new KanrenshaSeijidantaiHistory28Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory29Entity createEntity29(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory29Entity entity = new KanrenshaSeijidantaiHistory29Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory30Entity createEntity30(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory30Entity entity = new KanrenshaSeijidantaiHistory30Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory31Entity createEntity31(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory31Entity entity = new KanrenshaSeijidantaiHistory31Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory32Entity createEntity32(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory32Entity entity = new KanrenshaSeijidantaiHistory32Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory33Entity createEntity33(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory33Entity entity = new KanrenshaSeijidantaiHistory33Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory34Entity createEntity34(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory34Entity entity = new KanrenshaSeijidantaiHistory34Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory35Entity createEntity35(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory35Entity entity = new KanrenshaSeijidantaiHistory35Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory36Entity createEntity36(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory36Entity entity = new KanrenshaSeijidantaiHistory36Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory37Entity createEntity37(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory37Entity entity = new KanrenshaSeijidantaiHistory37Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory38Entity createEntity38(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory38Entity entity = new KanrenshaSeijidantaiHistory38Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory39Entity createEntity39(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory39Entity entity = new KanrenshaSeijidantaiHistory39Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory40Entity createEntity40(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory40Entity entity = new KanrenshaSeijidantaiHistory40Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory41Entity createEntity41(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory41Entity entity = new KanrenshaSeijidantaiHistory41Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory42Entity createEntity42(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory42Entity entity = new KanrenshaSeijidantaiHistory42Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory43Entity createEntity43(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory43Entity entity = new KanrenshaSeijidantaiHistory43Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory44Entity createEntity44(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory44Entity entity = new KanrenshaSeijidantaiHistory44Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory45Entity createEntity45(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory45Entity entity = new KanrenshaSeijidantaiHistory45Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory46Entity createEntity46(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory46Entity entity = new KanrenshaSeijidantaiHistory46Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory47Entity createEntity47(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory47Entity entity = new KanrenshaSeijidantaiHistory47Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }

    private KanrenshaSeijidantaiHistory99Entity createEntity99(final LeastUserDto userDto,
            final KanrenshaSeijidantaiHistoryBaseEntity baseEntity) {
        KanrenshaSeijidantaiHistory99Entity entity = new KanrenshaSeijidantaiHistory99Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setKanrenshaSeijidantaiHistoryId(0); // auto_increment明示
        return entity;
    }
}
