package net.seijishikin.jp.normalize.manage.kanrensha.service.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.EditAddressCityDeleteCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressCityDeleteEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressCityDeleteRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * DeleteAllCityDeleteService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("DeleteAllCityDeleteServiceTest.sql")
class DeleteAllCityDeleteServiceTest {

    /** テスト対象 */
    @Autowired
    private DeleteAllCityDeleteService deleteAllCityDeleteService;

    /** 地方自治体コード削除Repository */
    @Autowired
    private AddressCityDeleteRepository addressCityDeleteRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {
        final Integer deleteId = 161;

        EditAddressCityDeleteCapsuleDto capsuleDto = new EditAddressCityDeleteCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.getEditEntity().setAddressCityDeleteId(deleteId);

        assertEquals(deleteId, deleteAllCityDeleteService.practice(capsuleDto));

        AddressCityDeleteEntity entityAns = addressCityDeleteRepository.findById(deleteId).get();
        assertFalse(entityAns.getIsLatest());
    }

}
