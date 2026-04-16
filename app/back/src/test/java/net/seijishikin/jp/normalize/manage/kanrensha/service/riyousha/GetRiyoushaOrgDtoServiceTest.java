package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.GetRiyoushaOrgByCodeCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.RiyoushaOrgDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaOrgMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaOrgMasterRepository;

/**
 * GetRiyoushaOrgDtoService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetRiyoushaOrgDtoServiceTest.sql")
class GetRiyoushaOrgDtoServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetRiyoushaOrgDtoService getRiyoushaOrgDtoService;

    /** 利用者組織マスタRepository */
    @Autowired
    private RiyoushaOrgMasterRepository riyoushaOrgMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 呼び出しできないときは空Dtoを戻してControllerで処理
        assertDoesNotThrow(() -> getRiyoushaOrgDtoService.practice(new RiyoushaOrgMasterEntity()));

        RiyoushaOrgMasterEntity masterEntity = riyoushaOrgMasterRepository.findById(318).get();

        RiyoushaOrgDto orgDto = getRiyoushaOrgDtoService.practice(masterEntity);

        assertEquals("政治資金文書作成所", orgDto.getInputOrgNameDto().getOrgName());
        assertEquals("せいじしきんぶんしょさくせいしょ", orgDto.getInputOrgNameDto().getOrgNameKana());

        assertEquals("和歌山県架空市湖畔町", orgDto.getInputAddressDto().getAddressPostal());
        assertEquals("100番地の7", orgDto.getInputAddressDto().getAddressBlock());
        assertEquals("星形ビル444", orgDto.getInputAddressDto().getAddressBuilding());
        assertEquals("987", orgDto.getInputAddressDto().getPostalcode1());
        assertEquals("5432", orgDto.getInputAddressDto().getPostalcode2());
        assertEquals("123456", orgDto.getInputAddressDto().getLgCode());
        assertEquals("2345", orgDto.getInputAddressDto().getMachiazaId()); // NOPMD
        assertEquals("3456", orgDto.getInputAddressDto().getBlkId());
        assertEquals("4567", orgDto.getInputAddressDto().getPrcId());
        assertEquals("5678", orgDto.getInputAddressDto().getRsdtId());
        assertEquals("6789", orgDto.getInputAddressDto().getRsdt2Id());

        assertEquals("567", orgDto.getInputAccessDto().getPhon1());
        assertEquals("8901", orgDto.getInputAccessDto().getPhon2());
        assertEquals("2345", orgDto.getInputAccessDto().getPhon3());
        assertEquals("test@example.com", orgDto.getInputAccessDto().getEmail());
        assertEquals("http://example.com/", orgDto.getInputAccessDto().getMyPortalUrl());

        assertEquals(1, orgDto.getInputAccessDto().getSnsServiceId());
        assertEquals(2, orgDto.getInputAccessDto().getSnsServiceCode());
        assertEquals("弱小SNS", orgDto.getInputAccessDto().getSnsServiceName());
        assertEquals("http://jakushou.sns.net/", orgDto.getInputAccessDto().getSnsPortalUrl());
        assertEquals("@taro9898", orgDto.getInputAccessDto().getSnsAccount());

        assertEquals(masterEntity.getAllName(), orgDto.getInputOrgNameDto().getOrgName());
        assertEquals(masterEntity.getAllNameKana(), orgDto.getInputOrgNameDto().getOrgNameKana());
        assertEquals(masterEntity.getAddressAll(), orgDto.getInputAddressDto().getAddressAll());
        assertEquals(masterEntity.getRiyoushaOrgMasterId(), orgDto.getRiyoushaOrgMasterId());
        assertEquals(masterEntity.getRiyoushaOrgMasterCode(), orgDto.getRiyoushaOrgMasterCode());
        assertEquals(masterEntity.getRiyoushaOrgPropertyId(), orgDto.getRiyoushaOrgPropertyId());
        assertEquals(masterEntity.getRiyoushaOrgPropertyCode(), orgDto.getRiyoushaOrgPropertyCode());

        List<RiyoushaCombineOrgEntity> listPerson = orgDto.getListPersonCombine();
        assertEquals(3, listPerson.size());

        RiyoushaCombineOrgEntity personEntity0 = listPerson.get(0);
        assertEquals((short) 1, personEntity0.getRiyoushaKbn());
        assertEquals(190, personEntity0.getPersonRiyoushaCode());
        assertEquals("管理者太郎", personEntity0.getPersonName());

        RiyoushaCombineOrgEntity personEntity1 = listPerson.get(1);
        assertEquals((short) 2, personEntity1.getRiyoushaKbn());
        assertEquals(191, personEntity1.getPersonRiyoushaCode());
        assertEquals("管理者花子", personEntity1.getPersonName());

        RiyoushaCombineOrgEntity personEntity2 = listPerson.get(2);
        assertEquals((short) 3, personEntity2.getRiyoushaKbn());
        assertEquals(192, personEntity2.getPersonRiyoushaCode());
        assertEquals("管理者直子", personEntity2.getPersonName());
    }

    @Test
    @Tag("TableTruncate")
    void testByCode() throws Exception {

        GetRiyoushaOrgByCodeCapsuleDto capsuleDto = new GetRiyoushaOrgByCodeCapsuleDto();
        capsuleDto.setSelectedCode(216);

        RiyoushaOrgDto orgDto = getRiyoushaOrgDtoService.practiceByCode(capsuleDto);

        assertEquals("政治資金文書作成所", orgDto.getInputOrgNameDto().getOrgName());
        assertEquals("せいじしきんぶんしょさくせいしょ", orgDto.getInputOrgNameDto().getOrgNameKana());

        assertEquals("和歌山県架空市湖畔町", orgDto.getInputAddressDto().getAddressPostal());
        assertEquals("100番地の7", orgDto.getInputAddressDto().getAddressBlock());
        assertEquals("星形ビル444", orgDto.getInputAddressDto().getAddressBuilding());
        assertEquals("987", orgDto.getInputAddressDto().getPostalcode1());
        assertEquals("5432", orgDto.getInputAddressDto().getPostalcode2());
        assertEquals("123456", orgDto.getInputAddressDto().getLgCode());
        assertEquals("2345", orgDto.getInputAddressDto().getMachiazaId());
        assertEquals("3456", orgDto.getInputAddressDto().getBlkId());
        assertEquals("4567", orgDto.getInputAddressDto().getPrcId());
        assertEquals("5678", orgDto.getInputAddressDto().getRsdtId());
        assertEquals("6789", orgDto.getInputAddressDto().getRsdt2Id());

        assertEquals("567", orgDto.getInputAccessDto().getPhon1());
        assertEquals("8901", orgDto.getInputAccessDto().getPhon2());
        assertEquals("2345", orgDto.getInputAccessDto().getPhon3());
        assertEquals("test@example.com", orgDto.getInputAccessDto().getEmail());
        assertEquals("http://example.com/", orgDto.getInputAccessDto().getMyPortalUrl());

        assertEquals(1, orgDto.getInputAccessDto().getSnsServiceId());
        assertEquals(2, orgDto.getInputAccessDto().getSnsServiceCode());
        assertEquals("弱小SNS", orgDto.getInputAccessDto().getSnsServiceName());
        assertEquals("http://jakushou.sns.net/", orgDto.getInputAccessDto().getSnsPortalUrl());
        assertEquals("@taro9898", orgDto.getInputAccessDto().getSnsAccount());

        assertEquals("政治資金文書作成所", orgDto.getInputOrgNameDto().getOrgName());
        assertEquals("せいじしきんぶんしょさくせいしょ", orgDto.getInputOrgNameDto().getOrgNameKana());
        assertEquals("和歌山県架空市湖畔町100番地の7星形ビル444", orgDto.getInputAddressDto().getAddressAll());
        assertEquals(318, orgDto.getRiyoushaOrgMasterId());
        assertEquals(216, orgDto.getRiyoushaOrgMasterCode());
        assertEquals(427, orgDto.getRiyoushaOrgPropertyId());
        assertEquals(325, orgDto.getRiyoushaOrgPropertyCode());

        List<RiyoushaCombineOrgEntity> listPerson = orgDto.getListPersonCombine();
        assertEquals(3, listPerson.size());

        RiyoushaCombineOrgEntity personEntity0 = listPerson.get(0);
        assertEquals((short) 1, personEntity0.getRiyoushaKbn());
        assertEquals(190, personEntity0.getPersonRiyoushaCode());
        assertEquals("管理者太郎", personEntity0.getPersonName());

        RiyoushaCombineOrgEntity personEntity1 = listPerson.get(1);
        assertEquals((short) 2, personEntity1.getRiyoushaKbn());
        assertEquals(191, personEntity1.getPersonRiyoushaCode());
        assertEquals("管理者花子", personEntity1.getPersonName());

        RiyoushaCombineOrgEntity personEntity2 = listPerson.get(2);
        assertEquals((short) 3, personEntity2.getRiyoushaKbn());
        assertEquals(192, personEntity2.getPersonRiyoushaCode());
        assertEquals("管理者直子", personEntity2.getPersonName());
    }

}
