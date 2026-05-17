package net.seijishikin.jp.normalize.manage.kanrensha.service.yotei;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.yotei.EditTimerYoteiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TimerYoteiEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.TimerYoteiRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * DeleteTimerYoteiService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("DeleteTimerYoteiServiceTest.sql")
class DeleteTimerYoteiServiceTest {

    /** テスト対象 */
    @Autowired
    private DeleteTimerYoteiService deleteTimerYoteiService;

    /** 予約実行Repository */
    @Autowired
    private TimerYoteiRepository timerYoteiRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        final Long count = timerYoteiRepository.count();

        // 引数が空の場合はNullPointer
        assertThrows(NullPointerException.class, () -> deleteTimerYoteiService.practice(null));

        // 指定したIdが存在しない
        EditTimerYoteiCapsuleDto capsuleDto0 = new EditTimerYoteiCapsuleDto();
        assertThrows(EmptyResultDataAccessException.class, () -> deleteTimerYoteiService.practice(capsuleDto0));

        EditTimerYoteiCapsuleDto capsuleDto1 = new EditTimerYoteiCapsuleDto();
        final Integer callId = 123;
        TimerYoteiEntity deleteEntity = timerYoteiRepository.findById(callId).get();
        capsuleDto1.setTimerYoteiEntity(deleteEntity);
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        Integer deleteId = deleteTimerYoteiService.practice(capsuleDto1);

        TimerYoteiEntity entityAns = timerYoteiRepository.findById(deleteId).get();

        assertEquals(callId, deleteId);
        assertFalse(entityAns.getIsLatest());

        assertEquals(count, timerYoteiRepository.count()); // 前後で行数の追加なし
    }

}
