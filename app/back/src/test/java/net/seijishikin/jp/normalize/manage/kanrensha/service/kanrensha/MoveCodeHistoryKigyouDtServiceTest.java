package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals; // NOPMD HighNumberImport
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.ShinseiStatusConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeAcceptCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaCodeMoveEntity;
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
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * MoveCodeHistoryKigyouDtService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("MoveCodeHistoryKigyouDtServiceTest.sql")
class MoveCodeHistoryKigyouDtServiceTest { // NOPMD CouplingObjects

    /** テスト対象 */
    @Autowired
    private MoveCodeHistoryKigyouDtService moveCodeHistoryKigyouDtService;

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

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        MoveKanrenshaCodeAcceptCapsuleDto capsuleDto = new MoveKanrenshaCodeAcceptCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        KanrenshaCodeMoveEntity moveEntity = new KanrenshaCodeMoveEntity();

        final String orgCode = "12345";
        final String abolishCode = "98765";
        moveEntity.setMoveStatus(ShinseiStatusConstants.ACCEPT);
        moveEntity.setAbolishKanrenshaCode(abolishCode);
        moveEntity.setOriginKanrenshaCode(orgCode);
        moveEntity.setIsAbolishLast(false); // 元コードを最新にする

        moveEntity.setKanrenshaKbn(KanrenshaKbnConstants.PERSON);
        moveEntity.setAbolishKanrenshaName("廃止名称");
        moveEntity.setOriginName("残す名称");
        moveEntity.setMoveReason("移動理由");

        capsuleDto.setKanrenshaCodeMoveEntity(moveEntity);

        assertTrue(moveCodeHistoryKigyouDtService.practice(capsuleDto));

