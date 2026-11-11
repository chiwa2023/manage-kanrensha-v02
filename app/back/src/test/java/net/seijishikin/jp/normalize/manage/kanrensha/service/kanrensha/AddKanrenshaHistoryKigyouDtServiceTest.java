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

import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.KanrenshaTableHistoryKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.AddHistoryKanrenshaKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory01Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory01Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * AddKanrenshaHistoryKigyouDtService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("AddKanrenshaHistoryKigyouDtServiceTest.sql")
class AddKanrenshaHistoryKigyouDtServiceTest {

    /** テスト対象 */
    @Autowired
    private AddKanrenshaHistoryKigyouDtService addKanrenshaHistoryKigyouDtService;

    /** 関連者企業・団体履歴(01)Repository */
    @Autowired
    private KanrenshaKigyouDtHistory01Repository kanrenshaKigyouDtHistory01Repository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddHistoryKanrenshaKigyouDtCapsuleDto capsuleDto = new AddHistoryKanrenshaKigyouDtCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        KanrenshaTableHistoryKigyouDtDto editDto = new KanrenshaTableHistoryKigyouDtDto(0, "123-4567", "いいかげん企業A",
                "北海道架空市", "代表者　花子", "9999-88");
        capsuleDto.setEditDto(editDto);

        Integer savedId = addKanrenshaHistoryKigyouDtService.practice(capsuleDto);

        KanrenshaKigyouDtHistory01Entity entityAns = kanrenshaKigyouDtHistory01Repository.findById(savedId).get();
        assertEquals(editDto.getKigyouDtKanrenshaCode(), entityAns.getKigyouDtKanrenshaCode());
        assertEquals(editDto.getAllName(), entityAns.getAllName());
        assertEquals(editDto.getAllAddress(), entityAns.getAllAddress());
        assertEquals(editDto.getOrgDelegateCode(), entityAns.getOrgDelegateCode());
        assertEquals(editDto.getOrgDelegateName(), entityAns.getOrgDelegateName());

        // 重複データを許可しない
        assertThrows(DataIntegrityViolationException.class, () -> addKanrenshaHistoryKigyouDtService.practice(capsuleDto));
    }

}
