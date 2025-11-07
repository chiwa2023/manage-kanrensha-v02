package net.seijishikin.jp.normalize.manage.kanrensha.logic.file;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * StackTraceを保存するフォルダを取得する
 */
@Component
public class GetStackTraceFolderLogic {

    /** 絶対パス取得Logic */
    @Autowired
    private GetAbsolutePathLogic getAbsolutePathLogic;

    /**
     * 処理を行う
     *
     * @param taskPlanCode タスク計画コード
     * @param datetime     現在日時
     * @return StackTrace保存フォルダ
     */
    public Path practice(final Integer taskYear, final Integer taskPlanCode, final LocalDateTime datetime) {

        String traceRootDir = "stack_trace";
        Path path;
        if (Objects.isNull(taskPlanCode)) {
            // フォルダを構成する要素両方がない場合は例外
            if (Objects.isNull(datetime)) {
                throw new IllegalArgumentException("タスク計画コードと取得日の両方がnullです");
            }

            DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyyMMdd");
            path = Paths.get(getAbsolutePathLogic.getStorageFolder(), traceRootDir, datetime.format(formatterDate));

        } else {
            // 計画コードが指定してあっても発生年の指定がない場合はタスクを特定できないので例外
            if (Objects.isNull(taskYear)) {
                throw new IllegalArgumentException("タスク計画コードの指定はありますが、発生年の指定がありません");
            }

            path = Paths.get(getAbsolutePathLogic.getStorageFolder(), traceRootDir, "task_plan",
                    String.valueOf(taskYear), String.valueOf(taskPlanCode));
        }

        return path;
    }

}
