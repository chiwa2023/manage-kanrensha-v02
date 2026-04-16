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

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAccessDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputPersonNameDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SaveRiyoushaAdminCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaAdminMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaAdminMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaPersonPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SaveRiyoushaAdminEntityService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SaveRiyoushaAdminEntityServiceTest.sql")
class SaveRiyoushaAdminEntityServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SaveRiyoushaAdminEntityService saveRiyoushaAdminEntityService;

    /** 利用者個人属性Repository */
    @Autowired
    private RiyoushaPersonPropertyRepository riyoushaPersonPropertyRepository;

    /** 利用者運営者マスタRepository */
    @Autowired
    private RiyoushaAdminMasterRepository riyoushaAdminMasterRepository;

    @Test
    @Tag("TableTruncate")
    void testAdd() throws Exception {

        SaveRiyoushaAdminCapsuleDto capsuleDto = new SaveRiyoushaAdminCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        InputPersonNameDto nameDto = this.createPersonName();
        InputAddressDto addressDto = this.createAddress();
        InputAccessDto accessDto = this.createAccess();

        capsuleDto.getRiyoushaAdminDto().setInputPersonNameDto(nameDto);
        capsuleDto.getRiyoushaAdminDto().setInputAddressDto(addressDto);
        capsuleDto.getRiyoushaAdminDto().setInputAccessDto(accessDto);
        // その他のidなどは空にすると新規登録

        // マスタ
        Integer newId = saveRiyoushaAdminEntityService.practice(capsuleDto);

        assertNotEquals(0, newId);

        RiyoushaAdminMasterEntity savedMasterEntity = riyoushaAdminMasterRepository.findById(newId).get();

        assertEquals(nameDto.getAllName(), savedMasterEntity.getAllName());
        assertEquals(nameDto.getAllNameKana(), savedMasterEntity.getAllNameKana());
        assertEquals(addressDto.getAddressAll(), savedMasterEntity.getAddressAll());

        // 個人属性
        RiyoushaPersonPropertyEntity savedPropertyEntity = riyoushaPersonPropertyRepository
                .findById(savedMasterEntity.getRiyoushaPersonPropertyId()).get();

        assertEquals(nameDto.getFirstName(), savedPropertyEntity.getFirstName());
        assertEquals(nameDto.getFirstNameKana(), savedPropertyEntity.getFirstNameKana());
        assertEquals(nameDto.getLastName(), savedPropertyEntity.getLastName());
        assertEquals(nameDto.getLastNameKana(), savedPropertyEntity.getLastNameKana());
        assertEquals(nameDto.getMiddleName(), savedPropertyEntity.getMiddleName());
        assertEquals(nameDto.getMiddleNameKana(), savedPropertyEntity.getMiddleNameKana());

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
    void testUpdate() throws Exception {

        SaveRiyoushaAdminCapsuleDto capsuleDto = new SaveRiyoushaAdminCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        InputPersonNameDto nameDto = this.createPersonName();
        InputAddressDto addressDto = this.createAddress();
        InputAccessDto accessDto = this.createAccess();

        capsuleDto.getRiyoushaAdminDto().setInputPersonNameDto(nameDto);
        capsuleDto.getRiyoushaAdminDto().setInputAddressDto(addressDto);
        capsuleDto.getRiyoushaAdminDto().setInputAccessDto(accessDto);
        // id3のデータを編集したと仮定
        capsuleDto.getRiyoushaAdminDto().setRiyoushaAdminMasterId(3);
        capsuleDto.getRiyoushaAdminDto().setRiyoushaAdminMasterCode(13);
        capsuleDto.getRiyoushaAdminDto().setRiyoushaPersonPropertyId(3);
        capsuleDto.getRiyoushaAdminDto().setRiyoushaPersonPropertyCode(3);

        Integer newId = saveRiyoushaAdminEntityService.practice(capsuleDto);

        assertNotEquals(0, newId);

        RiyoushaAdminMasterEntity savedMasterEntity = riyoushaAdminMasterRepository.findById(newId).get();

        assertEquals(nameDto.getAllName(), savedMasterEntity.getAllName());
        assertEquals(nameDto.getAllNameKana(), savedMasterEntity.getAllNameKana());
        assertEquals(addressDto.getAddressAll(), savedMasterEntity.getAddressAll());

        // 個人属性
        RiyoushaPersonPropertyEntity savedPropertyEntity = riyoushaPersonPropertyRepository
                .findById(savedMasterEntity.getRiyoushaPersonPropertyId()).get();

        assertEquals(nameDto.getFirstName(), savedPropertyEntity.getFirstName());
        assertEquals(nameDto.getFirstNameKana(), savedPropertyEntity.getFirstNameKana());
        assertEquals(nameDto.getLastName(), savedPropertyEntity.getLastName());
        assertEquals(nameDto.getLastNameKana(), savedPropertyEntity.getLastNameKana());
        assertEquals(nameDto.getMiddleName(), savedPropertyEntity.getMiddleName());
        assertEquals(nameDto.getMiddleNameKana(), savedPropertyEntity.getMiddleNameKana());

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

    private InputPersonNameDto createPersonName() {
        InputPersonNameDto dto = new InputPersonNameDto();

        dto.setFirstName("太郎");
        dto.setFirstNameKana("たろう");
        dto.setLastName("運営者");
        dto.setLastNameKana("うんえいしゃ");
        dto.setMiddleName("ミカエル");
        dto.setMiddleNameKana("みかえる");

        dto.setAllName("運営者　ミカエル太郎");
        dto.setAllNameKana("うんえいしゃ　みかえるたろう");

        return dto;
    }

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
