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

import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.KanrenshaTableHistoryPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.AddHistoryKanrenshaPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory01Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory01Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * AddKanrenshaHistoryPersonService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("AddKanrenshaHistoryPersonServiceTest.sql")
class AddKanrenshaHistoryPersonServiceTest {

    /** テスト対象 */
    @Autowired
    private AddKanrenshaHistoryPersonService addKanrenshaHistoryPersonService;

    /** 関連者個人履歴(01)Repository */
    @Autowired
    private KanrenshaPersonHistory01Repository kanrenshaPersonHistory01Repository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddHistoryKanrenshaPersonCapsuleDto capsuleDto = new AddHistoryKanrenshaPersonCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        KanrenshaTableHistoryPersonDto editDto = new KanrenshaTableHistoryPersonDto(0, "111-222", "個人　太郎", "北海道架空市",
                "正義の味方");
        capsuleDto.setEditDto(editDto);

        Integer savedId = addKanrenshaHistoryPersonService.practice(capsuleDto);

        KanrenshaPersonHistory01Entity entityAns = kanrenshaPersonHistory01Repository.findById(savedId).get();
        assertEquals(editDto.getPersonKanrenshaCode(), entityAns.getPersonKanrenshaCode());
        assertEquals(editDto.getAllName(), entityAns.getAllName());
        assertEquals(editDto.getAllAddress(), entityAns.getAllAddress());
        assertEquals(editDto.getPersonShokugyou(), entityAns.getPersonShokugyou());

        // 重複データを許可しない
        assertThrows(DataIntegrityViolationException.class,
                () -> addKanrenshaHistoryPersonService.practice(capsuleDto));
    }

}
