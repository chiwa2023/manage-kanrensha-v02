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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;

/**
 * 最低限ユーザ(メール)取得Service
 */
@Service
public class GetLeastUserByMailService {

    /** ユーザ人物Repository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /** ユーザ権限Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

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
        listAuh.remove("FACTOR_PASSWORD"); // 権限でない値は消す
        personDto.setListRoles(listAuh);

        List<UserRoleEntity> listEntity = userRoleRepository.findByEmailAndIsLatestTrue(email);
        final int INIT_NUM = 0;
        final String BLANK = "";
        for (UserRoleEntity entity : listEntity) {
            String role = entity.getRole();
            if (role.startsWith("kanrensha_")) {
                String kanrenshaCode = entity.getKanrenshaCode();
                if (!BLANK.equals(kanrenshaCode)) {
                    personDto.setKanrenshaCode(kanrenshaCode);
                    personDto.setKanrenshaRole(role);
                }
            } else {
                Integer riyoushaCode = entity.getRiyoushaCode();
                if (INIT_NUM != riyoushaCode) {
                    personDto.setRiyoushaCode(riyoushaCode);
                    personDto.setRiyoushaRole(role);
                }
            }
        }

        return personDto;
    }

}
