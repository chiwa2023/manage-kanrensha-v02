package net.seijishikin.jp.normalize.manage.kanrensha.service.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.contact.AddContactMessageCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.ContactManagerEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.ContactManagerRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * FirstContactManagerService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("FirstContactManagerServiceTest.sql")
class FirstContactManagerServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private FirstContactManagerService firstContactManagerService;

    /** 運営者連絡Repository */
    @Autowired
    private ContactManagerRepository contactManagerRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        LocalDateTime dateTime = LocalDateTime.of(2025, 12, 5, 12, 34, 56);
        AddContactMessageCapsuleDto capsuleDto = new AddContactMessageCapsuleDto();
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto.setUserDto(userDto);
        capsuleDto.setInquireTitle("問い合わせタイトル");
        capsuleDto.setInquireContent("問い合わせ内容");

        Integer savedId = firstContactManagerService.practice(dateTime, capsuleDto);

        ContactManagerEntity entity = contactManagerRepository.findById(savedId).get();

        assertEquals(dateTime, entity.getFirstTimestamp());
        assertEquals(userDto.getUserPersonId(), entity.getInquireUserId());
        assertEquals(userDto.getUserPersonCode(), entity.getInquireUserCode());
        assertEquals(userDto.getUserPersonName(), entity.getInquireUserName());
        assertEquals(capsuleDto.getInquireTitle(), entity.getInquireTitle());
        assertEquals(capsuleDto.getInquireContent(), entity.getInquireContent());

        // 終了情報は固定
        assertEquals(false, entity.getIsClosed());
        assertEquals(DtoEntityInitialValueInterface.INIT_TIMESTAMP, entity.getCloseTimestamp());
    }

}
