package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.NotifyPartnerApiLimitCapsuleDto;

/**
 * APIトークン期限切れ通知非同期Service
 */
@Service
public class NotificationPartnerApiTokenLimitAsyncService {

    /** APIトークン期限切れ通知Service */
    @Autowired
    private NotificationPartnerApiTokenLimitService notificationPartnerApiTokenLimitService;

    /**
     * 処理を行う
     * 
     * @param createDatetime 実行日時
     * @param capsuleDto     ユーザDto
     */
    @Async
    public void practice(final LocalDateTime createDatetime, final NotifyPartnerApiLimitCapsuleDto capsuleDto) {

        // 非同期でラップしているだけ(非同期のままだとテストができないので)
        notificationPartnerApiTokenLimitService.practice(createDatetime, capsuleDto);
    }

}
