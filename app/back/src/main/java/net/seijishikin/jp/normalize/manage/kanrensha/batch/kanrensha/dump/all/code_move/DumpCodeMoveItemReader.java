package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.code_move;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.infrastructure.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.ShinseiStatusConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaCodeMoveEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaCodeMoveRepository;

/**
 * 関連者コード移動Itemreader
 */
@Component
public class DumpCodeMoveItemReader extends RepositoryItemReader<KanrenshaCodeMoveEntity> {

    /**
     * コンストラクタ
     * 
     * @param kanrenshaCodeMoveRepository 関連者コード移動Repository
     */
    public DumpCodeMoveItemReader(final @Autowired KanrenshaCodeMoveRepository kanrenshaCodeMoveRepository) {
        super(kanrenshaCodeMoveRepository, new HashMap<>());
        super.setMethodName("findByIsLatestTrueAndMoveStatusAndInsertTimestampLessThan");

        List<Object> list = new ArrayList<>();
        super.setArguments(list); // NOPMD
    }

    /**
     * 起動条件を設定する
     *
     * @param stepExecution StepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        LocalDateTime datetimeEnd = stepExecution.getJobParameters().getLocalDateTime("datetimeEnd");

        List<Object> list = new ArrayList<>();
        list.add((int)ShinseiStatusConstants.ACCEPT);
        list.add(datetimeEnd);

        super.setArguments(list);
    }

}
