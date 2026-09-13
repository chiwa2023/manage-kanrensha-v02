package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import org.springframework.beans.BeanUtils; // NOPMD HighImports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeAcceptCapsuleDto;
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

/**
 * 関連者個人コード移動履歴Service
 */
@Service
public class MoveCodeHistoryPersonService { // NOPMD TooManyFields

    /** 関連者個人履歴01Repository */
    @Autowired
    private KanrenshaPersonHistory01Repository kanrenshaPersonHistory01Repository;

    /** 関連者個人履歴02Repository */
    @Autowired
    private KanrenshaPersonHistory02Repository kanrenshaPersonHistory02Repository;

    /** 関連者個人履歴03Repository */
    @Autowired
    private KanrenshaPersonHistory03Repository kanrenshaPersonHistory03Repository;

    /** 関連者個人履歴04Repository */
    @Autowired
    private KanrenshaPersonHistory04Repository kanrenshaPersonHistory04Repository;

    /** 関連者個人履歴05Repository */
    @Autowired
    private KanrenshaPersonHistory05Repository kanrenshaPersonHistory05Repository;

    /** 関連者個人履歴06Repository */
    @Autowired
    private KanrenshaPersonHistory06Repository kanrenshaPersonHistory06Repository;

    /** 関連者個人履歴07Repository */
    @Autowired
    private KanrenshaPersonHistory07Repository kanrenshaPersonHistory07Repository;

    /** 関連者個人履歴08Repository */
    @Autowired
    private KanrenshaPersonHistory08Repository kanrenshaPersonHistory08Repository;

    /** 関連者個人履歴09Repository */
    @Autowired
    private KanrenshaPersonHistory09Repository kanrenshaPersonHistory09Repository;

    /** 関連者個人履歴10Repository */
    @Autowired
    private KanrenshaPersonHistory10Repository kanrenshaPersonHistory10Repository;

    /** 関連者個人履歴11Repository */
    @Autowired
    private KanrenshaPersonHistory11Repository kanrenshaPersonHistory11Repository;

    /** 関連者個人履歴12Repository */
    @Autowired
    private KanrenshaPersonHistory12Repository kanrenshaPersonHistory12Repository;

    /** 関連者個人履歴13Repository */
    @Autowired
    private KanrenshaPersonHistory13Repository kanrenshaPersonHistory13Repository;

    /** 関連者個人履歴14Repository */
    @Autowired
    private KanrenshaPersonHistory14Repository kanrenshaPersonHistory14Repository;

    /** 関連者個人履歴15Repository */
    @Autowired
    private KanrenshaPersonHistory15Repository kanrenshaPersonHistory15Repository;

    /** 関連者個人履歴16Repository */
    @Autowired
    private KanrenshaPersonHistory16Repository kanrenshaPersonHistory16Repository;

    /** 関連者個人履歴17Repository */
    @Autowired
    private KanrenshaPersonHistory17Repository kanrenshaPersonHistory17Repository;

    /** 関連者個人履歴18Repository */
    @Autowired
    private KanrenshaPersonHistory18Repository kanrenshaPersonHistory18Repository;

    /** 関連者個人履歴19Repository */
    @Autowired
    private KanrenshaPersonHistory19Repository kanrenshaPersonHistory19Repository;

    /** 関連者個人履歴20Repository */
    @Autowired
    private KanrenshaPersonHistory20Repository kanrenshaPersonHistory20Repository;

    /** 関連者個人履歴21Repository */
    @Autowired
    private KanrenshaPersonHistory21Repository kanrenshaPersonHistory21Repository;

    /** 関連者個人履歴22Repository */
    @Autowired
    private KanrenshaPersonHistory22Repository kanrenshaPersonHistory22Repository;

    /** 関連者個人履歴23Repository */
    @Autowired
    private KanrenshaPersonHistory23Repository kanrenshaPersonHistory23Repository;

    /** 関連者個人履歴24Repository */
    @Autowired
    private KanrenshaPersonHistory24Repository kanrenshaPersonHistory24Repository;

    /** 関連者個人履歴25Repository */
    @Autowired
    private KanrenshaPersonHistory25Repository kanrenshaPersonHistory25Repository;

    /** 関連者個人履歴26Repository */
    @Autowired
    private KanrenshaPersonHistory26Repository kanrenshaPersonHistory26Repository;

    /** 関連者個人履歴27Repository */
    @Autowired
    private KanrenshaPersonHistory27Repository kanrenshaPersonHistory27Repository;

    /** 関連者個人履歴28Repository */
    @Autowired
    private KanrenshaPersonHistory28Repository kanrenshaPersonHistory28Repository;

