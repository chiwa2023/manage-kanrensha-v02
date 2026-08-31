package net.seijishikin.jp.normalize.manage.kanrensha.service.task_info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.task_info.SearchTaskInfoCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task_info.SearchTaskInfoResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.TaskInfoRepository;

/**
 * タスク情報検索サービス
 */
@Service
public class SearchTaskInfoService {

    /** タスク情報Repository */
    @Autowired
    private TaskInfoRepository taskInfoRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto タスク情報検索Dto
     * @return 検索結果Dto
     */
    public SearchTaskInfoResultDto practice(final SearchTaskInfoCapsuleDto capsuleDto) {

        SearchTaskInfoResultDto resultDto = new SearchTaskInfoResultDto();
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        String words = "%" + capsuleDto.getSearchNaturalWords() + "%";
        String type = capsuleDto.getTaskType() + "%";

        resultDto.setAllCount(taskInfoRepository.countFullTextAndType(words, type));

        // 全件数が0の場合は結果を返却
        final Integer zero = 0;
        if (zero.equals(resultDto.getAllCount())) {
            resultDto.setPageNumber(0);
            return resultDto;
        }

        // 検索語を変更するなど、ページング条件で齟齬が発生した場合はページ番号を初期化
        if (resultDto.getAllCount() < resultDto.getLimit() * resultDto.getPageNumber()) {
            resultDto.setPageNumber(0);
        }

        // 実検索
        Pageable pageable = Pageable.ofSize(resultDto.getLimit()).withPage(resultDto.getPageNumber());
        resultDto.setListTask(taskInfoRepository.findFullTextAndType(words, type, pageable));

        return resultDto;
    }

}
