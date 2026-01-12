import { LeastUserDto, type LeastUserDtoInterface } from "../../../../main/dto/user/leastUserDto";
import UserRoleConstants from "../../../../main/dto/user/userRoleConstants";

export default function mockGetEditUser(userId:number):LeastUserDtoInterface{
    const dto:LeastUserDtoInterface = new LeastUserDto();

    dto.userPersonId = userId;
    dto.userPersonCode = userId-30; 
    dto.userPersonName = "aaa運営者太郎";

    dto.listRoles.push(UserRoleConstants.PARTNER_API);
    dto.listRoles.push(UserRoleConstants.MANAGER);
    //dto.listRoles.push(UserRoleConstants.KANRENSHA_PERSON);
    dto.listRoles.push(UserRoleConstants.KANRENSHA_SEIJIDANTAI);

    return dto;
}