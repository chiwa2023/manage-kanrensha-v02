package net.seijishikin.jp.normalize.manage.kanrensha.controller.works_approval;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.seijishikin.jp.normalize.common_tool.utils.GetObjectMapperWithTimeModuleUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.works_approval.SaveWorksApprovalCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaAddressBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SaveApprovalAddressControler単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("../../service/works_approval/SaveApprovalAddressServiceTest.sql")
class SaveApprovalAddressControlerTest {
    // CHECKSTYLE:OFF MagicNumber

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    /** 関連者企業団体住所Repository */
    @Autowired
    private KanrenshaKigyouDtAddressRepository kanrenshaKigyouDtAddressRepository;

    /** 関連者個人住所Repository */
    @Autowired
    private KanrenshaPersonAddressRepository kanrenshaPersonAddressRepository;

    /** 関連者政治団体住所Repository */
    @Autowired
    private KanrenshaSeijidantaiAddressRepository kanrenshaSeijidantaiAddressRepository;

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void test() throws Exception {
        final List<KanrenshaAddressBaseEntity> list = new ArrayList<>();

        KanrenshaKigyouDtAddressEntity entityAddressKigyouDt04 = kanrenshaKigyouDtAddressRepository.findById(2204)
                .get();
        KanrenshaAddressBaseEntity entityKigyouDt04 = new KanrenshaAddressBaseEntity();
        BeanUtils.copyProperties(entityAddressKigyouDt04, entityKigyouDt04);
        entityKigyouDt04.setKanrenshaKbn(KanrenshaKbnConstants.KIGYOU_DT);
        entityKigyouDt04.setKanrenshaAddressId(entityAddressKigyouDt04.getKanrenshaKigyouDtAddressId());
        entityKigyouDt04.setLgCode("124466"); // コードしか触っていないのでallAddressに影響しない
        list.add(entityKigyouDt04);

        KanrenshaPersonAddressEntity entityAddressPerson04 = kanrenshaPersonAddressRepository.findById(2204).get();
        KanrenshaAddressBaseEntity entityPerson04 = new KanrenshaAddressBaseEntity();
        BeanUtils.copyProperties(entityAddressPerson04, entityPerson04);
        entityPerson04.setKanrenshaKbn(KanrenshaKbnConstants.PERSON);
        entityPerson04.setKanrenshaAddressId(entityAddressPerson04.getKanrenshaPersonAddressId());
        entityPerson04.setLgCode("224466"); // コードしか触っていないのでallAddressに影響しない
        list.add(entityPerson04);

        KanrenshaSeijidantaiAddressEntity entityAddressSeijidantai04 = kanrenshaSeijidantaiAddressRepository
                .findById(2204).get();
        KanrenshaAddressBaseEntity entitySeijidantai04 = new KanrenshaAddressBaseEntity();
        BeanUtils.copyProperties(entityAddressSeijidantai04, entitySeijidantai04);
        entitySeijidantai04.setKanrenshaKbn(KanrenshaKbnConstants.SEIJIDANTAI);
        entitySeijidantai04.setKanrenshaAddressId(entityAddressSeijidantai04.getKanrenshaSeijidantaiAddressId());
        entitySeijidantai04.setLgCode("324466"); // コードしか触っていないのでallAddressに影響しない
        list.add(entitySeijidantai04);

        SaveWorksApprovalCapsuleDto capsuleDto = new SaveWorksApprovalCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setListAddress(list);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = PathRouteConstants.ROOT + "/works-approval/save-address";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
