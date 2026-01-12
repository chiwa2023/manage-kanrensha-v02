import { JwtTokenDto } from "../dto/login/jwtTokenDto";
import { LeastUserDto } from "../dto/user/leastUserDto";
import { useUserInfoStore } from '../stores/storeUserInfo';

function logout() {
        const userInfo = useUserInfoStore();
        userInfo.jwtDto = new JwtTokenDto();
        userInfo.userDto = new LeastUserDto();
}

export { logout }