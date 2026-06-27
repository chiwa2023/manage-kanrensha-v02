package net.seijishikin.jp.normalize.manage.kanrensha.controller.address_rsdt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.seijishikin.jp.normalize.common_tool.utils.GetObjectMapperWithTimeModuleUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.EditAddressRsdtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditAddressRsdtController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("../../service/address_rsdt/EditAddressRsdtServiceTest.sql")
class EditAddressRsdtControllerTest {
    // CHECKSTYLE:OFF MagicNumber

    /** WebApplicationContext */
    @Autowired
    private WebApplicationContext context;

    /** MockMvc */
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context) //
                .apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void test() throws Exception {

        // 既存データの編集
        final String lgCode = "131016";
        final Integer deleteId = 624;

        AddressRsdtTemplateEntity srcEntity = this.getAddressEntity(lgCode, deleteId);
        AddressRsdtTemplateEntity baseEntity = new AddressRsdtTemplateEntity();
        BeanUtils.copyProperties(srcEntity, baseEntity);

        baseEntity.setPostalcode1("123");
        baseEntity.setPostalcode2("3456");
        baseEntity.setMachiazaId("0013018");
        baseEntity.setPrcId("017");
        baseEntity.setBlkId("334");
        baseEntity.setRsdtId("556");
        baseEntity.setRsdt2Id("778");
        baseEntity.setEffectDate(LocalDate.of(2022, 11, 19));
        baseEntity.setAbolishDate(LocalDate.of(2041, 2, 6));
        baseEntity.setAddressBlock("札幌市豊平区月寒東五条十八丁目aaa17番地11号");
        baseEntity.setAddressBuilding("99号室");

        EditAddressRsdtCapsuleDto capsuleDto = new EditAddressRsdtCapsuleDto();
        capsuleDto.setEditEntity(baseEntity);
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        String path = PathRouteConstants.ROOT + "/address-rsdt/edit";

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) //
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

    private AddressRsdtTemplateEntity getAddressEntity(final String lgCode, final Integer rsdtId) {
        String sql = "SELECT * FROM address_rsdt_" + lgCode + "  WHERE address_rsdt_id = " + rsdtId;
        Query query = entityManager.createNativeQuery(sql, AddressRsdtTemplateEntity.class);
        return (AddressRsdtTemplateEntity) query.getSingleResult(); // NOPMD LawDemeter
    }

}
