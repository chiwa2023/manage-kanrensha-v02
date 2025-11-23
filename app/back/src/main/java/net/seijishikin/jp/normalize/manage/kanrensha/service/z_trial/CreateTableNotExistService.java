package net.seijishikin.jp.normalize.manage.kanrensha.service.z_trial;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.batch.reflesh.schema_table.CreateTableOnlyNothingTasklet;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 作成されていないテーブルを補充するTasklet非同期Service
 */
@Service
public class CreateTableNotExistService {

    /** 起動タスクレット */
    @Autowired
    private CreateTableOnlyNothingTasklet createTableOnlyNothingTasklet;

    /** 例外保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @throws Exception 一般例外
     */
    @Async
    public void practice() {

        try {
            createTableOnlyNothingTasklet.execute(null, null);
        } catch (Exception exception) { // NOPMD
            saveStackTraceService.practice(exception, Year.now().getValue(), -1);
        }

    }

}
