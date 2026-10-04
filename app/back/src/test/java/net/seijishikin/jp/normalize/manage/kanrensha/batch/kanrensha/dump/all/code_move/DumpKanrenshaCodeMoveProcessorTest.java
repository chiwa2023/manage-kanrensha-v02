package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.code_move;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.ShinseiStatusConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaCodeMoveEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * DumpKanrenshaCodeMoveProcessor単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class DumpKanrenshaCodeMoveProcessorTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private DumpKanrenshaCodeMoveProcessor dumpKanrenshaCodeMoveProcessor;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        KanrenshaCodeMoveEntity moveEntity = new KanrenshaCodeMoveEntity();

        moveEntity.setKanrenshaCodeMoveId(431);
        moveEntity.setKanrenshaCodeMoveCode(932);

        moveEntity.setMoveStatus(ShinseiStatusConstants.ACCEPT);
        moveEntity.setAbolishKanrenshaCode("98765");
        moveEntity.setOriginKanrenshaCode("12345");
        moveEntity.setIsAbolishLast(true); // 廃止コードを最新にする

        moveEntity.setKanrenshaKbn(KanrenshaKbnConstants.PERSON);
        moveEntity.setAbolishKanrenshaName("廃止名称");
        moveEntity.setOriginName("残す名称");
        moveEntity.setMoveReason("移動理由");
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        moveEntity.setInsertUserId(userDto.getUserPersonId());
        moveEntity.setInsertUserCode(userDto.getUserPersonCode());
        moveEntity.setInsertUserName(userDto.getUserPersonName());
        moveEntity.setInsertTimestamp(LocalDateTime.of(2024, 9, 13, 11, 22, 33));

        DumpKanrenshaCodeMoveDto dto = dumpKanrenshaCodeMoveProcessor.process(moveEntity);

        // テーブルId
        assertEquals(moveEntity.getKanrenshaCodeMoveId(), dto.getKanrenshaCodeMoveId());

        // 関連者コード移動申請コード
        assertEquals(moveEntity.getKanrenshaCodeMoveCode(), dto.getKanrenshaCodeMoveCode());

        // 最新フラグ
        assertEquals(moveEntity.getIsLatest(), dto.getIsLatest());

        // 承認状態
        assertEquals(moveEntity.getMoveStatus(), dto.getMoveStatus());

        // 関連者区分
        assertEquals(moveEntity.getKanrenshaKbn(), dto.getKanrenshaKbn());

        // 関連者区分名称
        assertEquals("個人", dto.getKanrenshaKbnName()); // 区分が個人を設定した

        // 併合先コード
        assertEquals(moveEntity.getOriginKanrenshaCode(), dto.getOriginKanrenshaCode());

        // 併合先コード名称
        assertEquals(moveEntity.getOriginName(), dto.getOriginName());

        // 廃止コード
        assertEquals(moveEntity.getAbolishKanrenshaCode(), dto.getAbolishKanrenshaCode());

        // 廃止コード名称
        assertEquals(moveEntity.getAbolishKanrenshaName(), dto.getAbolishKanrenshaName());

        // 移行理由
        assertEquals(moveEntity.getMoveReason(), dto.getMoveReason());

        // 廃止コード最新該当
        assertEquals(moveEntity.getIsAbolishLast(), dto.getIsAbolishLast());
        assertEquals("廃止が最新", dto.getSaishinName());

        // 挿入ユーザId
        assertEquals(moveEntity.getInsertUserId(), dto.getInsertUserId());

        // 挿入ユーザコード
        assertEquals(moveEntity.getInsertUserCode(), dto.getInsertUserCode());

        // 挿入ユーザ名称
        assertEquals(moveEntity.getInsertUserName(), dto.getInsertUserName());

        // 挿入日時
        assertEquals(moveEntity.getInsertTimestamp(), dto.getInsertTimestamp());
    }

}
