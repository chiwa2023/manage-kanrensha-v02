import { LeastUserDto } from "../dto/user/leastUserDto";
import { useUserInfoStore } from '../stores/storeUserInfo';

function getLoginUser(): LeastUserDto {

    const userInfo = useUserInfoStore();
    return userInfo.userDto;
}

export { getLoginUser }