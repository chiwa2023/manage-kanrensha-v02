package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_combine_org;

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_combine.UpdateWkTblCombineOrgCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaCombineOrgRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RegistCombineOrganizationService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("RegistCombineOrganizationServiceTest.sql")
class RegistCombineOrganizationServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistCombineOrganizationService registCombineOrganizationService;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblKanrenshaCombineOrgRepository wkTblKanrenshaCombineOrgRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 存在しないデータを呼び出すと0が戻る
        WkTblKanrenshaCombineOrgEntity entityInput00 = new WkTblKanrenshaCombineOrgEntity();
        entityInput00.setWkTblKanrenshaCombineOrgId(839);
        UpdateWkTblCombineOrgCapsuleDto capsuleDto00 = new UpdateWkTblCombineOrgCapsuleDto();
        capsuleDto00.setWkTblKanrenshaCombineOrgEntity(entityInput00);
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto00.setUserDto(userDto);
        assertEquals(0, registCombineOrganizationService.practice(capsuleDto00).getWkTblKanrenshaCombineOrgId());

        final Integer callId = 211;

        // 編集内容が追加され、元データが履歴になっている
        UpdateWkTblCombineOrgCapsuleDto capsuleDto01 = new UpdateWkTblCombineOrgCapsuleDto();
        capsuleDto01.setUserDto(userDto);
        WkTblKanrenshaCombineOrgEntity entityInput01 = wkTblKanrenshaCombineOrgRepository.findById(callId).get();
        WkTblKanrenshaCombineOrgEntity entityBase = new WkTblKanrenshaCombineOrgEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setOrgName("超元素製造組合");
        entityBase.setYearArrayText("2024");
        entityBase.setStartYear(Short.valueOf("2024"));
        entityBase.setEndYear(Short.valueOf("2023"));
        capsuleDto01.setWkTblKanrenshaCombineOrgEntity(entityBase);

        Integer newId = registCombineOrganizationService.practice(capsuleDto01).getWkTblKanrenshaCombineOrgId();
        assertNotEquals(0, newId);
        WkTblKanrenshaCombineOrgEntity entityInput02 = wkTblKanrenshaCombineOrgRepository.findById(callId).get();
        assertEquals(SetTableDataHistoryUtil.DELETE_STATE, entityInput02.getIsLatest());
        WkTblKanrenshaCombineOrgEntity entityCopy = wkTblKanrenshaCombineOrgRepository.findById(newId).get();
        assertEquals(entityBase.getWkTblKanrenshaCombineOrgCode(), entityCopy.getWkTblKanrenshaCombineOrgCode());
        assertEquals(entityBase.getOrgName(), entityCopy.getOrgName());
        assertEquals(entityBase.getYearArrayText(), entityCopy.getYearArrayText());
        assertEquals(SetTableDataHistoryUtil.INSERT_STATE, entityCopy.getIsLatest());
        // TODO 画面からの挙動を確認後確定
        assertEquals("終了年より開始年が大きい値です;", entityCopy.getJudgeReason()); // 編集内容に対してチェックが効いています
    }

}
