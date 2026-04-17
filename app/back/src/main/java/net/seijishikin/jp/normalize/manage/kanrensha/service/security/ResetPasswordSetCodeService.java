package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.ResetPassswordCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPasswordResetEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPasswordResetRepository;

/**
 * パスワードリセット認証コード確認Dto
 */
@Service
public class ResetPasswordSetCodeService {

    /** パスワードリセットRepository */
    @Autowired
    private UserPasswordResetRepository userPasswordResetRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto パスワードリセット処理条件Dto
     * @return 処理結果
     */
    public FrameworkMessageAndResultDto practice(final ResetPassswordCapsuleDto capsuleDto) {

        Optional<UserPasswordResetEntity> optional = userPasswordResetRepository.findById(capsuleDto.getEmail());
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        if (optional.isEmpty()) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("パスワードリセットのためのコード送信が行われていません");
            return resultDto;
        }

        UserPasswordResetEntity entity = optional.get();

        if (!capsuleDto.getAccessCode().equals(entity.getRegistCode())) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("入力されたコードが異なります");
            return resultDto;
        }

        if (LocalDateTime.now().isAfter(entity.getLimitDatetime())) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("コードの有効期間を過ぎています");
            return resultDto;
        }

        resultDto.setMessage("認証コードが確認できました。");

        return resultDto;
    }
}
