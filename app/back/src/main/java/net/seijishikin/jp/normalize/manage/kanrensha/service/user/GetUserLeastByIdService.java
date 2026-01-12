package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.GetUserDtoResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;

/**
 * 編集対象をIdから取得Service
 */
@Service
public class GetUserLeastByIdService {

    /** ユーザ個人Repository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /** ユーザ権限Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

    /**
     * 処理を行う
     * 
     * @param editUserId 編集対象のId
     * @return 編集対象取得Dto
     */
    public GetUserDtoResultDto practcie(final Integer editUserId) {

        Optional<UserPersonEntity> optionalOperator = userPersonRepository.findById(editUserId);

        GetUserDtoResultDto resultDto = new GetUserDtoResultDto();
        if (optionalOperator.isEmpty()) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("編集対象が取得できませんでした");
            return resultDto;
        }

        UserPersonEntity entityOperator = optionalOperator.get();

        LeastUserDto userDto = new LeastUserDto();
        userDto.setUserPersonId(entityOperator.getUserPersonId());
        userDto.setUserPersonCode(entityOperator.getUserPersonCode());
        userDto.setUserPersonName(entityOperator.getUserPersonName());
        userDto.setListRoles(userRoleRepository.findLatestRoleByMail(entityOperator.getEmail()));

        resultDto.setUserDto(userDto);
        resultDto.setMessage("編集対象が取得できました");

        return resultDto;
    }

}
