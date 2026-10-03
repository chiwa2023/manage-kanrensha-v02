package net.seijishikin.jp.normalize.manage.kanrensha.constants;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

/**
 * GetCurrentResourcePath単体テスト
 */
class GetCurrentResourcePathTest {

    @Test
    void testProject() {

        // テスト実施時、下記assertEquals実フォルダの値に変更し、assertEquals正しい値が取れることを確認
        assertNotEquals(Paths.get("C:/temp"), GetCurrentResourcePath.getProject(""));
        
        //assertEquals(Paths.get("C:/aaa/bbb/manage-kanrensha-v02"), GetCurrentResourcePath.getProject(""));
        
    }

}
