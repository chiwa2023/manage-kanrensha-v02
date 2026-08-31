package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SavePostalIrregularCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SavePostalIrregularBuildingAllFloorService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SavePostalIrregularBuildingAllFloorServiceTest.sql")
class SavePostalIrregularBuildingAllFloorServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SavePostalIrregularBuildingAllFloorService savePostalIrregularBuildingAllFloorService;

    /** 郵便番号不規則データRepository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    /** 郵便番号正規データRepository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddressPostalIrregularEntity entityEdit = addressPostalIrregularRepository.findById(776).get();
        final String addressPostal = "埼玉県さいたま市中央区新都心";
        final String addressBlock = "11番2";

        entityEdit.setAddressPostal(addressPostal);
        entityEdit.setAddressBlock(addressBlock);
        final String addressName = entityEdit.getAddressName();

        SavePostalIrregularCapsuleDto capsuleDto = new SavePostalIrregularCapsuleDto();
        capsuleDto.setAddressPostalIrregularEntity(entityEdit);
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        FrameworkMessageAndResultDto resultDto = savePostalIrregularBuildingAllFloorService.practice(capsuleDto);
        assertFalse(resultDto.getIsFailure());

        List<AddressPostalIrregularEntity> listAns = addressPostalIrregularRepository
                .findByAddressNameAndIsLatestTrue(entityEdit.getAddressName());
        assertEquals(36, listAns.size());
        for (AddressPostalIrregularEntity entity : listAns) {
            assertEquals(addressName, entity.getAddressName());
            assertEquals(addressPostal, entity.getAddressPostal());
            assertEquals(addressBlock, entity.getAddressBlock());

            List<AddressPostalEntity> listPostal = addressPostalRepository
                    .findByPostalcode1AndPostalcode2AndIsLatestTrueOrderByAddressNameAsc(entity.getPostalcode1(),
                            entity.getPostalcode2());
            for (AddressPostalEntity e : listPostal) {
                assertFalse(e.getIsGyoseikuData());
            }
        }
        assertEquals(981, listAns.getLast().getAddressPostalIrregularId()); // 履歴を追加した
    }

}
