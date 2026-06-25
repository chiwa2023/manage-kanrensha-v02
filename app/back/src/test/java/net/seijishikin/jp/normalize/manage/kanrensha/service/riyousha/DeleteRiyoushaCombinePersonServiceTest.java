package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.RiyoushaCombinePersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaCombineOrgRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * DeleteRiyoushaCombinePersonService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetRiyoushaOrgDtoServiceTest.sql")
class DeleteRiyoushaCombinePersonServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private DeleteRiyoushaCombinePersonService deleteRiyoushaCombinePersonService;

    /** 利用者組織紐づけ個人Respoitory */
    @Autowired
    private RiyoushaCombineOrgRepository riyoushaCombineOrgRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        FrameworkMessageAndResultDto resultDto0 = deleteRiyoushaCombinePersonService
                .practice(new RiyoushaCombinePersonCapsuleDto());
        assertTrue(resultDto0.getIsFailure());

        RiyoushaCombinePersonCapsuleDto capsuleDto = new RiyoushaCombinePersonCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        final Integer selectedId = 756;
        RiyoushaCombineOrgEntity entity = riyoushaCombineOrgRepository.findById(selectedId).get();
        capsuleDto.setCombineEntity(entity);

        FrameworkMessageAndResultDto resultDto1 = deleteRiyoushaCombinePersonService.practice(capsuleDto);
        assertFalse(resultDto1.getIsFailure());

        RiyoushaCombineOrgEntity entityResilt = riyoushaCombineOrgRepository.findById(selectedId).get();
        assertFalse(entityResilt.getIsLatest());

        List<RiyoushaCombineOrgEntity> list = riyoushaCombineOrgRepository.findByOrgRiyoushaCodeAndIsLatestTrue(216);
        assertEquals(2, list.size()); // 呼び出し時には2件に減っている
    }

}
