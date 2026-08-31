package net.seijishikin.jp.normalize.manage.kanrensha.logic.address.registory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressAllCityEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressAllCityRepository;

/**
 * ファイルダウンロード(アドレス・べース・レジストリから地番ファイルを県ごとに抜き出し)Logic
 */
@Component
public class GetChibanCsvLogic {

    /** 市区町村Repository */
    @Autowired
    private AddressAllCityRepository addressAllCityRepository;

    /** Logger */
    @Autowired
    private WriteLogAddressFormatLogic writeLogAddressFormatLogic;

    /**
     * 処理を行う
     *
     * @throws Exception 例外(取得できなかったらしょうがない)
     */
    public void practice(final String lgCodePref, final String storeDir) {

        List<AddressAllCityEntity> list = addressAllCityRepository.findByLgCodeStartingWith(lgCodePref);
        for (AddressAllCityEntity entity : list) {
            String lgCode = entity.getLgCode();

            try {
                URI uri = new URI(GetChibanConstants.ACCESS_URL + lgCode + ".csv.zip"); // NOPMD
                URL url = uri.toURL();

                // URLに紐づいたURLConnectionインスタンスを生成
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                // メソッドを設定
                conn.setRequestMethod("GET");
                // 接続を確立する
                conn.connect();

                // HTTP(S)接続の応答メッセージのステータスコードを返す
                int statusCode = conn.getResponseCode();
                writeLogAddressFormatLogic.practice(WriteLogAddressFormatLogic.INFO, "HTTP Status Code: ",
                        String.valueOf(statusCode), "===" + lgCode);
                this.saveFile(conn, lgCode, storeDir);

                Thread.sleep(1000); // SUPPRESS CHECKSTYLE MagicNumber // NOPMD

            } catch (Exception e) { // NOPMD
                writeLogAddressFormatLogic.practice(WriteLogAddressFormatLogic.ERROR, lgCode, "not access!");

            }
        }

    }

    private void saveFile(final HttpsURLConnection conn, final String lgCode, final String storeDir) {

        try (InputStream input = conn.getInputStream()) {

            File targetFile = new File(storeDir + lgCode + ".csv.zip");
            Files.copy(input, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException exception) { // NOPMD
            writeLogAddressFormatLogic.practice(WriteLogAddressFormatLogic.ERROR, lgCode, "not save!");
        }

    }

}
