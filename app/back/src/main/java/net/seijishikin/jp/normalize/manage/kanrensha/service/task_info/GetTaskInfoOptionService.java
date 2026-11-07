package net.seijishikin.jp.normalize.manage.kanrensha.service.task_info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.task_info.TaskInfoCodeCheckOptionDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.TaskInfoRepository;

/**
 * タスク情報選択選択肢取得Service
 */
@Service
public class GetTaskInfoOptionService {

    /** タスク情報Repository */
    @Autowired
    private TaskInfoRepository taskInfoRepository;

    /**
     * 処理を行う
     * 
     * @return 検索結果
     */
    public List<TaskInfoCodeCheckOptionDto> practice() {

        return taskInfoRepository.findAllAlive();
    }
}
