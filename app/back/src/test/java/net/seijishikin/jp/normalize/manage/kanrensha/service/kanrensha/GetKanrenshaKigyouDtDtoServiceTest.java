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
import net.seijishikin.jp.normalize.common_tool.dto.input.InputOrgNameDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.InputKanrenshaPersonLeastDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtPropertyRepository;

/**
 * GetKanrenshaKigyouDtDtoService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("GetKanrenshaKigyouDtDtoServiceTest.sql")
class GetKanrenshaKigyouDtDtoServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetKanrenshaKigyouDtDtoService getKanrenshaKigyouDtDtoService;

    /** マスタRepository */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    /** 連絡先Repository */
    @Autowired
    private KanrenshaKigyouDtAccessRepository kanrenshaKigyouDtAccessRepository;

    /** 住所Repository */
    @Autowired
    private KanrenshaKigyouDtAddressRepository kanrenshaKigyouDtAddressRepository;

    /** 属性Repository */
    @Autowired
    private KanrenshaKigyouDtPropertyRepository kanrenshaKigyouDtPropertyRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        KanrenshaKigyouDtMasterEntity masterEntity = kanrenshaKigyouDtMasterRepository.findById(145).get();

        KanrenshaKigyouDtDto dto = getKanrenshaKigyouDtDtoService.practice(masterEntity);

        assertEquals(masterEntity.getKanrenshaKigyouDtMasterId(), dto.getMasterId());
        assertEquals(masterEntity.getKigyouDtKanrenshaCode(), dto.getKigyouDtKanrenshaCode());
        assertEquals(masterEntity.getHoujinNo(), dto.getHoujinNo());

        KanrenshaKigyouDtAccessEntity accessEntity = kanrenshaKigyouDtAccessRepository.findById(298).get();

        InputAccessDto accessDto = dto.getInputAccessDto();
        assertEquals(accessEntity.getKanrenshaKigyouDtAccessId(), dto.getAccessId());
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
        KanrenshaKigyouDtAddressEntity addressEntity = kanrenshaKigyouDtAddressRepository.findById(314).get();

        assertEquals(addressEntity.getKanrenshaKigyouDtAddressId(), dto.getAddressId());
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

        KanrenshaKigyouDtPropertyEntity propertyEntity = kanrenshaKigyouDtPropertyRepository.findById(461).get();

        assertEquals(propertyEntity.getKanrenshaKigyouDtPropertyId(), dto.getPropertyId());
        assertEquals(propertyEntity.getIsShiten(), dto.getIsShiten());
        assertEquals(propertyEntity.getHoujinSbts(), dto.getHoujinSbts());

        InputOrgNameDto inputOrgNameDto = dto.getInputOrgNameDto();
        assertEquals(masterEntity.getKanrenshaName(), inputOrgNameDto.getOrgName());
        assertEquals(propertyEntity.getOrgNameKana(), inputOrgNameDto.getOrgNameKana());

        InputKanrenshaPersonLeastDto delegeateDto = dto.getOrgDelegateLeastDto();
        assertEquals(masterEntity.getKigyouDtDelegate(), delegeateDto.getPersonName());
        assertEquals(propertyEntity.getOrgDelegateCode(), delegeateDto.getPersonKanrenshaCode());

        // TODO dto.getIsCombineUser();
    }

}
