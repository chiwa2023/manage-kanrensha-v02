package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * MakeBuildingRsdtByPostalIrregularService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("MakeBuildingRsdtByPostalIrregularServiceTest.sql")
class MakeBuildingRsdtByPostalIrregularServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /** テスト対象 */
    @Autowired
    private MakeBuildingRsdtByPostalIrregularService makeBuildingRsdtByPostalIrregularService;

    @Test
    @Tag("TableTruncate")
    @SuppressWarnings("unchecked")
    void test() throws Exception {

        final int size = 12; // テストとして11行用意した
        assertEquals(size, makeBuildingRsdtByPostalIrregularService.practice("新都心明治安田生命さいたま新都心ビル",
                CreateLeastUserForTestUtil.practice()));

        String sqlBase = "SELECT * FROM address_rsdt_012041 WHERE is_latest = 1 "
                + " AND address_building LIKE '新都心明治安田生命%'";
        Query queryBase = entityManager.createNativeQuery(sqlBase, AddressRsdtTemplateEntity.class);
        List<AddressRsdtTemplateEntity> listInsert = (List<AddressRsdtTemplateEntity>) queryBase.getResultList();

        assertEquals(size, listInsert.size());

        AddressRsdtTemplateEntity entity00 = listInsert.get(0);
        assertTrue(entity00.getIsLatest());
        // 共通部分が複写されていること
        assertEquals("0094000", entity00.getMachiazaId());
        assertEquals("000070000200000", entity00.getPrcId());
        assertEquals("", entity00.getBlkId());
        assertEquals("", entity00.getRsdtId());
        assertEquals(LocalDate.of(1947,04,17), entity00.getEffectDate());
        assertNull(entity00.getAbolishDate());
        assertEquals("北海道旭川市神居町西丘7番地2号", entity00.getAddressBlock());
        
        // 不規則郵便番号が展開されていること
        final String lgCode ="012041"; 
        final String postalCode ="330"; 
        assertEquals(lgCode, entity00.getLgCode());
        assertEquals(postalCode, entity00.getPostalcode1());
        assertEquals("6000", entity00.getPostalcode2());
        assertEquals("新都心明治安田生命さいたま新都心ビル　地階", entity00.getAddressBuilding());
        assertEquals("0000000", entity00.getRsdt2Id());

        
        AddressRsdtTemplateEntity entity05 = listInsert.get(05);
        assertTrue(entity05.getIsLatest());
        assertEquals(lgCode, entity05.getLgCode());
        assertEquals(postalCode, entity05.getPostalcode1());
        assertEquals("6005", entity05.getPostalcode2());
        assertEquals("新都心明治安田生命さいたま新都心ビル　５階", entity05.getAddressBuilding());
        assertEquals("0500000", entity05.getRsdt2Id());

        
        
        AddressRsdtTemplateEntity entity11 = listInsert.get(11);
        assertTrue(entity11.getIsLatest());
        assertEquals(lgCode, entity11.getLgCode());
        assertEquals(postalCode, entity11.getPostalcode1());
        assertEquals("6011", entity11.getPostalcode2());
        assertEquals("新都心明治安田生命さいたま新都心ビル　１１階", entity11.getAddressBuilding());
        assertEquals("1100000", entity11.getRsdt2Id());
        
    }

}
