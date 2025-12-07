package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.history;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

/**
 * 関連者政治団体Mapper
 */
@Component
public class KanrenshaSeijidantaiHistoryLineMapper implements LineMapper<KanrenshaSeijidantaiHistoryDto> {

    /** 団体名称カラム位置 */
    private static final int POS_NAME = 0;

    /** 団体名称カラム位置 */
    private static final int POS_ADDRESS = 1;

    /** 団体名称カラム位置 */
    private static final int POS_DELEGATE = 2;

    /** 団体名称カラム位置 */
    private static final int POS_KANRENSHA_CODE = 3;

    /** 団体代表者コードカラム位置 */
    private static final int POS_DELEGATE_CODE = 4;

    /**
     * 処理を行う
     */
    @Override
    public KanrenshaSeijidantaiHistoryDto mapLine(final String line, final int lineNumber) throws Exception {

        KanrenshaSeijidantaiHistoryDto historyDto = new KanrenshaSeijidantaiHistoryDto();
        String[] cell = line.split(",");

        historyDto.setKanrenshaName(this.removeQuote(cell[POS_NAME]));
        historyDto.setAllAddress(this.removeQuote(cell[POS_ADDRESS]));
        historyDto.setSeijidantaiDelegate(this.removeQuote(cell[POS_DELEGATE]));
        historyDto.setSeijidantaiKanrenshaCode(this.removeQuote(cell[POS_KANRENSHA_CODE]));
        historyDto.setOrgDelegateCode(this.removeQuote(cell[POS_DELEGATE_CODE]));

        return historyDto;
    }

    private String removeQuote(final String data) {

        String dataNew = data;

        return dataNew.replaceAll("\"", "");
    }
}
