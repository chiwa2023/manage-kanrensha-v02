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
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertKanrenshaPersonHistoryService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("InsertKanrenshaPersonHistoryServiceTest.sql")
class InsertKanrenshaPersonHistoryServiceTest { // NOPMD ManyMethod

    /** テスト対象 */
    @Autowired
    private InsertKanrenshaPersonHistoryService insertKanrenshaPersonHistoryService;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者企業・団体履歴(01)Repository */
    @Autowired
    private KanrenshaPersonHistory01Repository KanrenshaPersonHistory01Repository;
    /** 関連者企業・団体履歴(02)Repository */
    @Autowired
    private KanrenshaPersonHistory02Repository KanrenshaPersonHistory02Repository;
    /** 関連者企業・団体履歴(03)Repository */
    @Autowired
    private KanrenshaPersonHistory03Repository KanrenshaPersonHistory03Repository;
    /** 関連者企業・団体履歴(04)Repository */
    @Autowired
    private KanrenshaPersonHistory04Repository KanrenshaPersonHistory04Repository;
    /** 関連者企業・団体履歴(05)Repository */
    @Autowired
    private KanrenshaPersonHistory05Repository KanrenshaPersonHistory05Repository;
    /** 関連者企業・団体履歴(06)Repository */
    @Autowired
    private KanrenshaPersonHistory06Repository KanrenshaPersonHistory06Repository;
    /** 関連者企業・団体履歴(07)Repository */
    @Autowired
    private KanrenshaPersonHistory07Repository KanrenshaPersonHistory07Repository;
    /** 関連者企業・団体履歴(08)Repository */
    @Autowired
    private KanrenshaPersonHistory08Repository KanrenshaPersonHistory08Repository;
    /** 関連者企業・団体履歴(09)Repository */
    @Autowired
    private KanrenshaPersonHistory09Repository KanrenshaPersonHistory09Repository;
    /** 関連者企業・団体履歴(10)Repository */
    @Autowired
    private KanrenshaPersonHistory10Repository KanrenshaPersonHistory10Repository;
    /** 関連者企業・団体履歴(11)Repository */
    @Autowired
    private KanrenshaPersonHistory11Repository KanrenshaPersonHistory11Repository;
    /** 関連者企業・団体履歴(12)Repository */
    @Autowired
    private KanrenshaPersonHistory12Repository KanrenshaPersonHistory12Repository;
    /** 関連者企業・団体履歴(13)Repository */
    @Autowired
    private KanrenshaPersonHistory13Repository KanrenshaPersonHistory13Repository;
    /** 関連者企業・団体履歴(14)Repository */
    @Autowired
    private KanrenshaPersonHistory14Repository KanrenshaPersonHistory14Repository;
    /** 関連者企業・団体履歴(15)Repository */
    @Autowired
    private KanrenshaPersonHistory15Repository KanrenshaPersonHistory15Repository;
    /** 関連者企業・団体履歴(16)Repository */
    @Autowired
    private KanrenshaPersonHistory16Repository KanrenshaPersonHistory16Repository;
    /** 関連者企業・団体履歴(17)Repository */
    @Autowired
    private KanrenshaPersonHistory17Repository KanrenshaPersonHistory17Repository;
    /** 関連者企業・団体履歴(18)Repository */
    @Autowired
    private KanrenshaPersonHistory18Repository KanrenshaPersonHistory18Repository;
    /** 関連者企業・団体履歴(19)Repository */
    @Autowired
    private KanrenshaPersonHistory19Repository KanrenshaPersonHistory19Repository;
    /** 関連者企業・団体履歴(20)Repository */
    @Autowired
    private KanrenshaPersonHistory20Repository KanrenshaPersonHistory20Repository;
    /** 関連者企業・団体履歴(21)Repository */
    @Autowired
    private KanrenshaPersonHistory21Repository KanrenshaPersonHistory21Repository;
    /** 関連者企業・団体履歴(22)Repository */
    @Autowired
    private KanrenshaPersonHistory22Repository KanrenshaPersonHistory22Repository;
    /** 関連者企業・団体履歴(23)Repository */
    @Autowired
    private KanrenshaPersonHistory23Repository KanrenshaPersonHistory23Repository;
    /** 関連者企業・団体履歴(24)Repository */
    @Autowired
    private KanrenshaPersonHistory24Repository KanrenshaPersonHistory24Repository;
    /** 関連者企業・団体履歴(25)Repository */
    @Autowired
    private KanrenshaPersonHistory25Repository KanrenshaPersonHistory25Repository;
    /** 関連者企業・団体履歴(26)Repository */
    @Autowired
    private KanrenshaPersonHistory26Repository KanrenshaPersonHistory26Repository;
    /** 関連者企業・団体履歴(27)Repository */
    @Autowired
    private KanrenshaPersonHistory27Repository KanrenshaPersonHistory27Repository;
    /** 関連者企業・団体履歴(28)Repository */
    @Autowired
    private KanrenshaPersonHistory28Repository KanrenshaPersonHistory28Repository;
    /** 関連者企業・団体履歴(29)Repository */
    @Autowired
    private KanrenshaPersonHistory29Repository KanrenshaPersonHistory29Repository;
    /** 関連者企業・団体履歴(30)Repository */
    @Autowired
    private KanrenshaPersonHistory30Repository KanrenshaPersonHistory30Repository;
    /** 関連者企業・団体履歴(31)Repository */
    @Autowired
    private KanrenshaPersonHistory31Repository KanrenshaPersonHistory31Repository;
    /** 関連者企業・団体履歴(32)Repository */
    @Autowired
    private KanrenshaPersonHistory32Repository KanrenshaPersonHistory32Repository;
    /** 関連者企業・団体履歴(33)Repository */
    @Autowired
    private KanrenshaPersonHistory33Repository KanrenshaPersonHistory33Repository;
    /** 関連者企業・団体履歴(34)Repository */
    @Autowired
    private KanrenshaPersonHistory34Repository KanrenshaPersonHistory34Repository;
    /** 関連者企業・団体履歴(35)Repository */
    @Autowired
    private KanrenshaPersonHistory35Repository KanrenshaPersonHistory35Repository;
    /** 関連者企業・団体履歴(36)Repository */
    @Autowired
    private KanrenshaPersonHistory36Repository KanrenshaPersonHistory36Repository;
    /** 関連者企業・団体履歴(37)Repository */
    @Autowired
    private KanrenshaPersonHistory37Repository KanrenshaPersonHistory37Repository;
    /** 関連者企業・団体履歴(38)Repository */
    @Autowired
    private KanrenshaPersonHistory38Repository KanrenshaPersonHistory38Repository;
    /** 関連者企業・団体履歴(39)Repository */
    @Autowired
    private KanrenshaPersonHistory39Repository KanrenshaPersonHistory39Repository;
    /** 関連者企業・団体履歴(40)Repository */
    @Autowired
    private KanrenshaPersonHistory40Repository KanrenshaPersonHistory40Repository;
    /** 関連者企業・団体履歴(41)Repository */
    @Autowired
    private KanrenshaPersonHistory41Repository KanrenshaPersonHistory41Repository;
    /** 関連者企業・団体履歴(42)Repository */
    @Autowired
    private KanrenshaPersonHistory42Repository KanrenshaPersonHistory42Repository;
    /** 関連者企業・団体履歴(43)Repository */
    @Autowired
    private KanrenshaPersonHistory43Repository KanrenshaPersonHistory43Repository;
    /** 関連者企業・団体履歴(44)Repository */
    @Autowired
    private KanrenshaPersonHistory44Repository KanrenshaPersonHistory44Repository;
    /** 関連者企業・団体履歴(45)Repository */
    @Autowired
    private KanrenshaPersonHistory45Repository KanrenshaPersonHistory45Repository;
    /** 関連者企業・団体履歴(46)Repository */
    @Autowired
    private KanrenshaPersonHistory46Repository KanrenshaPersonHistory46Repository;
    /** 関連者企業・団体履歴(47)Repository */
    @Autowired
    private KanrenshaPersonHistory47Repository KanrenshaPersonHistory47Repository;
    /** 関連者企業・団体履歴(99)Repository */
    @Autowired
    private KanrenshaPersonHistory99Repository KanrenshaPersonHistory99Repository;

