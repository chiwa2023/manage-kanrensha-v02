package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

/**
 * 個人団体紐づけLineMapper
 */
@Component
public class KanrenshaCombineOrgLineMapper implements LineMapper<KanrenshaCombineOrgDto> {

    /** 個人関連者コードカラム位置 */
    private static final int POS_PERSON_CODE = 0;

    /** 個人関連者氏名カラム位置 */
    private static final int POS_PERSON_NAME = 1;

    /** 個人関連者コードカラム位置 */
    private static final int POS_ORG_CODE = 2;

    /** 個人関連者氏名カラム位置 */
    private static final int POS_ORG_NAME = 3;

    /** 登録開始年カラム位置 */
    private static final int POS_YEAR_START = 4;

    /** 登録終了年カラム位置 */
    private static final int POS_YEAR_END = 5;

    /** 空文字 */
    private static final String EMPTY = "";
    
    
    /**
     * 処理を行う
     */
    @Override
    public KanrenshaCombineOrgDto mapLine(final String line, final int lineNumber) throws Exception {
        KanrenshaCombineOrgDto dto = new KanrenshaCombineOrgDto();
        String[] cell = line.split(",");

        dto.setPersonKanrenshaCode(this.removeQuote(cell[POS_PERSON_CODE]));
        dto.setPersonName(this.removeQuote(cell[POS_PERSON_NAME]));
        dto.setOrgKanrenshaCode(this.removeQuote(cell[POS_ORG_CODE]));
        dto.setOrgName(this.removeQuote(cell[POS_ORG_NAME]));
        dto.setStartYear(this.convertShort(this.removeQuote(cell[POS_YEAR_START])));
        dto.setEndYear(this.convertShort(this.removeQuote(cell[POS_YEAR_END])));
        
        return dto;
    }

    private String removeQuote(final String data) {

        String dataNew = data;

        return dataNew.replaceAll("\"", EMPTY);
    }

    private Short convertShort(final String data) {

        try {
            return Short.valueOf(data);
        } catch (NumberFormatException numberFormatException) {
            return Short.valueOf("-1");
        }

    }

}
