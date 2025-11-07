package net.seijishikin.jp.normalize.manage.kanrensha.controller.z_trial;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.GetAbsolutePathLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.SampleTableRepository;

/**
 * 機能疎通確認Controller
 */
@RestController
public class TrialAccessController {

    /** 絶対パス取得Logic */
    @Autowired
    private GetAbsolutePathLogic getAbsolutePathLogic;

    /** サンプルテーブルRepository */
    @Autowired
    private SampleTableRepository sampleTableRepository;

    /**
     * 処理を行う
     *
     * @return 表示文字列
     */
    @GetMapping("/trial-access")
    public ResponseEntity<String> practice() {

        final String BLANK = "";
        final String KAIGYOU = "\n";
        // ルートを取得
        Path path = getAbsolutePathLogic.practice(BLANK, BLANK);

        StringBuilder builder = new StringBuilder("access success!!").append(KAIGYOU);
        try {

            builder.append("ファイルストレージのルート：").append(path.toString()).append(KAIGYOU);
            boolean isFolderExist = Files.exists(path);
            builder.append("ストレージの存在確認：").append(isFolderExist).append(KAIGYOU);

            if (!isFolderExist) {
                Path saved = Files.createDirectories(path);
                builder.append("作成確認：").append(Files.exists(saved)).append(KAIGYOU);
            }

            boolean isExistSampleTable = sampleTableRepository.findMyself().isEmpty();

            // DB疎通確認
            if (isExistSampleTable) {
                builder.append("sample_tableが存在しません").append(KAIGYOU);
            } else {
                builder.append("sample_tableが作成されています").append(KAIGYOU);
            }

        } catch (Exception exception) { // NOPMD AvoidCatchingGenericException controller-runtime
            StringBuilder builderException = new StringBuilder();
            for (StackTraceElement element : exception.getStackTrace()) {
                builderException.append(element.toString()).append(KAIGYOU);
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(builderException.toString());
        }

        return ResponseEntity.status(HttpStatus.OK).body(builder.toString());
    }

}