    /** 010006,北海道 */
    @Test
    @Tag("TableTruncate") // NOPMD
    void test01() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎"); // NOPMD
        baseEntity.setAllAddress("北海道実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567"); // NOPMD
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory01Entity> listAns = KanrenshaPersonHistory01Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory01Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());

    }

    /** 020001,青森県 */
    @Test
    @Tag("TableTruncate")
    void test02() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("青森県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory02Entity> listAns = KanrenshaPersonHistory02Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory02Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 030007,岩手県 */
    @Test
    @Tag("TableTruncate")
    void test03() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("岩手県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory03Entity> listAns = KanrenshaPersonHistory03Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory03Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 040002,宮城県 */
    @Test
    @Tag("TableTruncate")
    void test04() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("宮城県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory04Entity> listAns = KanrenshaPersonHistory04Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory04Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 050008,秋田県 */
    @Test
    @Tag("TableTruncate")
    void test05() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("秋田県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory05Entity> listAns = KanrenshaPersonHistory05Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory05Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 060003,山形県 */
    @Test
    @Tag("TableTruncate")
    void test06() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("山形県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory06Entity> listAns = KanrenshaPersonHistory06Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory06Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 070009,福島県 */
    @Test
    @Tag("TableTruncate")
    void test07() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("福島県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory07Entity> listAns = KanrenshaPersonHistory07Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory07Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 080004,茨城県 */
    @Test
    @Tag("TableTruncate")
    void test08() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("茨城県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory08Entity> listAns = KanrenshaPersonHistory08Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory08Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 090000,栃木県 */
    @Test
    @Tag("TableTruncate")
    void test09() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("栃木県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory09Entity> listAns = KanrenshaPersonHistory09Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory09Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 100005,群馬県 */
    @Test
    @Tag("TableTruncate")
    void test10() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("群馬県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory10Entity> listAns = KanrenshaPersonHistory10Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory10Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 110001,埼玉県 */
    @Test
    @Tag("TableTruncate")
    void test11() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("埼玉県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory11Entity> listAns = KanrenshaPersonHistory11Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory11Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 120006,千葉県 */
    @Test
    @Tag("TableTruncate")
    void test12() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("千葉県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory12Entity> listAns = KanrenshaPersonHistory12Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory12Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 130001,東京都 */
    @Test
    @Tag("TableTruncate")
    void test13() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("東京都実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory13Entity> listAns = KanrenshaPersonHistory13Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory13Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 140007,神奈川県 */
    @Test
    @Tag("TableTruncate")
    void test14() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("神奈川県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory14Entity> listAns = KanrenshaPersonHistory14Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory14Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 150002,新潟県 */
    @Test
    @Tag("TableTruncate")
    void test15() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("新潟県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory15Entity> listAns = KanrenshaPersonHistory15Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory15Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 160008,富山県 */
    @Test
    @Tag("TableTruncate")
    void test16() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("富山県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory16Entity> listAns = KanrenshaPersonHistory16Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory16Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 170003,石川県 */
    @Test
    @Tag("TableTruncate")
    void test17() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("石川県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory17Entity> listAns = KanrenshaPersonHistory17Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory17Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 180009,福井県 */
    @Test
    @Tag("TableTruncate")
    void test18() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("福井県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory18Entity> listAns = KanrenshaPersonHistory18Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory18Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 190004,山梨県 */
    @Test
    @Tag("TableTruncate")
    void test19() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("山梨県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory19Entity> listAns = KanrenshaPersonHistory19Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory19Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 200000,長野県 */
    @Test
    @Tag("TableTruncate")
    void test20() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("長野県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory20Entity> listAns = KanrenshaPersonHistory20Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory20Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 210005,岐阜県 */
    @Test
    @Tag("TableTruncate")
    void test21() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("岐阜県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory21Entity> listAns = KanrenshaPersonHistory21Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory21Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 220001,静岡県 */
    @Test
    @Tag("TableTruncate")
    void test22() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("静岡県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory22Entity> listAns = KanrenshaPersonHistory22Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory22Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 230006,愛知県 */
    @Test
    @Tag("TableTruncate")
    void test23() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("愛知県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory23Entity> listAns = KanrenshaPersonHistory23Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory23Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 240001,三重県 */
    @Test
    @Tag("TableTruncate")
    void test24() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("三重県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory24Entity> listAns = KanrenshaPersonHistory24Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory24Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 250007,滋賀県 */
    @Test
    @Tag("TableTruncate")
    void test25() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("滋賀県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory25Entity> listAns = KanrenshaPersonHistory25Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory25Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 260002,京都府 */
    @Test
    @Tag("TableTruncate")
    void test26() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("京都府実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory26Entity> listAns = KanrenshaPersonHistory26Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory26Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 270008,大阪府 */
    @Test
    @Tag("TableTruncate")
    void test27() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("大阪府実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory27Entity> listAns = KanrenshaPersonHistory27Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory27Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 280003,兵庫県 */
    @Test
    @Tag("TableTruncate")
    void test28() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("兵庫県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory28Entity> listAns = KanrenshaPersonHistory28Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory28Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 290009,奈良県 */
    @Test
    @Tag("TableTruncate")
    void test29() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("奈良県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory29Entity> listAns = KanrenshaPersonHistory29Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory29Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 300004,和歌山県 */
    @Test
    @Tag("TableTruncate")
    void test30() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("和歌山県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory30Entity> listAns = KanrenshaPersonHistory30Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory30Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 310000,鳥取県 */
    @Test
    @Tag("TableTruncate")
    void test31() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("鳥取県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory31Entity> listAns = KanrenshaPersonHistory31Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory31Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 320005,島根県 */
    @Test
    @Tag("TableTruncate")
    void test32() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("島根県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory32Entity> listAns = KanrenshaPersonHistory32Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory32Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 330001,岡山県 */
    @Test
    @Tag("TableTruncate")
    void test33() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("岡山県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory33Entity> listAns = KanrenshaPersonHistory33Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory33Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 340006,広島県 */
    @Test
    @Tag("TableTruncate")
    void test34() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("広島県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory34Entity> listAns = KanrenshaPersonHistory34Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory34Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 350001,山口県 */
    @Test
    @Tag("TableTruncate")
    void test35() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("山口県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory35Entity> listAns = KanrenshaPersonHistory35Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory35Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 360007,徳島県 */
    @Test
    @Tag("TableTruncate")
    void test36() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("徳島県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory36Entity> listAns = KanrenshaPersonHistory36Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory36Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 370002,香川県 */
    @Test
    @Tag("TableTruncate")
    void test37() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("香川県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory37Entity> listAns = KanrenshaPersonHistory37Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory37Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 380008,愛媛県 */
    @Test
    @Tag("TableTruncate")
    void test38() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("愛媛県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory38Entity> listAns = KanrenshaPersonHistory38Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory38Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 390003,高知県 */
    @Test
    @Tag("TableTruncate")
    void test39() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("高知県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory39Entity> listAns = KanrenshaPersonHistory39Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory39Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 400009,福岡県 */
    @Test
    @Tag("TableTruncate")
    void test40() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("福岡県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory40Entity> listAns = KanrenshaPersonHistory40Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory40Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 410004,佐賀県 */
    @Test
    @Tag("TableTruncate")
    void test41() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("佐賀県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory41Entity> listAns = KanrenshaPersonHistory41Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory41Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 420000,長崎県 */
    @Test
    @Tag("TableTruncate")
    void test42() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("長崎県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory42Entity> listAns = KanrenshaPersonHistory42Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory42Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 430005,熊本県 */
    @Test
    @Tag("TableTruncate")
    void test43() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("熊本県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory43Entity> listAns = KanrenshaPersonHistory43Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory43Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 440001,大分県 */
    @Test
    @Tag("TableTruncate")
    void test44() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("大分県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory44Entity> listAns = KanrenshaPersonHistory44Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory44Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 450006,宮崎県 */
    @Test
    @Tag("TableTruncate")
    void test45() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("宮崎県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory45Entity> listAns = KanrenshaPersonHistory45Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory45Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 460001,鹿児島県 */
    @Test
    @Tag("TableTruncate")
    void test46() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("鹿児島県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory46Entity> listAns = KanrenshaPersonHistory46Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory46Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 470007,沖縄県 */
    @Test
    @Tag("TableTruncate")
    void test47() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("沖縄県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory47Entity> listAns = KanrenshaPersonHistory47Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory47Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 99,その他 */
    @Test
    @Tag("TableTruncate")
    void test99() throws Exception {
        KanrenshaPersonHistoryBaseEntity baseEntity = new KanrenshaPersonHistoryBaseEntity();
        baseEntity.setAllName("迂回献金　太郎");
        baseEntity.setAllAddress("青県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaPersonHistoryService.practice(userDto, baseEntity);

        List<KanrenshaPersonHistory99Entity> listAns = KanrenshaPersonHistory99Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaPersonHistory99Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

}
