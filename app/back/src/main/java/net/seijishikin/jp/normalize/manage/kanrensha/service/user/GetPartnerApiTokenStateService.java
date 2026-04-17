package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.PartnerAccessTokenStateDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessTokenEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.PartnerAccessTokenRepository;

/**
 * APIユーザトークン状況取得Service
 */
@Service
public class GetPartnerApiTokenStateService {

    /** APIパートナー長期トークン管理Repository */
    @Autowired
    private PartnerAccessTokenRepository partnerAccessTokenRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 長期トークン状態取得条件Dto
     * @return 長期トークン状態
     */
    public PartnerAccessTokenStateDto practice(final FrameworkCapsuleDto capsuleDto) {

        // PartnerAccessTokenEntity(トークン)を直接frontに持ち込まないように移し替え
        PartnerAccessTokenStateDto stateDto = new PartnerAccessTokenStateDto();

        List<PartnerAccessTokenEntity> list = partnerAccessTokenRepository
                .findByUserCodeAndRevokedAtNull(capsuleDto.getUserDto().getUserPersonCode());

        if (list.isEmpty()) {
            throw new EmptyResultDataAccessException("トークン管理状態が取得できませんでした", 1);
        }

        PartnerAccessTokenEntity tokenEntity = list.get(0);
        BeanUtils.copyProperties(tokenEntity, stateDto);

        return stateDto;
    }

}
