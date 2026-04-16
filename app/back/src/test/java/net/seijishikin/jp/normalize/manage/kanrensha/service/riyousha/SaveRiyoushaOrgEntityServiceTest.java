package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAccessDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputOrgNameDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SaveRiyoushaOrgCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaOrgMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaOrgPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaOrgMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaOrgPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SaveRiyoushaOrgEntityService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SaveRiyoushaOrgEntityServiceTest.sql")
class SaveRiyoushaOrgEntityServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SaveRiyoushaOrgEntityService saveRiyoushaOrgEntityService;

    /** 利用者組織マスタRepository */
    @Autowired
    private RiyoushaOrgMasterRepository riyoushaOrgMasterRepository;

    /** 利用者組織属性Repository */
    @Autowired
    private RiyoushaOrgPropertyRepository riyoushaOrgPropertyRepository;

    @Test
    @Tag("TableTruncate")
    void testAdd() throws Exception {

        SaveRiyoushaOrgCapsuleDto capsuleDto = new SaveRiyoushaOrgCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        InputOrgNameDto nameDto = this.createOrgName();
        InputAddressDto addressDto = this.createAddress();
        InputAccessDto accessDto = this.createAccess();

        capsuleDto.getRiyoushaOrgDto().setInputOrgNameDto(nameDto);
        capsuleDto.getRiyoushaOrgDto().setInputAddressDto(addressDto);
        capsuleDto.getRiyoushaOrgDto().setInputAccessDto(accessDto);
        // その他のidなどは空にすると新規登録

        // マスタ
        Integer newId = saveRiyoushaOrgEntityService.practice(capsuleDto);

        assertNotEquals(0, newId);

        RiyoushaOrgMasterEntity savedMasterEntity = riyoushaOrgMasterRepository.findById(newId).get();

        assertEquals(nameDto.getOrgName(), savedMasterEntity.getAllName());
        assertEquals(nameDto.getOrgNameKana(), savedMasterEntity.getAllNameKana());
        assertEquals(addressDto.getAddressAll(), savedMasterEntity.getAddressAll());

        // 個人属性
        RiyoushaOrgPropertyEntity savedPropertyEntity = riyoushaOrgPropertyRepository
                .findById(savedMasterEntity.getRiyoushaOrgPropertyId()).get();

        assertEquals(nameDto.getOrgName(), savedPropertyEntity.getOrgName());
        assertEquals(nameDto.getOrgNameKana(), savedPropertyEntity.getOrgNameKana());

        assertEquals(addressDto.getAddressPostal(), savedPropertyEntity.getAddressPostal());
        assertEquals(addressDto.getAddressBlock(), savedPropertyEntity.getAddressBlock());
        assertEquals(addressDto.getAddressBuilding(), savedPropertyEntity.getAddressBuilding());
        assertEquals(addressDto.getPostalcode1(), savedPropertyEntity.getPostalcode1());
        assertEquals(addressDto.getPostalcode2(), savedPropertyEntity.getPostalcode2());
        assertEquals(addressDto.getLgCode(), savedPropertyEntity.getLgCode());
        assertEquals(addressDto.getMachiazaId(), savedPropertyEntity.getMachiazaId());
        assertEquals(addressDto.getBlkId(), savedPropertyEntity.getBlkId());
        assertEquals(addressDto.getPrcId(), savedPropertyEntity.getPrcId());
        assertEquals(addressDto.getRsdtId(), savedPropertyEntity.getRsdtId());
        assertEquals(addressDto.getRsdt2Id(), savedPropertyEntity.getRsdt2Id());

        assertEquals(accessDto.getPhon1(), savedPropertyEntity.getPhon1());
        assertEquals(accessDto.getPhon2(), savedPropertyEntity.getPhon2());
        assertEquals(accessDto.getPhon3(), savedPropertyEntity.getPhon3());
        assertEquals(accessDto.getEmail(), savedPropertyEntity.getEmail());
        assertEquals(accessDto.getMyPortalUrl(), savedPropertyEntity.getMyPortalUrl());

        assertEquals(accessDto.getSnsServiceId(), savedPropertyEntity.getSnsServiceId());
        assertEquals(accessDto.getSnsServiceCode(), savedPropertyEntity.getSnsServiceCode());
        assertEquals(accessDto.getSnsServiceName(), savedPropertyEntity.getSnsServiceName());
        assertEquals(accessDto.getSnsPortalUrl(), savedPropertyEntity.getSnsPortalUrl());
        assertEquals(accessDto.getSnsAccount(), savedPropertyEntity.getSnsAccount());
    }

    @Test
    @Tag("TableTruncate")
    void testUpdate() throws Exception {

        SaveRiyoushaOrgCapsuleDto capsuleDto = new SaveRiyoushaOrgCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        InputOrgNameDto nameDto = this.createOrgName();
        InputAddressDto addressDto = this.createAddress();
        InputAccessDto accessDto = this.createAccess();

        capsuleDto.getRiyoushaOrgDto().setInputOrgNameDto(nameDto);
        capsuleDto.getRiyoushaOrgDto().setInputAddressDto(addressDto);
        capsuleDto.getRiyoushaOrgDto().setInputAccessDto(accessDto);

        capsuleDto.getRiyoushaOrgDto().setInputAccessDto(accessDto);
        capsuleDto.getRiyoushaOrgDto().setInputAddressDto(addressDto);
        capsuleDto.getRiyoushaOrgDto().setInputAccessDto(accessDto);
        // id316のデータを編集したと仮定
        capsuleDto.getRiyoushaOrgDto().setRiyoushaOrgMasterId(316);
        capsuleDto.getRiyoushaOrgDto().setRiyoushaOrgMasterCode(216);
        capsuleDto.getRiyoushaOrgDto().setRiyoushaOrgPropertyId(425);
        capsuleDto.getRiyoushaOrgDto().setRiyoushaOrgPropertyCode(325);

        Integer newId = saveRiyoushaOrgEntityService.practice(capsuleDto);

        assertNotEquals(0, newId);

        RiyoushaOrgMasterEntity savedMasterEntity = riyoushaOrgMasterRepository.findById(newId).get();

        assertEquals(nameDto.getOrgName(), savedMasterEntity.getAllName());
        assertEquals(nameDto.getOrgNameKana(), savedMasterEntity.getAllNameKana());
        assertEquals(addressDto.getAddressAll(), savedMasterEntity.getAddressAll());

        // 組織属性
        RiyoushaOrgPropertyEntity savedPropertyEntity = riyoushaOrgPropertyRepository
                .findById(savedMasterEntity.getRiyoushaOrgPropertyId()).get();

        assertEquals(nameDto.getOrgName(), savedPropertyEntity.getOrgName());
        assertEquals(nameDto.getOrgNameKana(), savedPropertyEntity.getOrgNameKana());

        assertEquals(addressDto.getAddressPostal(), savedPropertyEntity.getAddressPostal());
        assertEquals(addressDto.getAddressBlock(), savedPropertyEntity.getAddressBlock());
        assertEquals(addressDto.getAddressBuilding(), savedPropertyEntity.getAddressBuilding());
        assertEquals(addressDto.getPostalcode1(), savedPropertyEntity.getPostalcode1());
        assertEquals(addressDto.getPostalcode2(), savedPropertyEntity.getPostalcode2());
        assertEquals(addressDto.getLgCode(), savedPropertyEntity.getLgCode());
        assertEquals(addressDto.getMachiazaId(), savedPropertyEntity.getMachiazaId());
        assertEquals(addressDto.getBlkId(), savedPropertyEntity.getBlkId());
        assertEquals(addressDto.getPrcId(), savedPropertyEntity.getPrcId());
        assertEquals(addressDto.getRsdtId(), savedPropertyEntity.getRsdtId());
        assertEquals(addressDto.getRsdt2Id(), savedPropertyEntity.getRsdt2Id());

        assertEquals(accessDto.getPhon1(), savedPropertyEntity.getPhon1());
        assertEquals(accessDto.getPhon2(), savedPropertyEntity.getPhon2());
        assertEquals(accessDto.getPhon3(), savedPropertyEntity.getPhon3());
        assertEquals(accessDto.getEmail(), savedPropertyEntity.getEmail());
        assertEquals(accessDto.getMyPortalUrl(), savedPropertyEntity.getMyPortalUrl());

        assertEquals(accessDto.getSnsServiceId(), savedPropertyEntity.getSnsServiceId());
        assertEquals(accessDto.getSnsServiceCode(), savedPropertyEntity.getSnsServiceCode());
        assertEquals(accessDto.getSnsServiceName(), savedPropertyEntity.getSnsServiceName());
        assertEquals(accessDto.getSnsPortalUrl(), savedPropertyEntity.getSnsPortalUrl());
        assertEquals(accessDto.getSnsAccount(), savedPropertyEntity.getSnsAccount());
    }

    private InputOrgNameDto createOrgName() {

        InputOrgNameDto dto = new InputOrgNameDto();
        dto.setOrgName("政治資金文書作成所");
        dto.setOrgNameKana("せいじしきんぶんしょさくせいしょ");

        return dto;
    }

    /**
     * 住所Dtoを作成する
     * 
     * @return 住所Dto
     */
    private InputAddressDto createAddress() {
        InputAddressDto dto = new InputAddressDto();

        dto.setAddressPostal("和歌山県架空市湖畔町");
        dto.setAddressBlock("100番地の7");
        dto.setAddressBuilding("星形ビル444");
        dto.setPostalcode1("987");
        dto.setPostalcode2("5432");
        dto.setLgCode("123456");
        dto.setMachiazaId("2345");
        dto.setBlkId("3456");
        dto.setPrcId("4567");
        dto.setRsdtId("5678");
        dto.setRsdt2Id("6789");
        dto.setIsPostalEdit(true);
        dto.setIsBlockEdit(true);
        dto.setIsBuildingEdit(true);

        dto.setAddressAll("和歌山県架空市湖畔町100番地の7星形ビル444");

        return dto;

    }

    /**
     * 連絡先Dtoを作成する
     * 
     * @return 連絡先Dto
     */
    private InputAccessDto createAccess() {
        InputAccessDto dto = new InputAccessDto();

        dto.setPhon1("567");
        dto.setPhon2("8901");
        dto.setPhon3("2345");
        dto.setEmail("test@example.com");
        dto.setMyPortalUrl("http://example.com/");

        dto.setSnsServiceId(1);
        dto.setSnsServiceCode(2);
        dto.setSnsServiceName("弱小SNS");
        dto.setSnsPortalUrl("http://jakushou.sns.net/");
        dto.setSnsAccount("@taro9898");

        return dto;
    }

}
