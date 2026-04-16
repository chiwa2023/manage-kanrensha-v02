package net.seijishikin.jp.normalize.manage.kanrensha.utils;


import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;

/**
 * 自動タスク起動用のユーザを取得する
 * TODO 本番コンパイル時にはこのファイルは上書きの対象
 */
@Component
public class GetAutoTaskExecuteUserUtil {

    /**
     * 処理を行う
     * 
     * @return ユーザ最小限
     */
    public LeastUserDto practice() {
        // CHECKSTYLE:OFF MagicNumbber
        // TODO 本番用自動処理ユーザ
        LeastUserDto userDto = new LeastUserDto();
        userDto.setUserPersonId(11);
        userDto.setUserPersonCode(7);
        userDto.setUserPersonName("システム自動処理");

        return userDto;
    }

}
