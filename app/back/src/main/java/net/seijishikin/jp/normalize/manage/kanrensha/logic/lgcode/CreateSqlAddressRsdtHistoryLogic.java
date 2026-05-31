package net.seijishikin.jp.normalize.manage.kanrensha.logic.lgcode;

import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;

/**
 * アドレス・ベース・レジストリを履歴にするSQL作成ロジック
 */
@Component
public class CreateSqlAddressRsdtHistoryLogic {

    /**
     * 処理を行う
     * 
     * @param userDto            ユーザ最小限Dto
     * @param lgCode             地方自治体コード
     * @param addressRsdtId      テーブルId
     * @param timestampIsoString 更新時間
     * @return 履歴に更新用SQL
     */
    public String practice(final LeastUserDto userDto, final String lgCode, final Integer addressRsdtId,
            final String timestampIsoString) {

        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE address_rsdt_").append(lgCode).append(" SET is_latest = 0 , delete_user_id = ")
                .append(userDto.getUserPersonId()).append(", delete_user_code = ").append(userDto.getUserPersonCode())
                .append(" , delete_user_name = '").append(userDto.getUserPersonName())
                .append("' , delete_timestamp = '").append(timestampIsoString).append("' WHERE address_rsdt_id = ")
                .append(addressRsdtId);

        return builder.toString();
    }
}
