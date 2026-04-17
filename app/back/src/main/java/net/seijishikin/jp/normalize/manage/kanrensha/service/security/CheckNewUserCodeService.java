package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.NewComerDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserNewEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserNewRepository;

/**
 * 新規コードチェックService
 */
@Service
public class CheckNewUserCodeService {

    /** 新規登録中ユーザRepository */
    @Autowired
    private UserNewRepository userNewRepository;

    /**
     * 処理を行う
     *
     * @param newComerDto 新規ユーザDto
     * @return コード入力したDto
     */
    public NewComerDto practice(final NewComerDto newComerDto) {

        Optional<UserNewEntity> optional = userNewRepository.findById(newComerDto.getMailAddress());

        NewComerDto resultDto = new NewComerDto();
        BeanUtils.copyProperties(newComerDto, resultDto);
        final String blank = "";
        if (optional.isEmpty()) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("データが見つかりませんでした");

        } else {
            UserNewEntity entity = optional.get();

            // 指定時刻が有効期限より前
            if (newComerDto.getLimitDateTime().isBefore(entity.getLimitDatetime())
                    && !DtoEntityInitialValueInterface.INIT_TIMESTAMP.equals(entity.getLimitDatetime())) {
                resultDto.setLimitDateTime(newComerDto.getLimitDateTime());
            } else {
                resultDto.setIsFailure(true);
                resultDto.setMessage("期限切れです");
            }

            if (newComerDto.getRegistCode().equals(entity.getRegistCode())
                    && !blank.equals(newComerDto.getRegistCode())) {
                resultDto.setRegistCode(entity.getRegistCode());
            } else {
                resultDto.setIsFailure(true);
                resultDto.setMessage("コードが一致しません");
            }

            if (blank.equals(resultDto.getMessage())) {
                resultDto.setIsFailure(false);
                resultDto.setMessage("コードを確認できました");
            }
        }

        return resultDto;
    }
}
