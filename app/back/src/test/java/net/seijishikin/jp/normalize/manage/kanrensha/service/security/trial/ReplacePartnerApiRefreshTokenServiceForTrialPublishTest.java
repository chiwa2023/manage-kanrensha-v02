package net.seijishikin.jp.normalize.manage.kanrensha.service.security.trial;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.PartnerApiTokenCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.PartnerApiTokenResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.security.ReplacePartnerApiRefreshTokenService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.WriteLogService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * ReplacePartnerApiRefreshTokenService長期トークン発行試行
 * 
 * <p>
 * 共通ツール開発などで試行用長期トークンが必要な場合に発行する<br>
 * DBの向き先はテスト専用から本番用に切り替える<br>
 * </p>
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
// 発行処理のため、SQLとトランザクションはない
class ReplacePartnerApiRefreshTokenServiceForTrialPublishTest {

    /** テスト対象 */
    @Autowired
    private ReplacePartnerApiRefreshTokenService replacePartnerApiRefreshTokenService;

    /** ログ書き出しService */
    @Autowired
    private WriteLogService writeLogService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        PartnerApiTokenCapsuleDto capsuleDto = new PartnerApiTokenCapsuleDto();
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        userDto.setUserPersonName("taro_kanrinin@politician.balanse.report.net");
        capsuleDto.setUserDto(userDto);
        // 長期トークンはIPアドレスのチェックもしているので正確な値を入れる
        capsuleDto.setIpAddress("127.0.0.1"); // NOPMD

        // 本日発効日にして6か月間
        LocalDateTime createTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);

        PartnerApiTokenResultDto resultDto = replacePartnerApiRefreshTokenService.practice(capsuleDto, createTime);
        assertFalse(resultDto.getIsFailure()); // 失敗していない
        // ログに出力されたトークンを使用
        writeLogService.writeInfo(resultDto.getToken());
    }

}
