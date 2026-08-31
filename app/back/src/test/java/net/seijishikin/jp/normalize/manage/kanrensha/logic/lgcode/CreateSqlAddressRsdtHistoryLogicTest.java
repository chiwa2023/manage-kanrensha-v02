package net.seijishikin.jp.normalize.manage.kanrensha.logic.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * CreateSqlAddressRsdtHistoryLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("CreateSqlAddressRsdtHistoryLogicTest.sql")
@Transactional
class CreateSqlAddressRsdtHistoryLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CreateSqlAddressRsdtHistoryLogic createSqlAddressRsdtHistoryLogic;

    /** テスト対象 */
    @Autowired
    private EntityManager entityManager;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        LocalDateTime time = LocalDateTime.of(2025, 12, 5, 12, 34, 56);
        String timestampString = time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        final String lgCode = "011045";
        final Integer rsdtId = 803;

        String sql = createSqlAddressRsdtHistoryLogic.practice(userDto, lgCode, rsdtId, timestampString);
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();

        AddressRsdtBaseEntity ansEntity = this.findRsdtTable(lgCode, rsdtId);
        assertEquals(false, ansEntity.getIsLatest());
        assertEquals(userDto.getUserPersonId(), ansEntity.getDeleteUserId());
        assertEquals(userDto.getUserPersonCode(), ansEntity.getDeleteUserCode());
        assertEquals(userDto.getUserPersonName(), ansEntity.getDeleteUserName());
        assertEquals(time, ansEntity.getDeleteTimestamp());
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
