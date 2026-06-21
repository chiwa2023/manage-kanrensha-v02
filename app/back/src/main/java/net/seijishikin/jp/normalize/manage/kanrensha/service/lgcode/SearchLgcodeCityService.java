package net.seijishikin.jp.normalize.manage.kanrensha.service.lgcode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionStringDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchAllCityLgcodeCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressAllCityRepository;

/**
 * 地方自治体コード検索Service
 */
@Service
public class SearchLgcodeCityService {

    /** 地方自治体コードRepository */
    @Autowired
    private AddressAllCityRepository addressAllCityRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 地方自治体コード検索条件
     * @return 県地方自治体コード
     */
    public List<SelectOptionStringDto> practice(final SearchAllCityLgcodeCapsuleDto capsuleDto) {

        final String wild = "%";
        List<SelectOptionStringDto> list;
        if (capsuleDto.getIsSearch5Digit()) {
            list = addressAllCityRepository.findPrefCityDigit5(capsuleDto.getLgCode() + wild);

        } else {
            list = addressAllCityRepository.findPrefCity(capsuleDto.getLgCode() + wild);

        }

        // 先頭に未選択を追加
        list.addFirst(new SelectOptionStringDto("", ""));

        return list;
    }
}
