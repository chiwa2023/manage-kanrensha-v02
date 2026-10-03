package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.NotifyPartnerApiLimitCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2025.TaskPlan2025Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.TaskPlan2025Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * NotificationPartnerApiTokenLimitService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("NotificationPartnerApiTokenLimitServiceTest.sql")
class NotificationPartnerApiTokenLimitServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private NotificationPartnerApiTokenLimitService notificationPartnerApiTokenLimitService;

    /** テスト対象 */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

    @Test
    @Tag("ExternalService")
    void test() {

        LocalDateTime creaDateTime = LocalDateTime.of(2025, 8, 1, 11, 22, 33);
        NotifyPartnerApiLimitCapsuleDto capsuleDto = new NotifyPartnerApiLimitCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setCheckDate(LocalDate.of(2025, 7, 1));

        notificationPartnerApiTokenLimitService.practice(creaDateTime, capsuleDto);

        // タスクが登録される
        List<TaskPlan2025Entity> listTask = taskPlan2025Repository.findAll();
        // assertEquals(4, listTask.size());

        TaskPlan2025Entity taskEntity0 = listTask.get(0);
        assertEquals(80, taskEntity0.getTaskUserCode());

        TaskPlan2025Entity taskEntity1 = listTask.get(1);
        assertEquals(83, taskEntity1.getTaskUserCode());

        TaskPlan2025Entity taskEntity2 = listTask.get(2);
        assertEquals(194, taskEntity2.getTaskUserCode());

        TaskPlan2025Entity taskEntity3 = listTask.get(3);
        assertEquals(195, taskEntity3.getTaskUserCode());
    }

}
