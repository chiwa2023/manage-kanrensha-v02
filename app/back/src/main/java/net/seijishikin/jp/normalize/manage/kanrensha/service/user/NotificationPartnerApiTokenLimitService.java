package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.NotifyPartnerApiLimitCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessTokenEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.PartnerAccessTokenRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.task_plan.InsertTaskPlanOtherPersonService;

/**
 * APIトークン期限切れ通知Service
 */
@Service
public class NotificationPartnerApiTokenLimitService {

    /** APIパートナートークンRepository */
    @Autowired
    private PartnerAccessTokenRepository partnerAccessTokenRepository;

    /** ユーザ個人Repository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /** 他者タスク追加Service */
    @Autowired
    private InsertTaskPlanOtherPersonService insertTaskPlanOtherPersonService;

    /** 通知間隔1日前 */
    private static final int DATE1 = 1;
    /** 通知間隔2日前 */
    private static final int DATE2 = 2;
    /** 通知間隔7日前 */
    private static final int DATE7 = 7;
    /** 通知間隔17日前 */
    private static final int DATE17 = 17;

    /**
     * 処理を行う
     * 
     * @param createDatetime 作成日時
     * @param capsuleDto     実行ユーザ最小限Dto
     */
    public void practice(final LocalDateTime createDatetime, final NotifyPartnerApiLimitCapsuleDto capsuleDto) {

        // ある1か月にトークン更新が必要なAPIパートナー(政治資金関連機能提供者)が
        // 1000人とか考えづらいので(1000人いる場合は概算で6000人近いAPIユーザがいる計算)
        // とりあえず非同期にしておけば、バッチにまでする必要がない認識

        LocalDate checkDate = capsuleDto.getCheckDate();

        // 1日前
        LocalDate date1 = checkDate.plusDays(DATE1);
        this.insertTask(capsuleDto, createDatetime, LocalDateTime.of(date1, LocalTime.MIN),
                LocalDateTime.of(date1, LocalTime.MAX));

        // 2日前
        LocalDate date2 = checkDate.plusDays(DATE2);
        this.insertTask(capsuleDto, createDatetime, LocalDateTime.of(date2, LocalTime.MIN),
                LocalDateTime.of(date2, LocalTime.MAX));

        // 7日前
        LocalDate date7 = checkDate.plusDays(DATE7);
        this.insertTask(capsuleDto, createDatetime, LocalDateTime.of(date7, LocalTime.MIN),
                LocalDateTime.of(date7, LocalTime.MAX));

        // 17日前
        LocalDate date17 = checkDate.plusDays(DATE17);
        this.insertTask(capsuleDto, createDatetime, LocalDateTime.of(date17, LocalTime.MIN),
                LocalDateTime.of(date17, LocalTime.MAX));
    }

    private LeastUserDto createUserDto(final UserPersonEntity entity) {
        LeastUserDto userDto = new LeastUserDto();

        userDto.setUserPersonId(entity.getUserPersonId());
        userDto.setUserPersonCode(entity.getUserPersonCode());
        userDto.setUserPersonName(entity.getUserPersonName());

        return userDto;
    }

    private void insertTask(final FrameworkCapsuleDto capsuleDto, final LocalDateTime createDatetime,
            final LocalDateTime startDatetime, final LocalDateTime endDatetime) {

        List<PartnerAccessTokenEntity> list = partnerAccessTokenRepository.findByExpiresAtBetween(startDatetime,
                endDatetime);

        Map<String, String> map = new TreeMap<>();

        for (PartnerAccessTokenEntity entity : list) {

            Optional<UserPersonEntity> optional = userPersonRepository
                    .findFirstByUserPersonCodeAndIsLatestTrueOrderByInsertTimestampDesc(entity.getUserCode());

            // 何らかの原因で最新が存在しないユーザには通知しないでOK
            if (!optional.isEmpty()) {
                UserPersonEntity personEntiy = optional.get();

                insertTaskPlanOtherPersonService.practice(personEntiy.getEmail(), this.createUserDto(personEntiy),
                        capsuleDto.getUserDto(), createDatetime, TaskInfoConstants.NOTIFICATE_TOKEN_LIMIT, map);
            }
        }

    }

}
