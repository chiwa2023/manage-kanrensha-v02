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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_std.UpdateWkTblStdSeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RegistBulkMasterStdSeijidantaiService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("RegistBulkMasterStdSeijidantaiServiceTest.sql")
class RegistBulkMasterStdSeijidantaiServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistBulkMasterStdSeijidantaiService registBulkMasterStdSeijidantaiService;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblKanrenshaSeijidantaiMasterRepository wkTblKanrenshaSeijidantaiMasterRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    void test() throws Exception {

        // 存在しないデータを呼び出すと0が戻る
        WkTblKanrenshaSeijidantaiMasterEntity entityInput00 = new WkTblKanrenshaSeijidantaiMasterEntity();
        entityInput00.setWkTblKanrenshaSeijidantaiMasterId(839);
        UpdateWkTblStdSeijidantaiCapsuleDto capsuleDto00 = new UpdateWkTblStdSeijidantaiCapsuleDto();
        capsuleDto00.setWkTblKanrenshaSeijidantaiMasterEntity(entityInput00);
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto00.setUserDto(userDto);
        assertEquals(0,
                registBulkMasterStdSeijidantaiService.practice(capsuleDto00).getWkTblKanrenshaSeijidantaiMasterId());

        final Integer callId = 533;

        // 編集内容が追加され、元データが履歴になっている
        UpdateWkTblStdSeijidantaiCapsuleDto capsuleDto01 = new UpdateWkTblStdSeijidantaiCapsuleDto();
        capsuleDto01.setUserDto(userDto);
        WkTblKanrenshaSeijidantaiMasterEntity entityInput01 = wkTblKanrenshaSeijidantaiMasterRepository.findById(callId)
                .get();
        WkTblKanrenshaSeijidantaiMasterEntity entityBase = new WkTblKanrenshaSeijidantaiMasterEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setAddressBlock("山ビル2F");
        entityBase.setKanrenshaName("");
        capsuleDto01.setWkTblKanrenshaSeijidantaiMasterEntity(entityBase);

        Integer newId = registBulkMasterStdSeijidantaiService.practice(capsuleDto01)
                .getWkTblKanrenshaSeijidantaiMasterId();
        assertNotEquals(0, newId);
        WkTblKanrenshaSeijidantaiMasterEntity entityInput02 = wkTblKanrenshaSeijidantaiMasterRepository.findById(callId)
                .get();
        assertEquals(SetTableDataHistoryUtil.DELETE_STATE, entityInput02.getIsLatest());
        WkTblKanrenshaSeijidantaiMasterEntity entityCopy = wkTblKanrenshaSeijidantaiMasterRepository.findById(newId)
                .get();
        assertEquals(entityBase.getWkTblKanrenshaSeijidantaiMasterCode(),
                entityCopy.getWkTblKanrenshaSeijidantaiMasterCode());
        assertEquals(entityBase.getAddressBlock(), entityCopy.getAddressBlock());
        assertEquals(SetTableDataHistoryUtil.INSERT_STATE, entityCopy.getIsLatest());
        assertEquals("名称が入力されていません;", entityCopy.getJudgeReason());
    }

}
