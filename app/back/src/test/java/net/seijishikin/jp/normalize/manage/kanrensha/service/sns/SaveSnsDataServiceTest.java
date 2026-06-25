package net.seijishikin.jp.normalize.manage.kanrensha.service.sns;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.sns.EditSnsServiceCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.SnsServiceEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.SnsServiceRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SaveSnsDataService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SaveSnsDataServiceTest.sql")
@Transactional
class SaveSnsDataServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SaveSnsDataService saveSnsDataService;

    /** SNSサービスRepository */
    @Autowired
    private SnsServiceRepository snsServiceRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        EditSnsServiceCapsuleDto capsuleDtoAdd = new EditSnsServiceCapsuleDto();
        capsuleDtoAdd.setUserDto(CreateLeastUserForTestUtil.practice());

        SnsServiceEntity entityAdd = new SnsServiceEntity();
        entityAdd.setSnsServiceId(0); // 新規明記
        entityAdd.setSnsPortalUrl("https://jakushou-sns.com/");
        entityAdd.setSnsServiceName("弱小SNS");

        capsuleDtoAdd.setSnsServiceEntity(entityAdd);

        Integer addId = saveSnsDataService.practice(capsuleDtoAdd);
        assertNotEquals(0, addId);

        SnsServiceEntity entityAddAns = snsServiceRepository.findById(addId).get();
        assertEquals(entityAddAns.getSnsServiceName(), entityAdd.getSnsServiceName());
        assertEquals("弱小sns", entityAdd.getSearchText());
        assertEquals(entityAddAns.getSnsPortalUrl(), entityAdd.getSnsPortalUrl());
        assertEquals(247, entityAdd.getSnsServiceCode());

        final Integer callId = 497;
        EditSnsServiceCapsuleDto capsuleDtoUpdate = new EditSnsServiceCapsuleDto();
        capsuleDtoUpdate.setUserDto(CreateLeastUserForTestUtil.practice());
        SnsServiceEntity entityUpdatePre = snsServiceRepository.findById(callId).get();
        entityUpdatePre.setSnsServiceName("鏡文字メッセージ");
        capsuleDtoUpdate.setSnsServiceEntity(entityUpdatePre);

        Integer updateId = saveSnsDataService.practice(capsuleDtoUpdate);
        assertNotEquals(0, updateId);

        SnsServiceEntity entityUpdatePro = snsServiceRepository.findById(callId).get();
        assertFalse(entityUpdatePro.getIsLatest());
        // 履歴とした元のデータは変更していない
        assertNotEquals(entityUpdatePre.getSnsServiceName(), entityUpdatePro.getSnsServiceName());

        SnsServiceEntity entityUpdateEdit = snsServiceRepository.findById(updateId).get();
        // 更新後は更新内容が反映
        assertEquals(entityUpdatePre.getSnsServiceName(), entityUpdateEdit.getSnsServiceName());
        assertEquals(entityUpdatePre.getSnsServiceName(), entityUpdateEdit.getSearchText()); // たまたま検索用と名称が一致
        assertEquals(entityUpdatePre.getSnsServiceCode(), entityUpdateEdit.getSnsServiceCode());
    }

}
