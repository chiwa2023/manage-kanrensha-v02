package net.seijishikin.jp.normalize.manage.kanrensha.logic.file;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import net.seijishikin.jp.normalize.manage.kanrensha.logic.address.registory.GetChibanConstants;

/**
 * UnCompressZipFileLogic単体テスト
 */
class UnCompressZipFileLogicTest {

    @Test
    void test() throws Exception {

        String unCompressPath = "C:/temp/uncompress/address/";

        UnCompressZipFileLogic unCompressZipFileLogic = new UnCompressZipFileLogic();

        assertDoesNotThrow(() -> unCompressZipFileLogic.practice(GetChibanConstants.STORED_PATH, unCompressPath));
        // C:/temp/address/472018.csv.zip が解凍され
        // 圧縮内ファイル C:/temp/uncompress/address/mt_parcel_city472018.csvが存在すること
    }

}
