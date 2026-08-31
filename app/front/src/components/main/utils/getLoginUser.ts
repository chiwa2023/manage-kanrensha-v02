import { LeastUserDto } from 'seijishikin-jp-normalize_common-tool';
import { useUserInfoStore } from '../stores/storeUserInfo';

function getLoginUser(): LeastUserDto {

    const userInfo = useUserInfoStore();
    return userInfo.userDto;
}

export { getLoginUser }