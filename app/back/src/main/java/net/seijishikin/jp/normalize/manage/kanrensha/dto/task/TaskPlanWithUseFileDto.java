package net.seijishikin.jp.normalize.manage.kanrensha.dto.task;

import java.nio.file.Path;

/**
 * タスク計画と同時使用ファイル格納Dto
 */
public class TaskPlanWithUseFileDto extends TaskPlanInfoDto {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 読み取りファイル */
    private Path readFile = Path.of(INIT_STRING);

    /**
     * 読み取りファイルを取得する
     * 
     * @return 読み取りファイル
     */
    public Path getReadFile() {
        return readFile;
    }

    /**
     * 読み取りファイルを設定する
     * 
     * @param readFile 読み取りファイル
     */
    public void setReadFile(final Path readFile) {
        this.readFile = readFile;
    }
}
