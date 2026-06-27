package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_std;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_std.UpdateWkTblStdPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RegistBulkMasterStdPersonService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("RegistBulkMasterStdPersonServiceTest.sql")
class RegistBulkMasterStdPersonServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistBulkMasterStdPersonService registBulkMasterStdPersonService;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblKanrenshaPersonMasterRepository wkTblKanrenshaPersonMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 存在しないデータを呼び出すと0が戻る
        WkTblKanrenshaPersonMasterEntity entityInput00 = new WkTblKanrenshaPersonMasterEntity();
        entityInput00.setWkTblKanrenshaPersonMasterId(839);
        UpdateWkTblStdPersonCapsuleDto capsuleDto00 = new UpdateWkTblStdPersonCapsuleDto();
        capsuleDto00.setWkTblKanrenshaPersonMasterEntity(entityInput00);
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto00.setUserDto(userDto);
        assertEquals(0, registBulkMasterStdPersonService.practice(capsuleDto00).getWkTblKanrenshaPersonMasterId());

        final Integer callId = 296;
        // 編集内容が追加され、元データが履歴になっている
        UpdateWkTblStdPersonCapsuleDto capsuleDto01 = new UpdateWkTblStdPersonCapsuleDto();
        capsuleDto01.setUserDto(userDto);
        WkTblKanrenshaPersonMasterEntity entityInput01 = wkTblKanrenshaPersonMasterRepository.findById(callId).get();
        WkTblKanrenshaPersonMasterEntity entityBase = new WkTblKanrenshaPersonMasterEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setAddressBlock("山ビル2F");
        entityBase.setKanrenshaName("");
        capsuleDto01.setWkTblKanrenshaPersonMasterEntity(entityBase);

        Integer newId = registBulkMasterStdPersonService.practice(capsuleDto01).getWkTblKanrenshaPersonMasterId();
        assertNotEquals(0, newId);
        WkTblKanrenshaPersonMasterEntity entityInput02 = wkTblKanrenshaPersonMasterRepository.findById(callId).get();
        assertEquals(SetTableDataHistoryUtil.DELETE_STATE, entityInput02.getIsLatest());
        WkTblKanrenshaPersonMasterEntity entityCopy = wkTblKanrenshaPersonMasterRepository.findById(newId).get();
        assertEquals(entityBase.getWkTblKanrenshaPersonMasterCode(), entityCopy.getWkTblKanrenshaPersonMasterCode());
        assertEquals(entityBase.getAddressBlock(), entityCopy.getAddressBlock());
        assertEquals(SetTableDataHistoryUtil.INSERT_STATE, entityCopy.getIsLatest());
        assertEquals("名称が入力されていません;", entityCopy.getJudgeReason());
    }

}
