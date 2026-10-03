package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import org.springframework.beans.BeanUtils; // NOPMD HighImports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeAcceptCapsuleDto;
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

/**
 * 関連者企業・団体コード移動履歴Service
 */
@Service
public class MoveCodeHistoryKigyouDtService { // NOPMD TooManyFields

    /** 関連者企業・団体履歴01Repository */
    @Autowired
    private KanrenshaKigyouDtHistory01Repository kanrenshaKigyouDtHistory01Repository;

    /** 関連者企業・団体履歴02Repository */
    @Autowired
    private KanrenshaKigyouDtHistory02Repository kanrenshaKigyouDtHistory02Repository;

    /** 関連者企業・団体履歴03Repository */
    @Autowired
    private KanrenshaKigyouDtHistory03Repository kanrenshaKigyouDtHistory03Repository;

    /** 関連者企業・団体履歴04Repository */
    @Autowired
    private KanrenshaKigyouDtHistory04Repository kanrenshaKigyouDtHistory04Repository;

    /** 関連者企業・団体履歴05Repository */
    @Autowired
    private KanrenshaKigyouDtHistory05Repository kanrenshaKigyouDtHistory05Repository;

    /** 関連者企業・団体履歴06Repository */
    @Autowired
    private KanrenshaKigyouDtHistory06Repository kanrenshaKigyouDtHistory06Repository;

    /** 関連者企業・団体履歴07Repository */
    @Autowired
    private KanrenshaKigyouDtHistory07Repository kanrenshaKigyouDtHistory07Repository;

    /** 関連者企業・団体履歴08Repository */
    @Autowired
    private KanrenshaKigyouDtHistory08Repository kanrenshaKigyouDtHistory08Repository;

    /** 関連者企業・団体履歴09Repository */
    @Autowired
    private KanrenshaKigyouDtHistory09Repository kanrenshaKigyouDtHistory09Repository;

    /** 関連者企業・団体履歴10Repository */
    @Autowired
    private KanrenshaKigyouDtHistory10Repository kanrenshaKigyouDtHistory10Repository;

    /** 関連者企業・団体履歴11Repository */
    @Autowired
    private KanrenshaKigyouDtHistory11Repository kanrenshaKigyouDtHistory11Repository;

    /** 関連者企業・団体履歴12Repository */
    @Autowired
    private KanrenshaKigyouDtHistory12Repository kanrenshaKigyouDtHistory12Repository;

    /** 関連者企業・団体履歴13Repository */
    @Autowired
    private KanrenshaKigyouDtHistory13Repository kanrenshaKigyouDtHistory13Repository;

    /** 関連者企業・団体履歴14Repository */
    @Autowired
    private KanrenshaKigyouDtHistory14Repository kanrenshaKigyouDtHistory14Repository;

    /** 関連者企業・団体履歴15Repository */
    @Autowired
    private KanrenshaKigyouDtHistory15Repository kanrenshaKigyouDtHistory15Repository;

    /** 関連者企業・団体履歴16Repository */
    @Autowired
    private KanrenshaKigyouDtHistory16Repository kanrenshaKigyouDtHistory16Repository;

    /** 関連者企業・団体履歴17Repository */
    @Autowired
    private KanrenshaKigyouDtHistory17Repository kanrenshaKigyouDtHistory17Repository;

    /** 関連者企業・団体履歴18Repository */
    @Autowired
    private KanrenshaKigyouDtHistory18Repository kanrenshaKigyouDtHistory18Repository;

    /** 関連者企業・団体履歴19Repository */
    @Autowired
    private KanrenshaKigyouDtHistory19Repository kanrenshaKigyouDtHistory19Repository;

    /** 関連者企業・団体履歴20Repository */
    @Autowired
    private KanrenshaKigyouDtHistory20Repository kanrenshaKigyouDtHistory20Repository;

    /** 関連者企業・団体履歴21Repository */
    @Autowired
    private KanrenshaKigyouDtHistory21Repository kanrenshaKigyouDtHistory21Repository;

