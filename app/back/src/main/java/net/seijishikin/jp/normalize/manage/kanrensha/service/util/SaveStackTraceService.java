package net.seijishikin.jp.normalize.manage.kanrensha.service.util;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.GetStackTraceFolderLogic;


/**
 * StackTrace保存Service
 */
@Service
public class SaveStackTraceService {

    /** 例外出力時のインデント */
    private static final String INDENT = "    at ";

    /** 改行文字 */
    private static final String KAIGYOU = "\n";

    /** StackTrace保存フォルダ取得Logic */
    @Autowired
    private GetStackTraceFolderLogic getStackTraceFolderLogic;

    /** Logger */
    private final Logger log = LoggerFactory.getLogger(SaveStackTraceService.class);

    /**
     * 処理を行う
     *
     * @param exception    発生例外
     * @param taskPlanCode 実行中のタスク計画コード
     */
    public Path practice(final Exception exception, final Integer taskYear, final Integer taskPlanCode) {

        // 保存しようとする例外がない場合は引数例外
        if (Objects.isNull(exception)) {
            throw new IllegalArgumentException("保存しようとしているExceptionがnullです");
        }

        Path path = null;
        try {
            LocalDateTime now = LocalDateTime.now();

            Path dir = getStackTraceFolderLogic.practice(taskYear, taskPlanCode, now);
            Files.createDirectories(dir);
            // ↓↓このサービス固有実装で例外が発生した場合はログが流れることを確認するコード
            // Files.createFile(dir);

            // バッチで11件目と12件目が連続して例外発生ということを想定するのでナノ秒でファイル名生成する
            DateTimeFormatter formatterUnixTime = DateTimeFormatter.ofPattern("yyyyMMddHHmmssnnnnnnnnn");
            String formattedDateTimeUnixTime = now.format(formatterUnixTime);

            path = Paths.get(dir.toString(),
                    formattedDateTimeUnixTime + "_" + this.getExceptionName(exception) + "_" + ".log");

            Files.writeString(path, this.createMessage(exception), StandardCharsets.UTF_8);

        } catch (IllegalArgumentException illegalArgumentException) {

            // 引数例外だけは実装段階で発見できるように投げる
            throw illegalArgumentException;

        } catch (Exception e) { // NOPMD すべての例外を網羅が目的

            // ファイル・フォルダ作成時のIOExceptionなどが相当。
            // ログに残っていたらラッキー。ログの出力場所とStackTraceの出力場所は変えて設定する
            log.error(this.createMessage(exception));
        }

        return path;
    }

    private String getExceptionName(final Exception exception) {
        String className = exception.getClass().getName();
        int pos = className.lastIndexOf('.');
        return className.substring(pos + 1, className.length());
    }

    private String createMessage(final Exception exception) {

        StringBuilder builder = new StringBuilder();

        builder.append(exception.toString()).append(KAIGYOU);
        for (StackTraceElement stackElement : exception.getStackTrace()) {
            builder.append(INDENT).append(stackElement.toString()).append(KAIGYOU);
        }

        return builder.toString();
    }

}
