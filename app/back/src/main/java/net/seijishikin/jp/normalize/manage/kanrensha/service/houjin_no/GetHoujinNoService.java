package net.seijishikin.jp.normalize.manage.kanrensha.service.houjin_no;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.houjin_no.SearchHoujinNoCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.address.registory.WriteLogAddressFormatLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.houjin_no.CreateSearchHoujinNoUrlLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.houjin_no.ValidateCreateSearchHoujinNoUrlLogic;

/**
 * 法人番号取得Service
 */
@Service
public class GetHoujinNoService {

    /** サーバステータス400 */
    private static final int STATUS_400 = 400;

    /** サーバステータス403 */
    private static final int STATUS_403 = 403;

    /** サーバステータス404 */
    private static final int STATUS_404 = 404;

    /** サーバステータス500 */
    private static final int STATUS_500 = 500;

    /** 法人番号APIアクセスUrl作成Logic */
    @Autowired
    private ValidateCreateSearchHoujinNoUrlLogic validateCreateSearchHoujinNoUrlLogic;

    /** 法人番号APIアクセスUrl作成Logic */
    @Autowired
    private CreateSearchHoujinNoUrlLogic createSearchHoujinNoUrlLogic;

    /** 接続ドメイン */
    private static final String DOMAIN_URL = "http://localhost:7080/4/name?";

    /** Logger */
    @Autowired
    private WriteLogAddressFormatLogic writeLogAddressFormatLogic;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     */
    public void pratice(final SearchHoujinNoCapsuleDto capsuleDto) {

        try {
            validateCreateSearchHoujinNoUrlLogic.practice(DOMAIN_URL, capsuleDto);
            URI uri = new URI(createSearchHoujinNoUrlLogic.pracctice(DOMAIN_URL, capsuleDto));
            URL url = uri.toURL();

            // URLに紐づいたURLConnectionインスタンスを生成
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            // メソッドを設定
            conn.setRequestMethod("GET");
            // 接続を確立する
            conn.connect();

            // HTTP(S)接続の応答メッセージのステータスコードを返す
            int statusCode = conn.getResponseCode();

            // 取得内容のStream
            InputStream input = conn.getInputStream();

            
            
            
            
            if (STATUS_400 == statusCode) {
                // TODO inputの中にcsvがHTTP ステータス コード, エラー コード, エラーメッセージの順に1行だけ格納されているはずなので(本当か?)
                // エラーメッセージ(エラーコード)の形式でメッセージとして格納
            }

            if (STATUS_403 == statusCode) {
                // TODO このアプリケーションIdは機能制限されています(bodyなし)
            }
            if (STATUS_404 == statusCode) {
                // TODO アプリケーションIDが登録されていません(bodyなし)
            }
            if (STATUS_500 == statusCode) {
                // TODO 法人番号公表機能に障害等が発生しました(bodyなし)
            }

        } catch (Exception e) { // NOPMD
            writeLogAddressFormatLogic.practice(WriteLogAddressFormatLogic.ERROR, "not access!");

        }

    }
}