        // 01
        List<KanrenshaKigyouDtHistory01Entity> list01New = kanrenshaKigyouDtHistory01Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list01New.size());
        KanrenshaKigyouDtHistory01Entity entity010 = list01New.get(0);
        assertEquals("ぼったくり企業011a", entity010.getAllName());
        KanrenshaKigyouDtHistory01Entity entity011 = list01New.get(1);
        assertEquals("ぼったくり企業012a", entity011.getAllName());
        List<KanrenshaKigyouDtHistory01Entity> list01Old = kanrenshaKigyouDtHistory01Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list01Old.size());

        // 02
        List<KanrenshaKigyouDtHistory02Entity> list02New = kanrenshaKigyouDtHistory02Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list02New.size());
        KanrenshaKigyouDtHistory02Entity entity020 = list02New.get(0);
        assertEquals("ぼったくり企業021a", entity020.getAllName());
        KanrenshaKigyouDtHistory02Entity entity021 = list02New.get(1);
        assertEquals("ぼったくり企業022a", entity021.getAllName());
        List<KanrenshaKigyouDtHistory02Entity> list02Old = kanrenshaKigyouDtHistory02Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list02Old.size());

        // 03
        List<KanrenshaKigyouDtHistory03Entity> list03New = kanrenshaKigyouDtHistory03Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list03New.size());
        KanrenshaKigyouDtHistory03Entity entity030 = list03New.get(0);
        assertEquals("ぼったくり企業031a", entity030.getAllName());
        KanrenshaKigyouDtHistory03Entity entity031 = list03New.get(1);
        assertEquals("ぼったくり企業032a", entity031.getAllName());
        List<KanrenshaKigyouDtHistory03Entity> list03Old = kanrenshaKigyouDtHistory03Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list03Old.size());

        // 04
        List<KanrenshaKigyouDtHistory04Entity> list04New = kanrenshaKigyouDtHistory04Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list04New.size());
        KanrenshaKigyouDtHistory04Entity entity040 = list04New.get(0);
        assertEquals("ぼったくり企業041a", entity040.getAllName());
        KanrenshaKigyouDtHistory04Entity entity041 = list04New.get(1);
        assertEquals("ぼったくり企業042a", entity041.getAllName());
        List<KanrenshaKigyouDtHistory04Entity> list04Old = kanrenshaKigyouDtHistory04Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list04Old.size());

        // 05
        List<KanrenshaKigyouDtHistory05Entity> list05New = kanrenshaKigyouDtHistory05Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list05New.size());
        KanrenshaKigyouDtHistory05Entity entity050 = list05New.get(0);
        assertEquals("ぼったくり企業051a", entity050.getAllName());
        KanrenshaKigyouDtHistory05Entity entity051 = list05New.get(1);
        assertEquals("ぼったくり企業052a", entity051.getAllName());
        List<KanrenshaKigyouDtHistory05Entity> list05Old = kanrenshaKigyouDtHistory05Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list05Old.size());

        // 06
        List<KanrenshaKigyouDtHistory06Entity> list06New = kanrenshaKigyouDtHistory06Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list06New.size());
        KanrenshaKigyouDtHistory06Entity entity060 = list06New.get(0);
        assertEquals("ぼったくり企業061a", entity060.getAllName());
        KanrenshaKigyouDtHistory06Entity entity061 = list06New.get(1);
        assertEquals("ぼったくり企業062a", entity061.getAllName());
        List<KanrenshaKigyouDtHistory06Entity> list06Old = kanrenshaKigyouDtHistory06Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list06Old.size());

        // 07
        List<KanrenshaKigyouDtHistory07Entity> list07New = kanrenshaKigyouDtHistory07Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list07New.size());
        KanrenshaKigyouDtHistory07Entity entity070 = list07New.get(0);
        assertEquals("ぼったくり企業071a", entity070.getAllName());
        KanrenshaKigyouDtHistory07Entity entity071 = list07New.get(1);
        assertEquals("ぼったくり企業072a", entity071.getAllName());
        List<KanrenshaKigyouDtHistory07Entity> list07Old = kanrenshaKigyouDtHistory07Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list07Old.size());

        // 08
        List<KanrenshaKigyouDtHistory08Entity> list08New = kanrenshaKigyouDtHistory08Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list08New.size());
        KanrenshaKigyouDtHistory08Entity entity080 = list08New.get(0);
        assertEquals("ぼったくり企業081a", entity080.getAllName());
        KanrenshaKigyouDtHistory08Entity entity081 = list08New.get(1);
        assertEquals("ぼったくり企業082a", entity081.getAllName());
        List<KanrenshaKigyouDtHistory08Entity> list08Old = kanrenshaKigyouDtHistory08Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list08Old.size());

        // 09
        List<KanrenshaKigyouDtHistory09Entity> list09New = kanrenshaKigyouDtHistory09Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list09New.size());
        KanrenshaKigyouDtHistory09Entity entity090 = list09New.get(0);
        assertEquals("ぼったくり企業091a", entity090.getAllName());
        KanrenshaKigyouDtHistory09Entity entity091 = list09New.get(1);
        assertEquals("ぼったくり企業092a", entity091.getAllName());
        List<KanrenshaKigyouDtHistory09Entity> list09Old = kanrenshaKigyouDtHistory09Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list09Old.size());

        // 10
        List<KanrenshaKigyouDtHistory10Entity> list10New = kanrenshaKigyouDtHistory10Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list10New.size());
        KanrenshaKigyouDtHistory10Entity entity100 = list10New.get(0);
        assertEquals("ぼったくり企業101a", entity100.getAllName());
        KanrenshaKigyouDtHistory10Entity entity101 = list10New.get(1);
        assertEquals("ぼったくり企業102a", entity101.getAllName());
        List<KanrenshaKigyouDtHistory10Entity> list10Old = kanrenshaKigyouDtHistory10Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list10Old.size());


        // 11
        List<KanrenshaKigyouDtHistory11Entity> list11New = kanrenshaKigyouDtHistory11Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list11New.size());
        KanrenshaKigyouDtHistory11Entity entity110 = list11New.get(0);
        assertEquals("ぼったくり企業111a", entity110.getAllName());
        KanrenshaKigyouDtHistory11Entity entity111 = list11New.get(1);
        assertEquals("ぼったくり企業112a", entity111.getAllName());
        List<KanrenshaKigyouDtHistory11Entity> list11Old = kanrenshaKigyouDtHistory11Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list11Old.size());

        // 12
        List<KanrenshaKigyouDtHistory12Entity> list12New = kanrenshaKigyouDtHistory12Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list12New.size());
        KanrenshaKigyouDtHistory12Entity entity120 = list12New.get(0);
        assertEquals("ぼったくり企業121a", entity120.getAllName());
        KanrenshaKigyouDtHistory12Entity entity121 = list12New.get(1);
        assertEquals("ぼったくり企業122a", entity121.getAllName());
        List<KanrenshaKigyouDtHistory12Entity> list12Old = kanrenshaKigyouDtHistory12Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list12Old.size());

        // 13
        List<KanrenshaKigyouDtHistory13Entity> list13New = kanrenshaKigyouDtHistory13Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list13New.size());
        KanrenshaKigyouDtHistory13Entity entity130 = list13New.get(0);
        assertEquals("ぼったくり企業131a", entity130.getAllName());
        KanrenshaKigyouDtHistory13Entity entity131 = list13New.get(1);
        assertEquals("ぼったくり企業132a", entity131.getAllName());
        List<KanrenshaKigyouDtHistory13Entity> list13Old = kanrenshaKigyouDtHistory13Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list13Old.size());

        // 14
        List<KanrenshaKigyouDtHistory14Entity> list14New = kanrenshaKigyouDtHistory14Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list14New.size());
        KanrenshaKigyouDtHistory14Entity entity140 = list14New.get(0);
        assertEquals("ぼったくり企業141a", entity140.getAllName());
        KanrenshaKigyouDtHistory14Entity entity141 = list14New.get(1);
        assertEquals("ぼったくり企業142a", entity141.getAllName());
        List<KanrenshaKigyouDtHistory14Entity> list14Old = kanrenshaKigyouDtHistory14Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list14Old.size());

        // 15
        List<KanrenshaKigyouDtHistory15Entity> list15New = kanrenshaKigyouDtHistory15Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list15New.size());
        KanrenshaKigyouDtHistory15Entity entity150 = list15New.get(0);
        assertEquals("ぼったくり企業151a", entity150.getAllName());
        KanrenshaKigyouDtHistory15Entity entity151 = list15New.get(1);
        assertEquals("ぼったくり企業152a", entity151.getAllName());
        List<KanrenshaKigyouDtHistory15Entity> list15Old = kanrenshaKigyouDtHistory15Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list15Old.size());

        // 16
        List<KanrenshaKigyouDtHistory16Entity> list16New = kanrenshaKigyouDtHistory16Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list16New.size());
        KanrenshaKigyouDtHistory16Entity entity160 = list16New.get(0);
        assertEquals("ぼったくり企業161a", entity160.getAllName());
        KanrenshaKigyouDtHistory16Entity entity161 = list16New.get(1);
        assertEquals("ぼったくり企業162a", entity161.getAllName());
        List<KanrenshaKigyouDtHistory16Entity> list16Old = kanrenshaKigyouDtHistory16Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list16Old.size());

        // 17
        List<KanrenshaKigyouDtHistory17Entity> list17New = kanrenshaKigyouDtHistory17Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list17New.size());
        KanrenshaKigyouDtHistory17Entity entity170 = list17New.get(0);
        assertEquals("ぼったくり企業171a", entity170.getAllName());
        KanrenshaKigyouDtHistory17Entity entity171 = list17New.get(1);
        assertEquals("ぼったくり企業172a", entity171.getAllName());
        List<KanrenshaKigyouDtHistory17Entity> list17Old = kanrenshaKigyouDtHistory17Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list17Old.size());

        // 18
        List<KanrenshaKigyouDtHistory18Entity> list18New = kanrenshaKigyouDtHistory18Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list18New.size());
        KanrenshaKigyouDtHistory18Entity entity180 = list18New.get(0);
        assertEquals("ぼったくり企業181a", entity180.getAllName());
        KanrenshaKigyouDtHistory18Entity entity181 = list18New.get(1);
        assertEquals("ぼったくり企業182a", entity181.getAllName());
        List<KanrenshaKigyouDtHistory18Entity> list18Old = kanrenshaKigyouDtHistory18Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list18Old.size());

        // 19
        List<KanrenshaKigyouDtHistory19Entity> list19New = kanrenshaKigyouDtHistory19Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list19New.size());
        KanrenshaKigyouDtHistory19Entity entity190 = list19New.get(0);
        assertEquals("ぼったくり企業191a", entity190.getAllName());
        KanrenshaKigyouDtHistory19Entity entity191 = list19New.get(1);
        assertEquals("ぼったくり企業192a", entity191.getAllName());
        List<KanrenshaKigyouDtHistory19Entity> list19Old = kanrenshaKigyouDtHistory19Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list19Old.size());

        // 20
        List<KanrenshaKigyouDtHistory20Entity> list20New = kanrenshaKigyouDtHistory20Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list20New.size());
        KanrenshaKigyouDtHistory20Entity entity200 = list20New.get(0);
        assertEquals("ぼったくり企業201a", entity200.getAllName());
        KanrenshaKigyouDtHistory20Entity entity201 = list20New.get(1);
        assertEquals("ぼったくり企業202a", entity201.getAllName());
        List<KanrenshaKigyouDtHistory20Entity> list20Old = kanrenshaKigyouDtHistory20Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list20Old.size());


        // 21
        List<KanrenshaKigyouDtHistory21Entity> list21New = kanrenshaKigyouDtHistory21Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list21New.size());
        KanrenshaKigyouDtHistory21Entity entity210 = list21New.get(0);
        assertEquals("ぼったくり企業211a", entity210.getAllName());
        KanrenshaKigyouDtHistory21Entity entity211 = list21New.get(1);
        assertEquals("ぼったくり企業212a", entity211.getAllName());
        List<KanrenshaKigyouDtHistory21Entity> list21Old = kanrenshaKigyouDtHistory21Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list21Old.size());

        // 22
        List<KanrenshaKigyouDtHistory22Entity> list22New = kanrenshaKigyouDtHistory22Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list22New.size());
        KanrenshaKigyouDtHistory22Entity entity220 = list22New.get(0);
        assertEquals("ぼったくり企業221a", entity220.getAllName());
        KanrenshaKigyouDtHistory22Entity entity221 = list22New.get(1);
        assertEquals("ぼったくり企業222a", entity221.getAllName());
        List<KanrenshaKigyouDtHistory22Entity> list22Old = kanrenshaKigyouDtHistory22Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list22Old.size());

        // 23
        List<KanrenshaKigyouDtHistory23Entity> list23New = kanrenshaKigyouDtHistory23Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list23New.size());
        KanrenshaKigyouDtHistory23Entity entity230 = list23New.get(0);
        assertEquals("ぼったくり企業231a", entity230.getAllName());
        KanrenshaKigyouDtHistory23Entity entity231 = list23New.get(1);
        assertEquals("ぼったくり企業232a", entity231.getAllName());
        List<KanrenshaKigyouDtHistory23Entity> list23Old = kanrenshaKigyouDtHistory23Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list23Old.size());

        // 24
        List<KanrenshaKigyouDtHistory24Entity> list24New = kanrenshaKigyouDtHistory24Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list24New.size());
        KanrenshaKigyouDtHistory24Entity entity240 = list24New.get(0);
        assertEquals("ぼったくり企業241a", entity240.getAllName());
        KanrenshaKigyouDtHistory24Entity entity241 = list24New.get(1);
        assertEquals("ぼったくり企業242a", entity241.getAllName());
        List<KanrenshaKigyouDtHistory24Entity> list24Old = kanrenshaKigyouDtHistory24Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list24Old.size());

        // 25
        List<KanrenshaKigyouDtHistory25Entity> list25New = kanrenshaKigyouDtHistory25Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list25New.size());
        KanrenshaKigyouDtHistory25Entity entity250 = list25New.get(0);
        assertEquals("ぼったくり企業251a", entity250.getAllName());
        KanrenshaKigyouDtHistory25Entity entity251 = list25New.get(1);
        assertEquals("ぼったくり企業252a", entity251.getAllName());
        List<KanrenshaKigyouDtHistory25Entity> list25Old = kanrenshaKigyouDtHistory25Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list25Old.size());

        // 26
        List<KanrenshaKigyouDtHistory26Entity> list26New = kanrenshaKigyouDtHistory26Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list26New.size());
        KanrenshaKigyouDtHistory26Entity entity260 = list26New.get(0);
        assertEquals("ぼったくり企業261a", entity260.getAllName());
        KanrenshaKigyouDtHistory26Entity entity261 = list26New.get(1);
        assertEquals("ぼったくり企業262a", entity261.getAllName());
        List<KanrenshaKigyouDtHistory26Entity> list26Old = kanrenshaKigyouDtHistory26Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list26Old.size());

        // 27
        List<KanrenshaKigyouDtHistory27Entity> list27New = kanrenshaKigyouDtHistory27Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list27New.size());
        KanrenshaKigyouDtHistory27Entity entity270 = list27New.get(0);
        assertEquals("ぼったくり企業271a", entity270.getAllName());
        KanrenshaKigyouDtHistory27Entity entity271 = list27New.get(1);
        assertEquals("ぼったくり企業272a", entity271.getAllName());
        List<KanrenshaKigyouDtHistory27Entity> list27Old = kanrenshaKigyouDtHistory27Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list27Old.size());

        // 28
        List<KanrenshaKigyouDtHistory28Entity> list28New = kanrenshaKigyouDtHistory28Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list28New.size());
        KanrenshaKigyouDtHistory28Entity entity280 = list28New.get(0);
        assertEquals("ぼったくり企業281a", entity280.getAllName());
        KanrenshaKigyouDtHistory28Entity entity281 = list28New.get(1);
        assertEquals("ぼったくり企業282a", entity281.getAllName());
        List<KanrenshaKigyouDtHistory28Entity> list28Old = kanrenshaKigyouDtHistory28Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list28Old.size());

        // 29
        List<KanrenshaKigyouDtHistory29Entity> list29New = kanrenshaKigyouDtHistory29Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list29New.size());
        KanrenshaKigyouDtHistory29Entity entity290 = list29New.get(0);
        assertEquals("ぼったくり企業291a", entity290.getAllName());
        KanrenshaKigyouDtHistory29Entity entity291 = list29New.get(1);
        assertEquals("ぼったくり企業292a", entity291.getAllName());
        List<KanrenshaKigyouDtHistory29Entity> list29Old = kanrenshaKigyouDtHistory29Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list29Old.size());

        // 30
        List<KanrenshaKigyouDtHistory30Entity> list30New = kanrenshaKigyouDtHistory30Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list30New.size());
        KanrenshaKigyouDtHistory30Entity entity300 = list30New.get(0);
        assertEquals("ぼったくり企業301a", entity300.getAllName());
        KanrenshaKigyouDtHistory30Entity entity301 = list30New.get(1);
        assertEquals("ぼったくり企業302a", entity301.getAllName());
        List<KanrenshaKigyouDtHistory30Entity> list30Old = kanrenshaKigyouDtHistory30Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list30Old.size());


        // 31
        List<KanrenshaKigyouDtHistory31Entity> list31New = kanrenshaKigyouDtHistory31Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list31New.size());
        KanrenshaKigyouDtHistory31Entity entity310 = list31New.get(0);
        assertEquals("ぼったくり企業311a", entity310.getAllName());
        KanrenshaKigyouDtHistory31Entity entity311 = list31New.get(1);
        assertEquals("ぼったくり企業312a", entity311.getAllName());
        List<KanrenshaKigyouDtHistory31Entity> list31Old = kanrenshaKigyouDtHistory31Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list31Old.size());

        // 32
        List<KanrenshaKigyouDtHistory32Entity> list32New = kanrenshaKigyouDtHistory32Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list32New.size());
        KanrenshaKigyouDtHistory32Entity entity320 = list32New.get(0);
        assertEquals("ぼったくり企業321a", entity320.getAllName());
        KanrenshaKigyouDtHistory32Entity entity321 = list32New.get(1);
        assertEquals("ぼったくり企業322a", entity321.getAllName());
        List<KanrenshaKigyouDtHistory32Entity> list32Old = kanrenshaKigyouDtHistory32Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list32Old.size());

        // 33
        List<KanrenshaKigyouDtHistory33Entity> list33New = kanrenshaKigyouDtHistory33Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list33New.size());
        KanrenshaKigyouDtHistory33Entity entity330 = list33New.get(0);
        assertEquals("ぼったくり企業331a", entity330.getAllName());
        KanrenshaKigyouDtHistory33Entity entity331 = list33New.get(1);
        assertEquals("ぼったくり企業332a", entity331.getAllName());
        List<KanrenshaKigyouDtHistory33Entity> list33Old = kanrenshaKigyouDtHistory33Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list33Old.size());

        // 34
        List<KanrenshaKigyouDtHistory34Entity> list34New = kanrenshaKigyouDtHistory34Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list34New.size());
        KanrenshaKigyouDtHistory34Entity entity340 = list34New.get(0);
        assertEquals("ぼったくり企業341a", entity340.getAllName());
        KanrenshaKigyouDtHistory34Entity entity341 = list34New.get(1);
        assertEquals("ぼったくり企業342a", entity341.getAllName());
        List<KanrenshaKigyouDtHistory34Entity> list34Old = kanrenshaKigyouDtHistory34Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list34Old.size());

        // 35
        List<KanrenshaKigyouDtHistory35Entity> list35New = kanrenshaKigyouDtHistory35Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list35New.size());
        KanrenshaKigyouDtHistory35Entity entity350 = list35New.get(0);
        assertEquals("ぼったくり企業351a", entity350.getAllName());
        KanrenshaKigyouDtHistory35Entity entity351 = list35New.get(1);
        assertEquals("ぼったくり企業352a", entity351.getAllName());
        List<KanrenshaKigyouDtHistory35Entity> list35Old = kanrenshaKigyouDtHistory35Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list35Old.size());

        // 36
        List<KanrenshaKigyouDtHistory36Entity> list36New = kanrenshaKigyouDtHistory36Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list36New.size());
        KanrenshaKigyouDtHistory36Entity entity360 = list36New.get(0);
        assertEquals("ぼったくり企業361a", entity360.getAllName());
        KanrenshaKigyouDtHistory36Entity entity361 = list36New.get(1);
        assertEquals("ぼったくり企業362a", entity361.getAllName());
        List<KanrenshaKigyouDtHistory36Entity> list36Old = kanrenshaKigyouDtHistory36Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list36Old.size());

        // 37
        List<KanrenshaKigyouDtHistory37Entity> list37New = kanrenshaKigyouDtHistory37Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list37New.size());
        KanrenshaKigyouDtHistory37Entity entity370 = list37New.get(0);
        assertEquals("ぼったくり企業371a", entity370.getAllName());
        KanrenshaKigyouDtHistory37Entity entity371 = list37New.get(1);
        assertEquals("ぼったくり企業372a", entity371.getAllName());
        List<KanrenshaKigyouDtHistory37Entity> list37Old = kanrenshaKigyouDtHistory37Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list37Old.size());

        // 38
        List<KanrenshaKigyouDtHistory38Entity> list38New = kanrenshaKigyouDtHistory38Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list38New.size());
        KanrenshaKigyouDtHistory38Entity entity380 = list38New.get(0);
        assertEquals("ぼったくり企業381a", entity380.getAllName());
        KanrenshaKigyouDtHistory38Entity entity381 = list38New.get(1);
        assertEquals("ぼったくり企業382a", entity381.getAllName());
        List<KanrenshaKigyouDtHistory38Entity> list38Old = kanrenshaKigyouDtHistory38Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list38Old.size());

        // 39
        List<KanrenshaKigyouDtHistory39Entity> list39New = kanrenshaKigyouDtHistory39Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list39New.size());
        KanrenshaKigyouDtHistory39Entity entity390 = list39New.get(0);
        assertEquals("ぼったくり企業391a", entity390.getAllName());
        KanrenshaKigyouDtHistory39Entity entity391 = list39New.get(1);
        assertEquals("ぼったくり企業392a", entity391.getAllName());
        List<KanrenshaKigyouDtHistory39Entity> list39Old = kanrenshaKigyouDtHistory39Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list39Old.size());

        // 40
        List<KanrenshaKigyouDtHistory40Entity> list40New = kanrenshaKigyouDtHistory40Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list40New.size());
        KanrenshaKigyouDtHistory40Entity entity400 = list40New.get(0);
        assertEquals("ぼったくり企業401a", entity400.getAllName());
        KanrenshaKigyouDtHistory40Entity entity401 = list40New.get(1);
        assertEquals("ぼったくり企業402a", entity401.getAllName());
        List<KanrenshaKigyouDtHistory40Entity> list40Old = kanrenshaKigyouDtHistory40Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list40Old.size());


        // 41
        List<KanrenshaKigyouDtHistory41Entity> list41New = kanrenshaKigyouDtHistory41Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list41New.size());
        KanrenshaKigyouDtHistory41Entity entity410 = list41New.get(0);
        assertEquals("ぼったくり企業411a", entity410.getAllName());
        KanrenshaKigyouDtHistory41Entity entity411 = list41New.get(1);
        assertEquals("ぼったくり企業412a", entity411.getAllName());
        List<KanrenshaKigyouDtHistory41Entity> list41Old = kanrenshaKigyouDtHistory41Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list41Old.size());

        // 42
        List<KanrenshaKigyouDtHistory42Entity> list42New = kanrenshaKigyouDtHistory42Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list42New.size());
        KanrenshaKigyouDtHistory42Entity entity420 = list42New.get(0);
        assertEquals("ぼったくり企業421a", entity420.getAllName());
        KanrenshaKigyouDtHistory42Entity entity421 = list42New.get(1);
        assertEquals("ぼったくり企業422a", entity421.getAllName());
        List<KanrenshaKigyouDtHistory42Entity> list42Old = kanrenshaKigyouDtHistory42Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list42Old.size());

        // 43
        List<KanrenshaKigyouDtHistory43Entity> list43New = kanrenshaKigyouDtHistory43Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list43New.size());
        KanrenshaKigyouDtHistory43Entity entity430 = list43New.get(0);
        assertEquals("ぼったくり企業431a", entity430.getAllName());
        KanrenshaKigyouDtHistory43Entity entity431 = list43New.get(1);
        assertEquals("ぼったくり企業432a", entity431.getAllName());
        List<KanrenshaKigyouDtHistory43Entity> list43Old = kanrenshaKigyouDtHistory43Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list43Old.size());

        // 44
        List<KanrenshaKigyouDtHistory44Entity> list44New = kanrenshaKigyouDtHistory44Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list44New.size());
        KanrenshaKigyouDtHistory44Entity entity440 = list44New.get(0);
        assertEquals("ぼったくり企業441a", entity440.getAllName());
        KanrenshaKigyouDtHistory44Entity entity441 = list44New.get(1);
        assertEquals("ぼったくり企業442a", entity441.getAllName());
        List<KanrenshaKigyouDtHistory44Entity> list44Old = kanrenshaKigyouDtHistory44Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list44Old.size());

        // 45
        List<KanrenshaKigyouDtHistory45Entity> list45New = kanrenshaKigyouDtHistory45Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list45New.size());
        KanrenshaKigyouDtHistory45Entity entity450 = list45New.get(0);
        assertEquals("ぼったくり企業451a", entity450.getAllName());
        KanrenshaKigyouDtHistory45Entity entity451 = list45New.get(1);
        assertEquals("ぼったくり企業452a", entity451.getAllName());
        List<KanrenshaKigyouDtHistory45Entity> list45Old = kanrenshaKigyouDtHistory45Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list45Old.size());

        // 46
        List<KanrenshaKigyouDtHistory46Entity> list46New = kanrenshaKigyouDtHistory46Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list46New.size());
        KanrenshaKigyouDtHistory46Entity entity460 = list46New.get(0);
        assertEquals("ぼったくり企業461a", entity460.getAllName());
        KanrenshaKigyouDtHistory46Entity entity461 = list46New.get(1);
        assertEquals("ぼったくり企業462a", entity461.getAllName());
        List<KanrenshaKigyouDtHistory46Entity> list46Old = kanrenshaKigyouDtHistory46Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list46Old.size());

        // 47
        List<KanrenshaKigyouDtHistory47Entity> list47New = kanrenshaKigyouDtHistory47Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list47New.size());
        KanrenshaKigyouDtHistory47Entity entity470 = list47New.get(0);
        assertEquals("ぼったくり企業471a", entity470.getAllName());
        KanrenshaKigyouDtHistory47Entity entity471 = list47New.get(1);
        assertEquals("ぼったくり企業472a", entity471.getAllName());
        List<KanrenshaKigyouDtHistory47Entity> list47Old = kanrenshaKigyouDtHistory47Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list47Old.size());

        // 99
        List<KanrenshaKigyouDtHistory99Entity> list99New = kanrenshaKigyouDtHistory99Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list99New.size());
        KanrenshaKigyouDtHistory99Entity entity990 = list99New.get(0);
        assertEquals("ぼったくり企業991a", entity990.getAllName());
        KanrenshaKigyouDtHistory99Entity entity991 = list99New.get(1);
        assertEquals("ぼったくり企業992a", entity991.getAllName());
        List<KanrenshaKigyouDtHistory99Entity> list99Old = kanrenshaKigyouDtHistory99Repository
                .findByKigyouDtKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list99Old.size());
    }

}
