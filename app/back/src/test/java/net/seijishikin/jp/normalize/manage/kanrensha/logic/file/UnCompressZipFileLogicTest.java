package net.seijishikin.jp.normalize.manage.kanrensha.logic.file;

import static org.junit.jupiter.api.Assertions.fail;

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

        unCompressZipFileLogic.practice(GetChibanConstants.STORED_PATH, unCompressPath);

        fail("Not yet implemented");
    }

}
