package net.seijishikin.jp.normalize.manage.kanrensha.service.dump_record;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.DumpRecordEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.DumpRecordRepository;

/**
 * ダンプ実行記録Service
 */
@Service
public class InsertDupmRunRecordService {

    /** ダンプ実行記録Respoitory */
    @Autowired
    private DumpRecordRepository dumpRecordRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param listTask      タスク情報リスト
     * @param startDatetime ダンプ開始条件
     * @param endDatetime   ダンプ終了条件
     * @param userDto       ユーザ最小限
     * @return 登録Idリスト
     */
    @Transactional
    public List<Integer> practice(final List<Integer> listTask, final LocalDateTime startDatetime,
            final LocalDateTime endDatetime, final LeastUserDto userDto) {

        List<DumpRecordEntity> listUpdate = new ArrayList<>();

        List<Integer> listAddId = new ArrayList<>();

        for (Integer infoCode : listTask) {
            // 現在最新を履歴に
            listUpdate.addAll(this.getUpdateList(infoCode, userDto));
            if (!listUpdate.isEmpty()) {
                dumpRecordRepository.saveAll(listUpdate);
            }

            // 最新を追加
            Integer savedId = dumpRecordRepository.save(this.getAdd(infoCode, userDto, startDatetime, endDatetime))
                    .getDumpRecordId();
            listAddId.add(savedId);

            listUpdate.clear();
        }

        return listAddId;
    }

    private List<DumpRecordEntity> getUpdateList(final Integer infoCode, final LeastUserDto userDto) {

        List<DumpRecordEntity> list = dumpRecordRepository.findByTaskInfoCodeAndIsLatestTrue(infoCode);
        for (DumpRecordEntity entity : list) {
            // 履歴に変更
            setTableDataHistoryUtil.practiceDelete(userDto, entity);
        }

        return list;
    }

    private DumpRecordEntity getAdd(final Integer infoCode, final LeastUserDto userDto,
            final LocalDateTime startDatetime, final LocalDateTime endDatetime) {

        DumpRecordEntity entity = new DumpRecordEntity();

        entity.setTaskInfoCode(infoCode);
        entity.setStartDatetime(startDatetime);
        entity.setEndDatetime(endDatetime);

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setDumpRecordId(0); // auto increment明記
        return entity;
    }

}
