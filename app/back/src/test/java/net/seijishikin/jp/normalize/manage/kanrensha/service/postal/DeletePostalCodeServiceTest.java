package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
 * DeletePostalCodeService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("DeletePostalCodeServiceTest.sql")
class DeletePostalCodeServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private DeletePostalCodeService deletePostalCodeService;

    /** 郵便番号正規データRepository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 誤って削除対象を指定した場合
        SavePostalCapsuleDto capsuleDto0 = new SavePostalCapsuleDto();
        capsuleDto0.getAddressPostalEntity().setAddressPostalId(111);
        assertThrows(EmptyResultDataAccessException.class, () -> deletePostalCodeService.practice(capsuleDto0));

        SavePostalCapsuleDto capsuleDto1 = new SavePostalCapsuleDto();
        capsuleDto1.getAddressPostalEntity().setAddressPostalId(443);
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());

        Integer newId = deletePostalCodeService.practice(capsuleDto1);
        AddressPostalEntity newEntity = addressPostalRepository.findById(newId).get();
        assertFalse(newEntity.getIsLatest());
    }

}
