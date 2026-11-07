package net.seijishikin.jp.normalize.manage.kanrensha.service.year.y2024;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2024.KanrenshaCombineOrg2024Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2024.KanrenshaCombineOrg2024Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearUpdateCombineOrgService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SwitchYearUpdateCombineOrgService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SwitchYearUpdateCombineOrgY2024ServiceTest.sql")
class SwitchYearUpdateCombineOrgY2024ServiceTest {

    /** テスト対象 */
    @Autowired
    private SwitchYearUpdateCombineOrgService switchYearUpdateCombineOrgService;

    /** 個人団体紐づけRepository(2024) */
    @Autowired
    private KanrenshaCombineOrg2024Repository kanrenshaCombineOrg2024Repository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        userDto.getListRoles().add(UserRoleConstants.MANAGER);

        WkTblKanrenshaCombineOrgEntity entity = new WkTblKanrenshaCombineOrgEntity();
        entity.setYearArrayText("2024"); // 2024年のみ
        entity.setPersonKanrenshaCode("111-2222");
        entity.setPersonName("迂回献金　太郎");
        entity.setOrgKanrenshaCode("3333-4");
        entity.setOrgName("超元素製造組合");
        entity.setKanrenshaKbn(KanrenshaKbnConstants.KIGYOU_DT);

        // 新規登録
        Integer updateId = switchYearUpdateCombineOrgService.practice(entity, userDto);

        KanrenshaCombineOrg2024Entity entityAns = kanrenshaCombineOrg2024Repository.findById(updateId).get();
        assertTrue(entityAns.getIsLatest());
        assertEquals(entity.getKanrenshaKbn(), entityAns.getKanrenshaKbn());
        assertEquals(entity.getPersonKanrenshaCode(), entityAns.getPersonKanrenshaCode());
        assertEquals(entity.getPersonName(), entityAns.getPersonName());
        assertEquals(entity.getOrgKanrenshaCode(), entityAns.getOrgKanrenshaCode());
        assertEquals(entity.getOrgName(), entityAns.getOrgName());
    }

}