    /** 関連者個人履歴29Repository */
    @Autowired
    private KanrenshaPersonHistory29Repository kanrenshaPersonHistory29Repository;

    /** 関連者個人履歴30Repository */
    @Autowired
    private KanrenshaPersonHistory30Repository kanrenshaPersonHistory30Repository;

    /** 関連者個人履歴31Repository */
    @Autowired
    private KanrenshaPersonHistory31Repository kanrenshaPersonHistory31Repository;

    /** 関連者個人履歴32Repository */
    @Autowired
    private KanrenshaPersonHistory32Repository kanrenshaPersonHistory32Repository;

    /** 関連者個人履歴33Repository */
    @Autowired
    private KanrenshaPersonHistory33Repository kanrenshaPersonHistory33Repository;

    /** 関連者個人履歴34Repository */
    @Autowired
    private KanrenshaPersonHistory34Repository kanrenshaPersonHistory34Repository;

    /** 関連者個人履歴35Repository */
    @Autowired
    private KanrenshaPersonHistory35Repository kanrenshaPersonHistory35Repository;

    /** 関連者個人履歴36Repository */
    @Autowired
    private KanrenshaPersonHistory36Repository kanrenshaPersonHistory36Repository;

    /** 関連者個人履歴37Repository */
    @Autowired
    private KanrenshaPersonHistory37Repository kanrenshaPersonHistory37Repository;

    /** 関連者個人履歴38Repository */
    @Autowired
    private KanrenshaPersonHistory38Repository kanrenshaPersonHistory38Repository;

    /** 関連者個人履歴39Repository */
    @Autowired
    private KanrenshaPersonHistory39Repository kanrenshaPersonHistory39Repository;

    /** 関連者個人履歴40Repository */
    @Autowired
    private KanrenshaPersonHistory40Repository kanrenshaPersonHistory40Repository;

    /** 関連者個人履歴41Repository */
    @Autowired
    private KanrenshaPersonHistory41Repository kanrenshaPersonHistory41Repository;

    /** 関連者個人履歴42Repository */
    @Autowired
    private KanrenshaPersonHistory42Repository kanrenshaPersonHistory42Repository;

    /** 関連者個人履歴43Repository */
    @Autowired
    private KanrenshaPersonHistory43Repository kanrenshaPersonHistory43Repository;

    /** 関連者個人履歴44Repository */
    @Autowired
    private KanrenshaPersonHistory44Repository kanrenshaPersonHistory44Repository;

    /** 関連者個人履歴45Repository */
    @Autowired
    private KanrenshaPersonHistory45Repository kanrenshaPersonHistory45Repository;

    /** 関連者個人履歴46Repository */
    @Autowired
    private KanrenshaPersonHistory46Repository kanrenshaPersonHistory46Repository;

    /** 関連者個人履歴47Repository */
    @Autowired
    private KanrenshaPersonHistory47Repository kanrenshaPersonHistory47Repository;

