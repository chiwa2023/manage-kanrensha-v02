package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_min;

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_min.UpdateWkTblMinKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtAddMinRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RegistBulkMasterMinKigyouDtService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("RegistBulkMasterMinKigyouDtServiceTest.sql")
class RegistBulkMasterMinKigyouDtServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistBulkMasterMinKigyouDtService registBulkMasterMinKigyouDtService;

    /** ワークテーブルマスタ企業／団体最小Repository */
    @Autowired
    private WkTblKanrenshaKigyouDtAddMinRepository wkTblKanrenshaKigyouDtAddMinRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    void test() throws Exception {

        // 存在しないデータを呼び出すと0が戻る
        WkTblKanrenshaKigyouDtAddMinEntity entityInput00 = new WkTblKanrenshaKigyouDtAddMinEntity();
        entityInput00.setWkTblKanrenshaKigyouDtAddMinId(839);
        UpdateWkTblMinKigyouDtCapsuleDto capsuleDto00 = new UpdateWkTblMinKigyouDtCapsuleDto();
        capsuleDto00.setWkTblKanrenshaKigyouDtAddMinEntity(entityInput00);
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto00.setUserDto(userDto);
        assertEquals(0, registBulkMasterMinKigyouDtService.practice(capsuleDto00).getWkTblKanrenshaKigyouDtAddMinId());

        final Integer callId = 102;

        // 編集内容が追加され、元データが履歴になっている
        UpdateWkTblMinKigyouDtCapsuleDto capsuleDto01 = new UpdateWkTblMinKigyouDtCapsuleDto();
        capsuleDto01.setUserDto(userDto);
        WkTblKanrenshaKigyouDtAddMinEntity entityInput01 = wkTblKanrenshaKigyouDtAddMinRepository.findById(callId).get();
        WkTblKanrenshaKigyouDtAddMinEntity entityBase = new WkTblKanrenshaKigyouDtAddMinEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setAllAddress("山ビル2F");
        entityBase.setKanrenshaName("");
        capsuleDto01.setWkTblKanrenshaKigyouDtAddMinEntity(entityBase);

        Integer newId = registBulkMasterMinKigyouDtService.practice(capsuleDto01).getWkTblKanrenshaKigyouDtAddMinId();
        assertNotEquals(0, newId);
        WkTblKanrenshaKigyouDtAddMinEntity entityInput02 = wkTblKanrenshaKigyouDtAddMinRepository.findById(callId).get();
        assertEquals(SetTableDataHistoryUtil.DELETE_STATE, entityInput02.getIsLatest());
        WkTblKanrenshaKigyouDtAddMinEntity entityCopy = wkTblKanrenshaKigyouDtAddMinRepository.findById(newId).get();
        assertEquals(entityBase.getWkTblKanrenshaKigyouDtAddMinCode(), entityCopy.getWkTblKanrenshaKigyouDtAddMinCode());
        assertEquals(entityBase.getAllAddress(), entityCopy.getAllAddress());
        assertEquals(SetTableDataHistoryUtil.INSERT_STATE, entityCopy.getIsLatest());
        assertEquals("名称が入力されていません;", entityCopy.getJudgeReason());
    }

}
