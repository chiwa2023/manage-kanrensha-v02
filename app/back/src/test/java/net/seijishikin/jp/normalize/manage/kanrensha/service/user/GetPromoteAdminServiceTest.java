package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.GetPromoteAdminResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.PromoteAdminRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * GetPromoteAdminService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("GetPromoteAdminServiceTest.sql")
class GetPromoteAdminServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetPromoteAdminService getPromoteAdminService;

    /** SE権限追加推薦Repository */
    @Autowired
    private PromoteAdminRepository promoteAdminRepository;

    @Test
    void test() {
        // 存在しない場合は失敗を返す
        GetPromoteAdminResultDto resultDto0 = getPromoteAdminService.practice(new LeastUserDto());
        assertTrue(resultDto0.getIsFailure());

        // 取得できた場合は最新を返す
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        GetPromoteAdminResultDto resultDto1 = getPromoteAdminService.practice(userDto);
        assertEquals(158, resultDto1.getPromoteAdminEntity().getPromoteAdminId());
        assertEquals(userDto.getUserPersonCode(), resultDto1.getPromoteAdminEntity().getPromoteUserCode());

        // 内部では2件とっている
        assertEquals(2, promoteAdminRepository
                .findByPromoteUserCodeAndIsLatestTrueOrderByInsertTimestampDesc(userDto.getUserPersonCode()).size());
    }

}
