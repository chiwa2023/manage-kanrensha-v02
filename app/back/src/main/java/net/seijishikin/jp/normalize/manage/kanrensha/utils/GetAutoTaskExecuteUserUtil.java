package net.seijishikin.jp.normalize.manage.kanrensha.utils;


import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;

/**
 * 自動タスク起動用のユーザを取得する
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
        LeastUserDto userDto = new LeastUserDto();
        userDto.setUserPersonId(2);
        userDto.setUserPersonCode(2);
        userDto.setUserPersonName("システム自動処理");

        return userDto;
    }

}
