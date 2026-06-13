package net.seijishikin.jp.normalize.manage.kanrensha.service.houjin_no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.houjin_no.HoujinNoDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.houjin_no.SearchHoujinNoCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.houjin_no.SearchHoujinNoResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.houjin_no.CreateSearchHoujinNoUrlLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.houjin_no.ValidateCreateSearchHoujinNoUrlLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 法人番号取得Service
 */
@Service
public class GetHoujinNoService {

    /** サーバステータスOK */
    private static final int STATUS_200 = 200;

    /** サーバステータス400 */
    private static final int STATUS_400 = 400;

    /** サーバステータス403 */
    private static final int STATUS_403 = 403;

    /** サーバステータス404 */
    private static final int STATUS_404 = 404;

    /** サーバステータス500 */
    private static final int STATUS_500 = 500;

    /** 読み取り位置1 */
    private static final int POS_1 = 1;

    /** 読み取り位置1 */
    private static final int POS_2 = 2;

    /** 読み取り位置1 */
    private static final int POS_3 = 3;

    /** 読み取り位置6 */
    private static final int POS_6 = 6;

    /** 読み取り位置6 */
    private static final int POS_8 = 8;

    /** 法人番号APIアクセスUrl作成Logic */
    @Autowired
    private ValidateCreateSearchHoujinNoUrlLogic validateCreateSearchHoujinNoUrlLogic;

    /** 法人番号APIアクセスUrl作成Logic */
    @Autowired
    private CreateSearchHoujinNoUrlLogic createSearchHoujinNoUrlLogic;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** 接続ドメイン */
    private static final String DOMAIN_URL = "http://localhost:7080/4/name?";

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public SearchHoujinNoResultDto pratice(final SearchHoujinNoCapsuleDto capsuleDto) { // NOPMD CyclomaticComplexity

        final SearchHoujinNoResultDto resultDto = new SearchHoujinNoResultDto();

        try {
            validateCreateSearchHoujinNoUrlLogic.practice(DOMAIN_URL, capsuleDto);
            final URI uri = new URI(createSearchHoujinNoUrlLogic.pracctice(DOMAIN_URL, capsuleDto));
            final URL url = uri.toURL();

            // URLに紐づいたHttpURLConnectionインスタンスを生成 (http,
            // https両対応のためHttpsURLConnectionではなくHttpURLConnectionにする)
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // メソッドを設定
            conn.setRequestMethod("GET");
            // 接続を確立する
            conn.connect();

            // HTTP(S)接続の応答メッセージのステータスコードを返す
            final int statusCode = conn.getResponseCode();

            if (STATUS_400 == statusCode) {
                // エラーコードとメッセージがボディでわたってくるので処理
                return this.handleErrorResponseWithBody(conn);
            } else if (STATUS_200 == statusCode) {
                // 正常処理
                return this.getHoujiNoList(conn);

            } else {
                return this.handleErrorResponse(statusCode);
            }
        } catch (Exception exception) { // NOPMD

            saveStackTraceService.practice(exception, Year.now().getValue(), 0);

            resultDto.setIsFailure(true);
            resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_INTERNAL_ERROR);

            return resultDto;
        }
    }

    /**
     * エラーレスポンスを処理する
     * 
     * @param conn       HttpURLConnection
     * @param statusCode ステータスコード
     */
    private SearchHoujinNoResultDto handleErrorResponseWithBody(final HttpURLConnection conn)
            throws IOException, CsvValidationException {

        String message = "Error API : 400";
        try (InputStream input = conn.getErrorStream();
                InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(reader)) {
            String[] summaryRow;
            try (CSVReader csvReader = new CSVReader(bufferedReader)) {
                summaryRow = csvReader.readNext(); // 1行目を取得

                // 集計行データの確認（例: 1列目の値などを出力）
                if (summaryRow != null) {
                    message = "Error from API: " + summaryRow[2] + "(" + summaryRow[1] + ")";
                }
            }
        }
        SearchHoujinNoResultDto resultDto = new SearchHoujinNoResultDto();
        resultDto.setIsFailure(true);
        resultDto.setMessage(message);
        return resultDto;

    }

    /**
     * エラーレスポンスを処理する
     * 
     * @param conn       HttpURLConnection
     * @param statusCode ステータスコード
     */
    private SearchHoujinNoResultDto handleErrorResponse(final int statusCode) {

        SearchHoujinNoResultDto resultDto = new SearchHoujinNoResultDto();
        if (STATUS_403 == statusCode) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("このアプリケーションIDに対して機能制限がされています");
        } else if (STATUS_404 == statusCode) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("アプリケーションIDが登録されていません");
        } else if (STATUS_500 == statusCode) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("法人番号公表機能に障害等が発生しました");
        } else {
            resultDto.setIsFailure(true);
            resultDto.setMessage("HTTP error code: " + statusCode);
        }

        return resultDto;
    }

    private SearchHoujinNoResultDto getHoujiNoList(final HttpURLConnection conn)
            throws IOException, CsvValidationException {
        final SearchHoujinNoResultDto resultDto = new SearchHoujinNoResultDto();

        try (InputStream input = conn.getInputStream();
                InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(reader)) {

            // 1. まずCSVReaderを使って先頭の1行（集計行）だけを個別に読み込む
            String[] summaryRow;
            try (CSVReader csvReader = new CSVReader(bufferedReader)) {
                summaryRow = csvReader.readNext(); // 1行目を取得

                // 集計行データの確認（例: 1列目の値などを出力）
                if (summaryRow != null) {
                    resultDto.setUpdateDate(summaryRow[0]);
                    resultDto.setTotalCount(this.parseInteger(summaryRow[POS_1]));
                    resultDto.setDivideNumber(this.parseInteger(summaryRow[POS_2]));
                    resultDto.setDivideCount(this.parseInteger(summaryRow[POS_3]));
                }

                // 2.集計リスト
                List<HoujinNoDto> list = new ArrayList<>();
                String[] cell;
                while ((cell = csvReader.readNext()) != null) {
                    // 行をDtoに変換
                    list.add(this.convertHoujinNo(cell));
                }
                resultDto.setHoujinNoList(list);
            }
        }
        return resultDto;
    }

    /**
     * 文字列を数値にパースする。パースできない場合は0を返す。
     * 
     * @param value 文字列値
     * @return 数値
     */
    private Integer parseInteger(final String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private HoujinNoDto convertHoujinNo(final String[] cell) { // NOPMD UseVarArgs
        HoujinNoDto dto = new HoujinNoDto();
        dto.setHoujinNo(cell[POS_1]);
        dto.setHoujinName(cell[POS_6]);
        dto.setKind(cell[POS_8]);

        return dto;
    }

}
