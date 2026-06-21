package net.seijishikin.jp.normalize.manage.kanrensha.service.address_rsdt;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.EditWktblRsdtDeleteCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtDeleteEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtDeleteRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * DeleteWkTblRsdtDeleteService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("DeleteWkTblRsdtDeleteServiceTest.sql")
class DeleteWkTblRsdtDeleteServiceTest {
    // CHECKSTYLE:OFF magicNumber

    /** テスト対象 */
    @Autowired
    private DeleteWkTblRsdtDeleteService deleteWkTblRsdtDeleteService;

    /** 住居差分ワークテーブル削除Repository */
    @Autowired
    private WkTblAddressRsdtDeleteRepository wkTblAddressRsdtDeleteRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 誤って削除対象を指定した場合
        EditWktblRsdtDeleteCapsuleDto capsuleDto0 = new EditWktblRsdtDeleteCapsuleDto();
        capsuleDto0.getEditEntity().setWkTblAddressRsdtDeleteId(111);
        assertThrows(EmptyResultDataAccessException.class, () -> deleteWkTblRsdtDeleteService.practice(capsuleDto0));

        EditWktblRsdtDeleteCapsuleDto capsuleDto1 = new EditWktblRsdtDeleteCapsuleDto();
        capsuleDto1.getEditEntity().setWkTblAddressRsdtDeleteId(525);
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());

        Integer newId = deleteWkTblRsdtDeleteService.practice(capsuleDto1);
        WkTblAddressRsdtDeleteEntity newEntity = wkTblAddressRsdtDeleteRepository.findById(newId).get();
        assertFalse(newEntity.getIsLatest());
    }

}
