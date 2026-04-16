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
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory01Repository;
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
 * InsertKanrenshaSeijidantaiHistoryService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("InsertKanrenshaSeijidantaiHistoryServiceTest.sql")
class InsertKanrenshaSeijidantaiHistoryServiceTest { // NOPMD ManyMethod

    /** テスト対象 */
    @Autowired
    private InsertKanrenshaSeijidantaiHistoryService insertKanrenshaSeijidantaiHistoryService;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者企業・団体履歴(01)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory01Repository partnerPoliOrgHistory01Repository;
    /** 関連者企業・団体履歴(02)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory02Repository partnerPoliOrgHistory02Repository;
    /** 関連者企業・団体履歴(03)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory03Repository partnerPoliOrgHistory03Repository;
    /** 関連者企業・団体履歴(04)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory04Repository partnerPoliOrgHistory04Repository;
    /** 関連者企業・団体履歴(05)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory05Repository partnerPoliOrgHistory05Repository;
    /** 関連者企業・団体履歴(06)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory06Repository partnerPoliOrgHistory06Repository;
    /** 関連者企業・団体履歴(07)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory07Repository partnerPoliOrgHistory07Repository;
    /** 関連者企業・団体履歴(08)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory08Repository partnerPoliOrgHistory08Repository;
    /** 関連者企業・団体履歴(09)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory09Repository partnerPoliOrgHistory09Repository;
    /** 関連者企業・団体履歴(10)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory10Repository partnerPoliOrgHistory10Repository;
    /** 関連者企業・団体履歴(11)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory11Repository partnerPoliOrgHistory11Repository;
    /** 関連者企業・団体履歴(12)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory12Repository partnerPoliOrgHistory12Repository;
    /** 関連者企業・団体履歴(13)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory13Repository partnerPoliOrgHistory13Repository;
    /** 関連者企業・団体履歴(14)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory14Repository partnerPoliOrgHistory14Repository;
    /** 関連者企業・団体履歴(15)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory15Repository partnerPoliOrgHistory15Repository;
    /** 関連者企業・団体履歴(16)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory16Repository partnerPoliOrgHistory16Repository;
    /** 関連者企業・団体履歴(17)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory17Repository partnerPoliOrgHistory17Repository;
    /** 関連者企業・団体履歴(18)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory18Repository partnerPoliOrgHistory18Repository;
    /** 関連者企業・団体履歴(19)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory19Repository partnerPoliOrgHistory19Repository;
    /** 関連者企業・団体履歴(20)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory20Repository partnerPoliOrgHistory20Repository;
    /** 関連者企業・団体履歴(21)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory21Repository partnerPoliOrgHistory21Repository;
    /** 関連者企業・団体履歴(22)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory22Repository partnerPoliOrgHistory22Repository;
    /** 関連者企業・団体履歴(23)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory23Repository partnerPoliOrgHistory23Repository;
    /** 関連者企業・団体履歴(24)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory24Repository partnerPoliOrgHistory24Repository;
    /** 関連者企業・団体履歴(25)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory25Repository partnerPoliOrgHistory25Repository;
    /** 関連者企業・団体履歴(26)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory26Repository partnerPoliOrgHistory26Repository;
    /** 関連者企業・団体履歴(27)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory27Repository partnerPoliOrgHistory27Repository;
    /** 関連者企業・団体履歴(28)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory28Repository partnerPoliOrgHistory28Repository;
    /** 関連者企業・団体履歴(29)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory29Repository partnerPoliOrgHistory29Repository;
    /** 関連者企業・団体履歴(30)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory30Repository partnerPoliOrgHistory30Repository;
    /** 関連者企業・団体履歴(31)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory31Repository partnerPoliOrgHistory31Repository;
    /** 関連者企業・団体履歴(32)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory32Repository partnerPoliOrgHistory32Repository;
    /** 関連者企業・団体履歴(33)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory33Repository partnerPoliOrgHistory33Repository;
    /** 関連者企業・団体履歴(34)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory34Repository partnerPoliOrgHistory34Repository;
    /** 関連者企業・団体履歴(35)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory35Repository partnerPoliOrgHistory35Repository;
    /** 関連者企業・団体履歴(36)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory36Repository partnerPoliOrgHistory36Repository;
    /** 関連者企業・団体履歴(37)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory37Repository partnerPoliOrgHistory37Repository;
    /** 関連者企業・団体履歴(38)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory38Repository partnerPoliOrgHistory38Repository;
    /** 関連者企業・団体履歴(39)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory39Repository partnerPoliOrgHistory39Repository;
    /** 関連者企業・団体履歴(40)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory40Repository partnerPoliOrgHistory40Repository;
    /** 関連者企業・団体履歴(41)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory41Repository partnerPoliOrgHistory41Repository;
    /** 関連者企業・団体履歴(42)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory42Repository partnerPoliOrgHistory42Repository;
    /** 関連者企業・団体履歴(43)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory43Repository partnerPoliOrgHistory43Repository;
    /** 関連者企業・団体履歴(44)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory44Repository partnerPoliOrgHistory44Repository;
    /** 関連者企業・団体履歴(45)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory45Repository partnerPoliOrgHistory45Repository;
    /** 関連者企業・団体履歴(46)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory46Repository partnerPoliOrgHistory46Repository;
    /** 関連者企業・団体履歴(47)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory47Repository partnerPoliOrgHistory47Repository;
    /** 関連者企業・団体履歴(99)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory99Repository partnerPoliOrgHistory99Repository;

    /** 010006,北海道 */
    @Test
    @Tag("TableTruncate") // NOPMD
    void test01() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体"); // NOPMD
        baseEntity.setAllAddress("北海道実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎"); // NOPMD
        baseEntity.setSeijidantaiKanrenshaCode("123-4567"); // NOPMD
        baseEntity.setOrgDelegateCode("987-6543"); // NOPMD
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory01Entity> listAns = partnerPoliOrgHistory01Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory01Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());

    }

    /** 020001,青森県 */
    @Test
    @Tag("TableTruncate")
    void test02() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("青森県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory02Entity> listAns = partnerPoliOrgHistory02Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory02Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 030007,岩手県 */
    @Test
    @Tag("TableTruncate")
    void test03() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("岩手県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory03Entity> listAns = partnerPoliOrgHistory03Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory03Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 040002,宮城県 */
    @Test
    @Tag("TableTruncate")
    void test04() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("宮城県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory04Entity> listAns = partnerPoliOrgHistory04Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory04Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 050008,秋田県 */
    @Test
    @Tag("TableTruncate")
    void test05() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("秋田県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory05Entity> listAns = partnerPoliOrgHistory05Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory05Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 060003,山形県 */
    @Test
    @Tag("TableTruncate")
    void test06() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("山形県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory06Entity> listAns = partnerPoliOrgHistory06Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory06Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 070009,福島県 */
    @Test
    @Tag("TableTruncate")
    void test07() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("福島県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory07Entity> listAns = partnerPoliOrgHistory07Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory07Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 080004,茨城県 */
    @Test
    @Tag("TableTruncate")
    void test08() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("茨城県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory08Entity> listAns = partnerPoliOrgHistory08Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory08Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 090000,栃木県 */
    @Test
    @Tag("TableTruncate")
    void test09() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("栃木県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory09Entity> listAns = partnerPoliOrgHistory09Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory09Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 100005,群馬県 */
    @Test
    @Tag("TableTruncate")
    void test10() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("群馬県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory10Entity> listAns = partnerPoliOrgHistory10Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory10Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 110001,埼玉県 */
    @Test
    @Tag("TableTruncate")
    void test11() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("埼玉県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory11Entity> listAns = partnerPoliOrgHistory11Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory11Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 120006,千葉県 */
    @Test
    @Tag("TableTruncate")
    void test12() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("千葉県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory12Entity> listAns = partnerPoliOrgHistory12Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory12Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 130001,東京都 */
    @Test
    @Tag("TableTruncate")
    void test13() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("東京都実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory13Entity> listAns = partnerPoliOrgHistory13Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory13Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 140007,神奈川県 */
    @Test
    @Tag("TableTruncate")
    void test14() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("神奈川県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory14Entity> listAns = partnerPoliOrgHistory14Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory14Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 150002,新潟県 */
    @Test
    @Tag("TableTruncate")
    void test15() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("新潟県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory15Entity> listAns = partnerPoliOrgHistory15Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory15Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 160008,富山県 */
    @Test
    @Tag("TableTruncate")
    void test16() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("富山県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory16Entity> listAns = partnerPoliOrgHistory16Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory16Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 170003,石川県 */
    @Test
    @Tag("TableTruncate")
    void test17() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("石川県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory17Entity> listAns = partnerPoliOrgHistory17Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory17Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 180009,福井県 */
    @Test
    @Tag("TableTruncate")
    void test18() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("福井県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory18Entity> listAns = partnerPoliOrgHistory18Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory18Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 190004,山梨県 */
    @Test
    @Tag("TableTruncate")
    void test19() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("山梨県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory19Entity> listAns = partnerPoliOrgHistory19Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory19Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 200000,長野県 */
    @Test
    @Tag("TableTruncate")
    void test20() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("長野県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory20Entity> listAns = partnerPoliOrgHistory20Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory20Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 210005,岐阜県 */
    @Test
    @Tag("TableTruncate")
    void test21() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("岐阜県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory21Entity> listAns = partnerPoliOrgHistory21Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory21Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 220001,静岡県 */
    @Test
    @Tag("TableTruncate")
    void test22() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("静岡県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory22Entity> listAns = partnerPoliOrgHistory22Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory22Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 230006,愛知県 */
    @Test
    @Tag("TableTruncate")
    void test23() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("愛知県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory23Entity> listAns = partnerPoliOrgHistory23Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory23Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 240001,三重県 */
    @Test
    @Tag("TableTruncate")
    void test24() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("三重県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory24Entity> listAns = partnerPoliOrgHistory24Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory24Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 250007,滋賀県 */
    @Test
    @Tag("TableTruncate")
    void test25() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("滋賀県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory25Entity> listAns = partnerPoliOrgHistory25Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory25Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 260002,京都府 */
    @Test
    @Tag("TableTruncate")
    void test26() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("京都府実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory26Entity> listAns = partnerPoliOrgHistory26Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory26Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 270008,大阪府 */
    @Test
    @Tag("TableTruncate")
    void test27() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("大阪府実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory27Entity> listAns = partnerPoliOrgHistory27Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory27Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 280003,兵庫県 */
    @Test
    @Tag("TableTruncate")
    void test28() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("兵庫県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory28Entity> listAns = partnerPoliOrgHistory28Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory28Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 290009,奈良県 */
    @Test
    @Tag("TableTruncate")
    void test29() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("奈良県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory29Entity> listAns = partnerPoliOrgHistory29Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory29Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 300004,和歌山県 */
    @Test
    @Tag("TableTruncate")
    void test30() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("和歌山県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory30Entity> listAns = partnerPoliOrgHistory30Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory30Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 310000,鳥取県 */
    @Test
    @Tag("TableTruncate")
    void test31() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("鳥取県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory31Entity> listAns = partnerPoliOrgHistory31Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory31Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 320005,島根県 */
    @Test
    @Tag("TableTruncate")
    void test32() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("島根県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory32Entity> listAns = partnerPoliOrgHistory32Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory32Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 330001,岡山県 */
    @Test
    @Tag("TableTruncate")
    void test33() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("岡山県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory33Entity> listAns = partnerPoliOrgHistory33Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory33Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 340006,広島県 */
    @Test
    @Tag("TableTruncate")
    void test34() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("広島県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory34Entity> listAns = partnerPoliOrgHistory34Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory34Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 350001,山口県 */
    @Test
    @Tag("TableTruncate")
    void test35() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("山口県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory35Entity> listAns = partnerPoliOrgHistory35Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory35Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 360007,徳島県 */
    @Test
    @Tag("TableTruncate")
    void test36() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("徳島県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory36Entity> listAns = partnerPoliOrgHistory36Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory36Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 370002,香川県 */
    @Test
    @Tag("TableTruncate")
    void test37() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("香川県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory37Entity> listAns = partnerPoliOrgHistory37Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory37Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 380008,愛媛県 */
    @Test
    @Tag("TableTruncate")
    void test38() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("愛媛県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory38Entity> listAns = partnerPoliOrgHistory38Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory38Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 390003,高知県 */
    @Test
    @Tag("TableTruncate")
    void test39() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("高知県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory39Entity> listAns = partnerPoliOrgHistory39Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory39Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 400009,福岡県 */
    @Test
    @Tag("TableTruncate")
    void test40() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("福岡県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory40Entity> listAns = partnerPoliOrgHistory40Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory40Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 410004,佐賀県 */
    @Test
    @Tag("TableTruncate")
    void test41() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("佐賀県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory41Entity> listAns = partnerPoliOrgHistory41Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory41Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 420000,長崎県 */
    @Test
    @Tag("TableTruncate")
    void test42() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("長崎県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory42Entity> listAns = partnerPoliOrgHistory42Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory42Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 430005,熊本県 */
    @Test
    @Tag("TableTruncate")
    void test43() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("熊本県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory43Entity> listAns = partnerPoliOrgHistory43Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory43Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 440001,大分県 */
    @Test
    @Tag("TableTruncate")
    void test44() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("大分県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory44Entity> listAns = partnerPoliOrgHistory44Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory44Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 450006,宮崎県 */
    @Test
    @Tag("TableTruncate")
    void test45() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("宮崎県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory45Entity> listAns = partnerPoliOrgHistory45Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory45Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 460001,鹿児島県 */
    @Test
    @Tag("TableTruncate")
    void test46() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("鹿児島県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory46Entity> listAns = partnerPoliOrgHistory46Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory46Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 470007,沖縄県 */
    @Test
    @Tag("TableTruncate")
    void test47() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("沖縄県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory47Entity> listAns = partnerPoliOrgHistory47Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory47Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 99,その他 */
    @Test
    @Tag("TableTruncate")
    void test99() throws Exception {
        KanrenshaSeijidantaiHistoryBaseEntity baseEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        baseEntity.setAllName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("青県実在市湖畔町");
        baseEntity.setOrgDelegateName("代表者　太郎");
        baseEntity.setSeijidantaiKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, baseEntity);

        List<KanrenshaSeijidantaiHistory99Entity> listAns = partnerPoliOrgHistory99Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        KanrenshaSeijidantaiHistory99Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }
}
