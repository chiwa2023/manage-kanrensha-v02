package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.DeleteUserCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.LoginStatusEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.LoginStatusRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.security.CustomUserDetailsManager;

/**
 * ユーザ削除Service
 */
@Service
public class DeleteUserService {

    /** ユーザ詳細Manager */
    @Autowired
    private CustomUserDetailsManager customUserDetailsManager;

    /** ユーザ人物Repository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /** ログイン情報Repository */
    @Autowired
    private LoginStatusRepository loginStatusRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 退会ユーザ最小限Dto
     * @return 処理結果
     */
    public FrameworkMessageAndResultDto practice(final DeleteUserCapsuleDto capsuleDto) {

        Optional<UserPersonEntity> optionalPerson = userPersonRepository
                .findById(capsuleDto.getUserDto().getUserPersonId());

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        if (optionalPerson.isEmpty()) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("指定されたユーザが存在しません");
            return resultDto;
        }

        String mail = optionalPerson.get().getEmail();

        try {

            Optional<LoginStatusEntity> optionalLogin = loginStatusRepository.findById(mail);
            if (optionalLogin.isPresent()) {
                LoginStatusEntity statusEntity = optionalLogin.get();

                // ばかばかしいとは思ううが、退会理由と退会処理は@Overrideの都合上、分離しているため分離して処理
                statusEntity.setDisabledReason(capsuleDto.getWithdrawReason());
                loginStatusRepository.save(statusEntity);
            }

            // 削除処理
            customUserDetailsManager.deleteUser(mail);
            
            resultDto.setMessage("退会処理が完了しました。ご利用をいただきましてありがとうございました");
            return resultDto;
            
        } catch (IllegalStateException | UsernameNotFoundException e) {
            resultDto.setIsFailure(true);
            resultDto.setMessage(e.getMessage());
            return resultDto;
        }
    }

}
