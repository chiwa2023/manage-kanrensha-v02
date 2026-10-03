package net.seijishikin.jp.normalize.manage.kanrensha.service.year_option;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.YearOptionEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.YearOptionRepository;

/**
 * 利用可能年取得処理
 */
@Service
public class GetYearOptionService {

    /** 紐づけ年選択肢Repository */
    @Autowired
    private YearOptionRepository yearOptionRepository;

    /**
     * 処理を行う
     * 
     * @return 選択肢リスト
     */
    public List<YearOptionEntity> practice() {

        return yearOptionRepository.findAll();
    }

}
