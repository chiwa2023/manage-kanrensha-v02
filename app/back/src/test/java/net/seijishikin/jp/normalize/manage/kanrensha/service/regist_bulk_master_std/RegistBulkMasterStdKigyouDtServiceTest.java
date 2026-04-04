package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_std;

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_std.UpdateWkTblStdKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RegistBulkMasterStdKigyouDtService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("RegistBulkMasterStdKigyouDtServiceTest.sql")
class RegistBulkMasterStdKigyouDtServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistBulkMasterStdKigyouDtService registBulkMasterStdKigyouDtService;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblKanrenshaKigyouDtMasterRepository wkTblKanrenshaKigyouDtMasterRepository;

    @Test
    @Tag("TableTruncate")
    //@Sql("sample_wk_tbl_master_kigyouDt.sql")
    void test() throws Exception {

        // 存在しないデータを呼び出すと0が戻る
        WkTblKanrenshaKigyouDtMasterEntity entityInput00 = new WkTblKanrenshaKigyouDtMasterEntity();
        entityInput00.setWkTblKanrenshaKigyouDtMasterId(839);
        UpdateWkTblStdKigyouDtCapsuleDto capsuleDto00 = new UpdateWkTblStdKigyouDtCapsuleDto();
        capsuleDto00.setWkTblKanrenshaKigyouDtMasterEntity(entityInput00);
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto00.setUserDto(userDto);
        assertEquals(0, registBulkMasterStdKigyouDtService.practice(capsuleDto00).getWkTblKanrenshaKigyouDtMasterId());

        final Integer callId = 412;

        // 編集内容が追加され、元データが履歴になっている
        UpdateWkTblStdKigyouDtCapsuleDto capsuleDto01 = new UpdateWkTblStdKigyouDtCapsuleDto();
        capsuleDto01.setUserDto(userDto);
        WkTblKanrenshaKigyouDtMasterEntity entityInput01 = wkTblKanrenshaKigyouDtMasterRepository.findById(callId).get();
        WkTblKanrenshaKigyouDtMasterEntity entityBase = new WkTblKanrenshaKigyouDtMasterEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setAddressBlock("山ビル2F");
        entityBase.setKanrenshaName("");
        capsuleDto01.setWkTblKanrenshaKigyouDtMasterEntity(entityBase);

        Integer newId = registBulkMasterStdKigyouDtService.practice(capsuleDto01).getWkTblKanrenshaKigyouDtMasterId();
        assertNotEquals(0, newId);
        WkTblKanrenshaKigyouDtMasterEntity entityInput02 = wkTblKanrenshaKigyouDtMasterRepository.findById(callId).get();
        assertEquals(SetTableDataHistoryUtil.DELETE_STATE, entityInput02.getIsLatest());
        WkTblKanrenshaKigyouDtMasterEntity entityCopy = wkTblKanrenshaKigyouDtMasterRepository.findById(newId).get();
        assertEquals(entityBase.getWkTblKanrenshaKigyouDtMasterCode(), entityCopy.getWkTblKanrenshaKigyouDtMasterCode());
        assertEquals(entityBase.getAddressBlock(), entityCopy.getAddressBlock());
        assertEquals(SetTableDataHistoryUtil.INSERT_STATE, entityCopy.getIsLatest());
        assertEquals("名称が入力されていません;", entityCopy.getJudgeReason());
    }

}
