package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.RiyoushaPartnerApiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaPartnerApiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaPartnerApiMasterRepository;

/**
 * GetRiyoushaPartnerApiDtoService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SaveRiyoushaPartnerApiEntityServiceTest.sql")
class GetRiyoushaPartnerApiDtoServiceTest {

    /** テスト対象 */
    @Autowired
    private GetRiyoushaPartnerApiDtoService getRiyoushaPartnerApiDtoService;

    /** 利用者API接続者マスタRepository */
    @Autowired
    private RiyoushaPartnerApiMasterRepository riyoushaPartnerApiMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 呼び出しできないときは空Dtoを戻してControllerで処理
        assertDoesNotThrow(() -> getRiyoushaPartnerApiDtoService.practice(new RiyoushaPartnerApiMasterEntity()));

        RiyoushaPartnerApiMasterEntity masterEntity = riyoushaPartnerApiMasterRepository.findById(1).get();

        RiyoushaPartnerApiDto managerDto = getRiyoushaPartnerApiDtoService.practice(masterEntity);

        assertEquals("花子", managerDto.getInputPersonNameDto().getFirstName());
        assertEquals("はなこ", managerDto.getInputPersonNameDto().getFirstNameKana());
        assertEquals("管理者", managerDto.getInputPersonNameDto().getLastName());
        assertEquals("うんえいしゃ", managerDto.getInputPersonNameDto().getLastNameKana());
        assertEquals("マリア", managerDto.getInputPersonNameDto().getMiddleName());
        assertEquals("まりあ", managerDto.getInputPersonNameDto().getMiddleNameKana());

        assertEquals("宮崎県実在市山麓町", managerDto.getInputAddressDto().getAddressPostal());
        assertEquals("3丁目6の9", managerDto.getInputAddressDto().getAddressBlock());
        assertEquals("星形ビル444", managerDto.getInputAddressDto().getAddressBuilding());
        assertEquals("987", managerDto.getInputAddressDto().getPostalcode1());
        assertEquals("5432", managerDto.getInputAddressDto().getPostalcode2());
        assertEquals("123456", managerDto.getInputAddressDto().getLgCode());
        assertEquals("2345", managerDto.getInputAddressDto().getMachiazaId());
        assertEquals("3456", managerDto.getInputAddressDto().getBlkId());
        assertEquals("4567", managerDto.getInputAddressDto().getPrcId());
        assertEquals("5678", managerDto.getInputAddressDto().getRsdtId());
        assertEquals("6789", managerDto.getInputAddressDto().getRsdt2Id());

        assertEquals("567", managerDto.getInputAccessDto().getPhon1());
        assertEquals("8901", managerDto.getInputAccessDto().getPhon2());
        assertEquals("2345", managerDto.getInputAccessDto().getPhon3());
        assertEquals("test@example.com", managerDto.getInputAccessDto().getEmail());
        assertEquals("http://example.com/", managerDto.getInputAccessDto().getMyPortalUrl());

        assertEquals(1, managerDto.getInputAccessDto().getSnsServiceId());
        assertEquals(2, managerDto.getInputAccessDto().getSnsServiceCode());
        assertEquals("無名SNS", managerDto.getInputAccessDto().getSnsServiceName());
        assertEquals("http://jakushou.sns.net/", managerDto.getInputAccessDto().getSnsPortalUrl());
        assertEquals("@123hanakao", managerDto.getInputAccessDto().getSnsAccount());

        assertEquals(masterEntity.getAllName(), managerDto.getInputPersonNameDto().getAllName());
        assertEquals(masterEntity.getAllNameKana(), managerDto.getInputPersonNameDto().getAllNameKana());
        assertEquals(masterEntity.getAddressAll(), managerDto.getInputAddressDto().getAddressAll());
        assertEquals(masterEntity.getRiyoushaPartnerApiMasterId(), managerDto.getRiyoushaPartnerApiMasterId());
        assertEquals(masterEntity.getRiyoushaPartnerApiMasterCode(), managerDto.getRiyoushaPartnerApiMasterCode());
        assertEquals(masterEntity.getRiyoushaPersonPropertyId(), managerDto.getRiyoushaPersonPropertyId());
        assertEquals(masterEntity.getRiyoushaPersonPropertyCode(), managerDto.getRiyoushaPersonPropertyCode());

    }

}