    /** 関連者企業・団体履歴22Repository */
    @Autowired
    private KanrenshaKigyouDtHistory22Repository kanrenshaKigyouDtHistory22Repository;

    /** 関連者企業・団体履歴23Repository */
    @Autowired
    private KanrenshaKigyouDtHistory23Repository kanrenshaKigyouDtHistory23Repository;

    /** 関連者企業・団体履歴24Repository */
    @Autowired
    private KanrenshaKigyouDtHistory24Repository kanrenshaKigyouDtHistory24Repository;

    /** 関連者企業・団体履歴25Repository */
    @Autowired
    private KanrenshaKigyouDtHistory25Repository kanrenshaKigyouDtHistory25Repository;

    /** 関連者企業・団体履歴26Repository */
    @Autowired
    private KanrenshaKigyouDtHistory26Repository kanrenshaKigyouDtHistory26Repository;

    /** 関連者企業・団体履歴27Repository */
    @Autowired
    private KanrenshaKigyouDtHistory27Repository kanrenshaKigyouDtHistory27Repository;

    /** 関連者企業・団体履歴28Repository */
    @Autowired
    private KanrenshaKigyouDtHistory28Repository kanrenshaKigyouDtHistory28Repository;

    /** 関連者企業・団体履歴29Repository */
    @Autowired
    private KanrenshaKigyouDtHistory29Repository kanrenshaKigyouDtHistory29Repository;

    /** 関連者企業・団体履歴30Repository */
    @Autowired
    private KanrenshaKigyouDtHistory30Repository kanrenshaKigyouDtHistory30Repository;

    /** 関連者企業・団体履歴31Repository */
    @Autowired
    private KanrenshaKigyouDtHistory31Repository kanrenshaKigyouDtHistory31Repository;

    /** 関連者企業・団体履歴32Repository */
    @Autowired
    private KanrenshaKigyouDtHistory32Repository kanrenshaKigyouDtHistory32Repository;

    /** 関連者企業・団体履歴33Repository */
    @Autowired
    private KanrenshaKigyouDtHistory33Repository kanrenshaKigyouDtHistory33Repository;

    /** 関連者企業・団体履歴34Repository */
    @Autowired
    private KanrenshaKigyouDtHistory34Repository kanrenshaKigyouDtHistory34Repository;

    /** 関連者企業・団体履歴35Repository */
    @Autowired
    private KanrenshaKigyouDtHistory35Repository kanrenshaKigyouDtHistory35Repository;

    /** 関連者企業・団体履歴36Repository */
    @Autowired
    private KanrenshaKigyouDtHistory36Repository kanrenshaKigyouDtHistory36Repository;

    /** 関連者企業・団体履歴37Repository */
    @Autowired
    private KanrenshaKigyouDtHistory37Repository kanrenshaKigyouDtHistory37Repository;

    /** 関連者企業・団体履歴38Repository */
    @Autowired
    private KanrenshaKigyouDtHistory38Repository kanrenshaKigyouDtHistory38Repository;

    /** 関連者企業・団体履歴39Repository */
    @Autowired
    private KanrenshaKigyouDtHistory39Repository kanrenshaKigyouDtHistory39Repository;

    /** 関連者企業・団体履歴40Repository */
    @Autowired
    private KanrenshaKigyouDtHistory40Repository kanrenshaKigyouDtHistory40Repository;

    /** 関連者企業・団体履歴41Repository */
    @Autowired
    private KanrenshaKigyouDtHistory41Repository kanrenshaKigyouDtHistory41Repository;

    /** 関連者企業・団体履歴42Repository */
    @Autowired
    private KanrenshaKigyouDtHistory42Repository kanrenshaKigyouDtHistory42Repository;

    /** 関連者企業・団体履歴43Repository */
    @Autowired
    private KanrenshaKigyouDtHistory43Repository kanrenshaKigyouDtHistory43Repository;

