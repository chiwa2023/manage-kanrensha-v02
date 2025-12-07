package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_min;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

/**
 * 関連者個人最小登録LineMapper
 */
@Component
public class KanrenshaPersonAddMiniLineMapper implements LineMapper<KanrenshaPersonAddMiniDto> {

    /** 名称カラム位置 */
    private static final int POS_NAME = 0;

    /** 全住所カラム位置 */
    private static final int POS_ADDRESS = 1;

    /** 個人職業行カラム位置 */
    private static final int POS_SHOKUGYOU = 2;

    /** 空文字 */
    private static final String EMPTY = "";

    /**
     * 処理を行う
     */
    @Override
    public KanrenshaPersonAddMiniDto mapLine(final String line, final int lineNumber) throws Exception {
        KanrenshaPersonAddMiniDto dto = new KanrenshaPersonAddMiniDto();
        String[] cell = line.split(",");

        dto.setKanrenshaName(this.removeQuote(cell[POS_NAME]));
        dto.setAllAddress(this.removeQuote(cell[POS_ADDRESS]));
        dto.setPersonShokugyou(this.removeQuote(cell[POS_SHOKUGYOU]));
        
        return dto;
    }

    private String removeQuote(final String data) {

        String dataNew = data;

        return dataNew.replaceAll("\"", EMPTY);
    }

}
