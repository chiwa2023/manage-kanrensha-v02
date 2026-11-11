package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.KanrenshaTableHistorySeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.AddHistoryKanrenshaSeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory01Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory01Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * AddKanrenshaHistorySeijidantaiService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("AddKanrenshaHistorySeijidantaiServiceTest.sql")
class AddKanrenshaHistorySeijidantaiServiceTest {

    /** テスト対象 */
    @Autowired
    private AddKanrenshaHistorySeijidantaiService addKanrenshaHistorySeijidantaiService;

    /** 関連者政治団体履歴(01)Repository */
    @Autowired
    private KanrenshaSeijidantaiHistory01Repository kanrenshaSeijidantaiHistory01Repository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddHistoryKanrenshaSeijidantaiCapsuleDto capsuleDto = new AddHistoryKanrenshaSeijidantaiCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        KanrenshaTableHistorySeijidantaiDto editDto = new KanrenshaTableHistorySeijidantaiDto(0, "123-4567",
                "いいかげん政治団体A", "北海道架空市", "代表者　花子", "9999-88");
        capsuleDto.setEditDto(editDto);

        Integer savedId = addKanrenshaHistorySeijidantaiService.practice(capsuleDto);

        KanrenshaSeijidantaiHistory01Entity entityAns = kanrenshaSeijidantaiHistory01Repository.findById(savedId).get();
        assertEquals(editDto.getSeijidantaiKanrenshaCode(), entityAns.getSeijidantaiKanrenshaCode());
        assertEquals(editDto.getAllName(), entityAns.getAllName());
        assertEquals(editDto.getAllAddress(), entityAns.getAllAddress());
        assertEquals(editDto.getOrgDelegateCode(), entityAns.getOrgDelegateCode());
        assertEquals(editDto.getOrgDelegateName(), entityAns.getOrgDelegateName());

        // 重複データを許可しない
        assertThrows(DataIntegrityViolationException.class,
                () -> addKanrenshaHistorySeijidantaiService.practice(capsuleDto));
    }

}