    /** 関連者個人履歴99Repository */
    @Autowired
    private KanrenshaPersonHistory99Repository kanrenshaPersonHistory99Repository;

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
        for (KanrenshaPersonHistory01Entity srcEntity : kanrenshaPersonHistory01Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory01Entity newEntity = new KanrenshaPersonHistory01Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory01Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory01Repository.save(newEntity);
        }
    }

    private void execute02(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory02Entity srcEntity : kanrenshaPersonHistory02Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory02Entity newEntity = new KanrenshaPersonHistory02Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory02Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory02Repository.save(newEntity);
        }
    }

    private void execute03(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory03Entity srcEntity : kanrenshaPersonHistory03Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory03Entity newEntity = new KanrenshaPersonHistory03Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory03Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory03Repository.save(newEntity);
        }
    }

    private void execute04(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory04Entity srcEntity : kanrenshaPersonHistory04Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory04Entity newEntity = new KanrenshaPersonHistory04Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory04Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory04Repository.save(newEntity);
        }
    }

    private void execute05(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory05Entity srcEntity : kanrenshaPersonHistory05Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory05Entity newEntity = new KanrenshaPersonHistory05Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory05Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory05Repository.save(newEntity);
        }
    }

    private void execute06(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory06Entity srcEntity : kanrenshaPersonHistory06Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory06Entity newEntity = new KanrenshaPersonHistory06Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory06Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory06Repository.save(newEntity);
        }
    }

    private void execute07(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory07Entity srcEntity : kanrenshaPersonHistory07Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory07Entity newEntity = new KanrenshaPersonHistory07Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory07Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory07Repository.save(newEntity);
        }
    }

    private void execute08(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory08Entity srcEntity : kanrenshaPersonHistory08Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory08Entity newEntity = new KanrenshaPersonHistory08Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory08Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory08Repository.save(newEntity);
        }
    }

    private void execute09(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory09Entity srcEntity : kanrenshaPersonHistory09Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory09Entity newEntity = new KanrenshaPersonHistory09Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory09Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory09Repository.save(newEntity);
        }
    }

    private void execute10(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory10Entity srcEntity : kanrenshaPersonHistory10Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory10Entity newEntity = new KanrenshaPersonHistory10Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory10Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory10Repository.save(newEntity);
        }
    }

    private void execute11(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory11Entity srcEntity : kanrenshaPersonHistory11Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory11Entity newEntity = new KanrenshaPersonHistory11Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory11Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory11Repository.save(newEntity);
        }
    }

    private void execute12(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory12Entity srcEntity : kanrenshaPersonHistory12Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory12Entity newEntity = new KanrenshaPersonHistory12Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory12Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory12Repository.save(newEntity);
        }
    }

    private void execute13(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory13Entity srcEntity : kanrenshaPersonHistory13Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory13Entity newEntity = new KanrenshaPersonHistory13Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory13Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory13Repository.save(newEntity);
        }
    }

    private void execute14(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory14Entity srcEntity : kanrenshaPersonHistory14Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory14Entity newEntity = new KanrenshaPersonHistory14Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory14Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory14Repository.save(newEntity);
        }
    }

    private void execute15(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory15Entity srcEntity : kanrenshaPersonHistory15Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory15Entity newEntity = new KanrenshaPersonHistory15Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory15Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory15Repository.save(newEntity);
        }
    }

    private void execute16(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory16Entity srcEntity : kanrenshaPersonHistory16Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory16Entity newEntity = new KanrenshaPersonHistory16Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory16Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory16Repository.save(newEntity);
        }
    }

    private void execute17(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory17Entity srcEntity : kanrenshaPersonHistory17Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory17Entity newEntity = new KanrenshaPersonHistory17Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory17Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory17Repository.save(newEntity);
        }
    }

    private void execute18(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory18Entity srcEntity : kanrenshaPersonHistory18Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory18Entity newEntity = new KanrenshaPersonHistory18Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory18Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory18Repository.save(newEntity);
        }
    }

    private void execute19(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory19Entity srcEntity : kanrenshaPersonHistory19Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory19Entity newEntity = new KanrenshaPersonHistory19Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory19Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory19Repository.save(newEntity);
        }
    }

    private void execute20(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory20Entity srcEntity : kanrenshaPersonHistory20Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory20Entity newEntity = new KanrenshaPersonHistory20Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory20Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory20Repository.save(newEntity);
        }
    }

    private void execute21(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory21Entity srcEntity : kanrenshaPersonHistory21Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory21Entity newEntity = new KanrenshaPersonHistory21Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory21Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory21Repository.save(newEntity);
        }
    }

    private void execute22(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory22Entity srcEntity : kanrenshaPersonHistory22Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory22Entity newEntity = new KanrenshaPersonHistory22Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory22Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory22Repository.save(newEntity);
        }
    }

    private void execute23(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory23Entity srcEntity : kanrenshaPersonHistory23Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory23Entity newEntity = new KanrenshaPersonHistory23Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory23Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory23Repository.save(newEntity);
        }
    }

    private void execute24(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory24Entity srcEntity : kanrenshaPersonHistory24Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory24Entity newEntity = new KanrenshaPersonHistory24Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory24Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory24Repository.save(newEntity);
        }
    }

    private void execute25(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory25Entity srcEntity : kanrenshaPersonHistory25Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory25Entity newEntity = new KanrenshaPersonHistory25Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory25Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory25Repository.save(newEntity);
        }
    }

    private void execute26(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory26Entity srcEntity : kanrenshaPersonHistory26Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory26Entity newEntity = new KanrenshaPersonHistory26Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory26Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory26Repository.save(newEntity);
        }
    }

    private void execute27(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory27Entity srcEntity : kanrenshaPersonHistory27Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory27Entity newEntity = new KanrenshaPersonHistory27Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory27Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory27Repository.save(newEntity);
        }
    }

    private void execute28(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory28Entity srcEntity : kanrenshaPersonHistory28Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory28Entity newEntity = new KanrenshaPersonHistory28Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory28Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory28Repository.save(newEntity);
        }
    }

    private void execute29(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory29Entity srcEntity : kanrenshaPersonHistory29Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory29Entity newEntity = new KanrenshaPersonHistory29Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory29Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory29Repository.save(newEntity);
        }
    }

    private void execute30(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory30Entity srcEntity : kanrenshaPersonHistory30Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory30Entity newEntity = new KanrenshaPersonHistory30Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory30Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory30Repository.save(newEntity);
        }
    }

    private void execute31(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory31Entity srcEntity : kanrenshaPersonHistory31Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory31Entity newEntity = new KanrenshaPersonHistory31Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory31Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory31Repository.save(newEntity);
        }
    }

    private void execute32(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory32Entity srcEntity : kanrenshaPersonHistory32Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory32Entity newEntity = new KanrenshaPersonHistory32Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory32Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory32Repository.save(newEntity);
        }
    }

    private void execute33(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory33Entity srcEntity : kanrenshaPersonHistory33Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory33Entity newEntity = new KanrenshaPersonHistory33Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory33Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory33Repository.save(newEntity);
        }
    }

    private void execute34(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory34Entity srcEntity : kanrenshaPersonHistory34Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory34Entity newEntity = new KanrenshaPersonHistory34Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory34Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory34Repository.save(newEntity);
        }
    }

    private void execute35(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory35Entity srcEntity : kanrenshaPersonHistory35Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory35Entity newEntity = new KanrenshaPersonHistory35Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory35Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory35Repository.save(newEntity);
        }
    }

    private void execute36(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory36Entity srcEntity : kanrenshaPersonHistory36Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory36Entity newEntity = new KanrenshaPersonHistory36Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory36Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory36Repository.save(newEntity);
        }
    }

    private void execute37(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory37Entity srcEntity : kanrenshaPersonHistory37Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory37Entity newEntity = new KanrenshaPersonHistory37Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory37Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory37Repository.save(newEntity);
        }
    }

    private void execute38(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory38Entity srcEntity : kanrenshaPersonHistory38Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory38Entity newEntity = new KanrenshaPersonHistory38Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory38Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory38Repository.save(newEntity);
        }
    }

    private void execute39(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory39Entity srcEntity : kanrenshaPersonHistory39Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory39Entity newEntity = new KanrenshaPersonHistory39Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory39Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory39Repository.save(newEntity);
        }
    }

    private void execute40(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory40Entity srcEntity : kanrenshaPersonHistory40Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory40Entity newEntity = new KanrenshaPersonHistory40Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory40Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory40Repository.save(newEntity);
        }
    }

    private void execute41(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory41Entity srcEntity : kanrenshaPersonHistory41Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory41Entity newEntity = new KanrenshaPersonHistory41Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory41Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory41Repository.save(newEntity);
        }
    }

    private void execute42(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory42Entity srcEntity : kanrenshaPersonHistory42Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory42Entity newEntity = new KanrenshaPersonHistory42Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory42Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory42Repository.save(newEntity);
        }
    }

    private void execute43(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory43Entity srcEntity : kanrenshaPersonHistory43Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory43Entity newEntity = new KanrenshaPersonHistory43Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory43Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory43Repository.save(newEntity);
        }
    }

    private void execute44(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory44Entity srcEntity : kanrenshaPersonHistory44Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory44Entity newEntity = new KanrenshaPersonHistory44Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory44Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory44Repository.save(newEntity);
        }
    }

    private void execute45(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory45Entity srcEntity : kanrenshaPersonHistory45Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory45Entity newEntity = new KanrenshaPersonHistory45Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory45Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory45Repository.save(newEntity);
        }
    }

    private void execute46(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory46Entity srcEntity : kanrenshaPersonHistory46Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory46Entity newEntity = new KanrenshaPersonHistory46Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory46Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory46Repository.save(newEntity);
        }
    }

    private void execute47(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory47Entity srcEntity : kanrenshaPersonHistory47Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory47Entity newEntity = new KanrenshaPersonHistory47Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory47Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory47Repository.save(newEntity);
        }
    }

    private void execute99(final LeastUserDto userDto, final String orgCode, final String abolishCode) {

        for (KanrenshaPersonHistory99Entity srcEntity : kanrenshaPersonHistory99Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode)) {
            KanrenshaPersonHistory99Entity newEntity = new KanrenshaPersonHistory99Entity(); // NOPMD
            BeanUtils.copyProperties(srcEntity, newEntity);
            newEntity.setPersonKanrenshaCode(orgCode);
            setTableDataHistoryUtil.practiceDelete(userDto, srcEntity);
            kanrenshaPersonHistory99Repository.save(srcEntity);
            setTableDataHistoryUtil.practiceInsert(userDto, newEntity);
            newEntity.setKanrenshaPersonHistoryId(0); // auto increment明記
            kanrenshaPersonHistory99Repository.save(newEntity);
        }
    }

}