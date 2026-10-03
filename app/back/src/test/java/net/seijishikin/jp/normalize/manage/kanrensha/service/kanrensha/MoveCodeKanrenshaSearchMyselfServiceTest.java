package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaCodeMoveEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * MoveCodeKanrenshaSearchMyselfService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("MoveCodeKanrenshaSearchMyselfServiceTest.sql")
class MoveCodeKanrenshaSearchMyselfServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private MoveCodeKanrenshaSearchMyselfService moveCodeKanrenshaSearchMyselfService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        userDto.setKanrenshaCode("12345");
        List<KanrenshaCodeMoveEntity> list = moveCodeKanrenshaSearchMyselfService.practice(userDto.getKanrenshaCode());
        assertEquals(3, list.size());

        KanrenshaCodeMoveEntity entity0 = list.get(0);
        assertEquals(243, entity0.getKanrenshaCodeMoveId());
        KanrenshaCodeMoveEntity entity1 = list.get(1);
        assertEquals(245, entity1.getKanrenshaCodeMoveId());
        KanrenshaCodeMoveEntity entity2 = list.get(2);
        assertEquals(247, entity2.getKanrenshaCodeMoveId());
    }

}
