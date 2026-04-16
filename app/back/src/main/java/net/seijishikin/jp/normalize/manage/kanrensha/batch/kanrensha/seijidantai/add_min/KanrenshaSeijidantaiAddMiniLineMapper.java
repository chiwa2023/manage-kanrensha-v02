package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

/**
 * 関連者政治団体最小登録LineMapper
 */
@Component
public class KanrenshaSeijidantaiAddMiniLineMapper implements LineMapper<KanrenshaSeijidantaiAddMiniDto> {

    /** 名称カラム位置 */
    private static final int POS_NAME = 0;

    /** 全住所カラム位置 */
    private static final int POS_ADDRESS = 1;

    /** 団体代表者カラム位置 */
    private static final int POS_DELEGATE = 2;

    /** 法人番号 */
    private static final int POS_DANTAI_KBN = 3;

    /** 空文字 */
    private static final String EMPTY = "";

    /**
     * 処理を行う
     */
    @Override
    public KanrenshaSeijidantaiAddMiniDto mapLine(final String line, final int lineNumber) throws Exception {
        KanrenshaSeijidantaiAddMiniDto dto = new KanrenshaSeijidantaiAddMiniDto();
        String[] cell = line.split(",");

        dto.setKanrenshaName(this.removeQuote(cell[POS_NAME]));
        dto.setAllAddress(this.removeQuote(cell[POS_ADDRESS]));
        dto.setSeijidantaiDelegate(this.removeQuote(cell[POS_DELEGATE]));
        dto.setDantaiKbn(this.removeQuote(cell[POS_DANTAI_KBN]));
        
        return dto;
    }

    private String removeQuote(final String data) {

        String dataNew = data;

        return dataNew.replaceAll("\"", EMPTY);
    }

}
