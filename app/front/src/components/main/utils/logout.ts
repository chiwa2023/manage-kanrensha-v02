import { JwtTokenDto } from "../dto/login/jwtTokenDto";
import { LeastUserDto } from 'seijishikin-jp-normalize_common-tool';
import { useUserInfoStore } from '../stores/storeUserInfo';

function logout() {
        const userInfo = useUserInfoStore();
        userInfo.jwtDto = new JwtTokenDto();
        userInfo.userDto = new LeastUserDto();
}

export { logout }