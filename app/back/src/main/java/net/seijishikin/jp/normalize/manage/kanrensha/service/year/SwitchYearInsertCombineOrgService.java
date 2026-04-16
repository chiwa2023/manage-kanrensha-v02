package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org.CombineOrgCsvProcessor;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.InsertCombineOrgY2025Logic;


/**
 * 年ごとで個人団体紐づけに挿入Service
 */
@Component
public class SwitchYearInsertCombineOrgService {

    /** 空文字 */
    private static final String BLANK = "";

    /** 登録年区切り文字 */
    public static final String YEAR_SPLITER = CombineOrgCsvProcessor.YEAR_SPLITER;

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** タスク計画挿入Logic(2025) */
    @Autowired
    private InsertCombineOrgY2025Logic insertCombineOrgY2025Logic;

    /**
     * 処理を行う
     *
     * @param entity 個人団体紐づけワークテーブルEntity
     * @param userDto ユーザ最小限Dto
     * @return 処理結果(すべて登録できたらtrue)
     */
    @Transactional
    public Boolean practice(final WkTblKanrenshaCombineOrgEntity entity, final LeastUserDto userDto) {

        final Integer noRecord = 0;

        if (!BLANK.equals(entity.getYearArrayText())) {
            // String conditon = this.createCondition(entity);
            for (String year : entity.getYearArrayText().split(YEAR_SPLITER)) {

                switch (Integer.parseInt(year)) {

                    // 2025年
                    case YEAR_2025:
                        if (noRecord.equals(insertCombineOrgY2025Logic.practice(entity, userDto))) {
                            return false;
                        }
                        break;

                    default:
                        // TODO 本当はIllegalArgumentExceptionを流すが、まだ作成中のため何もしない

                        break;
                }

            }
        }

        return true;
    }

}
