package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.GetPromoteAdminResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.PromoteAdminEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.PromoteAdminRepository;

/**
 * SE権限追加推薦取得Service
 */
@Service
public class GetPromoteAdminService {

    /** SE権限追加推薦Repository */
    @Autowired
    private PromoteAdminRepository promoteAdminRepository;

    /**
     * 処理を行う
     * 
     * @param userDto ユーザ最小限Dto
     * @return 取得結果
     */
    public GetPromoteAdminResultDto practice(final LeastUserDto userDto) {

        List<PromoteAdminEntity> list = promoteAdminRepository
                .findByPromoteUserCodeAndIsLatestTrueOrderByInsertTimestampDesc(userDto.getUserPersonCode());

        GetPromoteAdminResultDto resultDto = new GetPromoteAdminResultDto();
        if (list.isEmpty()) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("追加推薦が見つかりませんでした");
            return resultDto;
        }

        // 取得できた最新を返却する
        // 複数人から数日間で数回推薦があった場合、1回で回答を済ませないと煩わしい
        resultDto.setPromoteAdminEntity(list.get(0));
        resultDto.setPromoteCount(list.size());
        return resultDto;
    }
}
