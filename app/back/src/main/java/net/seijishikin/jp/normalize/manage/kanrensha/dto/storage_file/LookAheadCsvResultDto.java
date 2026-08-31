package net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;

/**
 * Csv先読み結果Dto
 */
public class LookAheadCsvResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** ファイル格納Dto */
    private StorageFileDto storageFileDto;

    /**
     * ファイル格納Dtoを取得する
     *
     * @return ファイル格納Dto
     */
    public StorageFileDto getStorageFileDto() {
        return storageFileDto;
    }

    /**
     * ファイル格納Dtoを設定する
     *
     * @param storageFileDto ファイル格納Dto
     */
    public void setStorageFileDto(final StorageFileDto storageFileDto) {
        this.storageFileDto = storageFileDto;
    }

    /** 頭出しcsvデータ */
    private List<List<String>> tableData = new ArrayList<>();

    /**
     * 頭出しcsvデータを取得する
     *
     * @return 頭出しcsvデータ
     */
    public List<List<String>> getTableData() {
        return tableData;
    }

    /**
     * 頭出しcsvデータを設定する
     *
     * @param tableData 頭出しcsvデータ
     */
    public void setTableData(final List<List<String>> tableData) {
        this.tableData = tableData;
    }

}