    /** 関連者企業・団体履歴44Repository */
    @Autowired
    private KanrenshaKigyouDtHistory44Repository kanrenshaKigyouDtHistory44Repository;

    /** 関連者企業・団体履歴45Repository */
    @Autowired
    private KanrenshaKigyouDtHistory45Repository kanrenshaKigyouDtHistory45Repository;

    /** 関連者企業・団体履歴46Repository */
    @Autowired
    private KanrenshaKigyouDtHistory46Repository kanrenshaKigyouDtHistory46Repository;

    /** 関連者企業・団体履歴47Repository */
    @Autowired
    private KanrenshaKigyouDtHistory47Repository kanrenshaKigyouDtHistory47Repository;

    /** 関連者企業・団体履歴99Repository */
    @Autowired
    private KanrenshaKigyouDtHistory99Repository kanrenshaKigyouDtHistory99Repository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 処理条件Dto
     * @return 処理結果
     */
    public boolean practice(final MoveKanrenshaCodeAcceptCapsuleDto capsuleDto) {
        // すべての作業を一貫して行い、できない場合は巻き戻しが必要なのでここで@Transactionalは使用しない

        LeastUserDto userDto = capsuleDto.getUserDto();
        String orgCode = capsuleDto.getKanrenshaCodeMoveEntity().getOriginKanrenshaCode();
        String abolishCode = capsuleDto.getKanrenshaCodeMoveEntity().getAbolishKanrenshaCode();

        // 必ず全地域を走査する
        this.execute01(userDto, orgCode, abolishCode);
        this.execute02(userDto, orgCode, abolishCode);
        this.execute03(userDto, orgCode, abolishCode);
        this.execute04(userDto, orgCode, abolishCode);
        this.execute05(userDto, orgCode, abolishCode);
        this.execute06(userDto, orgCode, abolishCode);
        this.execute07(userDto, orgCode, abolishCode);
        this.execute08(userDto, orgCode, abolishCode);
        this.execute09(userDto, orgCode, abolishCode);
        this.execute10(userDto, orgCode, abolishCode);
        this.execute11(userDto, orgCode, abolishCode);
        this.execute12(userDto, orgCode, abolishCode);
        this.execute13(userDto, orgCode, abolishCode);
        this.execute14(userDto, orgCode, abolishCode);
        this.execute15(userDto, orgCode, abolishCode);
        this.execute16(userDto, orgCode, abolishCode);
        this.execute17(userDto, orgCode, abolishCode);
        this.execute01(userDto, orgCode, abolishCode);
        this.execute18(userDto, orgCode, abolishCode);
        this.execute19(userDto, orgCode, abolishCode);
        this.execute20(userDto, orgCode, abolishCode);
        this.execute21(userDto, orgCode, abolishCode);
        this.execute22(userDto, orgCode, abolishCode);
        this.execute23(userDto, orgCode, abolishCode);
        this.execute24(userDto, orgCode, abolishCode);
        this.execute25(userDto, orgCode, abolishCode);
        this.execute26(userDto, orgCode, abolishCode);
        this.execute27(userDto, orgCode, abolishCode);
        this.execute28(userDto, orgCode, abolishCode);
        this.execute29(userDto, orgCode, abolishCode);
        this.execute30(userDto, orgCode, abolishCode);
        this.execute31(userDto, orgCode, abolishCode);
        this.execute32(userDto, orgCode, abolishCode);
        this.execute33(userDto, orgCode, abolishCode);
        this.execute34(userDto, orgCode, abolishCode);
        this.execute35(userDto, orgCode, abolishCode);
        this.execute36(userDto, orgCode, abolishCode);
        this.execute37(userDto, orgCode, abolishCode);
        this.execute38(userDto, orgCode, abolishCode);
        this.execute39(userDto, orgCode, abolishCode);
        this.execute40(userDto, orgCode, abolishCode);
        this.execute41(userDto, orgCode, abolishCode);
        this.execute42(userDto, orgCode, abolishCode);
        this.execute43(userDto, orgCode, abolishCode);
        this.execute44(userDto, orgCode, abolishCode);
        this.execute45(userDto, orgCode, abolishCode);
        this.execute46(userDto, orgCode, abolishCode);
        this.execute47(userDto, orgCode, abolishCode);
        this.execute99(userDto, orgCode, abolishCode);

        return true;
    }

