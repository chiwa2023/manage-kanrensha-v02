package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import org.springframework.beans.BeanUtils; // NOPMD HighImports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeAcceptCapsuleDto;
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

/**
 * 関連者政治団体コード移動履歴Service
 */
@Service
public class MoveCodeHistorySeijidantaiService { // NOPMD TooManyFields

    /** 関連者政治団体履歴01Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory01Repository kanrenshaSeijidantaiHistory01Repository;

    /** 関連者政治団体履歴02Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory02Repository kanrenshaSeijidantaiHistory02Repository;

    /** 関連者政治団体履歴03Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory03Repository kanrenshaSeijidantaiHistory03Repository;

    /** 関連者政治団体履歴04Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory04Repository kanrenshaSeijidantaiHistory04Repository;

    /** 関連者政治団体履歴05Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory05Repository kanrenshaSeijidantaiHistory05Repository;

    /** 関連者政治団体履歴06Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory06Repository kanrenshaSeijidantaiHistory06Repository;

    /** 関連者政治団体履歴07Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory07Repository kanrenshaSeijidantaiHistory07Repository;

    /** 関連者政治団体履歴08Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory08Repository kanrenshaSeijidantaiHistory08Repository;

    /** 関連者政治団体履歴09Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory09Repository kanrenshaSeijidantaiHistory09Repository;

    /** 関連者政治団体履歴10Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory10Repository kanrenshaSeijidantaiHistory10Repository;

    /** 関連者政治団体履歴11Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory11Repository kanrenshaSeijidantaiHistory11Repository;

    /** 関連者政治団体履歴12Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory12Repository kanrenshaSeijidantaiHistory12Repository;

    /** 関連者政治団体履歴13Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory13Repository kanrenshaSeijidantaiHistory13Repository;

    /** 関連者政治団体履歴14Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory14Repository kanrenshaSeijidantaiHistory14Repository;

    /** 関連者政治団体履歴15Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory15Repository kanrenshaSeijidantaiHistory15Repository;

    /** 関連者政治団体履歴16Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory16Repository kanrenshaSeijidantaiHistory16Repository;

    /** 関連者政治団体履歴17Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory17Repository kanrenshaSeijidantaiHistory17Repository;

    /** 関連者政治団体履歴18Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory18Repository kanrenshaSeijidantaiHistory18Repository;

    /** 関連者政治団体履歴19Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory19Repository kanrenshaSeijidantaiHistory19Repository;

    /** 関連者政治団体履歴20Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory20Repository kanrenshaSeijidantaiHistory20Repository;

    /** 関連者政治団体履歴21Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory21Repository kanrenshaSeijidantaiHistory21Repository;

    /** 関連者政治団体履歴22Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory22Repository kanrenshaSeijidantaiHistory22Repository;

    /** 関連者政治団体履歴23Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory23Repository kanrenshaSeijidantaiHistory23Repository;

    /** 関連者政治団体履歴24Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory24Repository kanrenshaSeijidantaiHistory24Repository;

    /** 関連者政治団体履歴25Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory25Repository kanrenshaSeijidantaiHistory25Repository;

    /** 関連者政治団体履歴26Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory26Repository kanrenshaSeijidantaiHistory26Repository;

    /** 関連者政治団体履歴27Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory27Repository kanrenshaSeijidantaiHistory27Repository;

    /** 関連者政治団体履歴28Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory28Repository kanrenshaSeijidantaiHistory28Repository;

    /** 関連者政治団体履歴29Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory29Repository kanrenshaSeijidantaiHistory29Repository;

    /** 関連者政治団体履歴30Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory30Repository kanrenshaSeijidantaiHistory30Repository;

    /** 関連者政治団体履歴31Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory31Repository kanrenshaSeijidantaiHistory31Repository;

    /** 関連者政治団体履歴32Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory32Repository kanrenshaSeijidantaiHistory32Repository;

    /** 関連者政治団体履歴33Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory33Repository kanrenshaSeijidantaiHistory33Repository;

    /** 関連者政治団体履歴34Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory34Repository kanrenshaSeijidantaiHistory34Repository;

    /** 関連者政治団体履歴35Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory35Repository kanrenshaSeijidantaiHistory35Repository;

    /** 関連者政治団体履歴36Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory36Repository kanrenshaSeijidantaiHistory36Repository;

    /** 関連者政治団体履歴37Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory37Repository kanrenshaSeijidantaiHistory37Repository;

    /** 関連者政治団体履歴38Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory38Repository kanrenshaSeijidantaiHistory38Repository;

    /** 関連者政治団体履歴39Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory39Repository kanrenshaSeijidantaiHistory39Repository;

    /** 関連者政治団体履歴40Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory40Repository kanrenshaSeijidantaiHistory40Repository;

    /** 関連者政治団体履歴41Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory41Repository kanrenshaSeijidantaiHistory41Repository;

    /** 関連者政治団体履歴42Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory42Repository kanrenshaSeijidantaiHistory42Repository;

    /** 関連者政治団体履歴43Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory43Repository kanrenshaSeijidantaiHistory43Repository;

    /** 関連者政治団体履歴44Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory44Repository kanrenshaSeijidantaiHistory44Repository;

    /** 関連者政治団体履歴45Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory45Repository kanrenshaSeijidantaiHistory45Repository;

    /** 関連者政治団体履歴46Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory46Repository kanrenshaSeijidantaiHistory46Repository;

    /** 関連者政治団体履歴47Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory47Repository kanrenshaSeijidantaiHistory47Repository;

    /** 関連者政治団体履歴99Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory99Repository kanrenshaSeijidantaiHistory99Repository;

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
        for (KanrenshaSeijidantaiHistory01Entity srcEntity : kanrenshaSeijidantaiHistory01Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory01Entity newEntity = new KanrenshaSeijidantaiHistory01Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory01Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory01Repository.save(newEntity);
        }
    }

    private void execute02(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory02Entity srcEntity : kanrenshaSeijidantaiHistory02Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory02Entity newEntity = new KanrenshaSeijidantaiHistory02Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory02Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory02Repository.save(newEntity);
        }
    }

    private void execute03(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory03Entity srcEntity : kanrenshaSeijidantaiHistory03Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory03Entity newEntity = new KanrenshaSeijidantaiHistory03Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory03Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory03Repository.save(newEntity);
        }
    }

    private void execute04(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory04Entity srcEntity : kanrenshaSeijidantaiHistory04Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory04Entity newEntity = new KanrenshaSeijidantaiHistory04Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory04Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory04Repository.save(newEntity);
        }
    }

    private void execute05(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory05Entity srcEntity : kanrenshaSeijidantaiHistory05Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory05Entity newEntity = new KanrenshaSeijidantaiHistory05Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory05Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory05Repository.save(newEntity);
        }
    }

    private void execute06(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory06Entity srcEntity : kanrenshaSeijidantaiHistory06Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory06Entity newEntity = new KanrenshaSeijidantaiHistory06Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory06Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory06Repository.save(newEntity);
        }
    }

    private void execute07(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory07Entity srcEntity : kanrenshaSeijidantaiHistory07Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory07Entity newEntity = new KanrenshaSeijidantaiHistory07Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory07Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory07Repository.save(newEntity);
        }
    }

    private void execute08(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory08Entity srcEntity : kanrenshaSeijidantaiHistory08Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory08Entity newEntity = new KanrenshaSeijidantaiHistory08Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory08Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory08Repository.save(newEntity);
        }
    }

    private void execute09(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory09Entity srcEntity : kanrenshaSeijidantaiHistory09Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory09Entity newEntity = new KanrenshaSeijidantaiHistory09Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory09Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory09Repository.save(newEntity);
        }
    }

    private void execute10(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory10Entity srcEntity : kanrenshaSeijidantaiHistory10Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory10Entity newEntity = new KanrenshaSeijidantaiHistory10Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory10Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory10Repository.save(newEntity);
        }
    }

    private void execute11(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory11Entity srcEntity : kanrenshaSeijidantaiHistory11Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory11Entity newEntity = new KanrenshaSeijidantaiHistory11Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory11Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory11Repository.save(newEntity);
        }
    }

    private void execute12(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory12Entity srcEntity : kanrenshaSeijidantaiHistory12Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory12Entity newEntity = new KanrenshaSeijidantaiHistory12Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory12Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory12Repository.save(newEntity);
        }
    }

    private void execute13(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory13Entity srcEntity : kanrenshaSeijidantaiHistory13Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory13Entity newEntity = new KanrenshaSeijidantaiHistory13Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory13Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory13Repository.save(newEntity);
        }
    }

    private void execute14(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory14Entity srcEntity : kanrenshaSeijidantaiHistory14Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory14Entity newEntity = new KanrenshaSeijidantaiHistory14Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory14Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory14Repository.save(newEntity);
        }
    }

    private void execute15(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory15Entity srcEntity : kanrenshaSeijidantaiHistory15Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory15Entity newEntity = new KanrenshaSeijidantaiHistory15Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory15Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory15Repository.save(newEntity);
        }
    }

    private void execute16(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory16Entity srcEntity : kanrenshaSeijidantaiHistory16Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory16Entity newEntity = new KanrenshaSeijidantaiHistory16Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory16Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory16Repository.save(newEntity);
        }
    }

    private void execute17(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory17Entity srcEntity : kanrenshaSeijidantaiHistory17Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory17Entity newEntity = new KanrenshaSeijidantaiHistory17Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory17Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory17Repository.save(newEntity);
        }
    }

    private void execute18(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory18Entity srcEntity : kanrenshaSeijidantaiHistory18Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory18Entity newEntity = new KanrenshaSeijidantaiHistory18Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory18Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory18Repository.save(newEntity);
        }
    }

    private void execute19(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory19Entity srcEntity : kanrenshaSeijidantaiHistory19Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory19Entity newEntity = new KanrenshaSeijidantaiHistory19Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory19Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory19Repository.save(newEntity);
        }
    }

    private void execute20(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory20Entity srcEntity : kanrenshaSeijidantaiHistory20Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory20Entity newEntity = new KanrenshaSeijidantaiHistory20Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory20Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory20Repository.save(newEntity);
        }
    }

    private void execute21(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory21Entity srcEntity : kanrenshaSeijidantaiHistory21Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory21Entity newEntity = new KanrenshaSeijidantaiHistory21Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory21Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory21Repository.save(newEntity);
        }
    }

    private void execute22(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory22Entity srcEntity : kanrenshaSeijidantaiHistory22Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory22Entity newEntity = new KanrenshaSeijidantaiHistory22Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory22Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory22Repository.save(newEntity);
        }
    }

    private void execute23(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory23Entity srcEntity : kanrenshaSeijidantaiHistory23Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory23Entity newEntity = new KanrenshaSeijidantaiHistory23Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory23Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory23Repository.save(newEntity);
        }
    }

    private void execute24(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory24Entity srcEntity : kanrenshaSeijidantaiHistory24Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory24Entity newEntity = new KanrenshaSeijidantaiHistory24Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory24Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory24Repository.save(newEntity);
        }
    }

    private void execute25(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory25Entity srcEntity : kanrenshaSeijidantaiHistory25Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory25Entity newEntity = new KanrenshaSeijidantaiHistory25Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory25Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory25Repository.save(newEntity);
        }
    }

    private void execute26(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory26Entity srcEntity : kanrenshaSeijidantaiHistory26Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory26Entity newEntity = new KanrenshaSeijidantaiHistory26Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory26Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory26Repository.save(newEntity);
        }
    }

    private void execute27(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory27Entity srcEntity : kanrenshaSeijidantaiHistory27Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory27Entity newEntity = new KanrenshaSeijidantaiHistory27Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory27Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory27Repository.save(newEntity);
        }
    }

    private void execute28(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory28Entity srcEntity : kanrenshaSeijidantaiHistory28Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory28Entity newEntity = new KanrenshaSeijidantaiHistory28Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory28Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory28Repository.save(newEntity);
        }
    }

    private void execute29(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory29Entity srcEntity : kanrenshaSeijidantaiHistory29Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory29Entity newEntity = new KanrenshaSeijidantaiHistory29Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory29Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory29Repository.save(newEntity);
        }
    }

    private void execute30(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory30Entity srcEntity : kanrenshaSeijidantaiHistory30Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory30Entity newEntity = new KanrenshaSeijidantaiHistory30Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory30Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory30Repository.save(newEntity);
        }
    }

    private void execute31(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory31Entity srcEntity : kanrenshaSeijidantaiHistory31Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory31Entity newEntity = new KanrenshaSeijidantaiHistory31Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory31Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory31Repository.save(newEntity);
        }
    }

    private void execute32(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory32Entity srcEntity : kanrenshaSeijidantaiHistory32Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory32Entity newEntity = new KanrenshaSeijidantaiHistory32Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory32Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory32Repository.save(newEntity);
        }
    }

    private void execute33(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory33Entity srcEntity : kanrenshaSeijidantaiHistory33Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory33Entity newEntity = new KanrenshaSeijidantaiHistory33Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory33Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory33Repository.save(newEntity);
        }
    }

    private void execute34(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory34Entity srcEntity : kanrenshaSeijidantaiHistory34Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory34Entity newEntity = new KanrenshaSeijidantaiHistory34Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory34Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory34Repository.save(newEntity);
        }
    }

    private void execute35(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory35Entity srcEntity : kanrenshaSeijidantaiHistory35Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory35Entity newEntity = new KanrenshaSeijidantaiHistory35Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory35Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory35Repository.save(newEntity);
        }
    }

    private void execute36(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory36Entity srcEntity : kanrenshaSeijidantaiHistory36Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory36Entity newEntity = new KanrenshaSeijidantaiHistory36Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory36Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory36Repository.save(newEntity);
        }
    }

    private void execute37(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory37Entity srcEntity : kanrenshaSeijidantaiHistory37Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory37Entity newEntity = new KanrenshaSeijidantaiHistory37Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory37Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory37Repository.save(newEntity);
        }
    }

    private void execute38(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory38Entity srcEntity : kanrenshaSeijidantaiHistory38Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory38Entity newEntity = new KanrenshaSeijidantaiHistory38Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory38Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory38Repository.save(newEntity);
        }
    }

    private void execute39(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory39Entity srcEntity : kanrenshaSeijidantaiHistory39Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory39Entity newEntity = new KanrenshaSeijidantaiHistory39Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory39Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory39Repository.save(newEntity);
        }
    }

    private void execute40(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory40Entity srcEntity : kanrenshaSeijidantaiHistory40Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory40Entity newEntity = new KanrenshaSeijidantaiHistory40Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory40Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory40Repository.save(newEntity);
        }
    }

    private void execute41(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory41Entity srcEntity : kanrenshaSeijidantaiHistory41Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory41Entity newEntity = new KanrenshaSeijidantaiHistory41Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory41Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory41Repository.save(newEntity);
        }
    }

    private void execute42(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory42Entity srcEntity : kanrenshaSeijidantaiHistory42Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory42Entity newEntity = new KanrenshaSeijidantaiHistory42Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory42Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory42Repository.save(newEntity);
        }
    }

    private void execute43(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory43Entity srcEntity : kanrenshaSeijidantaiHistory43Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory43Entity newEntity = new KanrenshaSeijidantaiHistory43Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory43Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory43Repository.save(newEntity);
        }
    }

    private void execute44(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory44Entity srcEntity : kanrenshaSeijidantaiHistory44Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory44Entity newEntity = new KanrenshaSeijidantaiHistory44Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory44Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory44Repository.save(newEntity);
        }
    }

    private void execute45(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory45Entity srcEntity : kanrenshaSeijidantaiHistory45Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory45Entity newEntity = new KanrenshaSeijidantaiHistory45Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory45Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory45Repository.save(newEntity);
        }
    }

    private void execute46(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory46Entity srcEntity : kanrenshaSeijidantaiHistory46Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory46Entity newEntity = new KanrenshaSeijidantaiHistory46Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory46Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory46Repository.save(newEntity);
        }
    }

    private void execute47(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory47Entity srcEntity : kanrenshaSeijidantaiHistory47Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory47Entity newEntity = new KanrenshaSeijidantaiHistory47Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory47Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory47Repository.save(newEntity);
        }
    }

    private void execute99(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaSeijidantaiHistory99Entity srcEntity : kanrenshaSeijidantaiHistory99Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaSeijidantaiHistory99Entity newEntity = new KanrenshaSeijidantaiHistory99Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setSeijidantaiKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaSeijidantaiHistory99Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaSeijidantaiHistoryId(0); // auto increment明記
            kanrenshaSeijidantaiHistory99Repository.save(newEntity);
        }
    }

}