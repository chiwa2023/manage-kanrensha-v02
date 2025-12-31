package net.seijishikin.jp.normalize.manage.kanrensha.logic.works_apploval;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaAddressBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * UpdateApprovalKanrenshaKigyouDtAddressLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("UpdateApprovalKanrenshaKigyouDtAddressLogicTest.sql")
class UpdateApprovalKanrenshaKigyouDtAddressLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private UpdateApprovalKanrenshaKigyouDtAddressLogic updateApprovalKanrenshaKigyouDtAddressLogic;

    /** 関連者企業団体住所Repository */
    @Autowired
    private KanrenshaKigyouDtAddressRepository kanrenshaKigyouDtAddressRepository;

    /** 関連者企業団体住所Repository */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    @Test
    @Tag("TableTruncate")
    void testChangeMaster() throws Exception {

        /* 
         * 住所とアドレス両方変更
         */
        KanrenshaKigyouDtAddressEntity entityAddress03 = kanrenshaKigyouDtAddressRepository.findById(2203).get();

        KanrenshaAddressBaseEntity entity03 = new KanrenshaAddressBaseEntity();
        BeanUtils.copyProperties(entityAddress03, entity03);
        entity03.setKanrenshaAddressId(entityAddress03.getKanrenshaKigyouDtAddressId());
        entity03.setAddressBuilding("四角マンション3F");

        Integer updateCount03 = updateApprovalKanrenshaKigyouDtAddressLogic.practice(entity03,
                CreateLeastUserForTestUtil.practice());

        assertEquals(1, updateCount03);

        KanrenshaKigyouDtAddressEntity entityNew = kanrenshaKigyouDtAddressRepository.findById(2205).get();
        assertEquals(entity03.getAddressBuilding(), entityNew.getAddressBuilding());

        // 紐づくマスタが履歴に
        KanrenshaKigyouDtMasterEntity masterEntity1 = kanrenshaKigyouDtMasterRepository.findById(1).get();
        assertEquals(false, masterEntity1.getIsLatest());
        assertEquals("宮崎県架空市橘通東２丁目１０−１", masterEntity1.getAllAddress());

        // 同じコードの新規積みあげ
        KanrenshaKigyouDtMasterEntity masterEntity3 = kanrenshaKigyouDtMasterRepository.findById(3).get();
        assertEquals(true, masterEntity3.getIsLatest());
        assertEquals("宮崎県架空市橘通東２丁目１０−１　四角マンション3F", masterEntity3.getAllAddress());

        /* 
         * マスタに影響しない 
         */
        KanrenshaKigyouDtAddressEntity entityAddress04 = kanrenshaKigyouDtAddressRepository.findById(2204).get();

        KanrenshaAddressBaseEntity entity04 = new KanrenshaAddressBaseEntity();
        BeanUtils.copyProperties(entityAddress04, entity04);
        entity04.setKanrenshaAddressId(entityAddress04.getKanrenshaKigyouDtAddressId());
        entity04.setLgCode("224466"); // コードしか触っていないのでallAddressに影響しない

        Integer updateCount04 = updateApprovalKanrenshaKigyouDtAddressLogic.practice(entity04,
                CreateLeastUserForTestUtil.practice());

        assertEquals(1, updateCount04);

        KanrenshaKigyouDtAddressEntity entityNew4 = kanrenshaKigyouDtAddressRepository.findById(2206).get();
        assertEquals(entity04.getLgCode(), entityNew4.getLgCode());

        assertEquals(3L, kanrenshaKigyouDtMasterRepository.count(), "住所変更してもマスタに変更はなし");
    }

}
