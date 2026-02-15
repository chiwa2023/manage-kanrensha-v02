package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.DeleteRiyoushaOrgCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaOrgMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaOrgPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaCombineOrgRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaOrgMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaOrgPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * DeleteRiyoushaOrrgSevice単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetRiyoushaOrgDtoServiceTest.sql")
class DeleteRiyoushaOrgSeviceTest {

    /** テスト対象 */
    @Autowired
    private DeleteRiyoushaOrgSevice deleteRiyoushaOrgSevice;

    /** 利用者組織マスタRepository */
    @Autowired
    private RiyoushaOrgMasterRepository riyoushaOrgMasterRepository;

    /** 利用者組織属性Repository */
    @Autowired
    private RiyoushaOrgPropertyRepository riyoushaOrgPropertyRepository;

    /** 利用者組織個人紐づけRepository */
    @Autowired
    private RiyoushaCombineOrgRepository riyoushaCombineOrgRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 削除失敗
        FrameworkMessageAndResultDto resultDto0 = deleteRiyoushaOrgSevice.practice(new DeleteRiyoushaOrgCapsuleDto());
        assertTrue(resultDto0.getIsFailure());

        final Integer deleteId = 318;
        
        DeleteRiyoushaOrgCapsuleDto capsuleDto = new DeleteRiyoushaOrgCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        RiyoushaOrgMasterEntity masterEntity = riyoushaOrgMasterRepository.findById(deleteId).get();

        capsuleDto.setMasterEntity(masterEntity);

        FrameworkMessageAndResultDto resultDto1 = deleteRiyoushaOrgSevice.practice(capsuleDto);
        assertFalse(resultDto1.getIsFailure());

        RiyoushaOrgMasterEntity entityResultMaster = riyoushaOrgMasterRepository.findById(deleteId).get();
        assertFalse(entityResultMaster.getIsLatest());

        RiyoushaOrgPropertyEntity entityResultProperty = riyoushaOrgPropertyRepository
                .findById(entityResultMaster.getRiyoushaOrgPropertyId()).get();
        assertFalse(entityResultProperty.getIsLatest());

        List<RiyoushaCombineOrgEntity> listResultPerson = riyoushaCombineOrgRepository
                .findByOrgRiyoushaCodeAndIsLatestTrue(entityResultMaster.getRiyoushaOrgMasterCode());

        assertTrue(listResultPerson.isEmpty());
    }

}
