package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.all;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;

/**
 * 主に住所初期データ作成用ユーザを作成する
 */
public final class CreateSystemInitialUserUtil {

    /**
     * インスタンス生成よけ
     */
    private CreateSystemInitialUserUtil() {

    }

    /**
     * 処理を行う
     *
     * @return 初期データ作成ユーザ最小限
     */
    public static LeastUserDto practice() {

        LeastUserDto dto = new LeastUserDto();

        final int personId = 1;
        final int personCode = 1;

        dto.setUserPersonId(personId);
        dto.setUserPersonCode(personCode);
        dto.setUserPersonName("sys_init");

        return dto;
    }
}
