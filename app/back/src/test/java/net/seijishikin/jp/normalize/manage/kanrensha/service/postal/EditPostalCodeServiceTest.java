package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SavePostalCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditPostalCodeService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("EditPostalCodeServiceTest.sql")
class EditPostalCodeServiceTest {
    // CHECKSTYLE:OFF MagicnNumber

    /** テスト対象 */
    @Autowired
    private EditPostalCodeService editPostalCodeService;

    /** 郵便番号正規データRepository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 誤って削除対象を指定した場合
        SavePostalCapsuleDto capsuleDto0 = new SavePostalCapsuleDto();
        capsuleDto0.getAddressPostalEntity().setAddressPostalId(111);
        assertThrows(EmptyResultDataAccessException.class, () -> editPostalCodeService.practice(capsuleDto0));

        // 編集
        final int editId = 443;
        AddressPostalEntity postalEntity1 = new AddressPostalEntity();
        postalEntity1.setAddressPostalId(editId);
        postalEntity1.setAddressOrg("1");
        postalEntity1.setAddressName("2");
        postalEntity1.setIsGyoseikuData(true);
        postalEntity1.setIsLatest(true);
        postalEntity1.setLgCode("3");
        postalEntity1.setPostalcode1("4");
        postalEntity1.setPostalcode2("5");

        SavePostalCapsuleDto capsuleDto1 = new SavePostalCapsuleDto();
        capsuleDto1.setAddressPostalEntity(postalEntity1);
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());

        Integer newId1 = editPostalCodeService.practice(capsuleDto1);
        AddressPostalEntity newEntity1 = addressPostalRepository.findById(newId1).get();
        assertTrue(newEntity1.getIsLatest());
        assertEquals(postalEntity1.getAddressOrg(), newEntity1.getAddressOrg());
        assertEquals(postalEntity1.getAddressName(), newEntity1.getAddressName());
        assertEquals(newId1, newEntity1.getAddressPostalId());
        assertEquals(postalEntity1.getIsGyoseikuData(), newEntity1.getIsGyoseikuData());
        assertEquals(postalEntity1.getIsLatest(), newEntity1.getIsLatest());
        assertEquals(postalEntity1.getLgCode(), newEntity1.getLgCode());
        assertEquals(postalEntity1.getPostalcode1(), newEntity1.getPostalcode1());
        assertEquals(postalEntity1.getPostalcode2(), newEntity1.getPostalcode2());

        AddressPostalEntity oldEntity1 = addressPostalRepository.findById(editId).get();
        assertFalse(oldEntity1.getIsLatest());

        // 新規
        AddressPostalEntity postalEntity2 = new AddressPostalEntity();
        postalEntity2.setAddressPostalId(0); // 新規
        postalEntity2.setAddressOrg("11");
        postalEntity2.setAddressName("12");
        postalEntity2.setIsGyoseikuData(true);
        postalEntity2.setIsLatest(true);
        postalEntity2.setLgCode("13");
        postalEntity2.setPostalcode1("14");
        postalEntity2.setPostalcode2("15");

        SavePostalCapsuleDto capsuleDto2 = new SavePostalCapsuleDto();
        capsuleDto2.setAddressPostalEntity(postalEntity2);
        capsuleDto2.setUserDto(CreateLeastUserForTestUtil.practice());

        Integer newId2 = editPostalCodeService.practice(capsuleDto2);
        AddressPostalEntity newEntity2 = addressPostalRepository.findById(newId2).get();
        assertTrue(newEntity2.getIsLatest());
        assertEquals(postalEntity2.getAddressOrg(), newEntity2.getAddressOrg());
        assertEquals(postalEntity2.getAddressName(), newEntity2.getAddressName());
        assertEquals(newId2, newEntity2.getAddressPostalId());
        assertEquals(postalEntity2.getIsGyoseikuData(), newEntity2.getIsGyoseikuData());
        assertEquals(postalEntity2.getIsLatest(), newEntity2.getIsLatest());
        assertEquals(postalEntity2.getLgCode(), newEntity2.getLgCode());
        assertEquals(postalEntity2.getPostalcode1(), newEntity2.getPostalcode1());
        assertEquals(postalEntity2.getPostalcode2(), newEntity2.getPostalcode2());
    }

}
