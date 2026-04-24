package net.seijishikin.jp.normalize.manage.kanrensha.service.task_info;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task_info.EditTaskInfoCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskInfoEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.TaskInfoRepository;

/**
 * タスク情報更新Service
 */
@Service
public class SaveTaskInfoService {

    /** タスク情報Repository */
    @Autowired
    private TaskInfoRepository taskInfoRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param capsuleDto タスク上編集Dto
     * @return 処理結果Dto
     */
    @Transactional
    public Integer practice(final EditTaskInfoCapsuleDto capsuleDto) {

        Optional<TaskInfoEntity> optional = taskInfoRepository.findById(capsuleDto.getTaskInfoEntity().getTaskInfoId());

        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("idでタスク情報を呼び出せませんでした", 1);
        }

        TaskInfoEntity entityHistory = optional.get();
        LeastUserDto userDto = capsuleDto.getUserDto();
        setTableDataHistoryUtil.practiceDelete(userDto, entityHistory);
        taskInfoRepository.save(entityHistory);
        
        TaskInfoEntity entityNew = capsuleDto.getTaskInfoEntity();
        setTableDataHistoryUtil.practiceInsert(userDto, entityNew);

        entityNew.setTaskInfoId(0); // auto increment

        return taskInfoRepository.save(entityNew).getTaskInfoId();
    }

}
