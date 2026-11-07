package net.seijishikin.jp.normalize.manage.kanrensha.service.dump_record;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.DumpRecordEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.DumpRecordRepository;

/**
 * ダンプ最新実行記録取得Service
 */
@Service
public class GetDupmRunRecordService {

    /** ダンプ実行記録Respoitory */
    @Autowired
    private DumpRecordRepository dumpRecordRepository;

    /**
     * 処理を行う
     * 
     * @param listCode 取得タスク情報リスト
     * @return 検索結果
     */
    public List<DumpRecordEntity> practice(final List<Integer> listCode) {

        return dumpRecordRepository.findByTaskInfoCodeInAndIsLatestTrue(listCode);
    }
}
