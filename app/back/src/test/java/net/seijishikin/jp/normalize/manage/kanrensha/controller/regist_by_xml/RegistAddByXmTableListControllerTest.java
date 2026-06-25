package net.seijishikin.jp.normalize.manage.kanrensha.controller.regist_by_xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
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
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.UpdateWkTblAddByXmlTableListCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonAddMinRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import net.seijishikin.jp.normalize.common_tool.utils.GetObjectMapperWithTimeModuleUtil;

/**
 * RegistAddByXmTableListlController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("RegistAddByXmTableListControllerTest.sql")
class RegistAddByXmTableListControllerTest {
    // CHECKSTYLE:OFF

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    /** ワークテーブルマスタXMLRepository */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /** ワークテーブル個人最小マスタRepository */
    @Autowired
    private WkTblKanrenshaPersonAddMinRepository wkTblKanrenshaPersonAddMinRepository;

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void test() throws Exception {

        assertEquals(0L, wkTblKanrenshaPersonAddMinRepository.count());

        WkTblMasterAllByXmlEntity entityInput01 = wkTblMasterAllByXmlRepository.findById(342).get();
        WkTblMasterAllByXmlEntity entityBase01 = new WkTblMasterAllByXmlEntity();
        BeanUtils.copyProperties(entityInput01, entityBase01);
        entityBase01.setAllAddress("山ビル2F"); // 住所のみ変更して履歴積みあげ
        entityBase01.setKanrenshaKbn((short) 0);

        WkTblMasterAllByXmlEntity entityInput02 = wkTblMasterAllByXmlRepository.findById(345).get();
        WkTblMasterAllByXmlEntity entityBase02 = new WkTblMasterAllByXmlEntity();
        BeanUtils.copyProperties(entityInput02, entityBase02);
        entityBase02.setAllAddress("山ビル2F"); // 編集も関係ない
        entityBase02.setIsAffected(false); // リストに入っているが編集しない

        WkTblMasterAllByXmlEntity entityInput03 = wkTblMasterAllByXmlRepository.findById(344).get();
        WkTblMasterAllByXmlEntity entityBase03 = new WkTblMasterAllByXmlEntity();
        BeanUtils.copyProperties(entityInput03, entityBase03);
        entityBase03.setKanrenshaKbn((short) 1);
        entityBase03.setIsAffected(true); // 編集予定なしから変更
        entityBase03.setAllAddress("山ビル2F"); // 住所を変更して関連者個人に移管

        UpdateWkTblAddByXmlTableListCapsuleDto capsuleDto = new UpdateWkTblAddByXmlTableListCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.getListWkTblByXml().add(entityBase01);
        capsuleDto.getListWkTblByXml().add(entityBase02);
        capsuleDto.getListWkTblByXml().add(entityBase03);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = PathRouteConstants.ROOT + "/regist-by-xml/update-list";
        
        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());

        // この処理は変更を待って結果を戻しているのでデータ整合性を確認することも可能
        WkTblMasterAllByXmlEntity entityAns01 = wkTblMasterAllByXmlRepository.findById(342).get();
        assertEquals(false, entityAns01.getIsLatest()); // 履歴が積みあがったので削除済
        WkTblMasterAllByXmlEntity entityAns02 = wkTblMasterAllByXmlRepository.findById(345).get();
        assertEquals(true, entityAns02.getIsLatest()); // 編集対象外のため引き続き最新データ
        assertEquals("和歌山県実在市山麓町", entityAns02.getAllAddress()); // 住所は更新されていない

        WkTblMasterAllByXmlEntity entityAns03 = wkTblMasterAllByXmlRepository.findById(344).get();
        assertEquals(false, entityAns03.getIsLatest()); // 履歴が積みあがったので削除済

        // 個人に移管したので0からデータが増えた
        assertEquals(1L, wkTblKanrenshaPersonAddMinRepository.count());
    }

}
