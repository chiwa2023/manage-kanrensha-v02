package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_history;

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_history.UpdateWkTblHistoryKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtHistoryRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RegistBulkHistoryKigyouDtService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("RegistBulkHistoryKigyouDtServiceTest.sql")
class RegistBulkHistoryKigyouDtServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistBulkHistoryKigyouDtService registBulkHistoryKigyouDtService;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblKanrenshaKigyouDtHistoryRepository wkTblKanrenshaKigyouDtHistoryRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 存在しないデータを呼び出すと0が戻る
        WkTblKanrenshaKigyouDtHistoryEntity entityInput00 = new WkTblKanrenshaKigyouDtHistoryEntity();
        entityInput00.setWkKanrenshaKigyouDtHistoryId(839);
        UpdateWkTblHistoryKigyouDtCapsuleDto capsuleDto00 = new UpdateWkTblHistoryKigyouDtCapsuleDto();
        capsuleDto00.setWkTblKanrenshaKigyouDtHistoryEntity(entityInput00);
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto00.setUserDto(userDto);
        assertEquals(0, registBulkHistoryKigyouDtService.practice(capsuleDto00).getWkKanrenshaKigyouDtHistoryId());

        final Integer callId = 102;

        // 編集内容が追加され、元データが履歴になっている
        UpdateWkTblHistoryKigyouDtCapsuleDto capsuleDto01 = new UpdateWkTblHistoryKigyouDtCapsuleDto();
        capsuleDto01.setUserDto(userDto);
        WkTblKanrenshaKigyouDtHistoryEntity entityInput01 = wkTblKanrenshaKigyouDtHistoryRepository.findById(callId)
                .get();
        WkTblKanrenshaKigyouDtHistoryEntity entityBase = new WkTblKanrenshaKigyouDtHistoryEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setAllAddress("山ビル2F");
        entityBase.setKanrenshaName("");
        capsuleDto01.setWkTblKanrenshaKigyouDtHistoryEntity(entityBase);

        Integer newId = registBulkHistoryKigyouDtService.practice(capsuleDto01).getWkKanrenshaKigyouDtHistoryId();
        assertNotEquals(0, newId);
        WkTblKanrenshaKigyouDtHistoryEntity entityInput02 = wkTblKanrenshaKigyouDtHistoryRepository.findById(callId)
                .get();
        assertEquals(SetTableDataHistoryUtil.DELETE_STATE, entityInput02.getIsLatest());
        WkTblKanrenshaKigyouDtHistoryEntity entityCopy = wkTblKanrenshaKigyouDtHistoryRepository.findById(newId).get();
        assertEquals(entityBase.getWkKanrenshaKigyouDtHistoryCode(), entityCopy.getWkKanrenshaKigyouDtHistoryCode());
        assertEquals(entityBase.getAllAddress(), entityCopy.getAllAddress());
        assertEquals(SetTableDataHistoryUtil.INSERT_STATE, entityCopy.getIsLatest());
        assertEquals("名称が入力されていません;", entityCopy.getJudgeReason());
    }

}
