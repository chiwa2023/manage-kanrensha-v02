package net.seijishikin.jp.normalize.manage.kanrensha.logic.postal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalCommonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.ConvertNumberUtil;

/**
 * 読点の入った複数データ分割Logic
 */
@Component
public class SplitIrregularToutenLogic {

    /** カッコ文字前 */
    private static final String KEY_EMP = "（";
    /** カッコ文字後 */
    private static final String KEY_EMP_END = "）";

    /** 読点 */
    private static final String KEY_TOUTEN = "、";

    /** 空文字 */
    private static final String KEY_EMPTY = "";

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     * 
     * @param irregularEntity 郵便番号不規則
     * @return 読点で分割したワークテーブルリスト
     */
    public List<WkTblPostalCommonEntity> practice(final AddressPostalIrregularEntity irregularEntity,
            final LeastUserDto userDto) {

        String org = irregularEntity.getAddressOrg();
        int posEmp = org.indexOf(KEY_EMP);
        String core = org.substring(0, posEmp);

        String range = org.substring(posEmp + 1, org.indexOf(KEY_EMP_END));
        String[] cell = range.split(KEY_TOUTEN);
        int lastPos = cell.length - 1;

        String last = cell[lastPos];

        // 数字以外の表示を分割リストに追加する
        String addType = this.getAddType(last);

        List<WkTblPostalCommonEntity> list = new ArrayList<>();
        // 空文字が返って未処理でない場合は共通した処理
        if (!KEY_EMPTY.equals(addType)) {

            for (String cl : cell) {
                StringBuilder builder = new StringBuilder(); // NOPMD
                builder.append(core).append(KEY_EMP);
                if (cl.contains(addType)) {
                    builder.append(cl).append(KEY_EMP_END);
                } else {
                    builder.append(cl).append(addType).append(KEY_EMP_END);
                }
                list.add(this.createWkTblEntity(builder, irregularEntity, userDto));
            }

        }
        return list;
    }

    private String getAddType(final String data) {

        try {
            // int posHyphen = data.indexOf("−");
            int posNamisen = data.indexOf("〜");
            int tempPos = posNamisen;
            //if (posHyphen > posNamisen) {
            //    tempPos = posHyphen;
            //} else {
            //    tempPos = posNamisen;
            //}
            String lastData;
            if (-1 == tempPos) {
                lastData = data;
            } else {
                lastData = data.substring(tempPos, data.length());
            }

            int num = Integer.parseInt(ConvertNumberUtil.practice(lastData).replaceAll("[^0-9]", KEY_EMPTY));

            return lastData.substring(String.valueOf(num).length() , lastData.length());

        } catch (NumberFormatException e) {
            // 数字がついていない場合は手作業空文字
            return KEY_EMPTY;
        }

    }

    private WkTblPostalCommonEntity createWkTblEntity(final StringBuilder builder,
            final AddressPostalIrregularEntity irregularEntity, final LeastUserDto userDto) {

        WkTblPostalCommonEntity entity = new WkTblPostalCommonEntity();
        BeanUtils.copyProperties(irregularEntity, entity);
        entity.setAddressOrg(builder.toString());
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setAddressPostalIrregularId(0); // 追記用にIdを消去

        return entity;
    }

}
