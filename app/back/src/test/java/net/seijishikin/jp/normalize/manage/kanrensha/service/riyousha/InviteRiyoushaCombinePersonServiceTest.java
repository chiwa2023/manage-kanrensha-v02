package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.RiyoushaCombinePersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgTempEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaCombineOrgTempRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InviteRiyoushaCombinePersonService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("InviteRiyoushaCombinePersonServiceTest.sql")
class InviteRiyoushaCombinePersonServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private InviteRiyoushaCombinePersonService inviteRiyoushaCombinePersonService;

    /** 利用者組織紐づけ仮Repository */
    @Autowired
    private RiyoushaCombineOrgTempRepository riyoushaCombineOrgTempRepository;

    @Test
    @Tag("ExternalService")
    void test() {

        LocalDateTime createDatetime = LocalDateTime.of(2026, 1, 18, 0, 1, 2);

        // メールアドレスが存在しない(入力ミスの)ときは中断
        RiyoushaCombinePersonCapsuleDto capsuleDto0 = new RiyoushaCombinePersonCapsuleDto();
        capsuleDto0.setEmail("ggg@aaa.net");
        FrameworkMessageAndResultDto resultDto0 = inviteRiyoushaCombinePersonService.practice(capsuleDto0,
                createDatetime);
        assertTrue(resultDto0.getIsFailure());

        // 個人コードがメアドと異なる時も中断
        RiyoushaCombinePersonCapsuleDto capsuleDto1 = new RiyoushaCombinePersonCapsuleDto();
        capsuleDto1.setEmail("bbb@politician.balanse.report.net");
        RiyoushaCombineOrgEntity combineEntity1 = new RiyoushaCombineOrgEntity();
        combineEntity1.setPersonCode(1044);
        capsuleDto1.setCombineEntity(combineEntity1);
        FrameworkMessageAndResultDto resultDto1 = inviteRiyoushaCombinePersonService.practice(capsuleDto1,
                createDatetime);
        assertTrue(resultDto1.getIsFailure());

        // 正常に仮テーブルに登録(かつタスク登録もメールも送信できている)
        RiyoushaCombinePersonCapsuleDto capsuleDto2 = new RiyoushaCombinePersonCapsuleDto();
        capsuleDto2.setEmail("bbb@politician.balanse.report.net");
        capsuleDto2.setUserDto(CreateLeastUserForTestUtil.practice());
        // 個人コードと権限はfrontから設定できないのでbackで補充
        //  このテストでは979,parter_api
        RiyoushaCombineOrgEntity combineEntity2 = new RiyoushaCombineOrgEntity();
        combineEntity2.setOrgRiyoushaCode(245);
        combineEntity2.setOrgName("イケてる風システム会社");
        combineEntity2.setPersonRiyoushaCode(979);
        combineEntity2.setPersonRiyoushaName("運営者 太郎");
        capsuleDto2.setCombineEntity(combineEntity2);
        FrameworkMessageAndResultDto resultDto2 = inviteRiyoushaCombinePersonService.practice(capsuleDto2,
                createDatetime);
        assertFalse(resultDto2.getIsFailure()); // 成功

        // 登録したデータを取得
        RiyoushaCombineOrgTempEntity tempEntity = riyoushaCombineOrgTempRepository.findAll().getLast();

        assertEquals(311, tempEntity.getPersonCode());
        assertEquals("partner_api", tempEntity.getRiyoushaRole());
        assertEquals(combineEntity2.getOrgName(), tempEntity.getOrgName());
        assertEquals(combineEntity2.getOrgRiyoushaCode(), tempEntity.getOrgRiyoushaCode());
        assertEquals(combineEntity2.getPersonRiyoushaCode(), tempEntity.getPersonRiyoushaCode());
        assertEquals(combineEntity2.getPersonRiyoushaName(), tempEntity.getPersonRiyoushaName());
    }

}
