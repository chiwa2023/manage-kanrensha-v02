package net.seijishikin.jp.normalize.manage.kanrensha.logic.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * CreateSqlInsertAddressRsdtLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("CreateSqlInsertAddressRsdtLogicTest.sql")
class CreateSqlInsertAddressRsdtLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CreateSqlInsertAddressRsdtLogic createSqlInsertAddressRsdtLogic;

    /** テスト対象 */
    @Autowired
    private EntityManager entityManager;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void test() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        LocalDateTime time = LocalDateTime.of(2025, 12, 5, 12, 34, 56);
        String timestampString = time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        AddressRsdtTemplateEntity baseEntity = new AddressRsdtTemplateEntity();
        baseEntity.setLgCode("011045");
        baseEntity.setPostalcode1("11");
        baseEntity.setPostalcode2("12");
        baseEntity.setAddressBlock("13");
        baseEntity.setAddressBuilding("14");
        baseEntity.setMachiazaId("15");
        baseEntity.setBlkId("16");
        baseEntity.setPrcId("17");
        baseEntity.setRsdtId("18");
        baseEntity.setRsdt2Id("19");
        baseEntity.setEffectDate(LocalDate.of(2025, 6, 24));
        baseEntity.setAbolishDate(LocalDate.of(2026, 7, 11));

        String sql = createSqlInsertAddressRsdtLogic.practice(userDto, baseEntity, timestampString);
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();

        // 空にしているのでサンプルデータ最終行+1
        AddressRsdtBaseEntity ansEntity = this.findRsdtTable(baseEntity.getLgCode(), 810);

        assertEquals(baseEntity.getLgCode(), ansEntity.getLgCode());
        assertEquals(baseEntity.getPostalcode1(), ansEntity.getPostalcode1());
        assertEquals(baseEntity.getPostalcode2(), ansEntity.getPostalcode2());
        assertEquals(baseEntity.getAddressBlock(), ansEntity.getAddressBlock());
        assertEquals(baseEntity.getAddressBuilding(), ansEntity.getAddressBuilding());
        assertEquals(baseEntity.getMachiazaId(), ansEntity.getMachiazaId());
        assertEquals(baseEntity.getBlkId(), ansEntity.getBlkId());
        assertEquals(baseEntity.getPrcId(), ansEntity.getPrcId());
        assertEquals(baseEntity.getRsdtId(), ansEntity.getRsdtId());
        assertEquals(baseEntity.getRsdt2Id(), ansEntity.getRsdt2Id());
        assertEquals(baseEntity.getEffectDate(), ansEntity.getEffectDate());
        assertEquals(baseEntity.getAbolishDate(), ansEntity.getAbolishDate());
    }

    @SuppressWarnings("unchecked")
    private AddressRsdtBaseEntity findRsdtTable(final String lgCode, final Integer rsdtId) {

        String sql = "SELECT * FROM address_rsdt_" + lgCode + " WHERE address_rsdt_id = " + rsdtId;

        Query query = entityManager.createNativeQuery(sql, AddressRsdtBaseEntity.class);
        List<AddressRsdtBaseEntity> results = (List<AddressRsdtBaseEntity>) query.getResultList();

        // idを使って呼び出しているので必ず1件だけｈが取得
        assertEquals(1, results.size());

        return results.get(0);
    }

}
