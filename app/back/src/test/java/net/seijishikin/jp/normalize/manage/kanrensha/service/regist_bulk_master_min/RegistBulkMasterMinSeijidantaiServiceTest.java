package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_min;

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_min.UpdateWkTblMinSeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiAddMinRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RegistBulkMasterMinSeijidantaiService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("RegistBulkMasterMinSeijidantaiServiceTest.sql")
class RegistBulkMasterMinSeijidantaiServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistBulkMasterMinSeijidantaiService registBulkMasterMinSeijidantaiService;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblKanrenshaSeijidantaiAddMinRepository wkTblKanrenshaSeijidantaiAddMinRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 存在しないデータを呼び出すと0が戻る
        WkTblKanrenshaSeijidantaiAddMinEntity entityInput00 = new WkTblKanrenshaSeijidantaiAddMinEntity();
        entityInput00.setWkTblKanrenshaSeijidantaiAddMinId(839);
        UpdateWkTblMinSeijidantaiCapsuleDto capsuleDto00 = new UpdateWkTblMinSeijidantaiCapsuleDto();
        capsuleDto00.setWkTblKanrenshaSeijidantaiAddMinEntity(entityInput00);
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto00.setUserDto(userDto);
        assertEquals(0,
                registBulkMasterMinSeijidantaiService.practice(capsuleDto00).getWkTblKanrenshaSeijidantaiAddMinId());

        final Integer callId = 298;

        // 編集内容が追加され、元データが履歴になっている
        UpdateWkTblMinSeijidantaiCapsuleDto capsuleDto01 = new UpdateWkTblMinSeijidantaiCapsuleDto();
        capsuleDto01.setUserDto(userDto);
        WkTblKanrenshaSeijidantaiAddMinEntity entityInput01 = wkTblKanrenshaSeijidantaiAddMinRepository.findById(callId)
                .get();
        WkTblKanrenshaSeijidantaiAddMinEntity entityBase = new WkTblKanrenshaSeijidantaiAddMinEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setAllAddress("山ビル2F");
        entityBase.setKanrenshaName("");
        capsuleDto01.setWkTblKanrenshaSeijidantaiAddMinEntity(entityBase);

        Integer newId = registBulkMasterMinSeijidantaiService.practice(capsuleDto01)
                .getWkTblKanrenshaSeijidantaiAddMinId();
        assertNotEquals(0, newId);
        WkTblKanrenshaSeijidantaiAddMinEntity entityInput02 = wkTblKanrenshaSeijidantaiAddMinRepository.findById(callId)
                .get();
        assertEquals(SetTableDataHistoryUtil.DELETE_STATE, entityInput02.getIsLatest());
        WkTblKanrenshaSeijidantaiAddMinEntity entityCopy = wkTblKanrenshaSeijidantaiAddMinRepository.findById(newId)
                .get();
        assertEquals(entityBase.getWkTblKanrenshaSeijidantaiAddMinCode(),
                entityCopy.getWkTblKanrenshaSeijidantaiAddMinCode());
        assertEquals(entityBase.getAllAddress(), entityCopy.getAllAddress());
        assertEquals(SetTableDataHistoryUtil.INSERT_STATE, entityCopy.getIsLatest());
        assertEquals("名称が入力されていません;", entityCopy.getJudgeReason());
    }

}
