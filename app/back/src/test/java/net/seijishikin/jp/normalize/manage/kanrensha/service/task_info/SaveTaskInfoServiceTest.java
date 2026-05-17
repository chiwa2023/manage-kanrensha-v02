package net.seijishikin.jp.normalize.manage.kanrensha.service.task_info;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.task_info.EditTaskInfoCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskInfoEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.TaskInfoRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SaveTaskInfoService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SaveTaskInfoServiceTest.sql")
@Transactional
class SaveTaskInfoServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SaveTaskInfoService saveTaskInfoService;

    /** タスク情報Repository */
    @Autowired
    private TaskInfoRepository taskInfoRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        final Integer callId = 424;
        TaskInfoEntity entity = taskInfoRepository.findById(callId).get();
        TaskInfoEntity entityUpdate = new TaskInfoEntity();
        BeanUtils.copyProperties(entity, entityUpdate);

        // 画面上はこんなすべての項目は変更しない
        entityUpdate.setMessageFinish("終了メール内容");
        entityUpdate.setMessageStart("開始メール内容");
        entityUpdate.setMessageSuspend("中断メール内容");
        entityUpdate.setParamQuery("123");
        entityUpdate.setRoleList("admin");
        entityUpdate.setTaskInfoName("タスク情報");
        entityUpdate.setTransferPass("http:url");

        EditTaskInfoCapsuleDto capsuleDto = new EditTaskInfoCapsuleDto();
        capsuleDto.setTaskInfoEntity(entityUpdate);
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        Integer newId = saveTaskInfoService.practice(capsuleDto);

        TaskInfoEntity entityNew = taskInfoRepository.findById(newId).get();

        assertEquals(entityUpdate.getMessageFinish(), entityNew.getMessageFinish());
        assertEquals(entityUpdate.getMessageStart(), entityNew.getMessageStart());
        assertEquals(entityUpdate.getMessageSuspend(), entityNew.getMessageSuspend());
        assertEquals(entityUpdate.getParamQuery(), entityNew.getParamQuery());
        assertEquals(entityUpdate.getRoleList(), entityNew.getRoleList());
        assertEquals(entityUpdate.getTaskInfoName(), entityNew.getTaskInfoName());
        assertEquals(entityUpdate.getTransferPass(), entityNew.getTransferPass());
        assertEquals(entityUpdate.getTaskInfoCode(), entityNew.getTaskInfoCode());
    }

}
