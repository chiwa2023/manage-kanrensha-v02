import { LeastUserDto } from "../dto/user/leastUserDto";
import LoginConstants from "../dto/user/loginConstants";

function getLoginUser(): LeastUserDto {
    const userText: string | null = sessionStorage.getItem(LoginConstants.SESSION_KEY_USER);
    if (userText === null) {
        // 値が設定されていない場合は空Dtoを返す
        return new LeastUserDto();
    } else {
        // 値が入っている場合は変換して返却
        const userDto: LeastUserDto = JSON.parse(userText);
        // 正しく変換できない場合は値がundefinedになる
        if (undefined === userDto.listRoles) {
            return new LeastUserDto();
        }
        return userDto;
    }
}

export { getLoginUser }