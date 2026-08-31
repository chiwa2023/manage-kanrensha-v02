package net.seijishikin.jp.normalize.manage.kanrensha.controller.houjin_no;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;

/**
 * 法人番号取得APIのMockコントローラ
 */
@RestController
public class MockSendHoujinNoController {

    /**
     * 法人番号情報を取得する(Mock)
     *
     * @param id      アプリケーションID
     * @param name    商号又は名称
     * @param type    応答形式
     * @param mode    モード
     * @param target  ターゲット
     * @param address 住所
     * @param kind    種別
     * @param change  変更
     * @param close   閉鎖
     * @param from    開始日
     * @param to      終了日
     * @param divide  分割
     * @return CSV形式の法人番号情報
     * @throws IOException ファイル読み込み例外
     */
    @GetMapping(value = "/4/name", produces = "text/csv; charset=UTF-8")
    public ResponseEntity<String> getHoujinNo(final @RequestParam String id, // NOPMD CleanerAPI
            final @RequestParam String name, final @RequestParam String type,
            final @RequestParam(required = false) String mode, final @RequestParam(required = false) String target,
            final @RequestParam(required = false) String address, final @RequestParam(required = false) String kind,
            final @RequestParam(required = false) String change, final @RequestParam(required = false) String close,
            final @RequestParam(required = false) String from, //
            final @RequestParam(required = false) String to, // NOPMD ShortVariable
            final @RequestParam(required = false) String divide) throws IOException {

        if (name.contains("か")) {
            // 403ボディなし
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else if (name.contains("き")) {
            // 404ボディなし
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if (name.contains("く")) {
            // 500ボディなし
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else if (name.contains("け")) {
            // 504ボディなし
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).build();
        } else if (name.contains("こ")) {
            // 400 ボディあり
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.parseMediaType("text/csv; charset=UTF-8")).body(this.getError());
        } else {
            return ResponseEntity.ok().contentType(MediaType.parseMediaType("text/csv; charset=UTF-8"))
                    .body(this.getContent(name));
        }
    }

    /**
     * モックデータを取得する
     *
     * @param name 名称
     * @return CSV文字列
     * @throws IOException ファイル読み込み例外
     */
    private String getContent(final String name) throws IOException {

        Path testPath = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "file", "houjin_no");

        // external_api リポジトリに置いた実際の模擬法人番号APIでは異なるファイルを呼んでいる
        if (name.contains("あ")) {
            Path path = testPath.resolve("houjin_no_test91.csv");
            return Files.readString(path);
        } else if (name.contains("い")) {
            Path path = testPath.resolve("houjin_no_test92.csv");
            return Files.readString(path);

        } else if (name.contains("う")) {
            Path path = testPath.resolve("houjin_no_test93.csv");
            return Files.readString(path);

        } else {
            Path path = testPath.resolve("houjin_no_test90.csv");
            return Files.readString(path);
        }

    }

    private String getError() throws IOException {

        Path testPath = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "file", "houjin_no");
        Path path = testPath.resolve("houjin_no_test0.csv");
        return Files.readString(path);

    }
}
