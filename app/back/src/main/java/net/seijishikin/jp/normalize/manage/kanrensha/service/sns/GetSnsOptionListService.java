package net.seijishikin.jp.normalize.manage.kanrensha.service.sns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.sns.SnsServiceOptionDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.SnsServiceRepository;

/**
 * SNSサービス選択肢項目取得Service
 */
@Service
public class GetSnsOptionListService {

    /** SNSサービスRepository */
    @Autowired
    private SnsServiceRepository snsServiceRepository;

    /**
     * 処理を行う
     * 
     * @return 選択肢リスト
     */
    public List<SnsServiceOptionDto> practice() {

        List<SnsServiceOptionDto> list = snsServiceRepository.getSelectOptions();
        list.add(0, new SnsServiceOptionDto(0, "", 0, "")); // 未選択用空項目

        return list;
    }

}
