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
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * MoveCodeHistorySeijidantaiService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("MoveCodeHistorySeijidantaiServiceTest.sql")
class MoveCodeHistorySeijidantaiServiceTest { // NOPMD CouplingObjects

    /** テスト対象 */
    @Autowired
    private MoveCodeHistorySeijidantaiService moveCodeHistorySeijidantaiService;

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

        assertTrue(moveCodeHistorySeijidantaiService.practice(capsuleDto));

        // 01
        List<KanrenshaSeijidantaiHistory01Entity> list01New = kanrenshaSeijidantaiHistory01Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list01New.size());
        KanrenshaSeijidantaiHistory01Entity entity010 = list01New.get(0);
        assertEquals("ぼったくり政治団体011a", entity010.getAllName());
        KanrenshaSeijidantaiHistory01Entity entity011 = list01New.get(1);
        assertEquals("ぼったくり政治団体012a", entity011.getAllName());
        List<KanrenshaSeijidantaiHistory01Entity> list01Old = kanrenshaSeijidantaiHistory01Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list01Old.size());

        // 02
        List<KanrenshaSeijidantaiHistory02Entity> list02New = kanrenshaSeijidantaiHistory02Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list02New.size());
        KanrenshaSeijidantaiHistory02Entity entity020 = list02New.get(0);
        assertEquals("ぼったくり政治団体021a", entity020.getAllName());
        KanrenshaSeijidantaiHistory02Entity entity021 = list02New.get(1);
        assertEquals("ぼったくり政治団体022a", entity021.getAllName());
        List<KanrenshaSeijidantaiHistory02Entity> list02Old = kanrenshaSeijidantaiHistory02Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list02Old.size());

        // 03
        List<KanrenshaSeijidantaiHistory03Entity> list03New = kanrenshaSeijidantaiHistory03Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list03New.size());
        KanrenshaSeijidantaiHistory03Entity entity030 = list03New.get(0);
        assertEquals("ぼったくり政治団体031a", entity030.getAllName());
        KanrenshaSeijidantaiHistory03Entity entity031 = list03New.get(1);
        assertEquals("ぼったくり政治団体032a", entity031.getAllName());
        List<KanrenshaSeijidantaiHistory03Entity> list03Old = kanrenshaSeijidantaiHistory03Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list03Old.size());

        // 04
        List<KanrenshaSeijidantaiHistory04Entity> list04New = kanrenshaSeijidantaiHistory04Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list04New.size());
        KanrenshaSeijidantaiHistory04Entity entity040 = list04New.get(0);
        assertEquals("ぼったくり政治団体041a", entity040.getAllName());
        KanrenshaSeijidantaiHistory04Entity entity041 = list04New.get(1);
        assertEquals("ぼったくり政治団体042a", entity041.getAllName());
        List<KanrenshaSeijidantaiHistory04Entity> list04Old = kanrenshaSeijidantaiHistory04Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list04Old.size());

        // 05
        List<KanrenshaSeijidantaiHistory05Entity> list05New = kanrenshaSeijidantaiHistory05Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list05New.size());
        KanrenshaSeijidantaiHistory05Entity entity050 = list05New.get(0);
        assertEquals("ぼったくり政治団体051a", entity050.getAllName());
        KanrenshaSeijidantaiHistory05Entity entity051 = list05New.get(1);
        assertEquals("ぼったくり政治団体052a", entity051.getAllName());
        List<KanrenshaSeijidantaiHistory05Entity> list05Old = kanrenshaSeijidantaiHistory05Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list05Old.size());

        // 06
        List<KanrenshaSeijidantaiHistory06Entity> list06New = kanrenshaSeijidantaiHistory06Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list06New.size());
        KanrenshaSeijidantaiHistory06Entity entity060 = list06New.get(0);
        assertEquals("ぼったくり政治団体061a", entity060.getAllName());
        KanrenshaSeijidantaiHistory06Entity entity061 = list06New.get(1);
        assertEquals("ぼったくり政治団体062a", entity061.getAllName());
        List<KanrenshaSeijidantaiHistory06Entity> list06Old = kanrenshaSeijidantaiHistory06Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list06Old.size());

        // 07
        List<KanrenshaSeijidantaiHistory07Entity> list07New = kanrenshaSeijidantaiHistory07Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list07New.size());
        KanrenshaSeijidantaiHistory07Entity entity070 = list07New.get(0);
        assertEquals("ぼったくり政治団体071a", entity070.getAllName());
        KanrenshaSeijidantaiHistory07Entity entity071 = list07New.get(1);
        assertEquals("ぼったくり政治団体072a", entity071.getAllName());
        List<KanrenshaSeijidantaiHistory07Entity> list07Old = kanrenshaSeijidantaiHistory07Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list07Old.size());

        // 08
        List<KanrenshaSeijidantaiHistory08Entity> list08New = kanrenshaSeijidantaiHistory08Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list08New.size());
        KanrenshaSeijidantaiHistory08Entity entity080 = list08New.get(0);
        assertEquals("ぼったくり政治団体081a", entity080.getAllName());
        KanrenshaSeijidantaiHistory08Entity entity081 = list08New.get(1);
        assertEquals("ぼったくり政治団体082a", entity081.getAllName());
        List<KanrenshaSeijidantaiHistory08Entity> list08Old = kanrenshaSeijidantaiHistory08Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list08Old.size());

        // 09
        List<KanrenshaSeijidantaiHistory09Entity> list09New = kanrenshaSeijidantaiHistory09Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list09New.size());
        KanrenshaSeijidantaiHistory09Entity entity090 = list09New.get(0);
        assertEquals("ぼったくり政治団体091a", entity090.getAllName());
        KanrenshaSeijidantaiHistory09Entity entity091 = list09New.get(1);
        assertEquals("ぼったくり政治団体092a", entity091.getAllName());
        List<KanrenshaSeijidantaiHistory09Entity> list09Old = kanrenshaSeijidantaiHistory09Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list09Old.size());

        // 10
        List<KanrenshaSeijidantaiHistory10Entity> list10New = kanrenshaSeijidantaiHistory10Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list10New.size());
        KanrenshaSeijidantaiHistory10Entity entity100 = list10New.get(0);
        assertEquals("ぼったくり政治団体101a", entity100.getAllName());
        KanrenshaSeijidantaiHistory10Entity entity101 = list10New.get(1);
        assertEquals("ぼったくり政治団体102a", entity101.getAllName());
        List<KanrenshaSeijidantaiHistory10Entity> list10Old = kanrenshaSeijidantaiHistory10Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list10Old.size());


        // 11
        List<KanrenshaSeijidantaiHistory11Entity> list11New = kanrenshaSeijidantaiHistory11Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list11New.size());
        KanrenshaSeijidantaiHistory11Entity entity110 = list11New.get(0);
        assertEquals("ぼったくり政治団体111a", entity110.getAllName());
        KanrenshaSeijidantaiHistory11Entity entity111 = list11New.get(1);
        assertEquals("ぼったくり政治団体112a", entity111.getAllName());
        List<KanrenshaSeijidantaiHistory11Entity> list11Old = kanrenshaSeijidantaiHistory11Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list11Old.size());

        // 12
        List<KanrenshaSeijidantaiHistory12Entity> list12New = kanrenshaSeijidantaiHistory12Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list12New.size());
        KanrenshaSeijidantaiHistory12Entity entity120 = list12New.get(0);
        assertEquals("ぼったくり政治団体121a", entity120.getAllName());
        KanrenshaSeijidantaiHistory12Entity entity121 = list12New.get(1);
        assertEquals("ぼったくり政治団体122a", entity121.getAllName());
        List<KanrenshaSeijidantaiHistory12Entity> list12Old = kanrenshaSeijidantaiHistory12Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list12Old.size());

        // 13
        List<KanrenshaSeijidantaiHistory13Entity> list13New = kanrenshaSeijidantaiHistory13Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list13New.size());
        KanrenshaSeijidantaiHistory13Entity entity130 = list13New.get(0);
        assertEquals("ぼったくり政治団体131a", entity130.getAllName());
        KanrenshaSeijidantaiHistory13Entity entity131 = list13New.get(1);
        assertEquals("ぼったくり政治団体132a", entity131.getAllName());
        List<KanrenshaSeijidantaiHistory13Entity> list13Old = kanrenshaSeijidantaiHistory13Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list13Old.size());

        // 14
        List<KanrenshaSeijidantaiHistory14Entity> list14New = kanrenshaSeijidantaiHistory14Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list14New.size());
        KanrenshaSeijidantaiHistory14Entity entity140 = list14New.get(0);
        assertEquals("ぼったくり政治団体141a", entity140.getAllName());
        KanrenshaSeijidantaiHistory14Entity entity141 = list14New.get(1);
        assertEquals("ぼったくり政治団体142a", entity141.getAllName());
        List<KanrenshaSeijidantaiHistory14Entity> list14Old = kanrenshaSeijidantaiHistory14Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list14Old.size());

        // 15
        List<KanrenshaSeijidantaiHistory15Entity> list15New = kanrenshaSeijidantaiHistory15Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list15New.size());
        KanrenshaSeijidantaiHistory15Entity entity150 = list15New.get(0);
        assertEquals("ぼったくり政治団体151a", entity150.getAllName());
        KanrenshaSeijidantaiHistory15Entity entity151 = list15New.get(1);
        assertEquals("ぼったくり政治団体152a", entity151.getAllName());
        List<KanrenshaSeijidantaiHistory15Entity> list15Old = kanrenshaSeijidantaiHistory15Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list15Old.size());

        // 16
        List<KanrenshaSeijidantaiHistory16Entity> list16New = kanrenshaSeijidantaiHistory16Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list16New.size());
        KanrenshaSeijidantaiHistory16Entity entity160 = list16New.get(0);
        assertEquals("ぼったくり政治団体161a", entity160.getAllName());
        KanrenshaSeijidantaiHistory16Entity entity161 = list16New.get(1);
        assertEquals("ぼったくり政治団体162a", entity161.getAllName());
        List<KanrenshaSeijidantaiHistory16Entity> list16Old = kanrenshaSeijidantaiHistory16Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list16Old.size());

        // 17
        List<KanrenshaSeijidantaiHistory17Entity> list17New = kanrenshaSeijidantaiHistory17Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list17New.size());
        KanrenshaSeijidantaiHistory17Entity entity170 = list17New.get(0);
        assertEquals("ぼったくり政治団体171a", entity170.getAllName());
        KanrenshaSeijidantaiHistory17Entity entity171 = list17New.get(1);
        assertEquals("ぼったくり政治団体172a", entity171.getAllName());
        List<KanrenshaSeijidantaiHistory17Entity> list17Old = kanrenshaSeijidantaiHistory17Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list17Old.size());

        // 18
        List<KanrenshaSeijidantaiHistory18Entity> list18New = kanrenshaSeijidantaiHistory18Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list18New.size());
        KanrenshaSeijidantaiHistory18Entity entity180 = list18New.get(0);
        assertEquals("ぼったくり政治団体181a", entity180.getAllName());
        KanrenshaSeijidantaiHistory18Entity entity181 = list18New.get(1);
        assertEquals("ぼったくり政治団体182a", entity181.getAllName());
        List<KanrenshaSeijidantaiHistory18Entity> list18Old = kanrenshaSeijidantaiHistory18Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list18Old.size());

        // 19
        List<KanrenshaSeijidantaiHistory19Entity> list19New = kanrenshaSeijidantaiHistory19Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list19New.size());
        KanrenshaSeijidantaiHistory19Entity entity190 = list19New.get(0);
        assertEquals("ぼったくり政治団体191a", entity190.getAllName());
        KanrenshaSeijidantaiHistory19Entity entity191 = list19New.get(1);
        assertEquals("ぼったくり政治団体192a", entity191.getAllName());
        List<KanrenshaSeijidantaiHistory19Entity> list19Old = kanrenshaSeijidantaiHistory19Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list19Old.size());

        // 20
        List<KanrenshaSeijidantaiHistory20Entity> list20New = kanrenshaSeijidantaiHistory20Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list20New.size());
        KanrenshaSeijidantaiHistory20Entity entity200 = list20New.get(0);
        assertEquals("ぼったくり政治団体201a", entity200.getAllName());
        KanrenshaSeijidantaiHistory20Entity entity201 = list20New.get(1);
        assertEquals("ぼったくり政治団体202a", entity201.getAllName());
        List<KanrenshaSeijidantaiHistory20Entity> list20Old = kanrenshaSeijidantaiHistory20Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list20Old.size());


        // 21
        List<KanrenshaSeijidantaiHistory21Entity> list21New = kanrenshaSeijidantaiHistory21Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list21New.size());
        KanrenshaSeijidantaiHistory21Entity entity210 = list21New.get(0);
        assertEquals("ぼったくり政治団体211a", entity210.getAllName());
        KanrenshaSeijidantaiHistory21Entity entity211 = list21New.get(1);
        assertEquals("ぼったくり政治団体212a", entity211.getAllName());
        List<KanrenshaSeijidantaiHistory21Entity> list21Old = kanrenshaSeijidantaiHistory21Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list21Old.size());

        // 22
        List<KanrenshaSeijidantaiHistory22Entity> list22New = kanrenshaSeijidantaiHistory22Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list22New.size());
        KanrenshaSeijidantaiHistory22Entity entity220 = list22New.get(0);
        assertEquals("ぼったくり政治団体221a", entity220.getAllName());
        KanrenshaSeijidantaiHistory22Entity entity221 = list22New.get(1);
        assertEquals("ぼったくり政治団体222a", entity221.getAllName());
        List<KanrenshaSeijidantaiHistory22Entity> list22Old = kanrenshaSeijidantaiHistory22Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list22Old.size());

        // 23
        List<KanrenshaSeijidantaiHistory23Entity> list23New = kanrenshaSeijidantaiHistory23Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list23New.size());
        KanrenshaSeijidantaiHistory23Entity entity230 = list23New.get(0);
        assertEquals("ぼったくり政治団体231a", entity230.getAllName());
        KanrenshaSeijidantaiHistory23Entity entity231 = list23New.get(1);
        assertEquals("ぼったくり政治団体232a", entity231.getAllName());
        List<KanrenshaSeijidantaiHistory23Entity> list23Old = kanrenshaSeijidantaiHistory23Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list23Old.size());

        // 24
        List<KanrenshaSeijidantaiHistory24Entity> list24New = kanrenshaSeijidantaiHistory24Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list24New.size());
        KanrenshaSeijidantaiHistory24Entity entity240 = list24New.get(0);
        assertEquals("ぼったくり政治団体241a", entity240.getAllName());
        KanrenshaSeijidantaiHistory24Entity entity241 = list24New.get(1);
        assertEquals("ぼったくり政治団体242a", entity241.getAllName());
        List<KanrenshaSeijidantaiHistory24Entity> list24Old = kanrenshaSeijidantaiHistory24Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list24Old.size());

        // 25
        List<KanrenshaSeijidantaiHistory25Entity> list25New = kanrenshaSeijidantaiHistory25Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list25New.size());
        KanrenshaSeijidantaiHistory25Entity entity250 = list25New.get(0);
        assertEquals("ぼったくり政治団体251a", entity250.getAllName());
        KanrenshaSeijidantaiHistory25Entity entity251 = list25New.get(1);
        assertEquals("ぼったくり政治団体252a", entity251.getAllName());
        List<KanrenshaSeijidantaiHistory25Entity> list25Old = kanrenshaSeijidantaiHistory25Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list25Old.size());

        // 26
        List<KanrenshaSeijidantaiHistory26Entity> list26New = kanrenshaSeijidantaiHistory26Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list26New.size());
        KanrenshaSeijidantaiHistory26Entity entity260 = list26New.get(0);
        assertEquals("ぼったくり政治団体261a", entity260.getAllName());
        KanrenshaSeijidantaiHistory26Entity entity261 = list26New.get(1);
        assertEquals("ぼったくり政治団体262a", entity261.getAllName());
        List<KanrenshaSeijidantaiHistory26Entity> list26Old = kanrenshaSeijidantaiHistory26Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list26Old.size());

        // 27
        List<KanrenshaSeijidantaiHistory27Entity> list27New = kanrenshaSeijidantaiHistory27Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list27New.size());
        KanrenshaSeijidantaiHistory27Entity entity270 = list27New.get(0);
        assertEquals("ぼったくり政治団体271a", entity270.getAllName());
        KanrenshaSeijidantaiHistory27Entity entity271 = list27New.get(1);
        assertEquals("ぼったくり政治団体272a", entity271.getAllName());
        List<KanrenshaSeijidantaiHistory27Entity> list27Old = kanrenshaSeijidantaiHistory27Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list27Old.size());

        // 28
        List<KanrenshaSeijidantaiHistory28Entity> list28New = kanrenshaSeijidantaiHistory28Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list28New.size());
        KanrenshaSeijidantaiHistory28Entity entity280 = list28New.get(0);
        assertEquals("ぼったくり政治団体281a", entity280.getAllName());
        KanrenshaSeijidantaiHistory28Entity entity281 = list28New.get(1);
        assertEquals("ぼったくり政治団体282a", entity281.getAllName());
        List<KanrenshaSeijidantaiHistory28Entity> list28Old = kanrenshaSeijidantaiHistory28Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list28Old.size());

        // 29
        List<KanrenshaSeijidantaiHistory29Entity> list29New = kanrenshaSeijidantaiHistory29Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list29New.size());
        KanrenshaSeijidantaiHistory29Entity entity290 = list29New.get(0);
        assertEquals("ぼったくり政治団体291a", entity290.getAllName());
        KanrenshaSeijidantaiHistory29Entity entity291 = list29New.get(1);
        assertEquals("ぼったくり政治団体292a", entity291.getAllName());
        List<KanrenshaSeijidantaiHistory29Entity> list29Old = kanrenshaSeijidantaiHistory29Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list29Old.size());

        // 30
        List<KanrenshaSeijidantaiHistory30Entity> list30New = kanrenshaSeijidantaiHistory30Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list30New.size());
        KanrenshaSeijidantaiHistory30Entity entity300 = list30New.get(0);
        assertEquals("ぼったくり政治団体301a", entity300.getAllName());
        KanrenshaSeijidantaiHistory30Entity entity301 = list30New.get(1);
        assertEquals("ぼったくり政治団体302a", entity301.getAllName());
        List<KanrenshaSeijidantaiHistory30Entity> list30Old = kanrenshaSeijidantaiHistory30Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list30Old.size());


        // 31
        List<KanrenshaSeijidantaiHistory31Entity> list31New = kanrenshaSeijidantaiHistory31Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list31New.size());
        KanrenshaSeijidantaiHistory31Entity entity310 = list31New.get(0);
        assertEquals("ぼったくり政治団体311a", entity310.getAllName());
        KanrenshaSeijidantaiHistory31Entity entity311 = list31New.get(1);
        assertEquals("ぼったくり政治団体312a", entity311.getAllName());
        List<KanrenshaSeijidantaiHistory31Entity> list31Old = kanrenshaSeijidantaiHistory31Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list31Old.size());

        // 32
        List<KanrenshaSeijidantaiHistory32Entity> list32New = kanrenshaSeijidantaiHistory32Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list32New.size());
        KanrenshaSeijidantaiHistory32Entity entity320 = list32New.get(0);
        assertEquals("ぼったくり政治団体321a", entity320.getAllName());
        KanrenshaSeijidantaiHistory32Entity entity321 = list32New.get(1);
        assertEquals("ぼったくり政治団体322a", entity321.getAllName());
        List<KanrenshaSeijidantaiHistory32Entity> list32Old = kanrenshaSeijidantaiHistory32Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list32Old.size());

        // 33
        List<KanrenshaSeijidantaiHistory33Entity> list33New = kanrenshaSeijidantaiHistory33Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list33New.size());
        KanrenshaSeijidantaiHistory33Entity entity330 = list33New.get(0);
        assertEquals("ぼったくり政治団体331a", entity330.getAllName());
        KanrenshaSeijidantaiHistory33Entity entity331 = list33New.get(1);
        assertEquals("ぼったくり政治団体332a", entity331.getAllName());
        List<KanrenshaSeijidantaiHistory33Entity> list33Old = kanrenshaSeijidantaiHistory33Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list33Old.size());

        // 34
        List<KanrenshaSeijidantaiHistory34Entity> list34New = kanrenshaSeijidantaiHistory34Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list34New.size());
        KanrenshaSeijidantaiHistory34Entity entity340 = list34New.get(0);
        assertEquals("ぼったくり政治団体341a", entity340.getAllName());
        KanrenshaSeijidantaiHistory34Entity entity341 = list34New.get(1);
        assertEquals("ぼったくり政治団体342a", entity341.getAllName());
        List<KanrenshaSeijidantaiHistory34Entity> list34Old = kanrenshaSeijidantaiHistory34Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list34Old.size());

        // 35
        List<KanrenshaSeijidantaiHistory35Entity> list35New = kanrenshaSeijidantaiHistory35Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list35New.size());
        KanrenshaSeijidantaiHistory35Entity entity350 = list35New.get(0);
        assertEquals("ぼったくり政治団体351a", entity350.getAllName());
        KanrenshaSeijidantaiHistory35Entity entity351 = list35New.get(1);
        assertEquals("ぼったくり政治団体352a", entity351.getAllName());
        List<KanrenshaSeijidantaiHistory35Entity> list35Old = kanrenshaSeijidantaiHistory35Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list35Old.size());

        // 36
        List<KanrenshaSeijidantaiHistory36Entity> list36New = kanrenshaSeijidantaiHistory36Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list36New.size());
        KanrenshaSeijidantaiHistory36Entity entity360 = list36New.get(0);
        assertEquals("ぼったくり政治団体361a", entity360.getAllName());
        KanrenshaSeijidantaiHistory36Entity entity361 = list36New.get(1);
        assertEquals("ぼったくり政治団体362a", entity361.getAllName());
        List<KanrenshaSeijidantaiHistory36Entity> list36Old = kanrenshaSeijidantaiHistory36Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list36Old.size());

        // 37
        List<KanrenshaSeijidantaiHistory37Entity> list37New = kanrenshaSeijidantaiHistory37Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list37New.size());
        KanrenshaSeijidantaiHistory37Entity entity370 = list37New.get(0);
        assertEquals("ぼったくり政治団体371a", entity370.getAllName());
        KanrenshaSeijidantaiHistory37Entity entity371 = list37New.get(1);
        assertEquals("ぼったくり政治団体372a", entity371.getAllName());
        List<KanrenshaSeijidantaiHistory37Entity> list37Old = kanrenshaSeijidantaiHistory37Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list37Old.size());

        // 38
        List<KanrenshaSeijidantaiHistory38Entity> list38New = kanrenshaSeijidantaiHistory38Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list38New.size());
        KanrenshaSeijidantaiHistory38Entity entity380 = list38New.get(0);
        assertEquals("ぼったくり政治団体381a", entity380.getAllName());
        KanrenshaSeijidantaiHistory38Entity entity381 = list38New.get(1);
        assertEquals("ぼったくり政治団体382a", entity381.getAllName());
        List<KanrenshaSeijidantaiHistory38Entity> list38Old = kanrenshaSeijidantaiHistory38Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list38Old.size());

        // 39
        List<KanrenshaSeijidantaiHistory39Entity> list39New = kanrenshaSeijidantaiHistory39Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list39New.size());
        KanrenshaSeijidantaiHistory39Entity entity390 = list39New.get(0);
        assertEquals("ぼったくり政治団体391a", entity390.getAllName());
        KanrenshaSeijidantaiHistory39Entity entity391 = list39New.get(1);
        assertEquals("ぼったくり政治団体392a", entity391.getAllName());
        List<KanrenshaSeijidantaiHistory39Entity> list39Old = kanrenshaSeijidantaiHistory39Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list39Old.size());

        // 40
        List<KanrenshaSeijidantaiHistory40Entity> list40New = kanrenshaSeijidantaiHistory40Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list40New.size());
        KanrenshaSeijidantaiHistory40Entity entity400 = list40New.get(0);
        assertEquals("ぼったくり政治団体401a", entity400.getAllName());
        KanrenshaSeijidantaiHistory40Entity entity401 = list40New.get(1);
        assertEquals("ぼったくり政治団体402a", entity401.getAllName());
        List<KanrenshaSeijidantaiHistory40Entity> list40Old = kanrenshaSeijidantaiHistory40Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list40Old.size());


        // 41
        List<KanrenshaSeijidantaiHistory41Entity> list41New = kanrenshaSeijidantaiHistory41Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list41New.size());
        KanrenshaSeijidantaiHistory41Entity entity410 = list41New.get(0);
        assertEquals("ぼったくり政治団体411a", entity410.getAllName());
        KanrenshaSeijidantaiHistory41Entity entity411 = list41New.get(1);
        assertEquals("ぼったくり政治団体412a", entity411.getAllName());
        List<KanrenshaSeijidantaiHistory41Entity> list41Old = kanrenshaSeijidantaiHistory41Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list41Old.size());

        // 42
        List<KanrenshaSeijidantaiHistory42Entity> list42New = kanrenshaSeijidantaiHistory42Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list42New.size());
        KanrenshaSeijidantaiHistory42Entity entity420 = list42New.get(0);
        assertEquals("ぼったくり政治団体421a", entity420.getAllName());
        KanrenshaSeijidantaiHistory42Entity entity421 = list42New.get(1);
        assertEquals("ぼったくり政治団体422a", entity421.getAllName());
        List<KanrenshaSeijidantaiHistory42Entity> list42Old = kanrenshaSeijidantaiHistory42Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list42Old.size());

        // 43
        List<KanrenshaSeijidantaiHistory43Entity> list43New = kanrenshaSeijidantaiHistory43Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list43New.size());
        KanrenshaSeijidantaiHistory43Entity entity430 = list43New.get(0);
        assertEquals("ぼったくり政治団体431a", entity430.getAllName());
        KanrenshaSeijidantaiHistory43Entity entity431 = list43New.get(1);
        assertEquals("ぼったくり政治団体432a", entity431.getAllName());
        List<KanrenshaSeijidantaiHistory43Entity> list43Old = kanrenshaSeijidantaiHistory43Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list43Old.size());

        // 44
        List<KanrenshaSeijidantaiHistory44Entity> list44New = kanrenshaSeijidantaiHistory44Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list44New.size());
        KanrenshaSeijidantaiHistory44Entity entity440 = list44New.get(0);
        assertEquals("ぼったくり政治団体441a", entity440.getAllName());
        KanrenshaSeijidantaiHistory44Entity entity441 = list44New.get(1);
        assertEquals("ぼったくり政治団体442a", entity441.getAllName());
        List<KanrenshaSeijidantaiHistory44Entity> list44Old = kanrenshaSeijidantaiHistory44Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list44Old.size());

        // 45
        List<KanrenshaSeijidantaiHistory45Entity> list45New = kanrenshaSeijidantaiHistory45Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list45New.size());
        KanrenshaSeijidantaiHistory45Entity entity450 = list45New.get(0);
        assertEquals("ぼったくり政治団体451a", entity450.getAllName());
        KanrenshaSeijidantaiHistory45Entity entity451 = list45New.get(1);
        assertEquals("ぼったくり政治団体452a", entity451.getAllName());
        List<KanrenshaSeijidantaiHistory45Entity> list45Old = kanrenshaSeijidantaiHistory45Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list45Old.size());

        // 46
        List<KanrenshaSeijidantaiHistory46Entity> list46New = kanrenshaSeijidantaiHistory46Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list46New.size());
        KanrenshaSeijidantaiHistory46Entity entity460 = list46New.get(0);
        assertEquals("ぼったくり政治団体461a", entity460.getAllName());
        KanrenshaSeijidantaiHistory46Entity entity461 = list46New.get(1);
        assertEquals("ぼったくり政治団体462a", entity461.getAllName());
        List<KanrenshaSeijidantaiHistory46Entity> list46Old = kanrenshaSeijidantaiHistory46Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list46Old.size());

        // 47
        List<KanrenshaSeijidantaiHistory47Entity> list47New = kanrenshaSeijidantaiHistory47Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list47New.size());
        KanrenshaSeijidantaiHistory47Entity entity470 = list47New.get(0);
        assertEquals("ぼったくり政治団体471a", entity470.getAllName());
        KanrenshaSeijidantaiHistory47Entity entity471 = list47New.get(1);
        assertEquals("ぼったくり政治団体472a", entity471.getAllName());
        List<KanrenshaSeijidantaiHistory47Entity> list47Old = kanrenshaSeijidantaiHistory47Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list47Old.size());

        // 99
        List<KanrenshaSeijidantaiHistory99Entity> list99New = kanrenshaSeijidantaiHistory99Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(orgCode);
        assertEquals(2, list99New.size());
        KanrenshaSeijidantaiHistory99Entity entity990 = list99New.get(0);
        assertEquals("ぼったくり政治団体991a", entity990.getAllName());
        KanrenshaSeijidantaiHistory99Entity entity991 = list99New.get(1);
        assertEquals("ぼったくり政治団体992a", entity991.getAllName());
        List<KanrenshaSeijidantaiHistory99Entity> list99Old = kanrenshaSeijidantaiHistory99Repository
                .findBySeijidantaiKanrenshaCodeAndIsLatestTrue(abolishCode);
        assertEquals(0, list99Old.size());
    }

}