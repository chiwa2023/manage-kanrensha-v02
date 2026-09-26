package net.seijishikin.jp.normalize.manage.kanrensha.service.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.contact.AddContactMessageCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.ContactManagerEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.ContactManagerRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * AddContactManagerService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("AddContactManagerServiceTest.sql")
class AddContactManagerServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private AddContactManagerService addContactManagerService;

    /** 運営者連絡Repository */
    @Autowired
    private ContactManagerRepository contactManagerRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddContactMessageCapsuleDto capsuleDto = new AddContactMessageCapsuleDto();
        capsuleDto.setContactManagerCode(26);
        capsuleDto.setIsColsed(true);
        capsuleDto.setInquireContent("解決しました。ありがとうございます");
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        Integer savedId = addContactManagerService.practice(capsuleDto).getContactManagerId();

        ContactManagerEntity entity = contactManagerRepository.findById(savedId).get();

        final Integer callId = 325;
        ContactManagerEntity callEntity = contactManagerRepository.findById(callId).get();

        // 初期情報は変更前最新と変化がない
        assertEquals(callEntity.getContactManagerCode(), entity.getContactManagerCode());
        assertEquals(callEntity.getFirstTimestamp(), entity.getFirstTimestamp());
        assertEquals(callEntity.getInquireUserId(), entity.getInquireUserId());
        assertEquals(callEntity.getInquireUserCode(), entity.getInquireUserCode());
        assertEquals(callEntity.getInquireUserName(), entity.getInquireUserName());
        assertEquals(callEntity.getInquireTitle(), entity.getInquireTitle());

        // 追加情報
        assertEquals(capsuleDto.getInquireContent(), entity.getInquireContent());
        assertEquals(capsuleDto.getIsColsed(), entity.getIsClosed());
        // 問い合わせを閉じたので最終時間が入っている
        assertNotEquals(DtoEntityInitialValueInterface.INIT_TIMESTAMP, entity.getFirstTimestamp());

    }

}
