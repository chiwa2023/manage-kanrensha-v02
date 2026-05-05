package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.InputKanrenshaPersonLeastDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiPropertyRepository;

/**
 * GetKanrenshaSeijidantaiDtoService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("GetKanrenshaSeijidantaiDtoServiceTest.sql")
class GetKanrenshaSeijidantaiDtoServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetKanrenshaSeijidantaiDtoService getKanrenshaSeijidantaiDtoService;

    /** マスタRepository */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    /** 連絡先Repository */
    @Autowired
    private KanrenshaSeijidantaiAccessRepository kanrenshaSeijidantaiAccessRepository;

    /** 住所Repository */
    @Autowired
    private KanrenshaSeijidantaiAddressRepository kanrenshaSeijidantaiAddressRepository;

    /** 属性Repository */
    @Autowired
    private KanrenshaSeijidantaiPropertyRepository kanrenshaSeijidantaiPropertyRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        KanrenshaSeijidantaiMasterEntity masterEntity = kanrenshaSeijidantaiMasterRepository.findById(1215).get();

        KanrenshaSeijidantaiDto dto = getKanrenshaSeijidantaiDtoService.practice(masterEntity);

        assertEquals(masterEntity.getKanrenshaSeijidantaiMasterId(), dto.getMasterId());
        assertEquals(masterEntity.getSeijidantaiKanrenshaCode(), dto.getSeijidantaiKanrenshaCode());
        assertEquals(masterEntity.getPoliOrgNo(), dto.getPoliOrgNo());
        assertEquals(masterEntity.getDantaiKbn(), dto.getDantaiKbn());

        KanrenshaSeijidantaiAccessEntity accessEntity = kanrenshaSeijidantaiAccessRepository.findById(1324).get();

        InputAccessDto accessDto = dto.getInputAccessDto();
        assertEquals(accessEntity.getKanrenshaSeijidantaiAccessId(), dto.getAccessId());
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
        KanrenshaSeijidantaiAddressEntity addressEntity = kanrenshaSeijidantaiAddressRepository.findById(1411).get();

        assertEquals(addressEntity.getKanrenshaSeijidantaiAddressId(), dto.getAddressId());
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

        KanrenshaSeijidantaiPropertyEntity propertyEntity = kanrenshaSeijidantaiPropertyRepository.findById(1565).get();

        assertEquals(propertyEntity.getKanrenshaSeijidantaiPropertyId(), dto.getPropertyId());

        InputOrgNameDto orgNameDto = dto.getInputOrgNameDto();
        assertEquals(masterEntity.getKanrenshaName(), orgNameDto.getOrgName());
        assertEquals(propertyEntity.getOrgNameKana(), orgNameDto.getOrgNameKana());

        InputKanrenshaPersonLeastDto delegateDto = dto.getOrgDelegateLeastDto();
        assertEquals(propertyEntity.getOrgDelegateCode(), delegateDto.getPersonKanrenshaCode());
        assertEquals(masterEntity.getSeijidantaiDelegate(), delegateDto.getPersonName());

        InputKanrenshaPersonLeastDto accountMgrDto = dto.getAccounrMgrLeastDto();
        assertEquals(propertyEntity.getAccountMgrCode(), accountMgrDto.getPersonKanrenshaCode());
        assertEquals(propertyEntity.getAccountMgrName(), accountMgrDto.getPersonName());

    }

}
