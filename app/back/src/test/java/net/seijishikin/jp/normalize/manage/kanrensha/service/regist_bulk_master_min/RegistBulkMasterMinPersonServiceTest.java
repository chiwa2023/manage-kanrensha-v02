package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_min;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_min.UpdateWkTblMinPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonAddMinRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RegistBulkMasterMinPersonService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("RegistBulkMasterMinPersonServiceTest.sql")
class RegistBulkMasterMinPersonServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistBulkMasterMinPersonService registBulkMasterMinPersonService;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblKanrenshaPersonAddMinRepository wkTblKanrenshaPersonAddMinRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    void test() throws Exception {

        // 存在しないデータを呼び出すと0が戻る
        WkTblKanrenshaPersonAddMinEntity entityInput00 = new WkTblKanrenshaPersonAddMinEntity();
        entityInput00.setWkTblKanrenshaPersonAddMinId(839);
        UpdateWkTblMinPersonCapsuleDto capsuleDto00 = new UpdateWkTblMinPersonCapsuleDto();
        capsuleDto00.setWkTblKanrenshaPersonAddMinEntity(entityInput00);
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto00.setUserDto(userDto);
        assertEquals(0, registBulkMasterMinPersonService.practice(capsuleDto00).getWkTblKanrenshaPersonAddMinId());

        final Integer callId = 218;

        // 編集内容が追加され、元データが履歴になっている
        UpdateWkTblMinPersonCapsuleDto capsuleDto01 = new UpdateWkTblMinPersonCapsuleDto();
        capsuleDto01.setUserDto(userDto);
        WkTblKanrenshaPersonAddMinEntity entityInput01 = wkTblKanrenshaPersonAddMinRepository.findById(callId).get();
        WkTblKanrenshaPersonAddMinEntity entityBase = new WkTblKanrenshaPersonAddMinEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setAllAddress("山ビル2F");
        entityBase.setKanrenshaName("");
        capsuleDto01.setWkTblKanrenshaPersonAddMinEntity(entityBase);

        Integer newId = registBulkMasterMinPersonService.practice(capsuleDto01).getWkTblKanrenshaPersonAddMinId();
        assertNotEquals(0, newId);
        WkTblKanrenshaPersonAddMinEntity entityInput02 = wkTblKanrenshaPersonAddMinRepository.findById(callId).get();
        assertEquals(SetTableDataHistoryUtil.DELETE_STATE, entityInput02.getIsLatest());
        WkTblKanrenshaPersonAddMinEntity entityCopy = wkTblKanrenshaPersonAddMinRepository.findById(newId).get();
        assertEquals(entityBase.getWkTblKanrenshaPersonAddMinCode(), entityCopy.getWkTblKanrenshaPersonAddMinCode());
        assertEquals(entityBase.getAllAddress(), entityCopy.getAllAddress());
        assertEquals(SetTableDataHistoryUtil.INSERT_STATE, entityCopy.getIsLatest());
        assertEquals("名称が入力されていません;", entityCopy.getJudgeReason());
    }

}