    private void execute01(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        // 履歴は過去参照があるので、絶対に過去データでも使わないと確信が持てないと削除できない(はず)
        // したがって削除の考慮は必要ない
        for (KanrenshaKigyouDtHistory01Entity srcEntity : kanrenshaKigyouDtHistory01Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory01Entity newEntity = new KanrenshaKigyouDtHistory01Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory01Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory01Repository.save(newEntity);
        }
    }

    private void execute02(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory02Entity srcEntity : kanrenshaKigyouDtHistory02Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory02Entity newEntity = new KanrenshaKigyouDtHistory02Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory02Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory02Repository.save(newEntity);
        }
    }

    private void execute03(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory03Entity srcEntity : kanrenshaKigyouDtHistory03Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory03Entity newEntity = new KanrenshaKigyouDtHistory03Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory03Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory03Repository.save(newEntity);
        }
    }

    private void execute04(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory04Entity srcEntity : kanrenshaKigyouDtHistory04Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory04Entity newEntity = new KanrenshaKigyouDtHistory04Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory04Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory04Repository.save(newEntity);
        }
    }

    private void execute05(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory05Entity srcEntity : kanrenshaKigyouDtHistory05Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory05Entity newEntity = new KanrenshaKigyouDtHistory05Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory05Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory05Repository.save(newEntity);
        }
    }

    private void execute06(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory06Entity srcEntity : kanrenshaKigyouDtHistory06Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory06Entity newEntity = new KanrenshaKigyouDtHistory06Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory06Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory06Repository.save(newEntity);
        }
    }

    private void execute07(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory07Entity srcEntity : kanrenshaKigyouDtHistory07Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory07Entity newEntity = new KanrenshaKigyouDtHistory07Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory07Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory07Repository.save(newEntity);
        }
    }

    private void execute08(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory08Entity srcEntity : kanrenshaKigyouDtHistory08Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory08Entity newEntity = new KanrenshaKigyouDtHistory08Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory08Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory08Repository.save(newEntity);
        }
    }

    private void execute09(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory09Entity srcEntity : kanrenshaKigyouDtHistory09Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory09Entity newEntity = new KanrenshaKigyouDtHistory09Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory09Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory09Repository.save(newEntity);
        }
    }

    private void execute10(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory10Entity srcEntity : kanrenshaKigyouDtHistory10Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory10Entity newEntity = new KanrenshaKigyouDtHistory10Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory10Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory10Repository.save(newEntity);
        }
    }

    private void execute11(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory11Entity srcEntity : kanrenshaKigyouDtHistory11Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory11Entity newEntity = new KanrenshaKigyouDtHistory11Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory11Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory11Repository.save(newEntity);
        }
    }

    private void execute12(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory12Entity srcEntity : kanrenshaKigyouDtHistory12Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory12Entity newEntity = new KanrenshaKigyouDtHistory12Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory12Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory12Repository.save(newEntity);
        }
    }

    private void execute13(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory13Entity srcEntity : kanrenshaKigyouDtHistory13Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory13Entity newEntity = new KanrenshaKigyouDtHistory13Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory13Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory13Repository.save(newEntity);
        }
    }

    private void execute14(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory14Entity srcEntity : kanrenshaKigyouDtHistory14Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory14Entity newEntity = new KanrenshaKigyouDtHistory14Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory14Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory14Repository.save(newEntity);
        }
    }

    private void execute15(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory15Entity srcEntity : kanrenshaKigyouDtHistory15Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory15Entity newEntity = new KanrenshaKigyouDtHistory15Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory15Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory15Repository.save(newEntity);
        }
    }

    private void execute16(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory16Entity srcEntity : kanrenshaKigyouDtHistory16Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory16Entity newEntity = new KanrenshaKigyouDtHistory16Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory16Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory16Repository.save(newEntity);
        }
    }

    private void execute17(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory17Entity srcEntity : kanrenshaKigyouDtHistory17Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory17Entity newEntity = new KanrenshaKigyouDtHistory17Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory17Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory17Repository.save(newEntity);
        }
    }

    private void execute18(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory18Entity srcEntity : kanrenshaKigyouDtHistory18Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory18Entity newEntity = new KanrenshaKigyouDtHistory18Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory18Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory18Repository.save(newEntity);
        }
    }

    private void execute19(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory19Entity srcEntity : kanrenshaKigyouDtHistory19Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory19Entity newEntity = new KanrenshaKigyouDtHistory19Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory19Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory19Repository.save(newEntity);
        }
    }

    private void execute20(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory20Entity srcEntity : kanrenshaKigyouDtHistory20Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory20Entity newEntity = new KanrenshaKigyouDtHistory20Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory20Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory20Repository.save(newEntity);
        }
    }

    private void execute21(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory21Entity srcEntity : kanrenshaKigyouDtHistory21Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory21Entity newEntity = new KanrenshaKigyouDtHistory21Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory21Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory21Repository.save(newEntity);
        }
    }

    private void execute22(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory22Entity srcEntity : kanrenshaKigyouDtHistory22Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory22Entity newEntity = new KanrenshaKigyouDtHistory22Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory22Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory22Repository.save(newEntity);
        }
    }

    private void execute23(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory23Entity srcEntity : kanrenshaKigyouDtHistory23Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory23Entity newEntity = new KanrenshaKigyouDtHistory23Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory23Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory23Repository.save(newEntity);
        }
    }

    private void execute24(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory24Entity srcEntity : kanrenshaKigyouDtHistory24Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory24Entity newEntity = new KanrenshaKigyouDtHistory24Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory24Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory24Repository.save(newEntity);
        }
    }

    private void execute25(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory25Entity srcEntity : kanrenshaKigyouDtHistory25Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory25Entity newEntity = new KanrenshaKigyouDtHistory25Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory25Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory25Repository.save(newEntity);
        }
    }

    private void execute26(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory26Entity srcEntity : kanrenshaKigyouDtHistory26Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory26Entity newEntity = new KanrenshaKigyouDtHistory26Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory26Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory26Repository.save(newEntity);
        }
    }

    private void execute27(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory27Entity srcEntity : kanrenshaKigyouDtHistory27Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory27Entity newEntity = new KanrenshaKigyouDtHistory27Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory27Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory27Repository.save(newEntity);
        }
    }

    private void execute28(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory28Entity srcEntity : kanrenshaKigyouDtHistory28Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory28Entity newEntity = new KanrenshaKigyouDtHistory28Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory28Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory28Repository.save(newEntity);
        }
    }

    private void execute29(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory29Entity srcEntity : kanrenshaKigyouDtHistory29Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory29Entity newEntity = new KanrenshaKigyouDtHistory29Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory29Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory29Repository.save(newEntity);
        }
    }

    private void execute30(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory30Entity srcEntity : kanrenshaKigyouDtHistory30Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory30Entity newEntity = new KanrenshaKigyouDtHistory30Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory30Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory30Repository.save(newEntity);
        }
    }

    private void execute31(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory31Entity srcEntity : kanrenshaKigyouDtHistory31Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory31Entity newEntity = new KanrenshaKigyouDtHistory31Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory31Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory31Repository.save(newEntity);
        }
    }

    private void execute32(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory32Entity srcEntity : kanrenshaKigyouDtHistory32Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory32Entity newEntity = new KanrenshaKigyouDtHistory32Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory32Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory32Repository.save(newEntity);
        }
    }

    private void execute33(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory33Entity srcEntity : kanrenshaKigyouDtHistory33Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory33Entity newEntity = new KanrenshaKigyouDtHistory33Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory33Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory33Repository.save(newEntity);
        }
    }

    private void execute34(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory34Entity srcEntity : kanrenshaKigyouDtHistory34Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory34Entity newEntity = new KanrenshaKigyouDtHistory34Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory34Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory34Repository.save(newEntity);
        }
    }

    private void execute35(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory35Entity srcEntity : kanrenshaKigyouDtHistory35Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory35Entity newEntity = new KanrenshaKigyouDtHistory35Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory35Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory35Repository.save(newEntity);
        }
    }

    private void execute36(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory36Entity srcEntity : kanrenshaKigyouDtHistory36Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory36Entity newEntity = new KanrenshaKigyouDtHistory36Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory36Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory36Repository.save(newEntity);
        }
    }

    private void execute37(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory37Entity srcEntity : kanrenshaKigyouDtHistory37Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory37Entity newEntity = new KanrenshaKigyouDtHistory37Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory37Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory37Repository.save(newEntity);
        }
    }

    private void execute38(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory38Entity srcEntity : kanrenshaKigyouDtHistory38Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory38Entity newEntity = new KanrenshaKigyouDtHistory38Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory38Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory38Repository.save(newEntity);
        }
    }

    private void execute39(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory39Entity srcEntity : kanrenshaKigyouDtHistory39Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory39Entity newEntity = new KanrenshaKigyouDtHistory39Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory39Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory39Repository.save(newEntity);
        }
    }

    private void execute40(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory40Entity srcEntity : kanrenshaKigyouDtHistory40Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory40Entity newEntity = new KanrenshaKigyouDtHistory40Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory40Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory40Repository.save(newEntity);
        }
    }

    private void execute41(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory41Entity srcEntity : kanrenshaKigyouDtHistory41Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory41Entity newEntity = new KanrenshaKigyouDtHistory41Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory41Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory41Repository.save(newEntity);
        }
    }

    private void execute42(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory42Entity srcEntity : kanrenshaKigyouDtHistory42Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory42Entity newEntity = new KanrenshaKigyouDtHistory42Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory42Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory42Repository.save(newEntity);
        }
    }

    private void execute43(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory43Entity srcEntity : kanrenshaKigyouDtHistory43Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory43Entity newEntity = new KanrenshaKigyouDtHistory43Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory43Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory43Repository.save(newEntity);
        }
    }

    private void execute44(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory44Entity srcEntity : kanrenshaKigyouDtHistory44Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory44Entity newEntity = new KanrenshaKigyouDtHistory44Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory44Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory44Repository.save(newEntity);
        }
    }

    private void execute45(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory45Entity srcEntity : kanrenshaKigyouDtHistory45Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory45Entity newEntity = new KanrenshaKigyouDtHistory45Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory45Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory45Repository.save(newEntity);
        }
    }

    private void execute46(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory46Entity srcEntity : kanrenshaKigyouDtHistory46Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory46Entity newEntity = new KanrenshaKigyouDtHistory46Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory46Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory46Repository.save(newEntity);
        }
    }

    private void execute47(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory47Entity srcEntity : kanrenshaKigyouDtHistory47Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory47Entity newEntity = new KanrenshaKigyouDtHistory47Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory47Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory47Repository.save(newEntity);
        }
    }

    private void execute99(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaKigyouDtHistory99Entity srcEntity : kanrenshaKigyouDtHistory99Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaKigyouDtHistory99Entity newEntity = new KanrenshaKigyouDtHistory99Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setKigyouDtKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaKigyouDtHistory99Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaKigyouDtHistoryId(0); // auto increment明記
            kanrenshaKigyouDtHistory99Repository.save(newEntity);
        }
    }

}
