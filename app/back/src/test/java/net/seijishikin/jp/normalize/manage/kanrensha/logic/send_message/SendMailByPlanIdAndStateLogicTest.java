package net.seijishikin.jp.normalize.manage.kanrensha.logic.send_message;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.NoResultException;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SendMailByPlanIdAndStateLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SendMailByPlanIdAndStateLogicTest.sql")
class SendMailByPlanIdAndStateLogicTest {
    // CHECKSTYLE:OFF MagicNumbber

    /** テスト対象 */
    @Autowired
    private SendMailByPlanIdAndStateLogic sendMailByPlanIdAndStateLogic;

    @Test
    @Tag("ExternalService")
    void test() throws Exception {

        // 存在しないユーザの場合はEmptyResultDataAccessException
        assertThrows(EmptyResultDataAccessException.class, () -> sendMailByPlanIdAndStateLogic
                .practice(new LeastUserDto(), 2026, 453, SendMailByPlanIdAndStateLogic.STATE_START));

        LeastUserDto userDtoNoAlert = new LeastUserDto();
        userDtoNoAlert.setUserPersonId(197);
        // ユーザが通知は要らないと言っている場合は何もしない(送信通数を数えて送信されていないことを確認)
        assertDoesNotThrow(() -> sendMailByPlanIdAndStateLogic.practice(userDtoNoAlert, 2026, 453,
                SendMailByPlanIdAndStateLogic.STATE_START));
        assertDoesNotThrow(() -> sendMailByPlanIdAndStateLogic.practice(userDtoNoAlert, 2026, 453,
                SendMailByPlanIdAndStateLogic.STATE_END));
        assertDoesNotThrow(() -> sendMailByPlanIdAndStateLogic.practice(userDtoNoAlert, 2026, 453,
                SendMailByPlanIdAndStateLogic.STATE_SUSPEND));

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        // タスク計画が存在しない場合はNoResultException
        assertThrows(NoResultException.class, () -> sendMailByPlanIdAndStateLogic.practice(userDto, 2025, 453,
                SendMailByPlanIdAndStateLogic.STATE_END));

        // タスク情報が存在しない場合はNoResultException
        assertThrows(NoResultException.class, () -> sendMailByPlanIdAndStateLogic.practice(userDto, 2026, 454,
                SendMailByPlanIdAndStateLogic.STATE_END));

        // あまり見ない特殊なBatchStatusが返ったとき、または変な送信状態を指定(実装ミス)したときは
        assertThrows(IllegalArgumentException.class, () -> sendMailByPlanIdAndStateLogic.practice(userDto, 2026, 453,
                4925));

        // 正常に送信(目視で確認)
        assertDoesNotThrow(() -> sendMailByPlanIdAndStateLogic.practice(userDto, 2026, 453,
                SendMailByPlanIdAndStateLogic.STATE_START));
        assertDoesNotThrow(() -> sendMailByPlanIdAndStateLogic.practice(userDto, 2026, 453,
                SendMailByPlanIdAndStateLogic.STATE_END));
        assertDoesNotThrow(() -> sendMailByPlanIdAndStateLogic.practice(userDto, 2026, 453,
                SendMailByPlanIdAndStateLogic.STATE_SUSPEND));
    }

}
