package net.seijishikin.jp.normalize.manage.kanrensha.controller.z_trial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.service.z_trial.CreateTableNotExistService;

/**
 * 作成されていないテーブルを補充するController
 */
@RestController
public class CreateTableNotExistController {

    /** 作成されていないテーブルを補充する非同期Service */
    @Autowired
    private CreateTableNotExistService createTableNotExistService;

    /**
     * 処理を行うう
     * 
     * @return 処理受付メッセージ
     */
    @GetMapping("/create-table")
    public ResponseEntity<String> practice() {

        try {
            createTableNotExistService.practice();
        } catch (Exception e) { // NOPMD GeneralException
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("作成作業を起動できませんでした");
        }

        return ResponseEntity.status(HttpStatus.OK).body("作業を開始しています。終了までお待ちください");
    }

}
