package net.seijishikin.jp.normalize.manage.kanrensha.service.address_rsdt;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.EditAddressRsdtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * DeleteAddressRsdtService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("DeleteAddressRsdtServiceTest.sql")
class DeleteAddressRsdtServiceTest {

    /** テスト対象 */
    @Autowired
    private DeleteAddressRsdtService deleteAddressRsdtService;

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        final String lgCode = "131016";
        final Integer deleteId = 624;

        AddressRsdtTemplateEntity editEntity = this.getAddressEntity(lgCode, deleteId);
        EditAddressRsdtCapsuleDto capsuleDto = new EditAddressRsdtCapsuleDto();
        capsuleDto.setEditEntiy(editEntity);
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        assertNotEquals(0, deleteAddressRsdtService.practice(capsuleDto));

        AddressRsdtTemplateEntity proEntity = this.getAddressEntity(lgCode, deleteId);
        assertFalse(proEntity.getIsLatest());
    }

    private AddressRsdtTemplateEntity getAddressEntity(final String lgCode, final Integer rsdtId) {
        String sql = "SELECT * FROM address_rsdt_" + lgCode + "  WHERE address_rsdt_id = " + rsdtId;
        Query query = entityManager.createNativeQuery(sql, AddressRsdtTemplateEntity.class);
        return (AddressRsdtTemplateEntity) query.getSingleResult(); // NOPMD LawDemeter
    }

}
