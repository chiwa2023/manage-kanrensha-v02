package net.seijishikin.jp.normalize.manage.kanrensha.utils;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;

/**
 * テスト専用操作者ユーザ最小限を生成する
 */
public final class CreateLeastUserForTestUtil {

    /**
     * インスタンス生成よけ
     */
    private CreateLeastUserForTestUtil() {

    }

    /**
     * 処理を行う
     *
     * @return テスト専用操作者ユーザ最小限
     */
    public static LeastUserDto practice() {

        LeastUserDto dto = new LeastUserDto();

        final int personId = 196;
        final int personCode = 190;

        dto.setUserPersonId(personId);
        dto.setUserPersonCode(personCode);
        dto.setUserPersonName("管理人　太郎");

        return dto;
    }
}
