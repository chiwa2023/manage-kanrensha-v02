package net.seijishikin.jp.normalize.manage.kanrensha.service.file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public LookAheadCsvResultDto practice(final int month, final UploadFileDto uploadFileDto) throws IOException {

        // 保存場所の設定
        LookAheadCsvResultDto resultDto = new LookAheadCsvResultDto();
        StorageFileDto storageFileDto = getTempFilePathLogic.practice(month, uploadFileDto.getFileName());
        resultDto.setStorageFileDto(storageFileDto);

        Path path = getAbsolutePathLogic.practice(storageFileDto.getSavedDir(),storageFileDto.getFileName());

        if (saveFileLogic.practice(path, uploadFileDto.getFileContent())) {

            List<List<String>> listCsv = new ArrayList<>();
            try (Stream<String> stream = Files.lines(path, Charset.forName(uploadFileDto.getCharset()))
                    .limit(READ_LINE)) {
                Iterator<String> iterator = stream.iterator();
                // 最初の1行だけはカラム数がほしいのでWhile処理に混ぜない
                String[] cell = iterator.next().split(",");
                int count = cell.length;
                listCsv.add(Arrays.asList(cell));

                while (iterator.hasNext()) {
                    List<String> temp = Arrays.asList(iterator.next().split(","));
                    listCsv.add(this.addColumn(count, temp));
                }
            }

            resultDto.setTableData(listCsv);
            return resultDto;

        } else {
            // ファイルが正常保存できなかった場合
            return null;
        }

    }

    private List<String> addColumn(final int count, final List<String> list) {
        
        // Arrays.asListでは不変リストが作成されるので作り直し
        List<String> listData = new ArrayList<>(list);

        if (count > list.size()) {
            int start = list.size();
            for (int index = start; index < count; index++) {
                listData.add("");
            }
        }

        return listData;
    }

}
