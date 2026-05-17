package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.RefreshPasswordCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.security.CustomUserDetailsManager;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * パスワード更新サービス
 */
@Service
public class RefreshPasswordService {

    /** ユーザ詳細Manager */
    @Autowired
    private CustomUserDetailsManager customUserDetailsManager;

    /** ユーザ詳細Manager */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto パスワード更新条件Dto
     * @return 処理結果
     */
    public FrameworkMessageAndResultDto practice(final RefreshPasswordCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        String oldPassword = capsuleDto.getOldPassword();

        // この段階ではログインできる正しいパスワードとの比較
        if (oldPassword.equals(capsuleDto.getNewPassword())) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("変更前と変更後のパスワードが同じです");
            return resultDto;
        }

        String newPassword = passwordEncoder.encode(capsuleDto.getNewPassword());

        // パスワード更新(oldPassowrdはやっぱり使用できなかった)
        try {
            customUserDetailsManager.changePassword(oldPassword, newPassword);
        } catch (Exception exception) { // NOPMD AvoidGenricException
            saveStackTraceService.practice(exception, LocalDate.now().getYear(), 0);
            // パスワード切り替え失敗時
            resultDto.setIsFailure(true);
            resultDto.setMessage("パスワード更新に失敗しました");
            return resultDto;
        }

        // パスワード切り替え成功時
        resultDto.setMessage("パスワード更新に成功しました");

        return resultDto;
    }

}
