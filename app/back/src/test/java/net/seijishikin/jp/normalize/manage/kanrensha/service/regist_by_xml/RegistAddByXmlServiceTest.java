package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_by_xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.UpdateWkTblAddByXmlCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RegistAddByXmlService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("RegistAddByXmlServiceTest.sql")
class RegistAddByXmlServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistAddByXmlService registAddByXmlService;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 存在しないデータを呼び出すと0が戻る
        WkTblMasterAllByXmlEntity entityInput00 = new WkTblMasterAllByXmlEntity();
        entityInput00.setWkTblMasterAllByXmlId(839);
        UpdateWkTblAddByXmlCapsuleDto capsuleDto00 = new UpdateWkTblAddByXmlCapsuleDto();
        capsuleDto00.setWkTblMasterAllByXmlEntity(entityInput00);
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto00.setUserDto(userDto);
        assertEquals(0,
                registAddByXmlService
                        .practice(capsuleDto00.getWkTblMasterAllByXmlEntity(), capsuleDto00.getUserDto())
                        .getWkTblMasterAllByXmlId());

        final Integer callId = 342;

        // 編集内容が追加され、元データが履歴になっている
        UpdateWkTblAddByXmlCapsuleDto capsuleDto01 = new UpdateWkTblAddByXmlCapsuleDto();
        capsuleDto01.setUserDto(userDto);
        WkTblMasterAllByXmlEntity entityInput01 = wkTblMasterAllByXmlRepository.findById(callId).get();
        WkTblMasterAllByXmlEntity entityBase = new WkTblMasterAllByXmlEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setAllAddress("山ビル2F");
        entityBase.setKanrenshaName("");
        entityBase.setKanrenshaKbn((short)0); // そのままだと個人に分類されるがやめる想定
        capsuleDto01.setWkTblMasterAllByXmlEntity(entityBase);

        Integer newId = registAddByXmlService
                .practice(capsuleDto01.getWkTblMasterAllByXmlEntity(), capsuleDto01.getUserDto())
                .getWkTblMasterAllByXmlId();
        assertNotEquals(0, newId);
        WkTblMasterAllByXmlEntity entityInput02 = wkTblMasterAllByXmlRepository.findById(callId).get();
        assertEquals(SetTableDataHistoryUtil.DELETE_STATE, entityInput02.getIsLatest());
        WkTblMasterAllByXmlEntity entityCopy = wkTblMasterAllByXmlRepository.findById(newId).get();
        assertEquals(entityBase.getWkTblMasterAllByXmlCode(), entityCopy.getWkTblMasterAllByXmlCode());
        assertEquals(entityBase.getAllAddress(), entityCopy.getAllAddress());
        assertEquals(SetTableDataHistoryUtil.INSERT_STATE, entityCopy.getIsLatest());

    }

}
