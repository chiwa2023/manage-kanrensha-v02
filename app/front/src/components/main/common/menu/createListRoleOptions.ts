import { SelectOptionStringDto, type SelectOptionStringDtoInterface } from "../../dto/select_options/selectOptionStringDto";
import UserRoleConstants from "../../dto/user/userRoleConstants";

function createListRoleOptions(listUserRole: string[]): SelectOptionStringDtoInterface[] {

    const list: SelectOptionStringDtoInterface[] = [];
    list.push(createDto(""));

    for (let role of listUserRole) {
        switch (role) {
            case UserRoleConstants.ROLE_ADMIN:
                list.push(createDto(UserRoleConstants.ROLE_ADMIN));
                break;
            case UserRoleConstants.ROLE_MANAGER:
                list.push(createDto(UserRoleConstants.ROLE_MANAGER));
                break;
            case UserRoleConstants.ROLE_PARTNER_API:
                list.push(createDto(UserRoleConstants.ROLE_PARTNER_API));
                break;
            case UserRoleConstants.ROLE_KANRENSHA_KIGYOU_DT:
                list.push(createDto(UserRoleConstants.ROLE_KANRENSHA_KIGYOU_DT));
                break;
            case UserRoleConstants.ROLE_KANRENSHA_PERSON:
                list.push(createDto(UserRoleConstants.ROLE_KANRENSHA_PERSON));
                break;
            case UserRoleConstants.ROLE_KANRENSHA_SEIJIDANTAI:
                list.push(createDto(UserRoleConstants.ROLE_KANRENSHA_SEIJIDANTAI));
                break;
            default:
                // 何もしない
                break;
        }
    }

    return list;
}
function createDto(role: string) {
    const dto: SelectOptionStringDtoInterface = new SelectOptionStringDto();
    dto.value = role;
    dto.text = UserRoleConstants.getLabel(role);
    return dto;
}

export { createListRoleOptions }    