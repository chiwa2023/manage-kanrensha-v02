package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min;

import java.text.Normalizer;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

/**
 * 関連者企業・団体最小登録LineMapper
 */
@Component
public class KanrenshaKigyouDtAddMiniLineMapper implements LineMapper<KanrenshaKigyouDtAddMiniDto> {

    /** 名称カラム位置 */
    private static final int POS_NAME = 0;

    /** 全住所カラム位置 */
    private static final int POS_ADDRESS = 1;

    /** 団体代表者カラム位置 */
    private static final int POS_DELEGATE = 2;

    /** 法人番号 */
    private static final int POS_HOUJIN_NO = 3;

    /** ハイフン */
    private static final String HYPHEN = "-";
    /** 空文字 */
    private static final String EMPTY = "";

    /**
     * 処理を行う
     */
    @Override
    public KanrenshaKigyouDtAddMiniDto mapLine(final String line, final int lineNumber) throws Exception {
        KanrenshaKigyouDtAddMiniDto dto = new KanrenshaKigyouDtAddMiniDto();
        String[] cell = line.split(",");

        dto.setKanrenshaName(this.removeQuote(cell[POS_NAME]));
        dto.setAllAddress(this.removeQuote(cell[POS_ADDRESS]));
        dto.setKigyouDtDelegate(this.removeQuote(cell[POS_DELEGATE]));
        String seiki = Normalizer.normalize(this.removeQuote(cell[POS_HOUJIN_NO]), Normalizer.Form.NFKC)
                .replaceAll(HYPHEN, EMPTY);
        dto.setHoujinNo(seiki);
        
        return dto;
    }

    private String removeQuote(final String data) {

        String dataNew = data;

        return dataNew.replaceAll("\"", EMPTY);
    }

}
