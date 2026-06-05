package net.seijishikin.jp.normalize.manage.kanrensha.logic.lgcode;

import java.util.Objects;

import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;

/**
 * 住所住居最新挿入SQL作成Logic
 */
@Component
public class CreateSqlInsertAddressRsdtLogic {

    /** カンマ */
    private static final String COMMA = ",";

    /** シングルクォーテーション */
    private static final String QUOTE_SINGLE = "'";

    /**
     * 処理を行う
     * 
     * @param userDto            ユーザ最小限Dto
     * @param entity             住所住居Entity
     * @param timestampIsoString 記録時間
     * @return SQL
     */
    public String practice(final LeastUserDto userDto, final AddressRsdtTemplateEntity entity,
            final String timestampIsoString) {

        StringBuilder builder = new StringBuilder("INSERT INTO address_rsdt_");
        builder.append(entity.getLgCode())
                .append(" (address_rsdt_id , lg_code , postalcode1 , postalcode2 , machiaza_id , prc_id , ") // NOPMD
                .append(" blk_id , rsdt_id , rsdt2_id , address_block , address_building , ")
                .append(" effect_date , abolish_date , is_latest , insert_user_id , ")
                .append(" insert_user_code, insert_user_name , insert_timestamp , delete_user_id ,  ")
                .append(" delete_user_code ,delete_user_name , delete_timestamp) VALUES (0,")
                .append(this.createInsertParameter(entity, timestampIsoString, userDto)).append(" )");

        return builder.toString();
    }

    private String createInsertParameter(final AddressRsdtTemplateEntity entity, final String timestampIsoString,
            final LeastUserDto userDto) {
        StringBuilder builder = new StringBuilder();

        builder.append(QUOTE_SINGLE).append(entity.getLgCode()).append(QUOTE_SINGLE).append(COMMA) //
                .append(QUOTE_SINGLE).append(entity.getPostalcode1()).append(QUOTE_SINGLE).append(COMMA)
                .append(QUOTE_SINGLE).append(entity.getPostalcode2()).append(QUOTE_SINGLE).append(COMMA)
                .append(QUOTE_SINGLE).append(entity.getMachiazaId()).append(QUOTE_SINGLE).append(COMMA)
                .append(QUOTE_SINGLE).append(entity.getPrcId()).append(QUOTE_SINGLE).append(COMMA) //
                .append(QUOTE_SINGLE).append(entity.getBlkId()).append(QUOTE_SINGLE).append(COMMA) //
                .append(QUOTE_SINGLE).append(entity.getRsdtId()).append(QUOTE_SINGLE).append(COMMA) //
                .append(QUOTE_SINGLE).append(entity.getRsdt2Id()).append(QUOTE_SINGLE).append(COMMA)
                .append(QUOTE_SINGLE).append(entity.getAddressBlock()).append(QUOTE_SINGLE).append(COMMA)
                .append(QUOTE_SINGLE).append(entity.getAddressBuilding()).append(QUOTE_SINGLE).append(COMMA)
                .append(QUOTE_SINGLE).append(entity.getEffectDate()).append(QUOTE_SINGLE).append(COMMA);

        // null insert時にはコンマを外さないと例外を食らってしまう
        if (Objects.isNull(entity.getAbolishDate())) {
            builder.append(entity.getAbolishDate()).append(COMMA);
        } else {
            builder.append(QUOTE_SINGLE).append(entity.getAbolishDate()).append(QUOTE_SINGLE).append(COMMA);
        }

        builder.append(QUOTE_SINGLE).append(1).append(QUOTE_SINGLE).append(COMMA).append(QUOTE_SINGLE)
                .append(userDto.getUserPersonId()).append(QUOTE_SINGLE).append(COMMA).append(QUOTE_SINGLE)
                .append(userDto.getUserPersonCode()).append(QUOTE_SINGLE).append(COMMA).append(QUOTE_SINGLE)
                .append(userDto.getUserPersonName()).append(QUOTE_SINGLE).append(COMMA).append(QUOTE_SINGLE)
                .append(timestampIsoString).append(QUOTE_SINGLE).append(COMMA).append(QUOTE_SINGLE)
                .append(entity.getDeleteUserId()).append(QUOTE_SINGLE).append(COMMA).append(QUOTE_SINGLE)
                .append(entity.getDeleteUserCode()).append(QUOTE_SINGLE).append(COMMA).append(QUOTE_SINGLE)
                .append(entity.getDeleteUserName()).append(QUOTE_SINGLE).append(COMMA).append(QUOTE_SINGLE)
                .append(entity.getDeleteTimestamp()).append(QUOTE_SINGLE);

        return builder.toString();
    }

}
