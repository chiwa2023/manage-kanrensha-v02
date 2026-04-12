package net.seijishikin.jp.normalize.manage.kanrensha.service.yotei;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.yotei.EditTimerYoteiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TimerYoteiEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.TimerYoteiRepository;

/**
 * 予約時刻更新Service
 */
@Service
public class UpdateTimerYoteiService {

    /** 予約実行Repository */
    @Autowired
    private TimerYoteiRepository timerYoteiRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 更新条件Dto
     * @return 更新後Id
     */
    @Transactional
    public Integer practice(final EditTimerYoteiCapsuleDto capsuleDto) {

        // Id呼び出しして呼び出せない場合は中断
        Integer deleteId = capsuleDto.getTimerYoteiEntity().getTimerYoteiId();
        Optional<TimerYoteiEntity> optional = timerYoteiRepository.findById(deleteId);
        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("更新対象が確認できませんでした:" + deleteId, 1);
        }

        // 履歴にして保存
        LeastUserDto userDto = capsuleDto.getUserDto();
        TimerYoteiEntity historyEntity = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, historyEntity);
        timerYoteiRepository.save(historyEntity);

        // 編集後データは最新として履歴を積む
        TimerYoteiEntity updateEntity = capsuleDto.getTimerYoteiEntity();
        setTableDataHistoryUtil.practiceInsert(userDto, updateEntity);
        updateEntity.setTimerYoteiId(0); // auto increment

        return timerYoteiRepository.save(updateEntity).getTimerYoteiId();
    }
}
