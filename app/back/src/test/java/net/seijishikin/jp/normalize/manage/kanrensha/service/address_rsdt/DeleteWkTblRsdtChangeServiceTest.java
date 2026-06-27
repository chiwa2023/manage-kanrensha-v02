package net.seijishikin.jp.normalize.manage.kanrensha.service.address_rsdt;

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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.EditWktblRsdtChangeCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtChangeEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblAddressRsdtChangeRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * DeleteWkTblRsdtChangeService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("DeleteWkTblRsdtChangeServiceTest.sql")
class DeleteWkTblRsdtChangeServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private DeleteWkTblRsdtChangeService deleteWkTblRsdtChangeService;

    /** 住居差分ワークテーブル変更Repository */
    @Autowired
    private WkTblAddressRsdtChangeRepository wkTblAddressRsdtChangeRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 誤って削除対象を指定した場合
        EditWktblRsdtChangeCapsuleDto capsuleDto0 = new EditWktblRsdtChangeCapsuleDto();
        capsuleDto0.getEditEntity().setWkTblAddressRsdtChangeId(111);
        assertThrows(EmptyResultDataAccessException.class, () -> deleteWkTblRsdtChangeService.practice(capsuleDto0));

        EditWktblRsdtChangeCapsuleDto capsuleDto1 = new EditWktblRsdtChangeCapsuleDto();
        capsuleDto1.getEditEntity().setWkTblAddressRsdtChangeId(333);
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());

        Integer newId = deleteWkTblRsdtChangeService.practice(capsuleDto1);
        WkTblAddressRsdtChangeEntity newEntity = wkTblAddressRsdtChangeRepository.findById(newId).get();
        assertFalse(newEntity.getIsLatest());
    }

}
