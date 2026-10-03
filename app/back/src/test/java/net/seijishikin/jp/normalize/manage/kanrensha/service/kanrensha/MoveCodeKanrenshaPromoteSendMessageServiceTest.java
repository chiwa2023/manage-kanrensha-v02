package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodePromoteCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message.SendMaileResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;

/**
 * MoveCodeKanrenshaPromoteSendMessageService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("MoveCodeKanrenshaPromoteSendMessageServiceTest.sql")
@Transactional
class MoveCodeKanrenshaPromoteSendMessageServiceTest {

    /** テスト対象 */
    @Autowired
    private MoveCodeKanrenshaPromoteSendMessageService moveCodeKanrenshaPromoteSendMessageService;

    @Test
    @Tag("TableTruncate")
    void testNoSend() throws Exception {

        MoveKanrenshaCodePromoteCapsuleDto capsuleDto = new MoveKanrenshaCodePromoteCapsuleDto();
        capsuleDto.setKanrenshaKbn(KanrenshaKbnConstants.SEIJIDANTAI);
        capsuleDto.setAbolishKanrenshaCode("12345");
        capsuleDto.setOriginKanrenshaCode("23456");

        InsertTaskPlanResultDto planResultDto = new InsertTaskPlanResultDto();
        planResultDto.setTaskPlanName("関連者コード移動申請");
        planResultDto.setMessageTemplate("メール送信内容");

        // 紐づけがされていないコード同士を移動しようとしているの関係者にメールは飛ばさない
        // 空の送信結果Dtoが戻る
        SendMaileResultDto resultDto = moveCodeKanrenshaPromoteSendMessageService.practice(capsuleDto, planResultDto);
        assertTrue(resultDto.getListFailure().isEmpty());
        assertTrue(resultDto.getListSuccess().isEmpty());
    }

    @Test
    @Tag("TableTruncate")
    void testSend() throws Exception {

        MoveKanrenshaCodePromoteCapsuleDto capsuleDto = new MoveKanrenshaCodePromoteCapsuleDto();
        capsuleDto.setKanrenshaKbn(KanrenshaKbnConstants.PERSON);
        capsuleDto.setAbolishKanrenshaCode("aa-bb-cc");
        capsuleDto.setOriginKanrenshaCode("dd-ee-ff");

        InsertTaskPlanResultDto planResultDto = new InsertTaskPlanResultDto();
        planResultDto.setMessageTemplate("メール送信内容");
        planResultDto.setTaskPlanName("関連者コード移動申請");

        SendMaileResultDto resultDto = moveCodeKanrenshaPromoteSendMessageService.practice(capsuleDto, planResultDto);

        assertTrue(resultDto.getListFailure().isEmpty());
        assertFalse(resultDto.getListSuccess().isEmpty());

        // 2件メールが飛んでいることを目視確認
        // nnnn@politician.balanse.report.netとmmmm@politician.balanse.report.net
    }

}
