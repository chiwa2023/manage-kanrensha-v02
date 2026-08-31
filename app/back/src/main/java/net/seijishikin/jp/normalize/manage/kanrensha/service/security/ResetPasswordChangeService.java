package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.ResetPassswordCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.LoginStatusEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.LoginStatusRepository;

/**
 * パスワードリセット更新Service
 */
@Service
public class ResetPasswordChangeService {

    /** 認識コード確認Service */
    @Autowired
    private ResetPasswordSetCodeService resetPasswordSetCodeService;

    /** ログイン状態Repository */
    @Autowired
    private LoginStatusRepository loginStatusRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto パスワードリセット処理条件Dto
     * @return 処理結果
     */
    public FrameworkMessageAndResultDto practice(final ResetPassswordCapsuleDto capsuleDto) {

        // コード確認はもう一度行う
        FrameworkMessageAndResultDto checkCodeResultDto = resetPasswordSetCodeService.practice(capsuleDto);
        if (checkCodeResultDto.getIsFailure()) {
            return checkCodeResultDto;
        }

        String email = capsuleDto.getEmail();

        // もう一度メールアドレス登録確認を行う(編集Entityを呼ぶついでに)
        Optional<LoginStatusEntity> optional = loginStatusRepository.findById(email);
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        if (optional.isEmpty()) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("ご指定のアドレスでユーザ登録が見つかりませんでした");
            return resultDto;
        }

        LoginStatusEntity statusEntity = optional.get();
        statusEntity.setPassword(capsuleDto.getPassword());
        statusEntity.setPassChangeTime(LocalDateTime.now());
        loginStatusRepository.save(statusEntity);
        resultDto.setMessage("新たなパスワードで更新しました");
        
        return resultDto;
    }

}
