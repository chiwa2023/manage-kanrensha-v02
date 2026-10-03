package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaCodeMoveEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaCodeMoveRepository;

/**
 * コード移行申請状況自分自身検索Service
 */
@Service
public class MoveCodeKanrenshaSearchMyselfService {

    /** 関連者コード移動Repository */
    @Autowired
    private KanrenshaCodeMoveRepository kanrenshaCodeMoveRepository;

    /**
     * 処理を行う
     * 
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    public List<KanrenshaCodeMoveEntity> practice(final String kanrenshaCode) {

        return kanrenshaCodeMoveRepository.findMyselfData(kanrenshaCode);
    }

}
