package net.seijishikin.jp.normalize.manage.kanrensha.controller.file;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.service.file.DeleteStorageTempFileService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * ストレージ一時ファイル削除Controller
 * 将来front側から動かす可能性も含めて、タイマー処理とも実装形態を合わせる意味でもControllerとしているが
 * pathも設定していないし、返り値はResponseEntityでないので修正は必要(不必要にパスを設定してアクセスできるようにしない)
 */
@RestController
public class DeleteStorageTempFileController {

    /** ストレージ一時ファイル削除Service */
    @Autowired
    private DeleteStorageTempFileService deleteStorageTempFileService;

    /** 例外記録Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @return 起動結果
     */
    public boolean practice() {

        LocalDate now = LocalDate.now();
        try {
            deleteStorageTempFileService.practice(now.getMonthValue());
        } catch (Exception exception) { // NOPMD 業務上の理由で積極的に許容
            // 起動直後にだけなんかあったら中止連絡
            // 起動しているのが非同期処理なので処理結果は取得できない
            saveStackTraceService.practice(exception, now.getYear(), 0);
            return false;
        }
        return true;
    }

}
