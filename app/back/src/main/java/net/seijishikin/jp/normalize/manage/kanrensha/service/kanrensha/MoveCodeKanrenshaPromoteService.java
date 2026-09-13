package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.ShinseiStatusConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodePromoteCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaCodeMoveEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaCodeMoveRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.file.ModifyTempToStorageFileService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.task_plan.InsertTaskRoleOnlyService;

/**
 * 関連者コード移動申請登録Service
 */
@Service
public class MoveCodeKanrenshaPromoteService {

    /** 一時ファイル本ファイル複写兼タスク登録Service */
    @Autowired
    private ModifyTempToStorageFileService modifyTempToStorageFileService;

    /** 権限のみタスク登録Service */
    // 特に今回は申請者本人は本人タスクとしない
    @Autowired
    private InsertTaskRoleOnlyService insertTaskRoleOnlyService;

    /** 関連者コード移動Repository */
    @Autowired
    private KanrenshaCodeMoveRepository kanrenshaCodeMoveRepository;

    /** テーブル履歴設定til */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param createDateTime 作成時間
     * @param capsuleDto     ユーザ最小限
     * @return 保存Id
     * @throws IOException ファイル保存例外
     */
    @Transactional
    public Integer practice(final LocalDateTime createDateTime, final MoveKanrenshaCodePromoteCapsuleDto capsuleDto)
            throws IOException {

        // 一時フォルダからユーザ本フォルダに複写
        LeastUserDto userDto = capsuleDto.getUserDto();
        Integer taskYear = createDateTime.getYear();

        Integer strageId = modifyTempToStorageFileService.practiceId(taskYear, userDto, capsuleDto.getStorageFileDto(),
                (short) 0);

        // 役割対象のタスク登録をする
        Map<String, String> mapParam = new TreeMap<>();
        insertTaskRoleOnlyService.practice(userDto, createDateTime, TaskInfoConstants.MOVE_KANRENSHA_CODE_ACCEPT,
                mapParam);

        // 関連者コード移動に登録
        KanrenshaCodeMoveEntity moveEntity = new KanrenshaCodeMoveEntity();
        BeanUtils.copyProperties(capsuleDto, moveEntity);
        moveEntity.setSaveFileStorageId(strageId);
        moveEntity.setMoveStatus(ShinseiStatusConstants.PROMOTE);
        moveEntity.setTaskYear(taskYear);
        
        // コード発行
        Integer code = 1;
        Optional<KanrenshaCodeMoveEntity> optional = kanrenshaCodeMoveRepository
                .findFirstByOrderByKanrenshaCodeMoveCode();
        if (!optional.isEmpty()) {
            code += optional.get().getKanrenshaCodeMoveCode();
        }
        moveEntity.setKanrenshaCodeMoveCode(code);
        setTableDataHistoryUtil.practiceInsert(userDto, moveEntity);
        moveEntity.setKanrenshaCodeMoveId(0); // auto increment明記

        return kanrenshaCodeMoveRepository.save(moveEntity).getKanrenshaCodeMoveId();
    }

}
