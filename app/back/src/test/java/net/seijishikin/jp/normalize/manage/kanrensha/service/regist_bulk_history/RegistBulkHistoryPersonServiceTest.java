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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_history.UpdateWkTblHistoryPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonHistoryRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RegistBulkHistoryPersonService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("RegistBulkHistoryPersonServiceTest.sql")
class RegistBulkHistoryPersonServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistBulkHistoryPersonService registBulkHistoryPersonService;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblKanrenshaPersonHistoryRepository wkTblKanrenshaPersonHistoryRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    void test() throws Exception {

        // 存在しないデータを呼び出すと0が戻る
        WkTblKanrenshaPersonHistoryEntity entityInput00 = new WkTblKanrenshaPersonHistoryEntity();
        entityInput00.setWkKanrenshaPersonHistoryId(839);
        UpdateWkTblHistoryPersonCapsuleDto capsuleDto00 = new UpdateWkTblHistoryPersonCapsuleDto();
        capsuleDto00.setWkTblKanrenshaPersonHistoryEntity(entityInput00);
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto00.setUserDto(userDto);
        assertEquals(0, registBulkHistoryPersonService.practice(capsuleDto00).getWkKanrenshaPersonHistoryId());

        final Integer callId = 98;

        // 編集内容が追加され、元データが履歴になっている
        UpdateWkTblHistoryPersonCapsuleDto capsuleDto01 = new UpdateWkTblHistoryPersonCapsuleDto();
        capsuleDto01.setUserDto(userDto);
        WkTblKanrenshaPersonHistoryEntity entityInput01 = wkTblKanrenshaPersonHistoryRepository.findById(callId).get();
        WkTblKanrenshaPersonHistoryEntity entityBase = new WkTblKanrenshaPersonHistoryEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setAllAddress("山ビル2F");
        entityBase.setKanrenshaName("");
        capsuleDto01.setWkTblKanrenshaPersonHistoryEntity(entityBase);

        Integer newId = registBulkHistoryPersonService.practice(capsuleDto01).getWkKanrenshaPersonHistoryId();
        assertNotEquals(0, newId);
        WkTblKanrenshaPersonHistoryEntity entityInput02 = wkTblKanrenshaPersonHistoryRepository.findById(callId).get();
        assertEquals(SetTableDataHistoryUtil.DELETE_STATE, entityInput02.getIsLatest());
        WkTblKanrenshaPersonHistoryEntity entityCopy = wkTblKanrenshaPersonHistoryRepository.findById(newId).get();
        assertEquals(entityBase.getWkKanrenshaPersonHistoryCode(), entityCopy.getWkKanrenshaPersonHistoryCode());
        assertEquals(entityBase.getAllAddress(), entityCopy.getAllAddress());
        assertEquals(SetTableDataHistoryUtil.INSERT_STATE, entityCopy.getIsLatest());
        assertEquals("名称が入力されていません;", entityCopy.getJudgeReason());
    }

}
