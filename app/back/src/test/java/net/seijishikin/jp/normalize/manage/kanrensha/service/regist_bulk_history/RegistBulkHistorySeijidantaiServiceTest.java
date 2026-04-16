package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_history;

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_history.UpdateWkTblHistorySeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiHistoryRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RegistBulkHistorySeijidantaiService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("RegistBulkHistorySeijidantaiServiceTest.sql")
class RegistBulkHistorySeijidantaiServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistBulkHistorySeijidantaiService registBulkHistorySeijidantaiService;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblKanrenshaSeijidantaiHistoryRepository wkTblKanrenshaSeijidantaiHistoryRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    void test() throws Exception {

        // 存在しないデータを呼び出すと0が戻る
        WkTblKanrenshaSeijidantaiHistoryEntity entityInput00 = new WkTblKanrenshaSeijidantaiHistoryEntity();
        entityInput00.setWkKanrenshaSeijidantaiHistoryId(839);
        UpdateWkTblHistorySeijidantaiCapsuleDto capsuleDto00 = new UpdateWkTblHistorySeijidantaiCapsuleDto();
        capsuleDto00.setWkTblKanrenshaSeijidantaiHistoryEntity(entityInput00);
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto00.setUserDto(userDto);
        assertEquals(0,
                registBulkHistorySeijidantaiService.practice(capsuleDto00).getWkKanrenshaSeijidantaiHistoryId());

        final Integer callId = 313;

        // 編集内容が追加され、元データが履歴になっている
        UpdateWkTblHistorySeijidantaiCapsuleDto capsuleDto01 = new UpdateWkTblHistorySeijidantaiCapsuleDto();
        capsuleDto01.setUserDto(userDto);
        WkTblKanrenshaSeijidantaiHistoryEntity entityInput01 = wkTblKanrenshaSeijidantaiHistoryRepository
                .findById(callId).get();
        WkTblKanrenshaSeijidantaiHistoryEntity entityBase = new WkTblKanrenshaSeijidantaiHistoryEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setAllAddress("山ビル2F");
        entityBase.setKanrenshaName("");
        capsuleDto01.setWkTblKanrenshaSeijidantaiHistoryEntity(entityBase);

        Integer newId = registBulkHistorySeijidantaiService.practice(capsuleDto01).getWkKanrenshaSeijidantaiHistoryId();
        assertNotEquals(0, newId);
        WkTblKanrenshaSeijidantaiHistoryEntity entityInput02 = wkTblKanrenshaSeijidantaiHistoryRepository
                .findById(callId).get();
        assertEquals(SetTableDataHistoryUtil.DELETE_STATE, entityInput02.getIsLatest());
        WkTblKanrenshaSeijidantaiHistoryEntity entityCopy = wkTblKanrenshaSeijidantaiHistoryRepository.findById(newId)
                .get();
        assertEquals(entityBase.getWkKanrenshaSeijidantaiHistoryCode(),
                entityCopy.getWkKanrenshaSeijidantaiHistoryCode());
        assertEquals(entityBase.getAllAddress(), entityCopy.getAllAddress());
        assertEquals(SetTableDataHistoryUtil.INSERT_STATE, entityCopy.getIsLatest());
        assertEquals("名称が入力されていません;", entityCopy.getJudgeReason());
    }

}
