package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.history;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

/**
 * 関連者個人Mapper
 */
@Component
public class KanrenshaPersonHistoryLineMapper implements LineMapper<KanrenshaPersonHistoryDto> {

    /** 団体名称カラム位置 */
    private static final int POS_NAME = 0;

    /** 団体名称カラム位置 */
    private static final int POS_ADDRESS = 1;

    /** 団体名称カラム位置 */
    private static final int POS_DELEGATE = 2;

    /** 団体名称カラム位置 */
    private static final int POS_KANRENSHA_CODE = 3;

    /**
     * 処理を行う
     */
    @Override
    public KanrenshaPersonHistoryDto mapLine(final String line, final int lineNumber) throws Exception {

        KanrenshaPersonHistoryDto historyDto = new KanrenshaPersonHistoryDto();
        String[] cell = line.split(",");

        historyDto.setKanrenshaName(this.removeQuote(cell[POS_NAME]));
        historyDto.setAllAddress(this.removeQuote(cell[POS_ADDRESS]));
        historyDto.setPersonShokugyou(this.removeQuote(cell[POS_DELEGATE]));
        historyDto.setPersonKanrenshaCode(this.removeQuote(cell[POS_KANRENSHA_CODE]));

        return historyDto;
    }

    private String removeQuote(final String data) {

        String dataNew = data;

        return dataNew.replaceAll("\"", "");
    }
}
