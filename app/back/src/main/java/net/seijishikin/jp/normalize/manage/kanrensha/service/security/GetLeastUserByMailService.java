package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;

/**
 * 最低限ユーザ(メール)取得Service
 */
@Service
public class GetLeastUserByMailService {

    /** ユーザ人物Repository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /**
     * 処理を行う
     *
     * @param email          メールアドレス
     * @param authentication 権限(role)
     * @return ユーザ最低限Dto
     */
    public LeastUserDto practice(final String email, final Authentication authentication) {

        LeastUserDto personDto = new LeastUserDto();

        // ユーザ呼び出し
        Optional<UserPersonEntity> optional = userPersonRepository.findLatestByMail(email);
        if (!optional.isEmpty()) {

            UserPersonEntity entity = optional.get();
            personDto.setUserPersonId(entity.getUserPersonId());
            personDto.setUserPersonCode(entity.getUserPersonCode());
            personDto.setUserPersonName(entity.getUserPersonName());
        }

        // 権限呼び出し
        List<String> listAuh = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        personDto.setListRoles(listAuh);

        return personDto;
    }

}
