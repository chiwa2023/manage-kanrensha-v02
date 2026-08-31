package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.GetUserDtoResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
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
            resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_NO_CONTENT);
            return resultDto;
        }

        UserPersonEntity entityOperator = optionalOperator.get();

        LeastUserDto userDto = new LeastUserDto();
        userDto.setUserPersonId(entityOperator.getUserPersonId());
        userDto.setUserPersonCode(entityOperator.getUserPersonCode());
        userDto.setUserPersonName(entityOperator.getUserPersonName());

        // 取得した権限から詳細情報が入っているロールを特定してコードを格納
        List<UserRoleEntity> listEntity = userRoleRepository.findByEmailAndIsLatestTrue(entityOperator.getEmail());
        List<String> listRole = new ArrayList<>();
        final int INIT_NUM = 0;
        final String BLANK = "";
        for (UserRoleEntity entity : listEntity) {
            String role = entity.getRole();
            listRole.add("ROLE_"+role);
            if (role.startsWith("kanrensha_")) {
                String kanrenshaCode = entity.getKanrenshaCode();
                if (!BLANK.equals(kanrenshaCode)) {
                    userDto.setKanrenshaCode(kanrenshaCode);
                    userDto.setKanrenshaRole(role);
                }
            } else {
                Integer riyoushaCode = entity.getRiyoushaCode();
                if (INIT_NUM != riyoushaCode) {
                    userDto.setRiyoushaCode(riyoushaCode);
                    userDto.setRiyoushaRole(role);
                }
            }
        }
        userDto.setListRoles(listRole);

        resultDto.setUserDto(userDto);
        resultDto.setIsAlertTaskStart(entityOperator.getIsAlertTaskStart());
        resultDto.setIsAlertTaskEnd(entityOperator.getIsAlertTaskEnd());
        resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_EXPECTED);

        return resultDto;
    }

}
