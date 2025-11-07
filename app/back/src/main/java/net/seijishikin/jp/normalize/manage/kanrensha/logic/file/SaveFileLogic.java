package net.seijishikin.jp.normalize.manage.kanrensha.logic.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import org.springframework.stereotype.Component;

/**
 * ファイル保存処理Logic
 */
@Component
public class SaveFileLogic {

    /** Base64ヘッダ */
    private static final String BASE64_HEADER = ";base64,";

    /**
     * 処理を行う
     *
     * @param allPath  保存ファイル絶対パス
     * @param fileContent BASE64Encodeされたファイル内容
     * @return ファイル作成結果
     * @throws IOException ファイル書き込み例外
     */
    public boolean practice(final Path allPath, final String fileContent)
            throws IOException {

        // TODO Class名変更(Base64変換明記) 
        
        // Webから取得したデータの場合はMymeTypeのヘッダがついているので発見したら除去
        int pos = fileContent.indexOf(BASE64_HEADER);
        String content;
        if (-1 == pos) {
            content = fileContent;
        } else {
            content = fileContent.substring(pos + BASE64_HEADER.length(), fileContent.length());
        }

        // 配置ディレクトリがしなかったら作成
        if(!Files.exists(allPath.getParent())) {
            Files.createDirectories(allPath.getParent());
        }
        
        return Files.write(allPath, Base64.getDecoder().decode(content)).toFile().exists();
    }

}
