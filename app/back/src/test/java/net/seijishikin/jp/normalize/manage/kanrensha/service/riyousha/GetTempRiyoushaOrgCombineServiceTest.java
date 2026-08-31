package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

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
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.GetTempRiyoushaOrgCombineCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.GetTempRiyoushaOrgCombineResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgTempEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * GetTempRiyoushaOrgCombineService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("GetTempRiyoushaOrgCombineServiceTest.sql")
class GetTempRiyoushaOrgCombineServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetTempRiyoushaOrgCombineService getTempRiyoushaOrgCombineService;

    @Test
    @Tag("TableTruncate")
    void test() {

        // パラーメータを指定されているが取得できなかった(タスクリンクを使用している限り基本的に起きない)
        GetTempRiyoushaOrgCombineCapsuleDto capsuleDto0 = new GetTempRiyoushaOrgCombineCapsuleDto();
        capsuleDto0.setPersonCode(2931);
        capsuleDto0.setOrgCode(1527);
        capsuleDto0.setUserRole("kanrensha_kigyou_dt");
        GetTempRiyoushaOrgCombineResultDto resultDto0 = getTempRiyoushaOrgCombineService.practice(capsuleDto0);
        assertTrue(resultDto0.getIsFailure());

        // パラーメータを指定して取得
        GetTempRiyoushaOrgCombineCapsuleDto capsuleDto1 = new GetTempRiyoushaOrgCombineCapsuleDto();
        capsuleDto1.setPersonCode(246);
        capsuleDto1.setOrgCode(383);
        capsuleDto1.setUserRole("manager");
        GetTempRiyoushaOrgCombineResultDto resultDto1 = getTempRiyoushaOrgCombineService.practice(capsuleDto1);
        RiyoushaCombineOrgTempEntity tempEntity1 = resultDto1.getCombineTempEntity();
        assertEquals(731, tempEntity1.getRiyoushaCombineOrgTempId());

        // ユーザIdだけから取得しようとしたが取れなかった(招待されていないのに承諾ページを覗いてみて)
        GetTempRiyoushaOrgCombineCapsuleDto capsuleDto2 = new GetTempRiyoushaOrgCombineCapsuleDto();
        capsuleDto2.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto2.getUserDto().setUserPersonCode(2024);
        GetTempRiyoushaOrgCombineResultDto resultDto2 = getTempRiyoushaOrgCombineService.practice(capsuleDto2);
        assertTrue(resultDto2.getIsFailure());

        // タスクから遷移しなかったが正常に取得できた
        GetTempRiyoushaOrgCombineCapsuleDto capsuleDto3 = new GetTempRiyoushaOrgCombineCapsuleDto();
        capsuleDto3.setUserDto(CreateLeastUserForTestUtil.practice());
        GetTempRiyoushaOrgCombineResultDto resultDto3 = getTempRiyoushaOrgCombineService.practice(capsuleDto3);
        RiyoushaCombineOrgTempEntity tempEntity3 = resultDto3.getCombineTempEntity();
        assertEquals(755, tempEntity3.getRiyoushaCombineOrgTempId());
    }

}
