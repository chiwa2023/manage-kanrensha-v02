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
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * MoveCodeHistoryPersonService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("MoveCodeHistoryPersonServiceTest.sql")
class MoveCodeHistoryPersonServiceTest { // NOPMD CouplingObjects

    /** テスト対象 */
    @Autowired
    private MoveCodeHistoryPersonService moveCodeHistoryPersonService;

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

        assertTrue(moveCodeHistoryPersonService.practice(capsuleDto));

        // 01
        List<KanrenshaPersonHistory01Entity> list01New = kanrenshaPersonHistory01Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list01New.size());
        KanrenshaPersonHistory01Entity entity010 = list01New.get(0);
        assertEquals("ぼったくり個人011a", entity010.getAllName());
        KanrenshaPersonHistory01Entity entity011 = list01New.get(1);
        assertEquals("ぼったくり個人012a", entity011.getAllName());
        List<KanrenshaPersonHistory01Entity> list01Old = kanrenshaPersonHistory01Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list01Old.size());

        // 02
        List<KanrenshaPersonHistory02Entity> list02New = kanrenshaPersonHistory02Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list02New.size());
        KanrenshaPersonHistory02Entity entity020 = list02New.get(0);
        assertEquals("ぼったくり個人021a", entity020.getAllName());
        KanrenshaPersonHistory02Entity entity021 = list02New.get(1);
        assertEquals("ぼったくり個人022a", entity021.getAllName());
        List<KanrenshaPersonHistory02Entity> list02Old = kanrenshaPersonHistory02Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list02Old.size());

        // 03
        List<KanrenshaPersonHistory03Entity> list03New = kanrenshaPersonHistory03Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list03New.size());
        KanrenshaPersonHistory03Entity entity030 = list03New.get(0);
        assertEquals("ぼったくり個人031a", entity030.getAllName());
        KanrenshaPersonHistory03Entity entity031 = list03New.get(1);
        assertEquals("ぼったくり個人032a", entity031.getAllName());
        List<KanrenshaPersonHistory03Entity> list03Old = kanrenshaPersonHistory03Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list03Old.size());

        // 04
        List<KanrenshaPersonHistory04Entity> list04New = kanrenshaPersonHistory04Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list04New.size());
        KanrenshaPersonHistory04Entity entity040 = list04New.get(0);
        assertEquals("ぼったくり個人041a", entity040.getAllName());
        KanrenshaPersonHistory04Entity entity041 = list04New.get(1);
        assertEquals("ぼったくり個人042a", entity041.getAllName());
        List<KanrenshaPersonHistory04Entity> list04Old = kanrenshaPersonHistory04Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list04Old.size());

        // 05
        List<KanrenshaPersonHistory05Entity> list05New = kanrenshaPersonHistory05Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list05New.size());
        KanrenshaPersonHistory05Entity entity050 = list05New.get(0);
        assertEquals("ぼったくり個人051a", entity050.getAllName());
        KanrenshaPersonHistory05Entity entity051 = list05New.get(1);
        assertEquals("ぼったくり個人052a", entity051.getAllName());
        List<KanrenshaPersonHistory05Entity> list05Old = kanrenshaPersonHistory05Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list05Old.size());

        // 06
        List<KanrenshaPersonHistory06Entity> list06New = kanrenshaPersonHistory06Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list06New.size());
        KanrenshaPersonHistory06Entity entity060 = list06New.get(0);
        assertEquals("ぼったくり個人061a", entity060.getAllName());
        KanrenshaPersonHistory06Entity entity061 = list06New.get(1);
        assertEquals("ぼったくり個人062a", entity061.getAllName());
        List<KanrenshaPersonHistory06Entity> list06Old = kanrenshaPersonHistory06Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list06Old.size());

        // 07
        List<KanrenshaPersonHistory07Entity> list07New = kanrenshaPersonHistory07Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list07New.size());
        KanrenshaPersonHistory07Entity entity070 = list07New.get(0);
        assertEquals("ぼったくり個人071a", entity070.getAllName());
        KanrenshaPersonHistory07Entity entity071 = list07New.get(1);
        assertEquals("ぼったくり個人072a", entity071.getAllName());
        List<KanrenshaPersonHistory07Entity> list07Old = kanrenshaPersonHistory07Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list07Old.size());

        // 08
        List<KanrenshaPersonHistory08Entity> list08New = kanrenshaPersonHistory08Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list08New.size());
        KanrenshaPersonHistory08Entity entity080 = list08New.get(0);
        assertEquals("ぼったくり個人081a", entity080.getAllName());
        KanrenshaPersonHistory08Entity entity081 = list08New.get(1);
        assertEquals("ぼったくり個人082a", entity081.getAllName());
        List<KanrenshaPersonHistory08Entity> list08Old = kanrenshaPersonHistory08Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list08Old.size());

        // 09
        List<KanrenshaPersonHistory09Entity> list09New = kanrenshaPersonHistory09Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list09New.size());
        KanrenshaPersonHistory09Entity entity090 = list09New.get(0);
        assertEquals("ぼったくり個人091a", entity090.getAllName());
        KanrenshaPersonHistory09Entity entity091 = list09New.get(1);
        assertEquals("ぼったくり個人092a", entity091.getAllName());
        List<KanrenshaPersonHistory09Entity> list09Old = kanrenshaPersonHistory09Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list09Old.size());

        // 10
        List<KanrenshaPersonHistory10Entity> list10New = kanrenshaPersonHistory10Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list10New.size());
        KanrenshaPersonHistory10Entity entity100 = list10New.get(0);
        assertEquals("ぼったくり個人101a", entity100.getAllName());
        KanrenshaPersonHistory10Entity entity101 = list10New.get(1);
        assertEquals("ぼったくり個人102a", entity101.getAllName());
        List<KanrenshaPersonHistory10Entity> list10Old = kanrenshaPersonHistory10Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list10Old.size());


        // 11
        List<KanrenshaPersonHistory11Entity> list11New = kanrenshaPersonHistory11Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list11New.size());
        KanrenshaPersonHistory11Entity entity110 = list11New.get(0);
        assertEquals("ぼったくり個人111a", entity110.getAllName());
        KanrenshaPersonHistory11Entity entity111 = list11New.get(1);
        assertEquals("ぼったくり個人112a", entity111.getAllName());
        List<KanrenshaPersonHistory11Entity> list11Old = kanrenshaPersonHistory11Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list11Old.size());

        // 12
        List<KanrenshaPersonHistory12Entity> list12New = kanrenshaPersonHistory12Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list12New.size());
        KanrenshaPersonHistory12Entity entity120 = list12New.get(0);
        assertEquals("ぼったくり個人121a", entity120.getAllName());
        KanrenshaPersonHistory12Entity entity121 = list12New.get(1);
        assertEquals("ぼったくり個人122a", entity121.getAllName());
        List<KanrenshaPersonHistory12Entity> list12Old = kanrenshaPersonHistory12Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list12Old.size());

        // 13
        List<KanrenshaPersonHistory13Entity> list13New = kanrenshaPersonHistory13Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list13New.size());
        KanrenshaPersonHistory13Entity entity130 = list13New.get(0);
        assertEquals("ぼったくり個人131a", entity130.getAllName());
        KanrenshaPersonHistory13Entity entity131 = list13New.get(1);
        assertEquals("ぼったくり個人132a", entity131.getAllName());
        List<KanrenshaPersonHistory13Entity> list13Old = kanrenshaPersonHistory13Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list13Old.size());

        // 14
        List<KanrenshaPersonHistory14Entity> list14New = kanrenshaPersonHistory14Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list14New.size());
        KanrenshaPersonHistory14Entity entity140 = list14New.get(0);
        assertEquals("ぼったくり個人141a", entity140.getAllName());
        KanrenshaPersonHistory14Entity entity141 = list14New.get(1);
        assertEquals("ぼったくり個人142a", entity141.getAllName());
        List<KanrenshaPersonHistory14Entity> list14Old = kanrenshaPersonHistory14Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list14Old.size());

        // 15
        List<KanrenshaPersonHistory15Entity> list15New = kanrenshaPersonHistory15Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list15New.size());
        KanrenshaPersonHistory15Entity entity150 = list15New.get(0);
        assertEquals("ぼったくり個人151a", entity150.getAllName());
        KanrenshaPersonHistory15Entity entity151 = list15New.get(1);
        assertEquals("ぼったくり個人152a", entity151.getAllName());
        List<KanrenshaPersonHistory15Entity> list15Old = kanrenshaPersonHistory15Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list15Old.size());

        // 16
        List<KanrenshaPersonHistory16Entity> list16New = kanrenshaPersonHistory16Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list16New.size());
        KanrenshaPersonHistory16Entity entity160 = list16New.get(0);
        assertEquals("ぼったくり個人161a", entity160.getAllName());
        KanrenshaPersonHistory16Entity entity161 = list16New.get(1);
        assertEquals("ぼったくり個人162a", entity161.getAllName());
        List<KanrenshaPersonHistory16Entity> list16Old = kanrenshaPersonHistory16Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list16Old.size());

        // 17
        List<KanrenshaPersonHistory17Entity> list17New = kanrenshaPersonHistory17Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list17New.size());
        KanrenshaPersonHistory17Entity entity170 = list17New.get(0);
        assertEquals("ぼったくり個人171a", entity170.getAllName());
        KanrenshaPersonHistory17Entity entity171 = list17New.get(1);
        assertEquals("ぼったくり個人172a", entity171.getAllName());
        List<KanrenshaPersonHistory17Entity> list17Old = kanrenshaPersonHistory17Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list17Old.size());

        // 18
        List<KanrenshaPersonHistory18Entity> list18New = kanrenshaPersonHistory18Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list18New.size());
        KanrenshaPersonHistory18Entity entity180 = list18New.get(0);
        assertEquals("ぼったくり個人181a", entity180.getAllName());
        KanrenshaPersonHistory18Entity entity181 = list18New.get(1);
        assertEquals("ぼったくり個人182a", entity181.getAllName());
        List<KanrenshaPersonHistory18Entity> list18Old = kanrenshaPersonHistory18Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list18Old.size());

        // 19
        List<KanrenshaPersonHistory19Entity> list19New = kanrenshaPersonHistory19Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list19New.size());
        KanrenshaPersonHistory19Entity entity190 = list19New.get(0);
        assertEquals("ぼったくり個人191a", entity190.getAllName());
        KanrenshaPersonHistory19Entity entity191 = list19New.get(1);
        assertEquals("ぼったくり個人192a", entity191.getAllName());
        List<KanrenshaPersonHistory19Entity> list19Old = kanrenshaPersonHistory19Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list19Old.size());

        // 20
        List<KanrenshaPersonHistory20Entity> list20New = kanrenshaPersonHistory20Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list20New.size());
        KanrenshaPersonHistory20Entity entity200 = list20New.get(0);
        assertEquals("ぼったくり個人201a", entity200.getAllName());
        KanrenshaPersonHistory20Entity entity201 = list20New.get(1);
        assertEquals("ぼったくり個人202a", entity201.getAllName());
        List<KanrenshaPersonHistory20Entity> list20Old = kanrenshaPersonHistory20Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list20Old.size());


        // 21
        List<KanrenshaPersonHistory21Entity> list21New = kanrenshaPersonHistory21Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list21New.size());
        KanrenshaPersonHistory21Entity entity210 = list21New.get(0);
        assertEquals("ぼったくり個人211a", entity210.getAllName());
        KanrenshaPersonHistory21Entity entity211 = list21New.get(1);
        assertEquals("ぼったくり個人212a", entity211.getAllName());
        List<KanrenshaPersonHistory21Entity> list21Old = kanrenshaPersonHistory21Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list21Old.size());

        // 22
        List<KanrenshaPersonHistory22Entity> list22New = kanrenshaPersonHistory22Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list22New.size());
        KanrenshaPersonHistory22Entity entity220 = list22New.get(0);
        assertEquals("ぼったくり個人221a", entity220.getAllName());
        KanrenshaPersonHistory22Entity entity221 = list22New.get(1);
        assertEquals("ぼったくり個人222a", entity221.getAllName());
        List<KanrenshaPersonHistory22Entity> list22Old = kanrenshaPersonHistory22Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list22Old.size());

        // 23
        List<KanrenshaPersonHistory23Entity> list23New = kanrenshaPersonHistory23Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list23New.size());
        KanrenshaPersonHistory23Entity entity230 = list23New.get(0);
        assertEquals("ぼったくり個人231a", entity230.getAllName());
        KanrenshaPersonHistory23Entity entity231 = list23New.get(1);
        assertEquals("ぼったくり個人232a", entity231.getAllName());
        List<KanrenshaPersonHistory23Entity> list23Old = kanrenshaPersonHistory23Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list23Old.size());

        // 24
        List<KanrenshaPersonHistory24Entity> list24New = kanrenshaPersonHistory24Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list24New.size());
        KanrenshaPersonHistory24Entity entity240 = list24New.get(0);
        assertEquals("ぼったくり個人241a", entity240.getAllName());
        KanrenshaPersonHistory24Entity entity241 = list24New.get(1);
        assertEquals("ぼったくり個人242a", entity241.getAllName());
        List<KanrenshaPersonHistory24Entity> list24Old = kanrenshaPersonHistory24Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list24Old.size());

        // 25
        List<KanrenshaPersonHistory25Entity> list25New = kanrenshaPersonHistory25Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list25New.size());
        KanrenshaPersonHistory25Entity entity250 = list25New.get(0);
        assertEquals("ぼったくり個人251a", entity250.getAllName());
        KanrenshaPersonHistory25Entity entity251 = list25New.get(1);
        assertEquals("ぼったくり個人252a", entity251.getAllName());
        List<KanrenshaPersonHistory25Entity> list25Old = kanrenshaPersonHistory25Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list25Old.size());

        // 26
        List<KanrenshaPersonHistory26Entity> list26New = kanrenshaPersonHistory26Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list26New.size());
        KanrenshaPersonHistory26Entity entity260 = list26New.get(0);
        assertEquals("ぼったくり個人261a", entity260.getAllName());
        KanrenshaPersonHistory26Entity entity261 = list26New.get(1);
        assertEquals("ぼったくり個人262a", entity261.getAllName());
        List<KanrenshaPersonHistory26Entity> list26Old = kanrenshaPersonHistory26Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list26Old.size());

        // 27
        List<KanrenshaPersonHistory27Entity> list27New = kanrenshaPersonHistory27Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list27New.size());
        KanrenshaPersonHistory27Entity entity270 = list27New.get(0);
        assertEquals("ぼったくり個人271a", entity270.getAllName());
        KanrenshaPersonHistory27Entity entity271 = list27New.get(1);
        assertEquals("ぼったくり個人272a", entity271.getAllName());
        List<KanrenshaPersonHistory27Entity> list27Old = kanrenshaPersonHistory27Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list27Old.size());

        // 28
        List<KanrenshaPersonHistory28Entity> list28New = kanrenshaPersonHistory28Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list28New.size());
        KanrenshaPersonHistory28Entity entity280 = list28New.get(0);
        assertEquals("ぼったくり個人281a", entity280.getAllName());
        KanrenshaPersonHistory28Entity entity281 = list28New.get(1);
        assertEquals("ぼったくり個人282a", entity281.getAllName());
        List<KanrenshaPersonHistory28Entity> list28Old = kanrenshaPersonHistory28Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list28Old.size());

        // 29
        List<KanrenshaPersonHistory29Entity> list29New = kanrenshaPersonHistory29Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list29New.size());
        KanrenshaPersonHistory29Entity entity290 = list29New.get(0);
        assertEquals("ぼったくり個人291a", entity290.getAllName());
        KanrenshaPersonHistory29Entity entity291 = list29New.get(1);
        assertEquals("ぼったくり個人292a", entity291.getAllName());
        List<KanrenshaPersonHistory29Entity> list29Old = kanrenshaPersonHistory29Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list29Old.size());

        // 30
        List<KanrenshaPersonHistory30Entity> list30New = kanrenshaPersonHistory30Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list30New.size());
        KanrenshaPersonHistory30Entity entity300 = list30New.get(0);
        assertEquals("ぼったくり個人301a", entity300.getAllName());
        KanrenshaPersonHistory30Entity entity301 = list30New.get(1);
        assertEquals("ぼったくり個人302a", entity301.getAllName());
        List<KanrenshaPersonHistory30Entity> list30Old = kanrenshaPersonHistory30Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list30Old.size());


        // 31
        List<KanrenshaPersonHistory31Entity> list31New = kanrenshaPersonHistory31Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list31New.size());
        KanrenshaPersonHistory31Entity entity310 = list31New.get(0);
        assertEquals("ぼったくり個人311a", entity310.getAllName());
        KanrenshaPersonHistory31Entity entity311 = list31New.get(1);
        assertEquals("ぼったくり個人312a", entity311.getAllName());
        List<KanrenshaPersonHistory31Entity> list31Old = kanrenshaPersonHistory31Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list31Old.size());

        // 32
        List<KanrenshaPersonHistory32Entity> list32New = kanrenshaPersonHistory32Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list32New.size());
        KanrenshaPersonHistory32Entity entity320 = list32New.get(0);
        assertEquals("ぼったくり個人321a", entity320.getAllName());
        KanrenshaPersonHistory32Entity entity321 = list32New.get(1);
        assertEquals("ぼったくり個人322a", entity321.getAllName());
        List<KanrenshaPersonHistory32Entity> list32Old = kanrenshaPersonHistory32Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list32Old.size());

        // 33
        List<KanrenshaPersonHistory33Entity> list33New = kanrenshaPersonHistory33Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list33New.size());
        KanrenshaPersonHistory33Entity entity330 = list33New.get(0);
        assertEquals("ぼったくり個人331a", entity330.getAllName());
        KanrenshaPersonHistory33Entity entity331 = list33New.get(1);
        assertEquals("ぼったくり個人332a", entity331.getAllName());
        List<KanrenshaPersonHistory33Entity> list33Old = kanrenshaPersonHistory33Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list33Old.size());

        // 34
        List<KanrenshaPersonHistory34Entity> list34New = kanrenshaPersonHistory34Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list34New.size());
        KanrenshaPersonHistory34Entity entity340 = list34New.get(0);
        assertEquals("ぼったくり個人341a", entity340.getAllName());
        KanrenshaPersonHistory34Entity entity341 = list34New.get(1);
        assertEquals("ぼったくり個人342a", entity341.getAllName());
        List<KanrenshaPersonHistory34Entity> list34Old = kanrenshaPersonHistory34Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list34Old.size());

        // 35
        List<KanrenshaPersonHistory35Entity> list35New = kanrenshaPersonHistory35Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list35New.size());
        KanrenshaPersonHistory35Entity entity350 = list35New.get(0);
        assertEquals("ぼったくり個人351a", entity350.getAllName());
        KanrenshaPersonHistory35Entity entity351 = list35New.get(1);
        assertEquals("ぼったくり個人352a", entity351.getAllName());
        List<KanrenshaPersonHistory35Entity> list35Old = kanrenshaPersonHistory35Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list35Old.size());

        // 36
        List<KanrenshaPersonHistory36Entity> list36New = kanrenshaPersonHistory36Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list36New.size());
        KanrenshaPersonHistory36Entity entity360 = list36New.get(0);
        assertEquals("ぼったくり個人361a", entity360.getAllName());
        KanrenshaPersonHistory36Entity entity361 = list36New.get(1);
        assertEquals("ぼったくり個人362a", entity361.getAllName());
        List<KanrenshaPersonHistory36Entity> list36Old = kanrenshaPersonHistory36Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list36Old.size());

        // 37
        List<KanrenshaPersonHistory37Entity> list37New = kanrenshaPersonHistory37Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list37New.size());
        KanrenshaPersonHistory37Entity entity370 = list37New.get(0);
        assertEquals("ぼったくり個人371a", entity370.getAllName());
        KanrenshaPersonHistory37Entity entity371 = list37New.get(1);
        assertEquals("ぼったくり個人372a", entity371.getAllName());
        List<KanrenshaPersonHistory37Entity> list37Old = kanrenshaPersonHistory37Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list37Old.size());

        // 38
        List<KanrenshaPersonHistory38Entity> list38New = kanrenshaPersonHistory38Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list38New.size());
        KanrenshaPersonHistory38Entity entity380 = list38New.get(0);
        assertEquals("ぼったくり個人381a", entity380.getAllName());
        KanrenshaPersonHistory38Entity entity381 = list38New.get(1);
        assertEquals("ぼったくり個人382a", entity381.getAllName());
        List<KanrenshaPersonHistory38Entity> list38Old = kanrenshaPersonHistory38Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list38Old.size());

        // 39
        List<KanrenshaPersonHistory39Entity> list39New = kanrenshaPersonHistory39Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list39New.size());
        KanrenshaPersonHistory39Entity entity390 = list39New.get(0);
        assertEquals("ぼったくり個人391a", entity390.getAllName());
        KanrenshaPersonHistory39Entity entity391 = list39New.get(1);
        assertEquals("ぼったくり個人392a", entity391.getAllName());
        List<KanrenshaPersonHistory39Entity> list39Old = kanrenshaPersonHistory39Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list39Old.size());

        // 40
        List<KanrenshaPersonHistory40Entity> list40New = kanrenshaPersonHistory40Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list40New.size());
        KanrenshaPersonHistory40Entity entity400 = list40New.get(0);
        assertEquals("ぼったくり個人401a", entity400.getAllName());
        KanrenshaPersonHistory40Entity entity401 = list40New.get(1);
        assertEquals("ぼったくり個人402a", entity401.getAllName());
        List<KanrenshaPersonHistory40Entity> list40Old = kanrenshaPersonHistory40Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list40Old.size());


        // 41
        List<KanrenshaPersonHistory41Entity> list41New = kanrenshaPersonHistory41Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list41New.size());
        KanrenshaPersonHistory41Entity entity410 = list41New.get(0);
        assertEquals("ぼったくり個人411a", entity410.getAllName());
        KanrenshaPersonHistory41Entity entity411 = list41New.get(1);
        assertEquals("ぼったくり個人412a", entity411.getAllName());
        List<KanrenshaPersonHistory41Entity> list41Old = kanrenshaPersonHistory41Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list41Old.size());

        // 42
        List<KanrenshaPersonHistory42Entity> list42New = kanrenshaPersonHistory42Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list42New.size());
        KanrenshaPersonHistory42Entity entity420 = list42New.get(0);
        assertEquals("ぼったくり個人421a", entity420.getAllName());
        KanrenshaPersonHistory42Entity entity421 = list42New.get(1);
        assertEquals("ぼったくり個人422a", entity421.getAllName());
        List<KanrenshaPersonHistory42Entity> list42Old = kanrenshaPersonHistory42Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list42Old.size());

        // 43
        List<KanrenshaPersonHistory43Entity> list43New = kanrenshaPersonHistory43Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list43New.size());
        KanrenshaPersonHistory43Entity entity430 = list43New.get(0);
        assertEquals("ぼったくり個人431a", entity430.getAllName());
        KanrenshaPersonHistory43Entity entity431 = list43New.get(1);
        assertEquals("ぼったくり個人432a", entity431.getAllName());
        List<KanrenshaPersonHistory43Entity> list43Old = kanrenshaPersonHistory43Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list43Old.size());

        // 44
        List<KanrenshaPersonHistory44Entity> list44New = kanrenshaPersonHistory44Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list44New.size());
        KanrenshaPersonHistory44Entity entity440 = list44New.get(0);
        assertEquals("ぼったくり個人441a", entity440.getAllName());
        KanrenshaPersonHistory44Entity entity441 = list44New.get(1);
        assertEquals("ぼったくり個人442a", entity441.getAllName());
        List<KanrenshaPersonHistory44Entity> list44Old = kanrenshaPersonHistory44Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list44Old.size());

        // 45
        List<KanrenshaPersonHistory45Entity> list45New = kanrenshaPersonHistory45Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list45New.size());
        KanrenshaPersonHistory45Entity entity450 = list45New.get(0);
        assertEquals("ぼったくり個人451a", entity450.getAllName());
        KanrenshaPersonHistory45Entity entity451 = list45New.get(1);
        assertEquals("ぼったくり個人452a", entity451.getAllName());
        List<KanrenshaPersonHistory45Entity> list45Old = kanrenshaPersonHistory45Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list45Old.size());

        // 46
        List<KanrenshaPersonHistory46Entity> list46New = kanrenshaPersonHistory46Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list46New.size());
        KanrenshaPersonHistory46Entity entity460 = list46New.get(0);
        assertEquals("ぼったくり個人461a", entity460.getAllName());
        KanrenshaPersonHistory46Entity entity461 = list46New.get(1);
        assertEquals("ぼったくり個人462a", entity461.getAllName());
        List<KanrenshaPersonHistory46Entity> list46Old = kanrenshaPersonHistory46Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list46Old.size());

        // 47
        List<KanrenshaPersonHistory47Entity> list47New = kanrenshaPersonHistory47Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list47New.size());
        KanrenshaPersonHistory47Entity entity470 = list47New.get(0);
        assertEquals("ぼったくり個人471a", entity470.getAllName());
        KanrenshaPersonHistory47Entity entity471 = list47New.get(1);
        assertEquals("ぼったくり個人472a", entity471.getAllName());
        List<KanrenshaPersonHistory47Entity> list47Old = kanrenshaPersonHistory47Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list47Old.size());

        // 99
        List<KanrenshaPersonHistory99Entity> list99New = kanrenshaPersonHistory99Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list99New.size());
        KanrenshaPersonHistory99Entity entity990 = list99New.get(0);
        assertEquals("ぼったくり個人991a", entity990.getAllName());
        KanrenshaPersonHistory99Entity entity991 = list99New.get(1);
        assertEquals("ぼったくり個人992a", entity991.getAllName());
        List<KanrenshaPersonHistory99Entity> list99Old = kanrenshaPersonHistory99Repository
                .findByPersonKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list99Old.size());
    }

}