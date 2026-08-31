package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.NewComerDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserNewEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserNewRepository;

/**
 * 新規ユーザ用URLトークン検証Service
 */
@Service
public class VerifyNewUserService {

    /** 新規登録中ユーザRepository */
    @Autowired
    private UserNewRepository userNewRepository;

    /**
     * 処理を行う
     *
     * @param newComerDto 新規ユーザDto
     * @return コード入力したDto
     */
    public NewComerDto practice(final NewComerDto newComerDto) {
        String verifyToken = newComerDto.getVerifyToken();
        UserNewEntity userNewEntity = userNewRepository.findByVerifyToken(verifyToken);

        if (userNewEntity == null) {
            newComerDto.setIsFailure(true);
            newComerDto.setMessage("無効なトークンです。");
            return newComerDto;
        }

        LocalDateTime limit = userNewEntity.getVerifyLimitDateTime();
        if (limit == null || limit.isBefore(LocalDateTime.now())) {
            newComerDto.setIsFailure(true);
            newComerDto.setMessage("トークンの有効期限が切れています。");
            return newComerDto;
        }

        // 認証成功
        // トークンを無効化して再利用を防ぐ
        userNewEntity.setVerifyToken(null);
        userNewEntity.setVerifyLimitDateTime(null);
        userNewRepository.save(userNewEntity);

        newComerDto.setIsFailure(false);
        newComerDto.setMailAddress(userNewEntity.getEmail());
        newComerDto.setRegistCode(userNewEntity.getRegistCode()); // 手入力画面への遷移も考慮
        newComerDto.setMessage("認証に成功しました。");

        return newComerDto;
    }

}
