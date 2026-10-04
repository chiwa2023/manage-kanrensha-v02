package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.code_move;

import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaCodeMoveEntity;

/**
 * 関連者コード移動CSv出力Dto変換Processor
 */
@Component
public class DumpKanrenshaCodeMoveProcessor
        implements ItemProcessor<KanrenshaCodeMoveEntity, DumpKanrenshaCodeMoveDto> {

    /**
     * 変換処理を実行する
     */
    @Override
    public DumpKanrenshaCodeMoveDto process(final KanrenshaCodeMoveEntity item) throws Exception {

        DumpKanrenshaCodeMoveDto dto = new DumpKanrenshaCodeMoveDto();
        BeanUtils.copyProperties(item, dto);

        dto.setKanrenshaKbnName(KanrenshaKbnConstants.getLabel(dto.getKanrenshaKbn()));

        // 廃止したコードのデータが最初の時だけ注意喚起する
        if (dto.getIsAbolishLast()) {
            dto.setSaishinName("廃止が最新");
        }

        return dto;
    }

}
