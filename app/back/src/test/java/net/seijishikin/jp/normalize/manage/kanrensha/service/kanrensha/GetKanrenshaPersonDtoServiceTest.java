package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAccessDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputPersonNameDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputShokugyouDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonPropertyRepository;

/**
 * GetKanrenshaPersonDtoService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("GetKanrenshaPersonDtoServiceTest.sql")
class GetKanrenshaPersonDtoServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetKanrenshaPersonDtoService getKanrenshaPersonDtoService;

    /** マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /** 連絡先Repository */
    @Autowired
    private KanrenshaPersonAccessRepository kanrenshaPersonAccessRepository;

    /** 住所Repository */
    @Autowired
    private KanrenshaPersonAddressRepository kanrenshaPersonAddressRepository;

    /** 属性Repository */
    @Autowired
    private KanrenshaPersonPropertyRepository kanrenshaPersonPropertyRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        KanrenshaPersonMasterEntity masterEntity = kanrenshaPersonMasterRepository.findById(391).get();

        KanrenshaPersonDto dto = getKanrenshaPersonDtoService.practice(masterEntity);

        assertEquals(masterEntity.getKanrenshaPersonMasterId(), dto.getMasterId());
        assertEquals(masterEntity.getPersonKanrenshaCode(), dto.getPersonKanrenshaCode());

        KanrenshaPersonAccessEntity accessEntity = kanrenshaPersonAccessRepository.findById(658).get();

        InputAccessDto accessDto = dto.getInputAccessDto();
        assertEquals(accessEntity.getKanrenshaPersonAccessId(), dto.getAccessId());
        assertEquals(accessEntity.getPhon1(), accessDto.getPhon1());
        assertEquals(accessEntity.getPhon2(), accessDto.getPhon2());
        assertEquals(accessEntity.getPhon3(), accessDto.getPhon3());
        assertEquals(accessEntity.getEmail(), accessDto.getEmail());
        assertEquals(accessEntity.getMyPortalUrl(), accessDto.getMyPortalUrl());
        assertEquals(accessEntity.getSnsServiceId(), accessDto.getSnsServiceId());
        assertEquals(accessEntity.getSnsServiceCode(), accessDto.getSnsServiceCode());
        assertEquals(accessEntity.getSnsServiceName(), accessDto.getSnsServiceName());
        assertEquals(accessEntity.getSnsPortalUrl(), accessDto.getSnsPortalUrl());
        assertEquals(accessEntity.getSnsAccount(), accessDto.getSnsAccount());

        InputAddressDto addressDto = dto.getInputAddressDto();
        KanrenshaPersonAddressEntity addressEntity = kanrenshaPersonAddressRepository.findById(774).get();

        assertEquals(addressEntity.getKanrenshaPersonAddressId(), dto.getAddressId());
        assertEquals(masterEntity.getAllAddress(), addressDto.getAddressAll());
        assertEquals(addressEntity.getAddressPostal(), addressDto.getAddressPostal());
        assertEquals(addressEntity.getAddressBlock(), addressDto.getAddressBlock());
        assertEquals(addressEntity.getAddressBuilding(), addressDto.getAddressBuilding());
        assertEquals(addressEntity.getPostalcode1(), addressDto.getPostalcode1());
        assertEquals(addressEntity.getPostalcode2(), addressDto.getPostalcode2());
        assertEquals(addressEntity.getLgCode(), addressDto.getLgCode());
        assertEquals(addressEntity.getMachiazaId(), addressDto.getMachiazaId());
        assertEquals(addressEntity.getBlkId(), addressDto.getBlkId());
        assertEquals(addressEntity.getPrcId(), addressDto.getPrcId());
        assertEquals(addressEntity.getRsdtId(), addressDto.getRsdtId());
        assertEquals(addressEntity.getRsdt2Id(), addressDto.getRsdt2Id());

        KanrenshaPersonPropertyEntity propertyEntity = kanrenshaPersonPropertyRepository.findById(826).get();

        assertEquals(propertyEntity.getKanrenshaPersonPropertyId(), dto.getPropertyId());

        InputPersonNameDto personNameDto = dto.getInputPersonNameDto();
        assertEquals(masterEntity.getKanrenshaName(), personNameDto.getAllName());
        assertEquals(propertyEntity.getAllNameKana(), personNameDto.getAllNameKana());
        assertEquals(propertyEntity.getLastName(), personNameDto.getLastName());
        assertEquals(propertyEntity.getFirstName(), personNameDto.getFirstName());
        assertEquals(propertyEntity.getMiddleName(), personNameDto.getMiddleName());
        assertEquals(propertyEntity.getLastNameKana(), personNameDto.getLastNameKana());
        assertEquals(propertyEntity.getFirstNameKana(), personNameDto.getFirstNameKana());
        assertEquals(propertyEntity.getMiddleNameKana(), personNameDto.getMiddleNameKana());

        InputShokugyouDto shokugyouDto = dto.getInputShokugyouDto();
        assertEquals(masterEntity.getPersonShokugyou(), shokugyouDto.getAllShokugyou());
        assertEquals(propertyEntity.getGyoushu(), shokugyouDto.getGyoushu());
        assertEquals(propertyEntity.getYakushoku(), shokugyouDto.getYakushoku());
        assertEquals(propertyEntity.getShokugyouUserWrite(), shokugyouDto.getShokugyouUserWrite());
        assertEquals(propertyEntity.getKigyouDtNo(), shokugyouDto.getHoujinNo());
        assertEquals(propertyEntity.getKigyouDtName(), shokugyouDto.getHoujinName());
        assertEquals(propertyEntity.getKigyouDtAddress(), shokugyouDto.getHoujinAddress());
        assertEquals(propertyEntity.getIsForeign(), dto.getIsForeign());
    }

}
