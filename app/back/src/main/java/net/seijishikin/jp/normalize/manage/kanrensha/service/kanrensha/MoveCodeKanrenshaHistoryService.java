package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeHistoryCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeHistoryResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;

/**
 * 関連者コード移動履歴Service
 */
@Service
public class MoveCodeKanrenshaHistoryService {

    /** ユーザ個人Repository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public MoveKanrenshaCodeHistoryResultDto practice(final MoveKanrenshaCodeHistoryCapsuleDto capsuleDto) {

        String role = KanrenshaKbnConstants.getUserRole(capsuleDto.getKanrenshaKbn());
        String codeOrgin = capsuleDto.getCodeOrgin();
        String codeAbolish = capsuleDto.getCodeAbolish();

        MoveKanrenshaCodeHistoryResultDto resultDto = new MoveKanrenshaCodeHistoryResultDto();

        // 存在すれば存続コード×履歴
        resultDto.setListUserOrgin(userPersonRepository.getUserHistoryList(codeOrgin, role));

        // 存在すれば存続コード×廃止コード履歴
        resultDto.setListUserAbolish(userPersonRepository.getUserHistoryList(codeAbolish, role));

        // 存在すれば存続コード所持者ユーザ権限履歴
        resultDto.setListCodeOrgin(userPersonRepository.getCodeHistoryList(codeOrgin, role));
        // 存在すれば廃止コード所持者ユーザ権限履歴
        resultDto.setListCodeAbolish(userPersonRepository.getCodeHistoryList(codeAbolish, role));

        return resultDto;
    }
}
