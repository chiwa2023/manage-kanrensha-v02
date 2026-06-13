package net.seijishikin.jp.normalize.manage.kanrensha.service.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.LookAheadCsvResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.StorageFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.UploadFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.GetAbsolutePathLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.GetTempFilePathLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.SaveFileLogic;

/**
 * アップロードされたCSVファイルを10行先読みService
 */
@Service
public class LookAheadCsvFileService {

    /** 一時ファイルパス取得Logic */
    @Autowired
    private GetTempFilePathLogic getTempFilePathLogic;

    /** ファイル絶対パス取得Logic */
    @Autowired
    private GetAbsolutePathLogic getAbsolutePathLogic;

    /** ファイル保存Logic */
    @Autowired
    private SaveFileLogic saveFileLogic;

    /** 読み出し行ヘッダプラス10行 */
    private static final int READ_LINE = 11;

    /**
     * 処理を行う
     *
     * @param month         処理月(=保存場所)
     * @param uploadFileDto アップロードファイル内容
     * @return 頭出し結果
     * @throws IOException ファイルに関する例外
     */
    public LookAheadCsvResultDto practice(final int month, final UploadFileDto uploadFileDto)
            throws IOException, CsvValidationException {

        // 保存場所の設定
        LookAheadCsvResultDto resultDto = new LookAheadCsvResultDto();
        StorageFileDto storageFileDto = getTempFilePathLogic.practice(month, uploadFileDto.getFileName());
        resultDto.setStorageFileDto(storageFileDto);

        Path path = getAbsolutePathLogic.practice(storageFileDto.getSavedDir(), storageFileDto.getFileName());

        if (saveFileLogic.practice(path, uploadFileDto.getFileContent())) {

            List<List<String>> listCsv = new ArrayList<>();

            // openCsvを使用した実装に変更した
            BufferedReader bufferedReader = new BufferedReader(Files.newBufferedReader(path));
            try (CSVReader csvReader = new CSVReader(bufferedReader)) {

                int counter = 0;
                String[] cell;
                int maxCplumn = 0;
                List<String[]> listLine = new ArrayList<>();
                // ヘッダ+最大10行=11行を頭出し
                while ((cell = csvReader.readNext()) != null && counter < READ_LINE) {
                    // 最大列数を更新
                    if (maxCplumn < cell.length) {
                        maxCplumn = cell.length;
                    }

                    listLine.add(cell);
                    counter++;
                }

                // 確認した最大列数を使って、最大10行分を再作成処理
                for (String[] csvCell : listLine) {
                    listCsv.add(this.addColumn(maxCplumn, csvCell));
                }

                resultDto.setTableData(listCsv);
            }

            resultDto.setTableData(listCsv);

            if (listCsv.isEmpty()) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("ファイル内のデータが取得できませんでした");
            }
            return resultDto;

        } else {
            resultDto.setIsFailure(true);
            resultDto.setMessage("ファイルが正常に保存できませんでした");
            return resultDto;
        }
    }

    private List<String> addColumn(final int count, final String[] cell) {

        List<String> listData = new ArrayList<>();
        for (int index = 0; index < count; index++) {
            if (cell.length > index) {
                listData.add(cell[index]);
            } else {
                listData.add("");
            }
        }
        return listData;
    }

}
