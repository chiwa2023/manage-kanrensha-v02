package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha; // NOPMD ExcessiveImport

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;

import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

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

/**
 * InsertKanrenshaKigyouDtHistoryService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("InsertKanrenshaKigyouDtHistoryServiceTest.sql")
class InsertKanrenshaKigyouDtHistoryServiceTest { // NOPMD ManyMethod

    /** テスト対象 */
    @Autowired
    private InsertKanrenshaKigyouDtHistoryService insertKanrenshaKigyouDtHistoryService;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者企業・団体履歴(01)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory01Repository partnerCorpHistory01Repository;
    /** 関連者企業・団体履歴(02)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory02Repository partnerCorpHistory02Repository;
    /** 関連者企業・団体履歴(03)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory03Repository partnerCorpHistory03Repository;
    /** 関連者企業・団体履歴(04)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory04Repository partnerCorpHistory04Repository;
    /** 関連者企業・団体履歴(05)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory05Repository partnerCorpHistory05Repository;
    /** 関連者企業・団体履歴(06)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory06Repository partnerCorpHistory06Repository;
    /** 関連者企業・団体履歴(07)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory07Repository partnerCorpHistory07Repository;
    /** 関連者企業・団体履歴(08)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory08Repository partnerCorpHistory08Repository;
    /** 関連者企業・団体履歴(09)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory09Repository partnerCorpHistory09Repository;
    /** 関連者企業・団体履歴(10)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory10Repository partnerCorpHistory10Repository;
    /** 関連者企業・団体履歴(11)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory11Repository partnerCorpHistory11Repository;
    /** 関連者企業・団体履歴(12)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory12Repository partnerCorpHistory12Repository;
    /** 関連者企業・団体履歴(13)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory13Repository partnerCorpHistory13Repository;
    /** 関連者企業・団体履歴(14)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory14Repository partnerCorpHistory14Repository;
    /** 関連者企業・団体履歴(15)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory15Repository partnerCorpHistory15Repository;
    /** 関連者企業・団体履歴(16)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory16Repository partnerCorpHistory16Repository;
    /** 関連者企業・団体履歴(17)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory17Repository partnerCorpHistory17Repository;
    /** 関連者企業・団体履歴(18)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory18Repository partnerCorpHistory18Repository;
    /** 関連者企業・団体履歴(19)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory19Repository partnerCorpHistory19Repository;
    /** 関連者企業・団体履歴(20)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory20Repository partnerCorpHistory20Repository;
    /** 関連者企業・団体履歴(21)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory21Repository partnerCorpHistory21Repository;
    /** 関連者企業・団体履歴(22)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory22Repository partnerCorpHistory22Repository;
    /** 関連者企業・団体履歴(23)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory23Repository partnerCorpHistory23Repository;
    /** 関連者企業・団体履歴(24)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory24Repository partnerCorpHistory24Repository;
    /** 関連者企業・団体履歴(25)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory25Repository partnerCorpHistory25Repository;
    /** 関連者企業・団体履歴(26)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory26Repository partnerCorpHistory26Repository;
    /** 関連者企業・団体履歴(27)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory27Repository partnerCorpHistory27Repository;
    /** 関連者企業・団体履歴(28)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory28Repository partnerCorpHistory28Repository;
    /** 関連者企業・団体履歴(29)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory29Repository partnerCorpHistory29Repository;
    /** 関連者企業・団体履歴(30)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory30Repository partnerCorpHistory30Repository;
    /** 関連者企業・団体履歴(31)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory31Repository partnerCorpHistory31Repository;
    /** 関連者企業・団体履歴(32)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory32Repository partnerCorpHistory32Repository;
    /** 関連者企業・団体履歴(33)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory33Repository partnerCorpHistory33Repository;
    /** 関連者企業・団体履歴(34)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory34Repository partnerCorpHistory34Repository;
    /** 関連者企業・団体履歴(35)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory35Repository partnerCorpHistory35Repository;
    /** 関連者企業・団体履歴(36)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory36Repository partnerCorpHistory36Repository;
    /** 関連者企業・団体履歴(37)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory37Repository partnerCorpHistory37Repository;
    /** 関連者企業・団体履歴(38)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory38Repository partnerCorpHistory38Repository;
    /** 関連者企業・団体履歴(39)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory39Repository partnerCorpHistory39Repository;
    /** 関連者企業・団体履歴(40)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory40Repository partnerCorpHistory40Repository;
    /** 関連者企業・団体履歴(41)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory41Repository partnerCorpHistory41Repository;
    /** 関連者企業・団体履歴(42)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory42Repository partnerCorpHistory42Repository;
    /** 関連者企業・団体履歴(43)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory43Repository partnerCorpHistory43Repository;
    /** 関連者企業・団体履歴(44)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory44Repository partnerCorpHistory44Repository;
    /** 関連者企業・団体履歴(45)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory45Repository partnerCorpHistory45Repository;
    /** 関連者企業・団体履歴(46)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory46Repository partnerCorpHistory46Repository;
    /** 関連者企業・団体履歴(47)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory47Repository partnerCorpHistory47Repository;
    /** 関連者企業・団体履歴(99)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory99Repository partnerCorpHistory99Repository;

    /** 010006,北海道 */
    @Test
    @Tag("TableTruncate") // NOPMD
    void test01() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合"); // NOPMD
        baseEntity.setAllAddress("北海道実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子"); // NOPMD
        baseEntity.setKigyouDtKanrenshaCode("123-4567"); // NOPMD
        baseEntity.setOrgDelegateCode("987-6543"); // NOPMD
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory01Entity> listAns = partnerCorpHistory01Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory01Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
        assertEquals(baseEntity.getOrgDelegateCode(), ansEntity.getOrgDelegateCode());

    }

    /** 020001,青森県 */
    @Test
    @Tag("TableTruncate")
    void test02() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("青森県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory02Entity> listAns = partnerCorpHistory02Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory02Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 030007,岩手県 */
    @Test
    @Tag("TableTruncate")
    void test03() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("岩手県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory03Entity> listAns = partnerCorpHistory03Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory03Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 040002,宮城県 */
    @Test
    @Tag("TableTruncate")
    void test04() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("宮城県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory04Entity> listAns = partnerCorpHistory04Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory04Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 050008,秋田県 */
    @Test
    @Tag("TableTruncate")
    void test05() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("秋田県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory05Entity> listAns = partnerCorpHistory05Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory05Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 060003,山形県 */
    @Test
    @Tag("TableTruncate")
    void test06() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("山形県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory06Entity> listAns = partnerCorpHistory06Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory06Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 070009,福島県 */
    @Test
    @Tag("TableTruncate")
    void test07() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("福島県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory07Entity> listAns = partnerCorpHistory07Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory07Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 080004,茨城県 */
    @Test
    @Tag("TableTruncate")
    void test08() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("茨城県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory08Entity> listAns = partnerCorpHistory08Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory08Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 090000,栃木県 */
    @Test
    @Tag("TableTruncate")
    void test09() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("栃木県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory09Entity> listAns = partnerCorpHistory09Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory09Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 100005,群馬県 */
    @Test
    @Tag("TableTruncate")
    void test10() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("群馬県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory10Entity> listAns = partnerCorpHistory10Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory10Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 110001,埼玉県 */
    @Test
    @Tag("TableTruncate")
    void test11() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("埼玉県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory11Entity> listAns = partnerCorpHistory11Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory11Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 120006,千葉県 */
    @Test
    @Tag("TableTruncate")
    void test12() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("千葉県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory12Entity> listAns = partnerCorpHistory12Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory12Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 130001,東京都 */
    @Test
    @Tag("TableTruncate")
    void test13() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("東京都実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory13Entity> listAns = partnerCorpHistory13Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory13Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 140007,神奈川県 */
    @Test
    @Tag("TableTruncate")
    void test14() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("神奈川県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory14Entity> listAns = partnerCorpHistory14Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory14Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 150002,新潟県 */
    @Test
    @Tag("TableTruncate")
    void test15() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("新潟県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory15Entity> listAns = partnerCorpHistory15Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory15Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 160008,富山県 */
    @Test
    @Tag("TableTruncate")
    void test16() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("富山県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory16Entity> listAns = partnerCorpHistory16Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory16Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 170003,石川県 */
    @Test
    @Tag("TableTruncate")
    void test17() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("石川県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory17Entity> listAns = partnerCorpHistory17Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory17Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 180009,福井県 */
    @Test
    @Tag("TableTruncate")
    void test18() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("福井県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory18Entity> listAns = partnerCorpHistory18Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory18Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 190004,山梨県 */
    @Test
    @Tag("TableTruncate")
    void test19() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("山梨県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory19Entity> listAns = partnerCorpHistory19Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory19Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 200000,長野県 */
    @Test
    @Tag("TableTruncate")
    void test20() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("長野県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory20Entity> listAns = partnerCorpHistory20Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory20Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 210005,岐阜県 */
    @Test
    @Tag("TableTruncate")
    void test21() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("岐阜県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory21Entity> listAns = partnerCorpHistory21Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory21Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 220001,静岡県 */
    @Test
    @Tag("TableTruncate")
    void test22() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("静岡県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory22Entity> listAns = partnerCorpHistory22Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory22Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 230006,愛知県 */
    @Test
    @Tag("TableTruncate")
    void test23() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("愛知県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory23Entity> listAns = partnerCorpHistory23Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory23Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 240001,三重県 */
    @Test
    @Tag("TableTruncate")
    void test24() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("三重県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory24Entity> listAns = partnerCorpHistory24Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory24Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 250007,滋賀県 */
    @Test
    @Tag("TableTruncate")
    void test25() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("滋賀県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory25Entity> listAns = partnerCorpHistory25Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory25Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 260002,京都府 */
    @Test
    @Tag("TableTruncate")
    void test26() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("京都府実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory26Entity> listAns = partnerCorpHistory26Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory26Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 270008,大阪府 */
    @Test
    @Tag("TableTruncate")
    void test27() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("大阪府実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory27Entity> listAns = partnerCorpHistory27Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory27Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 280003,兵庫県 */
    @Test
    @Tag("TableTruncate")
    void test28() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("兵庫県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory28Entity> listAns = partnerCorpHistory28Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory28Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 290009,奈良県 */
    @Test
    @Tag("TableTruncate")
    void test29() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("奈良県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory29Entity> listAns = partnerCorpHistory29Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory29Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 300004,和歌山県 */
    @Test
    @Tag("TableTruncate")
    void test30() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("和歌山県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory30Entity> listAns = partnerCorpHistory30Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory30Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 310000,鳥取県 */
    @Test
    @Tag("TableTruncate")
    void test31() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("鳥取県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory31Entity> listAns = partnerCorpHistory31Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory31Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 320005,島根県 */
    @Test
    @Tag("TableTruncate")
    void test32() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("島根県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory32Entity> listAns = partnerCorpHistory32Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory32Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 330001,岡山県 */
    @Test
    @Tag("TableTruncate")
    void test33() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("岡山県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory33Entity> listAns = partnerCorpHistory33Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory33Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 340006,広島県 */
    @Test
    @Tag("TableTruncate")
    void test34() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("広島県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory34Entity> listAns = partnerCorpHistory34Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory34Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 350001,山口県 */
    @Test
    @Tag("TableTruncate")
    void test35() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("山口県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory35Entity> listAns = partnerCorpHistory35Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory35Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 360007,徳島県 */
    @Test
    @Tag("TableTruncate")
    void test36() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("徳島県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory36Entity> listAns = partnerCorpHistory36Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory36Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 370002,香川県 */
    @Test
    @Tag("TableTruncate")
    void test37() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("香川県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory37Entity> listAns = partnerCorpHistory37Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory37Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 380008,愛媛県 */
    @Test
    @Tag("TableTruncate")
    void test38() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("愛媛県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory38Entity> listAns = partnerCorpHistory38Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory38Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 390003,高知県 */
    @Test
    @Tag("TableTruncate")
    void test39() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("高知県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory39Entity> listAns = partnerCorpHistory39Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory39Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 400009,福岡県 */
    @Test
    @Tag("TableTruncate")
    void test40() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("福岡県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory40Entity> listAns = partnerCorpHistory40Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory40Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 410004,佐賀県 */
    @Test
    @Tag("TableTruncate")
    void test41() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("佐賀県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory41Entity> listAns = partnerCorpHistory41Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory41Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 420000,長崎県 */
    @Test
    @Tag("TableTruncate")
    void test42() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("長崎県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory42Entity> listAns = partnerCorpHistory42Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory42Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 430005,熊本県 */
    @Test
    @Tag("TableTruncate")
    void test43() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("熊本県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory43Entity> listAns = partnerCorpHistory43Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory43Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 440001,大分県 */
    @Test
    @Tag("TableTruncate")
    void test44() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("大分県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory44Entity> listAns = partnerCorpHistory44Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory44Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 450006,宮崎県 */
    @Test
    @Tag("TableTruncate")
    void test45() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("宮崎県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory45Entity> listAns = partnerCorpHistory45Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory45Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 460001,鹿児島県 */
    @Test
    @Tag("TableTruncate")
    void test46() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("鹿児島県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory46Entity> listAns = partnerCorpHistory46Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory46Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 470007,沖縄県 */
    @Test
    @Tag("TableTruncate")
    void test47() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("沖縄県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory47Entity> listAns = partnerCorpHistory47Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory47Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 99,その他 */
    @Test
    @Tag("TableTruncate")
    void test99() throws Exception {
        KanrenshaKigyouDtHistoryBaseEntity baseEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        baseEntity.setAllName("超元素製造組合");
        baseEntity.setAllAddress("青県実在市湖畔町");
        baseEntity.setOrgDelegateName("組合長　花子");
        baseEntity.setKigyouDtKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, baseEntity);

        List<KanrenshaKigyouDtHistory99Entity> listAns = partnerCorpHistory99Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaKigyouDtHistory99Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

}
